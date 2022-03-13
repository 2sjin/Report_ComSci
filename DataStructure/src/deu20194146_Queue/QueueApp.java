// QueueApp - ���� ť ��ü ����, ��� �Է¿� ���� �޼ҵ� ȣ���� ����ϴ� main Ŭ����
package deu20194146_Queue;

import java.util.Scanner;

public class QueueApp {
	public static void main(String[] args) {
		System.out.println("���α׷��� �����մϴ�. (20194146 �̽���)");
		System.out.println("ť�� �߰��� ���ڿ� �Ǵ� �Ʒ� ��ɾ �Է��ϼ���.");
		System.out.println("  /del - ť�� �� �� ��� ����");
		System.out.println("  /show - ť�� ��� ��� �� ���� ����");
		System.out.println("  /init - ť �ʱ�ȭ");
		System.out.println("  /exit - ���α׷� ����\n");
		Scanner scanner = new Scanner(System.in);
		CircularQueue q = new CircularQueue();		// �ϳ��� ť ��ü ����
		
		while(true) {
			System.out.print(">> ");
			String command = scanner.nextLine();	// ��ɾ� �Է�
			switch(command) {
				case "/del":	// "/del" �Է� ��, ��� ���� �޼ҵ� ȣ��
					String del = q.dequeue();
					System.out.println("������ ���: " + del + "\n");
					break;
				case "/show":	// "/show" �Է� ��, ť�� ��� ��� �� ���� ���¸� �����ϴ� �޼ҵ� ȣ��
					q.showAll();
					System.out.println("ť�� �� �� ���: " + q.peek());
					System.out.println("ť�� ��� ����: " + q.size());
					System.out.println("���� ����: " + q.is_empty());
					System.out.println("��ȭ ����: " + q.is_full() + "\n");
					break;
				case "/init":	// "/init" �Է� ��, ť �ʱ�ȭ �޼ҵ� ȣ��
					q.init();
					System.out.println("ť �ʱ�ȭ �Ϸ�.\n");
					break;
				case "/exit":	// "/exit" �Է� ��, ���α׷� ����
					System.out.println("���α׷��� �����մϴ�.");
					scanner.close();
					return;
				default:		// �� ���� ���ڿ� �Է� ��, ��� �߰� �޼ҵ� ȣ��
					q.enqueue(command);
					break;
			}
		}	
	}
}
