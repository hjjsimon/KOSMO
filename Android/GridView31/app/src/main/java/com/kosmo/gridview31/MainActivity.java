package com.kosmo.gridview31;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kosmo.gridview31.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //이미지 리소스 아이디 배열]
    private int[] resIds={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
    //영화 제목]
    private String[] movies={"조커","보통의 연예","제미니",
            "퍼펙트맨","소피와 드래곤","장사리",
            "세계를 찾아서","벌새","판소리 복서"};
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //어댑터 생성
        MyCustomAdapter adapter = new MyCustomAdapter(this,movies,resIds);
        //연결]그리드뷰.setAdapter()
        binding.gridview.setAdapter(adapter);

        //방법1)MainActivity에서 이벤트 처리
        /*
        View dialogView = View.inflate(this,R.layout.dialog_layout,null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_camera)
                .setView(dialogView)
                .create();
        dialogView.findViewById(R.id.close).setOnClickListener(v->{
            dialog.dismiss();
        });
        //그리드뷰에 리스너 부착-setOnItemClickListener
        binding.gridview.setOnItemClickListener((adapterView,view,position,id)->{
            if(!dialog.isShowing()){
                ((ImageView)(dialogView.findViewById(R.id.bigPoster))).setImageResource(resIds[position]);
                dialog.setTitle(movies[position]);
                dialog.show();
            }

        });
        */
    }/////////////////onCreate
}