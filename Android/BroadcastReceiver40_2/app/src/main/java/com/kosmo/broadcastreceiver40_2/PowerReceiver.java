package com.kosmo.broadcastreceiver40_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//1]BoardcastReceiver상속
public class PowerReceiver extends BroadcastReceiver {

    //2]onReceive()오버라이딩-방송 수신할때마다 자동 호출되는 메소드
    //intent -안드로이드 OS에서 보낸 방송 내용이 저장되 있다.
    @Override
    public void onReceive(Context context, Intent intent) {
       //화면 전환용 인텐트
        Intent powerIntent = new Intent(context,PowerActivity.class);
       //방송 내용(브로드캐스트 액션)에 따라 데이타를 설정
        if(intent.getAction() == Intent.ACTION_POWER_CONNECTED)
            powerIntent.putExtra("power_status","POWER IS CONNECTED");
        else if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED)
            powerIntent.putExtra("power_status","POWER IS DISCONNECTED");


        //PowerAtivity 화면 destory시(위로 드래그)
        //MainActivity도 삭제 하려면
        //AndoridManifest파일의 Activity태그에
        //android:noHistory="true"속성 추가 혹은 아래처럼 코드 추가
        //((MainActivity)context).finish();
        context.startActivity(powerIntent);


    }
}