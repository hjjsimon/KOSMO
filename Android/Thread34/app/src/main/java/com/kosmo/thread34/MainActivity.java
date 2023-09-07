package com.kosmo.thread34;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.kosmo.thread34.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int mainNumber,threadNumber;
    private WorkingThread workingThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*
        작업 내용:
        버튼 클릭시마다 mainNumber는  1씩 증가시키고
        threadNumber는 스레드내에서 시간 간격(0.5초마다)을 두고 자동으로 1씩 증가 시키자
        UI스레드(Main스레드)인 MainActivity의 텍스트뷰에 그 값을 출력한다
        작업 결과:
        버튼을 클릭할때마다  mainNumber는 1씩 증가하고
        threadNumber도 0.5초마다 1씩 증가한다.
        */
    }/////////////onCreate

    public void error(View v){
        mainNumber++;
        binding.tvMainNumber.setText(String.valueOf(mainNumber));

        workingThread=new WorkingThread();
        workingThread.start();

    }
    //[방법1]
     /*
      스레드 클래스(작업스레드)의 run()메소드안에서
      runOnUiThread()메소드 호출
      runOnUiThread()메소드안에서 위젯의 UI변경
     */

    public void runonui(View v){
        mainNumber++;
        binding.tvMainNumber.setText(String.valueOf(mainNumber));
        workingThread=new WorkingThread();
        workingThread.start();
    }
    //[방법2]
    /*
        스레드 클래스(작업스레드)의 run()메소드안에서
        위젯.post()메소드 호출
        위젯.post()메소드안에서 위젯의 UI변경
    */
    public void widgetpost(View v){
        mainNumber++;
        binding.tvMainNumber.setText(String.valueOf(mainNumber));
        workingThread=new WorkingThread();
        workingThread.start();
    }
    public void stop(View v){
        if(workingThread !=null && workingThread.isAlive()){
            workingThread.interrupt();
            workingThread=null;
        }
    }
    //Thread를 상속받은 작업스레드(Working Thread)
    //https://developer.android.com/guide/components/processes-and-threads?hl=ko
    private class WorkingThread extends  Thread{
        @Override
        public void run() {
            try {
                while (true) {
                    threadNumber++;
                    sleep(500);
                    //binding.tvThreadNumber.setText(String.valueOf(threadNumber));//CalledFromWrongThreadException: Only the original thread that
                    //created a view hierarchy can touch its views.
                    //방법1]
                    /*
                    runOnUiThread(()->{
                        //메인스레드에 부착된 위젯의 UI변경
                        binding.tvThreadNumber.setText(String.valueOf(threadNumber));
                    });*/
                    //방법2]
                    binding.tvThreadNumber.post(()->{
                        //메인스레드에 부착된 위젯의 UI변경
                        binding.tvThreadNumber.setText(String.valueOf(threadNumber));
                    });
                }
            }
            catch(InterruptedException e){e.printStackTrace();}
        }
    }////////////////
}