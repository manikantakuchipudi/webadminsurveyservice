/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovista.core.survey.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
@Table(name = "survey_question_option_questions",  schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestionOptionQuestions.findAll", query = "SELECT s FROM SurveyQuestionOptionQuestions s"),
    @NamedQuery(name = "SurveyQuestionOptionQuestions.findByQuOptQuId", query = "SELECT s FROM SurveyQuestionOptionQuestions s WHERE s.quOptQuId = :quOptQuId")})
public class SurveyQuestionOptionQuestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qu_opt_qu_id", nullable = false)
    private Integer quOptQuId;
    @JoinColumn(name = "qid", referencedColumnName = "qid", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyQuestions qid;
    @JoinColumn(name = "qu_opt_id", referencedColumnName = "qu_opt_id", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyQuestionOptions quOptId;
    @JoinColumn(name = "sub_qid", referencedColumnName = "qid", nullable = false)
    @ManyToOne(optional = false)
    @JsonManagedReference
    private SurveyQuestions subQid;

    public SurveyQuestionOptionQuestions() {
    }

    public SurveyQuestionOptionQuestions(Integer quOptQuId) {
        this.quOptQuId = quOptQuId;
    }

    public Integer getQuOptQuId() {
        return quOptQuId;
    }

    public void setQuOptQuId(Integer quOptQuId) {
        this.quOptQuId = quOptQuId;
    }

    public SurveyQuestions getQid() {
        return qid;
    }

    public void setQid(SurveyQuestions qid) {
        this.qid = qid;
    }

    public SurveyQuestionOptions getQuOptId() {
        return quOptId;
    }

    public void setQuOptId(SurveyQuestionOptions quOptId) {
        this.quOptId = quOptId;
    }

    public SurveyQuestions getSubQid() {
        return subQid;
    }

    public void setSubQid(SurveyQuestions subQid) {
        this.subQid = subQid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quOptQuId != null ? quOptQuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionOptionQuestions)) {
            return false;
        }
        SurveyQuestionOptionQuestions other = (SurveyQuestionOptionQuestions) object;
        if ((this.quOptQuId == null && other.quOptQuId != null) || (this.quOptQuId != null && !this.quOptQuId.equals(other.quOptQuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyQuestionOptionQuestions[ quOptQuId=" + quOptQuId + " ]";
    }
    
}
