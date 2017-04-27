package ru.itis.converter;

import ru.itis.dto.DetailDto;
import ru.itis.model.Detail;

import java.util.LinkedList;
import java.util.List;


public class DetailDtoConverter {
    public static DetailDto convert(Detail detail){
        String name = detail.getName();
        String firmName = detail.getFirm().getName();
        String nodeName = detail.getNode().getName();
        return new DetailDto(name,firmName,nodeName);
    }
    public static List<DetailDto> convertList(List<Detail> details){
        List<DetailDto> detailDtos = new LinkedList<>();
        for (Detail detail: details){
            detailDtos.add(convert(detail));
        }
        return detailDtos;
    }
}
