package com.GNKBC.GNKBC.controller;

import com.GNKBC.GNKBC.domain.StaticString;
import com.GNKBC.GNKBC.service.AdminService;
import com.GNKBC.GNKBC.service.BasicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired @Qualifier("StaticAdminService")
    private final AdminService adminService;

    @Autowired
    private BasicService basicService;

    @GetMapping("/")
    public String login(){
        return "/adminpage/loginPage";
    }

    @GetMapping("/home")
    public String updateForm(Model model){

        model = basicService.loadStaticString(model);

        return "/adminpage/homeAdmin";
    }

    @GetMapping("/getimage/{tag}")
    public String getImageWindow(@PathVariable String tag, Model model){
        //TODO: Image file upload service
        return "/adminpage/windows/getStringFromUser";
    }


    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("imageFiles") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        log.info(file.toString());

        if (!file.isEmpty()) {
            String fullPath = "/Users/chihoonkim/Desktop/GNKBC-WEBAPP/GNKBC-WebApp/src/main/resources/static/img/" + "carousel-1.jpg";
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }
        //how to flush the cache file to display the changed image right away
        return "/adminpage/homeAdmin";
    }

    @GetMapping("/getwindow/{tag}")
    public String getWindow(@PathVariable String tag, Model model){
        log.info("getWindow");
        model.addAttribute("tag", tag);
        model.addAttribute("content",adminService.getContent(tag).getContent());
        return "/adminpage/windows/getStringFromUser";
    }

    /**
     * take key(ex..introduction, header) and string data from user
     * @param content
     * @param tag
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/getwindow/{tag}")
    public String changeStaticString(@RequestParam("content") String content, @RequestParam("tag") String tag, RedirectAttributes redirectAttributes){

        log.info(content);
        log.info(tag);

        adminService.stringDataUpdate(tag, content);

        return "redirect:/admin/getwindow/{tag}";
    }
}
