package com.kosmo.recyclerview32_1;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//아이템간 여배 주기
//1)RecyclerView.ItemDecoration
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int top;//위 쪽 여백용
    //2)생성자로 여백 값 받기
    public MyItemDecoration(int top){
        this.top=top;
    }
    //3)getItemOffsets오버라이딩

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top=top;//아이템 뷰간 위쪽 마진 설정
    }
}
