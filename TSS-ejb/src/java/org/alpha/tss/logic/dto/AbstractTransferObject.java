/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.logic.dto;

import java.io.Serializable;

/**
 * @author Dr. Volker Riediger <riediger@uni-koblenz.de>
 */
public abstract class AbstractTransferObject implements Serializable {

    private static final long serialVersionUID = -3659487654436963178L;

    private long id;

    public AbstractTransferObject() {
    }

    public AbstractTransferObject(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractTransferObject other = (AbstractTransferObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (id=" + getId() + ")";
    }
}