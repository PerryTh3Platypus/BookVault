package com.github.perryth3platypus.model.entities;

import com.github.perryth3platypus.interfaces.EntityChangeListener;
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
    private int bookId;

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

    @Transient
    private ArrayList<EntityChangeListener> observers = new ArrayList<>();

    private void notifyObservers(EntityChangeListener.EVENT event){
        if (event == EntityChangeListener.EVENT.UPDATE){
            for (EntityChangeListener observer : observers){
                observer.entityChangeOccurred(this, EntityChangeListener.EVENT.UPDATE);
            }
        }
        if (event == EntityChangeListener.EVENT.DELETE)
            System.out.println("do something on book delete");
    }

    public void subscribeAsObserver(EntityChangeListener observer){
        observers.add(observer);
    }

    public HashMap<String, String> dumpAllAttributes(){
        // keys are the values in BookConstants.FIELDS
        HashMap<String, String> attributes = new HashMap<>();
        //this will cause a bug, when you order numbers in the results table by string

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

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getImprint() {
        return imprint;
    }

    public void setImprint(String imprint) {
        this.imprint = imprint;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public InternalLocation getLocation() {
        return location;
    }

    public void setLocation(InternalLocation location) {
        this.location = location;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public Patron getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(Patron currentHolder) {
        this.currentHolder = currentHolder;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
        notifyObservers(EntityChangeListener.EVENT.UPDATE);
    }
}
