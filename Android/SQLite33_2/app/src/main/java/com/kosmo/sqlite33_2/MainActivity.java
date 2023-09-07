package com.kosmo.sqlite33_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.kosmo.sqlite33_2.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME="ANDROID_DB.db";
    public static final String TABLE_NAME="USERS";
    public static final String TAG="com.kosmo.sqlite";

    //데이타베이스 관련 API
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    //리스트뷰와 커서(레코드 정보)를 간단하게 연결해주는 어댑터-안드로이드에서 제공해주는 커서어댑터
    private SimpleCursorAdapter adapter;
    //안드로이드에서 제공해주는 SimpleCursorAdapter생성자에 인자로 전달할 변수 선언
    //컬럼명을 담고 있는 String형 배열
    private String[] from={"username","password","name","age","joindate"};
    //위의 컬럼명에 해당하는 데이타를
    //표시할 아이템뷰(item_layout.xml)의 리소스 아이디 배열]-뷰는 반드시 TextView여야됨(SimpleCursorAdapter사용시).
    private  int[] to={R.id.username,R.id.password,R.id.name,R.id.age,R.id.joindate};
    //데이타 베이스 버전관리용
    int oldVersion=1,newVersion=1;
    //리스트뷰의 아이템 클릭여부 판단용 및 수정/삭제시 사용할 키값 저장변수
    private  int _id = -1;
    //경고 메시지 출력용
    AlertDialog dialog;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //MySQLiteOpenHelper를 인스턴스화 하는 것만으로도 데이타베이스 생성됨
        sqLiteOpenHelper=new MySQLiteOpenHelper(this,DATABASE_NAME,null,oldVersion);
        Log.i(TAG,"getWritableDatabase()호출 전");
        //데이타베이스 오픈
        sqLiteDatabase=sqLiteOpenHelper.getWritableDatabase();
        Log.i(TAG,"getWritableDatabase()호출 후");

        //4]앱 실행시 전체 레코드 가져와서 뿌려주기
        selectAll();

        //5]SimpleCursorAdapter 생성
        /* context : 리스트뷰가 속해 있는 Context
	       layout:리스트뷰 하나의  아이템을 정의한 뷰 레이아웃 파일의 아이디.
	              레이아웃 파일의 뷰들(위젯)은 적어도 "to"에 정의된 위젯의 아이디를 포함하고 있어야 한다.
           cursor :데이타베이스의 커서로 커서가 준비가 안되엇다면 null 지정가능
           from : UI에 바인딩 될 데이타를 나타내는 컬럼명 배열 .
	              커서가 준비가 안되엇다면 null 지정가능.
            to : "from" 매개변수에 정의된 컬럼명에 표시할 뷰의 리소스 아이디 배열.
	        ※이들 뷰는 모두 TextView여야한다.커서가 준비가 안되엇다면 null 지정가능.

        flags:  어댑터의 행동을 결정하는 상수, CursorAdapter(Context, Cursor, int) 참조
        */
        adapter= new SimpleCursorAdapter(this,R.layout.item_layout,cursor,from,to, CursorAdapter.NO_SELECTION);
        //6]리스트뷰와 어댑터 연결
        binding.listView.setAdapter(adapter);
        //7]리스트뷰에 리스너 부착
        binding.listView.setOnItemClickListener((adapterView,view,position,id)->{
            //psotion에 해당하는 커서 즉 레코드 얻기
            Cursor record=(Cursor)adapter.getItem(position);
            //SELECT *시 순서:"_id","username","password","name","age","joindate"
            //인덱스 0부터
            binding.editUser.setText(record.getString(1));
            binding.editPass.setText(record.getString(2));
            binding.editName.setText(record.getString(3));
            binding.editAge.setText(record.getString(4));
            //클릭한 아이템의 _ID로 설정
            _id= record.getInt(0);

        });
        //대화 상자 생성하기
        dialog= new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("회원가입")
                .setPositiveButton("확인",null).create();
    }//////////onCreate
    //모든 레코드 목록 가져오기
    private void selectAll(){
        if(sqLiteDatabase !=null){
            //query("테이블명",new String[]{조회할 컬럼명들},WHERE절,WHERE절 데이타,GROUP BY절 ,having절 ,ORRDR BY절)
            //※like절은 자바JDBC의 PreparedStatement객체 와 동일하게 사용해여 함 즉 '%?%' 형식 안됨.
            //예] cursor=sqLiteDatabase.query(TABLE_NAME,new String[]{"_id","username","password","name","age","joindate"},"name LIKE '%' || ? || '%'",new String[]{"DONG"},null,null,"_id DESC");
            //SELECT * FROM MEMBER WHERE NAME LIKE '%' || 'DONG' || '%' ORDER BY _ID DESC
            //방법1]query("테이블명",조회할 컬럼명 배열,.....
            //반드시 _id컬럼을 추가해야 한다.그렇지 않으면 column '_id' does not exist에러)
            cursor=sqLiteDatabase.query(TABLE_NAME,new String[]{"_id","username","password","name","age","joindate"},null,null,null,null,"_id DESC");
            //방법2]rawQuery("쿼리문",null)
            //cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY _id DESC",null);
        }
    }/////////////////selectAll
    //어댑터에 데이타 변경통지 및 텍스트 클리어
    private void notifyAdapter(){
        //변경된 데이타를 커서에  반영
        selectAll();
        //변경된 데이타가 반영된 커서를 다시 어댑터에 설정
        adapter.changeCursor(cursor);
        //변경된 데이타가 반영된 커서를 다시 어댑터에 설정
        adapter.notifyDataSetChanged();

    }
    //모든 텍스트 클리어
    private void clearText(){
        binding.editUser.setText("");
        binding.editAge.setText("");
        binding.editName.setText("");
        binding.editPass.setText("");
        binding.editUser.requestFocus();
    }
    //테이블 초기화
    public void init(View v){
        newVersion++;
        if(sqLiteDatabase !=null){
            //SQLiteOpenHelper의 onUpgrade()호출
            sqLiteOpenHelper.onUpgrade(sqLiteDatabase,oldVersion,newVersion);
            //oldVersion을 newVersion으로 초기화
            oldVersion= newVersion;
            //데이타 변경 반영
            notifyAdapter();
        }
    }
    //데이타 입력
    public void insert(View v){
        String username=binding.editUser.getText().toString().trim();
        String password=binding.editPass.getText().toString().trim();
        String name=binding.editName.getText().toString().trim();
        String age=binding.editAge.getText().toString().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String joindate = dateFormat.format(new Date());
        if(sqLiteDatabase!=null){
            //방법1]execSQL(쿼리문자열) 혹은 execSQL(쿼리문자열,데이타가 저장된 String형 배열)
            //sqLiteDatabase.execSQL("INSERT INTO USERS (username,password,name,age,joindate) VALUES(?,?,?,?,?)",new String[]{username,password,name,age,joindate});
            //방법2]ContentValue와 insert()메소드 이용
            //※키값은 반드시 컬럼명으로....
            ContentValues values= new ContentValues();
            values.put("username",username);
            values.put("password",password);
            values.put("name",name);
            values.put("age",age);
            values.put("joindate",joindate);
            sqLiteDatabase.insert(TABLE_NAME,null,values);
            //입력후 리스트뷰에 입력데이타 반영하기
            notifyAdapter();
            clearText();
        }

    }////////////////////
    public void update(View v){
        if(_id == -1){
            dialog.setMessage("수정할 아이템을 먼저 클릭하세요");
            dialog.show();
            return;
        }

        if(sqLiteDatabase !=null){
            String username=binding.editUser.getText().toString().trim();
            String password=binding.editPass.getText().toString().trim();
            String name=binding.editName.getText().toString().trim();
            String age=binding.editAge.getText().toString().trim();
            //방법1]execSQL(쿼리문자열) 혹은 execSQL(쿼리문자열,데이타가 저장된 String형 배열)
            //sqLiteDatabase.execSQL("UPDATE USERS SET username=?,password=?,name=?,age=? WHERE _id=?",new String[]{username,password,name,age,String.valueOf(_id)});
            //방법2]ContentValue와 update()메소드 이용
            ContentValues values= new ContentValues();
            values.put("username",username);
            values.put("password",password);
            values.put("name",name);
            values.put("age",age);
            sqLiteDatabase.update(TABLE_NAME,values,"_id=?",new String[]{String.valueOf(_id)});
            //수정후 리스트뷰에 데이타 반영하기
            notifyAdapter();
            clearText();
            //다시 -1로 초기화
            _id= -1;
        }

    }//////////////////
    public void delete(View v){
        if(_id == -1){
            dialog.setMessage("삭제할 아이템을 먼저 클릭하세요");
            dialog.show();
            return;
        }
        if(sqLiteDatabase !=null){
            //방법1]execSQL(쿼리문자열) 혹은 execSQL(쿼리문자열,데이타가 저장된 String형 배열)
            sqLiteDatabase.execSQL("DELETE FROM USERS WHERE _id=?",new Integer[]{_id});
            //방법2]ContentValue와 delete()메소드 이용
            //sqLiteDatabase.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(_id)});
            //삭제후 리스트뷰에 데이타 반영하기
            notifyAdapter();
            clearText();
            //다시 -1로 초기화
            _id= -1;
        }


    }///////////

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor !=null) cursor.close();
        if(sqLiteDatabase !=null) sqLiteDatabase.close();
    }
}