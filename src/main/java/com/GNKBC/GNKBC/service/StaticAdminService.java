package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.repository.StaticAssetsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("StaticAdminService")
@RequiredArgsConstructor
@Slf4j
public class StaticAdminService implements AdminService{

    @Autowired @Qualifier("StaticAssetsRepository")
    private final StaticAssetsRepository staticAssetsRepository;

    @Override
    public void stringDataUpdate(String key, String userInput) {

        log.info("User request == " + userInput);

        staticAssetsRepository.updateString(key, userInput);
    }
}
