package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.books.BooksConstants;
import com.github.perryth3platypus.model.entities.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchBookMainPanel extends JPanel implements ActionListener {
    private SearchBookFieldsPanel searchBookFieldsPanel;
    private SearchBookResultsPanel searchBookResultsPanel;
    private JButton searchButton;

    private DatabaseController dbController;

    public SearchBookMainPanel(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();
        bindActionListenersToButtons();
    }

    public void init(){
        searchBookFieldsPanel = new SearchBookFieldsPanel();
        searchBookResultsPanel = new SearchBookResultsPanel();
        searchButton = new JButton("Search");
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
        this.add(searchBookFieldsPanel, gbc);

        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        this.add(searchBookResultsPanel, gbc);

        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 2;
        this.add(searchButton, gbc);

    }

    private void bindActionListenersToButtons(){
        searchButton.addActionListener(this);
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton){
            ArrayList<Book> books = (ArrayList<Book>) dbController.readEntities(Book.class, searchBookFieldsPanel.getSearchConditions());
            searchBookResultsPanel.updateTableModel(books);
        }
    }
}
