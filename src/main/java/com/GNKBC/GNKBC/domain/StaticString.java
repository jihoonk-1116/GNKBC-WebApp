package com.GNKBC.GNKBC.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class StaticString {

    private String tag;
    private String content;

    public StaticString(){

    }

    public StaticString(String tag, String content){
        this.tag = tag;
        this.content = content;
    }


//    private String author;
//    private LocalDateTime createAt;
}
