/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyecto.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.miguel.proyecto.db.Calificaciones;
import com.miguel.proyecto.db.controller.CalificacionesJpaController;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class Agregados {

    private CalificacionesJpaController calificacionesJpa;
    private Double promedio;
    private Long suma;
    private Long calificacion;

    /**
     * Creates a new instance of Agregados
     */
    public Agregados() {
    }

    @PostConstruct
    private void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        calificacionesJpa = new CalificacionesJpaController(emf);
    }

    public void obtenPromedio() {
        promedio = calificacionesJpa.getPromedio();
        FacesContext.getCurrentInstance()
                    .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                 "Promedio",
                                                 "Calculando el promedio"));

    }

    public void obtenSuma() {
        suma = calificacionesJpa.getSumaPromedio();
        FacesContext.getCurrentInstance()
                    .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                 "Suma",
                                                 "Calculando la suma"));

    }

    public Double getPromedio() {
        return promedio;
    }

    public Long getSumaPromedio() {
        return suma;
    }

    public void setCalificacion(Long calificacion) {
        this.calificacion = calificacion;
    }

    public Long getCalificacion() {
        return calificacion;
    }

    public void agregaCalificacion() {
        if (calificacion == null) {
            return;
        }
        if (calificacion > 10) {
            FacesContext.getCurrentInstance()
                        .addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                     "Error",
                                                     "La calificacion no puede ser mayor a 10"));
            return;
        }
        Calificaciones c = new Calificaciones();
        c.setCalificacion(calificacion.intValue());
        calificacion = null;
        calificacionesJpa.create(c);
        FacesContext.getCurrentInstance()
                    .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                 "Mensaje",
                                                 "Se agregó correctamente la calificación"));
    }


}
