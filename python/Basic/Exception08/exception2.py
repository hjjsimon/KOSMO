'''
try:
    예외가 발생할 만한 코드
except 예외명1:
    예외명1이 발생했을 때 처리할 코드
except 예외명2:
    예외명2가 발생했을 때 처리할 코드

혹은 여러 예외 동시 처리
try:
    예외가 발생할 만한 코드
except (예외명1,예외명2) :
    예외명1 혹은 2가 발생했을 때 처리할 코드
'''

try:
    list_ = list(map(int,input('두 개의 숫자를 입력(공백)?').split()))#숫자가 아닐때 ValueError
    result = list_[0] / list_[1]#0으로 나눌때 ZeroDivisionError: division by zero
    print('{one}/{two}의 결과:{three}'.format(three=result,two=list_[1],one=list_[0]))
    index = int(input('인덱스를 입력하세요?'))
    print('인덱싱:',list_[index])#인덱스 범위 벗어났을때 IndexError: list index out of range
    '''
    except ValueError:
        print('숫자만...')
    except ZeroDivisionError:
        print('0으로 나눌수 없어요')
    except IndexError:
        print('해당 인덱스가 없어요')
    except Exception as e:
        print('문의하세요:',e)
    '''
    '''
    except (ValueError,ZeroDivisionError,IndexError) as e:
        print('에러 발생:',e)
    except Exception as e:
        print('문의하세요:',e)
    '''
    '''
        #Exception이 모든 에러를 다 잡는다.자바처럼 Unreachable Code error는 발생하지 않는다
        except Exception as e:
            print('문의하세요:', e)
        except (ValueError,ZeroDivisionError,IndexError) as e:
                print('에러 발생:',e)
    '''
except Exception as e:
        if isinstance(e,ValueError):
            print('숫자만...')
        elif isinstance(e,ZeroDivisionError):
            print('0으로 나눌수 없어요')
        elif isinstance(e,IndexError):
            print('해당 인덱스가 없어요')
        else:
            print('문의하세요:', e)
