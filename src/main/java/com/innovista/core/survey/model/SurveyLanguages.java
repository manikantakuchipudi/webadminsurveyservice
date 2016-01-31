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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestions;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_languages",  schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyLanguages.findAll", query = "SELECT s FROM SurveyLanguages s"),
    @NamedQuery(name = "SurveyLanguages.findByLangId", query = "SELECT s FROM SurveyLanguages s WHERE s.langId = :langId"),
    @NamedQuery(name = "SurveyLanguages.findByLangCode", query = "SELECT s FROM SurveyLanguages s WHERE s.langCode = :langCode"),
    @NamedQuery(name = "SurveyLanguages.findByLangName", query = "SELECT s FROM SurveyLanguages s WHERE s.langName = :langName")})
public class SurveyLanguages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lang_id", nullable = false)
    private Integer langId;
    @Basic(optional = false)
    @Column(name = "lang_code", nullable = false, length = 10)
    private String langCode;
    @Basic(optional = false)
    @Column(name = "lang_name", nullable = false, length = 100)
    private String langName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "langId")
    @JsonManagedReference
    @JsonIgnore
    private Collection<SurveyQuestions> surveyQuestionsCollection;

    public SurveyLanguages() {
    }

    public SurveyLanguages(Integer langId) {
        this.langId = langId;
    }

    public SurveyLanguages(Integer langId, String langCode, String langName) {
        this.langId = langId;
        this.langCode = langCode;
        this.langName = langName;
    }

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    @XmlTransient
    public Collection<SurveyQuestions> getSurveyQuestionsCollection() {
        return surveyQuestionsCollection;
    }

    public void setSurveyQuestionsCollection(Collection<SurveyQuestions> surveyQuestionsCollection) {
        this.surveyQuestionsCollection = surveyQuestionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langId != null ? langId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyLanguages)) {
            return false;
        }
        SurveyLanguages other = (SurveyLanguages) object;
        if ((this.langId == null && other.langId != null) || (this.langId != null && !this.langId.equals(other.langId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyLanguages[ langId=" + langId + " ]";
    }
    
}
