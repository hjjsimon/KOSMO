package com.kosmo.touchevent20.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Vector;


//1]View클래스 상속
public class MyTouchDrawing extends View {

    //붓 준비
    private Paint paint;
    //사용자가 터치한 지점의 좌표를 저장할 컬렉션
    private Vector<Dot> dots;

    //자바코드용 생성자
    public MyTouchDrawing(Context context,Vector<Dot> dots) {
        super(context);
        this.dots = dots;
        //붓 생성
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //붓 색상 설정
        paint.setColor(Color.RED);
        //붓 두께 설정
        paint.setStrokeWidth(20);
    }
    //2-1]XML용 생성자
    public MyTouchDrawing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dots = new Vector<>();
        //붓 생성
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //붓 색상 설정
        paint.setColor(Color.RED);
        //붓 두께 설정
        paint.setStrokeWidth(20);
    }

    //그림을 그리기 위해서 onDraw()메소드 오버라이딩


    @Override
    protected void onDraw(Canvas canvas) {
        //캔바스 배경색 설정
        canvas.drawColor(Color.argb(100,125,125,125));
        //벡터에 저장된 점의 좌표로 선을 그리자]
        //점이 n개인 경우 n-1개의 선이 그려진다.
        for(int i=0;i < dots.size()-1;i++){
            if(dots.get(i).isDrawing())
                canvas.drawLine(
                        dots.get(i).getxPos(),
                        dots.get(i).getyPos(),
                        dots.get(i+1).getxPos(),
                        dots.get(i+1).getyPos(),
                        paint
                        );
        }
    }

    //콜백 메소드:MyTouchDrawing이라는 내가 만든 뷰 영역을
    //           터치했을때
    //           자동으로 호출되는 콜백 메소드(좌표 저장)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            //최초 터치-점의 좌표만 저장
            dots.add(new Dot(x,y,false));
            Log.i("touch",String.format("[DOWN]X좌표:%s,Y좌표:%s",x,y));
        }
        else if(event.getAction()==MotionEvent.ACTION_MOVE){
            //터치후 손가락 이동-점의 좌표 저장하고 그리기 위해 true]
            dots.add(new Dot(x,y,true));
            //invalidate()메소드 호출로 onDraw()호출해서 그린다.
            invalidate();
            Log.i("touch",String.format("[MOVE]X좌표:%s,Y좌표:%s",x,y));
        }
        else if(event.getAction()==MotionEvent.ACTION_MOVE){
            //그린후 띄었다가 다시 그릴때 띈지점과 다시 그린 지점 연결 막기 즉 isDrawing을 false
            dots.add(new Dot(x,y,false));
            Log.i("touch",String.format("[UP]X좌표:%s,Y좌표:%s",x,y));
        }
        return true;
    }
}
