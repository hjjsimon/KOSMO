package com.kosmo.intent27_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kosmo.intent27_1.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView[] tvs = {binding.tv1,binding.tv2,binding.tv3,binding.tv4,binding.tv5,binding.tv6,binding.tv7,binding.tv8,binding.tv9};
        RatingBar[] rbs={binding.rb1,binding.rb2,binding.rb3,binding.rb4,binding.rb5,binding.rb6,binding.rb7,binding.rb8,binding.rb9};

        //getIntent()메소드로 전달된 Intent를 얻을 수 있다
        Intent intent=getIntent();
        //인텐트의 getXXXXExtra(키값)메소드로 데이타얻기
        int[] votes=intent.getIntArrayExtra("votes");
        String[] titles=intent.getStringArrayExtra("titles");
        for(int i=0;i < votes.length;i++){
            //영화제목 설정
            tvs[i].setText(titles[i]);
            //레이팅 설정
            rbs[i].setRating(votes[i]);
        }

        binding.btnBack.setOnClickListener(v->finish());
    }/////////////
}////////////
