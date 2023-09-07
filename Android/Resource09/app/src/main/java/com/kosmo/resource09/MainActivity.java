package com.kosmo.resource09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

/*
    res디렉토리]

   drawable - 이미지,xml파일
   values-문자열,배열,색상,크기등을 정의한 xml파일

   [레이아웃용 xml(activity_main.xml)에서 res폴더에 있는 리소스참조시]

   drawable디렉토리:@drawale/이미지파일명

   values디렉토리:
   문자열-@string/name속성값
   색상-@color/name속성값
   크기-@dimen/name속성값

   [자바소스에서 참조시]
   1]Resources resource = getResources();
   drawable디렉토리:
   resource.getDrawable(R.drawable시작하는 리소스아이디)
   values디렉토리:
   문자열-resource.getString(R.string시작하는 리소스 아읻)
   색상-resource.getColor() 혹은 ContextCompat.getColor(Context,R.color시작하는 리소스아이디)
   크기-resource.getDimension(R.dimen시작하는 리소스아이디)
*/
/*
    cannot resolve symbol~ 해결법
    1. Build메뉴
       Clean project->Rebuild Project
    2. File 메뉴
       Sync Project with Gradle Files
    3. File 메뉴
       Invalidate Cache/Restart
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        TextView textView = findViewById(R.id.codeTextview);
        ImageView imageView = findViewById(R.id.codeImageview);
        Button button = findViewById(R.id.codeButton);
         /*※values폴더의 strings.xml과  drawable폴더에 있는 이미지 접근시
            앱에서 가장 많이 사용하는 리소스 종류여서
            바로 메소드를 제공해준다
            getString()이나 getDrawable()로 가져올 수 있다
            즉 Resources객체 불필요*/
        //tag:com.kosmo.resource -tag:com.kosmo.resource09
        Log.i("com.kosmo.resource",String.format("문자열 데이타:%s",getString(R.string.codeMessage)));
        //1]res폴더에 정의된 모든 리소스(비트맵,XML등)를
        // 얻어 올수 있는 클래스:Resources(getResources()메소드로 얻는다.)
        Resources resources= getResources();
        textView.setText(getString(R.string.codeMessage));
        //textView.setTextSize(20);//기본 단위가 SP
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);//20SP
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,resources.getDimension(R.dimen.xmlDimen));
         /*
        ※ getDimension() 메소드가 pixel 형태로 리턴한다
          dimens.xml에 sp라고 명시시
          getDimension () 은 알아서 pixel로 변환해서 리턴
          dimens.xml에서 sp로 설정한 값을 자바코드로 적용시 아래 코드로 읽어와야
          크기가 똑같이 적용됨 즉 숫자로 직접 지정했을때와 같다
          */
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,resources.getDimension(R.dimen.xmlDimen));
        //textView.setTextColor(resources.getColor(R.color.xmlColor,null));
        //textView.setTextColor(resources.getColor(R.color.xmlColor,getTheme()));
        textView.setTextColor(ContextCompat.getColor(this,R.color.xmlColor));
        //이미지뷰의 src속성 설정
        //방법1]ImageView객체.setImageResource(int 리소스 아이디)
        //imageView.setImageResource(R.drawable.picture_emergency);
        //방법2]ImageView객체.setImageDrawable(Drawable)
        //imageView.setImageDrawable(getDrawable(R.drawable.picture_emergency));
        //방법3]ImageView객체.setImageBitmap(Bitmap)
        //Bitmap bitmap=((BitmapDrawable)getDrawable(R.drawable.picture_emergency)).getBitmap();
        Bitmap bitmap= BitmapFactory.decodeResource(resources,R.drawable.picture_emergency);
        imageView.setImageBitmap(bitmap);

        button.setOnClickListener(view -> {
            int[] ages=resources.getIntArray(R.array.ages);
            String[] mountains=resources.getStringArray(R.array.mountains);

            Log.i("com.kosmo.resource","연령대:"+ Arrays.toString(ages));
            Log.i("com.kosmo.resource","산:"+ Arrays.toString(mountains));
        });

    }
}