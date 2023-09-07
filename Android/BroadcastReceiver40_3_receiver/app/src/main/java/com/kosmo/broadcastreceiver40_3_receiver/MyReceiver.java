package com.kosmo.broadcastreceiver40_3_receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    //방송 내용이 MY_BROADCAST_ACTION("com.kosmo.broadcastreceiver.MY_BROADCAST_ACTION") 일때 화면 전환
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("com.kosmo.broadcast",intent.getStringExtra("data"));
    }

}