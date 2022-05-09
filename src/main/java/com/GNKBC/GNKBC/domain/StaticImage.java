package com.GNKBC.GNKBC.domain;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class StaticImage {

    private String uploadFileName;
    private String storeFileName;

    public StaticImage(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
