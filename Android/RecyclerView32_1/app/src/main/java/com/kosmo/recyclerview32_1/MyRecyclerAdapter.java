package com.kosmo.recyclerview32_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kosmo.recyclerview32_1.databinding.ItemLayoutBinding;

import java.util.List;

/*

1. RecyclerView.Adapter클래스를  상속받는다
   ViewHolder를 내부 클래스로 구현시에는
   public class MyRecyclerAdapter extends RecyclerView.Adapter<외부클래스명.ViewHolder를 상속받은 내부 클래스명>{}

   혹은 별도의 외부에 ReCyclerView.ViewHolder를 상속받은 ViewHolder구현시는
   public class MyRecyclerAdapter extends RecyclerView.Adapter{}

   ※ViewHolder가 되려면 RecyclerView.ViewHolder를 상속받아야 한다
2. 오버라이딩 및 생성자 정의

※ViewHolder란?
    하나의 아이템을 표시하는 위젯들을 필드로 가지는 클래스로
    생성자 인자로 전개된 아이템 뷰를 받는다
    이때 인자로 받은 아이템 뷰로 필드들을 초기화 한다
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Item> items;
    private ItemLayoutBinding binding;
    //생성자
    public MyRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }//////////////////
    // 뷰홀더(MyViewHolder)를 반환는 메소드로 아이템뷰(item_layout.xml)를 여기서 전개한다(inflate)
    // 전개된 아이템뷰는 뷰홀더의 생성자로 전달한다
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //아이템뷰 전개
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        View itemView = binding.getRoot();
        return new MyViewHolder(itemView);
    }
    //뷰홀더의 필드(아이템뷰의 위젯들)들에 POSITION에 해당하는 데이타를 설정하는 메소드(데이타 바인딩)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //position에 해당하는 데이타(Item) 추출
        Item item=items.get(position);
        //아이템뷰에 데이타 바인딩
        holder.itemImage.setImageResource(item.getItemPictureResId());
        holder.itemName.setText(item.getItemName());
        holder.itemDept.setText(item.getItemDept());
        holder.itemDate.setText(item.getItemDate());

        //루트 뷰에 이벤트 부착
        holder.itemLayout.setOnClickListener(v->{
            Toast.makeText(context,item.getItemName() , Toast.LENGTH_SHORT).show();
        });

    }/////////////

    //아이템의 총 수 반환
    @Override
    public int getItemCount() {
        return items.size();
    }

    //뷰 홀더(※private class 불가)
    class MyViewHolder extends  RecyclerView.ViewHolder{
        //값으로 셋팅할 위젯들을 필드로 나열
        //속성에 private을 역시 붙이지 않는다.(외부 클래스의 메소드에서 접근)
        ImageView itemImage;
        TextView itemName;
        TextView itemDept;
        TextView itemDate;

        //배경색 및 이벤트 처리용(클릭 이벤트 리스너 부착해도 됨)
        CardView itemLayout;

        public MyViewHolder(@NonNull View itemView) {//itemView는  item_layou.xml을 인플레이트한 뷰이다 즉 onCreateViewHolder에서 반환한 뷰이다
            super(itemView);
            //itemImage = itemView.findViewById(R.id.itemImage);혹은 (단,리스트뷰는 재사용하지 않기때문에 반드시 findViewById())
            itemImage = binding.itemImage;
            itemName = binding.itemName;
            itemDept= binding.itemDept;
            itemDate = binding.itemDate;
            itemLayout = binding.itemLayout;
        }
    }

}
