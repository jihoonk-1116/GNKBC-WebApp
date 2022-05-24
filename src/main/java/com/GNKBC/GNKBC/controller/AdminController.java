package com.GNKBC.GNKBC.controller;


import com.GNKBC.GNKBC.domain.Member;
import com.GNKBC.GNKBC.domain.Post;
import com.GNKBC.GNKBC.domain.Role;
import com.GNKBC.GNKBC.service.AdminPostService;
import com.GNKBC.GNKBC.service.AdminService;
import com.GNKBC.GNKBC.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
import javax.websocket.Session;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Value("${admin.id}")
    private String adminId;

    @Autowired @Qualifier("StaticAdminService")
    private final AdminService adminService;

    @Autowired
    private final AdminPostService adminPostService;

    @Autowired
    private final AdminUserService adminUserService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest req){

        HttpSession session = req.getSession();

        if(session.getAttribute("admin-email") != null){
            return "redirect:/admin/home";
        }
        return "/adminpage/loginPage";
    }

    @GetMapping("/loginfail")
    public String loginFailPage(HttpServletRequest req){
        return "/adminpage/loginFail";
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
    public String home(HttpServletRequest req, Model model){
        model = adminService.loadStaticData(model);
        return "/adminpage/adminHome";
    }

    @GetMapping("/postwriter")
    public String postWriter(Model model){
        return "/adminpage/addPost";
    }

    @PostMapping("/postwriter")
    public String uploadPost(@RequestBody Post content, HttpServletRequest req){

        log.info(content.getAuthor());
        log.info(content.getTitle());

        HttpSession session = req.getSession();

        Member member = adminUserService.getAdmin(session.getAttribute("admin-email").toString());
        if(member.getRole() != Role.ADMIN){
            log.info("Not Authorized user");
        }

        Post newPost = Post.createPost(member,content.getContent(),content.getTitle());
        newPost.setLocalDateTime(LocalDateTime.now());
        adminPostService.savePost(newPost);

        return "redirect:/admin/postwriter";
    }

    @GetMapping("/allposts")
    public String showAllPost(Model model){
        List<Post> posts = adminPostService.findAllPosts();
        model.addAttribute("posts", posts);

        List<Member> admins = adminUserService.getAllAdmin();
        model.addAttribute("admins", admins);

        return "/adminpage/newsandactivities";
    }

    @PostMapping("/addadmin")
    public String addAdmin(@RequestParam("adminname") String name,
                           @RequestParam("adminemail") String email){

        Member newAdmin = Member.createMember(name, email, Role.ADMIN);
        adminUserService.addNewAdmin(newAdmin);

        return "redirect:/admin/allposts";
    }

    @PostMapping("/uploadimage/{tag}")
    public String uploadImage(@PathVariable String tag, @RequestParam("imageFiles") List<MultipartFile> imgFiles, HttpServletRequest req, Model model) throws IOException {
        model = adminService.uploadImages(tag, imgFiles, model);
        return "redirect:/admin/home";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource getImage(@PathVariable String filename) throws MalformedURLException {
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

        adminService.stringDataUpdate(tag, content);

        return "redirect:/admin/getwindow/{tag}";
    }


}
