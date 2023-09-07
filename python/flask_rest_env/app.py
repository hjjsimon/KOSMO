'''
https://flask-restful.readthedocs.io/en/latest/
1. pip install flask
2. pip install flask-restful
3. pip install flask_cors
'''
from flask import  Flask
from flask_restful import Api
from flask_cors import CORS
import os

#내가 만든 RESTFul API서비스용 클래스 모듈들
from api.index import Index
from api.todo import Todo
from api.todos import Todos
from api.upload import Upload
from api.ocr import OCR
from api.starbucks import Starbucks
from api.stroke import StrokeModel
from api.skinlesion import SkinLesionModel
from api.lungcancer import LungCancerModel

#플라스크 앱 생성
app=Flask(__name__)

#CORS에러 처리
CORS(app)
'''
CORS(app, resource={
    r"/*":{
        "origins":"*"
    }
})
'''
#브라우저로 바로 JSON 응답시 한글 처리(16진수로 URL인코딩되는 거 막기)
#Response객체로 응답시는 생략(내부적으로 utf8을 사용)

#업로드용 폴더 설정
UPLOAD_FOLDER = os.getcwd()
app.config['UPLOAD_FOLDER']=os.path.join(UPLOAD_FOLDER,'uploads')
#최대 파일 업로드 용량 1M로 설정
app.config['MAX_CONTENT_LENGTH']= 1* 1024 *1024

#플라스크 앱(app)을 인자로 하여 Api객체 생성:URI 주소와 클래스를 매핑해서 요청을
#라우팅 즉 @app.route('/todos/<todo_id>')와 같다
api=Api(app)
'''
-요청을 처리할 클래스(반드시 Resource상속)와 요청URL 매핑(라우팅)하기
    Api객체.add_resource(클래스명,'/요청URL')    
'''
#1. / 로 GET 요청시
api.add_resource(Index,'/')
'''
#2.
    GET /todos : 모든 todo 조회
    POST /todos : 입력     
'''
api.add_resource(Todo,'/todos')
'''
#3.
    GET /todos/<todo_id> : todo_id로 조회
    DELETE /todos/<todo_id> : todo_id로 삭제
    PUT /todos/<todo_id> : todo_id로 수정 ,키가 없으면 입력(단,abort_if_todo_dosent_exisit(todo_id)함수는 주석)

    URL 파라미터명인 todo_id은 클래스의 오버라이딩한 HTTP Method의 인자와 일치 시켜야 한다
    예:
    def get(self,todo_id):
        pass
'''
api.add_resource(Todos,'/todos/<todo_id>')
'''
4.파일 업로드
    POST /upload
'''
api.add_resource(Upload,'/upload')
'''
객체탐지:OCR
POST /ocr
'''
api.add_resource(OCR,'/ocr')
'''
스타벅스 서울 전체 매장 크롤링 데이타 서비스
GET /starbucks
'''
api.add_resource(Starbucks,'/starbucks')


'''
머신러닝
POST /stroke
'''
api.add_resource(StrokeModel, '/StrokeModel')

'''
머신러닝
POST /skinlesion
'''
api.add_resource(SkinLesionModel, '/SkinLesionModel')

'''
머신러닝
POST /lungcancer
'''
api.add_resource(LungCancerModel, '/LungCancerModel')


if __name__ == '__main__':
    app.run(debug=True)#port:5000
