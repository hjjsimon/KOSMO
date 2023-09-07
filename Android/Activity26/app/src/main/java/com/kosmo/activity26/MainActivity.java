package com.kosmo.activity26;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kosmo.activity26.databinding.ActivityMainBinding;
/*
    onCreate():
      액티비티가 처음 생성될 때 호출된다.
      시스템에 의해 호출되며 인자로 받은 Bundle은 null
      액티비티가 실행된 적이 있는데, 어떤 이유로 종료된 후 자동으로 재시작되면,
      종료될 때 호출된 onSaveInstanceState()메소드에서 저장한 내용과 동일한 Bundle을 넘겨준다.
      예]디바이스가 회전되어 가로/세로 전환 등 리소스를 새롭게 갱신되어야 할 때 호출
      여기서는 주로 위젯을 얻거나 화면과 관련된 설정등을 한다

    onStart():
       액티비티가 생성된 후, 화면이 전면으로 나타나기 시작할때 호출된다
       동일 앱안에서 화면전환시나 다른 앱으로으로 전환시
       onStop()상태(백그라운드)로 갔다가 다시 전면으로 나올때도
       호출된다

    onRestart():
      액티비티가 onStop()상태되었다가 다시 실행될 때 호출된다
    onStop():
      HOME버튼 혹은 백 버튼 을 눌렀을 때와 같은 앱안에서 화면전환시나
      다른 앱으로으로 전환시 호출된다
      즉 액티비티가 백그라운드에 위치하게 된다

    onResume():
      액티비티가 전면에 나타날 때 즉 사용자와 상호작용하기 직전상태일때 호출된다.
    onPause():
      onStop(), onDestroy()가 호출되기 이전에 호출된다.
      액티비티가 화면에서 가려지는 경우에 호출된다.

      ※일반적으로 onResume()에서 했던 작업을 onPause()에서 정리한다
        예] onResume()에서 쓰레드를 실행시켰으면,
           onPause()가 호출될 때, 아직 쓰레드가 실행중이면 쓰레드를 중지시킨다
           onPause()가 호출되서 앱(또는 액티비티)이 일시 정지된 상태라면
           안드로이드 시스템에서 필요에 따라 완전이 죽일 수 있기 때문에
           그 이후의 작업을 못할 수도 있다는 점을 유의해야한다.

    onSaveInstanceState():
      onPause()이후에 호출된다 즉
      액티비티가 전면에서 백그라운드로 숨는 경우 호출된다.
      현재의 액티비티 상태를 저장하려면 이 메서드를 구현한다.
      예]액티비티 전환시나 앱 전환시 혹은 home버튼 혹은 백 버튼 클릭시
        즉 onPause()부터 onStop()상태로 이를때나(정상적인 상태)
        onPause()로 가서 메모리 부족으로 인해 안드로이드 시스템에
        의해 액티비티가 강제 종료시에 호출됨
    onRestoreInstanceState():
      ※onSaveInstanceState()에 의해서 저장된 상태가 있을때 호출된다
      onSaveInstanceState() 함수에서 저장했던 내용은 onCreate()에서
      Bundle 인스턴스로 넘겨받는데, onRestoreInstanceState()에서도 같은 내용을
      받을 수 있다.
      강제 종료(안드로이드 시스템에 의한 종료)후 복귀시 호출됨.
      ※단, 정상적인 종료시에는 호출 안된다.
      즉 finish()메소드(정상종료)로 액티비티를 종료시
         호출 안됨

    onDestroy():
      액티비티가 종료되기 전 호출된다.
      액티비티 내부에서 finish()를 실행하면 호출된다.
      시스템 메모리가 부족하면, 안드로이드가 강제로 죽일 때도 호출이 되는데,
      메모리 확보가 매우 시급할 때는 호출조차 되지 않는 경우도 있다.

    ※onPuase()이후를 killable상태라 한다. 즉 언제든지
     종료될수 있다는 의미(정상적이든 강제 종료(메모리 부족에 의한)든)
*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    public static final String TAG="com.kosmo.activity";
    //[액티비티의 생명주기에 해당하는 콜백 메소드 오버라이딩]
    //1]onCreate: UI(뷰)초기화
    /*
    onCreate()의 매개변수인 savedInstanceState는
    강제 종료후에 다시 앱이 재시작시에만
    안드로이드 시스템이 Bundle 객체를 생성해서 매개변수로 전달해 준다
    즉 정상 종료후 다시 앱을 실행할때는 Bundle 객체는 null 이다

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(TAG,"MainActivity의 onCreate() invoked");
        Log.i(TAG,"MainActivity의 onCreate():savedInstanceState-"+savedInstanceState);
        //버튼의 리스너 부착
        binding.btnActivityChange.setOnClickListener(this);
        binding.btnAppChange.setOnClickListener(this);
        binding.btnDataDelete.setOnClickListener(this);
        binding.btnDestroy.setOnClickListener(this);
        binding.btnDataRead.setOnClickListener(this);
        binding.btnDataSave.setOnClickListener(this);
        binding.btnModeChange.setOnClickListener(this);

    }//////////////onCreate
    //2]
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"MainActivity의 onStart() invoked");
    }
    //3]
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"MainActivity의 onResume() invoked");
    }

    //4]다른 액티비티나 다른 앱으로 전환시 이후
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"MainActivity의 onPause() invoked");
    }
    //5]
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"MainActivity의 onStop() invoked");
    }
    //6]
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"MainActivity의 onDestroy() invoked");
    }
    //5-1]
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"MainActivity의 onRestart() invoked");
    }
    /*
        ※액티비티 라이프 사이클에 속하지는 않지만
         액티비티 생명주기와 관련된 메소드(즉 반드시 호출되는 메소드가 아니다)
         화면전환 전후 인스턴스의 상태를 저장하는데  사용되는 콜백 메소드]
        onPause()다음에 호출되는 콜백 메소드]
     -호출되는 경우
        1.현재 액티비티가 onStop()까지만 갈때(onPause ->onStop->onDestroy까지 갈때 제외)
        예] 다른 화면(액티비티)이나 앱으로 전환시
           혹은 HOME버튼  혹은 백버튼 클릭시(즉 onStop까지만 간다)
        2. 강제종료되는 경우(onDestroy까지 갈수도 있다):

     -호출 안되는 경우
        정상적으로 onPause부터 쭈욱 onDestroy까지 갈때
        예]finish()호출시(액티비티 종료시)

    ※반드시 매개변수 하나짜리 오버라이딩.*/

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"MainActivity의 onSaveInstanceState() invoked");
    }
    /*onResume()전에 호출되는 콜백 메소드]
        -복구할 데이타가 있을때  호출된다
        -호출되는 경우
           강제종료후 앱을 다시 시작할때
           예] 디바이스의 화면회전(가로모드/세로모드)이 발생할 때나,
               프로세스 킬러 앱을 이용하여 강제종료 후 재시작 할 때 발생.
        -호출안되는 경우
          정상종료후 앱 다시 시작하거나
          현재 액티비티가 onStop()으로 간후 앱 다시 시작할때

    ※뷰의 상태(예를 들어 토글의 ON/OFF상태 변경후 강제 종료된 경우)를 복원하고자 한다면 굳이
    아래 메소드를 오버라이딩 할 필요없다.
    안드로이드 OS에서 강제종료후 알아서 이전 상태로 복원시켜준다.
    즉 onSaveInstanceState에서 저장한 데이타는
    onCreate메소드의 인자인 savedInstanceState를 널 체크한후 복원하자
    */

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //데이터 복원하기 위해서 null체크 할 필요 없다.
        //무조건 Bundle타입의 인스턴스를 생성해 전달해주니까
        Log.i(TAG,"MainActivity의 onRestoreInstanceState() invoked:"+savedInstanceState);
    }

    //버튼들의 이벤트 처리
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAppChange){
            //다른 앱으로 전환]
            //※onPause()->onStop()->onSaveInstanceState(Bundle outState)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01012345678")));
        }
        else if(view.getId()==R.id.btnActivityChange){
            // 같은 앱 안에서 화면(액티비티) 전환]
            //※onPause()->onStop()->onSaveInstanceState(Bundle outState)
            startActivity(new Intent(this,AnotherActivity.class));
        }
    }
}