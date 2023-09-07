'''
[패턴을 표현하는 문자]
. : 임의의 한글자를 의미
예) a.b(acb,afb........)
*  :  * 바로 앞의 문자가 없거나 한 개 이상이 있을 경우
예) a*b (b,ab,aab,aaab....)
+ : +바로 앞의 문자가 최소 한개 이상일때
예) a+b (ab,aab,aaab...)
? : ?바로 앞의 문자가 없거나 한 개 존재하는 경우
예) a?b (b,ab,)
^ : ^ 뒤에 문자열과 같은 문자열로 시작하는 경우
      즉 문자열의 시작을 의미
    [ ]안에서 ^ 는 [ ]안의 문자를 제외한 문자를 의미
예) ^ab(ab,abc,abdr...)
$ : $앞의 문자열과 같은 문자열로 끝나는 경우 즉 문자열의 끝을 의미
예) ab$ (avab,aab,abab...)
[] : []안에 문자열중에 하나만의 문자만을 의미
예) [a-z](a부터 z까지중 한 문자)
    [0-9](0부터 9까지 숫자중 한 문자)
 [abc](a혹은 b혹은 c)
{} :{}앞의 문자열의 개수를 의미 문자{최소개수,최대개수}
    최소개수는 반드시 있어야하고 최대개수가 없는경우는 1개또는 1개이상을 의미
 하고 숫자 하나만을 적어 주엇을때는 그 숫자만큼의 개수를 의미한다.
예) abc{1,2} (abc,abcc)
    a{3} (aaa)
 a{1,} (a,aa,aaa....)
 | : or 연산자
 [^ ] : []안의 문자는 사용 못한다는 의미
예) [^abc] (a나 b나 혹은 c를 포함하지 않은 한 문자)
    [^0-9] (0에서 9까지의 숫자를 포함하지 않은 한 문자)
\s	공백 문자
\S	공백 문자가 아닌 나머지 문자
\w	알파벳이나 숫자
\W	알파벳이나 숫자를 제외한 문자
\d	숫자 [0-9]와 동일
\D	숫자를 제외한 모든 문자
() 그룹지정
'''
import re #1. 정규표현식 라이브러리 임포트(이거 임포트해야 정규표현식 가능)
print(re.__file__) #패키지 위치
print('[1.패턴객체 사용 - 패턴객체.함수()]') #패턴객체 재사용 가능
email = input('이메일을 입력하세요?')
#re.compile('정규표현식') 로 pattern객체 생성

# 방법1) 아래 이스케이프 아니고 정규표현식이라는 의미로 \w에 \하나 더붙임
# pattern = re.compile('[a-zA-Z]+@\\w+\\.[a-zA-Z]{2,3}')

# 방법2) 앞에 r붙이면 이스케이프 처리 안됨, 더 편함(r은 raw String 약자, f가 formatString 약자이듯)
# raw string은 순수 문자임을 표시, \하나만 써도 이스케이프 처리 안됨
pattern = re.compile(r'[a-zA-Z]+@\w+\.[a-zA-Z]{2,3}')

print(f'value:{pattern},type:{type(pattern)}')
# value:re.compile('[a-zA-Z]+@\\w+\\.[a-zA-Z]{2,3}'),type:<class 're.Pattern'>
# re패키지의 Pattern 클래스
print(dir(pattern))

# 패턴객체.match(대상문자열)로 Match객체 생성
# match()메소드는 대상 문자열이 패턴에 일치하면 Match 객체 반환, 일치하지 않으면 None 반환
match = pattern.match(email) #사용자가 입력한 email 인자로 받음, 일치하는지 확인
print(f'value:{match},type:{type(match)}')
# 정규표현식 일치하면 아래, 일치하지 않으면 그 아래
# 이메일을 입력하세요?ab@c.com
# value:<re.Match object; span=(0, 8), match='ab@c.com'>,type:<class 're.Match'>
# 이메일을 입력하세요?123
# value:None,type:<class 'NoneType'>
if match:
    print('이메일 형식입니다')
else:
    print('이메일 형식이 아닙니다')

# 패턴객체.findall(대상문자열): 패턴과 일치하는 모든 문자열을 리스트로 반환, 불일치시 []반환
list_ = pattern.findall(email)
print(f'value:{list_},type:{type(list_)}')
# 이메일 형식이 아닙니다
# value:[],type:<class 'list'>
# 이메일 형식입니다
# value:['aaa@a.com'],type:<class 'list'>
if list_:
    print('이메일 형식입니다')
else:
    print('이메일 형식이 아닙니다')

print('[2.패턴객체 미사용 - re함수()') #재사용하지않고 한번만 사용시
print(re.match(r'[a-zA-Z]+@\w+\.[a-zA-Z]{2,3}',email))
print(re.findall(r'[a-zA-Z]+@\w+\.[a-zA-Z]{2,3}',email))
# 패턴일치: None / []
# 패턴불일치: <re.Match object; span=(0, 9), match='abc@a.com'> / ['abc@a.com']

log="[17.07.11 23:29:11] [INFO ]  [eclipse.galileo-bean-thread-50618297 galileo.site.SiteBean:317 ] - ##galileo_bean end. MessageExchange_ID:ID:localhost-15a6308ba1c-6:86071562";
pattern = re.compile(r'\[(\d{2}\.\d{2}\.\d{2}\s\d{2}:\d{2}:[0-9]{2})\]\s\[([A-Z]{4})\s\]\s{2}\[(.+)\]\s-\s(.+)')
match = pattern.match(log)
print(match)

# 그룹으로 지정한 패턴을 추출
'''
1] groups()는 일치하는 문자열을 튜플로 반환
2] group(인덱스)는 인덱스에 해당하는 문자열 반환
3] group() 또는 group(0)은 전체 문자열 반환
'''
if match:
    print(f'그룹:{match.groups()}')
    print(f'그룹의 수:{len(match.groups())}')
    print(f'매칭된 전체 문자열:{match.group()}')
    print(f'매칭된 전체 문자열:{match.group(0)}')
    print(f'매칭된 인덱스가 1인 문자열:{match.group(1)}')
    print(f'매칭된 시작되는 대상 문자열의 인덱스:{match.start()}')
    print(f'매칭된 끝나는 대상 문자열의 인덱스:{match.end()},대상 문자열의 길이:{len(log)}')
    for word in match.groups(): #튜플 반환
        print(word.strip()) # strip()는 자바 trim() 동일













































