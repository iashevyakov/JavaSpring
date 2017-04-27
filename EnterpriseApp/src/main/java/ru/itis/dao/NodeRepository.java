package ru.itis.dao;

import org.springframework.data.repository.CrudRepository;
import ru.itis.model.Node;

/**
 * Created by Ivan on 05.04.2017.
 */
public interface NodeRepository extends CrudRepository<Node,Integer> {
}
