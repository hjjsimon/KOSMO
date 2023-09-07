package com.kosmo.intent27_2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kosmo.intent27_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStartActivity.setOnClickListener(handler);
        binding.btnStartActivityforResult1.setOnClickListener(handler);
        binding.btnStartActivityforResult2.setOnClickListener(handler);
    }///////////onCreate
    //※전환하려는 액티비티별로 ActivityResultLauncher객체를 만든다
    //ActivityResultLauncher객체 생성-ActivityResultLauncher<T>에서 T는 전환된 화면서 받을 결과의 타입
    private ActivityResultLauncher<Intent> firstLauncher=
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    //전환된 화면(액티비티)에서  setResult(결과코드,인텐트)호출시 onActivityResult(ActivityResult result)가 자동 호출된다
                    /*
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {

                        }
                    }*/
                    result->{
                        switch(result.getResultCode()){
                            case StartForResultFirstActivity.RESULT_CODE_ID_FAIL:
                                binding.textviewMain.setText(result.getData().getStringExtra("idError"));break;
                            case StartForResultFirstActivity.RESULT_CODE_PWD_FAIL:
                                binding.textviewMain.setText(result.getData().getStringExtra("pwdError"));break;
                            case Activity.RESULT_OK:
                                binding.textviewMain.setText(result.getData().getStringExtra("success"));
                        }
                    });

    private ActivityResultLauncher<Intent> secondLauncher=
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result->{
                        if(result.getResultCode()==Activity.RESULT_OK){
                            Intent intent = result.getData();
                            binding.textviewMain.append("\r\n"+intent.getStringExtra("success"));
                            binding.textviewMain.append("\r\n"+intent.getStringExtra("username"));
                            binding.textviewMain.append("\r\n"+intent.getStringExtra("password"));
                        }
                    });

    private View.OnClickListener handler=v->{
        //사용자 입력값 받기
        String username = binding.editUser.getText().toString();
        String password = binding.editPass.getText().toString();

        //인텐트 생성
        Intent intent= new Intent(v.getContext(),StartActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        if(v.getId()==R.id.btnStartActivity){
            startActivity(intent);
        }
        else if(v.getId()==R.id.btnStartActivityforResult1){
            //This method(startActivityForResult()) was deprecated in API level 30.
            //전환할 액티비티로 설정변경
            intent.setClass(MainActivity.this, StartForResultFirstActivity.class);
            //startActivity()가 아닌 ActivityResultLauncher객체로 액티비티 전환 즉 다른 액티비티를 런칭시킨다
            firstLauncher.launch(intent);
        }
        else{
            //전환할 액티비티로 설정변경
            intent.setClass(MainActivity.this, StartForResultSecondActivity.class);
            secondLauncher.launch(intent);
        }

    };
    @Override
    protected void onResume() {
        super.onResume();
        //입력창 초기화 및 포커스 주기
        binding.editPass.setText("");
        binding.editUser.setText("");
        binding.editUser.requestFocus();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //세번째 버튼 이벤트를 위한 초기화-즉 계속 append되는 거 방지
        binding.textviewMain.setText("메인 액티비티 화면!");
    }
}