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

import com.innovista.core.survey.model.SurveyAreas;
import com.innovista.core.survey.model.SurveySurveyAreas;
import com.innovista.core.survey.model.SurveyUserAreas;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_areas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyAreas.findAll", query = "SELECT s FROM SurveyAreas s"),
    @NamedQuery(name = "SurveyAreas.findByAreaId", query = "SELECT s FROM SurveyAreas s WHERE s.areaId = :areaId"),
    @NamedQuery(name = "SurveyAreas.findByAreaName", query = "SELECT s FROM SurveyAreas s WHERE s.areaName = :areaName")})
public class SurveyAreas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "area_id", nullable = false)
    private Integer areaId;
    @Basic(optional = false)
    @Column(name = "area_name", nullable = false, length = 100)
    private String areaName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId")
    private Collection<SurveyUserAreas> surveyUserAreasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId")
    private Collection<SurveySurveyAreas> surveySurveyAreasCollection;

    public SurveyAreas() {
    }

    public SurveyAreas(Integer areaId) {
        this.areaId = areaId;
    }

    public SurveyAreas(Integer areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @XmlTransient
    public Collection<SurveyUserAreas> getSurveyUserAreasCollection() {
        return surveyUserAreasCollection;
    }

    public void setSurveyUserAreasCollection(Collection<SurveyUserAreas> surveyUserAreasCollection) {
        this.surveyUserAreasCollection = surveyUserAreasCollection;
    }

    @XmlTransient
    public Collection<SurveySurveyAreas> getSurveySurveyAreasCollection() {
        return surveySurveyAreasCollection;
    }

    public void setSurveySurveyAreasCollection(Collection<SurveySurveyAreas> surveySurveyAreasCollection) {
        this.surveySurveyAreasCollection = surveySurveyAreasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaId != null ? areaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyAreas)) {
            return false;
        }
        SurveyAreas other = (SurveyAreas) object;
        if ((this.areaId == null && other.areaId != null) || (this.areaId != null && !this.areaId.equals(other.areaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.core.survey.model.SurveyAreas[ areaId=" + areaId + " ]";
    }
    
}
