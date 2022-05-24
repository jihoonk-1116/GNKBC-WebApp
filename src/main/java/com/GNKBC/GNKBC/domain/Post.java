package com.GNKBC.GNKBC.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") //fk
    private Member member;

    @Lob
    private String content;

    @NotNull
    private String author;

    @NotNull
    private String title;

    private int count;
    private ContentType contentType;
    private LocalDateTime localDateTime;

    public static Post createPost(Member member, String content, String title){
        Post post = new Post();
        post.setMember(member);
        post.setContent(content);
        post.setAuthor(member.getName());
        post.setTitle(title);
        post.setLocalDateTime(LocalDateTime.now());
        return post;
    }
}
