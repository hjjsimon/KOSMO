package com.kosmo.customview10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kosmo.customview10_2.databinding.ActivityMainBinding;
import com.kosmo.customview10_2.databinding.ToastLayoutBinding;

public class MainActivity extends AppCompatActivity {
    /*
    뷰바인딩
    https://developer.android.com/topic/libraries/view-binding?hl=ko#java
    findViewById대신 뷰결합 사용
    1.build.gradle에 viewBinding {
            enabled = true
        }  추가후 sync now
    2.앱 실행
    3.Project탭으로 변경후 app->build->generated->data_binding_~밑에 바딩딩 클래스가 생성됨 확인
    */
    private ActivityMainBinding binding;
    private ToastLayoutBinding toastLayoutBinding;

    private View toastRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //activity_main.xml을 기반으로 추상화한 클래스 얻기(클래스명 대문자로 시작하고 파스칼형식의 레이아웃파일명+Binding)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        toastLayoutBinding=ToastLayoutBinding.inflate(getLayoutInflater());
        //binding.getRoot()로 최상위 레이아웃(루트 뷰) 얻기
        View rootView = binding.getRoot();
        toastRootView = toastLayoutBinding.getRoot();
        //레이아웃 전개
        setContentView(rootView);
        //binding객체로 뷰에 직접 접근
        binding.btnShort.setOnClickListener(handler);
        binding.btnLong.setOnClickListener(handler);
        binding.btnCustom.setOnClickListener(handler);
    }
    private View.OnClickListener handler=view->{
        String message = binding.infoMessage.getText().toString();
        if(view.getId() == R.id.btnShort)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        else if(view.getId()==R.id.btnLong)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        else{
            //사용자 입력값을 토스트용 커스템뷰의 텍스트뷰에 설정
            toastLayoutBinding.textView.setText(message);
            Toast toast = new Toast(view.getContext());//토스트 객체 생성
            toast.setDuration(Toast.LENGTH_SHORT);//토스트 지속시간 설정
            toast.setView(toastRootView); //토스트의 화면 전개
            toast.show();//토스트 보이기

        }
    };
}