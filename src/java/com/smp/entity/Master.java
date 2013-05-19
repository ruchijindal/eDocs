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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "MASTER")
@NamedQueries({
    @NamedQuery(name = "Master.findAll", query = "SELECT m FROM Master m"),
    @NamedQuery(name = "Master.findById", query = "SELECT m FROM Master m WHERE m.id = :id"),
    @NamedQuery(name = "Master.findByPropId", query = "SELECT m FROM Master m WHERE m.propId = :propId"),
    @NamedQuery(name = "Master.findByPropCode", query = "SELECT m FROM Master m WHERE m.propCode = :propCode"),
    @NamedQuery(name = "Master.findByPropNumber", query = "SELECT m FROM Master m WHERE m.propNumber = :propNumber"),
    @NamedQuery(name = "Master.findBySchemeCode", query = "SELECT m FROM Master m WHERE m.schemeCode = :schemeCode"),
    @NamedQuery(name = "Master.findByCatCode", query = "SELECT m FROM Master m WHERE m.catCode = :catCode"),
    @NamedQuery(name = "Master.findByAlloteeName", query = "SELECT m FROM Master m WHERE m.alloteeName = :alloteeName"),
    @NamedQuery(name = "Master.findByFatherName", query = "SELECT m FROM Master m WHERE m.fatherName = :fatherName"),
    @NamedQuery(name = "Master.findByAddress", query = "SELECT m FROM Master m WHERE m.address = :address"),
    @NamedQuery(name = "Master.findByPropertyType", query = "SELECT m FROM Master m WHERE m.propertyType = :propertyType"),
    @NamedQuery(name = "Master.findBySectionName", query = "SELECT m FROM Master m WHERE m.sectionName = :sectionName"),
    @NamedQuery(name = "Master.findByEstimationCost", query = "SELECT m FROM Master m WHERE m.estimationCost = :estimationCost"),
    @NamedQuery(name = "Master.findByFianlCost", query = "SELECT m FROM Master m WHERE m.fianlCost = :fianlCost"),
    @NamedQuery(name = "Master.findByAllotteeDate", query = "SELECT m FROM Master m WHERE m.allotteeDate = :allotteeDate"),
    @NamedQuery(name = "Master.findByRegistryDate", query = "SELECT m FROM Master m WHERE m.registryDate = :registryDate"),
    @NamedQuery(name = "Master.findByPossessionDate", query = "SELECT m FROM Master m WHERE m.possessionDate = :possessionDate"),
    @NamedQuery(name = "Master.findByTotalCatCode", query = "SELECT Distinct(m.catCode) FROM Master m "),
    @NamedQuery(name = "Master.findByTotalSchemeCode", query = "SELECT Distinct(m.schemeCode) FROM Master m "),
    @NamedQuery(name = "Master.findBySelectedItems", query = "SELECT m From Master m WHERE ((:schemeCode is null and :catCode is null) or (m.schemeCode = :schemeCode and m.catCode = :catCode)) and (:schemeName is null or m.schemeName = :schemeName) and (:propCode is null or m.propCode = :propCode) and (:propNumber is null or m.propNumber = :propNumber)and (:propId is null or m.propId = :propId) and (:alloteeName is null or m.alloteeName = :alloteeName)"),
    @NamedQuery(name = "Master.findBySelectedDate", query = "SELECT m From Master m WHERE ((:allotteeDate is null and :allotteeDate1 is null) or (m.allotteeDate >= :allotteeDate and m.allotteeDate<=:allotteeDate1 ))and ((:registryDate is null and :registryDate1 is null) or (m.registryDate >= :registryDate and m.registryDate<=:registryDate1 ))and ((:possessionDate is null and :possessionDate1 is null) or (m.possessionDate >= :possessionDate and m.possessionDate<=:possessionDate1 ))"),
    @NamedQuery(name = "Master.findBySchemeName", query = "SELECT m FROM Master m WHERE m.schemeName = :schemeName"),
    @NamedQuery(name = "Master.findByTotalSchemeName", query = "SELECT Distinct(m.schemeName) FROM Master m "),
    @NamedQuery(name = "Master.findByDisctinctSchemeCode", query = "SELECT Distinct(m.catCode) FROM Master m WHERE m.schemeCode = :schemeCode"),
    @NamedQuery(name = "Master.findByPropCodeAndPropId", query = "SELECT m FROM Master m WHERE m.propCode = :propCode AND m.propId= :propId"),
    @NamedQuery(name = "Master.findBySchemeCodeAndCatCodeAndPropId", query = "SELECT m FROM Master m WHERE m.schemeCode = :schemeCode AND m.catCode = :catCode AND m.propId = :propId")
})
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PROP_ID")
    private Integer propId;
    @Column(name = "PROP_CODE")
    private Integer propCode;
    @Column(name = "PROP_NUMBER")
    private Integer propNumber;
    @Column(name = "SCHEME_CODE")
    private String schemeCode;
    @Column(name = "CAT_CODE")
    private String catCode;
    @Column(name = "ALLOTEE_NAME")
    private String alloteeName;
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PROPERTY_TYPE")
    private String propertyType;
    @Column(name = "SECTION_NAME")
    private String sectionName;
    @Column(name = "ESTIMATION_COST")
    private Integer estimationCost;
    @Column(name = "FIANL_COST")
    private Integer fianlCost;
    @Column(name = "ALLOTTEE_DATE")
    @Temporal(TemporalType.DATE)
    private Date allotteeDate;
    @Column(name = "REGISTRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date registryDate;
    @Column(name = "POSSESSION_DATE")
    @Temporal(TemporalType.DATE)
    private Date possessionDate;
    @Column(name = "SCHEME_NAME")
    private String schemeName;

    public Master() {
    }

    public Master(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public Integer getPropCode() {
        return propCode;
    }

    public void setPropCode(Integer propCode) {
        this.propCode = propCode;
    }

    public Integer getPropNumber() {
        return propNumber;
    }

    public void setPropNumber(Integer propNumber) {
        this.propNumber = propNumber;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getAlloteeName() {
        return alloteeName;
    }

    public void setAlloteeName(String alloteeName) {
        this.alloteeName = alloteeName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getEstimationCost() {
        return estimationCost;
    }

    public void setEstimationCost(Integer estimationCost) {
        this.estimationCost = estimationCost;
    }

    public Integer getFianlCost() {
        return fianlCost;
    }

    public void setFianlCost(Integer fianlCost) {
        this.fianlCost = fianlCost;
    }

    public Date getAllotteeDate() {
        return allotteeDate;
    }

    public void setAllotteeDate(Date allotteeDate) {
        this.allotteeDate = allotteeDate;
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    public Date getPossessionDate() {
        return possessionDate;
    }

    public void setPossessionDate(Date possessionDate) {
        this.possessionDate = possessionDate;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
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
        if (!(object instanceof Master)) {
            return false;
        }
        Master other = (Master) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.entity.Master[id=" + id + "]";
    }
}
