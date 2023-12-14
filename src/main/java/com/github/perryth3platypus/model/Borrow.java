package com.github.perryth3platypus.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private long borrowId;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrow_date", columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime borrowDate;

    @Column(name = "due_date", columnDefinition = "DATETIME DEFAULT DATE_ADD(NOW(), INTERVAL 2 WEEK)")
    private LocalDateTime dueDate;

    @Column(name = "return_date", columnDefinition = "DATETIME")
    private LocalDateTime returnDate;

    private enum Status{
        BORROWED, RETURNED
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Borrow.Status status;

    @OneToOne
    @JoinColumn(name = "librarian_id")
    private Librarian checkedOutByLibrarianId;

}
