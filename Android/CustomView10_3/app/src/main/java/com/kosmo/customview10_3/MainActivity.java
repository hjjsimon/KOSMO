package com.kosmo.customview10_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kosmo.customview10_3.view.MyEditText;

public class MainActivity extends AppCompatActivity {

    //기본 에디트 텍스트상자로 문자열 길이 표시 구현용
    private int textLength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        TextView textView = findViewById(R.id.textview);
        LinearLayout linearLayout= findViewById(R.id.linearlayout);
        //기존 에디트 텍스트
        AppCompatEditText editText= findViewById(R.id.basicEditText);
        editText.addTextChangedListener(new TextWatcher() {

            //메소드 호출 순서:beforeTextChanged()->onTextChanged()->afterTextChanged()
            //한글자 입력전 혹은 삭제전마다 호출됨
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("com.kosmo.customview","기본 에디트 텍스트:beforeTextChanged()메소드 호출");
            }
            //한글자 입력시 혹은 삭제시마다 호출됨
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                Log.i("com.kosmo.customview","기본 에디트 텍스트:onTextChanged()메소드 호출");
                 /*
                text:에디트 텍스트에 입력된 문자열
                start:새로 추가되거나 삭제된 문자열의 시작 인덱스
                before:삭제된 문자열의 갯수
                count:새로 추가된 문자열의 갯수
                 */
                textLength+=count-before;
                textView.setText(String.valueOf(textLength));
                Log.i("com.kosmo.customview",String.format("text:%s,start:%s,before:%s,count:%s",text,start,before,count));
            }
            //한글자 입력후 혹은 삭제후마다 호출됨
            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("com.kosmo.customview","기본 에디트 텍스트:afterTextChanged()메소드 호출");
            }
        });

        //커스터마이징한 에디트 텍스트
        //1]내가 만든 EditText를 자바코드로 부착하는 경우
        /*
        MyEditText myEditText = new MyEditText(this);
        myEditText.setHint("커스터 마이징한 에디트 텍스트");
        myEditText.setHintTextColor(Color.RED);
        myEditText.setOnTextLengthListener(textLength -> textView.setText(String.valueOf(textLength)));
        //레이아웃(루트 뷰)에 부착 :addView(View view)
        linearLayout.addView(myEditText);*/

        MyEditText myEditText= findViewById(R.id.myEditText);
        myEditText.setOnTextLengthListener(textLength -> textView.setText(String.valueOf(textLength)));

    }
}