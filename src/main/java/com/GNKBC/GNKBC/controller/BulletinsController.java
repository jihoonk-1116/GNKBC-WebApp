package com.GNKBC.GNKBC.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/bulletins")
public class BulletinsController {

    @GetMapping("/noticePage")
    public String notice(){
        return "/bulletins/noticePage";
    }
    @GetMapping("/newsPage")
    public String churchNews(){
        return "/bulletins/churchNewsPage";
    }
    @GetMapping("/activityPage")
    public String churchActivity(){
        return "/bulletins/churchActivityPage";
    }

}
