package com.miguel.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miguel.proyecto.db.Persona;
import com.miguel.proyecto.db.controller.PersonaJpaController;

/**
 * Modifico mi comentario.
 */
@SuppressWarnings("serial")
public class MiServlet extends HttpServlet {

    private void foo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        PersonaJpaController pjc = new PersonaJpaController(emf);
        List<Persona> lp = pjc.findPersonaEntities();
//        Persona p = pjc.findPersona(1L);

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        pw.println("<HTML><HEAD><TITLE>Leyendo parámetros</TITLE></HEAD>");
        pw.println("<BODY BGCOLOR=\"#CCBBAA\">");
        pw.println("<H2>Leyendo parámetros desde un formulario html</H2><P>");
        pw.println("<UL>\n");

        for (Persona p : lp) {
            pw.println(String.format("<b>Nombre:</b> %s <br/> <b>Apellido:</b> %s <br/> <b> Apellido 2:</b> %s <br/>"
                                     , p.getNombre(), p.getApellido1(), p.getApellido2()));
            pw.println("<hr/>");
        }
        pw.println("</BODY></HTML>");
        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
        foo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
        foo(request, response);
    }
}
