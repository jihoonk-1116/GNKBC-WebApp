package com.GNKBC.GNKBC.repository;


import com.GNKBC.GNKBC.domain.StaticString;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

    @Value("${staticJson.dir}")
    private String fileDir;

    private static final HashMap<String, StaticString> staticStringStore = new HashMap<>();

    public HashMap<String, StaticString> getContentMap(){
        return staticStringStore;
    }

    public void updateString(String key, String userInput) {
        StaticString newStaticString = new StaticString(key,userInput);
        staticStringStore.put(newStaticString.getTag(), newStaticString);
    }
    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }
    @PostConstruct
    private void loadFromJson() throws IOException {
        InputStream input = new FileInputStream(fileDir);
        Gson gs = getGson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String json2 = readAllLines(reader);
        try{

            JsonArray jsonArray = (JsonArray) JsonParser.parseString(json2);

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
            OutputStream output = new FileOutputStream(fileDir);
            //OutputStream output = new FileOutputStream("staticString.json");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            //Writer writer = new FileWriter("src/main/resources/static/staticString.json");
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
