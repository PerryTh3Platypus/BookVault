package com.github.perryth3platypus;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.perryth3platypus.controller.DatabaseConnector;
import com.github.perryth3platypus.gui.DatabaseConnectorFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FlatDarkLaf.setup();

        DatabaseConnector dbConnector = new DatabaseConnector();

        DatabaseConnectorFrame databaseConnectorFrame = new DatabaseConnectorFrame(dbConnector);
        SwingUtilities.invokeLater(() -> databaseConnectorFrame.start());
        // the MainFrame gets spawned inside DatabaseConnectorFrame after acquiring a valid db connection
        //todo genres should be dynamic and a combolist

    }
}