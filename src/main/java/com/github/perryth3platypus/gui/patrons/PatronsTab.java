package com.github.perryth3platypus.gui.patrons;

import com.github.perryth3platypus.controller.DatabaseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PatronsTab extends JPanel implements ActionListener {

    private JComboBox<String> searchComboBoxIDPatrons;
    private JTextField searchFieldIDPatrons;
    private JComboBox<String> searchComboBoxFirstName;
    private JTextField searchFieldFirstName;
    private JComboBox<String> searchComboBoxLastName;
    private JTextField searchFieldLastName;
    private JComboBox<String> searchComboBoxEmail;
    private JTextField searchFieldEmail;
    private JComboBox<String> searchComboBoxPhone;
    private JTextField searchFieldPhone;
    private JComboBox<String> searchComboBoxRegistrationDate;
    private JTextField searchFieldRegistrationDate;
    private JButton addPatronsButton;
    private HashMap<JComboBox<String>, JTextField> searchFieldMap;

    private DatabaseController dbController;
    private JPanel buttonsPanel;

    public PatronsTab(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new BorderLayout());
        init();
        addWidgetsToPanel();
        bindActionListenerToButtons();
    }

    public void bindActionListenerToButtons(){
        addPatronsButton.addActionListener(this);
    }
    public PatronsTab() {
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();

        // Populează câmpurile de căutare cu opțiunile disponibile pentru împrumuturi
        //populateSearchOptions(dbController);
    }

    private void init() {
        // Instantierea widget-urilor
        searchFieldMap = new HashMap<>();
        searchComboBoxIDPatrons = new JComboBox<>();
        searchFieldIDPatrons = new JTextField();
        searchFieldMap.put(searchComboBoxIDPatrons, searchFieldIDPatrons);

        searchComboBoxFirstName = new JComboBox<>();
        searchFieldFirstName = new JTextField();
        searchFieldMap.put(searchComboBoxFirstName, searchFieldFirstName);

        searchComboBoxLastName = new JComboBox<>();
        searchFieldLastName = new JTextField();
        searchFieldMap.put(searchComboBoxLastName, searchFieldLastName);

        searchComboBoxEmail = new JComboBox<>();
        searchFieldEmail = new JTextField();
        searchFieldMap.put(searchComboBoxEmail, searchFieldEmail);

        searchComboBoxPhone = new JComboBox<>();
        searchFieldPhone = new JTextField();
        searchFieldMap.put(searchComboBoxPhone, searchFieldPhone);

        searchComboBoxRegistrationDate = new JComboBox<>();
        searchFieldRegistrationDate = new JTextField();
        searchFieldMap.put(searchComboBoxRegistrationDate, searchFieldRegistrationDate);


        addPatronsButton = new JButton("Add");
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(addPatronsButton);

        // Adaugarea opțiunilor de căutare pentru împrumuturi în combo boxes
        // Poți adăuga altele în funcție de nevoile tale
        searchComboBoxIDPatrons.addItem("Patron ID");
        searchComboBoxFirstName.addItem("First Name");
        searchComboBoxLastName.addItem("Last Name");
        searchComboBoxEmail.addItem("Email");
        searchComboBoxPhone.addItem("Phone Number");
        searchComboBoxRegistrationDate.addItem("Registration Date");

    }

    private void addWidgetsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(searchComboBoxIDPatrons, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0d;
        this.add(searchFieldIDPatrons, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        this.add(searchComboBoxFirstName, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0d;
        this.add(searchFieldFirstName, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        this.add(searchComboBoxLastName, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1.0d;
        this.add(searchFieldLastName, gbc);

        gbc.gridx = 6;
        gbc.weightx = 0;
        this.add(searchComboBoxEmail, gbc);

        gbc.gridx = 7;
        gbc.weightx = 1.0d;
        this.add(searchFieldEmail, gbc);

        gbc.gridx = 8;
        gbc.weightx = 0;
        this.add(searchComboBoxPhone, gbc);

        gbc.gridx = 9;
        gbc.weightx = 1.0d;
        this.add(searchFieldPhone, gbc);

        gbc.gridx = 10;
        gbc.weightx = 0;
        this.add(searchComboBoxRegistrationDate, gbc);

        gbc.gridx = 11;
        gbc.weightx = 1.0d;
        this.add(searchFieldRegistrationDate, gbc);


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
