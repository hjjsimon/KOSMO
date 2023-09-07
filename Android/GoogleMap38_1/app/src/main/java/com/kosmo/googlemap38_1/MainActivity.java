package com.kosmo.googlemap38_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosmo.googlemap38_1.databinding.ActivityMainBinding;
import com.kosmo.googlemap38_1.databinding.InfoWindowLayoutBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

/*
[1.지도를 표시하기 위한 필수 작업]
1. API키 얻는다
2. 매니페스트 파일에
    <uses-permission android:name="android.permission.INTERNET"/> 권한 추가

3.발급받은 API키를 매니페스트 파일에 등록한다
   <!--발급받은 API키 설정-->
        <meta-data android:name="com.google.android.geo.API_KEY" android:value="발급받은 API KEY"/>
4. 액티비티의 레이아웃 파일에 <fragment> 태그로 액티비티에 프래그먼트를 붙인다
    class:"com.google.android.gms.maps.SupportMapFragment"
    혹은
    android:name="com.google.android.gms.maps.MapFragment"

    아래 속성은 옵션으로 지도 표시될때 카메라의 위치와 줌 수준 설정
    map:cameraTargetLat="37.47874436957802"
    map:cameraTargetLng="126.87866648223704"
    map:cameraZoom="16"
    예]
    <fragment
    xmlns:map="http://schemas.android.com/apk/res-auto"
    android:id="@+id/map"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    class="com.google.android.gms.maps.SupportMapFragment"
    map:cameraTargetLat="37.47874436957802"
    map:cameraTargetLng="126.87866648223704"
    map:cameraZoom="16"

    />

[2. 위도/경도로 카메라 이동시키기]
1. OnMapReadyCallback를 implements한다
2. onMapReady(GoogleMap)을 오버라이딩한다.여기서 멤버필드 GoogleMap을 초기화 한다

    소스 예]
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //멤버필드를 인자로 받은 GoogleMap으로 초기화
        this.googleMap = googleMap;

        //카메라 이동
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(37.5111158, 127.098167)));
        //지동 유형
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
3. onResume() 오버라이딩하여  getMapAsync()호출 그러면 onMapReady()가 실행된다
    @Override
    protected void onResume() {
        super.onResume();
        ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

4. 입력 받은 위도/경도로 카메라를 이동시키기 위해서 아래 메소드 호출
   GoogleMap객체.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(위도,경도),줌수준));


※최초 앱 실행시
    onCreate()->onStart()->onResume():getMapAsync()호출->onMapReady() invoked(GoogleMap객체인자로 받는다)

※위치 서비스를 사용하기위해서는
하나ㅐㅐㅔ논 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 권한 추가및 사용자에게 권한을 요청해야 한다

[Geocoder클래스]

순방향 지오코딩(Forward Geocoding):주소정보를 위도/경도로 변환
                                 getFromLocationName(String 주소명,int 반환활 주소 최대개수,
                                 double 박스영역의 좌측 아래위도,
                                 double 박스영역의 좌측 아래경도,
                                 double 박스영역의 우측 상단위도,
                                 double 박스영역의 우측 상단경도
                                 )사용
                                 혹은
                                 getFromLocationName(String 주소명,int 반환활 주소 최대개수)사용



 역방향 지오코딩(Reverse Geocoding):위도/경도를 주소정보로 변환
                                  getFromLocation(double 위도,double 경도,int 반환활 주소 최대개수)사용
                                  최대갯수는 1~5개 권장


지오코딩에 대한 정보는 구글 데이타베이스에 저장되어 있음.
그래서 매니페스트 파일에 반드시 인터넷 권한 추가
즉 인터넷을 통해 정보를 조회하기 때문에 조회시간이 오래걸릴 수 이ㅛ다.
그러므로 지오코딩 관련처리는 동기적으로 처리하기보다는 비동기적으로 처리하여
사용자 화면이 멈추지 않도록 해야한다(서비스나 스레드 사용)


Address클래스의 주요 메소드]
getAddressLine(int index):주소 정보를 반환.
주소정보는 여러 라인으로 저장되어 있으며 ,
index는 0부터시작
getMaxAddresLineIndex():주소정보를 표현하는 라인 개수 반환.
            없다면 -1반환.getAddresLine(int index)와 함께 사용
getPostalCode():우편번호 반환
getLatitude():위도반혼
getLongitude():경도 반환

※구글 맵 애플리케이션과는 달리 지오코딩 조회는 만족할 만한 수준은 아니다

 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private InfoWindowLayoutBinding windowLayoutBinding;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        windowLayoutBinding = InfoWindowLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //위도/경도로 지도 표시하기 위한 이벤트 처리
        binding.btnLocationByLatLng.setOnClickListener(v->{
            double lat=Double.parseDouble(binding.editLatitude.getText().toString());
            double lng=Double.parseDouble(binding.editLongitude.getText().toString());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),16));
            Location location= new Location(LocationManager.FUSED_PROVIDER);
            location.setLatitude(lat);
            location.setLongitude(lng);
            addMarker(location);
        });
        //주소명으로 지도 표시 이벤트 처리
        binding.btnLocationByAddress.setOnClickListener(v->{
            //주소명으로  위치 찾기 메소드 호출
            List<Address> list= getLocationByAddressName();
            //찾은 주소 목록(addresses)를 대화상자로 보여주기
            //주소명만 가지고 배열로 변환
            String[] addressNames= new String[list.size()];
            Log.i("googlemap","addressNames.length:"+addressNames.length);
            for(int i=0;i < list.size();i++){
                addressNames[i]=list.get(i).getAddressLine(i);
                Log.i("googlemap","주소록:"+addressNames[i]);
            }
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_map)
                    .setCancelable(false)
                    .setTitle(binding.editAddrname.getText()+"로 검색한 결과");
            if(addressNames.length > 0){//찾은 주소가 있는 경우
                Log.i("googlemap","주소록2:"+addressNames[0]);

                dialog.setTitle("주소를 선택하세요?")
                        .setSingleChoiceItems(addressNames,-1,(dialogInterface,which)->{
                            double lat=list.get(which).getLatitude();
                            double lng=list.get(which).getLongitude();
                            Log.i("googlemap","위도:"+lat+",경도:"+lng);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),16));
                            Location location= new Location(LocationManager.FUSED_PROVIDER);
                            location.setLatitude(lat);
                            location.setLongitude(lng);
                            addMarker(location);
                            //카메라 이동후 다이얼로그 창 닫기
                            dialogInterface.dismiss();
                        }).create().show();
                return;
            }
            //찾은 주소가 없는 경우
            dialog.setMessage("찾는 주소가 없습니다").setPositiveButton("학인",null).show();

        });
        //지도를 이미지로 저장 이벤트 처리
        binding.btnSaveMap.setOnClickListener(v->{
            googleMap.snapshot(bitmap->{
                //bitmap:현재 지도를 스냅삿으로 찍은 이미지의 비트맵
                //내부 저장소 캐쉬 디렉토리에 저장하기 위해 캐쉬 디렉토리에 대한 File객체 얻기
                File file= getCacheDir();
                try {
                    //비트맵을 저장할 출력스트림 생성
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + File.separator + "maps.jpg");
                    //비트맵을 이미지 파일로 압축
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                }catch(Exception e){e.printStackTrace();}


            });
        });

    }///////////////onCreate
    //주소명으로 찾기용 메소드
    private List<Address> getLocationByAddressName(){
        List<Address> addresses= new Vector<>();
        //사용자 입력 주소 얻기
        String locationName = binding.editAddrname.getText().toString().trim();
        //반환활 주소 최대개수
        int maxResults=5;
        //Geocoder생성
        Geocoder geocoder= new Geocoder(this, Locale.KOREA);


        try {
            //API 31버전에서 Deprecated
            addresses= geocoder.getFromLocationName(locationName, maxResults);

        }
        catch(Exception e){e.printStackTrace();}
        return addresses;
    }/////////////////
    //마커 표시용 메소드
    private void addMarker(Location location){
        //MarkerOptions객체 생성
        MarkerOptions options = new MarkerOptions();
        //마커를 표시할 위치 설정
        options.position(new LatLng(location.getLatitude(),location.getLongitude()));
        //마커 아이콘 설정
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pin_icon));
        //마커 드래그 설정
        options.draggable(true);
        //마커 클릭 이벤트
        options.title("내가 찾은 주소");
        options.snippet(binding.editAddrname.getText().toString());

        //커스텀 인포 원도우 설정
        MyInfoWindowAdapter myInfoWindowAdapter = new MyInfoWindowAdapter(windowLayoutBinding.getRoot(),binding.editAddrname.getText().toString());
        googleMap.setInfoWindowAdapter(myInfoWindowAdapter);
        //마커 추가전 기존 마커 클리어
        googleMap.clear();
        //구글 맵에 마커 추가(add라 검색시 계속 마커가 추가된다)
        googleMap.addMarker(options);


    }///////////////////
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap=googleMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //아래 메소드 호출시 onMapReady()가 실행된다
        ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.googleMap)).getMapAsync(this);
    }
}