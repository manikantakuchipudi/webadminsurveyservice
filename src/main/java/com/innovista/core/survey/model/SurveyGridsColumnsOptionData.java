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

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_grids_columns_option_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyGridsColumnsOptionData.findAll", query = "SELECT s FROM SurveyGridsColumnsOptionData s"),
    @NamedQuery(name = "SurveyGridsColumnsOptionData.findByGridColumnOptDataId", query = "SELECT s FROM SurveyGridsColumnsOptionData s WHERE s.gridColumnOptDataId = :gridColumnOptDataId"),
    @NamedQuery(name = "SurveyGridsColumnsOptionData.findByData", query = "SELECT s FROM SurveyGridsColumnsOptionData s WHERE s.data = :data")})
public class SurveyGridsColumnsOptionData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grid_column_opt_data_id")
    private Integer gridColumnOptDataId;
    @Basic(optional = false)
    @Column(name = "data")
    private String data;
    @JoinColumn(name = "grid_id", referencedColumnName = "grid_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyGrid gridId;
    @JoinColumn(name = "grid_column_opt_id", referencedColumnName = "grid_column_opt_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyGridsColumnsOptions gridColumnOptId;
    @JoinColumn(name = "gridcol_type_id", referencedColumnName = "gridcol_type_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyGridColumnTypes gridcolTypeId;

    public SurveyGridsColumnsOptionData() {
    }

    public SurveyGridsColumnsOptionData(Integer gridColumnOptDataId) {
        this.gridColumnOptDataId = gridColumnOptDataId;
    }

    public SurveyGridsColumnsOptionData(Integer gridColumnOptDataId, String data) {
        this.gridColumnOptDataId = gridColumnOptDataId;
        this.data = data;
    }

    public Integer getGridColumnOptDataId() {
        return gridColumnOptDataId;
    }

    public void setGridColumnOptDataId(Integer gridColumnOptDataId) {
        this.gridColumnOptDataId = gridColumnOptDataId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public SurveyGrid getGridId() {
        return gridId;
    }

    public void setGridId(SurveyGrid gridId) {
        this.gridId = gridId;
    }

    public SurveyGridsColumnsOptions getGridColumnOptId() {
        return gridColumnOptId;
    }

    public void setGridColumnOptId(SurveyGridsColumnsOptions gridColumnOptId) {
        this.gridColumnOptId = gridColumnOptId;
    }

    public SurveyGridColumnTypes getGridcolTypeId() {
        return gridcolTypeId;
    }

    public void setGridcolTypeId(SurveyGridColumnTypes gridcolTypeId) {
        this.gridcolTypeId = gridcolTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gridColumnOptDataId != null ? gridColumnOptDataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyGridsColumnsOptionData)) {
            return false;
        }
        SurveyGridsColumnsOptionData other = (SurveyGridsColumnsOptionData) object;
        if ((this.gridColumnOptDataId == null && other.gridColumnOptDataId != null) || (this.gridColumnOptDataId != null && !this.gridColumnOptDataId.equals(other.gridColumnOptDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyGridsColumnsOptionData[ gridColumnOptDataId=" + gridColumnOptDataId + " ]";
    }
    
}
