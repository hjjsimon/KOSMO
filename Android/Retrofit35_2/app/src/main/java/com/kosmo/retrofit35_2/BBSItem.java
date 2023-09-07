package com.kosmo.retrofit35_2;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BBSItem {
    private String no;
    private String title;
    private String content;
    private String postDate;
    private String id;
    private String name;
    private String commentCounts;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String comments;
}
