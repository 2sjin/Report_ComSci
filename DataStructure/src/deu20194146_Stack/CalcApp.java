package deu20194146_Stack;

import java.util.*;

public class CalcApp {
	static public Scanner scanner = new Scanner(System.in);		// Ű �Է��� ���� Scanner ��ü ����
	private int size;					// �ʵ�: �迭�� ũ��
	private Stack<String> stringStack;	// �ʵ�: ���ڿ� ����(Stack ��ü�� ��üȭ)
	private Stack<Double> doubleStack;	// �ʵ�: �Ǽ� ����(Stack ��ü�� ��üȭ)
	
	// ������(�迭�� ����)
	public CalcApp(int arraySize) {
		this.size = arraySize;							// �Ű� ������ �迭�� ũ��� ����
		stringStack = new Stack<String>(this.size);		// ���ڿ� ���� ����
		doubleStack = new Stack<Double>(this.size);		// �Ǽ� ���� ����
	}
	
	// �޼ҵ�: ������ type(Ŭ���� �켱���� ����) ��ȯ
	public int type(String s) {
		if(s == null) return -1;			// ���� ���ڿ��� -1 ����
		switch(s) {
			case "^":			return 3;	// ����(^) �����ڴ� 3 ����
			case "*": case "/":	return 2;	// ����(*) �� ������(/) �����ڴ� 2 ����
			case "+": case "-":	return 1;	// ����(+) �� ����(-) �����ڴ� 1 ����
			case "(": case ")":	return 0;	// ��ȣ�� 0 ����
			default:			return -1;	// �ǿ����ڴ� -1 ����
		}
	}
	
	// �޼ҵ�: ���� ǥ���(infix)�� ���� ǥ���(prefix) �Ǵ� ���� ǥ���(postfix)���� ��ȯ
	public String convert(String infix, boolean modePrefix) {
		String fix = "";		// ���� ǥ���(prefix) Ȥ�� ���� ǥ���(postfix)�� ��ȯ�� ���ڿ�
		infix = infix.replace(" ", "");		// �Է��� ���ڿ�(���� ǥ���)�� ��� ���� ����

		stringStack.init(size);					// ���ڿ� ������ �ʱ�ȭ
		int [] flag = {0, infix.length(), 1};	// for���� ���ǹ��� Ȱ���� ���ǵ��� �迭�� ��Ƶ�
		String [] bracket = {"(", ")"};			// ���� ��ȣ�� �ݴ� ��ȣ�� �迭�� ��Ƶ�
				
		if(modePrefix) {	// ���� ǥ���(prefix)���� ��ȯ�� ���, �������� üũ�ϱ� ���� �迭�� ��� ���� ������
			flag[0] = infix.length()-1;
			flag[1] = -1;
			flag[2] = -1;
			bracket[0] = ")";
			bracket[1] = "(";
		}
		
		String tempOperand = "";		// �ǿ����ڸ� �ӽ÷� �����ϴ� ���ڿ� ���� (2�ڸ� �̻��� �Ǽ��� ����ϱ� ����)
		for(int i=flag[0]; i!=flag[1]; i+=flag[2]) {		// �迭�� ��ҿ� ���� �ݺ��� ����
			String s = Character.toString(infix.charAt(i));	// ���� ǥ���(infix) ���ڿ��� �� ���ھ� ���� �� ������� üũ��(prefix�� �������� üũ)
			if(type(s) == -1) {								// ���� üũ ���� ���ڰ� �ǿ������� ���
				tempOperand = tempOperand.concat(s);		// ���ڿ� ���� �޺κп� ����(�ǿ����ڰ� �������� ������ �ݺ� �����)
			}
			else {										// ���� üũ ���� ���ڰ� �������� ���,
				fix = fix.concat(tempOperand + " ");	// �ӽ÷� �����ߴ� �ǿ����� ���ڿ��� ��� fix ���ڿ� �޺κп� ����
				tempOperand = "";						// �ӽ÷� �����ߴ� �ǿ����� ���ڿ� ����
				if(s.equals(bracket[0])){		// ���� �����ڰ� ���� ��ȣ�̸�
					stringStack.push(s);		// ���ÿ� push
				}
				else if(s.equals(bracket[1])){							// ���� �����ڰ� �ݴ� ��ȣ�̸�,
					while(!(stringStack.peek().equals(bracket[0]))) {	// ���ÿ��� ���� ��ȣ�� top�� �� ������
						fix = fix.concat(stringStack.pop() + " ");		// ������ �����ڸ� pop�Ͽ� fix ���ڿ� �ڿ� ����
					}
					stringStack.pop();									// ���� ���� ���� ��ȣ�� fix�� �������� �ʰ� pop�� ����
				}
				else {														// ���� �����ڰ� ��ȣ�� �ƴ� �������� ��,						
					while(type(s) < type(stringStack.peek()))				// �� �����ڰ� ������ top�� �ִ� �����ں��� �켱������ ���� ������
						fix = fix.concat(stringStack.pop() + " ");			// ������ �����ڸ� pop�Ͽ� fix ���ڿ� �ڿ� �����ϴ� �۾��� �ݺ�
					if(!modePrefix && type(s) == type(stringStack.peek()))	// ���� ǥ���(postfix)���� ��ȯ�ϴ� ���, �켱������ ���� ������
						fix = fix.concat(stringStack.pop() + " ");			// ������ �����ڸ� pop�Ͽ� fix ���ڿ� �ڿ� ����
					stringStack.push(s);									// �켱������ ������� ���� �����ڴ� ������ push
				}
			}
		}
		
		fix = fix.concat(tempOperand + " ");			// �ӽ÷� �����ߴ� �ǿ����ڰ� ���������� ��� fix ���ڿ� �ڿ� ����
		while(!stringStack.isEmpty()) 					// ������ �� ���°� �� ������
			fix = fix.concat(stringStack.pop() + " ");	// �����ڸ� pop�Ͽ� fix ���ڿ� �ڿ� ����

		fix = fix.trim().replaceAll(" +", " ");	// ���ʿ��� ���� ����(trim���� �յ� ���� ���� ��, replaceAll���� ���Խ����� 1ĭ �̻��� ������ ��� 1ĭ���� ��ġ) 
		
		if(modePrefix) {										// ���� ǥ���(modePrefix)���� ��ȯ�ϴ� ���,
			stringStack.init(size);								// ���ڿ� ������ �ʱ�ȭ
			for(int i=0; i<fix.length(); i++) {					
				String s = Character.toString(fix.charAt(i));	// fix ���ڿ��� �� ���ھ� ���� �� �������
				stringStack.push(s);							// ���ÿ� push
			}			
			fix = "";									// fix ���ڿ��� �������� ���
			while(!stringStack.isEmpty())				// ������ �� ���°� �� ������
				fix = fix.concat(stringStack.pop());	// ��� ������ pop�Ͽ� fix ���ڿ��� ���� (���� fix ���ڿ� ���� �Ųٷ� ������ ���°� ��)
		}
		return fix;		// ���� fix ���ڿ� ���� ����(main �޼ҵ忡�� ���)
	}
	
	// �޼ҵ�: ���� ǥ����� ���� ��� ��ȯ
	public double calcPostfix(String postfix) {	
		for(int i=0; i<postfix.length(); i++) {
			String token = "";	// ��ū�� ����� ���ڿ� ����
			StringTokenizer st = new StringTokenizer(postfix, " ");	// ��Ʈ�� ��ũ������ ����(���� ���ڸ� �������� postfix ���ڿ��� ��ū ������ �и�)
			while(st.hasMoreTokens()) {		// ��Ʈ�� ��ũ�������� ��ū�� ������ �� ���� �ݺ�
				token = st.nextToken();		// ���� ��ū�� ������ ����(������� ��ū üũ)
				if(type(token) == -1)								// ���� üũ���� ��ū�� �ǿ������̸�
					doubleStack.push(Double.parseDouble(token));	// �Ǽ� Ÿ������ ��ȯ �� ���ÿ� push
				else {								// ���� üũ���� ��ū�� �������̸�
					double n2 = doubleStack.pop();	// ���� �ֱٿ� push�� �ǿ����ڸ� pop�Ͽ� 2��° �����ڷ� ����
					double n1 = doubleStack.pop();	// �� �������� push�� �ǿ����ڸ� pop�Ͽ� 1��° �����ڷ� ����
					switch(token) {
						case "+":		// ���� ����
							doubleStack.push(n1 + n2);
							break;
						case "-":		// ���� ����
							doubleStack.push(n1 - n2);
							break;
						case "*":		// ���� ����
							doubleStack.push(n1 * n2);
							break;
						case "/":		// ������ ����
							doubleStack.push(n1 / n2);
							break;
						case "^":		// ���� ����
							doubleStack.push(Math.pow(n1, n2));
							break;
					}
				}
			}
		}
		return doubleStack.pop();	// ���ÿ� �����ִ� 1���� ���� ���� ����� pop�Ͽ� ����(main �޼ҵ忡�� ���)
	}
	
	// �޼ҵ�: main() �޼ҵ�
	public static void main(String[] args) {
		System.out.println("���α׷��� �����մϴ�. (20194146 �̽���)");
		
		String prefix = "";		// ����� ���� ǥ����� �����ϴ� ���ڿ� ����
		String postfix = "";	// ����� ���� ǥ����� �����ϴ� ���ڿ� ����
		
		while(true) {
			System.out.print("\n���� ǥ��� �Է� >> ");
			String infix = "";			// ���� ǥ��� �Է� �� �ʱ�ȭ
			infix = scanner.nextLine();	// ���� ǥ��� �Է�(Enter�� ���� �ٹٲ��� �� ����)
			CalcApp app = new CalcApp(infix.length() * 3);	// CalcApp ��ü ����, �迭�� ũ��� �Է��� ���� ǥ���(infix)�� �����
			if(infix.equals("exit")) break;					// exit�� �Է��ϸ� ���α׷� ����
			try {
				prefix = app.convert(infix, true);					// convert �޼ҵ�κ��� ���� ǥ����� ���Ϲ޾� ����
				postfix = app.convert(infix, false);				// convert �޼ҵ�κ��� ���� ǥ����� ���Ϲ޾� ����
				System.out.println("  (1) ���� ǥ���: " + prefix);		// ���� ǥ��� ���
				System.out.println("  (2) ���� ǥ���: " + postfix);	// ���� ǥ��� ���
			}
			catch(NullPointerException e) {		// ��ȣ�� �߸� �Է��Ͽ� ���������� null ���۷����� ������ ���
					if(!infix.equals(""))		// (�ܼ��� ���鿡 ���� null�� ����)
						System.out.println("  [Error] �Ұ�ȣ�� �ùٸ��� �Է��� �ּ���.");		// ���� �޽��� ���
			}	
			try {
				double result = app.calcPostfix(postfix);			// calcPostfix �޼ҵ忡�� ���� ǥ��� ���� ����� ���Ϲ޾� ����
				System.out.println("  (3) ���� ���: " + result);		// ���� ��� ���
			}
			catch(NumberFormatException e) { System.out.print(""); }	// ���� ǥ��Ŀ� �Ǽ��� �ƴ� �ǿ����ڰ� ������ ���� ��� �����
			catch(NullPointerException e) { System.out.print(""); }		// ���� ���� �Է� �� ���� ��� ����� 
		}
		System.out.println("���α׷��� �����մϴ�."); 
	}
}
