package com.kosmo.retrofit35_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


/*
    Retrofit(Android Async HTTP Clients)
    - https://square.github.io/retrofit/
    - REST API 통신을 위한 Square사의 OkHttp 라이브러리를 구현한 라이브러리
    - AsyncTask의 doInBackground로 구현하지 않고 Working Thread를 실행한다
      Callback 메소드을 통해 메인 스레드에서 직접 UI 변경 가능
    - http://instructure.github.io/blog/2013/12/09/volley-vs-retrofit/ - 성능 비교

    1. 의존성 추가
        //Retrofit 라이브러리
        implementation 'com.squareup.retrofit2:retrofit:2.9.0'
        //jackson converter 사용시
        implementation 'com.squareup.retrofit2:converter-jackson:2.9.0'
    2. 메니페스트(Manifests) 파일에 인터넷 권한(퍼미션) 추가
        <uses-permission android:name="android.permission.INTERNET"/>
    3. REST API 서버로 부터 받아올 JSON데이터를 변환하여 매핑할 Item(DTO)클래스 정의
    4. Retrofit을 사용하기 위한 API 인터페이스 생성
    5. Interface를 사용할 Retrofit 인스턴스  생성
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}