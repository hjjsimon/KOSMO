package com.kosmo.sqlite33_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//1]SQLiteOpenHelper 상속
/*
    A]생성자 정의-super()호출시 데이터베이스 생성됨
    B]onCreate()오버라이딩-테이블 생성을 위한 코딩 작성
    C]onUpgrade()오버라이딩-데이타베이스 버전 변경시 (스키마 변경시)
    D]Pie버전 이후부터는 onOpen()오버라이딩
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String databaseName, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //context: Activity등의 Context 인스턴스
        //databaseName: 데이터베이스의 이름
        //null: 커서 팩토리(보통 null지정)
        //version: 데이터베이스 스키마 버전
        super(context, databaseName, factory, version);
    }
    //B]테이블 생성
    //onCreate()는 getWritableDatabase() 혹은 getReadableDatabase()호출시 딱 한번만 호출됨.
    //getWritableDatabase()에 의해 반환된 SQLiteDatabase가 onCreate의 인자로 전달된다
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //같은 이름의 테이블이 존재한다면 삭제
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s",MainActivity.TABLE_NAME));
        //테이블 생성
        //※SimpleCursorAdpapter사용시 반드시 _id컬럼이 존재해야됨.
        //※autoincrement 설정시 그 컬럼은 반드시 primary key로 설정해야 한다.
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s " +
                "(_id integer primary key autoincrement,username TEXT,password TEXT," +
                "name TEXT NOT NULL,age integer,joindate DATETIME)",MainActivity.TABLE_NAME));
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 onCreate() invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            onCreate(sqLiteDatabase);
        }
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 onUpgrade() invoked");
    }
    //Pie버전 이후부터는 아래 코드 추가
    //데이타베이스 오픈시 즉 getWritableDatabase()호출시(onCreate()이후)
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 onOpen() invoked");
    }
}
