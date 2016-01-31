/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovista.core.survey.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_grid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyGrid.findAll", query = "SELECT s FROM SurveyGrid s"),
    @NamedQuery(name = "SurveyGrid.findByGridId", query = "SELECT s FROM SurveyGrid s WHERE s.gridId = :gridId"),
    @NamedQuery(name = "SurveyGrid.findByGridName", query = "SELECT s FROM SurveyGrid s WHERE s.gridName = :gridName")})
public class SurveyGrid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grid_id")
    private Integer gridId;
    @Basic(optional = false)
    @Column(name = "grid_name")
    private String gridName;
    @OneToMany(mappedBy = "gridnameid")
    @JsonBackReference
    private Collection<SurveyGridQuestions> surveyGridQuestionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gridId")
    private Collection<SurveyGridsColumns> surveyGridsColumnsCollection;

    public SurveyGrid() {
    }

    public SurveyGrid(Integer gridId) {
        this.gridId = gridId;
    }

    public SurveyGrid(Integer gridId, String gridName) {
        this.gridId = gridId;
        this.gridName = gridName;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    @XmlTransient
    public Collection<SurveyGridQuestions> getSurveyGridQuestionsCollection() {
        return surveyGridQuestionsCollection;
    }

    public void setSurveyGridQuestionsCollection(Collection<SurveyGridQuestions> surveyGridQuestionsCollection) {
        this.surveyGridQuestionsCollection = surveyGridQuestionsCollection;
    }

    @XmlTransient
    public List<SurveyGridsColumns> getSurveyGridsColumnsCollection() {
    	
    	
    	//Collections.sort(surveyGridsColumnsCollection);
    	ColumnPositionCompartor cmp=new ColumnPositionCompartor();
    	List<SurveyGridsColumns> col =new ArrayList<SurveyGridsColumns>(surveyGridsColumnsCollection);
    	Collections.sort(col, cmp);
        return col;
    }

    public void setSurveyGridsColumnsCollection(Collection<SurveyGridsColumns> surveyGridsColumnsCollection) {
        this.surveyGridsColumnsCollection = surveyGridsColumnsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gridId != null ? gridId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyGrid)) {
            return false;
        }
        SurveyGrid other = (SurveyGrid) object;
        if ((this.gridId == null && other.gridId != null) || (this.gridId != null && !this.gridId.equals(other.gridId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyGrid[ gridId=" + gridId + " ]";
    }
    
}
