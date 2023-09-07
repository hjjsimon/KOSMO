package com.kosmo.notification23_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //시스템 서비스로 NotificationManager객체 얻기
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //오레오부터 아래 코드 추가해야 함 시작
            NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_ID","CHANNEL_NAME",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);//스마트폰에 노티가 도착했을때 빛을 표시할지 안할지 설정
            notificationChannel.setLightColor(Color.RED);//위 true설정시 빛의 색상
            notificationChannel.enableVibration(true);//노티 도착시 진동 설정
            notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,100});//진동 시간(1000분의 1초)
            //노피케이션 매니저와 연결
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    /*
    Button의 onClick 속성:
    속성값은 메소드명이다
    public void 메소드명(View view){}
     */
    //기본형]
    public void basicNotification(View v){
        //NotificationCompat.Builder객체 생성
        NotificationCompat.Builder builder=createNoticationCompatBuilder();
        //실행할 펜딩 인텐트 설정
        builder.setContentIntent(createPendingIntent());
        //Notification객체 생성
        Notification notification=builder.build();
        //통지하기
        //NotificationManager의 notify()메소드로 Notification객체 등록
        //notify(Notification을 구분하기 위한 구분자,Notification객체);
        notificationManager.notify(1,notification);
    }
    /*
   제목은 setBigContentTitle이 보인다
   내용은 기본의 setContentTitle
   노티를 아래로 드래그시 InboxStyle의 내용으로 변경된다.
   */
    //확장형]
    public void extendNotification(View v){
        NotificationCompat.Builder builder=createNoticationCompatBuilder();
        builder.setContentIntent(createPendingIntent());

        //InboxStyle을 Notification의 스타일로 사용하기위한 InboxStyle객체 생성
        NotificationCompat.InboxStyle style=new NotificationCompat.InboxStyle();
        style.setBigContentTitle("자바과정 커리큘럼");//제목
        style.setSummaryText("커리큘럼");//노티 스몰 아이콘 옆에 표시됨
        //내용은 addLine()으로 추가
        style.addLine("자바").addLine("스프링").addLine("안드로이드");
        builder.setStyle(style).setSmallIcon(android.R.drawable.ic_delete);

        Notification notification=builder.build();
        notificationManager.notify(2,notification);
    }
    /*
        제목과 내용이 커스텀 레이아웃으로 모두  대체되서 보이게 하자
    */
    //커스텀형]
    public void customNotification(View v){
        NotificationCompat.Builder builder=createNoticationCompatBuilder();
        builder.setContentIntent(createPendingIntent());
        //커스텀뷰 추가 시작
        RemoteViews remoteViews= new RemoteViews(getPackageName(),R.layout.custom_layout);
        remoteViews.setImageViewIcon(R.id.myimg, Icon.createWithResource(this, android.R.drawable.ic_menu_save));
        remoteViews.setTextViewText(R.id.mytitle,"커스텀 뷰 입니다");
        remoteViews.setTextViewTextSize(R.id.mytitle, TypedValue.COMPLEX_UNIT_SP,20);
        builder.setContent(remoteViews);

        Notification notification=builder.build();
        notificationManager.notify(3,notification);

    }
    //공통 메소드1:NotificationCompat.Builer(Notication객체 생성용 빌더)반환용
    private NotificationCompat.Builder createNoticationCompatBuilder(){
        return new NotificationCompat.Builder(this,"CHANNEL_ID")
                .setSmallIcon(android.R.drawable.ic_dialog_email)//노티 도착시 상태바에 표시되는 아이콘
                .setContentTitle("한국 소프트웨어 인재개발원")//노티 드래그시 보이는 제목
                .setContentText("가산 디지털에 위치하고 있는 교육기관입니다")//노티 드래그시 보이는 내용
                .setTicker("KOSMO")//상태바에 표시되는 티커
                .setAutoCancel(true)//노티 드래그후 클릭시 상태바에서 자동으로 사라지도록 설정
                .setWhen(SystemClock.currentThreadTimeMillis())//노티 전달 시간
                .setDefaults(Notification.DEFAULT_VIBRATE);//노티시 알림 방법
    }
    //공통 메소드2:펜딩 인텐트 생성용
    private PendingIntent createPendingIntent(){
        //1]상태바에 표시된 노티 클릭시 Notification객체에 등록할 인텐트 생성
        //1-1]노티 클릭시 이동할 화면정보를 가진 인텐트 생성
        //두번째 인자:노티클릭시 전환할 화면 클래스(즉 액티비티)
        //매니페스트에 액티비티 등록
        Intent intent = new Intent(this,KosmoActivity.class);
        //1-2]인텐트에 메시지 저장
        intent.putExtra("kosmo","메일이 도착했어요");

        //PendingIntent.FLAG_UPDATE_CURRENT:펜딩 인텐트가 이미 존재한다면 이를
        // 그대로 유지하면서 추가 데이터를만 업데이트 한다
        //API LEVEL 31이상 부터는 FLAG_IMMUTABLE 와 FLAG_MUTABLE중 하나
        //Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified when creating a PendingIntent.
        return PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_IMMUTABLE);

    }

}