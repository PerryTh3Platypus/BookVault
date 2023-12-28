package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.gui.books.BooksConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchBookResultsPanel extends JPanel {
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;


    public SearchBookResultsPanel(){
        resultsTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(BooksConstants.FIELDS);
        resultsTable.setModel(tableModel);
        resultsTable.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(resultsTable);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public DefaultTableModel getTableModel() {
        // getter for table model to add or clear rows/entries from results
        return tableModel;
    }
}
