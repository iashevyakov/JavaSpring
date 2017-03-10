package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.CountryRepository;
import ru.itis.dao.FirmRepository;
import ru.itis.models.Firm;

import java.util.List;


@Service
@Transactional
public class FirmService {

    @Autowired
    FirmRepository firmRepository;

    public Firm save(Firm firm){
        return  firmRepository.save(firm);
    }
    public void update(Firm firm){
        Firm updatedFirm = firmRepository.findOne(firm.getId());
        updatedFirm.setFirmName(firm.getFirmName());
        updatedFirm.setFoundation(firm.getFoundation());
        updatedFirm.setOwnerName(firm.getOwnerName());
        updatedFirm.setCountry(firm.getCountry());
    }
    public void delete(Integer id){
        firmRepository.delete(id);
    }
    public List<Firm> findAll(){
        return (List<Firm>) firmRepository.findAll();
    }

}
