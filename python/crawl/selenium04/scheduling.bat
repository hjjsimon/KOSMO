@REM @echo off
echo Start Crawling
@REM  가상환경 활성화
CD C:\Users\Kosmo\anaconda3\Scripts
CALL C:\Users\Kosmo\anaconda3\Scripts\activate.bat crawl
@REM 실행할 .py가 있는 경로로 이동
D:
CD D:\CCH\Workspace\python\crawl\selenium04
@REM 스크립트 파일실행
@REM python selenium04.py
python selenium04.py
@REM 콘솔창 바로 종료되지 않도록 pause 즉 pause주석시 콘솔창 바로 종료됨
@REM pause
exit
@REM 현재 배치 파일을 작업 스케줄러에 등록

