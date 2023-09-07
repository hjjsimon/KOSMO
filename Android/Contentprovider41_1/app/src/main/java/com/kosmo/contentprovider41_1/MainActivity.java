package com.kosmo.contentprovider41_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.kosmo.contentprovider41_1.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ContentResolver resolver;

    private ActivityMainBinding binding;
    //현재 앱에서 필요한 권한들
    private String[] permissions={
            Manifest.permission.READ_CONTACTS
    };
    //권한요청시 각 권한을 구분하기 위한 요청코드값(식별자)
    public static final int MY_READ_CONTACTS_PERMISSION =1;

    //허용이 안된 권한들을 저장할 컬렉션
    private List<String> deniedPermissions = new Vector<>();

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("denyPermission",MODE_PRIVATE);

        //안드로이드 시스템에서 제공하는 주소록 데이타에 접근하기위해
        //ContentResolver객체 얻기-getContentResolver()
        //ContentResolver객체의 query(조회)/insert()/delete()/update()메소드로
        //해당 앱(주소록 앱)의 컨텐츠 프로바이더가 제공하는 데이타를 CRUD할 수 있다.
        resolver = getContentResolver();


        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){

            boolean isDeny = preferences.getBoolean("DENY",false);
            if(isDeny){
                new AlertDialog.Builder(this)
                        .setTitle("앱 설정")
                        .setMessage("권한을 허용해야만 앱을 사용하실 수 있습니다\r\n설정 하시겠습니까?")
                        .setPositiveButton("예",(dialog,which)->{
                            //권한 설정화면으로 이동시키기(화면 전환)
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package",getPackageName(),null);
                            intent.setData(uri);
                            startActivity(intent);
                            //다시 false로 초기화
                            preferences.edit().putBoolean("DENY",false).commit();
                        })
                        .setNegativeButton("아니오",(dialog,which)->{finish();}).show();
            }
            else{
                if(requestUserPermissions()){//권한이 허용 된 경우
                    //해당 권한과 관련된 서비스 활성화 코드 작성
                    readContacts();
                }
            }
        }////////////////if

    }////////onCreate



    //사용자에게 권한을 요청하는 메소드(안드로이드 6.0(API LEVEL 23)이상부터 추가됨)
    private boolean requestUserPermissions(){
        for(String permission:permissions){
            int checkPermission= ActivityCompat.checkSelfPermission(this,permission);
            //권한이 없는 경우 deniedPermissions에 저장
            if(checkPermission== PackageManager.PERMISSION_DENIED)
                deniedPermissions.add(permission);
        }
        //권한이 없는게 있다면
        if(!deniedPermissions.isEmpty()){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .setTitle("권한요청")
                    .setMessage("권한을 허용해야만 앱을 정상적으로 사용 할 수 있습니다")
                    .setPositiveButton("확인",(dailog,which)->{
                        //사용자에게 권한을 요청하는  코드 작성
                        //두번째 인자:요청할 권한들의 String[]
                        ActivityCompat.requestPermissions(MainActivity.this,deniedPermissions.toArray(new String[deniedPermissions.size()]),MY_READ_CONTACTS_PERMISSION);
                    })
                    .setNegativeButton("앱 종료",(dailog,which)->{ finish(); }).show();
            return false;//획득하지 못한 권한이 있는 경우 false반환
        }

        return true;
        //※onRequestPermissionsResult오버라이딩 하자
    }///////////requestUserPermissions


    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch(requestCode){

            case MY_READ_CONTACTS_PERMISSION://위치서비스 권한 요청
                if(grantResults.length > 0){//거부 혹은 허용 한 경우
                    for(int i=0; i < grantResults.length;i++){
                        if(grantResults[i] == PackageManager.PERMISSION_GRANTED){//허용한 경우
                            //요청권한과 관련된 기능 및 서비스 활성화
                            readContacts();
                        }
                        else {//거부한 경우- 해당 권한과 관련된 서비스 비활성화 코드 작성
                            preferences.edit().putBoolean("DENY",true).commit();
                            Toast.makeText(this, "권한을 허용해야만 앱을 사용하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                }

        }
    }////////////////
    //안드로이드 OS의 주소록앱(Contacts)의 모든 주소를 읽어와 텍스트뷰에 뿌려주는 메소드
    //_id 및 이름 저장용-AlertDialog에 띄우기용(_id로 찾기 버튼 클릭시)
    private List<String> names = new Vector<>();
    private List<Long> _ids = new Vector<>();
    //다이얼로그창에서 사용자가 이름 선택시 _id값 저장용
    private int index = -1;
    private void readContacts() {
        //모든 주소록 데이타 저장용
        StringBuffer buf = new StringBuffer();
        //주소록 읽은 부분을 스레드로 구현
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .setTitle("주소록")
                .setMessage("주소록을 불러오는 중입니다")
                .create();
        dialog.show();
        new Thread(()->{
            //주소록 관련 데이타는  ContactsContract에 정의 되어 있음
            //ContactsContract.Contacts.CONTENT_URI는
            //content://com.android.contacts/contacts 와 같음
            //아래 퀴리는 SELECT * FROM 테이블명  ORDER BY _ID ASC와 같다
            //즉 모든 컬럼 그리고 _ID 정렬

            Cursor cursor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,"_id ASC");
            if(cursor!=null){
                while(cursor.moveToNext()){
                    //커서 객체의 getXXXXX(인덱스)로 컬럼의 데이터 얻기
                    //          getXXXXX(getColumnIndex(컬럼명))로 데이터 얻기

                    //해당 레코드의 ROW ID 즉 키값
                    @SuppressLint("Range") long _id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    _ids.add(_id);
                    //연락처 이름:ContactsContract.Contacts.DISPLAY_NAME
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    names.add(name);
                    buf.append(String.format("_id:%s,이름:%s",_id,name));
                    //전화번호 저장 여부: ContactsContract.Contacts.HAS_PHONE_NUMBER : 1 이면 저장,0 이면 미 저장
                    @SuppressLint("Range") int has_phone_number = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if(has_phone_number==1){
                        //SELECT * FROM 전화번호 WHERE contact_id=?
                        Cursor phoneCursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{String.valueOf(_id)},null);
                        StringBuffer phoneBuf = new StringBuffer();
                        while(phoneCursor.moveToNext()){
                            @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phoneBuf.append(phoneNumber+ "\r\n");
                        }
                        phoneCursor.close();
                        buf.append(String.format("%n전번:%s%n",phoneBuf));
                    }
                    Cursor  emailCursor=resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,"contact_id=?",new String[]{String.valueOf(_id)},null);
                    StringBuffer emailBuf = new StringBuffer();
                    while(emailCursor.moveToNext()){
                        @SuppressLint("Range") String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                        emailBuf.append(email+ "\r\n");
                    }
                    emailCursor.close();
                    buf.append(String.format("%n이메일:%s%n",emailBuf));

                }////while
                cursor.close();
            }///if
            runOnUiThread(()->{
                binding.tvContacts.setText(buf);
                dialog.dismiss();
            });
        }).start();

        binding.btnFindById.setOnClickListener(v->{
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_dialer)
                    .setTitle("주소록")
                    .setSingleChoiceItems(names.toArray(new String[names.size()]),-1,(dailog,which)->{
                        index = which;
                    })
                    .setPositiveButton("확인",(dailog,whicj)->{
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(ContactsContract.Contacts.CONTENT_URI+"/"+_ids.get(index)));
                        startActivity(intent);
                    })
                    .setNegativeButton("취소",null).show();
        });

        binding.btnFindAll.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
            resultLauncher.launch(intent);
        });


    }///////////////////onCreate
    ActivityResultLauncher<Intent> resultLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result->{
                if(result.getResultCode() == Activity.RESULT_OK){
                    binding.tvContacts.setText("받은 데이타:"+result.getData().getDataString());
                    Log.i("ok",result.getData().getDataString());
                }

            }
    );


}//////////class