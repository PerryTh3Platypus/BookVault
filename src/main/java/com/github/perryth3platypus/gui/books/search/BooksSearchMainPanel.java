package com.github.perryth3platypus.gui.books.search;

import javax.swing.*;
import java.awt.*;

public class BooksSearchMainPanel extends JPanel {
    private BooksSearchTitlePanel booksSearchTitlePanel;
    private BooksSearchFieldsPanel booksSearchFieldsPanel;
    private BooksSearchResultsPanel booksSearchResultsPanel;

    public BooksSearchMainPanel(){
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();
    }

    public void init(){
        booksSearchTitlePanel = new BooksSearchTitlePanel();
        booksSearchFieldsPanel = new BooksSearchFieldsPanel();
        booksSearchResultsPanel = new BooksSearchResultsPanel();
    }

    public void addWidgetsToPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        // make widgets center in the middle and let them expand horizontally
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0d;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(booksSearchTitlePanel, gbc);

        gbc.gridy = 1;
        this.add(booksSearchFieldsPanel, gbc);

        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        this.add(booksSearchResultsPanel, gbc);

    }
}
