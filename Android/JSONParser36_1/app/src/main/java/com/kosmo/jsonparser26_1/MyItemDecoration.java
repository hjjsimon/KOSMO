package com.kosmo.jsonparser26_1;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//리사이클러 뷰 아이템간의 여백용
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Rect rect;
    public MyItemDecoration(Rect rect){
        this.rect=rect;

    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = rect.top;
        outRect.bottom = rect.bottom;
        outRect.left=rect.left;
        outRect.right=rect.right;

    }
}