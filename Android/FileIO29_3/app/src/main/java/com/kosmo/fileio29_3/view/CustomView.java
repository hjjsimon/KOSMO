package com.kosmo.fileio29_3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;



public class CustomView extends View {
    //이미지 리소스 아이디-MainAtivity에서 설정 예정
    public int resId=-1;
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //onDraw()오버라이딩
    //onDraw가 호출될때마다 해당 이미지 그리기]

    @Override
    protected void onDraw(Canvas canvas) {
        /*
          BitmapFactory.decodeResource(Resources객체,int 리소스 아이디):res폴더 밑에 있는 이미지 파일 얻어올때
          BitmapFactory.decodeFile(String 이미지파일 경로):내부/및 외부 저장소에 저장된 이미지 파일 얻어올때
         */
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), resId);
        //원본 이미지 크기 설정
        Rect src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        //커스텀뷰의 가로 /세로크기에 맞추기
        Rect dest = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        //이미지 그리기
        canvas.drawBitmap(bitmap,src,dest,null);
        //메모리 누수를 막기위해 사용한 비트맵을 null처리하도록
        bitmap.recycle();

    }
}
