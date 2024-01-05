package com.github.perryth3platypus.interfaces;

public interface EntityChangeListener {
    public static enum EVENT{
        UPDATE, DELETE
    }
    public void entityChangeOccurred(Object entity , EVENT event); // implement this in views
}
