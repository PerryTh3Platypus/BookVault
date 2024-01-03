package com.github.perryth3platypus.gui.books.search;

import com.github.perryth3platypus.gui.books.BooksConstants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SearchBookFieldsPanel extends JPanel {
    private JComboBox<String> searchComboBox1;
    private JTextField searchField1;
    private JComboBox<String> searchComboBox2;
    private JTextField searchField2;
    private JComboBox<String> searchComboBox3;
    private JTextField searchField3;
    private JComboBox<String> searchComboBox4;
    private JTextField searchField4;

    private HashMap<JComboBox<String>, JTextField> searchFieldMap;


    public SearchBookFieldsPanel(){
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();
    }

    private void init(){
        // instantiate widgets
        searchFieldMap = new HashMap<>();
        searchComboBox1 = new JComboBox<>();
        searchField1 = new JTextField();
        searchFieldMap.put(searchComboBox1, searchField1);

        searchComboBox2 = new JComboBox<>();
        searchField2 = new JTextField();
        searchFieldMap.put(searchComboBox2, searchField2);

        searchComboBox3 = new JComboBox<>();
        searchField3 = new JTextField();
        searchFieldMap.put(searchComboBox3, searchField3);

        searchComboBox4 = new JComboBox<>();
        searchField4 = new JTextField();
        searchFieldMap.put(searchComboBox4, searchField4);


        // add the book field search criteria to combo boxes
        for (int i = 1; i < BooksConstants.FIELDS.length; i++){ // start at 1, skip db primary key (id)

            searchComboBox1.addItem(BooksConstants.FIELDS[i]);
            searchComboBox2.addItem(BooksConstants.FIELDS[i]);
            searchComboBox3.addItem(BooksConstants.FIELDS[i]);
            searchComboBox4.addItem(BooksConstants.FIELDS[i]);
        }
    }

    private void addWidgetsToPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        // make widgets center in the middle and let them expand horizontally
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(searchComboBox1, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0d;
        this.add(searchField1, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        this.add(searchComboBox2, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0d;
        this.add(searchField2, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        this.add(searchComboBox3, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1.0d;
        this.add(searchField3, gbc);

        gbc.gridx = 6;
        gbc.weightx = 0;
        this.add(searchComboBox4, gbc);

        gbc.gridx = 7;
        gbc.weightx = 1.0d;
        this.add(searchField4, gbc);
    }

    public JComboBox<String> getSearchComboBox1() {
        return searchComboBox1;
    }

    public Map<String, String> getSearchConditions(){
        HashMap<String, String> searchConditions = new HashMap<>();
        for (Map.Entry<JComboBox<String>, JTextField> searchFields : searchFieldMap.entrySet()){
            String searchCriteria = BooksConstants.ATTRIBUTE_MAP.get(searchFields.getKey().getSelectedItem().toString());
            String searchValue = searchFields.getValue().getText();
            if (searchValue != null && !searchValue.isEmpty())
                searchConditions.put(searchCriteria, searchValue);
        }
        return searchConditions;
    }
}
