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
import com.kosmo.recyclerview32_2.databinding.FragmentSecondBinding;

import java.util.List;
import java.util.Vector;


public class SecondFragment extends Fragment {


    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    private FragmentSecondBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //데이타 준비
        List<Item> items = new Vector<>();
        for(int i=1;i<=10;i++) items.add(new Item(i+"'th Content",-1));
        binding= FragmentSecondBinding.inflate(inflater,container,false);
        View view= binding.getRoot();
        //어댑터 생성
        MyRecyclerViewSecondAdapter adapter = new MyRecyclerViewSecondAdapter(context,items);
        //리사이클러뷰와 어댑터 연결
        binding.recyclerViewSecond.setAdapter(adapter);
        binding.recyclerViewSecond.addItemDecoration(new MyItemDecoration(-1,30));//오른쪽 여백은 의미없다
        //레이아웃 설정(필수-아이템 쁘려주기위해서)
        binding.recyclerViewSecond.setLayoutManager(new LinearLayoutManager(context));//리니어 세로방향
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}