package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.Member;
import com.GNKBC.GNKBC.domain.Role;
import com.GNKBC.GNKBC.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@SpringBootTest
@Transactional
class AdminUserServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminUserService adminUserService;

    @Test
    void addNewAdmin() {
        Member newAdmin = Member.createMember("test-admin", "admin-email", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin);

        Assertions.assertEquals(newAdmin, userRepository.findByEmail(newAdmin.getEmail()));

    }

    @Test
    void duplicatedAdmin() {
        Member newAdmin = Member.createMember("test-admin", "admin-email", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin);
        adminUserService.addNewAdmin(newAdmin);

        Assertions.assertEquals(newAdmin, userRepository.findByEmail(newAdmin.getEmail()));

    }

    @Test
    void changeAdminName() {
        Member newAdmin = Member.createMember("test-admin", "admin-email", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin);
        adminUserService.changeAdminName("admin-email", "change Test admin name");

        Member changedNameAdmin = userRepository.findByEmail("admin-email");

        org.assertj.core.api.Assertions.assertThat("change Test admin name").isEqualTo(changedNameAdmin.getName());

    }

    @Test
    void detachAdminAuthority() {

        Member newAdmin = Member.createMember("test-admin", "admin-email", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin);

        Member newAdmin2 = Member.createMember("test-admin2", "admin-email2", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin2);

        Member newAdmin3 = Member.createMember("test-admin3", "admin-email3", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin3);

        Assertions.assertEquals(3, adminUserService.getAllAdmin().size());

        adminUserService.detachAdminAuthority("admin-email");

        Assertions.assertEquals(2, adminUserService.getAllAdmin().size());


    }

    @Test
    void getAllAdmin() {
        Member newAdmin = Member.createMember("test-admin", "admin-email", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin);
        Member newAdmin2 = Member.createMember("test-admin", "admin-email2", Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin2);


        Assertions.assertEquals(2, adminUserService.getAllAdmin().size());
    }
}