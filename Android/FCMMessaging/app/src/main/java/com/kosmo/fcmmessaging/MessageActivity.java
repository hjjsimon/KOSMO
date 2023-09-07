package com.kosmo.fcmmessaging;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.textMessage)).setText(String.format(
                "알림제목:%s%n,알림텍스트:%s%n데이타메시지:%s",
                intent.getStringExtra("title"),
                intent.getStringExtra("body"),
                intent.getStringExtra("message")

        ));
    }
}