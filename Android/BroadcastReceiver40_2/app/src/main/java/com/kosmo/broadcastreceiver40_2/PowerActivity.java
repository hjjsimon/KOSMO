package com.kosmo.broadcastreceiver40_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kosmo.broadcastreceiver40_2.databinding.ActivityPowerBinding;

public class PowerActivity extends AppCompatActivity {
    private ActivityPowerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPowerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        //안드로이드 OS에서 보낸 방송 내용에 따라 전원 상태를 텍스트뷰에 뿌리기
        binding.textView.setText(intent.getStringExtra("power_status"));
    }
}