/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyecto.web;

import java.util.List;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.miguel.proyecto.db.Lugar;
import com.miguel.proyecto.db.controller.LugarJpaController;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class Mapas {

    private MapModel advancedModel;

    private Marker marker;

    private LugarJpaController lugarCtrl;

    private String nombre;

    private double lat;

    private double lng;

    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        advancedModel = new DefaultMapModel();
        lugarCtrl = new LugarJpaController(emf);
//        Lat:19.324499386445776, Lng:-99.17937085032463
//        Lat:19.324328359583355, Lng:-99.17934268712997

        //Shared coordinates
        List<Lugar> lugares = lugarCtrl.findLugarEntities();
        for (Lugar lugar : lugares) {
            Double latitud = Double.parseDouble(lugar.getLatitud());
            Double longitud = Double.parseDouble(lugar.getLongitud());
            String nombre = lugar.getNombre();
            System.out.println(latitud + ", " + longitud + ", " + nombre);
            advancedModel.addOverlay(new Marker(new LatLng(latitud, longitud), nombre));
        }
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        System.out.println(marker.getTitle());
    }

    public Marker getMarker() {
        return marker;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), nombre);
        Lugar l = new Lugar();
        l.setNombre(nombre);
        l.setLatitud(Double.toString(lat));
        l.setLongitud(Double.toString(lng));
        lugarCtrl.create(l);
        advancedModel.addOverlay(marker);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }

}
