package com.kosmo.linearlayout02_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        //View view=View.inflate(this,R.layout.activity_main,null);
        //레이이웃용 XML파일 불 필요.아래코드 주석
        //setContentView(view);
        //1]LinearLayout객체 생성
        LinearLayoutCompat layoutCompat= new LinearLayoutCompat(this);
        //2]오리엔테이션 설정
        layoutCompat.setOrientation(LinearLayoutCompat.HORIZONTAL);
        //3]리니어레이아웃의 가로폭/세로폭 설정
        LinearLayoutCompat.LayoutParams layoutParams =
                new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT);

        //3-1]LayoutParams를 LinearLayout객체에 적용
        layoutCompat.setLayoutParams(layoutParams);
        //3-2]배경색 설정
        layoutCompat.setBackgroundColor(Color.rgb(255,255,0));
        //4]Button타입 선언
        Button btnOne,btnTwo;
        //5]Button타입 객체 생성
        btnOne=new Button(this);
        btnTwo=new Button(this);
        //6]버튼들의 속성 설정
        ViewGroup.LayoutParams btnLayoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        btnOne.setLayoutParams(btnLayoutParams);
        btnOne.setText("버튼1");
        btnOne.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        btnOne.setTextColor(Color.RED);
        //※버튼에 가로/세로폭 미 적용시
        //디폴트가 가로폭 ,세로폭은 WRAP_CONTENT임.
        btnTwo.setText("버튼2");
        btnTwo.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        //7]레이아웃에 버튼 부착:addView()
        layoutCompat.addView(btnOne);
        layoutCompat.addView(btnTwo);
        //8]버튼에 이벤트 부착하기
        btnOne.setOnClickListener(view -> Toast.makeText(view.getContext(), "첫번째 버튼입니다", Toast.LENGTH_SHORT).show());
        btnTwo.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "두번째 버튼입니다", Toast.LENGTH_SHORT).show());
        //9]레이아웃 전개(inflate)
        setContentView(layoutCompat);

    }
}