package com.kosmo.fileio29_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.kosmo.fileio29_3.view.CustomView;

public class MainActivity extends AppCompatActivity {

    //이미지 리소스 아이디 저장 배열
    private int[] resIds={R.raw.pic1,R.raw.pic2,R.raw.pic3};
    //현재 인덱스 저장용
    private int currentIndex;
    //다이얼로그용
    private AlertDialog alertDialog;

    private CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = findViewById(R.id.customview);
        //커스텀뷰의 resId값 초기화
        customView.resId=resIds[0];
        //다이얼로그 생성
        alertDialog=new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_menu_camera)
                .setTitle("알림 메시지")
                .setPositiveButton("확인",null)
                .create();
    }

    public void previous(View v){
        if(currentIndex==0){
            alertDialog.setMessage("이전 이미지가 없어요");
            alertDialog.show();
            return;
        }
        //현재 인덱스 감소
        currentIndex--;
        customView.resId = resIds[currentIndex];
        //커스텀뷰의 invalidate()호출 -onDraw()메소드로가 호출됨
        customView.invalidate();
    }
    public void next(View v){
        if(currentIndex==resIds.length-1){
            alertDialog.setMessage("다음 이미지가 없어요");
            alertDialog.show();
            return;
        }
        //현재 인덱스 증가
        currentIndex++;
        customView.resId = resIds[currentIndex];
        //커스텀뷰의 invalidate()호출 -onDraw()메소드로가 호출됨
        customView.invalidate();
    }
}