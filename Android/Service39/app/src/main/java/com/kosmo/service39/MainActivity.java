package com.kosmo.service39;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }////////////

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("com.kosmo.service","액티비티의 onResume() invoked");
        if(intent ==null)
            intent = new Intent(this, MyService.class);
        intent.putExtra("service","한국 소프트웨어 인재개발원");
        startService(intent);//서비스 시작
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("com.kosmo.service","액티비티의 onPause() invoked");
        stopService(intent);
    }
}