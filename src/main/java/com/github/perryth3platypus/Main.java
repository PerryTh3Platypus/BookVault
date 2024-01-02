package com.github.perryth3platypus;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.github.perryth3platypus.controller.DatabaseConnector;
import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.DatabaseConnectorFrame;
import com.github.perryth3platypus.gui.MainFrame;
import com.github.perryth3platypus.model.Book;
import com.github.perryth3platypus.model.InternalLocation;
import com.github.perryth3platypus.model.Series;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FlatDarkLaf.setup();

        DatabaseConnector dbConnector = new DatabaseConnector();
        DatabaseController dbController = new DatabaseController(dbConnector);

        DatabaseConnectorFrame databaseConnectorFrame = new DatabaseConnectorFrame(dbConnector);
        SwingUtilities.invokeLater(() -> databaseConnectorFrame.start());
        // the MainFrame gets spawned inside DatabaseConnectorFrame after acquiring a valid db connection
        //todo genres should be dynamic and a combolist

    }
}