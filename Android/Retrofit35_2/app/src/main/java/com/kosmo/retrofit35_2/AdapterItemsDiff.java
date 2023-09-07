package com.kosmo.retrofit35_2;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

//1. DiffUtil.Callback 상속
public class AdapterItemsDiff extends DiffUtil.Callback {
    //어댑터의 데이타로 사용하는 타입을 속성으로 선언
    private List<BBSItem> oldItems,newItems;

    public AdapterItemsDiff(List<BBSItem> oldItems, List<BBSItem> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }
    @Override
    public int getOldListSize() {
        return oldItems.size();
    }
    @Override
    public int getNewListSize() {
        return newItems.size();
    }
    //아이템이 동일한 가의 여부. 예를 들면 아이템들이 고유한 ID(BBS의 NO값)값을 가질 때,
    //해당 메서드를 구현해 비교 처리 하도록 한다
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getNo()==newItems.get(newItemPosition).getNo();
    }
    //아이템의 내용이 동일한가 여부. areItemsTheSame이 true로 리턴되었을 때만 수행된다
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        BBSItem oldItem = oldItems.get(oldItemPosition);
        BBSItem newItem = newItems.get(newItemPosition);
        return oldItem.equals(newItem);
    }
}
