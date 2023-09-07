package com.kosmo.recyclerview32_1;
/*Lombok 설치
1.File-Settings-Plugins-lombok으로 검색후 설치  (2021년 말까지) 현 시점에서는 안됨
안드로이드 스튜디오가 버전이 업 되면서 아래 처럼
go to https://plugins.jetbrains.com/plugin/6317-lombok/versions
download .zip with the latest version (lombok-plugin-0.34.1-2019.1)
unpack it to ~/android-studio/plugins (use your path to Android Studio(C:\Program Files\Android\Android Studio))
restart IDE

압축푼 디렉토리 lombok-plugin-0.34.1-2019.1\lombok-plugin를 복사해서
안드로이드가 설치된 C:\Program Files\Android\Android Studio\plugins 디렉토리에 그대로 붙여 넣는다

2.app레벨 gradle파일에
 implementation 'org.projectlombok:lombok:1.18.28'
 annotationProcessor 'org.projectlombok:lombok:1.18.28' 추가후 Sync Project*/


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    private String itemName;
    private String itemDept;
    private String itemDate;
    private int itemPictureResId;

}
