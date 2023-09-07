#[2. csv파일에서 읽어서 파이썬 객체에 저장]
import csv

list_ =[]#요소가 list
dict_ =[]#요소가 dict

with open('users_list.csv','r',encoding='utf8') as f:
    reader=csv.reader(f)
    print(dir(reader))# __iter__가 있다 즉 반복가능한 객체
    '''
    # 방법1:__iter__() 미 사용
    #print(list(reader))
    records_new =list(reader)
    header=records_new[0]
    for index,record in enumerate(records_new):
        if index !=0:
            list_.append(record)
            dict_.append(dict(zip(header,record)))
    '''
    # 방법2:__iter__()사용
    it=reader.__iter__()
    header=it.__next__()
    for index ,record in enumerate(reader):
        list_.append(record)
        dict_.append(dict(zip(header,record)))

print(list_)
print(dict_)


