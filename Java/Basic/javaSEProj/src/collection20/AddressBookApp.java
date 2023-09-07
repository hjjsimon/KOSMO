package collection20;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


//한명의 이름/나이/주소/생일을 저장하는 클래스]
//방법1. Comparable인터페이스 구현-Collections.sort(List컬렉션(오름차순)) 인자 하나짜리 사용
//Collections.sort(리스트컬렉션)를 적용하려면 리스트컬렉션에 저장된 객체(타입)은 반드시 Comparable을 구현해야한다 -> 비교? Address안의 값들을 비교하고싶음 <T>에 Comparable 쓰면 됨 
class Address /*implements Comparable<Address>*/{ //List계열 컬렉션이라고 무조건 오름차순 아님, List계열에 저장된 객체가 반드시 Comparable을 구현해야함, List<> 에 넣은Address를 임플리먼트해야함 
	//[멤버변수]
	private String name;
	private int age;
	private String addr;
	private Date birthday;//sql에 있는걸 임포트
	//정렬시 사용할 상수(방법1,2모두)
	public static final int SORT_BY_NAME=1; //이름으로 정렬, 디폴트
	public static final int SORT_BY_AGE=2; //나이로 정렬
	public static final int SORT_BY_ADDRESS=3; //주소로 정렬
	public static final int SORT_BY_BIRTHDAY=4; //생일로 정렬
	//방법1일때-정렬시 구분자로 사용할 정적필드(디폴트는 이름으로 정렬)
//	public static int sortField=SORT_BY_NAME; //정적멤버필드 하나 정의, 위에 솔트바이네임 인트니까 int로 넣어줌 ->compareTo만 잘 쓰면 됨
	
	
	//[인자생성자] ->get,set안만들고 인자생성자로 초기화
	public Address(String name, int age, String addr, Date birthday) {
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.birthday = birthday;
	}
	//getter 안만들고 toString오버라이딩 ->소스 제너레이트 투 스트링
	@Override
	public String toString() {
		return String.format("[이름:%s,나이:%s,주소:%s,생일:%s]",name,age,addr,birthday);
	}
	//방법1일때]
/*	@Override
	public int compareTo(Address target) {//Address에 빨간줄-> 오버라이드해결, 변수명 o target으로 바꿈, 반환값 int해주면 내부적으로 알아서 자동으로 정렬해줌(이런건 자동 Comparable되어있음)
		switch(sortField) {//정적멤버니까 모두 공유, 선생님은 오름차순만 해줌 ㅏ->ㅑ->ㅓ->ㅕ->ㅗ
		//앞에 있으면 오름차순, 숫자일때는 -, 문자열일때는 compareTo() -> Date는 짜증남, 숫자로 바꾸던가(getTime), 문자로 바꾸던가 해도됨
			case SORT_BY_NAME: 
				return name.compareTo(target.name); //오름차순, name/target.name 반대면 내림차순
			case SORT_BY_AGE:
				return age - target.age; 
			case SORT_BY_ADDRESS:
				return addr.compareTo(target.addr);
			default: return //(int)birthday.getTime()-target.birthday.getTime(); //util의 데이트 상속받아서 getTime 그대로 쓰면 됨, 근데 long형이라 숫자 큼, 형변환하면 됨
			birthday.toString().compareTo(target.birthday.toString()); //위에 long이라 int에 담으면 문제가능성, 문자로 바꿔서 비교함
		}
	}
*/	
	//방법2일때]-> implements필요없음, 게터만 추가, 세터는 x/ 소스->제너레이트 게터로 선별
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getAddr() {
		return addr;
	}
	public Date getBirthday() {
		return birthday;
	}
}

public class AddressBookApp {

	//방법2] 정렬용 메소드 정의해서 사용
	//1.리스트 컬렉션에 저장할 객체인 Address가 Comparable인터페이스를 구현할 필요가 ㅇ벗다.
	//2.정렬시 구분자로 사용할 정적필드도 필요없다 -> sortField 했던거
	//정렬시 Collections.sort(List컬렉션,Comparator타입) 인자 두개짜리 사용 -> Comparator타입 여기서 오름차순 내림차순 바로 가능/ 인자 1개짜리는 
	public static void sort(int sortField,List<Address> values) { //소트필드는 인자로 받고 ->List<Address> values 를 솔트할것
		Collections.sort(values, new Comparator<Address>() {//인자2개짜리 sort//리스트컬렉션에 저장된 Address정렬
			@Override
			public int compare(Address src, Address target) {//빨간줄 해결 메소드
				switch(sortField) { //sortField Address에 만들어놓음, 클래스명으로 접근하면 됨			
					case Address.SORT_BY_NAME: 
						return src.getName().compareTo(target.getName()); //name 프라이빗이라 접근x, 그래서 getname 만든것
					case Address.SORT_BY_AGE:
						return src.getAge() - target.getAge(); 
					case Address.SORT_BY_ADDRESS:
						return src.getAddr().compareTo(target.getAddr());
					default: return //(int)birthday.getTime()-target.birthday.getTime(); //util의 데이트 상속받아서 getTime 그대로 쓰면 됨, 근데 long형이라 숫자 큼, 형변환하면 됨
					src.getBirthday().toString().compareTo(target.getAddr()); //위에 long이라 int에 담으면 문제가능성, 문자로 바꿔서 비교함
				}
			}
			
		}); 
	}
	
	
	
	
	public static void main(String[] args) {

		//'ㄱ'이라는 캐릭터 char형 있으면 이를 key(키)로, 값은 "가길동", "곽길동" 등 String으로 value(값)을
		//배열->for문 이듯이 지금은 Map 컬렉션이 좋음, 키에 값을 쌍으로 저장하니까!
		//Map<k,v> -> Object라서?? k는 char 기본자료형 [x] Character [o] / v는 String 하면 가길동하고 곽길동 저장하면 날아감
		//물론 가길동@곽길동 이런 식으로 추가하다가 구분자로 정규표현식이나 스플릿 써서 구분은 가능 근데 불편, List로 가길동, 곽길동 쭉 리스트니까 가져다쓰면 됨 이것도 String임
		//1]자음을 Key값-String("ㄱ") 혹은 Character('ㄱ')
		//List계열 컬렉션을 Value값-List<String>
		//Map계열 컬렉션-Value에는 자음에 해당하는 이름들 저장
		Map<Character, List<String>> nameAddresses=new HashMap<>();
		//static <T> List<T>
		//Class Arrays -> asList(T... a) /Returns a fixed-size list backed by the specified array. -> static <T> List<T> ->동일한 타입의 여러개 값을 List컬렉션의 요소로 만들때 쓰는 메소드
		//List가 내부적으로 배열형태로 구성, T에 String이면 List<String>으로 반환 ->List<T>, T가 String이면 String객체를 저장하는 List 컬렉션
		//1-1]이름저장용 List계열 컬렉션객체 생성 및 이름저장
		List<String> kieyeok=Arrays.asList("고길동","곽길동","가길동","강길동"); //이게 편함, 아니면 객체 생성하고 하나씩 list.add 해야함
		List<String> nieoun=Arrays.asList("노길동","나길동","남길동"); 
		//1-2]Map컬렉션(nameAddresses)에 이름이 저장된 List계열 컬렉션 저장 
		nameAddresses.put('ㄱ', kieyeok);//ㄱ이라는 이름으로 kieyok 리스트를 저장함
		nameAddresses.put('ㄴ', nieoun);
		System.out.println("[키값을 알때]");
		List<String> values=nameAddresses.get('ㄱ'); //List<String>타입 반환이라 거기에 담음 -> 바로 확장for문 적용
		for(String value:values) System.out.println(value); //'ㄱ'이라는 키값으로 꺼내옴
		System.out.println("[키값을 모를때]");//암기! 키셋, 확장for문, 겟키
		//1]keySet()으로 Set컬렉션 얻기
		Set<Character> keys=nameAddresses.keySet(); //key가 set<Character>라 거기에 담음
		//2]Set컬렉션에 확장for문 적용
		for(Character key:keys) {
			System.out.println(String.format("[%c로 시작하는 명단]", key));//ln 붙었으니 %n안해도 됨
			//3]get(키값)으로 value얻기
			values=nameAddresses.get(key); //key에 담아놨음, Value는 List<String>타입이니까 변수 그대로 가져다씀
			for(String value:values) System.out.println(value);
		}
		//'ㄱ' 하나로 "고길동", 20, "가산동", 2023.03.29 이런걸 다 저장하려면
		//List<Person> ->Person클래스를 만들어서 해도 됨, 보통 이렇게 함
		//근데 Person클래스를 따로 안만들려면 List<Map<String,Object>>
		//한사람 정보를 담을 Map을 만들어서 이름이라는 키로 고길동 저장, 나이라는 키로 20 이런식으로 각각 Map을 만들어서 List에 저장하는 것
		//2] 초성을 Key값-String 혹은 Character
		//List계열 컬렉션을 Value값-List<Map<String,Object>>
		//Map계열 컬렉션-Value에는 초성에 해당하는 이름,주소,전화번호,나이들 저장 -> 다양하니까 부모인 Object로 저장
		Map<Character,List<Map<String,Object>>> address=new HashMap<>();
		//2-1]이름/전화번호/주소/나이가 저장된 맵 컬렉션을 저장할 리스트계열 컬렉션 객체 생성
		List<Map<String,Object>> kie=new Vector<>(); //ㄱ
		List<Map<String,Object>> nie=new Vector<>();//ㄴ이라 nie
		//Map을 사람 하나당 하나씩 만들어야함, Person도 사람 하나당 하나씩 만드는데 똑같음
		Map<String,Object> k=new HashMap<>();
		k.put("name", "고길동");
		k.put("age", 20);
		k.put("addr", "가산동");
		k.put("birthday", new Date(new java.util.Date().getTime())); //new Date넓은게 sql로 임포트, 그래서 util은 직접 접근
		k=new HashMap<>();//다시 map객체 생성
		k.put("name", "곽길동");
		k.put("age", 30);
		k.put("addr", "서초동");
		k.put("birthday", new Date(new java.util.Date().getTime()));
		kie.add(k);
		Map<String,Object> n=new HashMap<>();
		n.put("name", "나길동");
		n.put("age", 25);
		n.put("addr", "나산동");
		n.put("birthday", new Date(new java.util.Date().getTime()));
		nie.add(n);
		n=new HashMap<>();
		n.put("name", "노길동");
		n.put("age", 35);
		n.put("addr", "방배동");
		n.put("birthday", new Date(new java.util.Date().getTime()));
		nie.add(n);
		//주소록 저장하는 맵컬렉션에 리스트저장]
		address.put('ㄱ', kie);
		address.put('ㄴ', nie);
		//객체 꺼내올 때]
		//Set/List계열(Collection계열) 무조건 확장for문 사용
		//Map계열은 keySet()으로 키값들이 저장된 Set계열 반환받은 후 확장for사용
		System.out.println("[키값을 알때]");
		List<Map<String,Object>> lists=address.get('ㄴ'); //lists에서 꺼내오면 맵컬렉션타입 
		for(Map<String,Object> map:lists) {//밸류를 꺼내온게 또 맵, 맵이면 키셋!
			Set<String> sets=map.keySet(); //맵의 키가 보라색 String이니까 String에 담음
			for(String key:sets){//키값 꺼내오면 String 또 확장 for문
				Object value=map.get(key);
				System.out.println(String.format("%s:%s",key,value));
			}
		}
		System.out.println("[키값을 모를때]");
		keys=address.keySet();//어드레스가 맵이니까 키셋, 셋케릭터 keys에 담았으니까 그냥 씀
		for(Character key:keys) {
			System.out.println(String.format("[%c로 시작하는 명단]",key));
			lists=address.get(key); 
			for(Map<String,Object> map:lists) {
				Set<String> sets=map.keySet(); 
				for(String ky:sets){ //key중복돼서 ky씀
					Object value=map.get(ky);
					System.out.println(String.format("%s:%s",ky,value));
				}
			}
		}
		//3]Address클래스를 사용해서 2]번처럼 구현
		//Map<Character,List<Map<String,Object>>> address=new HashMap<>(); -> 기존쓴거
		Map<Character,List<Address>> addressBook=new HashMap<>(); //위에서 만든 클래스 써서 Person처럼 바꿈
		//3-1]주소를 저장할 리스트계열 컬렉션 생성
		List<Address> kiec=new Vector<>();//클래스 써서 c, kiec이라는 이름으로 addressBook에 저장
		List<Address> niec=new Vector<>();
		//3-2]주소저장
		long epochTime=new java.util.Date().getTime();//자꾸 쓰면 귀찮, 변수로 뺌
		//kiec.add(new Address("곽길동",40,"가산동",new Date(new java.util.Date().getTime())));
		kiec.add(new Address("곽길동",40,"가산동",new Date(epochTime)));
		kiec.add(new Address("고길동",20,"청담동",new Date(epochTime)));
		kiec.add(new Address("가길동",35,"사당동",new Date(epochTime)));
		niec.add(new Address("노길동",45,"가산동1",new Date(epochTime)));
		niec.add(new Address("나길동",25,"청담동1",new Date(epochTime)));
		niec.add(new Address("남길동",35,"사당동1",new Date(epochTime)));
		//3-3]키값으로 리스트 컬렉션 객체를 맵(addressBook)에 저장
		addressBook.put('ㄱ', kiec);
		addressBook.put('ㄴ', niec);
		System.out.println("[한 사람의 정보를 저장하는 클래스를 사용해서 구현]");
		keys=addressBook.keySet();
		for(Character key:keys) {
			System.out.println(String.format("[%c로 시작하는 명단]",key));
			List<Address> vals=addressBook.get(key);//키 알고있으니 겟키, 밸류 List<Address>그대로 반환
			//출력전 정렬
			//[방법1으로 정렬시]
			//1.이름으로 정렬
			//Collections.sort(vals);//디폴트 이름으로 정렬, 오름차순 정렬 -> 가,고,과, 나,남,노 이름으로 정렬됨
			//2.나이로 정렬
			//Address.sortField=Address.SORT_BY_AGE; //정적필드 sortField 나이로 정렬
			//Collections.sort(vals); //20,35,40 25,35,45 오름차순
			//[방법2로 정렬시]
			sort(Address.SORT_BY_ADDRESS,vals);//주소로 정렬, 오름차순 가->사->청
			//리스트컬렉션 꺼냈으니 바로 확장for문
			for(Address val:vals){
				System.out.println(val); //toString 오버라이딩 해놨으니까 주소 말고 바로 나옴
			}
		}
		
	}////main

}////class
