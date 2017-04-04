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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(catalog = "miPrimerBase", schema = "", name = "comentario", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"), @NamedQuery(name = "Comentario.findById", query = "SELECT c FROM Comentario c WHERE c.id = :id")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(nullable = false)
    private Long id;
    @Basic(optional = false) @Lob @Column(nullable = false, length = 2147483647)
    private String comentario;
    @JoinColumn(name = "lugar_id", referencedColumnName = "id", nullable = false) @ManyToOne(optional = false)
    private Lugar lugarId;

    public Comentario() {
    }

    public Comentario(Long id) {
        this.id = id;
    }

    public Comentario(Long id, String comentario) {
        this.id = id;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Lugar getLugarId() {
        return lugarId;
    }

    public void setLugarId(Lugar lugarId) {
        this.lugarId = lugarId;
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
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miguel.proyecto.db.Comentario[ id=" + id + " ]";
    }

}
