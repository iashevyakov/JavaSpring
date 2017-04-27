package ru.itis.dao;

import org.springframework.data.repository.CrudRepository;
import ru.itis.model.Firm;


public interface FirmRepository extends CrudRepository<Firm,Integer> {

}