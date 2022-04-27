package com.GNKBC.GNKBC.repository;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class StaticAssetsRepositoryTest {

//    @Test
//    void saveToJson() {
//        StaticAssetsRepository staticAssetsRepository = new StaticAssetsRepository();
//
//        staticAssetsRepository.saveToJson("key","content");
//
//    }

    @Test
    void loadFromJson() throws FileNotFoundException {
        StaticAssetsRepository staticAssetsRepository = new StaticAssetsRepository();

        staticAssetsRepository.loadFromJson();
        System.out.println(staticAssetsRepository.printAll().toString());
    }
}