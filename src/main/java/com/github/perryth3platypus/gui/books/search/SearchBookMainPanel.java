package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.books.edit.EditBookMainPanel;
import com.github.perryth3platypus.model.cache.EntityCache;
import com.github.perryth3platypus.model.entities.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchBookMainPanel extends JPanel implements ActionListener {
    private SearchBookFieldsPanel searchBookFieldsPanel;
    private SearchBookResultsPanel searchBookResultsPanel;
    private EditBookMainPanel editBookMainPanel;

    private JButton searchButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel searchTypeLabel;
    private JRadioButton andSearchTypeButton;
    private JRadioButton orSearchTypeButton;
    private ButtonGroup searchTypeButtonGroup;
    private JButton showAllBooksButton;
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
        searchTypeLabel = new JLabel("Search Type: ");
        andSearchTypeButton = new JRadioButton("AND");
        orSearchTypeButton = new JRadioButton("OR");
        andSearchTypeButton.setSelected(true);
        searchTypeButtonGroup = new ButtonGroup();
        searchTypeButtonGroup.add(andSearchTypeButton);
        searchTypeButtonGroup.add(orSearchTypeButton);
        showAllBooksButton = new JButton("Show All Books");
        buttonsPanel = new JPanel();
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(searchButton);
        buttonsPanel.add(searchTypeLabel);
        buttonsPanel.add(andSearchTypeButton);
        buttonsPanel.add(orSearchTypeButton);
        buttonsPanel.add(showAllBooksButton);
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
        deleteButton.addActionListener(this);
        showAllBooksButton.addActionListener(this);
    }

    private Book getSelectedBookFromResultsTable(){
        // gets row index from the table
        int selectedRow = searchBookResultsPanel.getResultsTable().getSelectedRow();
        // gets row index from the model, this is the correct index of the row because someone might sort by a column
        int actuallySelectedRow = searchBookResultsPanel.getResultsTable().convertRowIndexToModel(selectedRow);
        int bookId = Integer.parseInt(searchBookResultsPanel.getTableModel().getValueAt(actuallySelectedRow, 0).toString());
        Book selectedBook = EntityCache.bookCache.get(bookId);
        return selectedBook;
    }

    private int getSelectedRowFromResultsTable(){
        // gets row index from the table
        int selectedRow = searchBookResultsPanel.getResultsTable().getSelectedRow();
        // gets row index from the model, this is the correct index of the row because someone might sort by a column
        int actuallySelectedRow = searchBookResultsPanel.getResultsTable().convertRowIndexToModel(selectedRow);
        return actuallySelectedRow;
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton){
            // read books from database and put them inside the cache
            DatabaseController.SearchType searchType;
            if (andSearchTypeButton.isSelected())
                searchType = DatabaseController.SearchType.AND;
            else
                searchType = DatabaseController.SearchType.OR;
            EntityCache.generateBookCache(dbController.readEntities(Book.class, searchBookFieldsPanel.getSearchConditions(), searchType));
            searchBookResultsPanel.updateTableModel(EntityCache.bookCache.values().stream().toList());
        }

        if (e.getSource() == editButton){

            Book bookToEdit = getSelectedBookFromResultsTable();

            // get the most up-to-date version of the entity before attempting to edit it
            dbController.refreshEntity(bookToEdit);

            System.out.println("Starting book editing operation");
            System.out.println("Editing book with id: " + bookToEdit.getBookId());
            System.out.println("and name: " + bookToEdit.getTitle());
            editBookMainPanel.editBook(bookToEdit);
        }

        if (e.getSource() == deleteButton){
            Book bookToDelete = getSelectedBookFromResultsTable();
            int answer =  JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the selected book?",
                        "Delete Book", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION){
                dbController.performCRUDOperation(bookToDelete, DatabaseController.CRUDOperation.DELETE);
                EntityCache.bookCache.remove(bookToDelete.getBookId());
                searchBookResultsPanel.getTableModel().removeRow(getSelectedRowFromResultsTable());
            }
        }

        if (e.getSource() == showAllBooksButton){
            ArrayList<Book> allBooks = (ArrayList<Book>) dbController.loadAllObjects(Book.class);
            EntityCache.generateBookCache(allBooks);
            searchBookResultsPanel.updateTableModel(allBooks);
        }
    }
}
