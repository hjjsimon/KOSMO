package com.kosmo.kosmoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.kakao.sdk.auth.AuthApiClient;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import com.kakao.sdk.common.model.KakaoSdkError;
import com.kakao.sdk.user.UserApiClient;
import com.kosmo.kosmoapp.databinding.ActivityLoginBinding;
import com.kosmo.kosmoapp.login.LoginService;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //액션바 색상 변경-자바코드
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x99FF0000));
        //자바코드로 버튼 배경 투명처리
        binding.btnLogin.getBackground().setAlpha(178);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                        .setIcon(android.R.drawable.ic_lock_lock)
                                .setTitle("로그인")
                                        .setView(R.layout.progress_layout).create();
        //버튼에 리스너 부착
        binding.btnLogin.setOnClickListener(v->{
            //프로그레스 다이얼로그 띄우기
            dialog.show();
            //사용자 입력값 받기
            username = binding.id.getText().toString().trim();
            password = binding.pwd.getText().toString().trim();
            //스프링 서버로 요청 보내기
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("http://192.168.0.13:8080").build();//스프링 REST API로 회원여부 판단을 위한 요청
            LoginService service=retrofit.create(LoginService.class);
            Call<Map<String,Boolean>> call= service.isLogin(username,password);
            call.enqueue(new Callback<Map<String, Boolean>>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<Map<String, Boolean>> call, Response<Map<String, Boolean>> response) {
                    if(response.isSuccessful()){
                        Map<String, Boolean> result=response.body();
                        boolean isLogin=result.get("isMember");
                        if(isLogin){//회원
                            //컨텐츠 화면(MainActivity)으로 전환
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                            startActivity(intent);
                            //다른 화면에서 로그인 여부 판단을 위한 아이디 저장
                            SharedPreferences preferences = getSharedPreferences("usersInfo",MODE_PRIVATE);
                            preferences.edit().putString("username",username).commit();
                            preferences.edit().putString("password",password).commit();

                        }
                        else{//비회원
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setIcon(android.R.drawable.ic_lock_lock)
                                    .setTitle("로그인")
                                    .setMessage("아이디와 비번이 불일치합니다")
                                    .setPositiveButton("확인",null).show();
                        }
                    }
                    else{
                        try {
                            Log.i("com.kosmo.kosmoapp","응답에러:"+response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    SystemClock.sleep(2000);
                    dialog.dismiss();
                }
                @Override
                public void onFailure(Call<Map<String, Boolean>> call, Throwable t) {
                    dialog.dismiss();
                    t.printStackTrace();
                }
            });
        });


        binding.kakaoLogin.setOnClickListener(v->{

            //토큰 존재 여부 파악( true라도 현재 사용자가 로그인 상태임을 보장하지 않습니다.
            if(AuthApiClient.getInstance().hasToken()){
                UserApiClient.getInstance().accessTokenInfo( (token, error) ->{
                    if (error != null) {
                        if (error instanceof KakaoSdkError && ((KakaoSdkError) error).isInvalidTokenError() == true) {
                            //로그인 필요
                            kakaoLogin();
                        }
                        else {
                            //기타 에러
                            Log.i("com.kosmo.kosmoapp","기타 에러 발생");
                        }
                    }
                    else {
                        //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                        Log.i("com.kosmo.kosmoapp","이미 로그인이 되어 있습니다");
                        //컨텐츠 화면(MainActivity)으로 전환
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                        startActivity(intent);
                        //다른 화면에서 로그인 여부 판단을 위한 아이디 저장
                        SharedPreferences preferences = getSharedPreferences("usersInfo",MODE_PRIVATE);
                        preferences.edit().putString("username",username).commit();
                        preferences.edit().putString("password",password).commit();
                    }
                    return null;
                });
            }
            else{
                //로그인 필요
                kakaoLogin();
            }
        });

    }///onCreate

    private void kakaoLogin(){

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(this)) {

            UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, (oAuthToken, error) -> {
                if (error != null) {
                    Log.e("com.kosmo.kosmoapp", "로그인 실패", error);

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error instanceof ClientError && ((ClientError) error).getReason() == ClientErrorCause.Cancelled) {
                        return null;
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.getInstance().loginWithKakaoAccount(getApplicationContext(),(token,loginError)->{
                        if(loginError!=null){
                            //로그인실패
                            Log.i("com.kosmo.kosmoapp", "로그인 실패: "+loginError);
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setIcon(android.R.drawable.ic_lock_lock)
                                    .setTitle("로그인")
                                    .setMessage("카카오계정으로 로그인 실패")
                                    .setPositiveButton("확인",null).show();
                        }
                        else{
                            //로그인성공
                            //사용자정보요청
                            Log.i("com.kosmo.kosmoapp", "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                            //컨텐츠 화면(MainActivity)으로 전환
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                            startActivity(intent);
                            //다른 화면에서 로그인 여부 판단을 위한 아이디 저장
                            SharedPreferences preferences = getSharedPreferences("usersInfo",MODE_PRIVATE);
                            preferences.edit().putString("username",username).commit();
                            preferences.edit().putString("password",password).commit();

                        }
                        return null;});

                } else if (oAuthToken != null) {
                    Log.i("com.kosmo.kosmoapp", "로그인 성공(토큰) : " + oAuthToken.getAccessToken());

                    UserApiClient.getInstance().me((user, meError) -> {
                        if (meError != null) {
                            Log.e("com.example.kakaologin", "사용자 정보 요청 실패", meError);
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setIcon(android.R.drawable.ic_lock_lock)
                                    .setTitle("로그인")
                                    .setMessage("카카오계정으로 로그인 실패")
                                    .setPositiveButton("확인",null).show();
                        } else {

                            Log.i("com.kosmo.kosmoapp", "정보요청 성공(getId()):" + user.getId());
                            Log.i("com.kosmo.kosmoapp", "user.getKakaoAccount():" + user.getKakaoAccount());
                            Log.i("com.kosmo.kosmoapp", "이메일:" + user.getKakaoAccount().getEmail());
                            Log.i("com.kosmo.kosmoapp", "닉네임:" + user.getKakaoAccount().getProfile().getNickname());
                            Log.i("com.kosmo.kosmoapp", "프로필사진(640x640):" + user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                            Log.i("com.kosmo.kosmoapp", "프로필사진(110X110):" + user.getKakaoAccount().getProfile().getThumbnailImageUrl());

                            //컨텐츠 화면(MainActivity)으로 전환
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                            startActivity(intent);
                            //다른 화면에서 로그인 여부 판단을 위한 아이디 저장
                            SharedPreferences preferences = getSharedPreferences("usersInfo",MODE_PRIVATE);
                            preferences.edit().putString("username",username).commit();
                            preferences.edit().putString("password",password).commit();

                        }
                        return null;
                    });


                }

                return null;
            });

        }
        else{
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            UserApiClient.getInstance().loginWithKakaoAccount(this,(oAuthToken,loginError)->{
                if(loginError!=null){
                    //로그인실패
                    Log.i("com.kosmo.kosmoapp", "로그인 실패: "+loginError);
                }
                else{
                    //로그인성공
                    //사용자정보요청
                    // Log.i("com.example.kakaologin", "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                }
                return  null;});
        }
    }///////////////////




    public static String getKeyHash(final Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null)
                return null;

            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}