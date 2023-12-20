package com.github.perryth3platypus.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class DatabaseController {
    public enum CRUDOperation{
        CREATE, UPDATE, DELETE
    }
    private EntityManager em;

    public DatabaseController(EntityManager em){
        this.em = em;
    }

    public <T> List<T> loadAllObjects(Class<T> type){
        // loads all objects of a type from the database to memory
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<T> criteria = criteriaBuilder.createQuery(type);
            criteria.from(type);
            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) { // todo: add more specific exception handling here
            ex.printStackTrace();
            return null;
        }
    }

    public boolean performCRUDOperation(Object object, CRUDOperation operation){
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            switch (operation){
                case CREATE -> em.persist(object);
                case UPDATE -> em.merge(object);
                case DELETE -> em.remove(object);
            }
            transaction.commit();
            return true;
        }catch (Exception ex){ // todo: add more specific exception handling here
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

}
