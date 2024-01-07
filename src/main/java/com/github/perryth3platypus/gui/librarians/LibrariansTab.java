package com.github.perryth3platypus.gui.librarians;

import com.github.perryth3platypus.controller.DatabaseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LibrariansTab extends JPanel implements ActionListener {
    private JComboBox<String> searchComboBoxIDLibrarian;
    private JTextField searchFieldIDLibriarian;
    private JComboBox<String> searchComboBoxUsername;
    private JTextField searchFieldUsername;
    private JComboBox<String> searchComboBoxPassword;
    private JTextField searchFieldPassword;
    private JComboBox<String> searchComboBoxFirstName;
    private JTextField searchFieldFirstName;
    private JComboBox<String> searchComboBoxLastName;
    private JTextField searchFieldLastName;
    private JComboBox<String> searchComboBoxEmail;
    private JTextField searchFieldEmail;
    private JComboBox<String> searchComboBoxPhone;
    private JTextField searchFieldPhone;
    private JButton addLibrarianButton;
    private HashMap<JComboBox<String>, JTextField> searchFieldMap;

    private DatabaseController dbController;
    private JPanel buttonsPanel;

    public LibrariansTab(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new BorderLayout());
        init();
        addWidgetsToPanel();
        bindActionListenerToButtons();
    }

    public void bindActionListenerToButtons(){
        addLibrarianButton.addActionListener(this);
    }
    public LibrariansTab() {
        this.setLayout(new GridBagLayout());
        init();
        addWidgetsToPanel();

        // Populează câmpurile de căutare cu opțiunile disponibile pentru împrumuturi
        //populateSearchOptions(dbController);
    }

    private void init() {
        // Instantierea widget-urilor
        searchFieldMap = new HashMap<>();
        searchComboBoxIDLibrarian = new JComboBox<>();
        searchFieldIDLibriarian = new JTextField();
        searchFieldMap.put(searchComboBoxIDLibrarian, searchFieldIDLibriarian);

        searchComboBoxUsername = new JComboBox<>();
        searchFieldUsername = new JTextField();
        searchFieldMap.put(searchComboBoxUsername, searchFieldUsername);

        searchComboBoxPassword = new JComboBox<>();
        searchFieldPassword = new JTextField();
        searchFieldMap.put(searchComboBoxPassword, searchFieldPassword);

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

        addLibrarianButton = new JButton("Add");
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(addLibrarianButton);

        // Adaugarea opțiunilor de căutare pentru împrumuturi în combo boxes
        // Poți adăuga altele în funcție de nevoile tale
        searchComboBoxIDLibrarian.addItem("Librarian ID");
        searchComboBoxUsername.addItem("Username");
        searchComboBoxPassword.addItem("Password");
        searchComboBoxFirstName.addItem("First Name");
        searchComboBoxLastName.addItem("Last Name");
        searchComboBoxEmail.addItem("Email");
        searchComboBoxPhone.addItem("Phone Number");
    }

    private void addWidgetsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(searchComboBoxIDLibrarian, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0d;
        this.add(searchFieldIDLibriarian, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        this.add(searchComboBoxUsername, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0d;
        this.add(searchFieldUsername, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        this.add(searchComboBoxPassword, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1.0d;
        this.add(searchFieldPassword, gbc);

        gbc.gridx = 6;
        gbc.weightx = 0;
        this.add(searchComboBoxFirstName, gbc);

        gbc.gridx = 7;
        gbc.weightx = 1.0d;
        this.add(searchFieldFirstName, gbc);

        gbc.gridx = 8;
        gbc.weightx = 0;
        this.add(searchComboBoxLastName, gbc);

        gbc.gridx = 9;
        gbc.weightx = 1.0d;
        this.add(searchFieldLastName, gbc);

        gbc.gridx = 10;
        gbc.weightx = 0;
        this.add(searchComboBoxEmail, gbc);

        gbc.gridx = 11;
        gbc.weightx = 1.0d;
        this.add(searchFieldEmail, gbc);

        gbc.gridx = 12;
        gbc.weightx = 0;
        this.add(searchComboBoxPhone, gbc);

        gbc.gridx = 13;
        gbc.weightx = 1.0d;
        this.add(searchFieldPhone, gbc);

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
