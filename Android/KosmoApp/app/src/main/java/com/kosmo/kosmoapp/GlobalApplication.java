package com.kosmo.kosmoapp;

import android.app.Application;


import com.kakao.sdk.common.KakaoSdk;


public class GlobalApplication extends Application {
    private static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 네이티브 앱 키로 초기화
        KakaoSdk.init(this, "df5a3b705a1931b9b18bb988fe269f7f");


    }
}
