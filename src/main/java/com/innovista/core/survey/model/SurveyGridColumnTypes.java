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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_grid_column_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyGridColumnTypes.findAll", query = "SELECT s FROM SurveyGridColumnTypes s"),
    @NamedQuery(name = "SurveyGridColumnTypes.findByGridcolTypeId", query = "SELECT s FROM SurveyGridColumnTypes s WHERE s.gridcolTypeId = :gridcolTypeId"),
    @NamedQuery(name = "SurveyGridColumnTypes.findByColumnType", query = "SELECT s FROM SurveyGridColumnTypes s WHERE s.columnType = :columnType")})
public class SurveyGridColumnTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gridcol_type_id")
    private Integer gridcolTypeId;
    @Basic(optional = false)
    @Column(name = "column_type")
    private String columnType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gridcolTypeId")
    @JsonIgnore
    private Collection<SurveyGridsColumns> surveyGridsColumnsCollection;

    public SurveyGridColumnTypes() {
    }

    public SurveyGridColumnTypes(Integer gridcolTypeId) {
        this.gridcolTypeId = gridcolTypeId;
    }

    public SurveyGridColumnTypes(Integer gridcolTypeId, String columnType) {
        this.gridcolTypeId = gridcolTypeId;
        this.columnType = columnType;
    }

    public Integer getGridcolTypeId() {
        return gridcolTypeId;
    }

    public void setGridcolTypeId(Integer gridcolTypeId) {
        this.gridcolTypeId = gridcolTypeId;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @XmlTransient
    public Collection<SurveyGridsColumns> getSurveyGridsColumnsCollection() {
        return surveyGridsColumnsCollection;
    }

    public void setSurveyGridsColumnsCollection(Collection<SurveyGridsColumns> surveyGridsColumnsCollection) {
        this.surveyGridsColumnsCollection = surveyGridsColumnsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gridcolTypeId != null ? gridcolTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyGridColumnTypes)) {
            return false;
        }
        SurveyGridColumnTypes other = (SurveyGridColumnTypes) object;
        if ((this.gridcolTypeId == null && other.gridcolTypeId != null) || (this.gridcolTypeId != null && !this.gridcolTypeId.equals(other.gridcolTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyGridColumnTypes[ gridcolTypeId=" + gridcolTypeId + " ]";
    }
    
}
