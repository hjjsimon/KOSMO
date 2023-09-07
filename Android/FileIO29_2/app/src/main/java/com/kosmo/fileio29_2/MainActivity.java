package com.kosmo.fileio29_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    //메모리 저장용 멤버 변수
    private Map<String,String> memory= new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("shared",MODE_PRIVATE);
    }
    public void saveInMemory(View v){
        //메모리(컬렉션-ram)에 데이타 저장
        memory.put("memory","메모리에 데이타 저장");
    }
    public void readInMemory(View v){
        //앱의 메모리(변수)에 저장된 데이타는 onDestory()시 삭제된다
        //즉 앱을 위로 드래그시 정상종료(onDestory()까지 호출됨)
        //시 데이타 가 사라진다
        if(!memory.containsKey("memory")){
            Toast.makeText(this, "먼저 메모리저장 버튼을 클릭하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, memory.get("memory"), Toast.LENGTH_SHORT).show();
    }
    public void saveInFile(View v) throws IOException {
        //내장 메모리에 파일 생성후 데이타 쓰기]
        //위치:/data/data/패키지명/files
        FileOutputStream fos=openFileOutput("inner",MODE_PRIVATE);
        fos.write("내장 메모리 파일에 저장한 데이타\r\n앱 삭제시나 데이타 삭제시에만 삭제되요".getBytes());
        fos.close();
    }
    public void readInFile(View v)  {
        try {
            FileInputStream fis = openFileInput("inner");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            Toast.makeText(this, new String(bytes), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, "파일이 존재하지 않아요", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveInShared(View v){
        //파일 IO 사용 안하고 내장 메모리의 shred_prefs폴더에
        //shared.xml파일이 생성됨.
        //위치:/data/data/패키지명/shared_prefs
        preferences.edit().putString("kosmo","shared.xml에 저장한 데이타").commit();
    }

    public void readInShared(View v){
        Toast.makeText(this, preferences.getString("kosmo","키가 존재하지 않아요"), Toast.LENGTH_SHORT).show();
    }
}