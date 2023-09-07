package com.kosmo.intent27_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kosmo.intent27_2.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //전달받은 인텐트에 저장된 데이타 읽어서 텍스트뷰에 뿌리기
        Intent intent = getIntent();
        binding.textStartActivity.setText(String.format("아이디:%s,비번:%s",
                intent.getStringExtra("username"),
                intent.getStringExtra("password")
                ));

        binding.btnBack.setOnClickListener(v->finish());
    }
}