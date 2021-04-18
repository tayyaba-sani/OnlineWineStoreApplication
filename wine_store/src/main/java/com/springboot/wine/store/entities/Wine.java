package com.springboot.wine.store.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries(value =
        {
                @NamedQuery(name = "Wine.findByYear", query = "select w from Wine w where w.year = ?1"),
                @NamedQuery(name = "Wine.findByCountry", query = "select w from Wine w where w.country = ?1"),
                @NamedQuery(name = "Wine.findByVarietal", query = "select w from Wine w where w.varietal = ?1")
        }
)
public class Wine extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;
    private String description;
    private String name;
    private int rating;
    private String region;
    private Float retailPrice;
    private String varietal;
    private int wineVersion;
    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Float retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public int getWineVersion() {
        return wineVersion;
    }

    public void setWineVersion(int wineVersion) {
        this.wineVersion = wineVersion;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
