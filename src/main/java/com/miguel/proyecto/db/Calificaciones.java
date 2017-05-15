/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyecto.db;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel
 */
@Entity
@Table(catalog = "miPrimerBase", schema = "", name = "calificaciones", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Calificaciones.findAll", query = "SELECT c FROM Calificaciones c"), @NamedQuery(name = "Calificaciones.findById", query = "SELECT c FROM Calificaciones c WHERE c.id = :id"), @NamedQuery(name = "Calificaciones.findByCalificacion", query = "SELECT c FROM Calificaciones c WHERE c.calificacion = :calificacion")})
public class Calificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(nullable = false)
    private Long id;
    @Basic(optional = false) @Column(nullable = false)
    private int calificacion;

    public Calificaciones() {
    }

    public Calificaciones(Long id) {
        this.id = id;
    }

    public Calificaciones(Long id, int calificacion) {
        this.id = id;
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificaciones)) {
            return false;
        }
        Calificaciones other = (Calificaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miguel.proyecto.db.Calificaciones[ id=" + id + " ]";
    }

}
