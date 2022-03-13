package deu20194146_Stack;

class Stack<T> {	// ���׸� Ŭ���� Stack(�Ϻ� ����� ������ Ÿ���� �Ϲ�ȭ ��)
	private Object[] array;			// ������ ������ �迭 �ʵ�
	private int top;				// top ��Ұ� �� ��° �ε����� �ִ��� ǥ���ϴ� ���� �ʵ�
	
	// ������(�迭�� ����)
	public Stack(int size) {
		init(size);
	}
	
	// �޼ҵ�: ���� init(������ �ʱ�ȭ)
	public void init(int size)  {
		top = -1;
		array = new Object[size];
	}
	
	// �޼ҵ�: ���� is_empty(������ ��������� true)
	public boolean isEmpty() {
		if(top <= -1) return true;
		else return false;
	}
	
	// �޼ҵ�: ���� is_full(������ ���� �� ������ true)
	public boolean isFull() {
		if(top >= array.length) return true;
		else return false;
	}
	
	// �޼ҵ�: ���� size(������ ��� ���� ��ȯ)
	public int size() {
		return top+1;
	}
	
	// �޼ҵ�: ���� push(���� ����)
	public void push(T s) {
		if(isFull()) System.out.println("������ ���� á���ϴ�!");	// ������ ���� ���� push �Ұ�
		else {						// ������ ���� �� ���� ������
			top++;					// top ��ġ 1 ����
			this.array[top] = s;	// �Ű� ���� ���� ���ÿ� ����
		}
	}
	
	// �޼ҵ�: ���� pop(��ȯ �� ����)
	@SuppressWarnings("unchecked")
	public T pop() {
		if(isEmpty()) return null;	// ������ ��������� ���� ���� ��ȯ
		else return (T) this.array[top--];	// ������ ������� ������ top ��� ��ȯ �� top ��ġ 1 ����
	}
	
	// �޼ҵ�: ���� peek(�������� �ʰ� ��ȯ)
	@SuppressWarnings("unchecked")
	public T peek() {
		if(isEmpty()) return null;	// ������ ��������� ���� ���� ��ȯ
		else return (T)this.array[top];		// ������ ������� ������ top ��� ��ȯ
	}
}