/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Lukas Härtel <lukashaertel@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.alpha.tss.entities.AbstractEntity;
import org.alpha.tss.entities.ContractEntity_;
import org.alpha.tss.util.mail.Multimaps;

/**
 *
 * @author Lukas Härtel <lukashaertel@uni-koblenz.de>
 */
public class GenDTOs {

    public static void main(String[] args) throws IOException {
        // All classes
        Set<Class<?>> classes = getClasses(AbstractEntity.class.getPackage());

        // Remove the entity class itself
        classes.remove(AbstractEntity.class);

        // Remove non-entity classes in place
        for (Iterator<Class<?>> it = classes.iterator(); it.hasNext();)
            if (!AbstractEntity.class.isAssignableFrom(it.next()))
                it.remove();

        // Iterate classes
        for (Class<?> c : classes) {
            //Do not generate entity itself
            if (c.equals(AbstractEntity.class))
                continue;

            File target = new File("src/java/org/alpha/tss/logic/dto/" + translatedName(c) + ".java");
            try (PrintStream stream = new PrintStream(target)) {
                generateClass(c, stream);
            }
        }
    }

    private static boolean isPrefixed(String p, String q) {
        if (q.length() <= p.length())
            return false;
        return q.startsWith(p) && Character.isUpperCase(q.charAt(p.length()));
    }

    private static String unPrefixed(String p, String q) {
        return Character.toLowerCase(q.charAt(p.length())) + q.substring(p.length() + 1);
    }

    private static String rePrefixed(String p, String q) {
        return p + Character.toUpperCase(q.charAt(0)) + q.substring(1);
    }

    private static String translatedName(Class<?> c) {
        if (!c.getSimpleName().endsWith("Entity"))
            return c.getSimpleName();

        return c.getSimpleName().substring(0, c.getSimpleName().length() - "Entity".length());
    }

    private static String printType(Type t) {
        // TODO: Kinda hacky
        return t.getTypeName().replaceAll("org.alpha.tss.entities.(\\w*)Entity", "$1");
    }

    private static boolean isEntity(Type t) {
        return t instanceof Class<?> && AbstractEntity.class.isAssignableFrom((Class<?>) t);
    }

    private static boolean isEntitySet(Type t) {
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            return pt.getRawType().equals(Set.class) && isEntity(pt.getActualTypeArguments()[0]);
        }
        return false;
    }

    private static boolean isEntityList(Type t) {
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            return pt.getRawType().equals(List.class) && isEntity(pt.getActualTypeArguments()[0]);
        }
        return false;
    }

    private static Class<?> getEntityClass(Type t) {
        if (isEntity(t))
            return (Class<?>) t;

        if (isEntitySet(t) || isEntityList(t)) {
            ParameterizedType pt = (ParameterizedType) t;
            return (Class<?>) pt.getActualTypeArguments()[0];
        }

        throw new IllegalArgumentException();
    }

    private static void generateClass(Class<?> c, PrintStream target) {
        if (!c.getSimpleName().endsWith("Entity"))
            throw new IllegalArgumentException("Class does not end with the 'Entity' suffix.");

        // Find getters
        Map<String, Type> getters = new HashMap<>();
        Set<String> isSuper = new HashSet<>();

        for (Method m : c.getMethods()) {
            // Skip non-entity hierarchy methods
            if (AbstractEntity.class.equals(m.getDeclaringClass()))
                continue;
            if (!AbstractEntity.class.isAssignableFrom(m.getDeclaringClass()))
                continue;

            // Detect getters by name
            String name;
            if (m.getParameterCount() == 0 && isPrefixed("get", m.getName()))
                name = unPrefixed("get", m.getName());
            else if (m.getParameterCount() == 0 && isPrefixed("is", m.getName()))
                name = unPrefixed("is", m.getName());
            else
                continue;

            // Read field for annotations
            try {
                Field f = m.getDeclaringClass().getDeclaredField(name);
                
                // Filter non-local properties
                OneToOne oneToOne = f.getAnnotation(OneToOne.class);
                OneToMany oneToMany = f.getAnnotation(OneToMany.class);
                ManyToOne manyToOne = f.getAnnotation(ManyToOne.class);
                ManyToMany manyToMany = f.getAnnotation(ManyToMany.class);

                if (oneToOne != null && !translatedName(c).toLowerCase().equals(oneToOne.mappedBy()))
                    continue;
                if (oneToMany != null && !translatedName(c).toLowerCase().equals(oneToMany.mappedBy()))
                    continue;
                if (manyToOne != null)
                    continue;
                if (manyToMany != null && !translatedName(c).toLowerCase().equals(manyToMany.mappedBy()))
                    continue;
            } catch (NoSuchFieldException ex) {
                target.println("// Field metadata for " + name + " could not be accessed.");
                // No meta information readable
            }

            // Add to getters
            getters.put(name, m.getGenericReturnType());

            // Flag supertype if not declared here
            if (!m.getDeclaringClass().equals(c))
                isSuper.add(name);
        }

        ////////////////////////////////////////////////////////////////////////
        // Header
        ////////////////////////////////////////////////////////////////////////
        target.println("package org.alpha.tss.logic.dto;");
        target.println();
        target.println("@javax.xml.bind.annotation.XmlRootElement");
        target.println("@javax.annotation.Generated(value = \"" + GenDTOs.class.getName() + "\", date = \"" + LocalDate.now() + "\")");

        // Class header with superclass
        if (c.getSuperclass().equals(AbstractEntity.class))
            target.println("public class " + translatedName(c) + " extends AbstractTransferObject {");
        else
            target.println("public class " + translatedName(c) + " extends " + translatedName(c.getSuperclass()) + " {");

        target.println("\tprivate static final long serialVersionUID = 1L;");
        target.println();

        ////////////////////////////////////////////////////////////////////////
        // Fields
        ////////////////////////////////////////////////////////////////////////
        for (Entry<String, Type> getter : getters.entrySet()) {
            target.println("\tprivate " + printType(getter.getValue()) + " " + getter.getKey() + ";");
            target.println();
        }

        ////////////////////////////////////////////////////////////////////////
        // Constructors
        ////////////////////////////////////////////////////////////////////////
        // Unparameterized constructor
        target.println("\tpublic " + translatedName(c) + "() { }");
        target.println();

        // Parameterized constructor
        target.println("\tpublic " + translatedName(c) + "(long id");
        for (Entry<String, Type> getter : getters.entrySet())
            target.println("\t\t, " + printType(getter.getValue()) + " " + getter.getKey());
        target.println("\t) {");

        // Add super call
        target.println("\t\tsuper(id");
        for (String getter : getters.keySet())
            if (isSuper.contains(getter))
                target.println("\t\t\t, " + getter);
        target.println("\t\t);");

        // Add field initialziation
        for (String getter : getters.keySet())
            target.println("\t\tthis." + getter + " = " + getter + ";");
        target.println("\t}");
        target.println();

        ////////////////////////////////////////////////////////////////////////
        // Conversion
        ////////////////////////////////////////////////////////////////////////
        // Set Wrapper
        target.println("\tpublic static java.util.Set<" + translatedName(c) + "> wrap" + translatedName(c) + "(java.util.Set<" + c.getTypeName() + "> ins) {");
        target.println("\t\tif(ins == null) return null;");
        target.println("\t\tjava.util.Set<" + translatedName(c) + "> out = new java.util.HashSet<>();");
        target.println("\t\tfor(" + c.getTypeName() + " in : ins)");
        target.println("\t\t\tout.add(wrap" + translatedName(c) + "(in));");
        target.println("\t\treturn out;");
        target.println("\t}");

        // List Wrapper
        target.println("\tpublic static java.util.List<" + translatedName(c) + "> wrap" + translatedName(c) + "(java.util.List<" + c.getTypeName() + "> ins) {");
        target.println("\t\tif(ins == null) return null;");
        target.println("\t\tjava.util.List<" + translatedName(c) + "> out = new java.util.ArrayList<>();");
        target.println("\t\tfor(" + c.getTypeName() + " in : ins)");
        target.println("\t\t\tout.add(wrap" + translatedName(c) + "(in));");
        target.println("\t\treturn out;");
        target.println("\t}");

        // TO generator
        target.println("\t// Abstract TO conversion is not implemented");
        target.println("\t@java.lang.Deprecated");
        target.println("\tpublic static " + translatedName(c) + " wrap" + translatedName(c) + "(" + c.getTypeName() + " in) {");
        target.println("\t\tif(in == null) return null;");
        target.println("\t\treturn new " + translatedName(c) + "(in.getId()");

        // Add all getters in same order as they appear in the constructor
        for (Entry<String, Type> getter : getters.entrySet())
            if (isEntity(getter.getValue()) || isEntitySet(getter.getValue()) || isEntityList(getter.getValue()))
                // TO objects have a converter, use it on any incoming entities
                target.println("\t\t\t, " + translatedName(getEntityClass(getter.getValue())) + ".wrap" + translatedName(getEntityClass(getter.getValue())) + "(in." + rePrefixed("get", getter.getKey()) + "())");
            else if (Boolean.class.equals(getter.getValue()) || Boolean.TYPE.equals(getter.getValue()))
                // Boolean requires getters with 'is'
                target.println("\t\t\t, in." + rePrefixed("is", getter.getKey()) + "()");
            else
                // Usual getters get passed right through
                target.println("\t\t\t, in." + rePrefixed("get", getter.getKey()) + "()");

        target.println("\t\t);");
        target.println("\t}");

        ////////////////////////////////////////////////////////////////////////
        // Getters and setters
        ////////////////////////////////////////////////////////////////////////
        for (Entry<String, Type> getter : getters.entrySet()) {
            // Getter signature based on type
            if (Boolean.class.equals(getter.getValue()) || Boolean.TYPE.equals(getter.getValue()))
                target.println("\tpublic " + printType(getter.getValue()) + " " + rePrefixed("is", getter.getKey()) + "() {");
            else
                target.println("\tpublic " + printType(getter.getValue()) + " " + rePrefixed("get", getter.getKey()) + "() {");

            // Getter body
            target.println("\t\treturn " + getter.getKey() + ";");
            target.println("\t}");
            target.println();

            // Setter
            target.println("\tpublic void " + rePrefixed("set", getter.getKey()) + "(" + printType(getter.getValue()) + " " + getter.getKey() + ") {");
            target.println("\t\tthis." + getter.getKey() + " = " + getter.getKey() + ";");
            target.println("\t}");
            target.println();
        }

        // End of class
        target.println("}");

    }

    public static Set<Class<?>> getClasses(Package p) throws IOException {
        return getClasses(Thread.currentThread().getContextClassLoader(), p);
    }

    public static Set<Class<?>> getClasses(ClassLoader cl, Package p) throws IOException {
        Set<Class<?>> classes = new HashSet<>();
        URL upackage = cl.getResource(p.getName().replaceAll("[.]", "/"));

        DataInputStream dis = new DataInputStream((InputStream) upackage.getContent());
        String line;
        while ((line = dis.readLine()) != null) {
            if (line.endsWith(".class"))
                try {
                    classes.add(Class.forName(p.getName() + "." + line.substring(0, line.lastIndexOf('.'))));
                } catch (ClassNotFoundException ex) {
                    // Cannot load this class, wont break the rest of the class loading
                    Logger.getLogger(GenDTOs.class.getName()).log(Level.WARNING, null, ex);
                }
        }
        return classes;
    }
}
