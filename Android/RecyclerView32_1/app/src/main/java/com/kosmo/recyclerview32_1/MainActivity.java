package com.kosmo.recyclerview32_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.kosmo.recyclerview32_1.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Vector;

/*
    -RecyclerView는 하나로 ListView 및 GridView와 같은 효과를 낼 수 있다(Flexible)
    -리스트뷰는 각 아이템뷰 즉 컨버트뷰를 아이템 갯수만큼 생성한다
     RecyclerView는 아이템 갯수만큼 아이템뷰(뷰홀더)를 생성하지 않고
      화면 해상도에 따른 아이템 표시를 위한
     갯수(화면표시용+스크롤 여분용)만큼 뷰홀더를 생성하여
     모든 아이템을 표시 한다(기존 뷰홀더 재활용:Recycle)
     그래서 대용량의 데이터를 소수의 아이템뷰만으로 표현할 수 있다.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //데이타 준비
        List<Item> items = new Vector<>();
        for(int i=1;i<=50;i++) items.add(new Item("한소인"+i,"개발부"+i,"2023-07-31",R.drawable.rounded));
        //1)어댑터 생성
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this,items);
        //2)리사이클러뷰와 어댑터 연결
        binding.recyclerView.setAdapter(adapter);
        //리사이클러 뷰 아이템간 여백용
        binding.recyclerView.addItemDecoration(new MyItemDecoration(20));

        //3)※RecyclerView는 반드시 레이아웃 매니저를 설정 해줘야 아이템이 보인다
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));//디폴트는 세로방향(ListView와 같다)

        //가로 방향으로 바꾸기
        /*
        Params:
        context – Current context, will be used to access resources.
        orientation – Layout orientation. Should be HORIZONTAL or VERTICAL.
        reverseLayout – When set to true, layouts from end to start.
         */
        //binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));

        /*
        Creates a vertical GridLayoutManager
        Params:
        context – Current context, will be used to access resources.
        spanCount – The number of columns in the grid
         */
        //RecyclerView로 GridView효과 내기 즉 Flexiable하다
        //2은  가로방향 카드 갯수 즉 컬럼수
        //binding.recyclerView.setLayoutManager(new GridLayoutManager(this,2));//(GridView와 같다)

    }////////////onCreate
}