package com.GNKBC.GNKBC.repository;

import com.GNKBC.GNKBC.utils.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ImageRepository {

    @Autowired
    private final FileStore fileStore;

    private final HashMap<String, ArrayList<String>> pathMap;
    private int carouselCount = 2;
    private int activityCount = 5;


    public void save(String tag, String path){

        if(!pathMap.containsKey(tag))
            pathMap.put(tag, new ArrayList<>());

        while(pathMap.get(tag).size() > carouselCount ||
                pathMap.get(tag).size() > activityCount){

            String fullPath = fileStore.getFullPath(pathMap.get(tag).get(0));
            File removedFile = new File(fullPath);
            log.info("Delete " + fullPath);
            removedFile.delete();
            pathMap.get(tag).remove(0);
        }

        pathMap.get(tag).add(path);
    }

    public HashMap<String, ArrayList<String>> getPathMap(){
        return this.pathMap;
    }

    public void setCarouselCount(int carouselCount) {
        this.carouselCount = carouselCount;
    }
    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }
}
