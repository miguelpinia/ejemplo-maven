package com.miguel.proyecto.web;

import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.miguel.proyecto.db.Imagen;
import com.miguel.proyecto.db.controller.ImagenJpaController;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class SubeImagen {

    private UploadedFile file;
    private String nombreImagen;
    private ImagenJpaController _imageJpa;
    private HttpSession _session;
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación

    public SubeImagen() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        _imageJpa = new ImagenJpaController(emf);
        _session = httpServletRequest.getSession();
    }

    public void guardaImagen() {
        Imagen imagen = new Imagen();
        imagen.setNombre(file.getFileName());
        imagen.setDatos(file.getContents());
        System.out.println("11111111111111");
//        System.out.println(file.getContents().length);
        _imageJpa.create(imagen);
        System.out.println("Creando imagen");
        _session.setAttribute("imagen", file.getFileName());
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getNombre() {
        if (_session.getAttribute("imagen") != null) {
            System.out.println("22222");
            nombreImagen = (String) _session.getAttribute("imagen");
            _session.removeAttribute("imagen");
            System.out.println(":D " + nombreImagen);
        }
        return nombreImagen;
    }

}
