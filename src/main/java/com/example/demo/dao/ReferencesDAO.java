package com.example.demo.dao;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.ListValues;
import com.example.demo.entity.Objects;
import com.example.demo.entity.References;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

@Repository
@Transactional
public class ReferencesDAO {

    @Autowired
    private EntityManager entityManager;

    public boolean createUserAccount(Objects user, Iterable<Attributes> attributes) {
        try {
            for (Attributes attr_id : attributes) {
                if (attr_id.getAttr_type_id() == 5)
                    entityManager.createNativeQuery("INSERT INTO ns_references (Attr_id, Object_id) VALUES " +
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

    public ArrayList<References> getReferences(Long userId) {
        try {
            String sql = "Select urn from " + References.class.getName() + " urn " //
                    + " where urn.Object_id = :userId";
            Query query = this.entityManager.createQuery(sql, References.class);
            query.setParameter("userId", userId);
            return (ArrayList<References>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean delReference(Long userId) {
        try {
            entityManager.createNativeQuery("DELETE FROM ns_references WHERE Object_id = :userId or Reference = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
