package encapsulation13;

public class ColdPatientApp {

	public static void main(String[] args) {

		//감기환자 객체화]
		ColdPatient patient=new ColdPatient();
		//감기약 복용]
		patient.take(); 
		
		//ColdPatient클래스 안의 take()메소드 안에 순서 바뀌면 안됨.
		//이걸 순서를 묶는 것도 캡슐화 의미 중 하나!
		
		
	}

}
