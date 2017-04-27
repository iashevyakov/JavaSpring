package ru.itis.dto;

/**
 * Created by Ivan on 09.04.2017.
 */
public class NodeDto {
    public NodeDto(String name) {
        this.name = name;
    }

    String name;
    public NodeDto(){};

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
