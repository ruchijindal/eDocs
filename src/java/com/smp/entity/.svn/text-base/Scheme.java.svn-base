/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "SCHEME")
@NamedQueries({
    @NamedQuery(name = "Scheme.findAll", query = "SELECT s FROM Scheme s"),
    @NamedQuery(name = "Scheme.findById", query = "SELECT s FROM Scheme s WHERE s.id = :id"),
    @NamedQuery(name = "Scheme.findByName", query = "SELECT s FROM Scheme s WHERE s.name = :name"),
    @NamedQuery(name = "Scheme.findByCode", query = "SELECT s FROM Scheme s WHERE s.code = :code")})
public class Scheme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CODE")
    private String code;

    public Scheme() {
    }

    public Scheme(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scheme)) {
            return false;
        }
        Scheme other = (Scheme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.entity.Scheme[id=" + id + "]";
    }

}
