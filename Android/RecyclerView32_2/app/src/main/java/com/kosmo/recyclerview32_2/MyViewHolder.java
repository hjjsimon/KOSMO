package com.kosmo.recyclerview32_2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

//액티비티용 뷰 홀더(외부 클래스로 작성)
public class MyViewHolder extends RecyclerView.ViewHolder {
    //아이템뷰의 데이타 설정을 하기 위한 위젯들을 멤버필드로 정의:
    //private 하지 않는다(Adapter에서 접근해야함)
    CardView cardView;
    RoundedImageView itemImage;
    TextView itemTitle;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = (CardView) itemView;
        itemImage=itemView.findViewById(R.id.itemImage);
        itemTitle=itemView.findViewById(R.id.itemTitle);
    }////////////
}
