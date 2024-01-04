package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.books.BooksConstants;
import com.github.perryth3platypus.model.entities.Book;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchBookMainPanel extends JPanel implements ActionListener {
    private SearchBookFieldsPanel searchBookFieldsPanel;
    private SearchBookResultsPanel searchBookResultsPanel;
    private JButton searchButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel buttonsPanel;

    private DatabaseController dbController;

    public SearchBookMainPanel(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new BorderLayout());
        init();
        addWidgetsToPanel();
        bindActionListenersToButtons();
    }

    public void init(){
        searchBookFieldsPanel = new SearchBookFieldsPanel();
        searchBookResultsPanel = new SearchBookResultsPanel();

        searchButton = new JButton("Search");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        buttonsPanel = new JPanel();
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(searchButton);

    }

    public void addWidgetsToPanel(){
        this.add(BorderLayout.NORTH, searchBookFieldsPanel);
        this.add(BorderLayout.CENTER ,searchBookResultsPanel);
        this.add(BorderLayout.SOUTH ,buttonsPanel);

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
