package com.kosmo.fragment28;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kosmo.fragment28.databinding.TopFragmentBinding;

public class TopFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private TopFragmentBinding binding;

    public TopFragment(){
        Log.i(MainActivity.TAG,"TopFragment의 생성자() invoked");
    }
    //1]액티비티의 onCreate() 실행후
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        Log.i(MainActivity.TAG,"TopFragment의 onAttach() invoked");
    }//////////
    //2]
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MainActivity.TAG,"TopFragment의 onCreate() invoked");
    }
    //3]
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(MainActivity.TAG,"TopFragment의 onCreateView() invoked");

        binding= TopFragmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        //버튼에 리스너 부착
        binding.btnFirstFragment.setOnClickListener(this);
        binding.btnSecondFragment.setOnClickListener(this);
        binding.btnThirdFragment.setOnClickListener(this);
        return view;
    }

    //4]액티비의 onStart()실행후
    @Override
    public void onStart() {
        super.onStart();
        Log.i(MainActivity.TAG,"TopFragment의 onStart() invoked");
    }
    //5]액티비의 onResume()실행후
    @Override
    public void onResume() {
        super.onResume();
        Log.i(MainActivity.TAG,"TopFragment의 onResume() invoked");
    }

    /////////사용자와 상호작용
    //6]아래 실행후 액티비티의 onPause()실행됨
    @Override
    public void onPause() {
        super.onPause();
        Log.i(MainActivity.TAG,"TopFragment의 onPause() invoked");
    }
    //7]아래 실행후 액티비티의 onStop()실행됨
    @Override
    public void onStop() {
        super.onStop();
        Log.i(MainActivity.TAG,"TopFragment의 onStop() invoked");
    }
    //8]
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
        Log.i(MainActivity.TAG,"TopFragment의 onDestroyView() invoked");
    }
    //9]
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.TAG,"TopFragment의 onDestroy() invoked");
    }
    //10]
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(MainActivity.TAG,"TopFragment의 onDetach() invoked");
    }

    //버튼 이벤트 처리
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnFirstFragment)
            getActivity().getSupportFragmentManager().beginTransaction().replace(((MainActivity)getActivity()).binding.container.getId(),((MainActivity)getActivity()).firstFragment).commit();
    }///////////////////////////
}
