package ru.itis.dto;

/**
 * Created by Ivan on 09.04.2017.
 */
public class DetailDto {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getName() {

        return name;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public DetailDto(String name, String firmName, String nodeName) {
        this.name = name;
        this.firmName = firmName;
        this.nodeName = nodeName;
    }

    String firmName;

    public DetailDto() {
    }

    String nodeName;

}
