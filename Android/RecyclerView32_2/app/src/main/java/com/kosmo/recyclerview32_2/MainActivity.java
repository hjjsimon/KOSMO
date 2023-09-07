package com.kosmo.recyclerview32_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kosmo.recyclerview32_2.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //프래그먼트 생성
        firstFragment= new FirstFragment();
        secondFragment = new SecondFragment();
        //화면로드시 첫번째 프래그먼트로 화면 설정
        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(),firstFragment).commit();
        //툴바를 액션바로 설정-툴바는 옵션메뉴처럼 메뉴를 인플레이트하고 이벤트 처리(menu폴더에 툴바용 xml메뉴 생성한다)
        setSupportActionBar(binding.toolbar);
        //데이타 준비
        List<Item> items = new Vector<>();
        for(int i=1;i <=50;i++) items.add(new Item(i+"번째 제목",R.drawable.rounded));
        //어댑터 생성
        MyRecyclerAdpater adpater= new MyRecyclerAdpater(this,items);
        //리사이클러뷰와 어댑터 연결
        binding.recyclerView.setAdapter(adpater);
        //아이템간 여백 설정
        binding.recyclerView.addItemDecoration(new MyItemDecoration(20,-1));
        //레이아웃 설정(필수-아이템 쁘려주기위해서)
        //binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));//리니어 세로 방향
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));//리니어 가로 방향
        //binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//그리드 세로방향
        //binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3,RecyclerView.HORIZONTAL,false));//그리드 가로방향
    }
    //툴바에 메뉴 아이콘 표시하기위한 콜백 메소드 오버라이딩
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }///////////
    // 메뉴 아이콘 이벤트 처리

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.search_menu)
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out).replace(binding.container.getId(),firstFragment).commit();
        else
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out).replace(binding.container.getId(),secondFragment).commit();
        return super.onOptionsItemSelected(item);
    }
}