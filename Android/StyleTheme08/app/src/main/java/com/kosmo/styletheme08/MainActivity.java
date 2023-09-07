package com.kosmo.styletheme08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*
        -자바코드에서 리소스 접근
        안드로이드에서 제공하는 리소스:android.R.리소스종류시작한다
        내가 만든 리소스:R.리소스종류시작한다
        -xml에서 리소스 접근
         안드로이드에서 제공하는 리소스:@android로 시작하거나
                                   @리소스종류(안드로이드에서 미리 정의한 리소스)
         내가 만든 리소스:@리소스 종류로 시작
         */

        //자바코드로 테마 변경
        setTheme(R.style.MyTheme_Base);//내가 만든 스타일
        //setTheme(android.R.style.Theme_Holo_Light_NoActionBar);//안드로이드 제공 리소스

        setContentView(R.layout.activity_main);


        findViewById(R.id.btnDark).setOnClickListener(listener);
        findViewById(R.id.btnLight).setOnClickListener(listener);
        findViewById(R.id.btnSystem).setOnClickListener(listener);
    }
    private View.OnClickListener listener=view ->  {
        if(view.getId()==R.id.btnDark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else if(view.getId()==R.id.btnLight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else{
            //API 29버전부터 다크모드 지원
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                //API 레벨이 29이상일때
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
            }
        }
    };
}