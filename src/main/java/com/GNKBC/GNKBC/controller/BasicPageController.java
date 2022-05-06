package com.GNKBC.GNKBC.controller;


import com.GNKBC.GNKBC.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class BasicPageController {

    @Autowired
    BasicService basicService;

    @GetMapping
    public String home(Model model){

        model = basicService.loadStaticString(model);

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
