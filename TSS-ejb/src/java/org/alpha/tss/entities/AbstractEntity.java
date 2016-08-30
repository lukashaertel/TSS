/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Dr. Volker Riediger <riediger@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 7263522586632357632L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Transient
    private int hash;

    @PrePersist
    public void createId() {
        if (id == null) {
            id = System.nanoTime();
        }
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        if (hash != 0) {
            return hash;
        }
        if (id == null) {
            throw new IllegalStateException();
        }
        hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        if (hash == 0) {
            hash = 1;
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (id == null) {
            throw new IllegalStateException();
        }
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (other.id == null) {
            throw new IllegalStateException();
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " ID=" + getId();
    }
}
