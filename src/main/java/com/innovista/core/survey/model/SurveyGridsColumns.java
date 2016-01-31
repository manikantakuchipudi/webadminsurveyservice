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
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_grids_columns")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyGridsColumns.findAll", query = "SELECT s FROM SurveyGridsColumns s order by s.gridColumnId"),
    @NamedQuery(name = "SurveyGridsColumns.findByGridColumnId", query = "SELECT s FROM SurveyGridsColumns s WHERE s.gridColumnId = :gridColumnId"),
    @NamedQuery(name = "SurveyGridsColumns.findByColumnName", query = "SELECT s FROM SurveyGridsColumns s WHERE s.columnName = :columnName")})
public class SurveyGridsColumns implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grid_column_id")
    private Integer gridColumnId;
    @Basic(optional = false)
    @Column(name = "column_name")
    private String columnName;
    @Column(name = "column_position")
    private Integer columnPosition;
    
    public Integer getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(Integer columnPosition) {
		this.columnPosition = columnPosition;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "gridColumnId")
    private Collection<SurveyGridsColumnsOptions> surveyGridsColumnsOptionsCollection;
    @JoinColumn(name = "grid_id", referencedColumnName = "grid_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyGrid gridId;
    @JoinColumn(name = "gridcol_type_id", referencedColumnName = "gridcol_type_id")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private SurveyGridColumnTypes gridcolTypeId;

    public SurveyGridsColumns() {
    }

    public SurveyGridsColumns(Integer gridColumnId) {
        this.gridColumnId = gridColumnId;
    }

    public SurveyGridsColumns(Integer gridColumnId, String columnName) {
        this.gridColumnId = gridColumnId;
        this.columnName = columnName;
    }

    public Integer getGridColumnId() {
        return gridColumnId;
    }

    public void setGridColumnId(Integer gridColumnId) {
        this.gridColumnId = gridColumnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @XmlTransient
    public Collection<SurveyGridsColumnsOptions> getSurveyGridsColumnsOptionsCollection() {
        return surveyGridsColumnsOptionsCollection;
    }

    public void setSurveyGridsColumnsOptionsCollection(Collection<SurveyGridsColumnsOptions> surveyGridsColumnsOptionsCollection) {
        this.surveyGridsColumnsOptionsCollection = surveyGridsColumnsOptionsCollection;
    }

    public SurveyGrid getGridId() {
        return gridId;
    }

    public void setGridId(SurveyGrid gridId) {
        this.gridId = gridId;
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
        hash += (gridColumnId != null ? gridColumnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyGridsColumns)) {
            return false;
        }
        SurveyGridsColumns other = (SurveyGridsColumns) object;
        if ((this.gridColumnId == null && other.gridColumnId != null) || (this.gridColumnId != null && !this.gridColumnId.equals(other.gridColumnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyGridsColumns[ gridColumnId=" + gridColumnId + " ]";
    }
    
}
