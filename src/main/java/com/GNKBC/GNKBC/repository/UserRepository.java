package com.GNKBC.GNKBC.repository;

import com.GNKBC.GNKBC.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

@Repository
@Slf4j
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        log.info(member.getEmail() + "   " + member.getName());
        return member;
    }

//    public User findOne(Long id){
//        return em.find(User.class, id);
//    }

    public Member findByEmail(String email){
        return null;
//        return em.createQuery("select m from Member m where m.email = :name", User.class)
//                .setParameter("email", email)
//                .getSingleResult();
    }
}