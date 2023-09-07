package com.kosmo.kosmoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.kosmo.kosmoapp.databinding.ActivityIntroBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
http://ajaxloadingimages.net/
https://www.unscreen.com/  배경제거
gif 이미지를 ImageView에 로드 하려면
1. gradle파일에 implementation 'com.github.bumptech.glide:glide:4.10.0' 추가
https://github.com/bumptech/glide
2. 다음 코드 작성
ImageView loading=findViewById(R.id.loading);
Glide.with(this).load(R.drawable.ajax_loader 혹은 원격이미지 주소).into(loading);
 */
public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.i("com.kosmo.kosmoapp",getKeyHash(this));

        Glide.with(this).load(R.drawable.loading).into(binding.loading);
        //액션바 색상 변경-자바코드
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#253697")));

        //2초 지연후 화면전환
        //스레드 실행을 위한 ScheduledExecutorService객체 생성
        ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
        //스레드 정의
        Runnable runnable = () ->{
            Intent intent = new Intent(IntroActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);//매니페스트에 android:noHistory="true"설정과 같다
            startActivity(intent);
        };
        //3초후에 스레드 실행
        worker.schedule(runnable,3, TimeUnit.SECONDS);


    }
    //카카오 로그인관련 해쉬값 얻기위한 메소드
    public static String getKeyHash(final Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null)
                return null;

            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}