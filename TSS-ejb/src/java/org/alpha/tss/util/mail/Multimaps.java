/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.util.mail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pazuzu
 */
public class Multimaps {
    public static final <K, V> boolean put(Map<K, Set<V>> multimap, K key, V value) {
        Set<V> values = multimap.get(key);
        if (values == null)
            values = new HashSet<>();
        boolean r = values.add(value);
        multimap.put(key, values);
        return r;
    }

    public static final <K, V> Set<V> get(Map<K, Set<V>> multimap, K key) {
        Set<V> values = multimap.get(key);
        if (values == null)
            values = Collections.emptySet();
        return values;
    }
}
