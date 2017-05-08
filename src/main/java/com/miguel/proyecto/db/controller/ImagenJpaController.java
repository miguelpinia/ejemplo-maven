/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyecto.db.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.miguel.proyecto.db.Imagen;
import com.miguel.proyecto.db.controller.exceptions.NonexistentEntityException;

/**
 *
 * @author miguel
 */
public class ImagenJpaController implements Serializable {

    public ImagenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagen imagen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(imagen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagen imagen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            imagen = em.merge(imagen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = imagen.getId();
                if (findImagen(id) == null) {
                    throw new NonexistentEntityException("The imagen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagen imagen;
            try {
                imagen = em.getReference(Imagen.class, id);
                imagen.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagen with id " + id + " no longer exists.", enfe);
            }
            em.remove(imagen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagen> findImagenEntities() {
        return findImagenEntities(true, -1, -1);
    }

    public List<Imagen> findImagenEntities(int maxResults, int firstResult) {
        return findImagenEntities(false, maxResults, firstResult);
    }

    private List<Imagen> findImagenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Imagen.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Imagen findImagen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagen.class, id);
        } finally {
            em.close();
        }
    }

    public Imagen findImagen(String nombre) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Imagen.findByNombre");
        q.setParameter("nombre", nombre);
        return (Imagen) q.getResultList().get(0);
    }

    public int getImagenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Imagen> rt = cq.from(Imagen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
