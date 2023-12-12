package com.github.perryth3platypus.gui;

import com.github.perryth3platypus.controller.DatabaseConnector;
import com.github.perryth3platypus.interfaces.DatabaseConnectorStatusListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DatabaseConnectorFrame extends JFrame implements ActionListener, DatabaseConnectorStatusListener {

    private DatabaseConnector dbConnector;

    Toolkit monitorSize; // used to determine monitor size

    JPanel mainPanel; // main panel of the frame: all widgets go here
    JLabel urlFieldLabel;
    JTextField urlField; // this is where the database's url goes
    JButton connectButton; // click to connect to db
    JLabel databaseUserLabel;
    JTextField databaseUserField; // database username
    JLabel databasePasswordLabel;
    JPasswordField databasePasswordField; // database password
    JTextArea status; // prints out stuff in case this client cant connect to the database
    JScrollPane statusScrollPane; // adds a scrollbar to the status text area
    GridBagConstraints gbc;

    public DatabaseConnectorFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Connect to your database");
        monitorSize = Toolkit.getDefaultToolkit();
        this.setSize(monitorSize.getScreenSize().width / 2, monitorSize.getScreenSize().height / 4);
        this.setLocationRelativeTo(null); // make the window pop up in the middle of the sceren
    }


    private void init(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbc.anchor = GridBagConstraints.CENTER;

        urlFieldLabel = new JLabel("Database URL (skip this step. Atm it's using whatever is in persistence.xml): ");
        urlField = new JTextField();
        urlField.setEditable(false);

        connectButton = new JButton("Connect");
        connectButton.setFocusable(false);

        databaseUserLabel = new JLabel("User: ");
        databasePasswordLabel = new JLabel("Password: ");

        databaseUserField = new JTextField();
        databasePasswordField = new JPasswordField();


        status = new JTextArea();
        status.setEditable(false);
        status.setLineWrap(true);
        status.setBackground(mainPanel.getBackground());
        status.setCaretColor(new Color(0,0,0,0));
        statusScrollPane = new JScrollPane(status);
        statusScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statusScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        statusScrollPane.setBorder(new EmptyBorder(0,0,0,0));
        statusScrollPane.setBackground(new Color(0,0,0,0));
        status.setFocusable(false);
    }

    private void addWidgetsToPanel(){
        // add url field label at position 0,0
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(urlFieldLabel, gbc);
        // add the user label at position 0,1
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(databaseUserLabel, gbc);
        // add the password label at position 0,2
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(databasePasswordLabel, gbc);
        // add the connect button at position 0,3
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(connectButton, gbc);

        // make the next widgets resizable
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0d;

        // add the url field at position 1, 0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(urlField, gbc);
        // add the user field at position 1,1
        gbc.gridy = 1;
        mainPanel.add(databaseUserField, gbc);
        //add the password field at position 1,2
        gbc.gridy = 2;
        mainPanel.add(databasePasswordField, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0d;
        gbc.gridy = 3;
        mainPanel.add(statusScrollPane, gbc);

        //add the main panel to the frame
        this.getContentPane().add(mainPanel);

    }

    private void addActionListenersToWidgets(){
        connectButton.addActionListener(this);
    }

    public void start(){
        init();
        addWidgetsToPanel();
        addActionListenersToWidgets();
        this.setVisible(true);
    }

    public void setDbConnector(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
        this.dbConnector.getStatusListeners().add(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton){
            //dbConnector.setUrl(urlField.getText());
            dbConnector.setUser(databaseUserField.getText());

            // not ideal, but it'll work for now.
            dbConnector.setPassword(databasePasswordField.getText()); // this will need to get patched later.


            dbConnector.start();
            System.out.println("hello");
        }
    }

    @Override
    public void updateStatus(String message) {
        status.append(message + "\n");
    }
}
