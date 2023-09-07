package com.kosmo.retrofit35_1;

//※전제 조건:반드시 REST API SERVER의 JSON데이타의 키와 Item(DTO계열)의 속성(필드)이 일치해야 한다(어노테이션 미 사용시)

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    @JsonProperty("userId")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int userId;//@JsonProperty("userId")는 JSON데이타의 키.private int userId를 다른 속성명으로 변경가능
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int id;
    private String title;
    private String body;
}
