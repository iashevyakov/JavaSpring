package ru.itis.model;

import javax.persistence.*;

@Entity
@Table(name="nodes")
public class Node {
    public Node() {
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
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


}
