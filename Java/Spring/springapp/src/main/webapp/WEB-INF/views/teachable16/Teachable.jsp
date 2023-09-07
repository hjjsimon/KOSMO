<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="container" style="margin-top:50px;">
        <div class="jumbotron bg-info">
            <h1>Teachable Machine<small>이미지 모델</small></h1>
        </div><!--jumbotron-->
        <button  onclick="predict()" class="btn btn-warning">예측하기</button>
		<input type="file" id="test_image" accept=".png,.jpg,.jpeg" class="form-control-file border"/>
		<!-- 선택한 이미지를 미리보기 위한 추가 -->
		<img id="preview_image" class="img-thumbnail" style="width:200px;height:200px;"/>
		
		<!-- 웹캠으로 캡처한 이미지 표시 영역(영상소스) -->
		<div id="webcam-container"></div>
		<!-- 예측결과 표시 영역 -->
		<div id="label-container"></div>
    </div><!-- container -->
    
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@latest/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@latest/dist/teachablemachine-image.min.js"></script>
<script type="text/javascript">
    // More API functions here:
    // https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/image

    // the link to your model provided by Teachable Machine export panel
    const URL = "http://localhost:9090/resources/my_model/";
	
    //웹캠 사용시
    //let model, webcam, labelContainer, maxPredictions;
	//웹캠을 이미지 파일로 변경시
    var model,//메모리에 로드한 모델 저장용
    labelContainer,//예측 결과를 보여줄 div요소 저장용
    maxPredictions;//클래스(분류)개수 저장용
	
    // Load the image model and setup the webcam
    //0.웹캠 관련 부분 주석처리
    async function initialize() {//async 는 ES7 문법 - await와 같이 사용된다
        const modelURL = URL + "model.json";
        const metadataURL = URL + "metadata.json";

        // load the model and metadata
        // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
        // or files from your local hard drive
        // Note: the pose library adds "tmImage" object to your window (window.tmImage)
        //이미지 분석용 티처블 머신으로 훈련한 이미지 모델 로드
        model = await tmImage.load(modelURL, metadataURL);
        console.log('model:',model);
        //총 클래스 분류 즉 분류(카테고리)수 반환
        maxPredictions = model.getTotalClasses();
        console.log('카테고리(클래스)의 수:',maxPredictions);
		
        //웹캠 설정 부분 및 재생
        // Convenience function to setup a webcam
        /*
        const flip = true; // whether to flip the webcam
        webcam = new tmImage.Webcam(200, 200, flip); // width, height, flip
        await webcam.setup(); // request access to the webcam
        await webcam.play();
        window.requestAnimationFrame(loop);

        // append elements to the DOM
        document.getElementById("webcam-container").appendChild(webcam.canvas);
        */
        //예측 결과를 표시하기위한 div영역에 클래스 수대로 div태그 추가
        labelContainer = document.getElementById("label-container");
        for (let i = 0; i < maxPredictions; i++) { // and class labels
            labelContainer.appendChild(document.createElement("div"));
        }
        
    }
	//웹캠 사용시 프레임 업데이트용 메소드
	/*
    async function loop() {
        webcam.update(); // update the webcam frame
        await predict();
        window.requestAnimationFrame(loop);
    }
	*/
	
    // run the webcam image through the image model
    //4.테스트 이미지로 예측하는 함수
    async function predict() {
        // predict can take in an image, video or canvas html element
        //웹캠의 이미지(프레임)으로 예측시
        //const prediction = await model.predict(webcam.canvas);
        
        //이미지 파일로 변경하기위해 아래코드 추가
        var image = document.getElementById('preview_image')
        //이미지파일로 예측하기위한 코드 추가
    	/*
        https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/image
       
        model.predict(
        image: HTMLImageElement | HTMLCanvasElement | HTMLVideoElement | ImageBitmap,
        flipped = false
        )
        flipped: a boolean to trigger whether to flip on X or not the image input
       
        */
        const prediction = await model.predict(image);
        console.log('prediction:',prediction);
        //예측 결과를 lableContainer에 표시주는 반복문
        //className:클래스명(분류명)
        //probability:확률
        //toFixed(소수점 자리수):자리수까지 표현(반올림)
        
        for (let i = 0; i < maxPredictions; i++) {
            const classPrediction =
                prediction[i].className + ": " + prediction[i].probability.toFixed(2)*100+'%';
            	labelContainer.childNodes[i].innerHTML = classPrediction;
        }
    }
	
	//1.페이지 로드시 모델 분류
	initialize();
	
	//2.이미지를 선택했을때 이미지 미리보기 함수 호출
	//type="file"요소에"change"이벤트 등록
	$('#test_image').on('change',function(){
		previewImage(this);
	});
	
	//3.이미지 미리보기 함수 정의
	//코드펜에서 image upload로 검색해서 코드 가져오기
	function previewImage(input){//input는 <input type="file" ~/>객체
		if(input.files && input.files[0]){
			console.log('input.files:',input.files);//FileList객체
			console.log('input.files[0]:',input.files[0]);//File객체
			//https://developer.mozilla.org/ko/docs/Web/API/FileReader
			var reader = new FileReader();
			//아래 메소드(readAsText)는 텍스트 파일 읽을때
			//reader.readAsText(input.files[0],'EUC-KR');
			//reader.readAsText(input.files[0],'UTF-8');
			//console.log();//텍스트로 파일 읽기
			//파일의 실제 데이터를 URL로 읽기 즉 파일 데이터를 주소처럼 활용
			reader.readAsDataURL(input.files[0]);//이미지 파일을 URL로 읽기
			reader.onload=function(e){
				console.log('e.tatget:',e.target);//FileReader객체(result속성에 base64로 인코딩된 문자열이 저장됨)
				console.log('e.target.result:',e.target.result);//이미지 파일의 base64로 인코딩된 문자열
				//img
				$('#preview_image').prop('src',e.target.result)
			};
		}
		
	}
	
</script>
