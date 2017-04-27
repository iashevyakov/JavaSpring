package ru.itis.dto;

/**
 * Created by Ivan on 09.04.2017.
 */
public class FirmDto {
    public FirmDto(String name) {

        this.name = name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    String name;

    public FirmDto(){}

}
