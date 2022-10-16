package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.Objects;
import com.example.demo.entity.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;

@Repository
@Transactional
public class ParamsDAO {

    @Autowired
    private EntityManager entityManager;

    public boolean createUserAccount(Objects user, Iterable<Attributes> attributes) {
        try {
            for (Attributes attr_id : attributes) {
                if (attr_id.getAttr_type_id() != 5)
                    entityManager.createNativeQuery("INSERT INTO ns_params (Attr_id, Object_id) VALUES " +
                                    "(:attr_id, :idUser)")
                            .setParameter("idUser", user.getObject_id())
                            .setParameter("attr_id", attr_id)
                            .executeUpdate();
            }
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Params getValue(Long userId, Long attrId) {
        try {
            String sql = "Select up from " + Params.class.getName() + " up " //
                    + " where up.Object_id = :userId and up.Attr_id = :attrId";
            Query query = this.entityManager.createQuery(sql, Params.class);
            query.setParameter("userId", userId);
            query.setParameter("attrId", attrId);
            return (Params) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Params> getValues(Long userId) {
        try {
            String sql = "Select up from " + Params.class.getName() + " up " //
                    + " where up.Object_id = :userId";
            Query query = this.entityManager.createQuery(sql, Params.class);
            query.setParameter("userId", userId);
            return (ArrayList<Params>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean delValues(Long userId) {
        try {
            entityManager.createNativeQuery("DELETE FROM ns_params WHERE Object_id = ?")
                    .setParameter(1, userId)
                    .executeUpdate();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}