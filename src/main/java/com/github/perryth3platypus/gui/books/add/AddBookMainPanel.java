package com.github.perryth3platypus.gui.books.add;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.interfaces.ValidEntityListener;
import com.github.perryth3platypus.model.entities.Book;
import com.github.perryth3platypus.model.entities.InternalLocation;
import com.github.perryth3platypus.model.entities.Series;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookMainPanel extends JPanel implements ActionListener, ValidEntityListener {
    /* Panel that will be used for adding books and persisting them to db */
    protected AddBookFormPanel addBookFormPanel;
    protected JButton saveBookButton;

    protected DatabaseController dbController;



    public AddBookMainPanel(DatabaseController dbController){
        this.dbController = dbController;

        this.setLayout(new GridBagLayout());

    }

    public void init(){
        addBookFormPanel = new AddBookFormPanel();
        saveBookButton = new JButton("Add Book");
        saveBookButton.setEnabled(false);
    }

    public void addWidgetsToPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0d;
        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(addBookFormPanel, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 1;
        this.add(saveBookButton, gbc);
    }

    public void start(){
        init();
        addWidgetsToPanel();
        bindActionListenerToButtons();

        addBookFormPanel.setValidEntityListener(this);
        addBookFormPanel.start();
    }

    public void bindActionListenerToButtons(){
        saveBookButton.addActionListener(this);
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }

    protected void bookAdditionSuccess(){
        // shows a dialogue info msg and clears text fields
        JOptionPane.showMessageDialog(this, "Book successfully added", "Book Addition", JOptionPane.INFORMATION_MESSAGE);
        addBookFormPanel.getTextFields().forEach(tf -> tf.setText(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBookButton){
            //todo: rewrite this later
            Book book = new Book();
            book.setTitle(addBookFormPanel.getTitleTextField().getText());
            book.setAuthor(addBookFormPanel.getAuthorTextField().getText());
            book.setSubject(addBookFormPanel.getSubjectTextField().getText());
            book.setGenre(addBookFormPanel.getGenreTextField().getText());
            Series series = new Series();
            series.setSeriesName(addBookFormPanel.getSeriesTextField().getText());
            // no series description for now
            book.setSeries(series);
            book.setVolumeNumber(addBookFormPanel.getVolumeNumberTextField().getText());
            book.setEditionNumber(addBookFormPanel.getEditionNumberTextField().getText());
            book.setCountry(addBookFormPanel.getCountryTextField().getText());
            book.setBookLanguage(addBookFormPanel.getBookLanguageTextField().getText());
            book.setReleasedYear(addBookFormPanel.getReleasedYearTextField().getText());
            book.setImprint(addBookFormPanel.getImprintTextField().getText());
            book.setPublisher(addBookFormPanel.getPublisherTextField().getText());
            book.setIsbn(addBookFormPanel.getIsbnTextField().getText());
            book.setBarcode(addBookFormPanel.getBarcodeTextField().getText());
            book.setAccessionNumber(addBookFormPanel.getAccessionNumberTextField().getText());
            InternalLocation internalLocation = new InternalLocation();
            internalLocation.setLocationName(addBookFormPanel.getLocationTextField().getText());
            // no location description for now
            book.setLocation(internalLocation);
            book.setNotes(addBookFormPanel.getNotesTextField().getText());

            dbController.performCRUDOperation(series, DatabaseController.CRUDOperation.CREATE);
            dbController.performCRUDOperation(internalLocation, DatabaseController.CRUDOperation.CREATE);
            dbController.performCRUDOperation(book, DatabaseController.CRUDOperation.CREATE);
            //todo: error checks here
            bookAdditionSuccess();
        }
    }

    @Override
    public void entityIsValid(boolean entityStatus) {
        saveBookButton.setEnabled(entityStatus);
    }
}
