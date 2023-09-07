from flask_restful import Resource,reqparse
from flask import make_response,request
#더미 데이타 및 공통으로 사용할 함수가 있는 items모듈 import
from api.items import TODOS
import json

class Todo(Resource):
    # 1.모든 todo조회
    def get(self):
        return make_response(json.dumps(TODOS,ensure_ascii=False))
    # 2.입력
    def post(self):
        #3번째 방법으로 파라미터 받기
        # STEP1. RequestParser객체 생성
        parser=reqparse.RequestParser()
        # STEP2. RequestParser객체에 add_argument('파라미터명')로 모든 파라미터명 추가
        # https://flask-restful.readthedocs.io/en/latest/reqparse.html?highlight=add_argument#argument-locations
        # RequestParser는 기본적으로 JSON값을 분석한다 이를 변경하려면 location키워드를 사용한다
        if request.content_type.find('application/json') != -1:#데이타가 json으로 전송될때
            parser.add_argument('task')
            parser.add_argument('when')
        else:#데이타가 key=value일때 location='form'추가
            parser.add_argument('task',location='form')
            parser.add_argument('when',location='form')
        # STEP3 모든 파라미터 받기
        args = parser.parse_args()
        #print('args:',args,',type:',type(args),sep='')
        next_max=max(map(int,map(lambda s:s[4:],TODOS.keys())))+1
        next_id = 'todo'+str(next_max)
        TODOS[next_id]=args
        return make_response(TODOS)