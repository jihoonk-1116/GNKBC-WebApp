package com.GNKBC.GNKBC.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
    @NotNull
    private String content;

    @NotNull
    private String author;

    @NotNull
    private String title;
}
