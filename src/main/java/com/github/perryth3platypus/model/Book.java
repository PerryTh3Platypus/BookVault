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

    @Column(name = "imprint", length = 100)
    private String imprint;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "place_of_publication", length = 100)
    private String placeOfPublication;

    @Column(name = "book_language", length = 50)
    private String bookLanguage;

    @Column(name = "year_of_publication", nullable = false)
    private int yearOfPublication;

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

    @OneToMany
    private List<Author> authors;

}
