package com.github.perryth3platypus.gui.books.edit;

import com.github.perryth3platypus.controller.DatabaseController;
import com.github.perryth3platypus.gui.books.add.AddBookFormPanel;
import com.github.perryth3platypus.gui.books.add.AddBookMainPanel;
import com.github.perryth3platypus.model.entities.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookMainPanel extends AddBookMainPanel implements ActionListener {

    private JFrame editBookFrame;
    private Book book;

    public EditBookMainPanel(DatabaseController dbController) {
        super(dbController);
        saveBookButton.setText("Save");
    }

    @Override
    public void init() {
        super.init();
        editBookFrame = new JFrame();
        editBookFrame.getContentPane().add(BorderLayout.CENTER, this);
        editBookFrame.setLocationRelativeTo(null);
        editBookFrame.setAlwaysOnTop(true);
        editBookFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void editBook(Book book){
        this.book = book;
        addBookFormPanel.getTitleTextField().setText(book.getTitle());
        addBookFormPanel.getAuthorTextField().setText(book.getAuthor());
        addBookFormPanel.getSubjectTextField().setText(book.getSubject());
        addBookFormPanel.getGenreTextField().setText(book.getGenre());
        addBookFormPanel.getSeriesTextField().setText(book.getSeries().getSeriesName());
        addBookFormPanel.getVolumeNumberTextField().setText(book.getVolumeNumber());
        addBookFormPanel.getEditionNumberTextField().setText(book.getEditionNumber());
        addBookFormPanel.getCountryTextField().setText(book.getCountry());
        addBookFormPanel.getBookLanguageTextField().setText(book.getBookLanguage());
        addBookFormPanel.getReleasedYearTextField().setText(book.getReleasedYear());
        addBookFormPanel.getImprintTextField().setText(book.getImprint());
        addBookFormPanel.getPublisherTextField().setText(book.getPublisher());
        addBookFormPanel.getIsbnTextField().setText(book.getIsbn());
        addBookFormPanel.getBarcodeTextField().setText(book.getBarcode());
        addBookFormPanel.getAccessionNumberTextField().setText(book.getAccessionNumber());
        addBookFormPanel.getLocationTextField().setText(book.getLocation().getLocationName());
        addBookFormPanel.getNotesTextField().setText(book.getNotes());

        editBookFrame.pack();
        this.repaint();
        editBookFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBookButton){
            //todo: rewrite this later
            book.setTitle(addBookFormPanel.getTitleTextField().getText());
            book.setAuthor(addBookFormPanel.getAuthorTextField().getText());
            book.setSubject(addBookFormPanel.getSubjectTextField().getText());
            book.setGenre(addBookFormPanel.getGenreTextField().getText());
            book.getSeries().setSeriesName(addBookFormPanel.getSeriesTextField().getText());
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
            book.getLocation().setLocationName(addBookFormPanel.getLocationTextField().getText());
            book.setNotes(addBookFormPanel.getNotesTextField().getText());

            // merge entiies in case they're not tracked
            dbController.performCRUDOperation(book.getSeries(), DatabaseController.CRUDOperation.UPDATE);
            dbController.performCRUDOperation(book.getLocation(), DatabaseController.CRUDOperation.UPDATE);
            dbController.performCRUDOperation(book, DatabaseController.CRUDOperation.UPDATE);

            editBookFrame.setVisible(false);
            //todo: error checks here
            System.out.println("New book title: " + book.getTitle());
        }
    }
}
