from flask_restful import Resource,reqparse
from flask import make_response
#파일 업로드
#https://flask-restful.readthedocs.io/en/latest/reqparse.html?highlight=add_argument#argument-locations
import werkzeug
import os


class Upload(Resource):
    def __init__(self):
        self.parser = reqparse.RequestParser()
        # 아래는 공통 파라미터 설정(key=value로 받기)
        self.parser.add_argument('title',location='form')
        self.parser.add_argument('name',location='form')
        self.upload_path = os.path.join(os.getcwd(),'uploads')
    # post오버라이딩
    # 포스트맨으로 테스트시는 Body-form data
    def post(self):
        try:
            # 파일업로드 관련 파라미터 추가
            # 첫번째 인자 'upload'는 파라미터명
            # location='files' 디렉토리 경로가 아니다.파일 업로드인 경우 반드시 "files"
            # <form action="http://파이션 Rest 서버 주소/upload" method="post" enctype="multipart/form-data">
            # <input type="file" name="upload"/>
            self.parser.add_argument('upload',location='files',type=werkzeug.datastructures.FileStorage)
            # 모든 파라미터 받기
            args = self.parser.parse_args()
            print('args:',args)
            file = args['upload']
            args['upload']=file.filename
            # 파일 업로드
            file.save(os.path.join(self.upload_path,file.filename))
        except:
            return make_response({'failure':'파일 업로드 최대용량 초과'},413)
        return make_response(args,200)