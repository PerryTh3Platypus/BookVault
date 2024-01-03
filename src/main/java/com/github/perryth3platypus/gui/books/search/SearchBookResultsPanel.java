package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.gui.books.BooksConstants;
import com.github.perryth3platypus.model.entities.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class SearchBookResultsPanel extends JPanel {
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
}
