package com.kosmo.jsonparser36_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.kosmo.jsonparser36_2.databinding.ItemLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private List<PhotoItem> items;

    private ItemLayoutBinding binding;

    public MyRecyclerViewAdapter(Context context, List<PhotoItem> items) {
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
        holder.itemId.setText(items.get(position).getId());
        //https://square.github.io/picasso/ : 원격의 이미지 URL를 이미지뷰에 표시하는 라이브러리
        Picasso.get().load(items.get(position).getThumbnailUrl()).into(holder.itemThumbnailUrl);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //아이템뷰에 데이타를 설정할 뷰들 필드 정의 및 초기화
    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView itemThumbnailUrl;
        TextView itemId;
        TextView itemTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemId = binding.itemId;
            itemThumbnailUrl=binding.itemThumbnailUrl;
            itemTitle=binding.itemTitle;
        }
    }


}
