package com.kosmo.bottomnavigationview24_viewpager2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kosmo.bottomnavigationview24_viewpager2.databinding.ActivityMainBinding;
import com.kosmo.bottomnavigationview24_viewpager2.model.MyPageAdapter;
import com.kosmo.bottomnavigationview24_viewpager2.view.Content1;
import com.kosmo.bottomnavigationview24_viewpager2.view.Content2;
import com.kosmo.bottomnavigationview24_viewpager2.view.Content3;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.Arrays;
import java.util.List;

/*
    프로젝트 복사





 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //백버튼 클릭시 바툼 네비게이션의 이전 메뉴 아이템 활성화용
    private int currentSelectedItemId=R.id.home_menu;
    private int beforeSelectedItemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //프래그먼트 생성
        Content1 content1 = new Content1();
        Content2 content2 = new Content2();
        Content3 content3 = new Content3();

        List<Fragment> fragments = Arrays.asList(content1,content2,content3);
        MyPageAdapter adapter = new MyPageAdapter(this,fragments);
        binding.viewPager.setAdapter(adapter);

        binding.dotsIndicator.attachTo(binding.viewPager);

        //1. 앱실행시 첫번째 프래그먼트를 화면에 표시

        //2. BottomNavigationView의 클릭 이벤트에 따라 프래그먼트(화면)을 교체
        binding.bottomNavigation.setOnItemSelectedListener((item)->{

            //현재 메뉴아이템 아이디를 이전 메뉴 아이템 아이디로 설정
            beforeSelectedItemId=currentSelectedItemId;
            //클릭한 메뉴아이템 아이디를 현재 메뉴아이템 아이디로 설정
            currentSelectedItemId=item.getItemId();

            if(item.getItemId()==R.id.home_menu){
                //인자은 인덱스다 즉 컬렉션에 저장된 프래그먼트의 인덱스다
                binding.viewPager.setCurrentItem(0);

            }
            else if(item.getItemId()==R.id.setting_menu){
                binding.viewPager.setCurrentItem(1);


            }
            else{
                binding.viewPager.setCurrentItem(2);
            }

            return true;//true반환해야 메뉴 아이템이 활성화 된다.false반환시는 메뉴 아이템이 활성화가 안된다
        });

    }//////////////onCreate

    //백 버튼 클릭시 호출되는 콜백 메소드
    @Override
    public void onBackPressed() {

        Log.i("bottom","백버튼 클릭이벤트 발생");
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Log.i("bottom","프래그먼트 갯수:"+fragments.size());
        Log.i("bottom","프래그먼트 보이는 여부:"+fragments.get(0).isVisible());

        if(currentSelectedItemId==R.id.home_menu) {//현재 아이템 아이디가 홈이라면 종료하기위한 UI표시
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_delete)
                    .setMessage("앱을 종료하시겠습니다?")
                    .setPositiveButton("예", (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton("아니오", null).show();
            return;

        }

        binding.bottomNavigation.setSelectedItemId(beforeSelectedItemId);
    }


}/////////