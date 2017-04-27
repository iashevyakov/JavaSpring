package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.NodeRepository;
import ru.itis.model.Node;

import java.util.List;


@Service
@Transactional
public class NodeService {

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    NodeRepository firmRepository;
    public Node findOne(Integer id){
        return nodeRepository.findOne(id);
    }

    public Node save(Node node){
        return  nodeRepository.save(node);
    }
    public void update(Node node){
        Node updatedNode = nodeRepository.findOne(node.getId());
        updatedNode.setName(node.getName());
    }
    public void delete(Integer id){
        nodeRepository.delete(id);
    }
    public List<Node> findAll(){
        return (List<Node>) nodeRepository.findAll();
    }

}