package com.kosmo.retrofit35_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kosmo.retrofit35_2.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<BBSItem> oldItems = new Vector<>();
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //버튼에 리스너 부착
        binding.btnPlain.setOnClickListener(handler);
        binding.btnJson.setOnClickListener(handler);
        binding.btnJsonArray.setOnClickListener(handler);
        //어댑터 생성-JSON 배열로 데이타 받을때
        adapter = new MyRecyclerViewAdapter(this,oldItems);
        //리사이클러뷰에 어댑터 연결
        binding.recyclerView.setAdapter(adapter);
        //아이템간 여백주기
        binding.recyclerView.addItemDecoration(new MyItemDecoration(20));
        //리사이클러뷰 레이아웃 매니저 설정
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));//세로


    }///////////////onCreate
    private View.OnClickListener handler=v->{
        String username = binding.id.getText().toString().trim();
        String password = binding.pwd.getText().toString().trim();

        if(v.getId()==R.id.btnPlain){
            //서버로부터 응답을 일반 텍스트(text/plain)으로 받을때는
            //1. implementation 'com.squareup.retrofit2:converter-scalars:2.9.0' 라이브러리 추가
            //2. 컨버터는 ScalarsConverterFactory 사용
            BBSService service = getBBSService();
            //요청을 보내기 위한 Call객체 생성
            Call<String> call= service.getText(username,password);
            //요청 보내기
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("com.kosmo.retrofit","onResponse()의 에러:"+response.errorBody());
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("com.kosmo.retrofit","onFailure()의 에러:"+t.getMessage());
                }
            });
        }//if
        else if(v.getId()==R.id.btnJson){
            BBSService service = getBBSService();
            Map<String,String> map= new HashMap<>();
            //맵의 키는 서버에서 받는 파라미터명으로..반드시
            map.put("id",username);
            map.put("pwd",password);
            Call<Map<String,String>> call= service.getJson(map);
            call.enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if(response.isSuccessful()){
                        Map<String, String> body=response.body();
                        Toast.makeText(MainActivity.this,body.get("isLogin") , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("com.kosmo.retrofit","onResponse()의 에러:"+response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    Log.i("com.kosmo.retrofit","onFailure()의 에러:"+t.getMessage());
                }
            });
        }
        else{
            BBSService service = getBBSService();
            Call<List<BBSItem>> call=service.getJsonArray();
            call.enqueue(new Callback<List<BBSItem>>() {
                @Override
                public void onResponse(Call<List<BBSItem>> call, Response<List<BBSItem>> response) {
                    if(response.isSuccessful()){
                        List<BBSItem> newItems=response.body();
                        //ListView라면
                        //oldItems=response.body();
                        //adapter.notifyDataSetChanged();
                        //RecyclerView는 ListView처럼 아이템들이 갱신시 어댑터에 통지하는 방식:X
                        //해결책
                        //방법1- 어댑터를 버튼 클릭시마다 계속 생성해서 리사이클러뷰에 다시 설정
                        /*
                        adapter = new MyRecyclerViewAdapter(v.getContext(),newItems);
                        binding.recyclerView.setAdapter(adapter);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                         */
                        //방법2- 기존 어댑터를 그대로 사용 즉 계속 어댑터를 (new)생성하지 않는다
                        //1.DiffUtil.Callback을 상속받은 클래스 작성
                        //2.기존 어댑터에 데이타 갱신을 위한 사용자 정의 메소드 추가.
                        //3.데이타 변경시 위의 2번에서 사용자가 정의한 메소드 호출
                        adapter.notifyItemsChanged(newItems);

                    }
                    else{
                        Log.i("com.kosmo.retrofit","onResponse()의 에러:"+response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<BBSItem>> call, Throwable t) {
                    Log.i("com.kosmo.retrofit","onFailure()의 에러:"+t.getMessage());
                }
            });
        }
    };/////////////////////
    //공통 메소드:BBSService객체 얻기
    private BBSService getBBSService(){
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://192.168.0.13:9090")
                .build()
                .create(BBSService.class);

    }
}