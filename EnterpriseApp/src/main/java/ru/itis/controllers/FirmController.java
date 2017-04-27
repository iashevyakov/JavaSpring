package ru.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.converter.FirmDtoConverter;
import ru.itis.dto.FirmDto;
import ru.itis.model.Firm;
import ru.itis.service.FirmService;

import java.io.IOException;
import java.util.List;

@Controller
public class FirmController {
    @Autowired
    FirmService firmService;
    @RequestMapping(value = "/firms/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFirm(@PathVariable("id") int firmId,
                          @RequestParam(value = "action", defaultValue = "") String action) {
        if (action.equals("")) {
            System.out.println(firmId);
            Firm firm = firmService.findOne(firmId);
            ObjectMapper mapper = new ObjectMapper();
            String firmAsJson = null;
            try {
                firmAsJson = mapper.writeValueAsString(FirmDtoConverter.convert(firm));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            return firmAsJson;
        } else {
            return "Not Supported";
        }
    }
    @RequestMapping(value = "/firms", method = RequestMethod.GET)
    @ResponseBody
    public String getFirms() {

        List<Firm> firms = firmService.findAll();

            ObjectMapper mapper = new ObjectMapper();
            String firmAsJson = null;
            try {
                firmAsJson = mapper.writeValueAsString(FirmDtoConverter.convertList(firms));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            return firmAsJson;

    }
    @RequestMapping(value = "/firms", method = RequestMethod.POST)
    @ResponseBody
    public String addFirm(@RequestBody String firmValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Firm firm = objectMapper.readValue(firmValue, Firm.class);
            firm.setId(0);
            System.out.println(firm.getName());
            firm = firmService.save(firm);
            FirmDto firmDto = FirmDtoConverter.convert(firm);
            return objectMapper.writeValueAsString(firmDto);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @RequestMapping(value = "/firms", method = RequestMethod.PUT)
    @ResponseBody
    public String updateDetail(@RequestBody String firmValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Firm firm = objectMapper.readValue(firmValue, Firm.class);
            firmService.update(firm);
            return objectMapper.writeValueAsString(firm);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @RequestMapping(value = "/firms/{id}", method = RequestMethod.DELETE)

    public void deleteDetail(@PathVariable("id") int firmId,
                             @RequestParam(value = "action", defaultValue = "") String action) {
        firmService.delete(firmId);
        System.out.println(firmId);

    }

}
