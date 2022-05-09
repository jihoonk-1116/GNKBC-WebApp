package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.StaticImage;
import com.GNKBC.GNKBC.domain.StaticString;
import com.GNKBC.GNKBC.repository.ImageRepository;
import com.GNKBC.GNKBC.repository.StringRepository;
import com.GNKBC.GNKBC.utils.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Qualifier("StaticAdminService")
@RequiredArgsConstructor
@Slf4j
public class StaticAdminService implements AdminService{

    @Autowired @Qualifier("StaticAssetsRepository")
    private final StringRepository stringRepository;
    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final FileStore fileStore;

    @Override
    public void stringDataUpdate(String key, String userInput) {

        log.info("User tag == " + key);
        log.info("User content == " + userInput);

        stringRepository.updateString(key, userInput);
    }

    @Override
    public StaticString getContent(String tag) {
        return stringRepository.getContentMap().get(tag);
    }

    @Override
    public Model uploadImages(String tag, List<MultipartFile> imageList, Model model) throws IOException {

        for (StaticImage staticImage : fileStore.storeFile(imageList)) {
            imageRepository.save(tag, staticImage.getStoreFileName());
            System.out.println(staticImage.getStoreFileName());
        }

        model.addAttribute("imgPathMap", imageRepository.getPathMap());

        return model;
    }

    @Override
    public Resource imageMapping(String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @Override
    public Model loadStaticData(Model model) {
        model.addAttribute("stringMap", stringRepository.getContentMap());
        model.addAttribute("imgPathMap",imageRepository.getPathMap());
        return model;
    }
}
