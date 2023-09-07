package com.kosmo.kosmoapp;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import com.kakao.sdk.user.UserApiClient;
import com.kosmo.kosmoapp.databinding.ActivityMainBinding;
import com.kosmo.kosmoapp.model.MyPagerAdapter;
import com.kosmo.kosmoapp.view.Content2;
import com.kosmo.kosmoapp.view.Content1;
import com.kosmo.kosmoapp.view.Content3;
import com.skydoves.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.bootpay.android.Bootpay;
import kr.co.bootpay.android.BootpayAnalytics;
import kr.co.bootpay.android.events.BootpayEventListener;
import kr.co.bootpay.android.models.BootExtra;
import kr.co.bootpay.android.models.BootItem;
import kr.co.bootpay.android.models.BootUser;
import kr.co.bootpay.android.models.Payload;
import kr.co.bootpay.android.webview.BootpayWebView;
import lombok.SneakyThrows;

//https://bootpay.gitbook.io/docs/client/pg/android
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //현재 앱에서 필요한 권한들
    private String[] requestPermissions={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    private SharedPreferences preferences;

    //권한 설정 클래스 선언
    private PermissionUtils permissionUtils;

    //백버튼 클릭시 바툼네비 메뉴 활성화용
    private int currentSelectedItemId=R.id.home_menu,preSelectedItemId;
    //뷰페이저 사용시 추가
    private int currentSelectedIndex=0,preSelectedIndex;
    BootpayWebView bootpayWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //결제
        BootpayAnalytics.init(this, "62e91c56cf9f6d001f7c17e3");
        // 초기설정 - 해당 프로젝트(안드로이드)의 application id 값을 설정합니다. 결제와 통계를 위해 꼭 필요합니다.
        //BootpayAnalytics.init(this, "62e91c56cf9f6d001f7c17e3");

        //전개된후 액션바의 제목 변경
        getSupportActionBar().setTitle("메인 컨텐츠");
        //상태바와 액션바(혹은 App Bar)의 배경색 변경:themes.xml
        //colorPrimary :액션바의 배경색
        //colorPrimaryVariant : 상태바의 배경색

        //프래그먼트 생성
        Content1 content1 = new Content1();
        Content2 content2 = new Content2();
        Content3 content3 = new Content3();

        //뷰페이저 사용시
        List<Fragment> fragments = Arrays.asList(content1, content2,content3);
        MyPagerAdapter adapter = new MyPagerAdapter(this,fragments);

        binding.viewPager.setAdapter(adapter);


        binding.dotsIndicator.attachTo(binding.viewPager);

        preferences = getSharedPreferences("camera",MODE_PRIVATE);

        //첫번째 프래그먼트 보이기
        //getSupportFragmentManager().beginTransaction().replace(R.id.containers,content1).commit();

        //BottomNavigationView에 이벤트 리스너 부착
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.home_menu:
                        //뷰 페이지 사용시
                        binding.viewPager.setCurrentItem(0);
                        preSelectedIndex=currentSelectedIndex;
                        currentSelectedIndex=0;

                        break;
                    case R.id.setting_menu:

                        //뷰 페이지 사용시
                        binding.viewPager.setCurrentItem(1);

                        binding.viewPager.setCurrentItem(1);
                        preSelectedIndex=currentSelectedIndex;
                        currentSelectedIndex=1;
                        break;
                    case R.id.contact_menu:
                        //뷰 페이지 사용시
                        binding.viewPager.setCurrentItem(2);
                        binding.viewPager.setCurrentItem(2);
                        preSelectedIndex=currentSelectedIndex;
                        currentSelectedIndex=2;
                        //3]권한 요청하기
                        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){

                            if (!permissionUtils.checkPermission()){//허용안된 권한이 있는 경우
                                //권한 요청
                                permissionUtils.requestPermissions();
                            }
                            else{//모든 권한이 허용된 경우
                                //카메라 버튼 활성화 하기위한 코드
                                preferences.edit().putString("camera_no","N").commit();
                            }
                        }////////////////if


                        break;
                    case R.id.all_menu:
                        //네비게이션 드로우 띄우기
                        binding.drawerLayout.openDrawer(GravityCompat.END);

                }
                //백버튼 클릭시 바툼네비 메뉴 활성화용
                preSelectedItemId=currentSelectedItemId;
                currentSelectedItemId=item.getItemId();
                return true;//true반환해야 선택이 활성화 된다
            }
        });
        //네비게이션 드러우(header_layout.xml)의 x이미지 이벤트 처리
        View headerView=binding.navigationView.getHeaderView(0);
        headerView.findViewById(R.id.closeDrawer).setOnClickListener(v->{
            Log.i("com.kosmo.kosmoapp","X버튼 이미지 클릭");
            if(binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END);
            }
        });
        //네비게이션 드러우의 탭 메뉴 추가
        TabLayout tabLayout=headerView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("HOME").setIcon(R.drawable.home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.contact).setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("설정1").setIcon(R.drawable.settings));
        tabLayout.addTab(tabLayout.newTab().setText("설정2").setIcon(R.drawable.settings));
        tabLayout.addTab(tabLayout.newTab().setText("설정3").setIcon(R.drawable.settings));
        tabLayout.addTab(tabLayout.newTab().setText("설정4").setIcon(R.drawable.settings));
        tabLayout.addTab(tabLayout.newTab().setText("설정5").setIcon(R.drawable.settings));
        //탭 메뉴 이벤트 처리
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭이 선택되었을때 자동 호출
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:Toast.makeText(getApplicationContext(), "첫번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;
                    case 1:Toast.makeText(getApplicationContext(), "두번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;
                    case 2:Toast.makeText(getApplicationContext(), "세번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;
                    case 3:Toast.makeText(getApplicationContext(), "네번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;
                    case 4:Toast.makeText(getApplicationContext(), "다섯번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;
                    case 5:Toast.makeText(getApplicationContext(), "여섯번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;
                    case 6:Toast.makeText(getApplicationContext(), "일곱번째 탭메뉴 아이템 클릭", Toast.LENGTH_SHORT).show();break;

                }
            }
            //탭이 선택되지 않았을때
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            //탭이 다시 선택되었을때
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        //네비게이션 드러우의 ExpandableLayout
        ExpandableLayout expandable = headerView.findViewById(R.id.expandable);
        expandable.parentLayout.setOnClickListener(v->{
            if(expandable.isExpanded())
                expandable.collapse();
            else   expandable.expand();
        });
        //펼쳐진 아이템 클릭시 이벤트 처리
        expandable.secondLayout.findViewById(R.id.button0).setOnClickListener(v->{

            Toast.makeText(v.getContext(), "첫번째 ExpandableLayout 아이템 클릭", Toast.LENGTH_SHORT).show();
        });

        expandable.secondLayout.findViewById(R.id.button1).setOnClickListener(v->{
           Toast.makeText(v.getContext(), "두번째 ExpandableLayout 아이템 클릭", Toast.LENGTH_SHORT).show();

        });
        expandable.secondLayout.findViewById(R.id.button2).setOnClickListener(v->{


            //Toast.makeText(v.getContext(), "세번째 ExpandableLayout 아이템 클릭", Toast.LENGTH_SHORT).show();
            // 결제호출

            BootUser user = new BootUser()
                    .setPhone("010-4203-4342") // 구매자 정보
                    .setId("hwanyhee")//구매자 아이디
                    .setEmail("hwanyhee@naver.com")//구매자 이메일
                    .setUsername("낭만자객");//구매자 username


            BootExtra extra = new BootExtra()
                    .setCardQuota("0,2,3")  // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)
                    .setOpenType("popup");
//                .setCarrier("SKT") //본인인증 시 고정할 통신사명, SKT,KT,LGT 중 1개만 가능
//                .setAgeLimit(40); // 본인인증시 제한할 최소 나이 ex) 20 -> 20살 이상만 인증이 가능

            Double price = 1000d;
            String pg = "kcp";
            String method = "card";



            //통계용 데이터 추가
            List<BootItem> items = new ArrayList<>();
            BootItem item1 = new BootItem().setName("마우스").setId("ITEM_CODE_MOUSE").setQty(1).setPrice(500d);
            BootItem item2 = new BootItem().setName("키보드").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(500d);
            items.add(item1);
            items.add(item2);

//        String value = "{\"application_id\":\"5b8f6a4d396fa665fdc2b5e8\",\"pg\":\"\",\"method\":\"\",\"methods\":[\"easy_card\",\"easy_bank\",\"card\",\"phone\",\"bank\",\"vbank\"],\"name\":\"블링블링's 마스카라\",\"price\":1000.0,\"tax_free\":0.0,\"order_id\":\"1234_1234_1243\",\"use_order_id\":0,\"account_expire_at\":\"\",\"show_agree_window\":0,\"paramJson\":\"\",\"user_token\":\"\",\"extra\":{\"start_at\":\"\",\"end_at\":\"\",\"expire_month\":0,\"vbank_result\":0,\"quotas\":[],\"app_scheme\":\"\",\"app_scheme_host\":\"\",\"locale\":\"\",\"popup\":0,\"escrow\":0},\"user_info\":{\"user_id\":\"\",\"username\":\"홍길동\",\"email\":\"testUser@email.com\",\"gender\":0,\"birth\":\"\",\"phone\":\"01012345678\",\"area\":\"서울\"},\"items\":[{\"item_name\":\"미키 마우스\",\"qty\":1,\"unique\":\"ITEM_CODE_MOUSE\",\"price\":1000.0,\"cat1\":\"\",\"cat2\":\"\",\"cat3\":\"\"},{\"item_name\":\"키보드\",\"qty\":1,\"unique\":\"ITEM_CODE_KEYBOARD\",\"price\":1000.0,\"cat1\":\"패션\",\"cat2\":\"여성상의\",\"cat3\":\"블라우스\"}]}";
//        Payload payload = Payload.fromJson(value);

            Payload payload = new Payload();
            payload.setApplicationId("62e91c56cf9f6d001f7c17e3")
                    .setOrderName("부트페이 결제테스트")
                    .setPg(pg)// 결제할 PG 사
                    .setOrderId("1234")// 결제 고유번호(랜덤하게 생성)
                    .setMethod(method)// 결제수단
                    .setPrice(price)// 결제할 금액
                    .setUser(user)
                    .setExtra(extra)
                    .setItems(items);


            Bootpay.init(getSupportFragmentManager(), getApplicationContext())
                    .setPayload(payload)
                    .setEventListener(new BootpayEventListener() {
                        @Override
                        public void onCancel(String data) {// 결제 취소시 호출
                            Log.d("com.kosmo.kosmoapp", "onCancel:"+data);
                            //x버튼 클릭시
                            Bootpay.removePaymentWindow();
                        }

                        @Override
                        public void onError(String data) {// 에러가 났을때 호출되는 부분
                            Log.d("com.kosmo.kosmoapp", "onError:"+data);
                            Bootpay.removePaymentWindow();
                        }

                        @Override
                        public void onClose(String data) {//결제창이 닫힐때 실행되는 부분
                            Log.d("com.kosmo.kosmoapp", "onClose:"+data);
                            Bootpay.removePaymentWindow();
                        }

                        @Override
                        public void onIssued(String data) {//가상계좌 발급이 완료되면 호출되는 함수
                            Log.d("com.kosmo.kosmoapp", "onIssued:"+data);
                        }

                        @Override
                        public boolean onConfirm(String data) {
                            Log.d("com.kosmo.kosmoapp", "onConfirm:"+data);
                          //Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
                            return true; //재고가 있어서 결제를 진행하려 할때 true (방법 2)
                          //return false; //결제를 진행하지 않을때 false
                        }

                        @SneakyThrows
                        @Override
                        public void onDone(String data) {// 결제완료시 호출
                            Log.d("com.kosmo.kosmoapp", "onDone:"+data);
                            /*
                            {"event":"done",
                            "data":{"receipt_id":"62ea36b0cf9f6d002b05da2b",
                                "order_id":"1234",
                                "price":1000,"tax_free":0,
                                "cancelled_price":0,
                                "cancelled_tax_free":0,
                                 "order_name":"부트페이 결제테스트",
                                 "company_name":"PICTORY",
                                 "gateway_url":"https://gw.bootpay.co.kr",
                                 "metadata":{},"sandbox":true,
                                 "pg":"KCP",
                                 "method":"카드",
                                 "method_symbol":"card",
                                 "method_origin":"카드",
                                 "method_origin_symbol":"card",
                                 "purchased_at":"2022-08-03T17:50:25+09:00",
                                 "requested_at":"2022-08-03T17:49:52+09:00",
                                 "status_locale":"결제완료",
                                "receipt_url":"https://door.bootpay.co.kr/receipt/bUFwMFYzclZmV1IzbUlDay9OeFF5QUdPYmJCOVNEa1FUTDR6emVnaVlKRk9t%0AUT09LS1pVGEzb2pkSzRoYVFkV1JDLS0vT04zOUh4WEdYSENpVVNRNERxdmln%0APT0%3D%0A",
                                "status":1,
                                "card_data":{
                                    "tid":"22465976835418",
                                    "card_approve_no":"37143171",
                                    "card_no":"5389200000002641",
                                    "card_quota":"00",
                                    "card_company_code":"CCBC",
                                    "card_company":"BC카드"
                                 }
                               }
                             }
                             */
                            //결제정보를 DB에 입력하기 위한 스프링 Rest API로 요청(Retrofit사용)코드 추가


                            Bootpay.removePaymentWindow(); //이코드 없으면 완료후 하얀 화면
                        }


                    }).requestPayment();


        });
        expandable.secondLayout.findViewById(R.id.button3).setOnClickListener(v->{
            Toast.makeText(v.getContext(), "네번째 ExpandableLayout 아이템 클릭", Toast.LENGTH_SHORT).show();
        });

        ExpandableLayout expandable2 = headerView.findViewById(R.id.expandable2);
        expandable2.parentLayout.setOnClickListener(v->{
            if(expandable2.isExpanded())
                expandable2.collapse();
            else   expandable2.expand();
        });

        permissionUtils = new PermissionUtils(this,this,requestPermissions);

    }//////////////onCreate



    //※onRequestPermissionsResult오버라이딩 하자
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!permissionUtils.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //거부한 경우 : 카메라 버튼 비 활성화를 위한 코드
            preferences.edit().putString("camera_no","Y").commit();
        }
        else{
            //허용한 경우 : 카메라 버튼 활성화를 위한 코드
            preferences.edit().putString("camera_no","N").commit();
        }
    }////////////////


    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            binding.drawerLayout.closeDrawer(GravityCompat.END);
            return;
        }

        if(currentSelectedIndex==0){
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_menu_close_clear_cancel)
                    .setTitle("앱 종료")
                    .setMessage("프로그램을 종료합니다")
                    .setPositiveButton("예", (dialog, which) -> {
                        System.exit(0);
                    })
                    .setNegativeButton("아니오", null).show();
            return;
        }
        //뷰 페이저 사용시 추가
        binding.viewPager.setCurrentItem(preSelectedItemId);
        //백버튼 클릭시 바툼네비 메뉴 활성화용
        binding.bottomNavigation.setSelectedItemId(preSelectedItemId);//바툼네비를 클릭하는 것과 같음
        //super.onBackPressed();//주석 미 처리시 바툼네비 메뉴만 변경처리되고 화면은 바뀌지 않음




    }
    //앱 종료시 SharedPreferences에 저장된 로그인 정보 삭제
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
        //카카로 로그아웃
        UserApiClient.getInstance().logout(error->{
            if (error != null) {
                Log.e("com.kosmo.kosmoapp", "로그아웃 실패. SDK에서 토큰 삭제됨", error);
            }
            else {
                Log.i("com.kosmo.kosmoapp", "로그아웃 성공. SDK에서 토큰 삭제됨");
            }
            return null;
        });
    }
}/////////////////class