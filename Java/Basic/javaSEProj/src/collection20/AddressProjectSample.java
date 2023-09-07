package collection20;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import common.utility.CommonUtil;
import console.academy.Person;

public class AddressProjectSample {

	public static void main(String[] args) {//지금해주는거랑 아카데미콘솔 등 잘 조합하면 개인프로젝트, 옛날엔 DB를 만듦 Map으로 모든 성씨를 ("가","ㄱ") 으로 저장한것
		//지금 오름차순은 안함, 개인프로젝트 입력, 출력 끝남
		
		Map<Character,List<Address>> addressBook=new HashMap<>();
		Scanner sc=new Scanner(System.in);
		//1]밸류타입을 null로 초기화
		List<Address> valueList=null;
		while(true) {	
			
			System.out.println("이름을 입력하세요?(종료시 EXIT)");
			String name=sc.nextLine().trim();
			if(name.equalsIgnoreCase("EXIT")) break;
			//2]입력한 이름에서 자음(ㄱ,ㄴ,ㄷ~ㅎ)얻기
			char jaeum=CommonUtil.getJaeum(name); //char 대신 Character해도 됨, 어차피 오토박싱
			//System.out.println(jaeum);
			if(jaeum=='0') {
				System.out.println("한글명이 아닙니다");
				continue;
			}////if
			System.out.println("주소를 입력하세요?");
			String addr=sc.nextLine().trim();
			System.out.println("나이를 입력하세요?");
			int age=Integer.parseInt(sc.nextLine().trim()); //숫자 문자열만 parseInt로 int에 담음
			Date birthday=new Date(new java.util.Date().getTime());
			
			//List<Address> 쓸건데 kie(ㄱ)~hie(ㅎ) 안쓸거임
			//맵컬렉션 addressBook에 'ㄱ'이라는 키값이 있는지 검사, 없으면 'ㄱ'이라는 키값으로 List<Address> 저장된게 없는것
			//없으면 null을 valuesList=new로 Address생성해서 김길동 넣을 것
			//근데 'ㄱ'으로 김길동 있으면 가길동을 새로 add할 것
			if(!addressBook.containsKey(jaeum)){//'ㄱ'을 맵컬렉션이 갖고있는지 판단, 키값이 없는경우
				valueList=new Vector<>(); //value타입인 List<Address>객체 생성
			}
			else {//키값이 존재시, 기존 키값으로 저장된값을 얻어옴, 그리고 valueList에 넣음
				valueList=addressBook.get(jaeum);
			}
			//입력한 정보로 Address타입 생성후 맵의 value에 추가
			valueList.add(new Address(name, age, addr, birthday));
			//4]맵컬렉션에 jaeum키값으로 저장
			addressBook.put(jaeum,valueList); //valueList를 키로 넣으면됨
		}////while			
		//출력]
		Set<Character> keys=addressBook.keySet();
		for(Character key:keys) {
			System.out.println(String.format("[%c로 시작하는 명단]",key));
			List<Address> values=addressBook.get(key);
			for(Address value:values) {
				System.out.println(value);
			}
		}
			
		//문제]찾고자하는 사람의 이름을 입력받아 맵컬렉션에 저장된 사람의 정보를 출력하자
		//해당하는 사람 없으면 "해당하는 사람이 없어요", 찾을때까지 계속 입력
		while(true) {
			System.out.println("찾는 사람의 이름을 입력하세요?");
			String name=sc.nextLine().trim();
			
			boolean isExist=false;//맨날하는 패턴, 위에는 컬렉션에 넣어놓음
			for(Character key:keys) {//위에서 keys꺼냄
				List<Address> values=addressBook.get(key);//밸류꺼냄-> get해서 키 꺼냄
				for(Address value:values) {	//여기 들어가면 key에 해당하는 요소 있음
					if(value.getName().equals(name)) {//이름 있으면
						System.out.println(value);//이름 있으면 출력 후 true됨
						isExist=true;
						break;//for문나감
					}
				}
				if(isExist) break;//break로 가까운 for나옴, key 이미 꺼냄, 찾아올필요없음
			}
			if(isExist) break;//찾으면 true니까 break로 while빠져나감
			else System.out.println("해당하는 사람이 없어요");//못찾으면 여기로
		}	
		
		
	}////main

}////class
