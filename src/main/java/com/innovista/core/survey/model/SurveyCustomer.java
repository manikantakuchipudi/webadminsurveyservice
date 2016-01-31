/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovista.core.survey.model;

/**
 *
 * @author mkuchipudi
 */
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyCustomer.findAll", query = "SELECT s FROM SurveyCustomer s"),
    @NamedQuery(name = "SurveyCustomer.findBySurveyCustomerId", query = "SELECT s FROM SurveyCustomer s WHERE s.surveyCustomerId = :surveyCustomerId"),
    @NamedQuery(name = "SurveyCustomer.findByCustomerId", query = "SELECT s FROM SurveyCustomer s WHERE s.customerId = :customerId"),
    @NamedQuery(name = "SurveyCustomer.findBySurveyDate", query = "SELECT s FROM SurveyCustomer s WHERE s.surveyDate = :surveyDate")})
public class SurveyCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "survey_customer_id")
    private Integer surveyCustomerId;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private String customerId;
    @Basic(optional = false)
    @Column(name = "survey_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date surveyDate;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private SurveyUser userId;
    @JoinColumn(name = "survey_id", referencedColumnName = "survey_id")
    @ManyToOne
    private Surveys surveyId;

    public SurveyCustomer() {
    }

    public SurveyCustomer(Integer surveyCustomerId) {
        this.surveyCustomerId = surveyCustomerId;
    }

    public SurveyCustomer(Integer surveyCustomerId, String customerId, Date surveyDate) {
        this.surveyCustomerId = surveyCustomerId;
        this.customerId = customerId;
        this.surveyDate = surveyDate;
    }

    public Integer getSurveyCustomerId() {
        return surveyCustomerId;
    }

    public void setSurveyCustomerId(Integer surveyCustomerId) {
        this.surveyCustomerId = surveyCustomerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public SurveyUser getUserId() {
        return userId;
    }

    public void setUserId(SurveyUser userId) {
        this.userId = userId;
    }

    public Surveys getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Surveys surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyCustomerId != null ? surveyCustomerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyCustomer)) {
            return false;
        }
        SurveyCustomer other = (SurveyCustomer) object;
        if ((this.surveyCustomerId == null && other.surveyCustomerId != null) || (this.surveyCustomerId != null && !this.surveyCustomerId.equals(other.surveyCustomerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyCustomer[ surveyCustomerId=" + surveyCustomerId + " ]";
    }
    
}
