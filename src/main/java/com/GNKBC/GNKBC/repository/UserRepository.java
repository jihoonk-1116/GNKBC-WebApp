package com.GNKBC.GNKBC.repository;

import com.GNKBC.GNKBC.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

@Repository
@Slf4j
public class UserRepository {

    private final HashMap<Long, User> store = new HashMap<>();
//    @PersistenceContext
//    private EntityManager em;

    public User save(User user){
//        em.persist(user);
        log.info(user.getEmail() + "   " + user.getName());
        store.put(user.getId(), user);
        return user;
    }

//    public User findOne(Long id){
//        return em.find(User.class, id);
//    }

    public User findByEmail(String email){
        return null;
//        return em.createQuery("select m from Member m where m.email = :name", User.class)
//                .setParameter("email", email)
//                .getSingleResult();
    }
}