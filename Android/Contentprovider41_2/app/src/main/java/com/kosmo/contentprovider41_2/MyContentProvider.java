package com.kosmo.contentprovider41_2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//1]ContentProvider 상속
//2]매니페스트파일에 provider태그로 등록
public class MyContentProvider extends ContentProvider {

    //컨텐츠 프로바이더에 접근하려는 다른 앱(리졸버 사용)에서 전달한 URI가 맞는지 체크하기위한 UriMatcher타입 선언
    private static UriMatcher uriMatcher;
    private static final  int URI_MATCHER_CODE=100;

    static {
        //최초는 NO_MATCH-처음에는 컨텐츠 URI를 전달받지 않음으로...
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //UriMatcher에  리졸버에서 보낸 URI가 프로바이더가 제공한 URI가 맞는지 비교할 URI를설정
        //String authority, String path, int code(매칭시 판단용 숫자값)
        //프로바이더가 제공하는 URI: content://com.kosmo.contentprovider41_2/users
        uriMatcher.addURI("com.kosmo.contentprovider41_2","users",URI_MATCHER_CODE);
    }

    private SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {//데이타 베이스 생성
        MySQLiteOpenHelper sqLiteOpenHelper= new MySQLiteOpenHelper(getContext(),"AndroidDB.db",null,1);
        sqLiteDatabase =sqLiteOpenHelper.getWritableDatabase();


        return sqLiteDatabase==null?false:true;
    }
    //컨텐츠 프로바이더에 있는 데이타의 MIME타입 반환
    @Override
    public String getType(Uri uri) {
        return null;
    }
    /*

   public final Cursor  query (Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
   uri
   The URI, using the content:// scheme, for the content to retrieve.
   projection
   A list of which columns to return. Passing null will return all columns, which is inefficient.
   selection
   A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given URI.

   selectionArgs
   You may include ?s in selection, which will be replaced by the values from selectionArgs, in the order that they appear in the selection. The values will be bound as Strings.

   sortOrder
   How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
   */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.i("com.kosmo.content","인자 uri:"+ uri.toString());
        Cursor cursor=null;
        if(uriMatcher.match(uri)==URI_MATCHER_CODE){//리졸버가 보낸 uri가 맞는 지 판단후 데이타 조회 서비스 제공
            cursor=sqLiteDatabase.query("users",projection,selection,selectionArgs,null,null,sortOrder);
            Log.i("com.kosmo.content","uri(match):"+ uri.toString());
        }
        else
            throw new UnsupportedOperationException("조회 불가");
        return cursor;
    }
    //※외부 즉 다른 앱(리졸버가 구현된 앱)에 입력/삭제/수정을 허용하지 않을때는 구현하지 않으면 된다.
    //수정과 삭제는 영향받은 행의 수 반환

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        //throw new UnsupportedOperationException("삭제도 안되");
        int affected=0;
        if(uriMatcher.match(uri)==URI_MATCHER_CODE){
            affected = sqLiteDatabase.delete("users",selection,selectionArgs);
        }
        else
            throw new UnsupportedOperationException("삭제할 수 없어요");
        return affected;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        //throw new UnsupportedOperationException("야 업데이트 안되");
        int affected=0;
        if(uriMatcher.match(uri)==URI_MATCHER_CODE){
            affected = sqLiteDatabase.update("users",values,selection,selectionArgs);
        }
        else
            throw new UnsupportedOperationException("수정할 수 없어요");
        return affected;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //throw new UnsupportedOperationException("입력 할수 없어");
        Uri returnUri=null;
        if(uriMatcher.match(uri)==URI_MATCHER_CODE){
            long rowId=sqLiteDatabase.insert("users",null,values);
            //content://com.kosmo.contentprovider41_2/users/rowId를 반환한다
            returnUri = ContentUris.withAppendedId(uri,rowId);
        }
        else
            throw new UnsupportedOperationException("입력할 수 없어요");
        return returnUri;
    }
}