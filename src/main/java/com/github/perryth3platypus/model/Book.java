package com.github.perryth3platypus.model;

import jakarta.persistence.*;
import java.util.List;

import java.time.LocalDateTime;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "subject", length = 100)
    private String subject;

    @Column(name = "genre", length = 30)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @Column(name = "volume_number")
    private int volumeNumber;

    @Column(name = "edition_number")
    private int editionNumber;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "book_language", length = 50)
    private String bookLanguage;

    @Column(name = "released_year", nullable = false)
    private int releasedYear;

    @Column(name = "imprint", length = 100)
    private String imprint;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;

    @Column(name = "barcode", unique = true, length = 20)
    private String barcode;

    @Column(name = "accession_number", unique = true, length = 20)
    private String accessionNumber;

    @OneToOne
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

    @OneToOne
    @JoinColumn(name = "patron_id")
    private Patron currentHolder;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

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

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
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

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
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
