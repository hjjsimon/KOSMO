package com.kosmo.listview30_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kosmo.listview30_2.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //더미 데이타 준비
        List<Item> items = new Vector<>();
        for(int i=1;i<=50;i++){
            items.add(new Item("한소인"+i,"개발부"+i,"2023-07-28",R.drawable.rounded));

        }
        //어댑터 생성
        MyCustomAdapter adapter = new MyCustomAdapter(this,items);
        //어댑터 연결
        binding.mylistView.setAdapter(adapter);

        //리스트 뷰에 리스너 부착: MainActivity 혹은 어댑터에서 이벤트 처리 가능
        /*
        binding.mylistView.setOnItemClickListener((adapterView,view,position,id)->{

            Toast.makeText(MainActivity.this,items.get(position).getItemName() , Toast.LENGTH_SHORT).show();
        });*/

    }
}