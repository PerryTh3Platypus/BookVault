package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.controller.DatabaseController;

import javax.swing.*;
import java.awt.*;

public class SearchBookMainPanel extends JPanel {
    private SearchBookTitlePanel searchBookTitlePanel;
    private SearchBookFieldsPanel searchBookFieldsPanel;
    private SearchBookResultsPanel searchBookResultsPanel;

    private DatabaseController dbController;

    public SearchBookMainPanel(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();
    }

    public void init(){
        searchBookTitlePanel = new SearchBookTitlePanel();
        searchBookFieldsPanel = new SearchBookFieldsPanel();
        searchBookResultsPanel = new SearchBookResultsPanel();
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
        this.add(searchBookTitlePanel, gbc);

        gbc.gridy = 1;
        this.add(searchBookFieldsPanel, gbc);

        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        this.add(searchBookResultsPanel, gbc);

    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }
}
