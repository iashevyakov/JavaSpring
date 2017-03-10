package ru.itis.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "firms")
public class Firm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firm_name")
    private String firmName;
    @Column(name = "foundation")
    private Date foundation;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getFirmName() {

        return firmName;
    }
    public Firm (){};

    public Date getFoundation() {
        return foundation;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Country getCountry() {
        return country;
    }

    @Column(name = "owner_name")

    private String ownerName;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Integer getId() {
        return id;
    }

    public Firm(String firmName, Date foundation, String ownerName, Country country) {

        this.firmName = firmName;
        this.foundation = foundation;
        this.ownerName = ownerName;
        this.country = country;
    }
}
