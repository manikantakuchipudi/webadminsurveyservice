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
import com.innovista.core.survey.model.SurveyQuestionTypes;
import com.innovista.core.survey.model.SurveyQuestions;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_question_types",  schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestionTypes.findAll", query = "SELECT s FROM SurveyQuestionTypes s"),
    @NamedQuery(name = "SurveyQuestionTypes.findByQtypeId", query = "SELECT s FROM SurveyQuestionTypes s WHERE s.qtypeId = :qtypeId"),
    @NamedQuery(name = "SurveyQuestionTypes.findByQtypeValue", query = "SELECT s FROM SurveyQuestionTypes s WHERE s.qtypeValue = :qtypeValue")})
public class SurveyQuestionTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qtype_id", nullable = false)
    private Integer qtypeId;
    @Basic(optional = false)
    @Column(name = "qtype_value", nullable = false, length = 20)
    private String qtypeValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qtypeId")
    @JsonBackReference
    private Collection<SurveyQuestions> surveyQuestionsCollection;

    public SurveyQuestionTypes() {
    }

    public SurveyQuestionTypes(Integer qtypeId) {
        this.qtypeId = qtypeId;
    }

    public SurveyQuestionTypes(Integer qtypeId, String qtypeValue) {
        this.qtypeId = qtypeId;
        this.qtypeValue = qtypeValue;
    }

    public Integer getQtypeId() {
        return qtypeId;
    }

    public void setQtypeId(Integer qtypeId) {
        this.qtypeId = qtypeId;
    }

    public String getQtypeValue() {
        return qtypeValue;
    }

    public void setQtypeValue(String qtypeValue) {
        this.qtypeValue = qtypeValue;
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
        hash += (qtypeId != null ? qtypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionTypes)) {
            return false;
        }
        SurveyQuestionTypes other = (SurveyQuestionTypes) object;
        if ((this.qtypeId == null && other.qtypeId != null) || (this.qtypeId != null && !this.qtypeId.equals(other.qtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyQuestionTypes[ qtypeId=" + qtypeId + " ]";
    }
    
}
