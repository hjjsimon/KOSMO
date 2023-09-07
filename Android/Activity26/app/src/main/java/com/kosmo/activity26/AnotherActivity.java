package com.kosmo.activity26;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kosmo.activity26.databinding.ActivityAnotherBinding;

public class AnotherActivity extends AppCompatActivity {

    private ActivityAnotherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAnotherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(MainActivity.TAG,"AnotherActivity의 onCreate() invoked");
        binding.btnBack.setOnClickListener(v->{
            finish();//현재 액티비티가 종료됨 즉 onPause()->onStop()->onDestory()
                     //정상 종료이기때문에 onSaveInstanceState()가 호출안됨.
        });

    }
    //2]
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MainActivity.TAG,"AnotherActivity의 onStart() invoked");
    }
    //3]

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MainActivity.TAG,"AnotherActivity의 onResume() invoked");
    }
    //다른 액티비티나 앱으로 전환시 이후
    //4]

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MainActivity.TAG,"AnotherActivity의 onPause() invoked");
    }
    //5]

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MainActivity.TAG,"AnotherActivity의 onStop() invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.TAG,"AnotherActivity의 onDestroy() invoked");
    }
    //5-1]

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(MainActivity.TAG,"AnotherActivity의 onRestart() invoked");
    }
}