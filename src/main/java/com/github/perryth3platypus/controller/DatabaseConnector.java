package com.github.perryth3platypus.controller;


import com.github.perryth3platypus.interfaces.DatabaseConnectorStatusListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseConnector{ // should probably be singleton

    ArrayList<DatabaseConnectorStatusListener> statusListeners; // used to notify object listening (like the connector frame) when a this object logs a message
    EntityManagerFactory entityManagerFactory; // db controller factory
    HashMap<String, Object> entityManagerFactoryProperties; // used to dynamically set url, user, and password at runtime
    EntityManager entityManager; // db controller

    public DatabaseConnector(){
        statusListeners = new ArrayList<>();
        entityManagerFactoryProperties = new HashMap<>();
    }

    public void setUrl(String url){
        entityManagerFactoryProperties.put("javax.persistence.jdbc.url", url);
    }

    public void setUser(String user){
        entityManagerFactoryProperties.put("javax.persistence.jdbc.user", user);
    }

    public void setPassword(String password){
        entityManagerFactoryProperties.put("javax.persistence.jdbc.password", password);
    }

    public void connect(){
        if (entityManagerFactory != null) // close emf is there is one already open
            entityManagerFactory.close();
        if (entityManager != null) // close em if there is one already open
            entityManager.close();


        notifyStatusListeners("Attempting to connect to BookVault database");

        // attempt connecting to the database
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("BookVaultPU", entityManagerFactoryProperties); // create an emf with persistence.xml static properties and emfp dynamic properties
        }catch (Exception ex){
            notifyStatusListeners("Cannot get a connection.");
            notifyStatusListeners("JDBC might not be properly initialized, DB might be offline, or credentials are invalid");
            notifyStatusListeners("Check STDERR for more information.");
            ex.printStackTrace();
            return;
        }

        entityManagerFactoryProperties = new HashMap<>(); // hopefully the jvm garbage collector will destroy that password. Will need to change the way this is done later


        entityManager = entityManagerFactory.createEntityManager();

//        if (testConnection())
//            notifyStatusListeners("Connected to BookVault database at " + entityManagerFactory.getProperties().get("hibernate.connection.url"));
//        else{
//            notifyStatusListeners("Failed to connect to BookVault database. Dumping connection properties\n" + entityManagerFactory.getProperties());
//            entityManagerFactory.close();
//            entityManagerFactory = null;
//            return;
//        }

        notifyStatusListeners("Connection established.");
    }

    public boolean testConnection(){
        try {
            Query query = entityManager.createNativeQuery("SELECT DATABASE()");
            String dbName = (String) query.getSingleResult();
            return dbName.equals("BookVault");
        }catch(NullPointerException ex){
            return false;
        }

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private void notifyStatusListeners(String message){
        // notify observers that are subscribed
        for (DatabaseConnectorStatusListener dbConnListener : statusListeners)
            dbConnListener.updateStatus(message);
    }

    public ArrayList<DatabaseConnectorStatusListener> getStatusListeners() {
        return statusListeners;
    }
}
