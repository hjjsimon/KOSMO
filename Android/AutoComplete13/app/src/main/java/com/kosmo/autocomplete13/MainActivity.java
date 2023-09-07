package com.kosmo.autocomplete13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    //※android.R로 시작하는 리소스는 안드로이드 SDK설치 디렉토리\platforms\플랫폼버전\data\res
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        AutoCompleteTextView single = findViewById(R.id.auto_single);
        MultiAutoCompleteTextView multi= findViewById(R.id.auto_multi);

        //어댑터 객체 생성:위젯과 데이타를 연결 시켜주고 데이타가 표시되는 모양(레이아웃)까지 갖고 있는 객체
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.items));
        //어댑터와 위젯 연결
        single.setAdapter(adapter);
        //multi.setAdapter(adapter);//데이타가 바인딩이 안된다

        //자동 완성기능을 위한 MultiAutoCompleteTextView 설정
        MultiAutoCompleteTextView.CommaTokenizer tokenizer=
                new MultiAutoCompleteTextView.CommaTokenizer();
        //콤마 토큰 설정(반드시 설정해야 자동 완성 기능이 적용됨)
        multi.setTokenizer(tokenizer);
        multi.setAdapter(adapter);

    }
}