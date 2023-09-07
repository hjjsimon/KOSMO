package com.kosmo.bottomnavigationview24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kosmo.bottomnavigationview24.databinding.ActivityMainBinding;
import com.kosmo.bottomnavigationview24.view.Content1;
import com.kosmo.bottomnavigationview24.view.Content2;
import com.kosmo.bottomnavigationview24.view.Content3;

import java.util.List;

/*
  ※액티비티는 스택에  쌓이지만 프래그먼트는 쌓이지 않는다
   즉  프래그먼트로 화면 전환시  백 버튼을 누르면 이전 액티비티로   전환이 된다
   다시말해 이전 프래그먼트가 보이지 않는다

   -백 버튼 클릭시 이전 프래그먼트 보이기
      addToBackStack(String 식별자  혹은 null)는 해당 프래그먼트를 스택에 쌓는다
      그래서 백 버튼 클릭시 이전 프래그먼트로 전환할 수 있다

     식별자 : 백스택에 넣을때 이름(식별자)으로 popBackStack(식별자,int Flag)메소드로 스택에서 제거할때 사용한다

    popBackStack(식별자,int Flag)
    식별자 : 제거할 프래그먼트의 식별자
    Flag :  0을 주면 해당 식별자인 프래그먼트만 스택에서 제거
            POP_BACK_STACK_INCLUSIVES는 해당 식별자의 프래그먼트와 그 위에 쌓인 프래그먼트까지 모두 제거

   여기서
   이때 두번째 인자로
   0을 주면 해당 식별자인 프래그먼트만 스택에서 제거되고
   POP_BAKC_STACK_INCLUSIVE는 해당 식별자의 프래그먼트와 그 위에 쌓인 프래그먼트까지 모두 제거된다

   FragmentManager의 프래그먼트 제어 메소드
    add() : 액티비티에 프래그먼트를 중첩해서 부착
    remove() : 액티비티에 붙여진 가장 최상위 프래그먼트 제거
    replace() : 기존에 액티비티에 쌓여있던 프래그먼트들을 모두 제거하고 해당 프래그먼트를 추가


   ※ replace().commit()는  스택에 쌓이지 않는다
     replace().addToBackStack().commit()는
    스택에 해당 프래그먼트가 쌓인다
    즉 프래그먼트로 화면전환후 백버튼 클릭시 이전 프래그먼트로 전환하려면
    replace().addToBackStack().commit()를 사용하자
  */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationView;

    //백버튼 클릭시 바툼 네비게이션의 이전 메뉴 아이템 활성화용
    private int currentSelectedItemId=R.id.home_menu;
    private int beforeSelectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //프래그먼트 생성
        Content1 content1 = new Content1();
        Content2 content2 = new Content2();
        Content3 content3 = new Content3();


        //[뷰페이저 미 사용시]
        //1. 앱실행시 첫번째 프래그먼트를 화면에 표시
        //getSupportFragmentManager().beginTransaction().replace(binding.containers.getId(),content1).addToBackStack("FIRST").commit();


        //2. BottomNavigationView의 클릭 이벤트에 따라 프래그먼트(화면)을 교체
        binding.bottomNavigation.setOnItemSelectedListener((item)->{

            //현재 메뉴아이템 아이디를 이전 메뉴 아이템 아이디로 설정
            beforeSelectedItemId=currentSelectedItemId;
            //클릭한 메뉴아이템 아이디를 현재 메뉴아이템 아이디로 설정
            currentSelectedItemId=item.getItemId();


            //addToBackStack()호출로 스택에  프래그먼트는 계속 쌓인다
            //이전 프래그먼트를 스택에서 제거하자
            //프래그먼트매니저객체.popBackStack(String 식별자,FragmentManager.POP_BACK_STACK_INCLUSIVE)
            getSupportFragmentManager().popBackStack(getStackIdentifier(beforeSelectedItemId), FragmentManager.POP_BACK_STACK_INCLUSIVE);


            if(item.getItemId()==R.id.home_menu){

                getSupportFragmentManager().beginTransaction().replace(binding.containers.getId(),content1).addToBackStack("FIRST").commit();

            }
            else if(item.getItemId()==R.id.setting_menu){

                getSupportFragmentManager().beginTransaction().replace(binding.containers.getId(),content2).addToBackStack("SECOND").commit();

            }
            else{
                getSupportFragmentManager().beginTransaction().replace(binding.containers.getId(),content3).addToBackStack("THIRD").commit();
            }

            return true;//true반환해야 메뉴 아이템이 활성화 된다.false반환시는 메뉴 아이템이 활성화가 안된다
        });

    }//////////////onCreate


    //메뉴 아이템의 아이디로 스택에 쌓인 프래그먼트의 식별자명 반환 메소드
    private String getStackIdentifier(int menuItemId){
        if(menuItemId==R.id.home_menu) return "FIRST";
        else if(menuItemId==R.id.setting_menu) return "SECOND";
        else return "THIRD";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("bottom","액티비티 onDestroy");
    }

    //백 버튼 클릭시 호출되는 콜백 메소드
    @Override
    public void onBackPressed() {
        Log.i("bottom","백버튼 클릭이벤트 발생");
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Log.i("bottom","프래그먼트 갯수:"+fragments.size());
        Log.i("bottom","프래그먼트 보이는 여부:"+fragments.get(0).isVisible());

        if(currentSelectedItemId==R.id.home_menu) {//현재 아이템 아이디가 홈이라면 종료하기위한 UI표시
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_delete)
                    .setMessage("앱을 종료하시겠습니다?")
                    .setPositiveButton("예", (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton("아니오", null).show();
            return;

        }
        //백버튼 클릭시 프래그먼트간 화면전환은 이루어지나 메뉴 아이템이 선택이 안된다
        //super.onBackPressed();//주석처리시 백버튼 기능이 안된다
        //백버튼 기능을 아래 메소드로 구현(메뉴 아이템 선택도 구현된다)
        //binding.bottomNavigation.setSelectedItemId(이전 메뉴아이템의 아이디);
        binding.bottomNavigation.setSelectedItemId(beforeSelectedItemId);
    }

    /*
    ※프래그먼트에는  onBackPressed()를 오버라이딩 할 수 없다
    프래그먼트 화면에서 onBackPressed() 효과 구현


    프래그먼트 화면에서 백버튼 클릭시 프래그먼트가 부착된 액티비티에 백버튼 이벤트를 아래와 같이 전달 할 수 있다

    전제조건:액티비티의 super.onBackPressed();가 실행되어야 한다(즉 오버라이딩 안해도 된다)


    프래그먼트에서 아래 코딩
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("bottom","Content2 onAttach");
        this.context=context;
        //OnBackPressedDispatcher 객체로 프래그먼트의 백버튼 클릭을 액티비티로 전달
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }
    //종료처리를 위한 OnBackPressedCallback객체 생성 및 handleOnBackPressed()오버라이딩으로 백버튼 클릭이벤트 처리
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("앱을 종료 하시겠습니까?")
        .setPositiveButton("예", (dialog, which) -> requireActivity().finish())
        .setNegativeButton("아니오", null)
        .show();
        }
    };
    */

}/////////