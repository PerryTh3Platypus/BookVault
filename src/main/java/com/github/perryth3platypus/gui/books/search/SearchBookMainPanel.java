package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.books.BooksConstants;
import com.github.perryth3platypus.gui.books.edit.EditBookMainPanel;
import com.github.perryth3platypus.model.cache.EntityCache;
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
    private EditBookMainPanel editBookMainPanel;

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
        editBookMainPanel = new EditBookMainPanel(dbController);
        editBookMainPanel.start();
    }

    public void addWidgetsToPanel(){
        this.add(BorderLayout.NORTH, searchBookFieldsPanel);
        this.add(BorderLayout.CENTER ,searchBookResultsPanel);
        this.add(BorderLayout.SOUTH ,buttonsPanel);

    }

    private void bindActionListenersToButtons(){
        searchButton.addActionListener(this);
        editButton.addActionListener(this);
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton){
            // read books from database and put them inside the cache
            EntityCache.generateBookCache(dbController.readEntities(Book.class, searchBookFieldsPanel.getSearchConditions()));
            searchBookResultsPanel.updateTableModel(EntityCache.bookCache.values().stream().toList());
        }

        if (e.getSource() == editButton){
            // gets row index from the table
            int selectedRow = searchBookResultsPanel.getResultsTable().getSelectedRow();
            // gets row index from the model, this is the correct index of the row because someone might sort by a column
            int actuallySelectedRow = searchBookResultsPanel.getResultsTable().convertRowIndexToModel(selectedRow);
            int bookId = Integer.parseInt(searchBookResultsPanel.getTableModel().getValueAt(actuallySelectedRow, 0).toString());
            Book bookToEdit = EntityCache.bookCache.get(bookId);

            // get the most up-to-date version of the entity before attempting to edit it
            dbController.refreshEntity(bookToEdit);

            System.out.println("Starting book editing operation");
            System.out.println("Editing book with id: " + bookId);
            System.out.println("and name: " + bookToEdit.getTitle());
            editBookMainPanel.editBook(bookToEdit);
        }
    }
}
