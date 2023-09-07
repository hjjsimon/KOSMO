package com.kosmo.tablelayout04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        AppCompatEditText username=findViewById(R.id.username);
        AppCompatEditText password=findViewById(R.id.password);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnCancel = findViewById(R.id.btnCancel);
        //버튼에 리스너 부착
        btnOk.setOnClickListener(view -> {
            String id = username.getText().toString();
            String pass = password.getText().toString();
            Toast.makeText(view.getContext(), String.format("아이디:%s,비번%s",id,pass), Toast.LENGTH_SHORT).show();
        });
        btnCancel.setOnClickListener(view -> {
            //텍스트 클리어
            username.setText("");
            password.setText("");
            //포커스 주기
            username.requestFocus();
        });

    }
}