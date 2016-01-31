/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovista.core.survey.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_questions_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestionsReport.findAll", query = "SELECT s FROM SurveyQuestionsReport s"),
    @NamedQuery(name = "SurveyQuestionsReport.findByQid", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.surveyQuestionsReportPK.qid = :qid"),
    @NamedQuery(name = "SurveyQuestionsReport.findByCompanyId", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.surveyQuestionsReportPK.companyId = :companyId"),
    @NamedQuery(name = "SurveyQuestionsReport.findByAnswer", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.answer = :answer"),
    @NamedQuery(name = "SurveyQuestionsReport.findBySurveyId", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.surveyQuestionsReportPK.surveyId = :surveyId"),
    @NamedQuery(name = "SurveyQuestionsReport.findByUserId", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.surveyQuestionsReportPK.userId = :userId"),
    @NamedQuery(name = "SurveyQuestionsReport.findByCustomerName", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.customerName = :customerName"),
    @NamedQuery(name = "SurveyQuestionsReport.findByCustomerId", query = "SELECT s FROM SurveyQuestionsReport s WHERE s.surveyQuestionsReportPK.customerId = :customerId")})
public class SurveyQuestionsReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SurveyQuestionsReportPK surveyQuestionsReportPK;
    @Basic(optional = false)
    private String answer;
    @Basic(optional = false)
    @Column(name = "customer_name")
    private String customerName;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SurveyUser surveyUser;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyCompanies surveyCompanies;
    @JoinColumn(name = "qid", referencedColumnName = "qid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SurveyQuestions surveyQuestions;
    @JoinColumn(name = "survey_id", referencedColumnName = "survey_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Surveys surveys;

    public SurveyQuestionsReport() {
    }

    public SurveyQuestionsReport(SurveyQuestionsReportPK surveyQuestionsReportPK) {
        this.surveyQuestionsReportPK = surveyQuestionsReportPK;
    }

    public SurveyQuestionsReport(SurveyQuestionsReportPK surveyQuestionsReportPK, String answer, String customerName) {
        this.surveyQuestionsReportPK = surveyQuestionsReportPK;
        this.answer = answer;
        this.customerName = customerName;
    }

    public SurveyQuestionsReport(int qid, int companyId, int surveyId, int userId, String customerId) {
        this.surveyQuestionsReportPK = new SurveyQuestionsReportPK(qid, companyId, surveyId, userId, customerId);
    }

    public SurveyQuestionsReportPK getSurveyQuestionsReportPK() {
        return surveyQuestionsReportPK;
    }

    public void setSurveyQuestionsReportPK(SurveyQuestionsReportPK surveyQuestionsReportPK) {
        this.surveyQuestionsReportPK = surveyQuestionsReportPK;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public SurveyUser getSurveyUser() {
        return surveyUser;
    }

    public void setSurveyUser(SurveyUser surveyUser) {
        this.surveyUser = surveyUser;
    }

    public SurveyCompanies getSurveyCompanies() {
        return surveyCompanies;
    }

    public void setSurveyCompanies(SurveyCompanies surveyCompanies) {
        this.surveyCompanies = surveyCompanies;
    }

    public SurveyQuestions getSurveyQuestions() {
        return surveyQuestions;
    }

    public void setSurveyQuestions(SurveyQuestions surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    public Surveys getSurveys() {
        return surveys;
    }

    public void setSurveys(Surveys surveys) {
        this.surveys = surveys;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyQuestionsReportPK != null ? surveyQuestionsReportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionsReport)) {
            return false;
        }
        SurveyQuestionsReport other = (SurveyQuestionsReport) object;
        if ((this.surveyQuestionsReportPK == null && other.surveyQuestionsReportPK != null) || (this.surveyQuestionsReportPK != null && !this.surveyQuestionsReportPK.equals(other.surveyQuestionsReportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyQuestionsReport[ surveyQuestionsReportPK=" + surveyQuestionsReportPK + " ]";
    }
    
}
