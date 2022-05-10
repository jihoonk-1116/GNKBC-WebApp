package com.GNKBC.GNKBC.controller;


import com.GNKBC.GNKBC.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired @Qualifier("StaticAdminService")
    private final AdminService adminService;

    @GetMapping("")
    public String login(HttpServletRequest req){
        HttpSession session = req.getSession(false);

        if(session != null && session.getAttribute("GNKBCadmin") != null){
            if(session.getAttribute("GNKBCadmin").equals("gnkbcAdmin")){
                return "/adminpage/adminHome";
            }
        }

        return "/adminpage/loginPage";
    }

    @PostMapping("")
    public String adminLogin(@RequestParam("id") String id, @RequestParam("password") String pw,
                             HttpServletRequest req, Model model){


        if(adminService.adminLogin(id,pw) == false){
            model.addAttribute("loginFail", "아이디와 비밀번호를 확인해주세요.");
            return "/adminpage/loginPage";
        }

        HttpSession session = req.getSession();
        session.setAttribute("GNKBCadmin", id);

//        log.info(session.toString());

        return "redirect:/admin/home";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session = req.getSession();
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
    @GetMapping("/home")
    public String updateForm(Model model){

        model = adminService.loadStaticData(model);
        return "/adminpage/adminHome";
    }

    @GetMapping("/getimage/{tag}")
    public String getImageWindow(@PathVariable String tag, Model model){
        //TODO: Image file upload service
        return "/adminpage/windows/getStringFromUser";
    }


    @PostMapping("/uploadimage/{tag}")
    public String uploadImage(@PathVariable String tag, @RequestParam("imageFiles") List<MultipartFile> imgFiles, RedirectAttributes redirectAttributes, Model model) throws IOException {

        model = adminService.uploadImages(tag, imgFiles, model);

        return "redirect:/admin/home";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return adminService.imageMapping(filename);
    }

    @GetMapping("/getwindow/{tag}")
    public String getWindow(@PathVariable String tag, Model model){
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
