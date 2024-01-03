package com.github.perryth3platypus.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "series_id")
    private long seriesId;

    @Column(name = "series_name", nullable = false, length = 100)
    private String seriesName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
