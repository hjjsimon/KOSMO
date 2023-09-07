package com.kosmo.bottomnavigationview24.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kosmo.bottomnavigationview24.R;
import com.kosmo.bottomnavigationview24.databinding.FragmentContent1Binding;

public class Content1 extends Fragment {

    private FragmentContent1Binding binding;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentContent1Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }//////////


}