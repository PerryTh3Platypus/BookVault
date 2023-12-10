package com.github.perryth3platypus;

import com.github.perryth3platypus.controller.DatabaseConnector;
import com.github.perryth3platypus.gui.DatabaseConnectorFrame;
public class Main {
    public static void main(String[] args) {
        DatabaseConnectorFrame databaseConnectorFrame = new DatabaseConnectorFrame();
        databaseConnectorFrame.setDbConnector(new DatabaseConnector());
        databaseConnectorFrame.start();
    }
}