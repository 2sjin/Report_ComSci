package deu20194146_Matrix;

import java.util.*;

public class MatrixApp {
	static public Scanner scanner = new Scanner(System.in);		// 키 입력을 위한 Scanner 객체 생성
	private Matrix m = new Matrix();	// Matrix 객체 생성 
	
	// 메소드: 삽입할 원소 입력(깔끔하게 정렬된 배열을 위해 0~9 사이의 정수로 범위 제한)
	public void setMatrix() {
		int row=0, column=0, value=0;
		System.out.println("행, 열, 값을 띄어쓰기로 구분하여 0~9 사이의 정수로 입력하세요. ");
		System.out.print("행 열 값 >> ");
		row = scanner.nextInt();		// 행 입력
		column = scanner.nextInt();		// 열 입력
		value = scanner.nextInt();		// 값 입력
		if((row >= 0 && row < 10) &&
			(column >= 0 && column < 10) &&
			(value >= 0 && value < 10))								// 모든 입력이 0~9 사이의 정수이면
			m.set(row, column, value);								// Matrix 클래스의 행렬 원소 삽입 메소드 실행
		else
			System.out.println("[Error] 0~9 사이의 정수를 입력하세요.");	// 하나라도 0~9 사이의 정수가 아니면 삽입 없이 메소드 종료
	}
	
	// 메소드: main() 메소드
	public static void main(String[] args) {
		System.out.println("프로그램을 시작합니다. (20194146 이승진)");
		MatrixApp app = new MatrixApp();			// MatrixApp 객체 생성(프로그램 창을 띄운 개념과 비슷)
		int command = 0;
		try {
			while(command != 4) {
				System.out.print("\n[1]원소 삽입 [2]행렬 보기 [3]행렬 전치 [4]종료 >> ");
				command = scanner.nextInt();			// 실행할 명령을 정수로 입력
				switch(command) {
					case 1: app.setMatrix(); break;		// 삽입할 원소 입력 메소드 실행
					case 2: app.m.show(); break;		// Matrix 클래스의 행렬 보기 메소드 실행
					case 3: app.m.transpose(); break;	// Matrix 클래스의 행렬 전치 메소드 실행
					case 4: break;						// 프로그램 종료
					default: System.out.println("[Error] 1~4 사이의 정수를 입력하세요.");
				}
			}
		}
		catch(InputMismatchException e) {
			System.out.println("[Error] 정수가 아닌 값이 입력되어 오류 발생. 프로그램을 재시작하세요.");	// 정수가 아닌 값을 입력하면 오류 
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
