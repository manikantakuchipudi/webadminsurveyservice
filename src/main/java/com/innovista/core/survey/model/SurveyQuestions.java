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


/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_questions",  schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestions.findAll", query = "SELECT s FROM SurveyQuestions s WHERE s.defaultQuestion ='Yes'  order by s.questionPostion"),
    @NamedQuery(name = "SurveyQuestions.findByQid", query = "SELECT s FROM SurveyQuestions s WHERE s.qid = :qid"),
    @NamedQuery(name = "SurveyQuestions.findByLangid", query = "SELECT s FROM SurveyQuestions s WHERE s.langId = :langid and s.defaultQuestion ='Yes'  order by s.questionPostion"),
    @NamedQuery(name = "SurveyQuestions.findByNoOfOptions", query = "SELECT s FROM SurveyQuestions s WHERE s.noOfOptions = :noOfOptions"),
    @NamedQuery(name = "SurveyQuestions.findByQuestion", query = "SELECT s FROM SurveyQuestions s WHERE s.question = :question"),
    @NamedQuery(name = "SurveyQuestions.findByColumn1", query = "SELECT s FROM SurveyQuestions s WHERE s.questionPostion = :questionPostion"),
    @NamedQuery(name = "SurveyQuestions.findByColumn2", query = "SELECT s FROM SurveyQuestions s WHERE s.defaultQuestion = :defaultQuestion"),
    @NamedQuery(name = "SurveyQuestions.findByColumn3", query = "SELECT s FROM SurveyQuestions s WHERE s.column3 = :column3"),
    @NamedQuery(name = "SurveyQuestions.findByColumn4", query = "SELECT s FROM SurveyQuestions s WHERE s.column4 = :column4")})
public class SurveyQuestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qid", nullable = false)
    private Integer qid;
    @Basic(optional = false)
    @Column(name = "no_of_options", nullable = false)
    private int noOfOptions;
    @Basic(optional = false)
    @Column(name = "question", nullable = false, length = 4000)
    private String question;
   // @JsonIgnore
    @Column(name = "column1", length = 255)
    private Double questionPostion;
    
    @Column(name = "column2", length = 255)
    //@JsonIgnore
    private String defaultQuestion;
    public String getDefaultQuestion() {
		return defaultQuestion;
	}

	public void setDefaultQuestion(String defaultQuestion) {
		this.defaultQuestion = defaultQuestion;
	}

	@JsonIgnore
    @Column(name = "column3", length = 255)
    private String column3;
    @Column(name = "column4")
    @JsonIgnore
    private Integer column4;
    
   // @JsonIgnore
   // @JsonBackReference
    @JoinColumn(name = "survey_id", referencedColumnName = "survey_id", nullable = false)
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Surveys surveyId;
    
    
    @JoinColumn(name = "qtype_id", referencedColumnName = "qtype_id", nullable = false)
    @ManyToOne(optional = false)
   // @JsonIgnore
    @JsonManagedReference
    private SurveyQuestionTypes qtypeId;
    
    @JoinColumn(name = "lang_id", referencedColumnName = "lang_id", nullable = false)
    @ManyToOne(optional = false)
  //  @JsonIgnore
    @JsonBackReference
    private SurveyLanguages langId;
  
    @OneToMany(mappedBy = "qid")
   // @JsonIgnore
    //@JsonBackReference
   // @JsonBackReference
  //  @JsonManagedReference
    private Collection<SurveyGridQuestions> surveyGridQuestionsCollection;
    
    @OneToMany(mappedBy = "qid")
   // @JsonIgnore
   // @JsonBackReference
    private Collection<SurveySubQuestions> surveySubQuestionsCollection;

    @OneToMany(mappedBy = "subQid")
   // @JsonIgnore
   // @JsonBackReference
    private Collection<SurveySubQuestions> surveySubQuestionsCollection1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qid")
   // @JsonIgnore
    @JsonBackReference
    private Collection<SurveyQuestionOptionQuestions> surveyQuestionOptionQuestionsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subQid")
   // @JsonIgnore
    @JsonBackReference
    private Collection<SurveyQuestionOptionQuestions> surveyQuestionOptionQuestionsCollection1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyQuestions")
    @JsonIgnore
   // @JsonBackReference
    private Collection<SurveyQuestionsReport> questionsReportCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qid")
   // @JsonIgnore
  //  @JsonBackReference
    private Collection<SurveyQuestionOptions> surveyQuestionOptionsCollection;

    public SurveyQuestions() {
    }

    public SurveyQuestions(Integer qid) {
        this.qid = qid;
    }

    public SurveyQuestions(Integer qid, int noOfOptions, String question) {
        this.qid = qid;
        this.noOfOptions = noOfOptions;
        this.question = question;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public int getNoOfOptions() {
        return noOfOptions;
    }

    public void setNoOfOptions(int noOfOptions) {
        this.noOfOptions = noOfOptions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Double getQuestionPostion() {
		return questionPostion;
	}

	public void setQuestionPostion(Double questionPostion) {
		this.questionPostion = questionPostion;
	}

    public String getColumn2() {
        return defaultQuestion;
    }

    public void setColumn2(String defaultQuestion) {
        this.defaultQuestion = defaultQuestion;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public Integer getColumn4() {
        return column4;
    }

    public void setColumn4(Integer column4) {
        this.column4 = column4;
    }

    public Surveys getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Surveys surveyId) {
        this.surveyId = surveyId;
    }

    public SurveyQuestionTypes getQtypeId() {
        return qtypeId;
    }

    public void setQtypeId(SurveyQuestionTypes qtypeId) {
        this.qtypeId = qtypeId;
    }

    public SurveyLanguages getLangId() {
        return langId;
    }

    public void setLangId(SurveyLanguages langId) {
        this.langId = langId;
    }
    
    
    @XmlTransient
    public Collection<SurveyGridQuestions> getSurveyGridQuestionsCollection() {
        return surveyGridQuestionsCollection;
    }

    public void setSurveyGridQuestionsCollection(Collection<SurveyGridQuestions> surveyGridQuestionsCollection) {
        this.surveyGridQuestionsCollection = surveyGridQuestionsCollection;
    }
    

    @XmlTransient
    public Collection<SurveySubQuestions> getSurveySubQuestionsCollection() {
        return surveySubQuestionsCollection;
    }

    public void setSurveySubQuestionsCollection(Collection<SurveySubQuestions> surveySubQuestionsCollection) {
        this.surveySubQuestionsCollection = surveySubQuestionsCollection;
    }

    @XmlTransient
    public Collection<SurveySubQuestions> getSurveySubQuestionsCollection1() {
        return surveySubQuestionsCollection1;
    }

    public void setSurveySubQuestionsCollection1(Collection<SurveySubQuestions> surveySubQuestionsCollection1) {
        this.surveySubQuestionsCollection1 = surveySubQuestionsCollection1;
    }

    @XmlTransient
    public Collection<SurveyQuestionOptionQuestions> getSurveyQuestionOptionQuestionsCollection() {
        return surveyQuestionOptionQuestionsCollection;
    }

    public void setSurveyQuestionOptionQuestionsCollection(Collection<SurveyQuestionOptionQuestions> surveyQuestionOptionQuestionsCollection) {
        this.surveyQuestionOptionQuestionsCollection = surveyQuestionOptionQuestionsCollection;
    }

    @XmlTransient
    public Collection<SurveyQuestionOptionQuestions> getSurveyQuestionOptionQuestionsCollection1() {
        return surveyQuestionOptionQuestionsCollection1;
    }

    public void setSurveyQuestionOptionQuestionsCollection1(Collection<SurveyQuestionOptionQuestions> surveyQuestionOptionQuestionsCollection1) {
        this.surveyQuestionOptionQuestionsCollection1 = surveyQuestionOptionQuestionsCollection1;
    }

    @XmlTransient
    public Collection<SurveyQuestionsReport> getQuestionsReportCollection() {
        return questionsReportCollection;
    }

    public void setQuestionsReportCollection(Collection<SurveyQuestionsReport> questionsReportCollection) {
        this.questionsReportCollection = questionsReportCollection;
    }

    @XmlTransient
    public Collection<SurveyQuestionOptions> getSurveyQuestionOptionsCollection() {
        return surveyQuestionOptionsCollection;
    }

    public void setSurveyQuestionOptionsCollection(Collection<SurveyQuestionOptions> surveyQuestionOptionsCollection) {
        this.surveyQuestionOptionsCollection = surveyQuestionOptionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qid != null ? qid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestions)) {
            return false;
        }
        SurveyQuestions other = (SurveyQuestions) object;
        if ((this.qid == null && other.qid != null) || (this.qid != null && !this.qid.equals(other.qid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyQuestions[ qid=" + qid +question+ " ]";
    }
    
}
