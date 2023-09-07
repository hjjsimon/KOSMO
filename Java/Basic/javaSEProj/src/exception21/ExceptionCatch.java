package exception21;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
-catch절을 여러개 사용할 수 있다
-여러개 사용시 자식 예외클래스부터 catch해야한다 -> 캐치(널포인트익셉션) 있어도 위에 캐치(익셉션) 있으면 위의 익셉션이 부모라 널포인트익셉션도 잡아버림, 아래 널포인트익셉션은 언리처블 에러, 널포인트익셉션이 아래 있어야함
-부모 예외 클래스를 자식 예외 클래스보다 상위에
 위치시켜 놓으면 부모가 예외를 모두 잡아버리기
 때문에
 자식예외클래스의 catch절에는
 unreacheable catch block이되어
 컴파일이 안된다.		 
 
 웬만한 예외는 Throwable이 담당, 근데 코드에서는 Exception 많이 씀
 
 
*/
public class ExceptionCatch {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		int[] arr=new int[2];
		try {
			System.out.println("arr[0]에 입력할 숫자?");
			String firstStr=sc.nextLine(); 
			arr[0]=Integer.parseInt(firstStr); //firstStr 스트링, 배열 각각은 int니까 parseInt로 바꿔줘야함
			System.out.println("arr[1]에 입력할 숫자?");
			arr[1]=sc.nextInt();
			System.out.println("두 숫자 나누기:"+arr[0]/arr[1]);
			//에러가능1. parseInt에 숫자 아닌거 입력시->NumberFormatException
			//에러가능2. nextInt에 숫자 아닌거 입력시->InputMismatchException
			//에러가능3. arr[1]=0일 때 0으로 나눌 수 없음->ArithmeticException
		}
		//아래처럼 여러개의 catch블락 사용시 부모 예외클래스는 항상 맨아래에 위치시켜라
//		catch(Exception e) {
//			System.out.println("예외가 발생했어요");
//		}
//		catch(NumberFormatException e) { 
//			System.out.println("arr[0]에는 숫자만 입력하세요");
//		}
		//Unreachable catch block for NumberFormatException. It is already handled by the catch block for Exception
		//위의 Exception 캐치절이 해결해버려서 아래 넘버포맷익셉션 절은 불필요한 절이 되어버림
		//차라리 Exception만 있으면 다 해결가능함, 근데 arr[0]에는 숫자만 입력하라는 등의 정확한 지시는 불가함
/*		catch(NumberFormatException e) { 
			System.out.println("arr[0]에는 숫자만 입력하세요");
		}
		catch(InputMismatchException e) { 
			System.out.println("arr[1]에는 숫자만 입력하세요");
		}
		catch(ArithmeticException e) { 
			System.out.println("0으로 나눌 수 없어요");
		}
*/		//모든 예외를 catch(Exception e)블락 하나로 잡을 수 있으나 예외 클래스별로 예외처리를 세분화하기 힘든 단점이 있다
		catch(Exception e) {
			if(e instanceof NumberFormatException) {//e가 넘버포멧 익셉션인지 확인, 이런식으로 3개 오류 다 처리하면 됨, 위에 캐치 3개 안써도 됨
				System.out.println("arr[0]에는 숫자만 입력하세요");
			}
			System.out.println("예외가 발생했어요");
		}
	}

}
