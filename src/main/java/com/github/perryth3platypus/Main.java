package com.github.perryth3platypus;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.perryth3platypus.controller.DatabaseConnector;
import com.github.perryth3platypus.gui.DatabaseConnectorFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        DatabaseConnectorFrame databaseConnectorFrame = new DatabaseConnectorFrame();
        databaseConnectorFrame.setDbConnector(new DatabaseConnector());
        databaseConnectorFrame.start();

    }
}