package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.FirmRepository;
import ru.itis.model.Firm;

import java.util.List;


@Service
@Transactional
public class FirmService {

    @Autowired
    FirmRepository firmRepository;
    public Firm findOne(Integer id){
        return firmRepository.findOne(id);
    }
    public Firm save(Firm firm){
        return  firmRepository.save(firm);
    }
    public void update(Firm firm){
        Firm updatedFirm = firmRepository.findOne(firm.getId());
        updatedFirm.setName(firm.getName());
    }
    public void delete(Integer id){
        firmRepository.delete(id);
    }
    public List<Firm> findAll(){
        return (List<Firm>) firmRepository.findAll();
    }

}