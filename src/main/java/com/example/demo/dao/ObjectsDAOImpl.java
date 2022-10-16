package com.example.demo.dao;

import com.example.demo.entity.Objects;
import org.springframework.data.repository.CrudRepository;

public interface ObjectsDAOImpl extends CrudRepository<Objects, Long> {
}
