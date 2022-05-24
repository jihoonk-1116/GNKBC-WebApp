package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.Member;
import com.GNKBC.GNKBC.domain.Post;
import com.GNKBC.GNKBC.domain.Role;
import com.GNKBC.GNKBC.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
//data CRUD by Entity manager must always be conducted in a transaction
    //from transaction's begin to commit
class AdminPostServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    AdminPostService adminPostService;
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    UserRepository userRepository;

    @Test
    void findPostsByEmail() {
        Member member = Member.createMember("test", "testemail", Role.ADMIN);
        userRepository.save(member);

        Post post1 = Post.createPost(member, "test Content1","test title1" );
        Post post2 = Post.createPost(member, "test Content2","test title2" );
        Post post3 = Post.createPost(member, "test Content3","test title3" );
        adminPostService.savePost(post1);
        adminPostService.savePost(post2);
        adminPostService.savePost(post3);

        Assertions.assertEquals(
                3,
                adminPostService.findPostsByAdminId(member.getId()).size()
        );

    }

    @Test
    void updatePostContent() {

        Member member = Member.createMember("test", "testemail", Role.ADMIN);
        userRepository.save(member);

        Post post1 = Post.createPost(member, "test Content1","test title1" );
        adminPostService.savePost(post1);

        Post p = adminPostService.updatePostContent(post1.getId(), "Changed Content");

        Assertions.assertEquals("Changed Content"
                                ,p.getContent()
        );
    }

    @Test
    void getPostAfterDeleteMember(){
        Member member = Member.createMember("test", "testemail", Role.ADMIN);
        userRepository.save(member);

        Post post1 = Post.createPost(member, "test Content1","test title1" );
        adminPostService.savePost(post1);

        Post post2 = Post.createPost(member, "test Content2","test title2" );
        adminPostService.savePost(post2);

        Post post3 = Post.createPost(member, "test Content3","test title3" );
        adminPostService.savePost(post3);

        adminUserService.detachAdminAuthority(member.getEmail());

        Assertions.assertEquals(3, adminPostService.findAllPosts().size());
        Assertions.assertEquals(0, adminUserService.getAllAdmin().size());

    }

    @Test
    void deletePost(){
        Long id = 1L;
        List<Post> posts = adminPostService.deletePost(id);
        Post findPost = adminPostService.findPostById(id);
        Assertions.assertEquals(null, findPost);
    }

    @Test
    void addPostbyNewAdmin(){

        Member newAdmin = adminUserService.addNewAdmin("test-email", "test-name");
        Post post = Post.createPost(newAdmin, "Content by new admin", "title by new admin");
        Post post2 = Post.createPost(newAdmin, "Content22 by new admin", "title22 by new admin");
        adminPostService.savePost(post);
        adminPostService.savePost(post2);

        Assertions.assertEquals(2,
                                adminPostService.findPostsByAdminId(newAdmin.getId()).size()
                );
    }
}