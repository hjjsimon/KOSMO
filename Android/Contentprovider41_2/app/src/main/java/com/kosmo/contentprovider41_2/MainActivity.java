package com.kosmo.contentprovider41_2;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.kosmo.contentprovider41_2.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //현재 앱은 프로바이더이면서 데이타를 제공받은 리졸버도 구현함
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.kosmo.contentprovider41_2/users");


        binding.btnInsert.setOnClickListener(v->{
            for (int i = 1; i <= 10; i++) {
                ContentValues values = new ContentValues();
                values.put("username", "KOSMO" + i);
                values.put("password", "123" + (3 + i));
                values.put("name", "한소인" + i);
                values.put("joindate", new java.sql.Date(new Date().getTime()).toString());
                resolver.insert(uri,values);
            }
        });
        binding.btnFindAll.setOnClickListener(v->{
            Cursor cursor = resolver.query(uri, null, null, null, "_id DESC");
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.item_layout,
                    cursor,
                    new String[]{"_id", "username", "password", "name", "joindate"},
                    new int[]{R.id.tvId, R.id.tvUser, R.id.tvPass, R.id.tvName, R.id.tvJoindate},
                    CursorAdapter.NO_SELECTION
            );

            binding.listview.setAdapter(adapter);
        });
    }
}