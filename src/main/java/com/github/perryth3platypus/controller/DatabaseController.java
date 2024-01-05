package com.github.perryth3platypus.controller;

import com.github.perryth3platypus.model.entities.Book;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseController {
    public enum CRUDOperation{
        CREATE, UPDATE, DELETE
    }

    private DatabaseConnector dbConnector;

    public DatabaseController(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public <T> List<T> loadAllObjects(Class<T> type){
        // loads all objects of a type from the database to memory
        try {
            CriteriaBuilder criteriaBuilder = dbConnector.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> criteria = criteriaBuilder.createQuery(type);
            criteria.from(type);
            return dbConnector.getEntityManager().createQuery(criteria).getResultList();
        } catch (Exception ex) { // todo: add more specific exception handling here
            ex.printStackTrace();
            return null;
        }
    }




    public <T> List<T> readEntities(Class<T> entityType, Map<String, String> searchConditions){
        // Map<String, String> searchConditions will have key = entity attribute name, value = entity attribute value you want to search for

        CriteriaBuilder criteriaBuilder = dbConnector.getEntityManager().getCriteriaBuilder();


        CriteriaQuery<T> entityCriteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> entityRoot = entityCriteriaQuery.from(entityType);

        ArrayList<Predicate> searchPredicates = new ArrayList<>();
        for (Map.Entry<String, String> searchCondition : searchConditions.entrySet()){
            Predicate searchPredicate = criteriaBuilder.like(entityRoot.get(searchCondition.getKey()), "%" + searchCondition.getValue() + "%");
            searchPredicates.add(searchPredicate);
        }

        Predicate finalPredicate = criteriaBuilder.and(searchPredicates.toArray(new Predicate[0]));
        entityCriteriaQuery.where(finalPredicate);

        return dbConnector.getEntityManager().createQuery(entityCriteriaQuery).getResultList();
    }

    public boolean performCRUDOperation(Object object, CRUDOperation operation){
        EntityTransaction transaction = dbConnector.getEntityManager().getTransaction();
        try{
            transaction.begin();
            switch (operation){
                case CREATE -> dbConnector.getEntityManager().persist(object);
                case UPDATE -> dbConnector.getEntityManager().merge(object);
                case DELETE -> dbConnector.getEntityManager().remove(object);
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

    public void refreshEntity(Object object){
        dbConnector.getEntityManager().refresh(object);
    }
}
