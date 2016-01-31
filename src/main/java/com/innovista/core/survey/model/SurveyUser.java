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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyUser;
import com.innovista.core.survey.model.SurveyUserAreas;
import com.innovista.core.survey.model.SurveyUserRole;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyUser.findAll", query = "SELECT s FROM SurveyUser s"),
    @NamedQuery(name = "SurveyUser.findByUserId", query = "SELECT s FROM SurveyUser s WHERE s.userId = :userId"),
    @NamedQuery(name = "SurveyUser.findByUsername", query = "SELECT s FROM SurveyUser s WHERE s.username = :username"),
    @NamedQuery(name = "SurveyUser.findByPassword", query = "SELECT s FROM SurveyUser s WHERE s.password = :password"),
    @NamedQuery(name = "SurveyUser.findByPhoneno", query = "SELECT s FROM SurveyUser s WHERE s.phoneno = :phoneno")})
public class SurveyUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 100)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "phoneno")
    private String phoneno;
   
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    @JsonIgnore
    private SurveyCompanies companyId;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    @JsonIgnore
    private SurveyUserRole roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyUser")
    @JsonBackReference
    @JsonIgnore
    private Collection<SurveyQuestionsReport> questionsReportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonBackReference
    @JsonIgnore
    private Collection<SurveyUserAreas> surveyUserAreasCollection;

    public SurveyUser() {
    }

    public SurveyUser(Integer userId) {
        this.userId = userId;
    }

    public SurveyUser(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public SurveyCompanies getCompanyId() {
        return companyId;
    }

    public void setCompanyId(SurveyCompanies companyId) {
        this.companyId = companyId;
    }

    public SurveyUserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(SurveyUserRole roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<SurveyQuestionsReport> getQuestionsReportCollection() {
        return questionsReportCollection;
    }

    public void setQuestionsReportCollection(Collection<SurveyQuestionsReport> questionsReportCollection) {
        this.questionsReportCollection = questionsReportCollection;
    }

    @XmlTransient
    public Collection<SurveyUserAreas> getSurveyUserAreasCollection() {
        return surveyUserAreasCollection;
    }

    public void setSurveyUserAreasCollection(Collection<SurveyUserAreas> surveyUserAreasCollection) {
        this.surveyUserAreasCollection = surveyUserAreasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyUser)) {
            return false;
        }
        SurveyUser other = (SurveyUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyUser[ userId=" + userId + " ]";
    }
    
}


