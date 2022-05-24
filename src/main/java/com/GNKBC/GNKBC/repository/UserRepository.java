package com.GNKBC.GNKBC.repository;

import com.GNKBC.GNKBC.domain.Member;
import com.GNKBC.GNKBC.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member findById(Long id){
        try{
            return em.find(Member.class, id);
        }
        catch(Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public Member findByEmail(String email){

        try{
            return em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }
        catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public List<Member> findAllAdmin(){
        return em.createQuery("select m from Member m where m.role = :role",Member.class)
                .setParameter("role", Role.ADMIN)
                .getResultList();
    }

    public List<Member> findAllUser(){
        return em.createQuery("select m from Member m where m.role = :role",Member.class)
                .setParameter("role", Role.USER)
                .getResultList();
    }
}