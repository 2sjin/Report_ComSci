// QueueApp - 원형 큐 객체 생성, 명령 입력에 따른 메소드 호출을 담당하는 main 클래스
package deu20194146_Queue;

import java.util.Scanner;

public class QueueApp {
	public static void main(String[] args) {
		System.out.println("프로그램을 시작합니다. (20194146 이승진)");
		System.out.println("큐에 추가할 문자열 또는 아래 명령어를 입력하세요.");
		System.out.println("  /del - 큐의 맨 앞 요소 삭제");
		System.out.println("  /show - 큐의 모든 요소 및 상태 보기");
		System.out.println("  /init - 큐 초기화");
		System.out.println("  /exit - 프로그램 종료\n");
		Scanner scanner = new Scanner(System.in);
		CircularQueue q = new CircularQueue();		// 하나의 큐 객체 생성
		
		while(true) {
			System.out.print(">> ");
			String command = scanner.nextLine();	// 명령어 입력
			switch(command) {
				case "/del":	// "/del" 입력 시, 요소 삭제 메소드 호출
					String del = q.dequeue();
					System.out.println("삭제한 요소: " + del + "\n");
					break;
				case "/show":	// "/show" 입력 시, 큐의 모든 요소 및 여러 상태를 리턴하는 메소드 호출
					q.showAll();
					System.out.println("큐의 맨 앞 요소: " + q.peek());
					System.out.println("큐의 요소 개수: " + q.size());
					System.out.println("공백 상태: " + q.is_empty());
					System.out.println("포화 상태: " + q.is_full() + "\n");
					break;
				case "/init":	// "/init" 입력 시, 큐 초기화 메소드 호출
					q.init();
					System.out.println("큐 초기화 완료.\n");
					break;
				case "/exit":	// "/exit" 입력 시, 프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					scanner.close();
					return;
				default:		// 그 외의 문자열 입력 시, 요소 추가 메소드 호출
					q.enqueue(command);
					break;
			}
		}	
	}
}
