package com.github.perryth3platypus.gui.books;

import com.github.perryth3platypus.gui.books.search.BooksMainPanel;

import javax.swing.*;
import java.awt.*;


public class BooksTab extends JPanel {
    private BooksMainPanel booksMainPanel;
    private JButton newBookButton;
    private JButton searchBookButton;

    public BooksTab(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0d;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(new BooksMainPanel(), gbc);
    }

}
