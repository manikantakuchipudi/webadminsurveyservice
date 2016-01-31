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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.innovista.core.survey.model.SurveyQuestionOptionQuestions;
import com.innovista.core.survey.model.SurveyQuestionOptions;
import com.innovista.core.survey.model.SurveyQuestions;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_question_options", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestionOptions.findAll", query = "SELECT s FROM SurveyQuestionOptions s"),
    @NamedQuery(name = "SurveyQuestionOptions.findByQuOptId", query = "SELECT s FROM SurveyQuestionOptions s WHERE s.quOptId = :quOptId"),
    @NamedQuery(name = "SurveyQuestionOptions.findByAnswerKey", query = "SELECT s FROM SurveyQuestionOptions s WHERE s.answerKey = :answerKey"),
    @NamedQuery(name = "SurveyQuestionOptions.findByAnswerValue", query = "SELECT s FROM SurveyQuestionOptions s WHERE s.answerValue = :answerValue")})
public class SurveyQuestionOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qu_opt_id", nullable = false)
    private Integer quOptId;
    @Basic(optional = false)
    @Column(name = "answer_key", nullable = false, length = 20)
    private String answerKey;
    @Basic(optional = false)
    @Column(name = "answer_value", nullable = false, length = 4000)
    private String answerValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quOptId")
    private Collection<SurveyQuestionOptionQuestions> surveyQuestionOptionQuestionsCollection;
    @JoinColumn(name = "qid", referencedColumnName = "qid", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyQuestions qid;

    public SurveyQuestionOptions() {
    }

    public SurveyQuestionOptions(Integer quOptId) {
        this.quOptId = quOptId;
    }

    public SurveyQuestionOptions(Integer quOptId, String answerKey, String answerValue) {
        this.quOptId = quOptId;
        this.answerKey = answerKey;
        this.answerValue = answerValue;
    }

    public Integer getQuOptId() {
        return quOptId;
    }

    public void setQuOptId(Integer quOptId) {
        this.quOptId = quOptId;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    @XmlTransient
    public Collection<SurveyQuestionOptionQuestions> getSurveyQuestionOptionQuestionsCollection() {
        return surveyQuestionOptionQuestionsCollection;
    }

    public void setSurveyQuestionOptionQuestionsCollection(Collection<SurveyQuestionOptionQuestions> surveyQuestionOptionQuestionsCollection) {
        this.surveyQuestionOptionQuestionsCollection = surveyQuestionOptionQuestionsCollection;
    }

    public SurveyQuestions getQid() {
        return qid;
    }

    public void setQid(SurveyQuestions qid) {
        this.qid = qid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quOptId != null ? quOptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionOptions)) {
            return false;
        }
        SurveyQuestionOptions other = (SurveyQuestionOptions) object;
        if ((this.quOptId == null && other.quOptId != null) || (this.quOptId != null && !this.quOptId.equals(other.quOptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyQuestionOptions[ quOptId=" + quOptId + " ]";
    }
    
}
