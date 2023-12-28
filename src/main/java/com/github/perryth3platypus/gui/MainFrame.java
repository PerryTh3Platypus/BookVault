package com.github.perryth3platypus.gui;

import com.github.perryth3platypus.gui.books.BooksTab;

import javax.swing.*;

import static java.awt.BorderLayout.CENTER;

public class MainFrame extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabs;

    public MainFrame(){
        mainPanel = new JPanel();
        mainPanel.add(new BooksTab());

        this.setSize(1600, 900);
        this.setTitle("This is the MainFrame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(CENTER, new BooksTab());
        this.setVisible(true);
    }
}
