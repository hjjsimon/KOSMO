package com.kosmo.jsonparser36_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.SystemClock;

import com.kosmo.jsonparser36_2.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //다이얼로그 띄우기
        AlertDialog dialog= new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(R.layout.progress_layout).show();
        //Retrofit객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        //Retrofit객체로 PhotoService타입 인스턴스화
        PhotoService service = retrofit.create(PhotoService.class);
        Call<List<PhotoItem>> call= service.photos();
        //Call객체로 비동기 요청 보내고 응답받기
        call.enqueue(new Callback<List<PhotoItem>>() {
            @Override
            public void onResponse(Call<List<PhotoItem>> call, Response<List<PhotoItem>> response) {
                if(response.isSuccessful()){
                    List<PhotoItem> items=response.body();
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(MainActivity.this,items);
                    binding.recyclerView.setAdapter(adapter);
                    binding.recyclerView.addItemDecoration(new MyItemDecoration(20));
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    SystemClock.sleep(2000);//속도가 빨라서 진행창이 너무 빨리 닫히니까
                    dialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<List<PhotoItem>> call, Throwable t) {}
        });

    }///////////////////////
}