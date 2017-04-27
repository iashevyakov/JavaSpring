package ru.itis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "firms")
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    public Firm(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Column
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Firm() {

    }

    //@OneToMany(fetch = FetchType.EAGER,mappedBy = "firm", cascade = CascadeType.ALL)
    //private List<Detail> details;
}
