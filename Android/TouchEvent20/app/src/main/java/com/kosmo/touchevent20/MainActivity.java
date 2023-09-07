package com.kosmo.touchevent20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kosmo.touchevent20.databinding.ActivityMainBinding;
import com.kosmo.touchevent20.view.Dot;
import com.kosmo.touchevent20.view.MyTouchDrawing;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private MyTouchDrawing myTouchDrawing;
    private Vector<Dot> dots= new Vector<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        LinearLayout rootView = binding.getRoot();
        setContentView(rootView);

        //내가 만든 뷰를 자바 코드로 부착
        myTouchDrawing=new MyTouchDrawing(this,dots);
        ViewGroup.LayoutParams layoutParams=
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        myTouchDrawing.setLayoutParams(layoutParams);
        rootView.addView(myTouchDrawing);

        /*
         ※뷰(화면) 클릭시(터치시)에만 이벤트를 감지하려면  보통 onClickListener를 붙인다.
         단,뷰(화면) 클릭(터치)후 띄거나 혹은 클릭후 움직이거나(드래그) 할때 이벤트를 감지하기 위해서는
         onTouchListener부착
         고로 버튼에는 onClickListener를 부착하는게 유리하다.
         */
        binding.btnClick.setOnClickListener(v-> {
            Log.i("touch","버튼에서 클릭 이벤트 발생");
        });

        binding.btnTouch.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                Log.i("touch","버튼에서 터치(DOWN) 이벤트 발생");
            else if(motionEvent.getAction()==MotionEvent.ACTION_MOVE)
                Log.i("touch","버튼에서 터치(MOVE) 이벤트 발생");
            else if(motionEvent.getAction()==MotionEvent.ACTION_UP)
                Log.i("touch","버튼에서 터치(UP) 이벤트 발생");

            return true;//액티비티의 onTouchEvent콜백메소드 오버라이딩해서 false와 true차이 확인
        });

        binding.imageview.setOnTouchListener((v,e)->{
            Log.i("touch","이미지뷰에서 터치 이벤트 발생");
            return false;
        });
        //사인 지우기
        binding.btnClear.setOnClickListener(v->{
            //점 좌표 클릭어
            dots.clear();
            //onDraw()호출하기 위한 invalidate()호출
            myTouchDrawing.invalidate();
        });
    }///////////

    //컨텍스트 화면 터치시마다 자동으로 호출되는 콜백메소드
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("touch","액티비티에서 터치 이벤트 발생");
        return super.onTouchEvent(event);
    }
}