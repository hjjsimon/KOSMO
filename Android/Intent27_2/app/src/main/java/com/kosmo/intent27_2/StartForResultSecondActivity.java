package com.kosmo.intent27_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kosmo.intent27_2.databinding.ActivityStartForResultSecondBinding;

public class StartForResultSecondActivity extends AppCompatActivity {

    private ActivityStartForResultSecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStartForResultSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        binding.textStartActivityForResult2.setText(String.format("아이디:%s,비번:%s",username,password));

        binding.btnBack.setOnClickListener(v->{
            intent.putExtra("success","한국 소프트웨어 인재개발원");
            setResult(RESULT_OK,intent);
            //액티비티 종료
            finish();
        });
    }
}