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
public class SurveyQuestionsGridReportPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "grid_id")
    private int gridId;
    @Basic(optional = false)
    @Column(name = "grid_row_id")
    private int gridRowId;
    @Basic(optional = false)
    private int qid;
    @Basic(optional = false)
    @Column(name = "company_id")
    private int companyId;
    @Basic(optional = false)
    @Column(name = "grid_column_id")
    private int gridColumnId;
    @Basic(optional = false)
    @Column(name = "survey_id")
    private int surveyId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private String customerId;

    public SurveyQuestionsGridReportPK() {
    }

    public SurveyQuestionsGridReportPK(int gridId, int gridRowId, int qid, int companyId, int gridColumnId, int surveyId, int userId, String customerId) {
        this.gridId = gridId;
        this.gridRowId = gridRowId;
        this.qid = qid;
        this.companyId = companyId;
        this.gridColumnId = gridColumnId;
        this.surveyId = surveyId;
        this.userId = userId;
        this.customerId = customerId;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public int getGridRowId() {
        return gridRowId;
    }

    public void setGridRowId(int gridRowId) {
        this.gridRowId = gridRowId;
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

    public int getGridColumnId() {
        return gridColumnId;
    }

    public void setGridColumnId(int gridColumnId) {
        this.gridColumnId = gridColumnId;
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
        hash += (int) gridId;
        hash += (int) gridRowId;
        hash += (int) qid;
        hash += (int) companyId;
        hash += (int) gridColumnId;
        hash += (int) surveyId;
        hash += (int) userId;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionsGridReportPK)) {
            return false;
        }
        SurveyQuestionsGridReportPK other = (SurveyQuestionsGridReportPK) object;
        if (this.gridId != other.gridId) {
            return false;
        }
        if (this.gridRowId != other.gridRowId) {
            return false;
        }
        if (this.qid != other.qid) {
            return false;
        }
        if (this.companyId != other.companyId) {
            return false;
        }
        if (this.gridColumnId != other.gridColumnId) {
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
        return "com.innovista.survey.model.SurveyQuestionsGridReportPK[ gridId=" + gridId + ", gridRowId=" + gridRowId + ", qid=" + qid + ", companyId=" + companyId + ", gridColumnId=" + gridColumnId + ", surveyId=" + surveyId + ", userId=" + userId + ", customerId=" + customerId + " ]";
    }
    
}
