package com.kosmo.kosmoapp.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kosmo.kosmoapp.MainActivity;
import com.kosmo.kosmoapp.R;
import com.kosmo.kosmoapp.databinding.FragmentContent1Binding;
import com.kosmo.kosmoapp.databinding.FragmentContent2Binding;
import com.kosmo.kosmoapp.photo.PhotoAdapter;
import com.kosmo.kosmoapp.photo.PhotoItem;
import com.kosmo.kosmoapp.photo.PhotoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class Content2 extends Fragment {


    public Content2(){
        Log.i("com.kosmo.kosmoapp","Content2생성자");
    }
    private FragmentContent2Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentContent2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Log.i("com.kosmo.kosmoapp","Content2 onCreateView");
        //다이얼로그 띄우기
        /*
        View progressLayout = LayoutInflater.from(getContext()).inflate(R.layout.progress_layout,null);
        ((TextView)progressLayout.findViewById(R.id.progressTitle)).setText("로딩중입니다...");
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setView(progressLayout).create();
        dialog.show();*/
        //Retrofit객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        //Retrofit객체로 PhotoService타입 인스턴스화
        PhotoService service=retrofit.create(PhotoService.class);
        Call<List<PhotoItem>> call= service.photos();
        //Call객체로 비동기 요청 보내고 응답받기
        call.enqueue(new Callback<List<PhotoItem>>() {
            @Override
            public void onResponse(Call<List<PhotoItem>> call, Response<List<PhotoItem>> response) {
                if(response.isSuccessful()){
                    List<PhotoItem> items=response.body();
                    PhotoAdapter adapter = new PhotoAdapter(getContext(),items,R.layout.photo_layout);
                    binding.recyclerView.setAdapter(adapter);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    //SystemClock.sleep(3000);//속도가 빨라서 다이얼로그창 오래보고 싶어서
                    //dialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<List<PhotoItem>> call, Throwable t) {}
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("com.kosmo.kosmoapp","Content2 onViewCreated");
    }
}