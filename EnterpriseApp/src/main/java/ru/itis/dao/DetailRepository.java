package ru.itis.dao;

import org.springframework.data.repository.CrudRepository;
import ru.itis.model.Detail;

/**
 * Created by Ivan on 05.04.2017.
 */
public interface DetailRepository extends CrudRepository<Detail,Integer> {
}
