package com.GNKBC.GNKBC.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class ImageRepository {
    private final ArrayList<String> paths = new ArrayList<>();

    public void save(String path){
        paths.add(path);
    }

    public ArrayList<String> getPathList(){
        return this.paths;
    }

}
