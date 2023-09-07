package com.kosmo.recyclerview32_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kosmo.recyclerview32_2.databinding.ItemFirstFragmentBinding;
import com.kosmo.recyclerview32_2.databinding.ItemSecondFragmentBinding;

import java.util.List;


//첫번째 프래그먼트용 어댑터

public class MyRecyclerViewSecondAdapter extends RecyclerView.Adapter<MyRecyclerViewSecondAdapter.MyViewHolder> {

    private Context context;
    private List<Item> items;

    private ItemSecondFragmentBinding binding;
    public MyRecyclerViewSecondAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= ItemSecondFragmentBinding.inflate(LayoutInflater.from(context),parent,false);
        View itemView= binding.getRoot();
        return new MyViewHolder(itemView);
    }
    //position위치에 해당하는 items의 데이타를 뷰홀더의 필드인 윗젯에 데이타 설정
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewSecond.setText(items.get(position).getItemTitle());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewSecond;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSecond = binding.textViewSecond;
        }
    }
}
