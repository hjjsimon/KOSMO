package com.kosmo.retrofit35_2;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//리사이클러 뷰 아이템간의 여백용
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int top;
    public MyItemDecoration(int top){
        this.top=top;

    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = top;//아이템간 위쪽 마진

    }
}