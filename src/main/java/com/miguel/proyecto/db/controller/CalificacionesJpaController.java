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

import com.miguel.proyecto.db.Calificaciones;
import com.miguel.proyecto.db.controller.exceptions.NonexistentEntityException;

/**
 *
 * @author miguel
 */
public class CalificacionesJpaController implements Serializable {

    public CalificacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificaciones calificaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(calificaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificaciones calificaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calificaciones = em.merge(calificaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calificaciones.getId();
                if (findCalificaciones(id) == null) {
                    throw new NonexistentEntityException("The calificaciones with id " + id + " no longer exists.");
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
            Calificaciones calificaciones;
            try {
                calificaciones = em.getReference(Calificaciones.class, id);
                calificaciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(calificaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificaciones> findCalificacionesEntities() {
        return findCalificacionesEntities(true, -1, -1);
    }

    public List<Calificaciones> findCalificacionesEntities(int maxResults, int firstResult) {
        return findCalificacionesEntities(false, maxResults, firstResult);
    }

    private List<Calificaciones> findCalificacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificaciones.class));
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

    public Calificaciones findCalificaciones(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificaciones> rt = cq.from(Calificaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Double getPromedio() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select AVG(c.calificacion) from Calificaciones c");
            Double result = (Double) q.getSingleResult();
            return result;
        } finally {
            em.close();
        }
    }

    public Long getSumaPromedio() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select SUM(c.calificacion) from Calificaciones c");
            Long result = (Long) q.getSingleResult();
            return result;
        } finally {
            em.close();
        }
    }

}
