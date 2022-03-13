package deu20194146_Stack;

import java.util.*;

public class CalcApp {
	static public Scanner scanner = new Scanner(System.in);		// 키 입력을 위한 Scanner 객체 생성
	private int size;					// 필드: 배열의 크기
	private Stack<String> stringStack;	// 필드: 문자열 스택(Stack 객체의 구체화)
	private Stack<Double> doubleStack;	// 필드: 실수 스택(Stack 객체의 구체화)
	
	// 생성자(배열의 생성)
	public CalcApp(int arraySize) {
		this.size = arraySize;							// 매개 변수를 배열의 크기로 지정
		stringStack = new Stack<String>(this.size);		// 문자열 스택 생성
		doubleStack = new Stack<Double>(this.size);		// 실수 스택 생성
	}
	
	// 메소드: 문자의 type(클수록 우선순위 높음) 반환
	public int type(String s) {
		if(s == null) return -1;			// 공백 문자열은 -1 리턴
		switch(s) {
			case "^":			return 3;	// 제곱(^) 연산자는 3 리턴
			case "*": case "/":	return 2;	// 곱셈(*) 및 나눗셈(/) 연산자는 2 리턴
			case "+": case "-":	return 1;	// 덧셈(+) 및 뺄셈(-) 연산자는 1 리턴
			case "(": case ")":	return 0;	// 괄호는 0 리턴
			default:			return -1;	// 피연산자는 -1 리턴
		}
	}
	
	// 메소드: 중위 표기식(infix)을 전위 표기식(prefix) 또는 후위 표기식(postfix)으로 변환
	public String convert(String infix, boolean modePrefix) {
		String fix = "";		// 전위 표기식(prefix) 혹은 후위 표기식(postfix)을 반환할 문자열
		infix = infix.replace(" ", "");		// 입력한 문자열(중위 표기식)의 모든 공백 제거

		stringStack.init(size);					// 문자열 스택의 초기화
		int [] flag = {0, infix.length(), 1};	// for문의 조건문에 활용할 조건들을 배열로 모아둠
		String [] bracket = {"(", ")"};			// 여는 괄호와 닫는 괄호를 배열로 모아둠
				
		if(modePrefix) {	// 전위 표기식(prefix)으로 변환할 경우, 역순으로 체크하기 위해 배열의 요소 값을 변경함
			flag[0] = infix.length()-1;
			flag[1] = -1;
			flag[2] = -1;
			bracket[0] = ")";
			bracket[1] = "(";
		}
		
		String tempOperand = "";		// 피연산자를 임시로 저장하는 문자열 변수 (2자리 이상인 실수를 계산하기 위해)
		for(int i=flag[0]; i!=flag[1]; i+=flag[2]) {		// 배열의 요소에 의한 반복문 실행
			String s = Character.toString(infix.charAt(i));	// 중위 표기식(infix) 문자열을 한 글자씩 나눈 후 순서대로 체크함(prefix는 역순으로 체크)
			if(type(s) == -1) {								// 현재 체크 중인 문자가 피연산자일 경우
				tempOperand = tempOperand.concat(s);		// 문자열 변수 뒷부분에 삽입(피연산자가 연속으로 나오면 반복 실행됨)
			}
			else {										// 현재 체크 중인 문자가 연산자일 경우,
				fix = fix.concat(tempOperand + " ");	// 임시로 저장했던 피연산자 문자열을 모두 fix 문자열 뒷부분에 삽입
				tempOperand = "";						// 임시로 저장했던 피연산자 문자열 비우기
				if(s.equals(bracket[0])){		// 현재 연산자가 여는 괄호이면
					stringStack.push(s);		// 스택에 push
				}
				else if(s.equals(bracket[1])){							// 현재 연산자가 닫는 괄호이면,
					while(!(stringStack.peek().equals(bracket[0]))) {	// 스택에서 여는 괄호가 top이 될 때까지
						fix = fix.concat(stringStack.pop() + " ");		// 스택의 연산자를 pop하여 fix 문자열 뒤에 삽입
					}
					stringStack.pop();									// 스택 내의 여는 괄호는 fix에 삽입하지 않고 pop만 수행
				}
				else {														// 현재 연산자가 괄호가 아닌 연산자일 때,						
					while(type(s) < type(stringStack.peek()))				// 그 연산자가 스택의 top에 있는 연산자보다 우선순위가 높을 때까지
						fix = fix.concat(stringStack.pop() + " ");			// 스택의 연산자를 pop하여 fix 문자열 뒤에 삽입하는 작업을 반복
					if(!modePrefix && type(s) == type(stringStack.peek()))	// 후위 표기식(postfix)으로 변환하는 경우, 우선순위가 같을 때에도
						fix = fix.concat(stringStack.pop() + " ");			// 스택의 연산자를 pop하여 fix 문자열 뒤에 삽입
					stringStack.push(s);									// 우선순위에 상관없이 현재 연산자는 무조건 push
				}
			}
		}
		
		fix = fix.concat(tempOperand + " ");			// 임시로 저장했던 피연산자가 남아있으면 모두 fix 문자열 뒤에 삽입
		while(!stringStack.isEmpty()) 					// 스택이 빈 상태가 될 때까지
			fix = fix.concat(stringStack.pop() + " ");	// 연산자를 pop하여 fix 문자열 뒤에 삽입

		fix = fix.trim().replaceAll(" +", " ");	// 불필요한 공백 제거(trim으로 앞뒤 공백 제거 후, replaceAll에서 정규식으로 1칸 이상의 공백을 모두 1칸으로 대치) 
		
		if(modePrefix) {										// 전위 표기식(modePrefix)으로 변환하는 경우,
			stringStack.init(size);								// 문자열 스택의 초기화
			for(int i=0; i<fix.length(); i++) {					
				String s = Character.toString(fix.charAt(i));	// fix 문자열을 한 글자씩 나눈 후 순서대로
				stringStack.push(s);							// 스택에 push
			}			
			fix = "";									// fix 문자열을 공백으로 비움
			while(!stringStack.isEmpty())				// 스택이 빈 상태가 될 때까지
				fix = fix.concat(stringStack.pop());	// 모든 내용을 pop하여 fix 문자열에 삽입 (기존 fix 문자열 값을 거꾸로 뒤집은 상태가 됨)
		}
		return fix;		// 최종 fix 문자열 값을 리턴(main 메소드에서 출력)
	}
	
	// 메소드: 후위 표기식의 연산 결과 반환
	public double calcPostfix(String postfix) {	
		for(int i=0; i<postfix.length(); i++) {
			String token = "";	// 토큰이 저장될 문자열 변수
			StringTokenizer st = new StringTokenizer(postfix, " ");	// 스트링 토크나이저 생성(공백 문자를 기준으로 postfix 문자열을 토큰 단위로 분리)
			while(st.hasMoreTokens()) {		// 스트링 토크나이저에 토큰이 없어질 때 까지 반복
				token = st.nextToken();		// 다음 토큰을 변수에 저장(순서대로 토큰 체크)
				if(type(token) == -1)								// 현재 체크중인 토큰이 피연산자이면
					doubleStack.push(Double.parseDouble(token));	// 실수 타입으로 변환 후 스택에 push
				else {								// 현재 체크중인 토큰이 연산자이면
					double n2 = doubleStack.pop();	// 가장 최근에 push된 피연산자를 pop하여 2번째 연산자로 지정
					double n1 = doubleStack.pop();	// 그 다음으로 push된 피연산자를 pop하여 1번째 연산자로 지정
					switch(token) {
						case "+":		// 덧셈 연산
							doubleStack.push(n1 + n2);
							break;
						case "-":		// 뺄셈 연산
							doubleStack.push(n1 - n2);
							break;
						case "*":		// 곱셈 연산
							doubleStack.push(n1 * n2);
							break;
						case "/":		// 나눗셈 연산
							doubleStack.push(n1 / n2);
							break;
						case "^":		// 제곱 연산
							doubleStack.push(Math.pow(n1, n2));
							break;
					}
				}
			}
		}
		return doubleStack.pop();	// 스택에 남아있는 1개의 최종 연산 결과를 pop하여 리턴(main 메소드에서 출력)
	}
	
	// 메소드: main() 메소드
	public static void main(String[] args) {
		System.out.println("프로그램을 시작합니다. (20194146 이승진)");
		
		String prefix = "";		// 출력할 전위 표기식을 저장하는 문자열 변수
		String postfix = "";	// 출력할 후위 표기식을 저장하는 문자열 변수
		
		while(true) {
			System.out.print("\n중위 표기식 입력 >> ");
			String infix = "";			// 중위 표기식 입력 값 초기화
			infix = scanner.nextLine();	// 중위 표기식 입력(Enter를 눌러 줄바꿈할 때 까지)
			CalcApp app = new CalcApp(infix.length() * 3);	// CalcApp 객체 생성, 배열의 크기는 입력한 중위 표기식(infix)에 비례함
			if(infix.equals("exit")) break;					// exit를 입력하면 프로그램 종료
			try {
				prefix = app.convert(infix, true);					// convert 메소드로부터 전위 표기식을 리턴받아 저장
				postfix = app.convert(infix, false);				// convert 메소드로부터 후위 표기식을 리턴받아 저장
				System.out.println("  (1) 전위 표기식: " + prefix);		// 전위 표기식 출력
				System.out.println("  (2) 후위 표기식: " + postfix);	// 후위 표기식 출력
			}
			catch(NullPointerException e) {		// 괄호를 잘못 입력하여 비정상적인 null 레퍼런스를 참조할 경우
					if(!infix.equals(""))		// (단순히 공백에 의한 null은 제외)
						System.out.println("  [Error] 소괄호를 올바르게 입력해 주세요.");		// 오류 메시지 출력
			}	
			try {
				double result = app.calcPostfix(postfix);			// calcPostfix 메소드에서 후위 표기식 연산 결과를 리턴받아 저장
				System.out.println("  (3) 연산 결과: " + result);		// 연산 결과 출력
			}
			catch(NumberFormatException e) { System.out.print(""); }	// 중위 표기식에 실수가 아닌 피연산자가 있으면 연산 결과 미출력
			catch(NullPointerException e) { System.out.print(""); }		// 공백 문자 입력 시 연산 결과 미출력 
		}
		System.out.println("프로그램을 종료합니다."); 
	}
}
