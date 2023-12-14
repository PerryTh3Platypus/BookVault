package com.github.perryth3platypus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "internal_locations")
public class InternalLocation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "location_id")
    private long locationId;

    @Column(name = "location_name", nullable = false, length = 50)
    private String locationName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
