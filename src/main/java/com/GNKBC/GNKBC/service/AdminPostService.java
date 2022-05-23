package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.Post;
import com.GNKBC.GNKBC.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminPostService {

    @Autowired
    private final PostRepository postRepository;

    @Transactional
    public void savePost(Post post){
        postRepository.save(post);
    }

    public List<Post> findPostsByAdminId(Long id){
        return postRepository.findByAdminId(id);
    }

    public List<Post> findPostsByName(String name){
        return postRepository.findByName(name);
    }

    public Post findPostById(Long postId){
        return postRepository.findById(postId);
    }

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    @Transactional
    public Post updatePostContent(Long postId, String newContent){

        Post savedPost = findPostById(postId);
        savedPost.setContent(newContent);
        savedPost.setLocalDateTime(LocalDateTime.now());

        postRepository.save(savedPost);

        return findPostById(postId);

    }

    @Transactional
    public List<Post> deletePost(Long postId){
        return postRepository.deleteById(postId);
    }


}
