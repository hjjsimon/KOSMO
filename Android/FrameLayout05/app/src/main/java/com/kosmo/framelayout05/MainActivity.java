package com.kosmo.framelayout05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        Button btnMenu1 = findViewById(R.id.btnMenu1);
        Button btnMenu2 = findViewById(R.id.btnMenu2);
        Button btnMenu3 = findViewById(R.id.btnMenu3);

        FrameLayout container=findViewById(R.id.container);
        LinearLayoutCompat frame1 = findViewById(R.id.frame1);
        RelativeLayout frame2 = findViewById(R.id.frame2);
        RelativeLayout frame3 = findViewById(R.id.frame3);

        //방법1:각 화면을 구성하는 뷰의 visibilty속성을 invisibe 혹은 visible로 제어하기
        //방법2:프레임 레이아웃의 메소드 사용
        container.removeAllViews();//부착된 모든 뷰 제거
        container.addView(frame1);//첫번째 화면 부착


        btnMenu1.setOnClickListener(view -> {
            //방법1 -뷰의  visibility속성 이용 즉 android:visibility="invisible"사용시
            /*
            if(frame1.getVisibility()== View.INVISIBLE){
                frame1.setVisibility(View.VISIBLE);
            }
            frame2.setVisibility(View.INVISIBLE);
            frame3.setVisibility(View.INVISIBLE);*/
            //방법2
            container.removeAllViews();//부착된 모든 뷰 제거
            container.addView(frame1);//첫번째 화면 부착
        });
        btnMenu2.setOnClickListener(view -> {
            /*
            //방법1 -뷰의  visibility속성 이용 즉 android:visibility="invisible"사용시
            if(frame2.getVisibility()== View.INVISIBLE){
                frame2.setVisibility(View.VISIBLE);
            }
            frame1.setVisibility(View.INVISIBLE);
            frame3.setVisibility(View.INVISIBLE);*/
            //방법2
            container.removeAllViews();//부착된 모든 뷰 제거
            container.addView(frame2);//첫번째 화면 부착
        });
        btnMenu3.setOnClickListener(view -> {
            /*
            //방법1 -뷰의  visibility속성 이용 즉 android:visibility="invisible"사용시
            if(frame3.getVisibility()== View.INVISIBLE){
                frame3.setVisibility(View.VISIBLE);
            }
            frame2.setVisibility(View.INVISIBLE);
            frame1.setVisibility(View.INVISIBLE);*/
            //방법2
            container.removeAllViews();//부착된 모든 뷰 제거
            container.addView(frame3);//첫번째 화면 부착
        });




    }
}