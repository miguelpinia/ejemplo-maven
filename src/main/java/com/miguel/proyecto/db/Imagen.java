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
import javax.persistence.Lob;
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
@Table(catalog = "miPrimerBase", schema = "", name="imagen", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}), @UniqueConstraint(columnNames = {"nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i"),
    @NamedQuery(name = "Imagen.findById", query = "SELECT i FROM Imagen i WHERE i.id = :id"),
    @NamedQuery(name = "Imagen.findByNombre", query = "SELECT i FROM Imagen i WHERE i.nombre = :nombre")})
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(nullable = false)
    private Long id;
    @Basic(optional = false) @Lob @Column(nullable = false)
    private byte[] datos;
    @Basic(optional = false) @Column(nullable = false, length = 255)
    private String nombre;

    public Imagen() {
    }

    public Imagen(Long id) {
        this.id = id;
    }

    public Imagen(Long id, byte[] datos, String nombre) {
        this.id = id;
        this.datos = datos;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miguel.proyecto.db.Imagen[ id=" + id + " ]";
    }

}
