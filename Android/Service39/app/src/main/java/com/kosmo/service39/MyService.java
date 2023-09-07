package com.kosmo.service39;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

//1]Service상속
public class MyService extends Service {
    private MediaPlayer player;
    //오버라이딩-필수
    @Override
    public IBinder onBind(Intent intent) {
        //지역서비스:내가 만든 앱에서 실행되는 서비스 무조건 null반환
        Log.i("com.kosmo.service","서비스의 onBind() invoked");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("com.kosmo.service","서비스의 onCreate() invoked");
        //MediaPlayer객체 생성
        player =MediaPlayer.create(this,R.raw.kalimba);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("com.kosmo.service","서비스의 onStartCommand() invoked");
        //서비스 시작 코드 작성
        //음악 재생
        Log.i("com.kosmo.service","player !=null && !player.isPlaying():"+(player !=null && !player.isPlaying()));
        if(player !=null && !player.isPlaying()) player.start();
        //액티비티에서 서비스 시작시 보낸 데이타 얻기
        String data=intent.getStringExtra("service");
        Log.i("com.kosmo.service","액티비티에서 보낸 데이타:"+data);
        return super.onStartCommand(intent, flags, startId);
    }
    //서비스가 중지될때 혹은 stopService()호출시
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("com.kosmo.service","서비스의 onDestroy() invoked");
        //미디어 재생 중지
        if(player !=null && player.isPlaying()) player.stop();
        //MediaPlayer객체 자원 해제
        player.release();
    }
}