package com.kosmo.listview30_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosmo.listview30_2.databinding.ItemLayoutBinding;

import java.util.List;

//1]BaseAdapter상속-4개의 메소드 오버라이딩
public class MyCustomAdapter extends BaseAdapter {

    private ItemLayoutBinding binding;
    private Context context;//리스트 뷰가 실행되는 컨텍스트(필수)
    private List<Item> items;//리스트뷰에 뿌릴 데이타(필수)
    private int itemLayoutResId;//아이템 레이아웃 리소스 아이디(선택사항)

    //2]생성자 정의:생성자로 Context와 리스트뷰에 뿌려줄 데이타를 받는다.
    //             리소스 레이아웃 아이디(int)는 선택사항
    //인자생성자1]컨텍스트와 데이타
    public MyCustomAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }
    //인자생성자2]컨텍스트와 데이타와 아이템 레이아웃 리소스아이디-뷰결합시 필요 없음
    public MyCustomAdapter(Context context, List<Item> items, int itemLayoutResId) {
        this.context = context;
        this.items = items;
        this.itemLayoutResId = itemLayoutResId;
    }
    //※리스트뷰에 의해서 호출됨.
    //아이템의 총 갯수 반환,
    //리스트뷰는 아이템 총 갯수만큼 아래의 getView()메소드를 호출한다.
    @Override
    public int getCount() {
        return items.size();
    }
    //position에 해당하는 아이템 반환
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    //아이템의 아이디 반환
    @Override
    public long getItemId(int id) {
        return id;
    }
    /*
    리스트뷰가 각 아이템을 출력할때 어댑터의 getView()메소드 호출
    즉 어댑터는  출력할 아이템의 뷰를 생성해서 리스트 뷰에 반환
    view는 리스트 뷰의 각 아이템을 나타내는 뷰이고
    viewGroup는 부모 뷰 즉 리스트뷰가 됨.
    리스트뷰는 getView()호출시 최초 요청일때는 position이 0이고 view는 null이다
    두번째 아이템 항목부터는 view 레이아웃이 그대로 리스트뷰로부터 전달됨으로
    항목을 구성하는 위젯만 변경해서(리스트뷰로부터 전달받은 position값에 따라)  view를
    리스트뷰에 반환한다.
    */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null){
            binding = ItemLayoutBinding.inflate(((MainActivity)context).getLayoutInflater());
            view = binding.getRoot();
        }
        //아이템 뷰에 position위치에 해당하는 데이타(items컬렉션) 설정
        ((TextView)view.findViewById(R.id.itemName)).setText(items.get(position).getItemName());
        ((TextView)view.findViewById(R.id.itemDept)).setText(items.get(position).getItemDept());
        ((TextView)view.findViewById(R.id.itemDate)).setText(items.get(position).getItemDate());
        ((ImageView)view.findViewById(R.id.itemImage)).setImageResource(items.get(position).getItemPictureResId());

        //position에 해당하는 아이템(뷰-CardView)에 리스너 부착
        view.setOnClickListener(v->{
            Toast.makeText(context, items.get(position).getItemDept(), Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
