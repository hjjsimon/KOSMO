package com.kosmo.webview19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.kosmo.webview19.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //[WebView설정하기]
        //1]WebView의 getSettings()메소드로 WebSettings객체
        WebSettings webSettings= binding.webview.getSettings();
        //1.(필수 설정)자스가 실행되도록 설정- 기본적으로 웹뷰는 자스를 지원하지 않음
        webSettings.setJavaScriptEnabled(true);
        //2.(필수 설정)스마트폰 웹뷰안에 사이트가 들어오도록 설정 즉 상단에
        //에디트 텍스트나 버튼들 그대도 보이도록 설정.
        //아래 부분 생략시 웹뷰가 전체 레이아웃을 차지함(사이트 로드시)
        binding.webview.setWebViewClient(new WebViewClient());
        //3.(필수 설정)아래 코드 미 추가시 alert()창 안뜬다
        //웹의 기본 경고창(alert) 그대로 사용시
        //binding.webview.setWebChromeClient(new WebChromeClient());
        //모바일에 맞게 기본 경고창 커스터 마이징시
        binding.webview.setWebChromeClient(new MyWebChromeClient());
        //4.(옵션)확대모드 설정
        //webSettings.setBuiltInZoomControls(true);

        //버튼 이벤트 처리
        binding.buttonGo.setOnClickListener(v->{
            String url= binding.editUrl.getText().toString();
            binding.webview.loadUrl(url);

        });
        binding.buttonBack.setOnClickListener(v->{
            binding.webview.goBack();
        });
        //API서버로부터 데이타받아서 웹뷰에 데이타 뿌려주기
        binding.btnData1.setOnClickListener(v->{
           /*
            사전 작업:
                앱안에 있는 html파일 로딩하기
                Android탭->app선택후 New->Folder->Assets Foldr->Finish
                 그리고 하위에 New -> Directory로  html 및 images디렉토리 생성
                 assets가 아닌 file:///android_asset 으로 URL 설정(file://이 아니다)
           */


            //방법1:html소스를 문자열로 로드하기
            String data="<html>\n" +
                    "  <head>\n" +
                    "\t<meta charset=\"UTF-8\">\n" +
                    "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "\t \n" +
                    "\t<link rel=\"stylesheet\"\n" +
                    "\t\thref=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\">\n" +
                    "\t<script\n" +
                    "\t\tsrc=\"https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js\"></script>\n" +
                    "\t\t<script\n" +
                    "\t\tsrc=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\n" +
                    "\t<script\n" +
                    "\t\tsrc=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                    "    <script type=\"text/javascript\">\n" +
                    "      google.charts.load('current', {'packages':['corechart']});\n" +
                    "      google.charts.setOnLoadCallback(drawChart);\n" +
                    "\n" +
                    "      function drawChart() {\n" +
                    "\n" +
                    "        var data = google.visualization.arrayToDataTable([\n" +
                    "          ['Task', 'Hours per Day'],\n" +
                    "          ['일',     11],\n" +
                    "          ['식사',      2],\n" +
                    "          ['커뮤니티',  2],\n" +
                    "          ['Watch TV', 2],\n" +
                    "          ['잠',    7]\n" +
                    "        ]);\n" +
                    "\n" +
                    "        var options = {\n" +
                    "          title: '나의 하루 일과'\n" +
                    "        };\n" +
                    "\n" +
                    "        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n" +
                    "\n" +
                    "        chart.draw(data, options);\n" +
                    "      }\n" +
                    "    </script>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "\t<div class=\"container\"\n" +
                    "\t\t<div id=\"piechart\" ></div>\n" +
                    "<div class=\"d-flex justify-content-center\">"+
                    //"<img src='file:///android_asset/images/sumnail.png' alt='이미지' class='img-thumbnail'/>" +//loadData()호출시 이미지 표시 불가
                    "<img src='images/sumnail.png' alt='이미지' class='img-thumbnail'/>" +
                    "\t</div>\n" +
                    "</div>"+
                    "  </body>\n" +
                    "</html>";
            //이미지 표시 못함
            //binding.webview.loadData(data,"text/html","UTF-8");
            //이미지 표시하기:file:///android_asset/ baseUrl은 HTML태그에서 상대경로 표시시 사용
            //binding.webview.loadDataWithBaseURL("file:///android_asset/",data,"text/html","UTF-8",null);

            //방법2:assets안에 있는 html 소스를 로드하기
            //url:file:///android_asset/html/index.html
            binding.webview.loadUrl("file:android_asset/html/index.html");

        });
        binding.btnData2.setOnClickListener(v->{

            AssetManager assetManager= getAssets();
            try {
                //assets폴더(읽기전용)에 있는 차트용 템플릿 파일(template.html파일) 읽기
                InputStream is= assetManager.open("html/template.html");
                byte[] bytes= new byte[is.available()];
                is.read(bytes);
                String html=new String(bytes);
                //원격으로부터 받은 데이타(map)로 통계 데이타 변경후  웹뷰에 표시
                Map<String,Integer> map = new HashMap<>();
                map.put("일",4);
                map.put("식사",6);
                map.put("TV 보기",4);
                map.put("잠",10);

                StringBuffer pieData = new StringBuffer();
                Set<String> keys = map.keySet();
                //['일',4]
                for(String key:keys){
                    int value = map.get(key);
                    pieData.append(String.format("['%s',%s],%n",key,value));
                }
                binding.webview.loadDataWithBaseURL("file:///android_asset/",html.replace("googlePieChart",pieData.toString()),"text/html","UTF-8",null);
                is.close();
            }
            catch(IOException e){e.printStackTrace();}
        });

        //※WebView와 프론트엔드의 자바스크립트 통신
        //※먼저 WebView에  자바스크립트 코드가 있는 프론트 엔드의 URL을 로드해야 한다


        binding.btnCallJs.setOnClickListener(v->{
            //안드로이드 WebView에서 프론트엔드의 자바스크립트 함수 호출(확인은 안드로이드 앱에서 확인)
            //1)WebView.loadUrl("javascript:프론트렌드의 자바스크립트 함수()") 즉 a태그의 href속성에 javascript:함수()식으로 호출한다.
            //2)프론트 엔드의 HTML에 아래 함수(showAlert())를 정의한다

            binding.webview.loadUrl("javascript:showAlert('안드로이드에서 호출합니다');");



        });

        //프론트엔드의 자바스크립트에서 안드로이드 WebView의 메소드 호출
        //1)프론트 엔드의 html에서 자스로 호출할 함수 API(WebAppInterface) 정의(아래 정의 했음)
        //2)프론트엔드의 html에서 자스로 호출할 함수 API 등록
        // 자스에서 호출시 window.Android.showToast()

        //프론트 엔드의 자스에서 웹뷰에 등록한 메소드를 호출할 수 있도록
        //자바스크립트 인터페이스로 웹뷰에 등록
        //첫번째 인자는 함수 인터페이스 객체
        //두번째 인자는 자스에서 호출할 함수에 접근할 식별자명
        binding.webview.addJavascriptInterface(new WebAppInterface(),"Android");

    }/////////////onCreate
    //프론트 엔드의 자스에서 호출할 API
    class WebAppInterface{
        @JavascriptInterface
        public void showToast(String message){
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }




    //자스의 alert() 및 confirm()을 스마트폰 앱에 맞는 UI로 변경하기 위한 클래스
    private class MyWebChromeClient extends  WebChromeClient {

        //웹의 자스 alert()창을 Toast AlertDialog로 변경
        /*
        view	WebView: The WebView that initiated the callback.
        url	String: The url of the page requesting the dialog.
        message	String: Message to be displayed in the window.
        result	JsResult: A JsResult to confirm that the user closed the window.
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //경고 메시지를 Toast로 보여주기
            //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            //result.confirm();//자바스크립트 경고창의 확인 버튼을 클릭한 것으로 처리하도록 호출(미 호출시 다른부분이 클릭이 안된다)
            //return true;
            //return super.onJsAlert(view, url, message, result);혹은 return false;-->Toast도 뜨고 기본 Alert창도 뜬다

            new AlertDialog.Builder(view.getContext())
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_menu_camera)
                    .setTitle("경고대화상자")
                    .setMessage(message)
                    .setPositiveButton("확인",(dialogInterface,which)->{
                        result.confirm();
                    }).show();

            return true;//기본 경고창 안보이게 하려면 반드시 true반환

        }

        //웹의  confirm()창을 AlertDialog로 변경


        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            new AlertDialog.Builder(view.getContext())
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_menu_camera)
                    .setTitle("확인대화상자")
                    .setMessage(message)
                    .setPositiveButton("확인",(dialogInterface,which)-> {result.confirm();})
                    .setNegativeButton("취소",(dialogInterface,which)->{result.cancel();})
                    .show();

            //return super.onJsConfirm(view, url, message, result);
            return true;
        }
    }/////////////////MyWebChromeClient
}