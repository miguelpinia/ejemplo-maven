/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyecto.db;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel
 */
@Entity
@Table(catalog = "miPrimerBase", schema = "", name = "lugar", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l"), @NamedQuery(name = "Lugar.findById", query = "SELECT l FROM Lugar l WHERE l.id = :id"), @NamedQuery(name = "Lugar.findByLatitud", query = "SELECT l FROM Lugar l WHERE l.latitud = :latitud"), @NamedQuery(name = "Lugar.findByLongitud", query = "SELECT l FROM Lugar l WHERE l.longitud = :longitud"), @NamedQuery(name = "Lugar.findByNombre", query = "SELECT l FROM Lugar l WHERE l.nombre = :nombre")})
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(nullable = false)
    private Long id;
    @Basic(optional = false) @Column(nullable = false, length = 20)
    private String latitud;
    @Basic(optional = false) @Column(nullable = false, length = 20)
    private String longitud;
    @Basic(optional = false) @Column(nullable = false, length = 255)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarId")
    private List<Comentario> comentarioList;

    public Lugar() {
    }

    public Lugar(Long id) {
        this.id = id;
    }

    public Lugar(Long id, String latitud, String longitud, String nombre) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
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
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miguel.proyecto.db.Lugar[ id=" + id + " ]";
    }

}
