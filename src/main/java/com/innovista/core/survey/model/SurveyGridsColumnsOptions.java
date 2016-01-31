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

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_grids_columns_options")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyGridsColumnsOptions.findAll", query = "SELECT s FROM SurveyGridsColumnsOptions s"),
    @NamedQuery(name = "SurveyGridsColumnsOptions.findByGridColumnOptId", query = "SELECT s FROM SurveyGridsColumnsOptions s WHERE s.gridColumnOptId = :gridColumnOptId"),
    @NamedQuery(name = "SurveyGridsColumnsOptions.findByOptionName", query = "SELECT s FROM SurveyGridsColumnsOptions s WHERE s.optionName = :optionName")})
public class SurveyGridsColumnsOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grid_column_opt_id")
    private Integer gridColumnOptId;
    @Basic(optional = false)
    @Column(name = "option_name")
    private String optionName;
    @JoinColumn(name = "grid_column_id", referencedColumnName = "grid_column_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private SurveyGridsColumns gridColumnId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gridColumnOptId")
    private Collection<SurveyGridsColumnsOptionData> surveyGridsColumnsOptionDataCollection;

    public SurveyGridsColumnsOptions() {
    }

    public SurveyGridsColumnsOptions(Integer gridColumnOptId) {
        this.gridColumnOptId = gridColumnOptId;
    }

    public SurveyGridsColumnsOptions(Integer gridColumnOptId, String optionName) {
        this.gridColumnOptId = gridColumnOptId;
        this.optionName = optionName;
    }

    public Integer getGridColumnOptId() {
        return gridColumnOptId;
    }

    public void setGridColumnOptId(Integer gridColumnOptId) {
        this.gridColumnOptId = gridColumnOptId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public SurveyGridsColumns getGridColumnId() {
        return gridColumnId;
    }

    public void setGridColumnId(SurveyGridsColumns gridColumnId) {
        this.gridColumnId = gridColumnId;
    }

    @XmlTransient
    public Collection<SurveyGridsColumnsOptionData> getSurveyGridsColumnsOptionDataCollection() {
        return surveyGridsColumnsOptionDataCollection;
    }

    public void setSurveyGridsColumnsOptionDataCollection(Collection<SurveyGridsColumnsOptionData> surveyGridsColumnsOptionDataCollection) {
        this.surveyGridsColumnsOptionDataCollection = surveyGridsColumnsOptionDataCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gridColumnOptId != null ? gridColumnOptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyGridsColumnsOptions)) {
            return false;
        }
        SurveyGridsColumnsOptions other = (SurveyGridsColumnsOptions) object;
        if ((this.gridColumnOptId == null && other.gridColumnOptId != null) || (this.gridColumnOptId != null && !this.gridColumnOptId.equals(other.gridColumnOptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyGridsColumnsOptions[ gridColumnOptId=" + gridColumnOptId + " ]";
    }
    
}
