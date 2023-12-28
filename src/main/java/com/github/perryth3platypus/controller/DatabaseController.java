package com.github.perryth3platypus.controller;

import com.github.perryth3platypus.gui.books.BooksConstants;
import com.github.perryth3platypus.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

    public List<Book> loadBooks(String title, String searchField1, String searchTerm1, String searchField2, String searchTerm2, String searchField3, String searchTerm3){
        //todo: update this method later
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);

        Predicate searchPredicate = criteriaBuilder.like(bookRoot.get(BooksConstants.ATTRIBUTE_MAP.get(searchField1)), "%" + searchTerm1.toLowerCase() + "%");
        bookCriteriaQuery.where(searchPredicate);

        return em.createQuery(bookCriteriaQuery).getResultList();
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
