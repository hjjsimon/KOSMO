package com.kosmo.intent27_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kosmo.intent27_2.databinding.ActivityStartForResultFirstBinding;

public class StartForResultFirstActivity extends AppCompatActivity {
    //결과코드 정의
    public static final int RESULT_CODE_ID_FAIL=1000;
    public static final int RESULT_CODE_PWD_FAIL=1001;

    private ActivityStartForResultFirstBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStartForResultFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //전달받은 인텐트에 저장된 데이타 읽어서 텍스트뷰에 뿌리기
        Intent intent = getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        binding.textStartActivityForResult1.setText(String.format("아이디:%s,비번:%s",username,password));

        binding.btnBack.setOnClickListener(v->{
            //아이디가 "KIM"이고 비밀번호가 "1234"회원이라고 가정]
            if(!"KIM".equals(username)){
                intent.putExtra("idError","아이디가 틀려요");
                setResult(RESULT_CODE_ID_FAIL,intent);
            }
            else if(!"1234".equals(password)){
                intent.putExtra("pwdError","비번이 틀려요");
                setResult(RESULT_CODE_PWD_FAIL,intent);
            }
            else{
                intent.putExtra("success",username+"님 방가방가!!!");
                setResult(RESULT_OK,intent);
            }
            //액티비티 종료
            finish();
        });

    }
}