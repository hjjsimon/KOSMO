package com.kosmo.jsonparser26_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.kosmo.jsonparser26_1.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String url ="android.resource://"+getPackageName()+"/"+ R.raw.happynew;
        Uri uri = Uri.parse(url);
        //uri 설정
        binding.videoView.setVideoURI(uri);
        //영상에 비디오 컨트롤러 부착(영상클릭시 하단에 컨트롤 버튼 나타남)
        binding.videoView.setMediaController(new MediaController(this));
        //영상종료시
        binding.videoView.setOnCompletionListener(mediaPlayer->{
            Log.i("video","setOnCompletionListener");
            binding.videoView.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "영상이 종료 되었습니다", Toast.LENGTH_SHORT).show();
        });
        //영상 재생이 준비될시
        binding.videoView.setOnPreparedListener(mediaPlayer -> {
            //mediaPlayer.setLooping(true);//무한 루프 재생
            Log.i("video","setOnPreparedListener");
            binding.videoView.start();
        });
    }///////////onCreate

    public void jsonParse(View v){
        new Thread(new Runnable() {
            StringBuilder builder;
            @Override
            public void run() {
                try {
                    //1]raw폴더에 있는 json.txt파일 읽기
                    InputStream is = getResources().openRawResource(R.raw.json);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is,"UTF-8"));
                    String data;
                    builder= new StringBuilder();
                    while((data=br.readLine())!=null){
                        builder.append(data);
                    }
                    Log.i("video",builder.toString());
                    runOnUiThread(()->{
                        try {
                            //여기서는 UI변경 가능
                            //2]읽어온 문자열 데이타를 JSON으로 파싱해서 카드뷰에 데이타 바인딩(문자열->JSONObject타입으로 변환)
                            //new JSONObject("JSON형식의 문자열")
                            JSONObject json = new JSONObject(builder.toString());
                            binding.name.setText(json.getString("name"));
                            binding.age.setText(json.getString("age"));
                            //binding.hobbies.setText(json.getString("hobbies"));
                            //binding.users.setText(json.getString("users"));
                            JSONArray array=json.getJSONArray("hobbies");
                            binding.hobbies.setText(array.toString());
                            JSONObject users=json.getJSONObject("users");
                            binding.users.setText(String.format("아이디:%s,비번:%s",users.getString("username"),users.getString("password")));
                            //리사이클러뷰 숨기기
                            if(binding.recyclerView.getVisibility()==View.VISIBLE){
                                binding.recyclerView.setVisibility(View.GONE);
                            }

                            //카드뷰 보이기
                            binding.oneUser.setVisibility(View.VISIBLE);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }


                    });
                }
                catch(Exception e){e.printStackTrace();}

            }
        }).start();
    }
    public void jsonArrayParse(View v){

        new Thread(new Runnable() {
            StringBuilder builder;
            @Override
            public void run() {
                try {
                    //1]raw폴더에 있는 json.txt파일 읽기
                    InputStream is = getResources().openRawResource(R.raw.json_array);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is,"UTF-8"));
                    String data;
                    builder= new StringBuilder();
                    while((data=br.readLine())!=null){
                        builder.append(data);
                    }

                    runOnUiThread(()->{
                        try {
                            //여기서는 UI변경 가능
                            //2]읽어온 문자열 데이타를 JSON으로 파싱해서 리스트뷰의 아이테뷰에 데이타 바인딩(문자열->JSONArray타입으로 변환)
                            //new JSONArray("JSON형식의 문자열")
                            List<UsersItem> items = new Vector<>();
                            //문자열이 자스 배열이면 즉 [] 혹은 [{},{},{}...]형태면 new JSONArray(문자열)
                            JSONArray json = new JSONArray(builder.toString());
                            for(int i=0;i < json.length();i++) {
                                JSONObject ele = json.getJSONObject(i);

                                String name = ele.getString("name");
                                String age = ele.getString("age");
                                String hobbies = ele.getString("hobbies");
                                JSONObject user = ele.getJSONObject("users");
                                String users = String.format("아이디:%s,비번:%s", user.getString("username"), user.getString("password"));
                                items.add(new UsersItem(name,age,hobbies,users));
                            }
                            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(MainActivity.this,items);
                            binding.recyclerView.setAdapter(adapter);
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            //하나인 카드뷰 숨기기
                            if(binding.oneUser.getVisibility()==View.VISIBLE){
                                binding.oneUser.setVisibility(View.GONE);
                            }
                            //리사클러 뷰 보이기
                            binding.recyclerView.setVisibility(View.VISIBLE);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }

                    });
                }
                catch(Exception e){e.printStackTrace();}

            }
        }).start();
    }
}