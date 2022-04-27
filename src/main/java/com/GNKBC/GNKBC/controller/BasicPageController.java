package com.GNKBC.GNKBC.controller;


import com.GNKBC.GNKBC.domain.StaticString;
import com.GNKBC.GNKBC.repository.BasicRespository;
import com.GNKBC.GNKBC.repository.StaticAssetsRepository;
import com.GNKBC.GNKBC.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@Slf4j
@Controller
@RequestMapping("/")
public class BasicPageController {

    @Autowired @Qualifier("StaticAssetsRepository")
    private BasicRespository basicRespository;

    @GetMapping
    public String home(Model model){
        StaticString staticString = basicRespository.getContent("key3");
        log.info(staticString.toString());
        model.addAttribute("stringData", staticString);
        return "/basicpage/index";
    }

    @GetMapping("/intro")
    public String intro(Model model){
        model.addAttribute("test", "intro Page");
        return "/basicpage/introPage";
    }

    @GetMapping("/contactus")
    public String contactus(Model model){
        model.addAttribute("test", "message from controller!");
        return "/basicpage/contactusPage";
    }

    @GetMapping("/videoWorship")
    public String vWorship(Model model){
        model.addAttribute("test", "message from controller!");
        return "/basicpage/videoWorship";
    }


}
