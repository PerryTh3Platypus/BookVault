package com.github.perryth3platypus.gui.books.add;

import javax.swing.*;
import java.awt.*;

public class AddBookMainPanel extends JPanel{
    /* Panel that will be used for adding books and persisting them to db */
    private AddBookFormPanel addBookFormPanel;
    private JButton saveBookButton;

    public AddBookMainPanel(){
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();
    }

    public void init(){
        addBookFormPanel = new AddBookFormPanel();
        saveBookButton = new JButton("Add");
    }

    public void addWidgetsToPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0d;
        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(addBookFormPanel, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 1;
        this.add(saveBookButton, gbc);

    }



}
