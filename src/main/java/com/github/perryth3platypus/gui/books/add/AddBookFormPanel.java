package com.github.perryth3platypus.gui.books.add;

import com.github.perryth3platypus.interfaces.ValidEntityListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class AddBookFormPanel extends JPanel implements DocumentListener {
    // holds the form for adding books to db (asks for title, author, genre etc)
    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> textFields;
    private ArrayList<JTextField> mandatoryTextFields;

    private JLabel titleLabel;
    private JTextField titleTextField;
    private JLabel authorLabel;
    private JTextField authorTextField;
    private JLabel subjectLabel;
    private JTextField subjectTextField;
    private JLabel genreLabel;
    private JTextField genreTextField;
    private JLabel seriesLabel;
    private JTextField seriesTextField;
    private JLabel volumeNumberLabel;
    private JTextField volumeNumberTextField;
    private JLabel editionNumberLabel;
    private JTextField editionNumberTextField;
    private JLabel countryLabel;
    private JTextField countryTextField;
    private JLabel bookLanguageLabel;
    private JTextField bookLanguageTextField;
    private JLabel releasedYearLabel;
    private JTextField releasedYearTextField;
    private JLabel imprintLabel;
    private JTextField imprintTextField;
    private JLabel publisherLabel;
    private JTextField publisherTextField;
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JLabel barcodeLabel;
    private JTextField barcodeTextField;
    private JLabel accessionNumberLabel;
    private JTextField accessionNumberTextField;
    private JLabel locationLabel;
    private JTextField locationTextField;
    private JLabel notesLabel;
    private JTextField notesTextField;

    private LineBorder redLineBorder;

    private ValidEntityListener validEntityListener;

    public AddBookFormPanel(){
        labels = new ArrayList<>();
        textFields = new ArrayList<>();
        mandatoryTextFields = new ArrayList<>();

        this.setLayout(new GridBagLayout());
    }

    public void init(){
        //todo: some of these arent mandatory to be filled, find a way to show that in gui
        titleLabel = new JLabel("Title * : ");
        labels.add(titleLabel);
        titleTextField = new JTextField();
        textFields.add(titleTextField);
        mandatoryTextFields.add(titleTextField);

        authorLabel = new JLabel("Author(s) * : ");
        labels.add(authorLabel);
        authorTextField = new JTextField();
        textFields.add(authorTextField);
        mandatoryTextFields.add(authorTextField);

        subjectLabel = new JLabel("Subject: ");
        labels.add(subjectLabel);
        subjectTextField = new JTextField();
        textFields.add(subjectTextField);

        genreLabel = new JLabel("Genre: ");
        labels.add(genreLabel);
        genreTextField = new JTextField();
        textFields.add(genreTextField);

        seriesLabel = new JLabel("Series: ");
        labels.add(seriesLabel);
        seriesTextField = new JTextField();
        textFields.add(seriesTextField);

        volumeNumberLabel = new JLabel("Volume: ");
        labels.add(volumeNumberLabel);
        volumeNumberTextField = new JTextField();
        textFields.add(volumeNumberTextField);

        editionNumberLabel = new JLabel("Edition: ");
        labels.add(editionNumberLabel);
        editionNumberTextField = new JTextField();
        textFields.add(editionNumberTextField);

        countryLabel = new JLabel("Country");
        labels.add(countryLabel);
        countryTextField = new JTextField();
        textFields.add(countryTextField);

        bookLanguageLabel = new JLabel("Language * ");
        labels.add(bookLanguageLabel);
        bookLanguageTextField = new JTextField();
        textFields.add(bookLanguageTextField);
        mandatoryTextFields.add(bookLanguageTextField);

        releasedYearLabel = new JLabel("Released year * : ");
        labels.add(releasedYearLabel);
        releasedYearTextField = new JTextField();
        textFields.add(releasedYearTextField);
        mandatoryTextFields.add(releasedYearTextField);

        imprintLabel = new JLabel("Imprint: ");
        labels.add(imprintLabel);
        imprintTextField = new JTextField();
        textFields.add(imprintTextField);

        publisherLabel = new JLabel("Publisher: ");
        labels.add(publisherLabel);
        publisherTextField = new JTextField();
        textFields.add(publisherTextField);

        isbnLabel = new JLabel("ISBN * : ");
        labels.add(isbnLabel);
        isbnTextField = new JTextField();
        textFields.add(isbnTextField);
        mandatoryTextFields.add(isbnTextField);

        barcodeLabel = new JLabel("Barcode: ");
        labels.add(barcodeLabel);
        barcodeTextField = new JTextField();
        textFields.add(barcodeTextField);

        accessionNumberLabel = new JLabel("Accession * : ");
        labels.add(accessionNumberLabel);
        accessionNumberTextField = new JTextField();
        textFields.add(accessionNumberTextField);
        mandatoryTextFields.add(accessionNumberTextField);

        locationLabel = new JLabel("Location: ");
        labels.add(locationLabel);
        locationTextField = new JTextField();
        textFields.add(locationTextField);

        notesLabel = new JLabel("Notes: ");
        labels.add(notesLabel);
        notesTextField = new JTextField();
        textFields.add(notesTextField);

        redLineBorder = new LineBorder(new Color(0xFF0000));
    }

    public void addWidgetsToPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0d;

        // this form will have 3 fields (label + text field) per row
        int col = 0; // x = 0
        int row = 0; // y = 0


        for (int i = 0; i < labels.size(); i++) { // doesn't matter if you iterate through labels or text fields, both lists are the same size
            JPanel panel = new JPanel(); // panel to hold the label and text field
            panel.setLayout(new BorderLayout()); // border layout because the center position lets the text field expand
            panel.add(BorderLayout.WEST, labels.get(i)); // add label to the left of its text field, not resizable
            panel.add(BorderLayout.CENTER, textFields.get(i)); // add text field to the right of its label, resizable
            gbc.gridy = row;
            gbc.gridx = col;
            this.add(panel, gbc);

            // because this form has 3 fields per row, when col reaches 3, go to the next row and reset col
            ++col;
            if ((col % 3) == 0){
                col = 0;
                ++row;
            }
        }
    }

    public void bindDocumentListenersToTextFields(){
        for (JTextField textField : textFields)
            textField.getDocument().addDocumentListener(this);
    }

    public void checkBookFieldsForValidity(){
        boolean isValid = true;
        for (JTextField textField : textFields){
            if (textField.getText().length() > 255){
                textField.setBorder(redLineBorder);
                isValid = false;
            }
            else{
                textField.setBorder(null);
            }
        }
        for (JTextField mandatoryTextField : mandatoryTextFields){
            if (mandatoryTextField.getText().isEmpty()){
                mandatoryTextField.setBorder(redLineBorder);
                isValid = false;
            }
            else{
                mandatoryTextField.setBorder(null);
            }
        }
        validEntityListener.entityIsValid(isValid);
    }

    public void start(){
        init();
        addWidgetsToPanel();
        bindDocumentListenersToTextFields();
        checkBookFieldsForValidity();
    }
    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public ArrayList<JTextField> getTextFields() {
        return textFields;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public JLabel getAuthorLabel() {
        return authorLabel;
    }

    public JTextField getAuthorTextField() {
        return authorTextField;
    }

    public JLabel getSubjectLabel() {
        return subjectLabel;
    }

    public JTextField getSubjectTextField() {
        return subjectTextField;
    }

    public JLabel getGenreLabel() {
        return genreLabel;
    }

    public JTextField getGenreTextField() {
        return genreTextField;
    }

    public JLabel getSeriesLabel() {
        return seriesLabel;
    }

    public JTextField getSeriesTextField() {
        return seriesTextField;
    }

    public JLabel getVolumeNumberLabel() {
        return volumeNumberLabel;
    }

    public JTextField getVolumeNumberTextField() {
        return volumeNumberTextField;
    }

    public JLabel getEditionNumberLabel() {
        return editionNumberLabel;
    }

    public JTextField getEditionNumberTextField() {
        return editionNumberTextField;
    }

    public JLabel getCountryLabel() {
        return countryLabel;
    }

    public JTextField getCountryTextField() {
        return countryTextField;
    }

    public JLabel getBookLanguageLabel() {
        return bookLanguageLabel;
    }

    public JTextField getBookLanguageTextField() {
        return bookLanguageTextField;
    }

    public JLabel getReleasedYearLabel() {
        return releasedYearLabel;
    }

    public JTextField getReleasedYearTextField() {
        return releasedYearTextField;
    }

    public JLabel getImprintLabel() {
        return imprintLabel;
    }

    public JTextField getImprintTextField() {
        return imprintTextField;
    }

    public JLabel getPublisherLabel() {
        return publisherLabel;
    }

    public JTextField getPublisherTextField() {
        return publisherTextField;
    }

    public JLabel getIsbnLabel() {
        return isbnLabel;
    }

    public JTextField getIsbnTextField() {
        return isbnTextField;
    }

    public JLabel getBarcodeLabel() {
        return barcodeLabel;
    }

    public JTextField getBarcodeTextField() {
        return barcodeTextField;
    }

    public JLabel getAccessionNumberLabel() {
        return accessionNumberLabel;
    }

    public JTextField getAccessionNumberTextField() {
        return accessionNumberTextField;
    }

    public JLabel getLocationLabel() {
        return locationLabel;
    }

    public JTextField getLocationTextField() {
        return locationTextField;
    }

    public JLabel getNotesLabel() {
        return notesLabel;
    }

    public JTextField getNotesTextField() {
        return notesTextField;
    }

    public void setValidEntityListener(ValidEntityListener validEntityListener) {
        this.validEntityListener = validEntityListener;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkBookFieldsForValidity();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkBookFieldsForValidity();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
