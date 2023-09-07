package com.kosmo.optionmenu21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {


    private ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout= findViewById(R.id.container);
    }
    //콜백 메소드:옵션 메뉴(xml전개 혹은 자바코드(메뉴용 XML불필요))를 생성하기 위한 메소드(옵션메뉴 표시용)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        //res폴더 선택->New->Android Resource Directory-> Resource Type을 menu선택
        //옵션메뉴 표시시 테마를 Theme.Material3.DayNight로 변경
        //getMenuInflater().inflate(R.menu.option_menu,menu);


        //방법2]자바코드만로 옵션 메뉴 생성(메뉴용 xml파일 불필요)
        /*
        add(int groupId,int itemId,int order,CharSequence title)메소드로 메뉴 추가
		인자 설명]
		groupId:그룹아이디로 그룹에 포함 되않은 경우는 Menu.NONE이나 0설정
		itemId:메뉴 아이템의 아이디,필요 없을 경우 Menu.NONE 이나 0
		order:메뉴 아이템의 순서.순서를 지정하고 싶지 않으면 Menu.NONE 이나 0
		title:메뉴명
         */
        menu.add(Menu.NONE,101,1,"Opt Menu1").setIcon(R.drawable.home).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(Menu.NONE,102,2,"Opt Menu2").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(Menu.NONE,103,3,"Opt Menu3").setIcon(R.drawable.contact).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(Menu.NONE,104,4,"Opt Menu4");
        menu.add(Menu.NONE,105,5,"Opt Menu5");
        menu.add(Menu.NONE,106,6,"Opt Menu6");

        SubMenu subMenu=menu.addSubMenu(Menu.NONE,107,7,"서브메뉴");
        subMenu.add(Menu.NONE,1,1,"Sub Menu1");
        subMenu.add(Menu.NONE,2,2,"Sub Menu2");
        subMenu.add(Menu.NONE,3,3,"Sub Menu3");
        return super.onCreateOptionsMenu(menu);
    }


    //콜백메소드:옵션 메뉴 아이템 이벤트 처리용 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId()==101 || item.getItemId()==102 || item.getItemId()==103 || item.getItemId()==R.id.opt_menu1 || item.getItemId()==R.id.opt_menu2 ||item.getItemId()==R.id.opt_menu3){

           Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
       }
       else if(item.getItemId()==R.id.item_red || item.getItemId()==104){
            layout.setBackgroundColor(Color.RED);
       }
       else if(item.getItemId()==R.id.item_green || item.getItemId()==105){
           layout.setBackgroundColor(Color.GREEN);
       }
       else if(item.getItemId()==R.id.item_blue || item.getItemId()==106){
           layout.setBackgroundColor(Color.BLUE);
       }
       return super.onOptionsItemSelected(item);
    }
}