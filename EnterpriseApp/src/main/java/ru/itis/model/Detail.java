package ru.itis.model;

import javax.persistence.*;

/**
 * Created by Ivan on 05.04.2017.
 */
@Entity
@Table(name = "details")
public class Detail {
    @ManyToOne
    @JoinColumn(name = "firm_id")
    Firm firm;
    @ManyToOne
    @JoinColumn(name = "node_id")
    Node node;

    @Column
    String name;

    public Detail() {
    }

    public void setFirm(Firm firm) {

        this.firm = firm;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Firm getFirm() {

        return firm;
    }

    public Node getNode() {
        return node;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

}
