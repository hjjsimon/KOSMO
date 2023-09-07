package com.kosmo.contextmenu22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private LinearLayout layout;
    private ImageView menu1,menu2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        layout= findViewById(R.id.layout);
        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);

        //선행작업:위젯(뷰) 롱 클릭시 컨텍스트 메뉴가  뜨도록 설정]
        //registerForContextMenu(layout);
        registerForContextMenu(menu1);
        registerForContextMenu(menu2);

    }

    //켄텍스트 메뉴 표시용 콜백 메소드
    @Override
    //View view : 롱 클릭이벤트가 발생한 뷰
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /*
        ※모든 위젯에 공통 컨텍스트 메뉴 적용하려면
        위젯을 배치한 레이아웃에만 컨텍스트 메뉴 적용
        각각의 위젯에 서로 다른 컨텍스트 메뉴 적용하려면
        각각의 위젯에 컨텍스트 메뉴 등록(단, 레이아웃에는 미 적용)
         */

        MenuInflater menuInflater=getMenuInflater();
        //1.모든 위젯에 공통 컨텍스트 메뉴 적용-이때는 모든 위젯에 registerForContextMenu()적용 불필요
        /*if(v.getId()==R.id.layout)
            menuInflater.inflate(R.menu.context_common,menu);*/
        //2. 각각의 위젯에 서로 다른 컨텍스트 메뉴 적용
        if(v.getId()==R.id.menu1)
            menuInflater.inflate(R.menu.context_menu1,menu);
        else if(v.getId()==R.id.menu2)
            menuInflater.inflate(R.menu.context_menu2,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //컨텍스트 메뉴 아이템 처리용 콜백 메소드
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(R.id.home==item.getItemId())
            Toast.makeText(this, "홈 메뉴", Toast.LENGTH_SHORT).show();
        else if(R.id.settings==item.getItemId())
            Toast.makeText(this, "설정 메뉴", Toast.LENGTH_SHORT).show();
        else if(R.id.contact==item.getItemId())
            Toast.makeText(this, "연락처 메뉴", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}