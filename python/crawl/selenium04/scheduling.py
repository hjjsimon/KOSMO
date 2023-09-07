'''
스케줄링 하기
1. 파이썬의 schedule라이브러리로 스케줄링
2. .bat(배치파일)만들어서 윈도우의 작업스케줄러로 스케줄링(리눅스는 cron)
'''

import schedule
import time
from selenium04 import starbucks

def job():
    starbucks()
schedule.every().day.at("13:15").do(job)

while True:
    schedule.run_pending()
    time.sleep(1)