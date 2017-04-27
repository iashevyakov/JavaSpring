package ru.itis.converter;

import ru.itis.dto.FirmDto;
import ru.itis.model.Firm;

import java.util.LinkedList;
import java.util.List;


public class FirmDtoConverter {
    public static FirmDto convert(Firm firm){
        return new FirmDto(firm.getName());
    }
    public static Firm convert(FirmDto firmDto){
        Firm firm = new Firm();
        firm.setName(firmDto.getName());
        return firm;
    }
    public static List<FirmDto> convertList(List<Firm> firmList){
        List<FirmDto> firmDtos = new LinkedList<>();
        for (Firm firm : firmList){
            firmDtos.add(convert(firm));
        }
        return firmDtos;
    }
}
