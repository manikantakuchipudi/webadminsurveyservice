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

/**
 *
 * @author mkuchipudi
 */
@Entity
@Table(name = "survey_user_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyUserRole.findAll", query = "SELECT s FROM SurveyUserRole s"),
    @NamedQuery(name = "SurveyUserRole.findByRoleId", query = "SELECT s FROM SurveyUserRole s WHERE s.roleId = :roleId"),
    @NamedQuery(name = "SurveyUserRole.findByRoleName", query = "SELECT s FROM SurveyUserRole s WHERE s.roleName = :roleName")})
public class SurveyUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    
    @Basic(optional = false)
    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;
    
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
   // private Collection<SurveyUser> surveyUserCollection;

    public SurveyUserRole() {
    }

    public SurveyUserRole(Integer roleId) {
        this.roleId = roleId;
    }

    public SurveyUserRole(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

  /*  @XmlTransient
    public Collection<SurveyUser> getSurveyUserCollection() {
        return surveyUserCollection;
    }

    public void setSurveyUserCollection(Collection<SurveyUser> surveyUserCollection) {
        this.surveyUserCollection = surveyUserCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyUserRole)) {
            return false;
        }
        SurveyUserRole other = (SurveyUserRole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovista.survey.model.SurveyUserRole[ roleId=" + roleId + " ]";
    }
    
}
