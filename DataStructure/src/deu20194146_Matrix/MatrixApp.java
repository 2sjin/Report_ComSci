package deu20194146_Matrix;

import java.util.*;

public class MatrixApp {
	static public Scanner scanner = new Scanner(System.in);		// Ű �Է��� ���� Scanner ��ü ����
	private Matrix m = new Matrix();	// Matrix ��ü ���� 
	
	// �޼ҵ�: ������ ���� �Է�(����ϰ� ���ĵ� �迭�� ���� 0~9 ������ ������ ���� ����)
	public void setMatrix() {
		int row=0, column=0, value=0;
		System.out.println("��, ��, ���� ����� �����Ͽ� 0~9 ������ ������ �Է��ϼ���. ");
		System.out.print("�� �� �� >> ");
		row = scanner.nextInt();		// �� �Է�
		column = scanner.nextInt();		// �� �Է�
		value = scanner.nextInt();		// �� �Է�
		if((row >= 0 && row < 10) &&
			(column >= 0 && column < 10) &&
			(value >= 0 && value < 10))								// ��� �Է��� 0~9 ������ �����̸�
			m.set(row, column, value);								// Matrix Ŭ������ ��� ���� ���� �޼ҵ� ����
		else
			System.out.println("[Error] 0~9 ������ ������ �Է��ϼ���.");	// �ϳ��� 0~9 ������ ������ �ƴϸ� ���� ���� �޼ҵ� ����
	}
	
	// �޼ҵ�: main() �޼ҵ�
	public static void main(String[] args) {
		System.out.println("���α׷��� �����մϴ�. (20194146 �̽���)");
		MatrixApp app = new MatrixApp();			// MatrixApp ��ü ����(���α׷� â�� ��� ����� ���)
		int command = 0;
		try {
			while(command != 4) {
				System.out.print("\n[1]���� ���� [2]��� ���� [3]��� ��ġ [4]���� >> ");
				command = scanner.nextInt();			// ������ ����� ������ �Է�
				switch(command) {
					case 1: app.setMatrix(); break;		// ������ ���� �Է� �޼ҵ� ����
					case 2: app.m.show(); break;		// Matrix Ŭ������ ��� ���� �޼ҵ� ����
					case 3: app.m.transpose(); break;	// Matrix Ŭ������ ��� ��ġ �޼ҵ� ����
					case 4: break;						// ���α׷� ����
					default: System.out.println("[Error] 1~4 ������ ������ �Է��ϼ���.");
				}
			}
		}
		catch(InputMismatchException e) {
			System.out.println("[Error] ������ �ƴ� ���� �ԷµǾ� ���� �߻�. ���α׷��� ������ϼ���.");	// ������ �ƴ� ���� �Է��ϸ� ���� 
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
}
