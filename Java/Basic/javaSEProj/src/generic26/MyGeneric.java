package generic26;

import java.util.List;

/*

1]제너릭(Generic)
- JDK5 에 처음 도입
- 클래스나 메소드에서 사용할 데이터 타입을 클래스 정의시에 결정하는 것이 아니라, 사용시 결정하는 것이다. 
  즉, 클래스(제너릭 클래스.ex.HashSet, 객체생성시 T 결정)는 객체생성, 메소드(제너릭 메소드.ex.add메소드)는 호출시 결정하는 기법이다.(그냥 <>없으면 그냥 클래스, 있으면 제너릭 클래스)
- 클래스나 메소드 사용시 타입을 지정할 수 있도록 하는 일반(Generic)화된 타입이라는 의미이다.
- 제너릭은 사용시 타입을 지정하기 때문에 컴파일시 타입 체크를 해주는 기능(Type Safety)를 통해 런타임시 에러를 사전에 방지할 수 있다.    
- JDK1.7부터 클래스를 인스턴스화 할때 new 클래스명<파라미터 타입>()의  '파라미터 타입'을 생략할 수 있다. 즉 new 클래스명<>()로 인스턴스화 할 수 있다
- 제너릭의 장점 3개: 1)컴파일시 타입 체크 2)형변환 불필요(꺼내오면 해당타입) 3)코드의 중복 제거    

	 ex.public interface MyInter<T>{ -><T>있으니까 제너릭 인터페이스 
			void add(T a,T b) ->그림그리려면 형태필요, 메소드의 형태인 {}가 없음, 그래서 메모리에 만들 수 없음, 그래서 인스턴스화 불가함
	    }
		
		MyInter<Double> inter=new MyInter<>(); ->[x]제너릭이어도 추상메소드밖에 없음, 형태가 없어 메모리에 그릴 수 없음, 인터페이스는 인스턴스화 불가
		
		-제너릭인터페이스 사용(익명클래스로 객체화하여 이질화, 왼쪽 MyInter가 부모) 
		MyInter<Double> inter=new MyInter<>(){ ->[o]MyInter 상속받은 익명클래스는 가능, 부모의 이름을 빌림 이름없음, 아래처럼 오버라이딩 하라고 나옴, 위처럼 {}괄호 없으면 불가 
			public void add(Double a,Double b){ ->타입파라미터 더블로 지정해서 더블로 바뀐 오버라이딩이 된 것, 이런식으로 맨위의 T만 만들면 다양한 타입 만들 수 있어서 중복코딩제거가능
												  그렇지 않으면 위의 T를 Double로, String으로... 이런식으로 여러개 중복코딩 계속 해야함
 				~~~~~~~~						->구현부는 추상메소드에 맞게 구현하면 된다~
			}
		};	
		
2]제너릭 타입
- 타입을 파라미터로 가지는 클래스(제너릭 클래스)와 인터페이스(제너릭 인터페이스)들을 제너릭 타입이라 한다(즉, Integer 타입,String타입이 아닌 일반화된 타입이다)
  타입 파라미터는 일반적으로 대문자 알파벳 한 글자로 보통 표현한다. 제네릭 타입은 두 개 이상의 멀티 파라미터를 이용할 수 있다.이때 각 타입 파라미터는 콤마로 구분
- 타입 파라미터는 static 이 붙는 정적 멤버변수 나 정적 메소드 그리고 new로 초기화하는 인스턴스 멤버 변수에는 사용할 수 없다. (단,제너릭 메소드에는 static을 붙일 수 있다***)

	public class MyGeneric<T>{
		public static T name -> [x]static변수 불가, 이건 클래스 로드할때 생김, 근데 T는 인스턴스화시 생김, 당연히 불가
		public static T add(){} -> [x]static메소드 당연히 불가 
	}	

	public T s; ->[o]이건 됨
	public T s=new Student(); ->[x]이건 불가

	  public class 클래스<T[,K,V,..]> { ... } -><T>있으니까 제너릭 클래스
      public interface 인터페이스<T[,K,V,..]> { ... } ->제너릭 인터페이스
      
      예]
      //1)제너릭을 사용하지 않은 경우
      public class NonGeneric{
        private Object field; ->타입 Object로 결정
        public void set(Object field){ ->타입 Object로 결정
            this.field=field; ->필드가 Object니까 Object에 담는 초기화 가능
        }
        public Object get(){ ->set에 타입무관 모두 담을 수 있음, 그래서 get으로 Object만 내보내야함, 원래 String이었다고 치면 복구하려고 형변환 해야함 
            return field;
        } 
      }
      
      NonGeneric ng=new NonGeneric();
      ng.set(new Student()) -> Student객체 넣어줌
      Object obj=ng.get(); -> 반환시키는 Object로 담아야함
      obj. -> 이걸로 찍으면 Student 멤버 안뜸
      ((Student)obj). ->이걸로 형변환해야 Student객체 멤버 사용가능!!
      
      
      //2)제너릭을 사용한 경우
      public class Generic<T>{
        private T field;
        //private T newField=new T();//[x]//타입이 결정되지 않아서 heap에 메모리 생성 불가(이건 외워)
        //public static T staticField;//[x]//타입이 결정되지 않아서 클래스 로드시점에 메모리 생성불가
                    
        public void set(T field){
            this.field=field;
        }
        //2-1)제너릭 메소드가 아님
        public T get(){
            return field;
        } 
        //2-2)아래는 제너릭 메소드 이때 T는 Generic<T>의  T와 무관하다 -> 제너릭 메소드는 제너릭 클래스 무관, 제너릭 클래스에 T 없어도 별개로 제너릭 메소드 T가질 수 있음
        public <T> T genericMethod(T param){ ->반환타입 T는 위 제너릭 메소드랑 동일, 근데 <>다이아몬드 있어서 제너릭 메소드임, 위 //2)밑의 코드 T와 지금 (T param)의 T는 무관       
            return param;
        }
        //2-3)아래는 제너릭 메소드 이때 E는 Generic<T>의  T와 무관하다
        public static <E> E genericStaticMethod(E param){ ->제너릭메소드는 static이 가능하다!! 클래스로드할 시점에 만들어진다는 말~, 그럼 뭘로 결정되냐? 그건 그냥 내비둬 상식x임     
            return param;
        }
      }
      
      //NonGeneric인스턴스화
      NonGeneric ng = new NonGeneric();
      ng.set("NonGeneric타입");
      String value=(String)ng.get();//Object에서 String으로 강제 형변환, 부모를 자식에 담을 수 없어서 (String)으로 오른쪽 형변환 후 강제 담음
      
      //Generic인스턴스화
      Generic<String> ge=new Generic<>();
      ge.set("Generic");
      String value=ge.get();//형변환이 필요 없다, 이미 String이니까~
      System.out.println(ge.genericMethod(10).getClass().getName());
      System.out.println(Generic.genericStaticMethod(true).getClass().getName());
      
3]제너릭 메소드(Generic Method)
- 클래스에 타입 파라미터를 선언하지 않고 메소드마다 타입 파라미터를 선언해 사용할수 있다. 즉 제너릭 클래스(제너릭 타입)가 아니어도 해당 클래스안에 제너릭 메소드를 만들 수 있다     
- 반환타입 이전에 <>로 타입 파라미터를 선언. 제너릭 클래스의 타입 파라미터와는 무관하다
- 제너릭메소드의 T는 호출 시 결정된다
    
    [static] <타입파라미터>  반환자료형  메소드명(타입파라미터 변수){}     
    
    예]
    public <T> T genericMethod(T param){ ->데이터가 "~~~"이면 메소드(T param) 의 T가 String으로 알아서 추론해줌
        return param; ->반환타입 param이니까 반환타입 위의 <T> T 중 오른쪽 T인 반환타입도 String으로 알아서 추론해줌(<T> 이건 그냥 제너릭메소드다 라고 알려주는것)
    }
    
    String value=인스턴스변수.genericMethod("문자열");//컴파일러가 인자의 타입을 보고 반환 타입을 추정한다(<String>.genericMethod(new Student) 이런건 타입 일치x라 빨간줄)
    (static 없으면 변수명.으로 호출, String으로 호출하면 T가 String으로 설정돼 호출되는 것)
    (static 있으면 클래스명.으로 호출, 클래스명.<String>genericMethod("문자열"); 사용)
    
    -제너릭 메소드 호출(<자료형> 굳이 지정안해도 됨, 메소드명(데이터) <-안의 데이터타입을 보고 이놈이 알아서 추정해줌)
    방법1] 클래스명(static이 붙은 경우).<자료형>메소드명(데이타) 혹은 인스턴스변수.<자료형>메소드명(데이타): 타입에 맞는 데이타만 전달할 수 있다
    방법2] 클래스명(static이 붙은 경우).메소드명(데이타) 혹은 인스턴스변수.메소드명(데이타): 데이타의 타입을 보고 컴파일러가 타입을 추정한다
    
    1)타입 파라미터를 제한한 제너릭을 사용한 메소드(이건 그냥 일반메소드, 단지 받는 List인자를 해당 클래스명을 상속받은 애들만 저장할 수 있도록 제한한것)
    static 반환타입 메소드명(List<? extends 클래스명> 변수){} ->변수인자는 타입이 List여야함, 근데 List에 저장될 객체를 <? extends 클래스명> 으로 제한함(메소드 아니라 무조건 ?써야함)
    												   ->List<Object> 변수 -> 이거면 모든 타입 담을 수 있음
    												   ->List<? extends Person> -> 이거면 Person을 포함, Person을 확장한(상속,extends)한 ?(Student,Teacher 뭐든)가능
    
    1-1)위를 제너릭 메소드로 변환.즉 T의 타입을 클래스명 및 클래스명을 상속받은 자식으로 제한(위랑 개념적으로는 동일함~ 그냥 같은 코드임)
    static <T extends 클래스명> 반환타입 메소드명(List<T> 변수){} ->T는 클래스명에 해당하거나 클래스명의 자식이어야함, 위의 Person예시처럼
    
    2)또 다른 예(이것도 그냥 일반 정적메소드)      
    static void 메소드명(List<? extends 클래스명> 변수1,List<? extends 클래스명> 변수2) ->클래스명 상속 자식만 List에 저장가능
    
    2-1)위를 제너릭 메소드로 변환
    static <T extends 클래스명> void 메소드명(List<T> 변수1,List<T> 변수2) ->이것도 위코드 동일, 그냥 긴걸 T로 바꿈, 그리고 T는 앞에 <>에 설명해줌
    
    => 제너릭 메소드를 사용하지 않는다면 매개변수의 타입에다가 타입 제한을 해야한다. 제너릭 메소드를 사용하면 그럴 필요가 없다.(T 쓰고 <>다이아몬드에서 제한걸어줌)
	   (ex. public HashSet(Collection<? extends E> c)는 생성자!! 반환타입 없고, 클래스명과 동일하니까)
	=> 클래스는 설계도니까 new가 사용하는것, 메소드는 정의해놓은거니까 호출이 사용하는것 ->이 때 T가 결정된다~!!!!*********
	   
4]타입 파라미터 제한하기(T 쓰고 <>다이아몬드에서 제한걸어줌) 

- 한정된 타입 파라미터(Bounded Type Parameter): 타입 파라미터를 구체적인 타입으로 제한
   
    <T extends 최상위 타입> 키워드는  무조건 extends. 최상위 타입 및 최상위 타입을 상속받은 클래스나 인터페이스로 제한
    <T super 최하위 타입> 최하위 타입 및 최하위 타입의 부모 클래스나 인터페이스로 제한 -> super는 부모니까 위랑 반대로 최하위타입(자식)의 부모면 뭐든 가능
    
- 와일드카드 타입(보통 * 쓰지만 제네릭은 ? 씀)
    - 와일드 카드는 메소드의 매개변수, 필드 타입에 사용    
    - 타입 파라미터를 알수 없는 타입(?)으로 제한
    - 형태 3가지 존재-> <>안에 기본자료형 넣으면 안된다!! 클래스 넣어야한다! (Integer는 클래스잖아)
        1)<?> -> <?>는 모든 타입 가능, Object와 유사하나 아래 약간차이 
        2)<? extends 최상위 타입> -> 최상위타입 상속받은 모든 것
        3)<? super 최하위 타입> -> 최하위타입 부모 모든 것
        
        ex.List<Double> db=new Vector<>();
           List<Number> nm=new Vector<>(); ->Number가 Double의 부모임
           db.add(3.14); ->된다~
           nm.add(4); ->된다~
           nm.add(3.14); ->잘 된다~
           nm.add(new Double(3.14)); ->Number가 Double부모라 괜찮음~ 그냥 부모니까 다 된다고
   
    - 와일드 카드는 매개변수, 필드의 타입을 나타내는 등 다양하게 사용된다
    - List<Integer>, List<Double>, List<Number>에 만 적용되는 메소드를 작성하고 싶다면
      Integer, Double 클래스는 모두 Number(부모) 클래스를 상속받기 때문에 ?를 사용하여 아래와 같이 작성
      public static void wildCard(List<? extends Number> list) { ... } -> 와일드타입과 ?를 사용해서 Integer, Double 각각 만들필요없이 다 저장이 가능하다는뜻~
    
    * List<?>와 List<Object> 차이점:<?>는 한정된 타입 파라미터에서 T는 특정 타입으로 지정이 되지만 ?는 타입이 지정되지 않는다는 의미이다
    public static void wildCard(List<?> list) { ... } ->List<?>는 모든 타입 출력
    public static void wildCard(List<Object> list) { ... }
    
    List<Object>는 Object 객체의 리스트만 출력.왜냐하면 List<List<Integer>>, List<List<String>>, List<List<Double>>와 같은 클래스들은 Object의 자식이 아니다.
    ->위의 List<List<Integer>>의 List<Integer>랑 <String>, <Double>는 <?>는 담을 수 있는데 <object>에는 담을 수 없음
    ->List는 인터페이스고 Object는 클래스임, Object는 아빠임, 할아버지인 List<~~~>를 List<Object> list에는 담을 수 없음
    ->Vector<Integer>는 급이 낮은 클래스(컬렉션일뿐)라서 List<Object>에 담을 수 있음, 모든 클래스는 Object상속이니까
    
 */
public class MyGeneric<T> {//제너릭클래스! new할때 T결정된다

	private T field;//정의할때는 T정의x
	//정적멤버필드(변수)에는 타입파라미터(T) 사용불가
	//public static T staticfield; ->[x]정적멤버는 클래스로드 시 생김, 근데 T는 new해야 생김, 불가 Cannot make a static reference to the non-static type T 
	//일반적인 인스턴스 메소드
	public T getField() {
		return field;
	}
	public void setField(T field) {
		this.field=field;
	}
	//정적메소드에도 타입파라미터(T) 사용불가(단, 제네릭 메소드에 static은 사용가능, 외워)
	//public static void staticMethod(T param) {}//[x]Cannot make a static reference to the non-static type T
	//제너릭메소드[x]
/*  public T getEndValue1(T ... t) {//Var args, T는 배열! 여러개 저장가능, 제너릭메소드x 그냥 제너릭클래스의 일반메소드, 이 메소드 쓰려면 위 클래스를 new해야함, 그 때 String지정시 T결정, String형 배열이 되는 것, 반환도 String이겠네
		return t[t.length-1]; //마지막방 반환한다~
	}*/
	//제너릭 인스턴스형 메소드: 인스턴스화해서 인스턴스변수로 접근(T는 이 메소드를 호출할 때 타입이 결정된다)
	//지금 <T>는 클래스 무관, 메소드 호출시 결정(클래스 호출시 생성되는거 아님), MyGeneric을 String으로 호출해도 지금 모든 <T>, T, T String아님
	public <T> T getEndValue1(T ... t) {
		return t[t.length-1];
		//return field;//[x] 빨간줄, T반환하는데 왜 안됨? -> MyGeneric<T> 의 T와 지금 메소드의 반환타입 위치의 T는 무관하니까!(지금 메소드의 모든 T를 E로 바꾸고 해보면됨)
	}
	//제너릭 정적메소드: 인스턴스화 필요없이 클래스명으로 접근
	public static <T> T getEndValue2(T ... t) {
		for (T e: t) System.out.println(e);//확장for문
		return t[t.length-1];
	}
	//[타입 제한하기]: 제너릭메소드가 아닌 일반메소드로 메소드파라미터의 타입제한
	//즉, 컬렉션에 저장할 요소 객체를 숫자로 제한(<? extends Number>) 그리고 요소의 모든 합을 구하는 메소드
	//제너릭메소드: 반환타입 void앞에 <>있으니까(T는 클래스 T 무관)
	//아래는 매개변수 타입인 List<T>의 T는 MyGeneric<T>의 T로 컴파일된다. 
	//public static <? extends Number> void genericMethod(List<T> numbers) {}//[x] 이렇게 쓰면 안됨
	public static <T extends Number> void genericMethod(List<T> numbers) {//[o] ?를 T로 해야 MyGeneric<T>의 T가 아닌 <T>반환타입 형태의 T로 컴파일된다(즉, 제너릭메소드 됨)
		int total=0;
		for(Number number:numbers){//부모 Number, 꺼내오면 Number니까 거기에 담음 
			//total+=number;//지금 number는 클래스고 total은 기본자료형임 연산불가
			total+=number.intValue();//이렇게해야계산됨, int로 반환해서 계산하자~
		}
		System.out.println("컬렉션에 저장된 요소의 총합:"+total);
	}
	//public static void genericMethod(List<T> numbers) {}//[x] static 붙어서 T안됨
	//제네릭메소드가 아닌 일반(정적)메소드로 타입을 제한
	//즉 위의 컬렉션에 저장하는 객체의 타입을 제한한 제너릭메소드를 일반메소드로 변환 시 아래와 같음(List<T> 랑 List<? extends Number> 같은 타입이라 오버로딩[x]! 이름 바꿔야함!)
	public static void genericMethod1(List<? extends Number> numbers) {//1안붙으면 위랑 지금 빨간줄, 오버로딩이 안되는것, 왜? 두 메소드는 같은거니까!
		int total=0;
		for(Number number:numbers){
			total+=number.intValue();
		}
		System.out.println("컬렉션에 저장된 요소의 총합:"+total);
	}	
	
}
