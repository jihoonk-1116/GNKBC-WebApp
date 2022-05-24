package com.GNKBC.GNKBC.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();



    public static Member createMember(String name, String email, Role role){

        Member member = new Member();
        member.setEmail(email);
        member.setName(name);
        member.setRole(role);
        member.setPosts(new ArrayList<>());

        return member;
    }
}
