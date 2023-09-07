package com.kosmo.recyclerview32_2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kosmo.recyclerview32_2.databinding.FragmentFirstBinding;

import java.util.List;
import java.util.Vector;


public class FirstFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    private FragmentFirstBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //데이타 준비
        List<Item> items = new Vector<>();
        for(int i=1;i<=10;i++) items.add(new Item(i+"번째 컨텐츠",-1));
        binding= FragmentFirstBinding.inflate(inflater,container,false);
        View view= binding.getRoot();
        //어댑터 생성
        MyRecyclerViewFirstAdapter adapter = new MyRecyclerViewFirstAdapter(context,items);
        //리사이클러뷰와 어댑터 연결
        binding.recyclerViewFirst.setAdapter(adapter);
        binding.recyclerViewFirst.addItemDecoration(new MyItemDecoration(-1,20));//오른쪽 여백은 의미없다
        //레이아웃 설정(필수-아이템 쁘려주기위해서)
        binding.recyclerViewFirst.setLayoutManager(new LinearLayoutManager(context));//리니어 세로방향
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}