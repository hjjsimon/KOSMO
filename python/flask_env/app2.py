from flask import Flask# Flask 앱 생성용
from flask import request#url에 따른 요청 객체(Request)
from flask import render_template#템플릿 파일(.html) 서비스용
from flask import make_response#응답 객체(Response) 생성용
from flask import jsonify#JSON형태의 문자열로 응답시
from flask import flash,redirect#응답메시지 및 리다이렉트용
from flask_cors import CORS#CORS에러 해결

import os
import json

#app=Flask(__name__,template_folder='webapp')#템플릿 파일의  기본 폴더 위치명 변경시
app=Flask(__name__,)
#JSON응답시 한글이 깨지는 경우
app.config['JSON_AS_ASCII']=False
app.config['MAX_CONTENT_LENGTH'] = 1 * 1024 * 1024#1M
#메시지 Flash를 사용하기 위해서는 반드시 아래 secret_key설정
app.secret_key='&%%%%#$'

CORS(app)

'''
[템플릿 파일 사용하기]
Flask 템플릿 파일은 .html을 사용하고 템플릿 엔진은 jinja2
.html파일을 기본적으로 app.py가 실행되는 같은 위치에 templates폴더에서 찾는다
단,app = Flask(__name__,template_folder='임의의 폴더명')코드로 templates 폴더명 변경 가능
템플릿 파일인 .html파일을 templates 폴더에 저장
'''
#HTML FORM요소로 요청 보내고 받기

#1.str타입으로 응답하기
@app.route('/')# 입력폼으로 이동
def index():
    # 렌더링된 HTML소소 문자열 반환(문자열로 응답)
    # 디폴트로 form.html파일을 templates폴더에서 찾는다
    return render_template('form.html')


@app.route('/form',methods=['POST'])
def form():
    name = request.form['name']
    username = request.form['username']
    password = request.form['password']
    #직접 문자열(str) 반환
    return f'''
                <ul>
                    <li>이름 : {name}</li>
                    <li>아이디 : {username}</li>
                    <li>비밀번호 : {password}</li>
                </ul>   
            '''
#2.Response객체로 응답하기
@app.route('/response')
def response():
    # 브라우저로 응답하는 방법
    # 방법1. 문자열로 응답 - 응답헤더 설정 불가(Content-Type은 디폴트 text/html;charset=UTF-8)
    # render_template('html파일명')함수(즉 렌더링된 HTML소스 문자열 반환) 혹은 직접 문자열 반환
    # 방법2. Response객체로 응답
    # 응답헤더 설정 가능 즉 응답헤더를 설정해야할때 사용(JSON으로 응답시(내부적으로 utf8을 사용))

    response=make_response(render_template('index.html'))
    # 응답헤더 설정
    # response.headers['헤더명'] = '헤더값'
    # response.헤더명='헤더값'
    #response.content_type='text/plain;charset=utf-8'
    response.headers['content-type']='text/html;charset=utf-8'
    return response
#3.정적 자원(이미지,.css,.js등) 사용하기
@app.route('/static')
def static_resource():
    return render_template('static.html')

@app.route('/ajax',methods=['POST'])
def ajax():
    '''
    JSON형태의 데이타를 받을때: request.get_json()는 JSON형태의 문자열을 파이썬 객체(딕셔너리)로 변환
    JSON형태의 문자열로 응답시: jsonify(딕션너리 객체)해서 Response객체로 반환
    request.is_json : JSON형태의 문자열인지 판단
    request.content_type : 컨텐츠의 타입
    '''
    print('JSON형태의 문자열인지 판단:',request.is_json)
    print('요청 컨텐츠 타입:', request.content_type)
    json = None
    if request.is_json:
        json = request.get_json()
        print('클라이언트로부터 받은 데이타:',json)
        print('클라이언트로부터 받은 데이타 타입:', type(json))#<class 'dict'>
    # 브라우저로 바로 JSON응답시 한글이 깨지는 경우(디폴트가 ascii 인코딩) UTF8로 인코딩
    # app = Flask(__name__,template_folder='templates') 다음 라인에
    # app.config['JSON_AS_ASCII'] = False 추가
    if json:
        json['loc']='가산동'
        return jsonify(json)
    else:
        return jsonify({'name':'샘플용 데이타입니다','loc':'가산디지털단지역'})
#4. jinja2 템플릿엔진 사용하기
#https://jinja.palletsprojects.com/en/3.0.x/templates/
'''
템플릿 파일인 .html에서 파이썬 코드 사용하기
{{변수}} 는 출력문(EL)과 같다 즉 ${JSTL변수} 혹은 <%= %>) 
{% 파이썬 코드 %} (스크립팅요소인 <% 자바코드 %>와 같다)
{#   주석   #}  (<%-- --%>와 같다)
for문이나 if문은 반드시 블락을 닫아야 한다
{% for i in range(10) %}

{% endfor %}
{% if True %}

{% endif %} 식으로
'''
@app.route('/jinja2')
def jinja2():
    title ='템플릿 엔진 JINJA2'
    # render_template('템플릿파일명',키워드인수=값,....) 즉 키워드 인수를 html페이지에서 변수로 사용
    return render_template('jinja2/jinja2.html',header='JINJA2',title=title)
@app.route('/jinja2/extends')
def extends():
    return render_template('jinja2/jinja2_1.html',years=2023,title='템플릿 상속받기',header='JINJA2상속')
#5. 파일 업로드
#https://flask.palletsprojects.com/en/2.2.x/patterns/fileuploads/
@app.route('/upload')#업로드 폼으로 이동
def upload():
    return render_template('upload.html')

#업로드 파일 경로 설정
UPLOAD_FOLDER =os.path.join(os.getcwd(),'upload')
app.config['UPLOAD_FOLDER']=UPLOAD_FOLDER
print(f'value:{app.config},type:{type(app.config)}')
#허용 확장자
ALLOWED_EXTENSIONS=set(['txt','png','jpg','gif'])
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/upload',methods=['POST'])
def upload_ok():
    print('요청 URL:',request.url)
    print('os.path.isdir(UPLOAD_FOLDER):',os.path.isdir(UPLOAD_FOLDER))

    try:
        if not os.path.isdir(UPLOAD_FOLDER):
            os.mkdir(UPLOAD_FOLDER)
        """
        # 파일하나 업로드: request.files['파라미터명'] 으로 파일을 받는다
        files = request.files['upload']
        title = request.form['title']
        print('files:', files)
        print('dir(files):', dir(files))
        if len(files.filename)== 0:
            '''
            메시지 플래싱은 사용자에게 한 번만 보여줄 메시지를 저장하고 다음 요청에서 표시하는 기능
            주로 사용자에게 알림, 성공 또는 오류 메시지를 표시할 때 유용
            템플릿에서는 get_flashed_messages() 함수를 사용하여 플래싱된 메시지를 가져온다. 
            이 함수는 플래싱된 메시지를 리스트로 반환하며 플래싱된 메시지가 없을 경우 빈 리스트를 반환
            단,플라스크 앱의 secret_key를 반드시 설정해야 한다
            '''
            flash('파일을 선택하세요')
            return redirect(request.url)

        if files and allowed_file(files.filename):
            print('values{},type:{}'.format(files,type(files)))
            print('업로드한 파일의 컨텐츠 타입:',files.content_type)
            filename = files.filename
            print('업로드한 파일명:', filename)
            files.save(os.path.join(app.config['UPLOAD_FOLDER'],filename))
            flash('파일 업로드 성공')
            #return jsonify({'success':filename,'title':title})
            return json.dumps({'success':filename,'title':title},ensure_ascii=False)
        else:
            flash('허용된 파일 타입이 아닙니다')
            return redirect(request.url)
        """
        #파일 여러개 업로드: request.files.getlist('파라미터명') 으로 파일을 받는다
        filenames = []  # 다중 파일 업로드
        files = request.files.getlist('upload')
        title = request.form['title']
        print('len(files):',len(files))#파일 미 첨부시 1 : <FileStorage: '' ('application/octet-stream')>
        print('value:{},type:{}'.format(files,type(files)))
        print('files[0].filename:',files[0].filename)#0번방의 파일명
        if len(files[0].filename)==0:
            flash('파일을 선택하세요')
            return redirect(request.url)
        for file in files:#file은 FileStorage객체
            if allowed_file(file.filename):
                filenames.append(file.filename)
                file.save(os.path.join(app.config['UPLOAD_FOLDER'],file.filename))
            else:
                #정상적인 파일 삭제
                for f in filenames:
                    if os.path.exists(os.path.join(app.config['UPLOAD_FOLDER'],f)):
                        os.remove(os.path.join(app.config['UPLOAD_FOLDER'],f))
                #flash('허용된 파일 타입이 아닙니다')
                #return redirect(request.url)
                raise Exception()

        flash('파일 업로드 성공')
        return json.dumps({'success':','.join(filenames),'title':title},ensure_ascii=False)



    except Exception as e:
        print(e)
        flash('최대 업로드 용량을 초과하였거나 허용되지 않는 파일입니다')
        return redirect(request.url)

@app.route('/coffees',methods=['POST'])
def coffees():
    return jsonify({'store':'starbucks','stores_counts':'100개'})


if __name__ =='__main__':
    app.run(host='0.0.0.0',port=8282,debug=True)