package com.kosmo.clockpicker11_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker datePicker = findViewById(R.id.datePicker);
        //API레벨이 26이상인 스마트폰만 실행됨
        //방법1:버전체크
        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            datePicker.setOnDateChangedListener(
                    (datePicker1, year,month, dayOfMonth) -> Log.i("com.kosmo.clockpicker",String.format("%s년%s월%s일",year,month+1,dayOfMonth))
            );
        }*/
        //방법2:모듈레벨의 build.gradle파일에서 minSdk 26으로 수정후 Sync Now
        datePicker.setOnDateChangedListener(
                (datePicker1, year,month, dayOfMonth) -> Log.i("com.kosmo.clockpicker",String.format("%s년%s월%s일",year,month+1,dayOfMonth))
        );
    }
}