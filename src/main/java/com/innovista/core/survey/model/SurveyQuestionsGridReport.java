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
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_questions_grid_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestionsGridReport.findAll", query = "SELECT s FROM SurveyQuestionsGridReport s"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByGridId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.gridId = :gridId"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByGridRowId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.gridRowId = :gridRowId"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByQid", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.qid = :qid"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByCompanyId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.companyId = :companyId"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByGridColumnId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.gridColumnId = :gridColumnId"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByAnswer", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.answer = :answer"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findBySurveyId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.surveyId = :surveyId"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByUserId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.userId = :userId"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByCustomerName", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.customerName = :customerName"),
    @NamedQuery(name = "SurveyQuestionsGridReport.findByCustomerId", query = "SELECT s FROM SurveyQuestionsGridReport s WHERE s.surveyQuestionsGridReportPK.customerId = :customerId")})
public class SurveyQuestionsGridReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SurveyQuestionsGridReportPK surveyQuestionsGridReportPK;
    @Basic(optional = false)
    private String answer;
    @Basic(optional = false)
    @Column(name = "customer_name")
    private String customerName;
    @JoinColumn(name = "grid_id", referencedColumnName = "grid_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SurveyGrid surveyGrid;
    @JoinColumn(name = "grid_column_id", referencedColumnName = "grid_column_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SurveyGridsColumns surveyGridsColumns;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private SurveyUser surveyUser;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private SurveyCompanies surveyCompanies;
    @JoinColumn(name = "qid", referencedColumnName = "qid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private SurveyQuestions surveyQuestions;
    @JoinColumn(name = "survey_id", referencedColumnName = "survey_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private Surveys surveys;

    public SurveyQuestionsGridReport() {
    }

    public SurveyQuestionsGridReport(SurveyQuestionsGridReportPK surveyQuestionsGridReportPK) {
        this.surveyQuestionsGridReportPK = surveyQuestionsGridReportPK;
    }

    public SurveyQuestionsGridReport(SurveyQuestionsGridReportPK surveyQuestionsGridReportPK, String answer, String customerName) {
        this.surveyQuestionsGridReportPK = surveyQuestionsGridReportPK;
        this.answer = answer;
        this.customerName = customerName;
    }

    public SurveyQuestionsGridReport(int gridId, int gridRowId, int qid, int companyId, int gridColumnId, int surveyId, int userId, String customerId) {
        this.surveyQuestionsGridReportPK = new SurveyQuestionsGridReportPK(gridId, gridRowId, qid, companyId, gridColumnId, surveyId, userId, customerId);
    }

    public SurveyQuestionsGridReportPK getSurveyQuestionsGridReportPK() {
        return surveyQuestionsGridReportPK;
    }

    public void setSurveyQuestionsGridReportPK(SurveyQuestionsGridReportPK surveyQuestionsGridReportPK) {
        this.surveyQuestionsGridReportPK = surveyQuestionsGridReportPK;
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

    public SurveyGrid getSurveyGrid() {
        return surveyGrid;
    }

    public void setSurveyGrid(SurveyGrid surveyGrid) {
        this.surveyGrid = surveyGrid;
    }

    public SurveyGridsColumns getSurveyGridsColumns() {
        return surveyGridsColumns;
    }

    public void setSurveyGridsColumns(SurveyGridsColumns surveyGridsColumns) {
        this.surveyGridsColumns = surveyGridsColumns;
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
        hash += (surveyQuestionsGridReportPK != null ? surveyQuestionsGridReportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionsGridReport)) {
            return false;
        }
        SurveyQuestionsGridReport other = (SurveyQuestionsGridReport) object;
        if ((this.surveyQuestionsGridReportPK == null && other.surveyQuestionsGridReportPK != null) || (this.surveyQuestionsGridReportPK != null && !this.surveyQuestionsGridReportPK.equals(other.surveyQuestionsGridReportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyQuestionsGridReport[ surveyQuestionsGridReportPK=" + surveyQuestionsGridReportPK + " ]";
    }
    
}
