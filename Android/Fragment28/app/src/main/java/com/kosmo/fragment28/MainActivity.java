package com.kosmo.fragment28;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kosmo.fragment28.databinding.ActivityMainBinding;
import com.kosmo.fragment28.databinding.FirstFragmentBinding;
import com.kosmo.fragment28.databinding.TopFragmentBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;
    public static final String TAG="com.kosmo.fragment";

    public FirstFragment firstFragment;

    public MainActivity(){
        Log.i(TAG,"MainActivity의 생성자 onvoked");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firstFragment = new FirstFragment();

        Log.i(TAG,"MainActivity의 onCreate() onvoked");
    }//onCreate

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"MainActivity의 onStart() onvoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"MainActivity의 onResume() onvoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"MainActivity의 onRestart() onvoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"MainActivity의 onPause() onvoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"MainActivity의 onStop() onvoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"MainActivity의 onDestroy() onvoked");
    }
    //※내부 클래스로 프래그먼트 구현시 반드시 public static class로
    //Fragment com.kosmo.fragment28.MainActivity.TopFragment must be a public static class to be  properly recreated from instance state.
    /*
    public static class TopFragment extends Fragment{
        private TopFragmentBinding topFragmentBinding;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            topFragmentBinding=TopFragmentBinding.inflate(inflater,container,false);
            View view = topFragmentBinding.getRoot();
            view.findViewById(R.id.btnFirstFragment).setOnClickListener(v-> Toast.makeText(getContext(), "탑의 첫번째 버튼 클릭", Toast.LENGTH_SHORT).show());
            return view;

        }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            topFragmentBinding=null;
        }
    }//////////TopFragment
    */
    //첫번째 화면용 프래그먼트
    public static class FirstFragment extends  Fragment{
        private Context context;
        private FirstFragmentBinding binding;
        //1]액티비티의 onCreate() 실행후
        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            this.context=context;
            Log.i(TAG,"FirstFragment의 onAttach() invoked");
        }//////////
        //2]
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i(TAG,"FirstFragment의 onCreate() invoked");
        }
        //3]
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.i(TAG,"FirstFragment의 onCreateView() invoked");

            binding= FirstFragmentBinding.inflate(inflater,container,false);
            View view = binding.getRoot();
            return view;
        }

        //4]액티비의 onStart()실행후
        @Override
        public void onStart() {
            super.onStart();
            Log.i(MainActivity.TAG,"FirstFragment의 onStart() invoked");
        }
        //5]액티비의 onResume()실행후
        @Override
        public void onResume() {
            super.onResume();
            Log.i(MainActivity.TAG,"FirstFragment의 onResume() invoked");
        }

        /////////사용자와 상호작용
        //6]아래 실행후 액티비티의 onPause()실행됨
        @Override
        public void onPause() {
            super.onPause();
            Log.i(MainActivity.TAG,"FirstFragment의 onPause() invoked");
        }
        //7]아래 실행후 액티비티의 onStop()실행됨
        @Override
        public void onStop() {
            super.onStop();
            Log.i(MainActivity.TAG,"FirstFragment의 onStop() invoked");
        }
        //8]
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding=null;
            Log.i(MainActivity.TAG,"FirstFragment의 onDestroyView() invoked");
        }
        //9]
        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i(MainActivity.TAG,"FirstFragment의 onDestroy() invoked");
        }
        //10]
        @Override
        public void onDetach() {
            super.onDetach();
            Log.i(MainActivity.TAG,"FirstFragment의 onDetach() invoked");
        }
    }
    public static class SecondFragment extends  Fragment{

    }
    public static class ThirdFragment extends  Fragment{

    }








}

/*
fragment 태그로 프래그먼트를 붙일때
앱실행시
TopFragment의 onAttach() invoked
TopFragment의 onCreate() invoked
TopFragment의 onCreateView() invoked
MainActivity의 onCreate() onvoked
TopFragment의 onStart() invoked
MainActivity의 onStart() onvoked
MainActivity의 onResume() onvoked
TopFragment의 onResume() invoked
앱 중지시
TopFragment의 onPause() invoked
MainActivity의 onPause() onvoked
TopFragment의 onStop() invoked
MainActivity의 onStop() onvoked
TopFragment의 onDestroyView() invoked
TopFragment의 onDestroy() invoked
TopFragment의 onDetach() invoked
MainActivity의 onDestroy() onvoked



 */