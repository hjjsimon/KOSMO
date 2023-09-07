package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"버튼을 클릭했어요",Toast.LENGTH_SHORT).show();
            }
        });*/
        //람다식으로 변경
        button.setOnClickListener(view->Toast.makeText(getApplicationContext(),"버튼을 클릭했어요",Toast.LENGTH_SHORT).show());
    }
}