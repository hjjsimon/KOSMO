package com.kosmo.tablayoutviewpager18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.kosmo.tablayoutviewpager18.databinding.ActivityMainBinding;
import com.kosmo.tablayoutviewpager18.model.MyPagerAdapter;
import com.kosmo.tablayoutviewpager18.view.CubeOutTransformer;
import com.kosmo.tablayoutviewpager18.view.TabContent1;
import com.kosmo.tablayoutviewpager18.view.TabContent2;
import com.kosmo.tablayoutviewpager18.view.TabContent3;

import java.util.Arrays;
import java.util.List;


//STEP3.프래그먼트에서 전송한 데이타를 받기 위한 OnDataTransferListener 구현
public class MainActivity extends AppCompatActivity implements TabContent1.OnDataTransferListener {

    private ActivityMainBinding binding;

    public TabContent1 tabContent1;//디른 프래그먼트로 데이타 전송하기 위함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("com.kosmo.tablayout","MainActivity의 onCreate");
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //1.탭 메뉴 추가후 실행후 앱에서 확인
        TabLayout tabLayout= binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText("HOME").setIcon(R.drawable.home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.contact).setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("설정").setIcon(R.drawable.settings));
        //2.Fragment 생성후 컬렉션에 저장
        tabContent1 = new TabContent1();
        //1번)액티비티->프래그먼트(로 데이타 전송하기):프래그먼트 객체.setArgments(Bundle)
        Bundle bundle = new Bundle();
        bundle.putString("ActivityToFragment","액티비티에서 번들로 전송하는 데이타");
        tabContent1.setArguments(bundle);


        TabContent2 tabContent2 = new TabContent2();
        TabContent3 tabContent3 = new TabContent3();

        //어댑터에 전달할 프래그먼트들을 담은 컬렉션
        List<Fragment> fragments = Arrays.asList(tabContent1,tabContent2,tabContent3);
        //3.PageAdapter객체 생성
        MyPagerAdapter adapter= new MyPagerAdapter(this,fragments);
        //4.ViewPager에 PageAdapter를 연결
        binding.viewPager.setAdapter(adapter);
        //binding.viewPager.setCurrentItem(1);//앱 최초 실행시 두번째 화면이 보임
        //위까지 코딩시 이제 화면이 보인다.단,탭 메뉴 클릭시 화면전환은 안된다

        //옵션:화면전환시 ViewPager의 PageTransformer를 이용한 변형 설정하기
        binding.viewPager.setPageTransformer(new CubeOutTransformer());


        //5.뷰페이저에 인디케이터 붙이기
        binding.dotsIndicator.attachTo(binding.viewPager);//인디케이터의 DOT이 3개  표시됨

        //6.탭 레리아웃(TabLayout) 이벤트 처리-즉 탭 메뉴 클릭시 비로소 화면전환이 된다
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭이 선택되었을때
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(탭메뉴인덱스) 호출시
                //어댑터의 createFragment(int position)가 호출된다
                binding.viewPager.setCurrentItem(tab.getPosition());
            }
            //탭이 선택되지 않았을때
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            //탭이 다시 선택되었을때
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


    }///////////onCreate
    //STEP3.프래그먼트에서 전송한 데이타를 받기 위한 오버라이딩(내가 만든 이벤트 리스너의 추상 메소드)
    @Override
    public void onDataTransfer(String data) {
        Log.i("com.kosmo.tablayout","액티비티의 onDataTransfer호출:"+data);
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}//////////////class