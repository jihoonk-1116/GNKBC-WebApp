package com.GNKBC.GNKBC.service;

import com.GNKBC.GNKBC.domain.StaticString;

public interface AdminService {
    void stringDataUpdate(String key, String userInput);
    StaticString getContent(String tag);

}
