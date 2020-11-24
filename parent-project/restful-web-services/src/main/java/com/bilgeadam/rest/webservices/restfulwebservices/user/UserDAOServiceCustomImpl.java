package com.bilgeadam.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOServiceCustomImpl implements UserDAOServiceCustom{

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDAOServiceCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> findUserByFirstLetter(String letter) {
        Query q = entityManager.createNativeQuery("SELECT u.* FROM User u WHERE u.Name like ?1 ");
        return q.setParameter(1, " "+letter).getResultList();
    }
}
