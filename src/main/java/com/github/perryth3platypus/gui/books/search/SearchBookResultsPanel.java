package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.gui.books.BooksConstants;
import com.github.perryth3platypus.interfaces.EntityChangeListener;
import com.github.perryth3platypus.model.entities.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class SearchBookResultsPanel extends JPanel implements EntityChangeListener {
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;


    public SearchBookResultsPanel(){
        //todo: add buttons at the bottom here to modify/delete books that are selected in the results table
        resultsTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(BooksConstants.FIELDS);
        resultsTable.setModel(tableModel);
        resultsTable.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(resultsTable);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTableModel(List<Book> books){
        tableModel.setRowCount(0); // reset the model

        for (Book book : books){
            book.subscribeAsObserver(this);

            HashMap<String, String> bookAttributes = book.dumpAllAttributes();
            Vector<String> rowData = new Vector<>();
            for(int i = 0; i < tableModel.getColumnCount(); i++) {
                String columnName = tableModel.getColumnName(i);
                String value = bookAttributes.get(columnName);
                rowData.add(value);
            }
            tableModel.addRow(rowData);
        }
    }

    public JTable getResultsTable() {
        return resultsTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    private int findRowIndexById(int targetId) {
        for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
            // Assuming the ID column is at index 0 (adjust if it's at a different index)
            int idValue = Integer.parseInt((String)tableModel.getValueAt(rowIndex, 0));

            if (idValue == targetId) {
                // Found the row with the matching ID
                return rowIndex;
            }
        }

        // Return -1 if the ID is not found
        return -1;
    }

    @Override
    public void entityChangeOccurred(Object entity, EVENT event) {
        /* this is VERY inefficient, but it'll work for now. This has a prototype status. Definitely needs to be rewritten.
           With that being said, let's walk through how it works.*/

        // method receives 2 arguments, an entity and an event, possible events being update and delete

        if (event == EVENT.UPDATE){
            Book book = (Book)entity;
            // stores all book attributes in a map
            HashMap<String, String> bookAttributes = book.dumpAllAttributes();
            // create a vector that'll hold data for the new row inside the JTable's model
            Vector<String> rowData = new Vector<>();

            // this loop stores book data inside the rowData vector, using the order of the columns in the table
            for(int i = 0; i < tableModel.getColumnCount(); i++) {
                // store column name
                String columnName = tableModel.getColumnName(i);
                // get data that column from the book attributes map, using the column name as key to access said data
                String value = bookAttributes.get(columnName);
                // add book data to vector
                rowData.add(value);
            }

            // find the row that holds the book that just updated, search by bookId
            int rowIndexToModify = findRowIndexById(book.getBookId());

            // if the book that god updated is not in the results table, exit subroutine
            if (rowIndexToModify == -1)
                return;

            // if the book is inside the results table, start replacing columns with new data
            for (int i = 0; i < tableModel.getColumnCount(); i++){
                tableModel.setValueAt(rowData.get(i), rowIndexToModify, i);
            }
        }
    }
}
