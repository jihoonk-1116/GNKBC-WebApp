package com.GNKBC.GNKBC.repository;


import com.GNKBC.GNKBC.domain.StaticString;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Slf4j
@Repository
@Qualifier("StaticAssetsRepository")
public class StringRepository {

    private static final HashMap<String, StaticString> staticStringStore = new HashMap<>();

    public HashMap<String, StaticString> getContentMap(){
        return staticStringStore;
    }

    public void updateString(String key, String userInput) {
        StaticString newStaticString = new StaticString(key,userInput);
        staticStringStore.put(newStaticString.getTag(), newStaticString);
    }

    @PostConstruct
    private void loadFromJson() throws FileNotFoundException {

        Gson gs = getGson();
        Reader reader = new FileReader("staticString.json");

        try{

            String jsonData = readFileAsString("staticString.json");

            JsonArray jsonArray = (JsonArray) JsonParser.parseString(jsonData);

            for (JsonElement jsonElement : jsonArray) {

                StaticString staticString = gs.fromJson(jsonElement, StaticString.class);
                log.info(staticString.toString());
                staticStringStore.put(staticString.getTag(), staticString);

            }

        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    @PreDestroy
    private void saveToJson() {
        /**
         * TODO - Write static string data in staticString.json as json data format
         * use GSON
         */
        try {
            Writer writer = new FileWriter("staticString.json");
            Gson gs = getGson();
            gs.toJson(staticStringStore.values(), writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Gson getGson() {
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        return gb.create();
    }

    private static String readFileAsString(String file)throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
