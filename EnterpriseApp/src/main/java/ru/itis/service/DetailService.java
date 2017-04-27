package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.DetailRepository;
import ru.itis.model.Detail;

import java.util.List;


@Service
@Transactional
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    public Detail findOne(Integer id){
        return detailRepository.findOne(id);

    }

    public Detail save(Detail detail){
        return  detailRepository.save(detail);
    }
    public void update(Detail detail){
        Detail updatedDetail = detailRepository.findOne(detail.getId());
        updatedDetail.setName(detail.getName());
        updatedDetail.setFirm(detail.getFirm());
        updatedDetail.setNode(detail.getNode());
    }
    public void delete(Integer id){
        detailRepository.delete(id);
    }
    public List<Detail> findAll(){
        return (List<Detail>) detailRepository.findAll();
    }

}