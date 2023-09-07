package com.kosmo.intent27_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.kosmo.intent27_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //투표수를 저장할 배열
    private int[] votes=new int[9];
    //영화제목 저장 배열
    private String[] titles={"조커","보통의 연예","제미니","퍼펙트맨","소피와 드래곤","장사리","세계를 찾아서","벌새","판소리 복서"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //ImageView객체를 저장할 배열
        ImageView[] images={binding.iv1,binding.iv2,binding.iv3,binding.iv4,binding.iv5,binding.iv6,binding.iv7,binding.iv8,binding.iv9};
        //이미지뷰에 리스너 부착
        for(int i=0;i<images.length;i++){
            final int index =i;
            images[i].setOnClickListener(v->{
                //투표수 증가
                votes[index]++;
                Log.i("com.kosmo.intent",String.format("%s : %s",titles[index],votes[index]));
            });
        }
        binding.btnResult.setOnClickListener(v->{
            //명시적 인텐트 생성-ResultActivity지정]
            //new Intent(Context,전환할 액티비티.class)
            Intent intent = new Intent(v.getContext(), ResultActivity.class);
            //전환할 액티비티로 putExtra("키값",값)메소드로 데이타 전달]
            //제목과 투표수 전달]
            intent.putExtra("titles",titles);
            intent.putExtra("votes",votes);
            //화면전환
            startActivity(intent);
        });

    }/////////////////onCreate

    @Override
    protected void onResume() {
        super.onResume();
        //투표수 데이타 초기화
        for(int i=0;i<votes.length;i++) votes[i]=0;
    }
}