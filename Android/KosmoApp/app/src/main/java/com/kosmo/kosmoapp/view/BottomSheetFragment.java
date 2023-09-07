package com.kosmo.kosmoapp.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kosmo.kosmoapp.R;
import com.kosmo.kosmoapp.databinding.FragmentBottomSheetBinding;
import com.kosmo.kosmoapp.photo.PhotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


//BottomSheetDialogFragment 상속
public class BottomSheetFragment extends BottomSheetDialogFragment {

   private FragmentBottomSheetBinding binding;
   private String filename;
   private Uri uri;
   private Bitmap bitmap;
   public BottomSheetFragment(Uri uri,String filename,Bitmap bitmap){
       this.uri=uri;
       this.filename=filename;
       this.bitmap=bitmap;
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBottomSheetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //서버전송
        binding.btnDownloaf.setOnClickListener(v->{
            //방법1-갤러리에 저장된 이미지를 바로 서버로 전송
            //sendImageToServer(new File(getPhotoPath(getActivity().getContentResolver(),uri)));
            //방법2-내부 저장소에 갤러리에 저장된 이미지를 저장후 서버에 전송
            File file=saveBitmap(bitmap);
            sendImageToServer(file);

        });
        //다른 앱으로 공유
        binding.btnShare.setOnClickListener(v->{

            Intent intent= new Intent(Intent.ACTION_SEND);
            //공유할 이미지의 경로 Uri를  Intent.EXTRA_STREAM 액션으로 설정
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("image/*");
            startActivity(Intent.createChooser(intent, "")  );

        });

        binding.closeBottomSheet.setOnClickListener(v->{

            //getActivity().onBackPressed();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(BottomSheetFragment.this).commit();
            fragmentManager.popBackStack();

        });

        return view;
    }

    //서버 전송용 메소드
    private void sendImageToServer(File file) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.10:9090")
                .addConverterFactory(JacksonConverterFactory.create()).build();
        PhotoService service=retrofit.create(PhotoService.class);
        //파일 업로드관련 요청바디 설정 코드
        RequestBody postFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("uploadFile", file.getName(),postFile);
        //기타 텍스트 파라미터관련 요청바디 설정 코드
        RequestBody title = RequestBody.create("upload image:"+file.getName(),MediaType.parse("text/plain"));
        Call<Map<String,String>> call= service.camera(uploadFile,title);
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.isSuccessful())
                    Log.i("com.kosmo.kosmoapp",response.body().toString());
                else
                    Log.i("com.kosmo.kosmoapp",response.errorBody().toString());
            }
            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    //방법1-갤러리에 저장된 이미지를 바로 서버로 전송하기 위해 파일 경로를 얻는 메소드
    public static String getPhotoPath(ContentResolver contentResolver,Uri photoUri) {
        //쿼리 컬럼
        String[] columns = {MediaStore.Images.Media.DATA};
        //갤러리에 저장된 이미지 Uri로 _data 조회
        Cursor cursor = contentResolver.query(photoUri,
                columns, null, null, null);
        //커서가  널이거나 레코드가 없는 경우
        if (cursor == null || cursor.getCount() < 1) return null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(columns[0]);
        if (columnIndex < 0) return null;
        //선택한 파일 경로
        String photoPath = cursor.getString(columnIndex);
        cursor.close();
        return photoPath;
    }

    //내부 저장소에 갤러리에 저장된 이미지 저장하는 메소드(방법2용)
    /*
    앱 내부 스토리지 : Context객체.getFileDir()
    내부 앱 내부 캐시 스토리지 : Context객체.getCacheDir()
    외부 앱 전용 스토리지 : Context객체.getExternalFilesDir(Environment.DIRECTORY_DCIM등)
    외부 앱 전용 캐시 스토리지 :  Context객체.getExternalCacheDir()
     */
    private File saveBitmap(Bitmap bitmap) {

        File file = new File(getContext().getCacheDir(), filename);
        try {

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, out);
            out.close();
        }
        catch(IOException e){e.printStackTrace();}
        return file;
    }
}