package ru.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.converter.DetailDtoConverter;
import ru.itis.dto.DetailDto;
import ru.itis.model.Detail;
import ru.itis.service.DetailService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class DetailController {
    @Autowired
    DetailService detailService;
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getDetail(@PathVariable("id") int detailId,
                            @RequestParam(value = "action", defaultValue = "") String action) {
        if (action.equals("")) {
            System.out.println(detailId);
            Detail detail = detailService.findOne(detailId);
            ObjectMapper mapper = new ObjectMapper();
            String detailAsJson = null;
            try {
                detailAsJson = mapper.writeValueAsString(DetailDtoConverter.convert(detail));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            return detailAsJson;
        } else {
            return "Not Supported";
        }
    }
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @ResponseBody
    public String getDetails() {

        List<Detail> details = detailService.findAll();
        List<DetailDto> detailDtoList = new LinkedList<>();

        ObjectMapper mapper = new ObjectMapper();
        String detailAsJson = null;
        try {
            detailAsJson = mapper.writeValueAsString(DetailDtoConverter.convertList(details));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return detailAsJson;

    }
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    @ResponseBody
    public String addDetail(@RequestBody String detailValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Detail detail= objectMapper.readValue(detailValue, Detail.class);
            detail = detailService.save(detail);
            DetailDto detailDto = DetailDtoConverter.convert(detail);
            return objectMapper.writeValueAsString(detailDto);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @RequestMapping(value = "/details", method = RequestMethod.PUT)
    @ResponseBody
    public String updateDetail(@RequestBody String detailValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Detail detail = objectMapper.readValue(detailValue, Detail.class);
            detailService.update(detail);
            return objectMapper.writeValueAsString(detail);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @RequestMapping(value = "/details/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDetail(@PathVariable("id") int detailId,
                            @RequestParam(value = "action", defaultValue = "") String action) {
        System.out.println(detailId);
        detailService.delete(detailId);
    }



}
