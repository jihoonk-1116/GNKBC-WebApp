package com.GNKBC.GNKBC.controller;


import com.GNKBC.GNKBC.domain.StaticImage;
import com.GNKBC.GNKBC.repository.ImageRepository;
import com.GNKBC.GNKBC.service.AdminService;
import com.GNKBC.GNKBC.service.BasicService;
import com.GNKBC.GNKBC.utils.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.Session;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired @Qualifier("StaticAdminService")
    private final AdminService adminService;

    @Autowired
    private final ImageRepository imageRepository;

    private final FileStore fileStore;
    ArrayList<String> paths = new ArrayList<>();
    List<StaticImage> imgList = new ArrayList<>();
    @Autowired
    private BasicService basicService;

    @GetMapping("/")
    public String login(){
        return "/adminpage/loginPage";
    }

    @GetMapping("/home")
    public String updateForm(Model model){

        model = basicService.loadStaticString(model);

        model.addAttribute("imgPaths", imageRepository.getPathList());

        return "/adminpage/homeAdmin";
    }

    @GetMapping("/getimage/{tag}")
    public String getImageWindow(@PathVariable String tag, Model model){
        //TODO: Image file upload service
        return "/adminpage/windows/getStringFromUser";
    }


    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("imageFiles") List<MultipartFile> imgFiles, RedirectAttributes redirectAttributes, Model model) throws IOException {

        //log.info(file.toString());

        imgList = fileStore.storeFile(imgFiles);

        log.info("After Store1");

        for (StaticImage staticImage : imgList) {
            imageRepository.save(staticImage.getStoreFileName());
            System.out.println(staticImage.getStoreFileName());
        }
        redirectAttributes.addAttribute("imgPaths", imageRepository.getPathList());



        log.info("After Store22");
        return "redirect:/admin/home";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
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
