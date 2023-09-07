package com.kosmo.fcmmessaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

//https://firebase.google.com/docs/cloud-messaging/android/receive?hl=ko

//※ 매니페스트파일에 등록해야 한다
//1. FirebaseMessagingService 확장(상속)
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG="com.kosmo.fcmmessaging";

    //2. onMessageReceived()와 onNewToken() 오버라이딩

    //※포그라운드 상태인 앱에서도 알림 메시지(FCM에서 자동처리)수신하려면 onMessageReceived 콜백 오버라이딩
    //파이어베이스 콘솔(메시지를 보내는 UI)에서 알림 메시지 및 데이타 메시지를 보낼때
    //내가만든 UI에서 데이타메시지만 보낼때는 onMessageReceived()호출이 안된다

    //FCM에서 받은 메시지를 상태바에 알림으로 표시되도록 하자
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        //super.onMessageReceived(message);
        //푸쉬 메시지를 저장하는 맵
        Map<String,String> pushMessage= new HashMap<>();
        Log.i("com.kosmo.fcmmessaging","From:"+message.getFrom());
        //알림메시지:키값이 정해져 있다 :제목-title,텍스트-body
        //예: {"notification":{"title":"알림 제목","body":"알림 텍스트"}}
        if(message.getNotification() !=null){//알림메시지를 받은 경우
            Log.i("com.kosmo.fcmmessaging","알림 제목:"+message.getNotification().getTitle());
            Log.i("com.kosmo.fcmmessaging","알림 내용:"+message.getNotification().getBody());
            pushMessage.put("title",message.getNotification().getTitle());
            pushMessage.put("body",message.getNotification().getBody());
        }
        //데이타 메시지(추가 옵션(선택사항)인 키/값 항목에 입력한 데이타)
        //getData():Map컬렉션 반환
        //알림메시지는 노티피케이션으로 띄우자 그리고 모든 푸쉬메시지를 MainActivity의 인텐트 부가 정보로 전송하자
        if(message.getData() !=null && message.getData().size() > 0){//데이타 메시지를 받은 경우
            //※※※사용자 웹(우리가 만든 웹 앱) UI 폼 하위요소의 파라미터명이 키 이다
            //파이어베이스 웹 UI는 데이타(선택사항)의 키 입력값이 키이다.
            Map<String,String> map = message.getData();
            Set<String> keys = map.keySet();
            for(String key:keys){
                String value = map.get(key);
                Log.i("com.kosmo.fcmmessaging",String.format("데이타메시지:키-%s,값-%s",key,value));
                pushMessage.put(key,value);
            }
        }////////////////

        //상태바에 알림을 표시하기 위한 메소드 호출
        showNotification(pushMessage);
    }
    //상태바에 알림을 표시하기 위한 메소드
    //또한 (프론트 엔드 상태 에서) 앱 상태바에 노티 드래그해서 클릭시 MainActivity(디폴트는 런처)가 아닌 다른 액티비티로 전환
    private void showNotification(Map<String, String> pushNessage) {
        Intent intent = new Intent(this,MessageActivity.class);
        intent.putExtra("title",pushNessage.get("title"));
        intent.putExtra("body",pushNessage.get("body"));
        //인텐트에 부가 정보 저장("title","body")키는 필수고 나머지키는 사용자 정의 키이다)
        Set<String> keys = pushNessage.keySet();
        String message="";
        for(String key:keys){//데이타 메시지는 "message"라는 키로 하나의 문자열로 저장하자
            if(!(key.equals("title") || key.equals("body"))){
                String value = pushNessage.get(key);
                message+=String.format("%s:%s%n",key,value);
                intent.putExtra("message",message);
            }
        }
        //화면전환을 인텐트의 플래그 설정
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //펜딩 인텐트로 설정
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = createNotificationCompatBuilder(pushNessage.get("title"),pushNessage.get("body"));

        //실행할 펜딩 인텐트 설정
        builder.setContentIntent(pendingIntent);
        //Notification객체 생성
        Notification notification=builder.build();
        //통지하기
        //NotificationManager의 notify()메소드로 Notification객체 등록
        //notify(Notification을 구분하기 위한 구분자,Notification객체);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {//버전체크 코드
            //오레오 부터 아래 코드 추가해야 함 시작
            NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);//스마트폰에 노티가 도착했을때 빛을 표시할지 안할지 설정
            notificationChannel.setLightColor(Color.RED);//위 true설정시 빛의 색상
            notificationChannel.enableVibration(true);//노티 도착시 진동 설정
            notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,100});//진동 시간(1000분의 1초)
            //오레오 부터 아래 코드 추가해야 함 끝
            //노피케이션 매니저와 연결
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(1,notification);
    }//////////////////showNotification

    private NotificationCompat.Builder createNotificationCompatBuilder(String title,String content){
        return new NotificationCompat.Builder(this,"CHANNEL_ID")
                .setSmallIcon(android.R.drawable.ic_dialog_email)//노티 도착시 상태바에 표시되는 아이콘
                .setContentTitle(title)//노티 드래그시 보이는 제목
                .setContentText(content)//노티 드래그시 보이는 내용
                .setAutoCancel(true)//노티 드래그후 클릭시 상태바에서 자동으로 사라지도록 설정
                .setWhen(SystemClock.currentThreadTimeMillis())//노티 전달 시간
                .setDefaults(Notification.DEFAULT_VIBRATE);//노티시 알림 방법
    }/////////////////

    //토큰이 변경될때마다 호출되는 콜백 메소드- FCM에서 발행된 토큰을 우리의 서버로 전송
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("com.kosmo.fcmmessaging","FCM에서 발행한 토큰:"+token);
        //생성된 토큰을 내 서버에 보내기
        sendNewTokenToServer(token);
    }/////////////////////
    //서버로 토큰을 보내 데이타베이스에 저장하기 위한 HTTP 요청용 메소드
    private void sendNewTokenToServer(String token) {

        Retrofit retrofit =new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://192.168.0.10:9090")
                .build();

        FCMService service= retrofit.create(FCMService.class);
        Call<Map<String,String>> call= service.newToken(token);
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                Log.i("com.kosmo.fcmmessaging",response.isSuccessful()?response.body().toString():response.errorBody().toString());
            }
            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }///////////////////

}