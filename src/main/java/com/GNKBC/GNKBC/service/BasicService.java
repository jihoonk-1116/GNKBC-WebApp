package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.StaticString;
import com.GNKBC.GNKBC.repository.StringRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Slf4j
public class BasicService {

    @Autowired
    StringRepository repo;

    public Model loadStaticString(Model model){
        model.addAttribute("stringMap",repo.getContentMap());
        return model;
    }

    public StaticString getContent(String key) {
        return repo.getContentMap().get(key);
    }


}
