package com.kosmo.tablayoutviewpager18.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.kosmo.tablayoutviewpager18.R;


//1]Fragement상속
//※androidx.fragment.app.Fragment 상속
public class TabContent2 extends Fragment {


    //2]onCreateView()오버 라이딩
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("com.kosmo.tablayout","TabContent2의 onCreateView");
        //3]layout inflate
        return inflater.inflate(R.layout.fragment_tab_content2,container,false);
    }//////////////////////

}