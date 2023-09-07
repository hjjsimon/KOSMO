from flask_restful import Resource
from flask import make_response
from api.starbucks_seoul_all import starbucks

class Starbucks(Resource):
    def get(self):
        stores=starbucks()
        print(stores)
        return make_response(stores,200)