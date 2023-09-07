package com.kosmo.intent27_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.kosmo.intent27_3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //버튼에 이벤트 리스너 부착
        binding.btndial.setOnClickListener(handler);
        binding.btnmap.setOnClickListener(handler);
        binding.btnsearch.setOnClickListener(handler);
        binding.btnsms.setOnClickListener(handler);
        binding.btnweb.setOnClickListener(handler);

    }///////////////
    private View.OnClickListener handler=v->{
        //암시적 인텐트: 특정 액티비티 클래스를 지정하지 않는다.
        //new Intent(액티비티 액션,Uri객체)
        if(v.getId()==R.id.btndial){
            //[전화걸기]
            //액티비티 액션:ACTION_DIAL
            //uri- tel:01012345678
            //tel은 반드시 소문자로
            //※에뮬레이터(TARGET 23) 즉 마쉬멜로에서는 오류:실제 핸폰에서 동작
            //http://developer.android.com/intl/ko/training/permissions/requesting.htm
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1234-5678"));
            startActivity(intent);
        }
        else if(v.getId()==R.id.btnweb){
            //[브라우저 실행]
            //액티비티 액션:ACTION_VIEW
            //uri- http나 https로 시작하는 URL지정
            //http나 https도 반드시 소문자로
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
            startActivity(intent);
        }
        else if(v.getId()==R.id.btnmap){
            //[구글맵]
            //액션:ACTION_VIEW
            //www.google.co.kr에서 구글지도 검색->
            //구글지도(https://maps.google.co.kr/)클릭->
            //해당 지역 클릭후 URL주소창 복사
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.kr/maps/?hl=ko&entry=ttu"));
            //startActivity(intent);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5202,127.1273"));
            startActivity(intent);
        }
        else if(v.getId()==R.id.btnsearch){
            //[구글 검색하기]
            //액티비티 액션:ACTION_WEB_SEARCH
            //putExtra(SearchManager.QUERY,"검색할 단어");
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY,"안드로이드");
            startActivity(intent);
        }
        else{
            //[SMS문자 보내기]
            //액티비티 액션:ACTION_SENDTO
            //uri- smsto로 시작하는 URL지정
            //실제 핸드폰으로 테스트해야 문자가 보내진다.
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-1234-5678"));
            startActivity(intent);
        }

    };
}