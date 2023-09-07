from flask_restful import abort
#더미 데이타
TODOS ={'todo1':{'task':'RESTFul API만들기'},
        'todo2':{'task':'Flask 앱 만들기'},
        'todo3':{'task':'그냥 놀기'}
        }
def abort_if_todo_dosent_exist(todo_id):
    if  todo_id not in TODOS:
        abort(404,message=f'Todo {todo_id} dosen\'t exist')