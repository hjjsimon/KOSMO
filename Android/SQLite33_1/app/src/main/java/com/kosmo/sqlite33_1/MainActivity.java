package com.kosmo.sqlite33_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosmo.sqlite33_1.databinding.ActivityMainBinding;

//※SQLiteOpenHelper클래스를 상속받아서 생성자와 onCreate()
//메소드로 데이타베이스와 테이블을 생성하지 않고
//SQLiteDatabase클래스의 openOrCreateDatabase()메소드로 데이타베이스 생성하기]
public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    private AlertDialog dialog;
    private SharedPreferences preferences;
    private String databaseName;
    private String tableName;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("데이타 베이스")
                .setPositiveButton("확인",null).create();
        //앱 종료후 다시 실행시 직전의 데이타베이스를 사용하기 위한 추가 코드
        preferences = getSharedPreferences("kosmodb",MODE_PRIVATE);
        databaseName = preferences.getString("dbname",null);
        if(databaseName!=null){
            binding.editDatabase.setHint(databaseName);
            setDisabled(binding.editDatabase,binding.btnDatabase);
        }
        tableName = preferences.getString("tbname",null);
        if(tableName !=null){
            binding.editTable.setHint(tableName);
            setDisabled(binding.editTable,binding.btnTable);
        }
    }/////////////onCreate
    //대화상자 띄우기용 메소드
    private boolean showMessage(EditText editText,String message){
        if(editText.getText().toString().trim().length()==0){
            dialog.setMessage(message);
            dialog.show();
            editText.requestFocus();
            return false;
        }
        return true;
    }///////////////////////
    //데이타베이스 연결용 메소드
    private SQLiteDatabase connect(SQLiteDatabase sqLiteDatabase,String databaseName){
        if(sqLiteDatabase ==null) {
            sqLiteDatabase = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
            //Pie버전부터는 아래코드 추가해야 함
            sqLiteDatabase.disableWriteAheadLogging();
        }
        return sqLiteDatabase;
    }
    //입력창 및 버튼 비활성화 메소드
    private void setDisabled(EditText editText, Button button){

        //입력상자 비활성화
        editText.setEnabled(false);
        //버튼 비활성화
        button.setEnabled(false);
    }
    //버튼의 이벤트 처리
    public void createDatabase(View v){
        //1.데이타베이스명 입력여부 판단
        if(!showMessage(binding.editDatabase,"데이타베이스명을 입력하세요")){
            return;
        }
        databaseName=binding.editDatabase.getText().toString().trim();
        //SQLiteDatabase openOrCreateDatabase(String 데이타베이스명,int 모드,Cursorfactory)
        //Cursorfactory는 null지정
        //※데이타 베이스는 /data/data/패키지명/databases에 생성됨.
        //3.데이타 베이스 생성]
        /*

        name – The name (unique in the application package) of the database.
        mode – Operating mode.
        factory – An optional factory class that is called to instantiate a cursor when query is called.
         */
        sqLiteDatabase= openOrCreateDatabase(databaseName,MODE_PRIVATE,null);
        //Pie버전부터는 아래코드 추가해야 함
        sqLiteDatabase.disableWriteAheadLogging();
        Toast.makeText(this, "데이타베이스가 생성되었습니다", Toast.LENGTH_SHORT).show();
        //앱 종료후 다시 실행시 기존 데이타베이스를 사용하기 위한 코드
        preferences.edit().putString("dbname",databaseName).commit();
        //비활성화
        setDisabled(binding.editDatabase,binding.btnDatabase);
    }//////////////////////////
    public void createTable(View v){
        //1.반드시 데이타 베이스 생성후에 테이블 생성]
        if(databaseName ==null){
            if(!showMessage(binding.editDatabase,"데이타베이스를 먼저 생성하세요")){
                return;
            }
        }
        //2.테이블명 입력여부 판단
        if(!showMessage(binding.editTable,"테이블명을 입력하세요")){
            return;
        }
        //3.테이블 생성
        tableName=binding.editTable.getText().toString().trim();
        //데이타베이스 연결
        sqLiteDatabase=connect(sqLiteDatabase,databaseName);

        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s(_id integer primary key autoincrement,name text not null)",tableName));
        Toast.makeText(this, "테이블이 생성되었습니다", Toast.LENGTH_SHORT).show();
        //앱 종료후 다시 실행시 기존 테이블를 사용하기 위한 코드
        preferences.edit().putString("tbname",tableName).commit();

        //비활성화
        setDisabled(binding.editTable,binding.btnTable);
    }
    public void insert(View v){
        //1.이름 입력여부 판단
        if(!showMessage(binding.editInsert,"이름을 입력하세요")){
            return;
        }
        //2.데이타베이스 연결
        sqLiteDatabase=connect(sqLiteDatabase,databaseName);
        //3.데이타 입력
        String name=binding.editInsert.getText().toString().trim();
        sqLiteDatabase.execSQL("INSERT INTO "+tableName+"(name) VALUES('"+name+"')");
        binding.editInsert.setText("");
        binding.editInsert.requestFocus();
    }
    public void select(View v){
        //1.반드시 테이블 존재 여부 판단
        if(tableName ==null){
            if(!showMessage(binding.editTable,"테이블명을 먼저 생성하세요")){
                return;
            }
        }
        //2.데이타베이스 연결
        sqLiteDatabase=connect(sqLiteDatabase,databaseName);
        //3.조회
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+tableName+" ORDER BY _id DESC",null);
        binding.tvResult.setText("");
        while(cursor.moveToNext()){
            //Cursor의 getXXXXX()메소드로 각 컬럼의 데이터 꺼내오기]
            //컬럼 인덱스는 0부터 시작(자바의 JDBC API는 1부터 시작)
            int _id = cursor.getInt(0);
            @SuppressLint("Range") String name= cursor.getString(cursor.getColumnIndex("name"));
            binding.tvResult.append(String.format("번호:%s,이름:%s%n",_id,name));

        }
    }
    //데이타 베이스 연결 끊기
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor !=null) cursor.close();
        if(sqLiteDatabase !=null) sqLiteDatabase.close();
    }
}