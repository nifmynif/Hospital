package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.example.demo.entity.Objects;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

@Repository
@Transactional
public class ObjectDAO {

    @Autowired
    private EntityManager entityManager;

    public Objects findUserAccount(String userName) {
        try {
            String sql = "Select u from " + Objects.class.getName() + " u " //
                    + " Where u.Name = :userName and u.Object_type_id=2";

            Query query = entityManager.createQuery(sql, Objects.class);
            query.setParameter("userName", userName);

            return (Objects) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Objects findById(Long id) {
        try {
            String sql = "Select u from " + Objects.class.getName() + " u " //
                    + " Where u.Object_id = :id";

            Query query = entityManager.createQuery(sql, Objects.class);
            query.setParameter("id", id);

            return (Objects) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean createUserAccount(String userName) {
        try {
            if (findUserAccount(userName) != null)
                throw new SQLIntegrityConstraintViolationException();
            entityManager.createNativeQuery("INSERT INTO ns_objects (Name, Object_type_id, Parent_id) VALUES (?, 2, 4)")
                    .setParameter(1, userName)
                    .executeUpdate();
            return true;
        } catch (NoResultException | SQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    public ArrayList<Objects> findByObjectTypeId(Long objectTypeId) {
        try {
            String sql = "Select u from " + Objects.class.getName() + " u " //
                    + " Where u.Object_type_id= :objectTypeId";

            Query query = entityManager.createQuery(sql, Objects.class);
            query.setParameter("objectTypeId", objectTypeId);

            return (ArrayList) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}