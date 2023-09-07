package com.kosmo.kosmoapp.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;

import com.kosmo.kosmoapp.R;
import com.kosmo.kosmoapp.databinding.FragmentContent2Binding;
import com.kosmo.kosmoapp.databinding.FragmentContent3Binding;
import com.kosmo.kosmoapp.photo.PhotoService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class Content3 extends Fragment {
    private FragmentContent3Binding binding;
    private String cameraImagePath;

    //현재 앱에서 필요한 권한들(Q이후 Scoped Storage등장- 앱이 생성한 파일 읽고 쓰는데 권한 불필요)
    //학습용
    private String[] permissions={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    //권한요청시 각 권한을 구분하기 위한 요청코드값(식별자)
    public static final int MY_EXTERNAL_READ_WRITE_PERMISSION =1;

    //허용이 안된 권한들을 저장할 컬렉션
    private List<String> deniedPermissions = new Vector<>();

    private SharedPreferences preferences;
    //갤러리에 저장된 이미지 URI
    private Uri photoUri;
    private Bitmap rotatedBitmap;
    //방법1-갤러리에 저장된 이미지를 바로 서버로 전송하기 위한 경로(compress()미사용-용량이 더 적음)
    private String photoPath;
    //방법2-내부 저장소에 갤러리에 저장된 이미지를 저장후 서버에 전송하기 위한 이미지 파일명(compress()사용-방법1보다 용량이 큼)
    private String photoFileName;
    public Content3(){
        Log.i("com.kosmo.kosmoapp","Content3생성자");
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getContext().getSharedPreferences("camera", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContent3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        Log.i("com.kosmo.kosmoapp","Content3 onCreateView");
        //카메라 버튼
        binding.btnCamera.setOnClickListener(v->{

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            //방법2-내부 저장소에 갤러리에 저장된 이미지를 저장후 서버에 전송하기 위한 이미지 파일명
            photoFileName=dateFormat.format(new Date())+"_camera.png";
            //리졸버로 갤러리에 이미지 데이타 정보 입력
            ContentResolver resolver = getContext().getContentResolver();
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME,photoFileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE,"image/png");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH ,Environment.DIRECTORY_DCIM);
            //빈 이미지 데이타의 Uri 얻기
            //URI 형식 EX) content://media/external/images/media/1006
            photoUri = resolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values);

            //방법1-갤러리에 저장된 이미지를 바로 서버로 전송하기 위한 경로(compress()미사용-용량이 더 적음)
            //photoPath=getPhotoPath(getContext().getContentResolver(),photoUri);

            //카메라앱으로 화면 전환
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //카메라 찰영후 갤러리에 저장된 이미지를 두번째 인자로 전달한 Uri로 매핑시키기 위한 설정
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);//찰영한 사진 썸네일로 이미지뷰에 출력시  주석처리
            cameraLauncher.launch(intent);
        });
        //갤러리 버튼
        binding.btnGallery.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            galleryLauncher.launch(intent);

        });

        //공유버튼 클릭
        binding.sharedImage.setOnClickListener(v->{
            //바툼쉬트 띄우기
            BottomSheetFragment bottomSheetDialog = new BottomSheetFragment(photoUri,photoFileName,rotatedBitmap);
            bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "bottomSheetDialog");
            /*
            Intent intent= new Intent(Intent.ACTION_SEND);
            //공유할 이미지의 경로 Uri를  Intent.EXTRA_STREAM 액션으로 설정
            intent.putExtra(Intent.EXTRA_STREAM, photoUri);
            intent.setType("image/*");
            startActivity(Intent.createChooser(intent, "")  );*/
        });

        return view;
    }////////////////////////onCreateView


    ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result->{
                switch(result.getResultCode()){
                    case Activity.RESULT_OK:
                        if(result.getData() !=null){//외부 앱으로 부터 받은 데이타
                            //갤러리에서 선택한 이미지 Uri
                            Uri selecetdImageUri=result.getData().getData();
                            //산택한 이미지의 Uri로 이미지뷰에 표시
                            binding.imageView.setImageURI(selecetdImageUri);
                            binding.sharedImage.setVisibility(View.GONE);
                        }
                }
            });


    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            (result)->{
                switch(result.getResultCode()){
                    case Activity.RESULT_OK://카메라 앱으로부터 데이타 받기
                        /*
                        if(result.getData()!=null) {
                            //썸네일 이미지
                            //Bitmap bitmap=(Bitmap)result.getData().getExtras().get("data");//"data"라는 키로 카메라앱이 찍은 썸네일 사진 이미지를 전달함
                            //binding.imageView.setImageBitmap(bitmap);
                        }*/
                        InputStream is = null;
                        try {
                            //찰영 이미지 Uri의 입력스트림 생성
                            is = getContext().getContentResolver().openInputStream(photoUri);
                            //스트림으로 비트맵 이미지 생성
                            Bitmap bitmap = BitmapFactory.decodeStream(is);//원본 이미지 비트맵
                            is.close();
                            //이미지 회전
                            rotatedBitmap=rotateImage(bitmap);
                            //이미지 뷰에 찰영 이미지 보이기
                            binding.imageView.setImageBitmap(rotatedBitmap);
                            //공유 이미지 보이기
                            binding.sharedImage.setVisibility(View.VISIBLE);

                            //방법1-갤러리에 저장된 이미지를 바로 서버로 전송
                            //sendImageToServer(new File(photoPath));
                            //방법2-내부 저장소에 갤러리에 저장된 이미지를 저장후 서버에 전송
                            //File file=saveBitmap(bitmap);
                            //sendImageToServer(file);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                       break;
                }
            }
    );


    //찰영 이미지 회전 메소드
    public Bitmap rotateImage(Bitmap bitmap) {
        //회전 각도 얻어오기
        float degree=0;
        try {
            ExifInterface ei = new ExifInterface(BottomSheetFragment.getPhotoPath(getActivity().getContentResolver(),photoUri));
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree=90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree=180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree=270;
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    degree=0;
            }
        }
        catch(IOException e){e.printStackTrace();}

        //회전각도로 원본 이미지 비트맵을 회전하기
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("com.kosmo.kosmoapp","Content3 onViewCreated");
        String camera=preferences.getString("camera_no","N");
        if(camera.equals("Y")){
            binding.btnCamera.setEnabled(false);
        }
        else binding.btnCamera.setEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        String camera=preferences.getString("camera_no","N");
        if(camera.equals("Y")){
            binding.btnCamera.setEnabled(false);
        }
        else binding.btnCamera.setEnabled(true);
    }
}