package com.kosmo.location37;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kosmo.location37.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

//실행시 권한 요청:
//https://developer.android.com/training/permissions/requesting.html
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //현재 앱에서 필요한 권한들
    private String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
    //권한요청시 각 권한을 구분하기 위한 요청코드값(식별자)
    public static final int MY_LOCATION_SERVICE_PERMISSION=1;
    //사용자 위치 정보 관련 API 들
    private LocationManager locationManager;
    private LocationListener locationListener;
    //거부된 권한들을 저장할 컬렉션
    private List<String> deniedPermissions= new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //현재 앱이 필요로 하는 모든 권한을 사용자로 부터 얻기
        //사용자 권한은 String형 상수로 정의 되어 있다
        //자바코드:Manifest.permission.권한
        //xml : "android.permission.권한"
        //마쉬멜로우 이후 버전(API 23)부터 사용자에게 권한 요청 보낸다
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(requestUserPermissions()){//사용자가 위치 서비스 권한 허용시
                binding.btnMyLastPosition.setEnabled(true);
            }
        }/////
        //위치 관리자는 시스템 서비스 이므로 객체를 참조하기 위해 getSystemService()메소드 사용
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean isGps=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetwork=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.i("location",String.format("GPS:%s,NETWORK:%s",isGps,isNetwork));
        //위치 변화가 있을때마다  위치를 감지하는 리스너 객체 생성
        locationListener = location->{
            double lat=location.getLatitude();
            double lng=location.getLongitude();
            Log.i("location",String.format("위도:%s,경도:%s",lat,lng));
        };
        //최적의 프로바이더 얻기:true-활성화된 위치 제공자에서 찾기,false-모든 위치제공자에서(비활성화된거 포함)
        String provider = locationManager.getBestProvider(getCriteria(),true);
        Log.i("location",String.format("기준에 부합하는 위치 제공자:%s",provider));
        //버튼 이벤트 처리
        binding.btnStart.setOnClickListener(handler);
        binding.btnStop.setOnClickListener(handler);
        binding.btnMyLastPosition.setOnClickListener(handler);
    }////////onCreate
    //설정한 기준에 맞는 Provider(위치 서비스 제공자)를 얻기위한 기준 설정용 메소드
    private Criteria getCriteria(){
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);//true로 설정시 setAltitudeAccuracy()도 함께 설정하자
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setSpeedRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        return criteria;
    }///////////////////
    //사용자에게 권한을 요청하는 메소드(안드로이드 6.0(API LEVEL 23)이상부터 추가됨)
    private boolean requestUserPermissions(){
        for(String permission:permissions){
            int checkPermission=ActivityCompat.checkSelfPermission(this,permission);
            //0:권한 있다,-1:권한 없다
            if(checkPermission== PackageManager.PERMISSION_DENIED)//권한이 없는 경우
                deniedPermissions.add(permission);//권한이 없는 경우 deniedPermissions에 저장
        }
        //그렇다면 권한이 없는게 있다면 유저한테 요청 보내기
        if(!deniedPermissions.isEmpty()){
            //권한 요청 보낸다
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .setTitle("권한요청")
                    .setMessage("권한을 허용해야 앱을 정상적으로 사용할 수 있습니다")
                    .setPositiveButton("확인",(dialog,which)->{
                        //사용자에게 권한을 요청하는  코드 작성
                        //두번째 인자:요청할 권한들의 String[]
                        ActivityCompat.requestPermissions(MainActivity.this,deniedPermissions.toArray(new String[deniedPermissions.size()]),MY_LOCATION_SERVICE_PERMISSION);
                        //※onRequestPermissionsResult오버라이딩 하자(사용자가 권한 허용했는지 거부했는지 결과를 받기 위함)
                    })
                    .setNegativeButton("앱 종료",(dialog,which)->{finish();}).show();
            return false;
        }
        return true;
    }//////////////////requestUserPermissions
    //권한 허용을 물어보는 창(안드로이드 시스템에서 띄움)에서 동의(allow) /비동의(deny)를 클릭시 아래 메소드 자동 호출됨(콜백 메소드).
    /*
    int requestCode: ActivityCompat.requestPermissions()메소드 호출시 보낸 요청코드(어떤 권한을 요청했는지에 대한 식별자)
    String[] permissions: 사용자에게 요청한 권한들 즉 권한이 없는 권한들
    int[] grantResults:권한에 따른 사용자의 deny 혹은  allow결과를 담은 int형 배열

     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("location",String.format("grantResults.length:%s,grantResults:%s",grantResults.length, Arrays.toString(grantResults)));
        /*
            앱 사용중에만 허용,이번만 허용 :grantResults.length:2,grantResults:[0, 0]
            허용안함 : grantResults.length:2,grantResults:[-1, -1]
           사용자가  allow(허용)나 deny를 누른 경우 다시 묻지 않음이 표시됨(2021.12.20현재 사라짐)
         */
        switch (requestCode){
            case MY_LOCATION_SERVICE_PERMISSION://위치서비스 권한 요청이면
                if(grantResults.length > 0){//거부(-1) 혹은 허용(0) 한 경우
                    boolean isAllowed=false;
                    for(int i=0;i < grantResults.length;i++){
                        if(grantResults[i]==PackageManager.PERMISSION_GRANTED){//허용한 경우
                            isAllowed=true;
                            break;
                        }
                    }
                    if(isAllowed){
                        //요청 권한과 관련된 기능 및 서비스 활성화
                        binding.btnMyLastPosition.setEnabled(true);
                    }
                    else{
                        //요청 권한과 관련된 기능 및 서비스 활성화
                        binding.btnMyLastPosition.setEnabled(false);
                    }
                }
        }
    }/////////////////

    private View.OnClickListener handler=v->{
        if(v.getId()==R.id.btnStart){//위치 감지 시작-onResume()에서 호출해도 됨(requestLocationUpdates()메소드)
            try {
                locationManager.requestLocationUpdates(LocationManager.FUSED_PROVIDER, 3000, 5, locationListener);
            }
            catch (SecurityException e){e.printStackTrace();}
        }
        else if (v.getId()==R.id.btnStop) {//위치 감지 중지-onPause()나 onStop()에서 호출해도 됨.
                locationManager.removeUpdates(locationListener);
        }
        else{
            try{
                Location location= locationManager.getLastKnownLocation(LocationManager.FUSED_PROVIDER);
                Log.i("location","Location객체:"+location);
                if(location !=null){
                    Log.i("location",String.format("나의 최근 위치:위도-%s,경도-%s",location.getLatitude(),location.getLongitude()));
                }
            }
            catch(SecurityException e){e.printStackTrace();}
        }
    };
}