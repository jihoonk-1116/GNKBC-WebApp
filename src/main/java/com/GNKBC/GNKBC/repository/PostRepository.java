package com.GNKBC.GNKBC.repository;

import com.GNKBC.GNKBC.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Post post){
        if(post.getId() == null){
            em.persist(post);
        }else{
            em.merge(post);
        }

    }

    public List<Post> findByAdminId(Long id){
        return em.createQuery("select p from Post p where p.member.id = :member_id ", Post.class)
                .setParameter("member_id", id)
                .getResultList();
    }

    public List<Post> findByName(String name){
        return em.createQuery("select p from Post p where p.author = :name ", Post.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public Post findById(Long postId){
        return em.find(Post.class, postId);
    }

    public List<Post> deleteById(Long postId){
        Post post = findById(postId);
        if(post != null){
            em.remove(post);
        }
        return findAll();
    }
}
