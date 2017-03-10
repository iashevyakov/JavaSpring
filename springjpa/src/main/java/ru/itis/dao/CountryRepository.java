package ru.itis.dao;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.Country;


public interface CountryRepository extends CrudRepository<Country,Integer> {
}
