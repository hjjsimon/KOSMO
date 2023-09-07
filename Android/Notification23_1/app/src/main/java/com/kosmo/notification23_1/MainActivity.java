package com.kosmo.notification23_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kosmo.notification23_1.databinding.ActivityMainBinding;
import com.kosmo.notification23_1.databinding.CustomLayoutBinding;

public class MainActivity extends AppCompatActivity {

     /*
        목록형  혹은 라디오버튼형 혹은 체크박스형태는
        대화상자를 띄울때는 setMessage()대신
        목록형:setItems()
        라디오버튼형:setSingleChoiceItems()메소드 사용
        체크박스형:setMultiChoiceItems()
        (왜냐하면 setMessage()와 동시 사용시 setMessage()가 적용되니까)
    */

    private ActivityMainBinding binding;
    private CustomLayoutBinding customLayoutBinding;
    private int which_radio=-1;
    private boolean[] which_checks=new boolean[]{false,false,false,true};
    private AlertDialog progressDialog;
    private AlertDialog customDialog;

    //안드로이드에서 제공하는 CountDownTimer클래스 사용(로그인 창 닫기)
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        customLayoutBinding = CustomLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //안드로이드에서 제공하는 CountDownTimer클래스 사용
        //첫번째 인자:1000분의 1초단위. 지정한 시간 후에 onFinish()가 호출됨
        //두번째 인자:1000분의 1초단위. 지정한 간격시간마다 onTick()이 호출됨
        countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
                Log.i("notification","1초마다 호출됨");
            }
            //CountDownTimer가 위에서 지정한 3초후에 자동으로 stop될때 호출되는 메소드
            @Override
            public void onFinish() {
                Log.i("notification","3초후 호출됨");
                if(progressDialog !=null) progressDialog.dismiss();
            }
        };

        //커스텀 대화상자 생성
        customDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_lock_power_off)
                .setTitle("사용자 정의 대화상자")
                .setView(customLayoutBinding.getRoot())//커스텀뷰 위젯 이벤트 처리시
                //.setView(R.layout.custom_layout)//이벤트 처리 하지 않을때 그대로 뷰만 보여줄때
                                        .create();

        //커스텀 뷰에 있는 위젯들의 이벤트 처리
        customLayoutBinding.btnOk.setOnClickListener(v->{
            String sport=customLayoutBinding.editText.getText().toString();
            Toast.makeText(v.getContext(), "당신이 좋아하는 스포츠:"+sport, Toast.LENGTH_SHORT).show();
            //다이얼로그 창 닫기
            customDialog.dismiss();
        });

        //기본 대화상자 띄우기]
        binding.btnAlertBasic.setOnClickListener(v->{
            new AlertDialog.Builder(v.getContext())
                    .setCancelable(false)//기본값 true
                    .setIcon(android.R.drawable.ic_dialog_email)
                    .setTitle("올레 서비스")
                    .setMessage("올레 서비스에 가입 하시겠습니까?")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            //which는 무조건 -1이다
                            Toast.makeText(MainActivity.this, "가입절차를 진행하겠습니다", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setNegativeButton("아니오",null)
                    .show();

        });
        //목록형 대화상자 띄우기]
        String[] sports= getResources().getStringArray(R.array.sports);

        binding.btnAlertItems.setOnClickListener(v->{
            new AlertDialog.Builder(v.getContext())
                    .setCancelable(false)//기본값 true
                    .setIcon(android.R.drawable.ic_dialog_map)
                    .setItems(sports, new DialogInterface.OnClickListener() {//목록형이다
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            //which:선택한 아이템의 인덱스
                            Toast.makeText(MainActivity.this, "당신이 좋아하는 스포츠는 "+sports[which]+"이군요", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setTitle("당신이 좋아하는 스포츠는?")
                    //.setMessage("Which sports Do you like?")//setItems()보다 우선순위가 높다
                    //.setPositiveButton("예", (DialogInterface dialogInterface, int which)->{})#의미가 없다
                    .setNegativeButton("아니오",null)
                    .show();

        });
        //라디오형 대화상자-하나만 선택 가능
        binding.btnAlertRadio.setOnClickListener(v->{
            new AlertDialog.Builder(v.getContext())
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setTitle("당신이 좋아하는 스포츠는?")
                    //두번째 인자(checkedItem)는 체크된 상태로 보여줄 아이템의 인덱스값
                    //-1이면 아무것도 선택이 안된 상태
                    .setSingleChoiceItems(sports, -1, (dialogInterface,which)-> {//라디오형
                        //which:선택한 아이템의 인덱스
                        which_radio = which;
                    })
                    .setPositiveButton("예",(dialogInterface,which)-> {
                        //which:무조건 -1
                        if(which_radio == -1){
                            Toast.makeText(v.getContext(), "스포츠 종목을 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(v.getContext(), "You like "+sports[which_radio], Toast.LENGTH_SHORT).show();


                    })
                    .setNegativeButton("아니오",null).show();

        });
        //체크박스형 대화상자-여러개 선택 가능]
        binding.btnAlertCheck.setOnClickListener(v->{
            new AlertDialog.Builder(v.getContext())
                    .setIcon(android.R.drawable.ic_dialog_dialer)
                    .setTitle("당신이 좋아하는 스포츠는?")
                    //new boolean[]-두번째 인자는 선택된 상태로 보여줄 boolean형 배열
                    .setMultiChoiceItems(sports, which_checks, (dialogInterface,which,isChecked)-> {//체크박스형
                        //which는 선택한 아이템의 인덱스
                        //isChecked: 선택여부
                        which_checks[which]=isChecked;

                    })
                    .setPositiveButton("확인",(dialogInterface,which)->{
                        String checked="";
                        for(int i=0;i<which_checks.length;i++)
                            if(which_checks[i]) checked+=sports[i]+ " ";
                        Toast.makeText(v.getContext(), "You like "+checked, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("취소",null).show();
        });
        //진행대화상자 띄우기]
         /*
        ※setView메소드 사용시 주의점
         AlertDialog.Builder객체.setView(R.layout.리소스 아이디)시에는
         Set a custom view resource to be the contents of the Dialog.
         The resource will be inflated, adding all top-level views to the screen
         즉 계속 생성해도 된다
         아래처럼 View를 전개해서 계속 생성시에는
         View view=View.inflate(컨텍스트,R.layout.리소스 아이디,null);

         AlertDialog.Builder객체.setView(view);
         아래 에러는 API수준 33에서는 에러 안남.
         java.lang.IllegalStateException: The specified child already has a parent.
         You must call removeView() on the child's parent first.에러

        해결법:
        방법1. AlertDialog를 한번만 생성한다(null체크해서)
        방법2. 버튼 클릭시마다 생성시에는
           Dialog객체.setOnDismissListener((dialog)->{
                ((ViewGroup)전개한View객체.getParent()).removeView(전개한View객체);
            });
         */
        binding.btnProgress.setOnClickListener(v->{

            //View view=getLayoutInflater().inflate(R.layout.progress_layout,null);//액티비티안에서 주로 사용
            View view=View.inflate(MainActivity.this,R.layout.progress_layout,null);//액티비티가 아닌곳에서 사용
            //1.빌더로 AlertDialog생성
            progressDialog = new AlertDialog.Builder(v.getContext())
                    //.setCancelable(false)
                    .setIcon(android.R.drawable.ic_input_delete)
                    .setTitle("로그인")
                    .setView(view)
                    //.setView(R.layout.progress_layout)
                    //.show()
                    //혹은
                    .create();
            //2.AlertDialog의 show()로 보이기
            progressDialog.show();
            //타이머 시작
            countDownTimer.start();
        });
        //커스텀 대화상자]
        binding.btnCustom.setOnClickListener(v->{
            if(customDialog !=null && !customDialog.isShowing())
                customDialog.show();
        });
    }//////////////onCreate


}/////////////////class