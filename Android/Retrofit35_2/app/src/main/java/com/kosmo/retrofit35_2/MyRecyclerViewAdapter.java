package com.kosmo.retrofit35_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kosmo.retrofit35_2.databinding.ItemLayoutBinding;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private List<BBSItem> items;

    private ItemLayoutBinding binding;

    public MyRecyclerViewAdapter(Context context, List<BBSItem> items) {
        this.context = context;
        this.items = items;
    }
    //뷰 홀더 생성해서 반환
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MyViewHolder(binding.getRoot());
    }
    //데이타 바인딩 즉 생성된 뷰홀더에 데이타 설정
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position).getTitle());
        holder.itemName.setText(items.get(position).getName());
        holder.itemNo.setText(items.get(position).getNo());
        holder.itemPostDate.setText(items.get(position).getPostDate());
        //이벤트 처리
        holder.cardView.setOnClickListener(v->{
            Toast.makeText(context, items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //아이템뷰에 데이타를 설정할 뷰들 필드 정의 및 초기화
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemNo;
        TextView itemName;
        TextView itemTitle;
        TextView itemPostDate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView) itemView;
            itemNo=binding.itemNo;
            itemName=binding.itemName;
            itemPostDate = binding.itemPostDate;
            itemTitle=binding.itemTitle;
        }
    }

    //데이타 변화 감지용 추가 메소드:리사이클러 뷰의 데이타 갱신을 위한 메소드 추가
    public void notifyItemsChanged(List<BBSItem> items){//items:갱신된 새로운 데이다
        //this.items는 이전 데이타.인자로 받은 데이타가 새로운 데이타다
        AdapterItemsDiff itemsDiff = new AdapterItemsDiff(this.items,items);
        //리사이클러뷰에 있는 DiffUtil API가 우리가 만든 AdapterItemsDiff객체를 인자로 받아
        //oldItems와 newItems간의 차이를 계산한다
        //그리고 그 결과를 반환한다
        DiffUtil.DiffResult diffResult= DiffUtil.calculateDiff(itemsDiff);
        //어댑터 이전 데이타를 지우고
        this.items.clear();
        //새로운 데이타로 채운다
        this.items.addAll(items);
        //만약 데이타의 차이가 있다면 현재 어댑터를 업데이트하도록 전파한다
        diffResult.dispatchUpdatesTo(this);
    }
}
