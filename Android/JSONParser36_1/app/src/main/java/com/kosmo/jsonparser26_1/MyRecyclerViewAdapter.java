package com.kosmo.jsonparser26_1;

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


import com.kosmo.jsonparser26_1.databinding.ItemLayoutBinding;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private List<UsersItem> items;

    private ItemLayoutBinding binding;

    public MyRecyclerViewAdapter(Context context, List<UsersItem> items) {
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
        holder.name.setText(items.get(position).getName());
        holder.age.setText(items.get(position).getAge());
        holder.hobbies.setText(items.get(position).getHobbies());
        holder.users.setText(items.get(position).getUsers());
        //이벤트 처리
        holder.cardView.setOnClickListener(v->{
            Toast.makeText(context, items.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //아이템뷰에 데이타를 설정할 뷰들 필드 정의 및 초기화
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView age;
        TextView hobbies;
        TextView users;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView) itemView;
            name=binding.name;
            age=binding.age;
            hobbies = binding.hobbies;
            users=binding.users;
        }
    }


}
