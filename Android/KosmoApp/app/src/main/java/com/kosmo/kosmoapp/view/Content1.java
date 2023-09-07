package com.kosmo.kosmoapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.kosmo.kosmoapp.R;
import com.kosmo.kosmoapp.databinding.FragmentContent1Binding;

import java.net.URLEncoder;


public class Content1 extends Fragment {
    private FragmentContent1Binding binding;
    //아이디 비번 저장용



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContent1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        WebSettings settings=binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);//필수 설정
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        //binding.webView.loadUrl("http://192.168.0.17:9090/springapp/");//GET요청
        //post요청 - 안드로이드 앱에서 로그인후 들어오니까 웹뷰 로드시 바로 로그인 처리하기
        //SharedPreference에 의해 저장된 파일에서 아이디와 비번 읽어서 스프링 서버로 POST전송
        SharedPreferences preferences = getContext().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        String username=preferences.getString("username",null);
        String password=preferences.getString("password",null);
        try {
            String params = "id=" + URLEncoder.encode(username, "UTF-8") + "&pwd=" + URLEncoder.encode(password, "UTF-8");
            //웹뷰로 POST요청시
            binding.webView.postUrl("http://192.168.0.13:9090/onememo/auth/LoginProcess.do",params.getBytes());
            //binding.webView.loadUrl("http://192.168.0.10:8080/springapp/");//GET요청
        }catch(Exception e){e.printStackTrace();}

        return view;
    }
}