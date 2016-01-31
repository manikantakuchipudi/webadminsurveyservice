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

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_user_areas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyUserAreas.findAll", query = "SELECT s FROM SurveyUserAreas s"),
    @NamedQuery(name = "SurveyUserAreas.findBySuUserId", query = "SELECT s FROM SurveyUserAreas s WHERE s.suUserId = :suUserId")})
public class SurveyUserAreas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "su_user_id", nullable = false)
    private Integer suUserId;
    @JoinColumn(name = "area_id", referencedColumnName = "area_id", nullable = false)
    @ManyToOne(optional = false)
    private SurveyAreas areaId;
    @JoinColumn(name = "user_id", referencedColumnName = "role_id", nullable = false)
    @ManyToOne(optional = false)
    private SurveyUser userId;

    public SurveyUserAreas() {
    }

    public SurveyUserAreas(Integer suUserId) {
        this.suUserId = suUserId;
    }

    public Integer getSuUserId() {
        return suUserId;
    }

    public void setSuUserId(Integer suUserId) {
        this.suUserId = suUserId;
    }

    public SurveyAreas getAreaId() {
        return areaId;
    }

    public void setAreaId(SurveyAreas areaId) {
        this.areaId = areaId;
    }

    public SurveyUser getUserId() {
        return userId;
    }

    public void setUserId(SurveyUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suUserId != null ? suUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyUserAreas)) {
            return false;
        }
        SurveyUserAreas other = (SurveyUserAreas) object;
        if ((this.suUserId == null && other.suUserId != null) || (this.suUserId != null && !this.suUserId.equals(other.suUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyUserAreas[ suUserId=" + suUserId + " ]";
    }
    
}
