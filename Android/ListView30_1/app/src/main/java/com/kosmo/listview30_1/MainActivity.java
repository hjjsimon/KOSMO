package com.kosmo.listview30_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //1]데이타용
    private String[] items ={"지리산","설악산","덕유산","태백산","비슬산","대둔산","치악산","한라산","성인봉","북한산","도봉산","관악산","사패산","응봉산"};
    private ListView listView;
    private String selectedItem;
    private List<String> multipleItem = new Vector<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2]리스트뷰 객체 얻기
        listView=findViewById(R.id.listView);
        //3]어댑터 생성
        //안드로이드에서 제공하는 하나의 아이템을 위한 레이아웃들
        //1)simple_list_item_1.xml(TextView) 일때-모드 설정 불필요
        //ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        //2)simple_list_item_checked(CheckedTextView)일때 모드 설정 필요(single이나 multi둘 다 설정 가능)
        //ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_checked,items);
        //3)simple_list_item_single_choice(LinearLayout)일때-모드 설정 필요
        //ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,items);
        //4)simple_list_item_multiple_choice(CheckedTextView)일때-모드 설정 필요
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,items);
        //4]어댑터를 리스트뷰와 연결-setAdapter(어댑터)로 연결
        listView.setAdapter(adapter);
        //4-1]리스트뷰의 모드 설정
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //4-2]아이템의 분리선 설정
        //※분리선의 기본 색상 이외에 다른 색상 설정시에는 반드시 색상 설정한후 두께 설정한다. (순서에 주의)
        //분리선 색상 설정]-xml속성에도 있다
        listView.setDivider(new ColorDrawable(Color.parseColor("#FF0000")));
        //분리선 두께 설정]-xml속성에도 있다
        listView.setDividerHeight(5);
        //403]헤더와 푸터 설정-헤더와 푸터도 아이템으로 포함됨 -XML속성에는 없다
        //listView.addHeaderView(View v);
        //listView.addFooterView(View v);

        listView.addHeaderView(View.inflate(this,R.layout.listview_header,null));
        listView.addFooterView(View.inflate(this,R.layout.listview_footer,null));

        //5]리스트뷰에 리스너 부착(setOnItemClickListener)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //adapterView:클릭 이벤트가 발생한 ListView
            //view:하나의 아이템을 위한 레이아웃용 뷰
            //position:클릭한 아이템의 인덱스 값(헤더는 0 푸터는 배열의 length혹은 length+1(헤더포함시))
            //id:실제 데이타 아이템의 인덱스값(0부터 시작:헤더나 푸터는 -1)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("listview",String.format("position:%s,id:%s",position,id));
                Log.i("listview",String.format("아이템 수:%s,헤더 수:%s,푸터 수:%s",adapterView.getCount(),((ListView)adapterView).getHeaderViewsCount(),listView.getFooterViewsCount()));
                //Log.i("listview",items[position]+"선택");//비 추천(헤더 부착시 오류)
                /*
                 ※특정 인덱스의 아이템 가져올때는 getItemAtPosition(int position)메소드 사용
                 배열명[인덱스] 비 추천
                 왜냐하면
                 리스트뷰에 헤더나 푸터 추가시 헤더나 푸터도 아이템에 포함됨.
                 모드가 single인 경우:getCheckedItemPosition()으로 체크된 아이템
                       multiple인 경우:getCheckedItemPositions()으로 체크된 아이템을 얻는다
                       그리고 getItemAtPosition(int position)메소드로 아이템의 텍스트를 얻는다
                 ※isItemChecked(int position) : 반드시 인자로 받은 position 을 넣어야한다
                                                isItemChecked()메소드는 헤더나 푸터를 아이템으로 인식한다


                 */
                Log.i("listview",adapterView.getItemAtPosition(position)+(((ListView) adapterView).isItemChecked(position)?"선택":"해제"));

                if(((ListView) adapterView).getChoiceMode()==ListView.CHOICE_MODE_SINGLE){
                    selectedItem =adapterView.getItemAtPosition(position).toString();
                }
                else if(((ListView) adapterView).getChoiceMode()==ListView.CHOICE_MODE_MULTIPLE){
                    if(listView.isItemChecked(position)){//선택
                        multipleItem.add(adapterView.getItemAtPosition(position).toString());
                    }
                    else{//해제
                        multipleItem.remove(adapterView.getItemAtPosition(position).toString());
                    }
                    selectedItem=multipleItem.toString();

                }
            }
        });
    }
    public void click(View v){
        Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show();
    }
}