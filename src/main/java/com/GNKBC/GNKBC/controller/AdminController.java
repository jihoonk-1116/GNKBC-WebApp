package com.GNKBC.GNKBC.controller;

import com.GNKBC.GNKBC.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired @Qualifier("StaticAdminService")
    private final AdminService adminService;

    @GetMapping("/")
    public String login(){
        System.out.println(this.adminService.getClass());
        return "login";
    }

    @GetMapping("/home")
    public String adminHome(){
        return "adminHome";
    }

    @PostMapping("/changestaticstring")
    public String changeStaticString(String inputStr){
        /**
         * TODO take key(ex..introduction, header) and string data from user
         */
        adminService.stringDataUpdate("key3", "but I have a good coffee");
        return "changeHeader";
    }

}
