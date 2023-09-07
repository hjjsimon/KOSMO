package com.kosmo.gridview31;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.kosmo.gridview31.databinding.ItemLayoutBinding;

//1]BaseAdapter상속
public class MyCustomAdapter  extends BaseAdapter {

    private ItemLayoutBinding binding;
    private Context context;
    //영화 제목
    private String[] movies;
    //이미지 리소스 아이디
    private int[] resIds;

    //이벤트 처리용
    private AlertDialog dialog;
    private View dialogView;

    public MyCustomAdapter(Context context, String[] movies, int[] resIds) {
        this.context = context;
        this.movies = movies;
        this.resIds = resIds;
        //이벤트 처리용 초기화
        dialogView = View.inflate(context,R.layout.dialog_layout,null);
        dialogView.findViewById(R.id.close).setOnClickListener(v->{
            dialog.dismiss();
        });
        dialog = new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_menu_camera)
                .setView(dialogView)
                .create();

    }

    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        return movies[position];
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view ==null){
            binding=ItemLayoutBinding.inflate((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            view = binding.getRoot();
        }
        //데이타 설정
        ((ImageView)view.findViewById(R.id.moviePoster)).setImageResource(resIds[position]);
        ((TextView)view.findViewById(R.id.movieTitle)).setText(movies[position]);
        //방법2)어댑터에서 이벤트 처리

        view.setOnClickListener(v->{
            if(!dialog.isShowing()){
                ((ImageView)(dialogView.findViewById(R.id.bigPoster))).setImageResource(resIds[position]);
                dialog.setTitle(movies[position]);
                dialog.show();
            }
        });

        return view;
    }
}
