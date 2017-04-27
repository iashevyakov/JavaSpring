package ru.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.converter.NodeDtoConverter;
import ru.itis.dto.NodeDto;
import ru.itis.model.Node;
import ru.itis.service.NodeService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Controller
public class NodeController {
    @Autowired
    NodeService nodeService;
    @RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFirm(@PathVariable("id") int nodeId,
                          @RequestParam(value = "action", defaultValue = "") String action) {
        if (action.equals("")) {
            System.out.println(nodeId);
            Node node = nodeService.findOne(nodeId);
            ObjectMapper mapper = new ObjectMapper();
            String firmAsJson = null;
            try {
                firmAsJson = mapper.writeValueAsString(NodeDtoConverter.convert(node));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            return firmAsJson;
        } else {
            return "Not Supported";
        }
    }
    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    @ResponseBody
    public String getNodes() {

        List<Node> nodes = nodeService.findAll();
        List<NodeDto> nodeDtoList = new LinkedList<>();
        for (Node node: nodes){
            nodeDtoList.add(NodeDtoConverter.convert(node));
        }

        ObjectMapper mapper = new ObjectMapper();
        String firmAsJson = null;
        try {
            firmAsJson = mapper.writeValueAsString(nodeDtoList);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return firmAsJson;

    }
    @RequestMapping(value = "/nodes", method = RequestMethod.POST)
    @ResponseBody
    public String addNode(@RequestBody String nodeValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Node node = objectMapper.readValue(nodeValue, Node.class);
            node.setId(0);
            System.out.println(node.getName());
            node = nodeService.save(node);
            NodeDto nodeDto = NodeDtoConverter.convert(node);
            return objectMapper.writeValueAsString(nodeDto);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @RequestMapping(value = "/nodes", method = RequestMethod.PUT)
    @ResponseBody
    public String updateDetail(@RequestBody String nodeValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Node node = objectMapper.readValue(nodeValue, Node.class);
            nodeService.update(node);
            return objectMapper.writeValueAsString(node);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @RequestMapping(value = "/nodes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDetail(@PathVariable("id") int nodeId,
                             @RequestParam(value = "action", defaultValue = "") String action) {
        System.out.println(nodeId);
        nodeService.delete(nodeId);
    }
}
