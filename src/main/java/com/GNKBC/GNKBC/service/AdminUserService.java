package com.GNKBC.GNKBC.service;


import com.GNKBC.GNKBC.domain.Member;
import com.GNKBC.GNKBC.domain.Role;
import com.GNKBC.GNKBC.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
//data CRUD by Entity manager must always be conducted in a transaction
//from transaction's begin to commit
public class AdminUserService {

    int flag = 0;

    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public Member addNewAdmin(String email, String name){
        Member newAdmin = Member.createMember(name, email, Role.ADMIN);
        userRepository.save(newAdmin);
        return newAdmin;
    }

    @Transactional
    public Member addNewAdmin(Member newAdmin){
        userRepository.save(newAdmin);
        return newAdmin;
    }

    @Transactional
    public boolean processOAuthPostAdminLogin(String useremail) {

        log.info("adminservice == " + useremail);
        if(flag == 0 && useremail.equals("mynoja3@gmail.com")){
            flag ++;
            addNewAdmin(useremail, "Jihoon Kim");
        }

        Member checkAdmin = userRepository.findByEmail(useremail);

        if(checkAdmin != null && checkAdmin.getRole() == Role.ADMIN){
            return true;
        }
        else{
            if(checkAdmin != null){
                log.info(checkAdmin.toString());
            }
            return false;
        }


    }

    @Transactional
    public Member changeAdminName(String email, String changedName){
        Member requestMember = userRepository.findByEmail(email);
        requestMember.setName(changedName);
        return userRepository.save(requestMember);
    }

    @Transactional
    public Member detachAdminAuthority(String email){
        Member requestMember = userRepository.findByEmail(email);
        requestMember.setRole(Role.USER);
        return userRepository.save(requestMember);
    }

    public List<Member> getAllAdmin(){
        return userRepository.findAllAdmin();
    }

    public Member getAdmin(String email){
        return userRepository.findByEmail(email);
    }


}
