package com.github.perryth3platypus.gui;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.books.BooksTab;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.concurrent.CountDownLatch;

import static java.awt.BorderLayout.CENTER;

public class MainFrame extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabs;

    private BooksTab booksTab;



    private DatabaseController dbController;

    public MainFrame(DatabaseController dbController){
        this.dbController = dbController;
    }

    private void init(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        tabs = new JTabbedPane();
        booksTab = new BooksTab(dbController);
    }

    private void addTabsToTabbedPane(){
        tabs.addTab("Books", booksTab);
    }

    private void addWidgetsToPanel(){
        mainPanel.add(BorderLayout.CENTER, tabs);
    }

    public void start(){
        init();
        addTabsToTabbedPane();
        addWidgetsToPanel();
        this.setSize(1600, 900);
        this.setTitle("This is the MainFrame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(BorderLayout.CENTER, mainPanel);
        this.setVisible(true);
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }
}
