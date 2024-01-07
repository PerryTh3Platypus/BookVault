package com.github.perryth3platypus.model.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Event_name")
    private String eventName;

    @Column(name = "Event_description")
    private String eventDescription;


    @Column(name = "Event_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;


    // Constructor, getters, setters//
}
