// CircularQueue - ���� ť ��ü�� �����ϴ� Ŭ���� 
package deu20194146_Queue;

public class CircularQueue {
	private static final int SIZE_ARR = 8;			// �迭�� ��ü ũ��(���)
	private String [] queue = new String[SIZE_ARR];	// ���� ť �迭
	private int front, rear;		// ���� ���� ť�� �� �հ� �� �ڸ� ��Ÿ���� ����
									//  (��, �迭�� �ε����� ����� ���� front % SIZE_ARR ó�� ���)
									// (��: 7%8=7, 8%8=0, 9%8=1, 10%8=2, 11%8=3, ...)
	private boolean flag;	// true:��ȭ, false:���� (ť�� ��� ������ ����ϱ� ���� �߰��� ����)
	
	// ������
	public CircularQueue() {
		init();		// ť�� �ʱ�ȭ�Ѵ�.
	}
	
	// �޼ҵ�: ť�� �ʱ�ȭ�Ѵ�.
	public void init() {
		for(int i=0; i<SIZE_ARR; i++)
			queue[i] = null;	// ť�� ��� ������ �ʱ�ȭ
		front = 0;				// front �ʱ�ȭ
		rear = 0;				// rear �ʱ�ȭ
		flag = false;			// flag �ʱ�ȭ
	}
	
	// �޼ҵ�: �־��� ��� e�� ť�� �� �ڿ� �߰��Ѵ�.
	public void enqueue(String e) {
		if(is_full()) {		// ť�� ���� �� ������ �������� �ʰ� ����
			System.out.println("ť�� ��ȭ �����̹Ƿ� ��Ҹ� �߰��� �� �����ϴ�.");
			return;
		}
		rear++;						// rear 1 ���� ��
		queue[rear % SIZE_ARR] = e;	// ť�� �� �ڿ� ��� e �߰�
		if(rear % SIZE_ARR == front % SIZE_ARR)	// rear�� front�� ���� ����(�ε���)�� �����ϸ�
			flag = true;						// flag ���� ��ȭ(true)�� ����
	}
	
	// �޼ҵ�: ť�� �� �� ��Ҹ� �����ϰ� ��ȯ�Ѵ�.
	public String dequeue() {
		if(is_empty()) {	// ť�� ��������� �������� �ʰ� ����
			return "ť�� ���� �����̹Ƿ� ������ �� �����ϴ�.";
		}
		front++;								// front 1 ���� ��
		String temp = queue[front % SIZE_ARR];	// ť�� �� �� ��Ҹ� �ӽ� ������ ���� 
		queue[front % SIZE_ARR] = null;			// ť�� �� ���� �ʱ�ȭ
		flag = false;	// ������ �����Ͽ����� flag ���� ����(false)�� ����				
		return temp;	// �ӽ� ������ �����ߴ� ���� ����
	}
	
	// �޼ҵ�: ť�� ��������� TRUE��, �ƴϸ� FALSE�� ��ȯ�Ѵ�.
	public boolean is_empty() {
		if(front == rear && flag == false) return true;	// if ���� ������ ��� �����ϸ� ���� ����
		else return false;
	}
	
	// �޼ҵ�: ť�� ���� �� ������ TRUE��, �ƴϸ� FALSE�� ��ȯ�Ѵ�.
	public boolean is_full() {
		if(flag == true) return true;	// if ���� ������ ��� �����ϸ� ���� ����
		else return false;		
	}

	// �޼ҵ�: ť�� �� �� ��Ҹ� �������� �ʰ� ��ȯ�Ѵ�.
	public String peek() {
		return queue[(front + 1) % SIZE_ARR];
	}
	
	// �޼ҵ�: ť�� ��� ��ҵ��� ������ ��ȯ�Ѵ�.
	public int size() {
		return (rear - front);
	}
	
	// �߰� �޼ҵ�: ť�� ��� ��ҵ��� ��� (ť�� ���¸� ������ Ȯ���ϱ� ���� ���Ƿ� �߰�)
	public void showAll() {
		System.out.print("( Queue: ");
		for(int i=front+1; i<=rear; i++)	// �� �պ��� �� �ڱ��� ������� ���
			System.out.print(queue[i % SIZE_ARR] + " ");
		System.out.println(")");
	}
}
