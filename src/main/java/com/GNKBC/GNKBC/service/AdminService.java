package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.StaticString;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface AdminService {
    void stringDataUpdate(String key, String userInput);
    StaticString getContent(String tag);
    Model uploadImages(String tag, List<MultipartFile> imageList, Model model) throws IOException;
    Resource imageMapping(String filename) throws MalformedURLException;
    Model loadStaticData(Model model);
    Boolean adminLogin(String id, String pw);

}
