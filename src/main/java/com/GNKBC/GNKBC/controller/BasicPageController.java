package com.GNKBC.GNKBC.controller;


import com.GNKBC.GNKBC.repository.ImageRepository;
import com.GNKBC.GNKBC.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/")
public class BasicPageController {

    @Autowired
    BasicService basicService;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping
    public String home(Model model, HttpServletRequest req, @RequestHeader Map<String, String> headers){
        

        model = basicService.loadStaticString(model);
        model.addAttribute("imgPathMap", imageRepository.getPathMap());

        return "/basicpage/index";
    }



    @GetMapping("/intro")
    public String intro(Model model){

        model = basicService.loadStaticString(model);
        return "/basicpage/introPage";
    }

    @GetMapping("/contactus")
    public String contactus(Model model){
        model = basicService.loadStaticString(model);
        return "/basicpage/contactusPage";
    }

    @GetMapping("/videoWorship")
    public String vWorship(Model model){
        model = basicService.loadStaticString(model);
        return "/basicpage/videoWorship";
    }

    @GetMapping("/pastorIntroPage")
    public String pastorIntro(Model model){
        model = basicService.loadStaticString(model);
        return "/basicpage/pastorIntro";
    }

    @GetMapping("/historyPage")
    public String historyPage(Model model){
        model = basicService.loadStaticString(model);
        return "/basicpage/history";
    }

    @GetMapping("/schedulePage")
    public String schedulePage(Model model){
        model = basicService.loadStaticString(model);
        return "/basicpage/worshipSchedule";
    }




}
