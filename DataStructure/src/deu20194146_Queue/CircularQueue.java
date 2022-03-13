// CircularQueue - 원형 큐 객체를 정의하는 클래스 
package deu20194146_Queue;

public class CircularQueue {
	private static final int SIZE_ARR = 8;			// 배열의 전체 크기(상수)
	private String [] queue = new String[SIZE_ARR];	// 원형 큐 배열
	private int front, rear;		// 각각 원형 큐의 맨 앞과 맨 뒤를 나타내는 변수
									//  (단, 배열의 인덱스로 사용할 때는 front % SIZE_ARR 처럼 사용)
									// (예: 7%8=7, 8%8=0, 9%8=1, 10%8=2, 11%8=3, ...)
	private boolean flag;	// true:포화, false:여유 (큐의 모든 공간을 사용하기 위해 추가한 변수)
	
	// 생성자
	public CircularQueue() {
		init();		// 큐를 초기화한다.
	}
	
	// 메소드: 큐를 초기화한다.
	public void init() {
		for(int i=0; i<SIZE_ARR; i++)
			queue[i] = null;	// 큐의 모든 데이터 초기화
		front = 0;				// front 초기화
		rear = 0;				// rear 초기화
		flag = false;			// flag 초기화
	}
	
	// 메소드: 주어진 요소 e를 큐의 맨 뒤에 추가한다.
	public void enqueue(String e) {
		if(is_full()) {		// 큐가 가득 차 있으면 삭제하지 않고 리턴
			System.out.println("큐가 포화 상태이므로 요소를 추가할 수 없습니다.");
			return;
		}
		rear++;						// rear 1 증가 후
		queue[rear % SIZE_ARR] = e;	// 큐의 맨 뒤에 요소 e 추가
		if(rear % SIZE_ARR == front % SIZE_ARR)	// rear과 front가 같은 공간(인덱스)에 존재하면
			flag = true;						// flag 값을 포화(true)로 설정
	}
	
	// 메소드: 큐의 맨 앞 요소를 삭제하고 반환한다.
	public String dequeue() {
		if(is_empty()) {	// 큐가 비어있으면 삭제하지 않고 리턴
			return "큐가 공백 상태이므로 삭제할 수 없습니다.";
		}
		front++;								// front 1 증가 후
		String temp = queue[front % SIZE_ARR];	// 큐의 맨 앞 요소를 임시 변수에 저장 
		queue[front % SIZE_ARR] = null;			// 큐의 맨 앞을 초기화
		flag = false;	// 삭제를 성공하였으면 flag 값을 여유(false)로 설정				
		return temp;	// 임시 변수에 저장했던 값을 리턴
	}
	
	// 메소드: 큐가 비어있으면 TRUE를, 아니면 FALSE를 반환한다.
	public boolean is_empty() {
		if(front == rear && flag == false) return true;	// if 내의 조건을 모두 만족하면 공백 상태
		else return false;
	}
	
	// 메소드: 큐가 가득 차 있으면 TRUE를, 아니면 FALSE를 반환한다.
	public boolean is_full() {
		if(flag == true) return true;	// if 내의 조건을 모두 만족하면 공백 상태
		else return false;		
	}

	// 메소드: 큐의 맨 앞 요소를 삭제하지 않고 반환한다.
	public String peek() {
		return queue[(front + 1) % SIZE_ARR];
	}
	
	// 메소드: 큐의 모든 요소들의 개수를 반환한다.
	public int size() {
		return (rear - front);
	}
	
	// 추가 메소드: 큐의 모든 요소들을 출력 (큐의 상태를 눈으로 확인하기 위해 임의로 추가)
	public void showAll() {
		System.out.print("( Queue: ");
		for(int i=front+1; i<=rear; i++)	// 맨 앞부터 맨 뒤까지 순서대로 출력
			System.out.print(queue[i % SIZE_ARR] + " ");
		System.out.println(")");
	}
}
