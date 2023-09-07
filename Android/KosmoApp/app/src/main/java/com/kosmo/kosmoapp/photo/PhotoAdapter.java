package com.kosmo.kosmoapp.photo;

import android.content.Context;
import android.text.PrecomputedText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.kosmo.kosmoapp.MainActivity;
import com.kosmo.kosmoapp.databinding.PhotoLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private List<PhotoItem> items;
    private int itemLayoutResId;
    private PhotoLayoutBinding binding;

    public PhotoAdapter(Context context, List<PhotoItem> items, int itemLayoutResId) {
        this.context = context;
        this.items = items;
        this.itemLayoutResId = itemLayoutResId;
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PhotoLayoutBinding.inflate(((MainActivity)context).getLayoutInflater());
        //반드시 아이템 레이아웃의 가로폭/세로폭 지정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
                );
        binding.getRoot().setLayoutParams(params);
        return new PhotoViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position).getTitle());
        holder.itemId.setText(items.get(position).getId());
        //https://square.github.io/picasso/ : 원격의 이미지 URL를 이미지뷰에 표시하는 라이브러리
        Picasso.get().load(items.get(position).getThumbnailUrl()).into(holder.itemImage);
        holder.cardView.setOnClickListener(v->{
            Toast.makeText(context, items.get(position).getUrl(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView itemImage;
        TextView itemId;
        TextView itemTitle;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = binding.cardView;
            itemImage = binding.itemImage;
            itemId = binding.itemId;
            itemTitle = binding.itemTitle;
        }
    }
}
