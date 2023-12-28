package com.github.perryth3platypus.gui.books.search;

import javax.swing.*;
import java.awt.*;

public class BooksSearchTitlePanel extends JPanel {
    /* This class exists because there seems to be an unwanted interaction with
    * search fields and resizing on the same column*/
    private JLabel searchLabel;
    private JTextField searchField;

    public BooksSearchTitlePanel(){
        searchLabel = new JLabel("Title: ");
        searchField = new JTextField();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0d;
        this.add(searchField, gbc);
    }
}
