package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.example.demo.entity.ListValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ListValuesDAO {

    @Autowired
    private EntityManager entityManager;

    public ListValues getListValue(Long id, Long attr_type_def_id) {
        try {
            String sql = "Select urn from " + ListValues.class.getName() + " urn " //
                    + " where urn.List_value_id = :id and urn.Attr_type_def_id = :attr_type_def_id";

            Query query = this.entityManager.createQuery(sql, ListValues.class);
            query.setParameter("id", id);
            query.setParameter("attr_type_def_id", attr_type_def_id);
            return (ListValues) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<ListValues> getListValues(Long attr_type_def_id) {
        try {
            String sql = "Select urn from " + ListValues.class.getName() + " urn " //
                    + " where urn.Attr_type_def_id = :attr_type_def_id";

            Query query = this.entityManager.createQuery(sql, ListValues.class);
            query.setParameter("attr_type_def_id", attr_type_def_id);
            return (ArrayList) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}