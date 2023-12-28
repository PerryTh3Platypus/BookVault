package com.github.perryth3platypus.gui.books;

import com.github.perryth3platypus.gui.books.add.AddBookMainPanel;
import com.github.perryth3platypus.gui.books.search.SearchBookMainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BooksTab extends JPanel implements ActionListener {
    private SearchBookMainPanel searchBookMainPanel;
    private AddBookMainPanel addBookMainPanel;
    private JPanel booksPanel; // this will hold the search and add panels for books, and switch between them

    private JPanel buttonsPanel;

    private JButton addBookButton;
    private JButton searchBookButton;

    public BooksTab(){
        this.setLayout(new BorderLayout());
        init();
        addWidgetsToPanel();
        bindActionListenerToButtons();
    }

    public void init(){
        searchBookMainPanel = new SearchBookMainPanel();
        addBookMainPanel = new AddBookMainPanel();
        booksPanel = new JPanel();
        booksPanel.setLayout(new CardLayout());
        booksPanel.add(searchBookMainPanel, "search");
        booksPanel.add(addBookMainPanel, "add");

        addBookButton = new JButton("New Book");
        searchBookButton = new JButton("Search Book");
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(searchBookButton);
        buttonsPanel.add(Box.createVerticalStrut(30)); // space between the 2 buttons
        buttonsPanel.add(addBookButton);
    }

    public void addWidgetsToPanel(){
        this.add(BorderLayout.CENTER, booksPanel);
        this.add(BorderLayout.WEST, buttonsPanel);
    }

    public void bindActionListenerToButtons(){
        addBookButton.addActionListener(this);
        searchBookButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBookButton){
            CardLayout cardLayout = (CardLayout) booksPanel.getLayout();
            cardLayout.show(booksPanel, "add");
        }

        if (e.getSource() == searchBookButton){
            CardLayout cardLayout = (CardLayout) booksPanel.getLayout();
            cardLayout.show(booksPanel, "search");
        }
    }
}
