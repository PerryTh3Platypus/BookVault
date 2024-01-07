package com.github.perryth3platypus.gui.loans;

import com.github.perryth3platypus.controller.DatabaseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoansTab extends JPanel implements ActionListener {
    private JComboBox<String> searchComboBox1;
    private JTextField searchField1;
    private JComboBox<String> searchComboBox2;
    private JTextField searchField2;
    private JComboBox<String> searchComboBox3;
    private JTextField searchField3;
    private JComboBox<String> searchComboBox4;
    private JTextField searchField4;
    private JComboBox<String> searchComboBox5;
    private JTextField searchField5;
    private JComboBox<String> searchComboBox6;
    private JTextField searchField6;
    private JComboBox<String> searchComboBox7;
    private JTextField searchField7;
    private JComboBox<String> searchComboBox8;
    private JTextField searchField8;
    private JComboBox<String> searchComboBox9;
    private JTextField searchField9;
    private JButton addLoansButton;
    private HashMap<JComboBox<String>, JTextField> searchFieldMap;

    private DatabaseController dbController;
    private JPanel buttonsPanel;

    public LoansTab(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new BorderLayout());
        init();
        addWidgetsToPanel();
        bindActionListenerToButtons();
    }

    public void bindActionListenerToButtons(){
        addLoansButton.addActionListener(this);
    }
    public LoansTab() {
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();

        // Populează câmpurile de căutare cu opțiunile disponibile pentru împrumuturi
        //populateSearchOptions(dbController);
    }

    private void init() {
        // Instantierea widget-urilor
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

        searchComboBox5 = new JComboBox<>();
        searchField5 = new JTextField();
        searchFieldMap.put(searchComboBox5, searchField5);

        searchComboBox6 = new JComboBox<>();
        searchField6 = new JTextField();
        searchFieldMap.put(searchComboBox6, searchField6);

        searchComboBox7 = new JComboBox<>();
        searchField7 = new JTextField();
        searchFieldMap.put(searchComboBox7, searchField7);

        searchComboBox8 = new JComboBox<>();
        searchField8 = new JTextField();
        searchFieldMap.put(searchComboBox8, searchField8);

        searchComboBox9 = new JComboBox<>();
        searchField9 = new JTextField();
        searchFieldMap.put(searchComboBox9, searchField9);

        addLoansButton = new JButton("Add");
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(addLoansButton);

        // Adaugarea opțiunilor de căutare pentru împrumuturi în combo boxes
        // Poți adăuga altele în funcție de nevoile tale
        searchComboBox1.addItem("Loan ID");
        searchComboBox2.addItem("Borrow Date");
        searchComboBox3.addItem("Book ID");
        searchComboBox4.addItem("Patron ID");
        searchComboBox5.addItem("Due Date");
        searchComboBox6.addItem("Return Date");
        searchComboBox7.addItem("Status ENUM");
        searchComboBox8.addItem("Checked out by");
        searchComboBox9.addItem("Checked in by");
    }

    private void addWidgetsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
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

        gbc.gridx = 8;
        gbc.weightx = 0;
        this.add(searchComboBox5, gbc);

        gbc.gridx = 9;
        gbc.weightx = 1.0d;
        this.add(searchField5, gbc);

        gbc.gridx = 10;
        gbc.weightx = 0;
        this.add(searchComboBox6, gbc);

        gbc.gridx = 11;
        gbc.weightx = 1.0d;
        this.add(searchField6, gbc);

        gbc.gridx = 12;
        gbc.weightx = 0;
        this.add(searchComboBox7, gbc);

        gbc.gridx = 13;
        gbc.weightx = 1.0d;
        this.add(searchField7, gbc);

        gbc.gridx = 14;
        gbc.weightx = 0;
        this.add(searchComboBox8, gbc);

        gbc.gridx = 15;
        gbc.weightx = 1.0d;
        this.add(searchField8, gbc);

        gbc.gridx = 16;
        gbc.weightx = 0;
        this.add(searchComboBox9, gbc);

        gbc.gridx = 17;
        gbc.weightx = 1.0d;
        this.add(searchField9, gbc);
    }

    private void populateSearchOptions(DatabaseController dbController) {
        // Dacă aveți nevoie de opțiuni specifice pentru căutarea împrumuturilor, adăugați-le aici
        // Exemplu: dbController.getLoanSearchOptions()
    }

    public Map<String, String> getSearchConditions() {
        HashMap<String, String> searchConditions = new HashMap<>();
        for (Map.Entry<JComboBox<String>, JTextField> searchFields : searchFieldMap.entrySet()) {
            String searchCriteria = searchFields.getKey().getSelectedItem().toString();
            String searchValue = searchFields.getValue().getText();
            if (searchValue != null && !searchValue.isEmpty())
                searchConditions.put(searchCriteria, searchValue);
        }
        return searchConditions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
