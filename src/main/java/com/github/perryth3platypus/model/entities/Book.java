package com.github.perryth3platypus.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "subject")
    private String subject;

    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @Column(name = "volume_number")
    private String volumeNumber;

    @Column(name = "edition_number")
    private String editionNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "book_language")
    private String bookLanguage;

    @Column(name = "released_year", nullable = false)
    private String releasedYear;

    @Column(name = "imprint")
    private String imprint;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "accession_number", unique = true)
    private String accessionNumber;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private InternalLocation location;

    @Column(name = "entry_date", columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime entryDate;

    private enum Status{
        AVAILABLE, CHECKED_OUT, ON_HOLD, LOST, DAMAGED
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron currentHolder;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public HashMap<String, String> dumpAllAttributes(){
        // keys are the values in BookConstants.FIELDS
        HashMap<String, String> attributes = new HashMap<>();

        attributes.put("id", Long.toString(bookId));
        attributes.put("Title", title);
        attributes.put("Author(s)", author);
        attributes.put("Subject", subject);
        attributes.put("Genre", genre);
        attributes.put("Series", series.getSeriesName());
        // skip series description for now
        attributes.put("Volume", volumeNumber);
        attributes.put("Edition", editionNumber);
        attributes.put("Country", country);
        attributes.put("Language", bookLanguage);
        attributes.put("Release Year", releasedYear);
        attributes.put("Imprint", imprint);
        attributes.put("Publisher", publisher);
        attributes.put("ISBN", isbn);
        attributes.put("Barcode", barcode);
        attributes.put("Accession", accessionNumber);
        attributes.put("Location", location.getLocationName());
        // skip location description for now
        //attributes.put("Entry Date", entryDate.toString()); // LocalDate.toString() defaults to this format: yyyy-MM-dd
        attributes.put("Entry Date", "N/A for now");
        attributes.put("Status", "N/A for now");
        attributes.put("Current Holder", "N/A for now"); // patron will have some kind of ID
        attributes.put("Notes", notes);
        return attributes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getImprint() {
        return imprint;
    }

    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public InternalLocation getLocation() {
        return location;
    }

    public void setLocation(InternalLocation location) {
        this.location = location;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Patron getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(Patron currentHolder) {
        this.currentHolder = currentHolder;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
