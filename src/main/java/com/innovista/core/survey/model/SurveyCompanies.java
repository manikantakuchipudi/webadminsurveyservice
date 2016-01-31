/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovista.core.survey.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_companies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyCompanies.findAll", query = "SELECT s FROM SurveyCompanies s"),
    @NamedQuery(name = "SurveyCompanies.findByCompanyId", query = "SELECT s FROM SurveyCompanies s WHERE s.companyId = :companyId"),
    @NamedQuery(name = "SurveyCompanies.findByCompanyName", query = "SELECT s FROM SurveyCompanies s WHERE s.companyName = :companyName"),
    @NamedQuery(name = "SurveyCompanies.findByComapnyDetails", query = "SELECT s FROM SurveyCompanies s WHERE s.comapnyDetails = :comapnyDetails")})
public class SurveyCompanies implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_id")
    private Integer companyId;
    @Basic(optional = false)
    @Column(name = "company_name")
    private String companyName;
    @Basic(optional = false)
    @Column(name = "comapny_details")
    private String comapnyDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    @JsonIgnore
    private Collection<SurveyUser> surveyUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyCompanies")
    @JsonIgnore
    private Collection<SurveyQuestionsReport> surveyQuestionsReportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyCompanies")
    @JsonIgnore
    private Collection<SurveyQuestionsGridReport> surveyQuestionsGridReportCollection;

    public SurveyCompanies() {
    }

    public SurveyCompanies(Integer companyId) {
        this.companyId = companyId;
    }

    public SurveyCompanies(Integer companyId, String companyName, String comapnyDetails) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.comapnyDetails = comapnyDetails;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getComapnyDetails() {
        return comapnyDetails;
    }

    public void setComapnyDetails(String comapnyDetails) {
        this.comapnyDetails = comapnyDetails;
    }

    @XmlTransient
    public Collection<SurveyUser> getSurveyUserCollection() {
        return surveyUserCollection;
    }

    public void setSurveyUserCollection(Collection<SurveyUser> surveyUserCollection) {
        this.surveyUserCollection = surveyUserCollection;
    }

    @XmlTransient
    public Collection<SurveyQuestionsReport> getSurveyQuestionsReportCollection() {
        return surveyQuestionsReportCollection;
    }

    public void setSurveyQuestionsReportCollection(Collection<SurveyQuestionsReport> surveyQuestionsReportCollection) {
        this.surveyQuestionsReportCollection = surveyQuestionsReportCollection;
    }

    @XmlTransient
    public Collection<SurveyQuestionsGridReport> getSurveyQuestionsGridReportCollection() {
        return surveyQuestionsGridReportCollection;
    }

    public void setSurveyQuestionsGridReportCollection(Collection<SurveyQuestionsGridReport> surveyQuestionsGridReportCollection) {
        this.surveyQuestionsGridReportCollection = surveyQuestionsGridReportCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyCompanies)) {
            return false;
        }
        SurveyCompanies other = (SurveyCompanies) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyCompanies[ companyId=" + companyId + " ]";
    }
    
}
