package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.gui.books.BooksConstants;

import javax.swing.*;
import java.awt.*;

public class BooksSearchFieldsPanel extends JPanel {
    private JComboBox<String> searchComboBox1;
    private JTextField searchField1;
    private JComboBox<String> searchComboBox2;
    private JTextField searchField2;
    private JComboBox<String> searchComboBox3;
    private JTextField searchField3;

    public BooksSearchFieldsPanel(){
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();
    }

    private void init(){
        // instantiate widgets
        searchComboBox1 = new JComboBox<>();
        searchField1 = new JTextField();

        searchComboBox2 = new JComboBox<>();
        searchField2 = new JTextField();

        searchComboBox3 = new JComboBox<>();
        searchField3 = new JTextField();

        // add the book field search criteria to combo boxes
        for (int i = 0; i < BooksConstants.FIELDS.length; i++){
            searchComboBox1.addItem(BooksConstants.FIELDS[i]);
            searchComboBox2.addItem(BooksConstants.FIELDS[i]);
            searchComboBox3.addItem(BooksConstants.FIELDS[i]);
        }
    }

    private void addWidgetsToPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        // make widgets center in the middle and let them expand horizontally
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(searchComboBox1, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0d;
        this.add(searchField1, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        this.add(searchComboBox2, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0d;
        this.add(searchField2, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        this.add(searchComboBox3, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1.0d;
        this.add(searchField3, gbc);
    }
}
