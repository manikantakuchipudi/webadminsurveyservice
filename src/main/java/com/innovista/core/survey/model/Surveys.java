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

import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveySurveyAreas;
import com.innovista.core.survey.model.Surveys;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "surveys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveys.findAll", query = "SELECT s FROM Surveys s"),
    @NamedQuery(name = "Surveys.findBySurveyId", query = "SELECT s FROM Surveys s WHERE s.surveyId = :surveyId"),
    @NamedQuery(name = "Surveys.findBySurveyName", query = "SELECT s FROM Surveys s WHERE s.surveyName = :surveyName")})
public class Surveys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "survey_id", nullable = false)
    private Integer surveyId;
    @Basic(optional = false)
    @Column(name = "survey_name", nullable = false, length = 100)
    private String surveyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyId")
    @JsonBackReference
    @JsonIgnore
    private Collection<SurveyQuestions> surveyQuestionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveys")
    @JsonIgnore
    private Collection<SurveyQuestionsReport> questionsReportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyId")
    @JsonBackReference
    @JsonIgnore
    private Collection<SurveySurveyAreas> surveySurveyAreasCollection;

    public Surveys() {
    }

    public Surveys(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Surveys(Integer surveyId, String surveyName) {
        this.surveyId = surveyId;
        this.surveyName = surveyName;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    @XmlTransient
    public Collection<SurveyQuestions> getSurveyQuestionsCollection() {
        return surveyQuestionsCollection;
    }

    public void setSurveyQuestionsCollection(Collection<SurveyQuestions> surveyQuestionsCollection) {
        this.surveyQuestionsCollection = surveyQuestionsCollection;
    }

    @XmlTransient
    public Collection<SurveyQuestionsReport> getQuestionsReportCollection() {
        return questionsReportCollection;
    }

    public void setQuestionsReportCollection(Collection<SurveyQuestionsReport> questionsReportCollection) {
        this.questionsReportCollection = questionsReportCollection;
    }

    @XmlTransient
    public Collection<SurveySurveyAreas> getSurveySurveyAreasCollection() {
        return surveySurveyAreasCollection;
    }

    public void setSurveySurveyAreasCollection(Collection<SurveySurveyAreas> surveySurveyAreasCollection) {
        this.surveySurveyAreasCollection = surveySurveyAreasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyId != null ? surveyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surveys)) {
            return false;
        }
        Surveys other = (Surveys) object;
        if ((this.surveyId == null && other.surveyId != null) || (this.surveyId != null && !this.surveyId.equals(other.surveyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.Surveys[ surveyId=" + surveyId + " ]";
    }
    
}
