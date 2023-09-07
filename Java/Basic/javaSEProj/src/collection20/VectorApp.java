package collection20;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.lang.model.util.Elements;

import console.academy.Student;

//Vector: list계열 컬렉션(선생님은 이걸 많이 씀)-> 차이는 성능, 동기화 여부
public class VectorApp {

	public static void main(String[] args) {
		//컬렉션 객체 생성]
		Vector<Student> vec=new Vector<>();
		//컬렉션에 저장할 객체 생성]
		Student stu1=new Student("가길동",30,"2019학번");
		Student stu2=new Student("나길동",20,"2023학번");
		Student stu3=new Student("라길동",25,"2020학번");
		Student stu4=new Student("다길동",40,"2018학번");
		//객체 저장]
		vec.add(stu1);
		vec.add(stu2);
		vec.add(stu3);
		vec.add(stu4);
		//출력] 
		System.out.println("[일반 for문:get(index)]");
		for(int i=0;i<vec.size();i++) vec.get(i).print();
		System.out.println("[확장 for문]");
		for(Student s:vec) s.print();
		System.out.println("[반복기]");
		Iterator<Student> it=vec.iterator(); 
		while(it.hasNext()) it.next().print();
		//Vector는 꺼내올 수 있는게 하나 더 있음, elements메소드, 위랑 똑같음
		//열거형으로 꺼내오기: elements()메소드로 Enumeration 타입얻기
		//Enumeration타입의 주요 메소드]
		//hasMoreElements(): 저장된 객체가 있으면 true, 없으면 false
		//nextElement(); 저장된 객체 얻을 때
		System.out.println("[열거형]");
		Enumeration<Student> em=vec.elements(); 
		while(em.hasMoreElements()) em.nextElement().print();
		//삭제]
		//인덱스로 삭제]
//		System.out.println("삭제된 학생의 이름:"+vec.remove(1).name); //나길동 삭제
		//인스턴스(주소)로 삭제]
//		System.out.println(vec.remove(stu3)); //다길동 삭제, 삭제하면 true
	
		//문제] vec컬렉션에 저장된 Student객체들을 나이가 높은순으로 내림차순 재배치 후 출력(0번방에 다길동)
		//단, List계열의 get(인덱스) 및 set(인덱스,객체)메소드사용
		
/*		List<Student> list2=new Vector<>();
		
		for(int i=0;i<list2.size()-1;i++) {
			for(int k=i+1 ; k<list2.size() ; k++) {
				if(list2.get(i).age<list2.get(k).age) {
					int temp=list2.get(i).age;
					       =list2.get(k).age;
					list2.get(k).age= temp;
				}
			}
		}
*/		
		for(int i=0;i<vec.size()-1;i++) { //-1없어도 됨, 마지막방끼리 비교해봤자 동일, 반복1회 더 하면 효율x
			for(int k=i+1;k<vec.size();k++) {
				if(vec.get(i).age<vec.get(k).age) {
					Student temp=vec.get(i); //꺼내온건 Student타입이라 Student에 담아야함
					vec.set(i,vec.get(k)); //i번째 방을 k로 세팅, 이전에 = 대신 set을 써야함
					vec.set(k,temp);
				}
			}
		}
		System.out.println("[나이순으로 정렬]");
		for(Student s:vec) s.print();
	}

}
