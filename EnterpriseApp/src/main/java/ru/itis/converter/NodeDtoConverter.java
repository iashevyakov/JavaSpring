package ru.itis.converter;

import ru.itis.dto.NodeDto;
import ru.itis.model.Node;

import java.util.LinkedList;
import java.util.List;


public class NodeDtoConverter {
    public static NodeDto convert(Node node){
        return new NodeDto(node.getName());
    }
    public static Node convert(NodeDto nodeDto){
        Node node = new Node();
        node.setName(nodeDto.getName());
        return node;
    }
    public static List<NodeDto> convertList(List<Node> nodes){
        List<NodeDto> nodeDtos = new LinkedList<>();
        for (Node node: nodes){
            nodeDtos.add(convert(node));

        }
        return nodeDtos;
    }
}
