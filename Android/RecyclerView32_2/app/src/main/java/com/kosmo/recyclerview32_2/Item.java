package com.kosmo.recyclerview32_2;

//액티비티 및 두 개의 프래그먼트의 각 리사클러뷰용 아이템에 공통으로 사용
public class Item {
    private String itemTitle;
    private int itemImage;

    public Item(String itemTitle, int itemImage) {
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
