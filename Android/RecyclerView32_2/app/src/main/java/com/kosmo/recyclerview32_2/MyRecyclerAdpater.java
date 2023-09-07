package com.kosmo.recyclerview32_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kosmo.recyclerview32_2.databinding.ItemLayoutBinding;
import com.kosmo.recyclerview32_2.databinding.ItemSecondFragmentBinding;

import java.util.List;

//MainActivity용 리사이클러 어댑터
public class MyRecyclerAdpater extends RecyclerView.Adapter {
    private Context context;
    private List<Item> items;

    private ItemLayoutBinding binding;

    public MyRecyclerAdpater(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= ItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        View itemView= binding.getRoot();
        return new MyViewHolder(itemView);
    }
    //position위치에 해당하는 items의 데이타를 뷰홀더의 필드인 윗젯에 데이타 바인딩
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).itemTitle.setText(items.get(position).getItemTitle());
        ((MyViewHolder)holder).itemImage.setImageResource(items.get(position).getItemImage());
        //루트 뷰인 CardView에 이벤트 부착
        ((MyViewHolder)holder).cardView.setOnClickListener(v->{
            Toast.makeText(context, items.get(position).getItemTitle(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
