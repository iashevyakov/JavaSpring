package ru.itis.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Иван on 05.03.2017.
 */
@Entity
@Table(name = "countries")

public class Country {
    public Integer getId() {
        return id;
    }
    public Country(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    public void setFederation(boolean federation) {
        isFederation = federation;
    }

    public Country(String countryName, String continent, String presidentName, boolean isFederation) {
        this.countryName = countryName;
        this.continent = continent;
        this.presidentName = presidentName;
        this.isFederation = isFederation;

    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public boolean isFederation() {
        return isFederation;
    }

    @Column(name = "country_name")
    private String countryName;
    @Column(name = "continent")
    private String continent;
    @Column(name = "president_name")
    private String presidentName;

    public void setFirms(List<Firm> firms) {
        this.firms = firms;
    }

    @Column(name = "isfederation")
    private boolean isFederation;

    public List<Firm> getFirms() {
        return firms;
    }

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Firm> firms;

}
