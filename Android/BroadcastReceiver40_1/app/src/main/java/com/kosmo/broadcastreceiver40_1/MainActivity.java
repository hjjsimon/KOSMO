package com.kosmo.broadcastreceiver40_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import com.kosmo.broadcastreceiver40_1.databinding.ActivityMainBinding;

/*
    자바코드로 리시버 등록 및 해제]
    해당 어플리케이션에 리시버를 등록하여
    배터리 변화를 감지하여 이미지 뷰에 이미지 설정 및
    에디트 텍스트에는 외부전원연결 감지하여 표시

 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }////////////

    //브로드캐스트 리시버-익명 클래스로 구현]
    private BroadcastReceiver receiver = new BroadcastReceiver(){
        //2]onReceive()오버라이딩-방송 수신시 자동 호출됨.
        @Override
        public void onReceive(Context context, Intent intent) {

            //시스템에서 보낸 방송내용이 배터리상태 변화라면
            if(intent.getAction() == Intent.ACTION_BATTERY_CHANGED){
                //현재 배터리 레벨(충전량)을 가져오자
                int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                if(level ==0)
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_0);
                else if(level >0 && level <=10)
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_10);
                else if(level >10 && level <=20)
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_20);
                else if(level >20 && level <=40)
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_40);
                else if(level >40 && level <=60)
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_60);
                else if(level >60 && level <=80)
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_80);
                else
                    binding.imageView.setImageResource(R.drawable.stat_sys_battery_100);

                //배터리 전원 플러그 파악]
                int plugged=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch(plugged){
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        binding.editText.setText("AC전원 연결");break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        binding.editText.setText("USB전원 연결");break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        binding.editText.setText("무선전원 연결");break;
                    case 0:binding.editText.setText("전원 연결 OFF");break;
                }
            }
        }
    };

    //브로드캐스트 리시버를 앱에 등록]-매니페스트 파일에는 설정 불필요
    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(브로드캐스트객체,인텐트 필터)메소드로 등록
        //수신할 방송 내용을 설정:IntentFilter객체
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(receiver,filter);

    }
    //등록된 브로드캐스트 리시버 해제
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


}