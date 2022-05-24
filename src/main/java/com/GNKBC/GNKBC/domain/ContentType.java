package com.GNKBC.GNKBC.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentType {

    NEWS("TYPE_NEWS","NEWS"),
    ACTIVITY("TYPE_ACTIVITY","ACTIVITY");

    private final String key;
    private final String title;
}

