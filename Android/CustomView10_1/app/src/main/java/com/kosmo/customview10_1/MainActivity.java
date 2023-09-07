package com.kosmo.customview10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kosmo.customview10_1.view.MyCustomView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 XML파일로 뷰 전개(inflate)]
        //1.setContentView(R.layout으로 시작하는 리소스아이디)
        setContentView(R.layout.activity_main);
        //2.setContentView(View)
        //setContentView(new MyCustomView(this));
    }
}