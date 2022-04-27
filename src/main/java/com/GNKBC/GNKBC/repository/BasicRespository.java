package com.GNKBC.GNKBC.repository;

import com.GNKBC.GNKBC.domain.StaticString;

import java.io.FileNotFoundException;

public interface BasicRespository {

    //To modify data in the database
    void saveToJson(String key, StaticString userInput);

    //When a client request replace or change data
    void updateString(String key, String userInput);

    //When this app is initiated, load data from database
    void loadFromJson() throws FileNotFoundException;

    StaticString getContent(String key);
}
