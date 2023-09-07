package com.kosmo.recyclerview32_2;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
    1. RecyclerView.ItemDecoration 상속
    2. getItemOffsets()오버 라이딩
 */
//리사클러뷰 아이템간 여백용
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int right;
    private int bottom;

    public MyItemDecoration(int right, int bottom) {
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getId() == R.id.recyclerView)//activity_main.xml의 리사이클러 뷰  위젯인 경우
            outRect.right=right;
        else //프래그먼트들의 레이아웃용 리사이클러 뷰 위젯인 경우
            outRect.bottom=bottom;


    }
}
