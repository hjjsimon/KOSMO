package com.kosmo.notification23_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class KosmoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosmo);
        ((TextView)findViewById(R.id.textView)).setText(getIntent().getStringExtra("kosmo"));
    }
}