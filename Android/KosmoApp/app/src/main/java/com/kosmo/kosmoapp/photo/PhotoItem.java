package com.kosmo.kosmoapp.photo;

import lombok.Getter;
import lombok.Setter;

//인자 생성자 추가시에는 기본생성자도 함께 추가하자
@Getter
@Setter
public class PhotoItem {
    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
