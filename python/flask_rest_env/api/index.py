from flask_restful import Resource
from flask import make_response
import json

#자원(Resource)을 HTTP method에 맞게 처리하는 api(메소드)를 정의한 클래스
#1.Resource상속
class Index(Resource):
    # get 오버라이딩
    def get(self):
        # flask_restful은 디폴트 응답헤더 Content-Type: application/json
        # flask는 디폴트 응답헤더 Content-Type: text/html
        # 1.직접 문자열 반환: Content-Type: application/json라 "<h2>Hello World</h2>"그대로 브라우저에 출력됨
        #return '<h2>Hello World</h2>'
        # 2.Response객체 반환:make_response("문자열")
        # ※make_response("문자열")로 반환시 응답헤더 Content-Type이 자동으로 text/html로 변경된다
        #return make_response('<h2>Hello World</h2>')
        # 3.딕셔너리객체 반환
        # Content-Type: application/json로 바뀐다
        #return {'server':'오픈  RESTFul API'}
        #Content-Type: application/json로 바뀐다
        #JSON객체가 아닌 문자열로 처리된다 즉 "{\"server\": \"\uc624\ud508  RESTFul API\"}"
        #return json.dumps({'server':'오픈  RESTFul API'},ensure_ascii=False)

        #return make_response(json.dumps({'server': '오픈  RESTFul API'},ensure_ascii=False))

        '''
        RESTFul api는 주로 JSON형태로 데이타를 서비스 한다
        1. import json
        2. make_response(json.dumps(딕셔너리객체,ensure_ascii=False))호출해서 Response객체 반환
        '''
        return make_response(json.dumps([{'name':'가길동'},{'name':'나길동'},{'name':'다길동'}],ensure_ascii=False))
