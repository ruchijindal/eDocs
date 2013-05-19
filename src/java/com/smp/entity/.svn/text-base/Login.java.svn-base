/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "LOGIN")
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username"),
    @NamedQuery(name = "Login.findByUserid", query = "SELECT l FROM Login l WHERE l.userid = :userid"),
    @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password"),
    @NamedQuery(name = "Login.findByUserrole", query = "SELECT l FROM Login l WHERE l.userrole = :userrole"),
    @NamedQuery(name = "Login.findByDate", query = "SELECT l FROM Login l WHERE l.date = :date"),
    @NamedQuery(name = "Login.findByUseridAndPassword", query = "SELECT l FROM Login l WHERE l.userid = :userid and l.password= :password"),
    @NamedQuery(name = "Login.findByCreatedby", query = "SELECT l FROM Login l WHERE l.createdby = :createdby"),
    @NamedQuery(name = "Login.findByFkUserrole", query = "SELECT l FROM Login l WHERE l.fkUserrole = :fkUserrole")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "USERNAME")
    private String username;
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "USERROLE")
    private String userrole;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "CREATEDBY")
    private String createdby;
    @Column(name = "FK_USERROLE")
    private Integer fkUserrole;

    public Login() {
    }

    public Login(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Integer getFkUserrole() {
        return fkUserrole;
    }

    public void setFkUserrole(Integer fkUserrole) {
        this.fkUserrole = fkUserrole;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.entity.Login[userid=" + userid + "]";
    }
}
