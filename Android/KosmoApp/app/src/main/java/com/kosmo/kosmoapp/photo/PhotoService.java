package com.kosmo.kosmoapp.photo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PhotoService {
    //JSONPLACEHOLDER용
    @GET("/photos")
    Call<List<PhotoItem>> photos();
    //카메라로 찰영한 사진 서버 업로드용
    /*
    REST API 소스
    @CrossOrigin
	@PostMapping("/camera")
	public Map<String,String> camera(HttpServletRequest req,
			@RequestPart MultipartFile uploadFile,@RequestParam String title){
		Map<String,String> map = new HashMap<>();
		try {

			String path = req.getSession().getServletContext().getRealPath("/resources");
			File f = new File(path+File.separator+uploadFile.getOriginalFilename());
			uploadFile.transferTo(f);
			map.put("isSucess", f.getName());
			System.out.println("제목 파라미터:"+title);
		}
		catch(Exception e) {
			map.put("isSucess", "FAIL");
		}
		return map;
	}
     */
    @Multipart
    @POST("/restapi/camera")
    Call<Map<String,String>> camera(@Part MultipartBody.Part uploadFile, @Part("title") RequestBody title);
}
