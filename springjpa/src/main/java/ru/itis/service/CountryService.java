package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.CountryRepository;
import ru.itis.models.Country;

import java.util.List;


@Service
@Transactional
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country save(Country country){
        return countryRepository.save(country);
    }

    public void delete(Integer id){
        countryRepository.delete(id);
    }
    public List<Country> findAll(){
        return (List<Country>) countryRepository.findAll();
    }
    public void update(Country country){
        Country updatedCountry = countryRepository.findOne(country.getId());
        updatedCountry.setCountryName(country.getCountryName());
        updatedCountry.setContinent(country.getContinent());
        updatedCountry.setPresidentName(country.getPresidentName());
        updatedCountry.setFederation(country.isFederation());
    }


}
