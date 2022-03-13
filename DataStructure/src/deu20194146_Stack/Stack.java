package deu20194146_Stack;

class Stack<T> {	// 제네릭 클래스 Stack(일부 멤버의 데이터 타입이 일반화 됨)
	private Object[] array;			// 스택을 구현한 배열 필드
	private int top;				// top 요소가 몇 번째 인덱스에 있는지 표시하는 정수 필드
	
	// 생성자(배열의 생성)
	public Stack(int size) {
		init(size);
	}
	
	// 메소드: 스택 init(스택의 초기화)
	public void init(int size)  {
		top = -1;
		array = new Object[size];
	}
	
	// 메소드: 스택 is_empty(스택이 비어있으면 true)
	public boolean isEmpty() {
		if(top <= -1) return true;
		else return false;
	}
	
	// 메소드: 스택 is_full(스택이 가득 차 있으면 true)
	public boolean isFull() {
		if(top >= array.length) return true;
		else return false;
	}
	
	// 메소드: 스택 size(스택의 요소 개수 반환)
	public int size() {
		return top+1;
	}
	
	// 메소드: 스택 push(원소 삽입)
	public void push(T s) {
		if(isFull()) System.out.println("스택이 가득 찼습니다!");	// 스택이 가득 차면 push 불가
		else {						// 스택이 가득 차 있지 않으면
			top++;					// top 위치 1 증가
			this.array[top] = s;	// 매개 변수 값을 스택에 삽입
		}
	}
	
	// 메소드: 스택 pop(반환 및 삭제)
	@SuppressWarnings("unchecked")
	public T pop() {
		if(isEmpty()) return null;	// 스택이 비어있으면 공백 문자 반환
		else return (T) this.array[top--];	// 스택이 비어있지 않으면 top 요소 반환 후 top 위치 1 감소
	}
	
	// 메소드: 스택 peek(삭제하지 않고 반환)
	@SuppressWarnings("unchecked")
	public T peek() {
		if(isEmpty()) return null;	// 스택이 비어있으면 공백 문자 반환
		else return (T)this.array[top];		// 스택이 비어있지 않으면 top 요소 반환
	}
}