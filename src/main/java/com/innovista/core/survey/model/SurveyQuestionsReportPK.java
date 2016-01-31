/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovista.core.survey.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mkuchipudi
 */
@Embeddable
public class SurveyQuestionsReportPK implements Serializable {
    @Basic(optional = false)
    private int qid;
    @Basic(optional = false)
    @Column(name = "company_id")
    private int companyId;
    @Basic(optional = false)
    @Column(name = "survey_id")
    private int surveyId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private String customerId;

    public SurveyQuestionsReportPK() {
    }

    public SurveyQuestionsReportPK(int qid, int companyId, int surveyId, int userId, String customerId) {
        this.qid = qid;
        this.companyId = companyId;
        this.surveyId = surveyId;
        this.userId = userId;
        this.customerId = customerId;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) qid;
        hash += (int) companyId;
        hash += (int) surveyId;
        hash += (int) userId;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionsReportPK)) {
            return false;
        }
        SurveyQuestionsReportPK other = (SurveyQuestionsReportPK) object;
        if (this.qid != other.qid) {
            return false;
        }
        if (this.companyId != other.companyId) {
            return false;
        }
        if (this.surveyId != other.surveyId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyQuestionsReportPK[ qid=" + qid + ", companyId=" + companyId + ", surveyId=" + surveyId + ", userId=" + userId + ", customerId=" + customerId + " ]";
    }
    
}
