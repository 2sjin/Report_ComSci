package deu20194146_Matrix;

class Matrix {
	final private int ARRSIZE = 10;	// �迭�� ũ��� ����� ����
	private int[][] matrixSparse;	// ������ �ʵ�
	private int[][] matrixCOO;		// 3���� ��� �ʵ�
	
	// ������(����� ����)
	public Matrix() {
		matrixSparse = new int[ARRSIZE][ARRSIZE];	// 10x10�� ������ ����
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++)
				matrixSparse[i][j] = 0;		// ������ �ʱⰪ�� ��� 0���� �ʱ�ȭ
		}
		matrixCOO = new int[3][1];		// ���ο� 3���� ��� ����(������ ���� �������� �����Ͱ� �����Ƿ� ǥ������ ����)
		this.matrixCOO[0][0] = 0;		// [0]���� ��� ���Ҹ� 0���� �ʱ�ȭ
		this.matrixCOO[1][0] = 0;
		this.matrixCOO[2][0] = 0;
	}
		
	// �޼ҵ�: ��� ���� ����(������ ���� ���� ���� �� 3���� ��� ����)
	public void set(int i, int j, int value) {
		matrixSparse[i][j] = value;		// ��ǥ�� ���� �Ű� ������ �޾� �����Ŀ� ����
		System.out.println("������ [" + i + "][" + j + "]�� " + value + " ���� �����Ͽ����ϴ�.");
		updateCOO();					// ������ ���� �������� 3���� ��� ����
	}
	
	// �޼ҵ�: ��� ����(�����İ� 3���� ��� ���)
	public void show() {
		System.out.println("\n****** ������ ******");
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++)
				System.out.print(matrixSparse[i][j] + " ");	// ������ [i][j]�� ���� ��� 
			System.out.println();							// ���� �ٲ�� �ٹٲ�
		}
		System.out.println("\n****** 3���� ��� ******");	
		String[] label = {"��", "��", "��"};									// 3���� ��Ŀ� ����� �ʵ�(���̺�) �̸�
		for(int field=0; field<3; field++) {
			System.out.print(label[field] + " ");							// �ʵ�(���̺�) ���
			for(int record=0; record<matrixCOO[0].length-1; record++) {
				System.out.print(matrixCOO[field][record] + " ");			// 3���� ��� [field][record]�� ���� ���
			}
			System.out.println();	// 3���� ��� ��� ��� �� �ٹٲ�
		}
	}
	
	// �޼ҵ�: ��� ��ġ(3���� ��� ��ġ �� ������ ����)
	public void transpose() {
		for(int record=0; record<matrixCOO[0].length-1; record++) {
			int temp = matrixCOO[0][record];				// �� ���� temp�� ����
			matrixCOO[0][record] = matrixCOO[1][record];	// �� ���� �࿡ ����
			matrixCOO[1][record] = temp;					// temp ���� ���� ����
		}
		System.out.println("3���� ��Ŀ��� ��� ���� ��ġ�� �Ϸ��Ͽ����ϴ�.");
		updateSparse();	// 3���� ��� ���� �������� ������ ����
	}
	
	// �޼ҵ�: ������ ����(3���� ���� �������� ������ ����)
	public void updateSparse() {
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++)
				matrixSparse[i][j] = 0;		// ������ ���� ��� 0���� �ʱ�ȭ
		}
		for(int record=0; record<matrixCOO[0].length-1; record++) {
			int i = this.matrixCOO[0][record];						// ��: 3���� ��� [0] ���� ���� i�� ����
			int j = this.matrixCOO[1][record];						// ��: 3���� ��� [1] ���� ���� j�� ����
			this.matrixSparse[i][j] = this.matrixCOO[2][record];	// ��: 3���� ��� [2] ���� ������ [i][j]�� ���� 
		}
		System.out.println("3���� ����� ����(��, ��, ��) ��� �����Ŀ� �����Ͽ����ϴ�.");
	}

	// �޼ҵ�: 3���� ��� ����(������ ���� �������� 3���� ��� ����)
	public void updateCOO() {
		int count = 0;								// ī��Ʈ �� �ʱ�ȭ
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++) {
				if(this.matrixSparse[i][j] != 0)	// �����Ŀ��� 0�� �ƴ� ����
					count++;						// ��� �� ������ ī��Ʈ
			}
		}
		matrixCOO = new int[3][count+1];			// ī��Ʈ�� ���� �������� 3���� �迭 ����
		count = 0;									// ī��Ʈ �� �ʱ�ȭ
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++) {
				if(this.matrixSparse[i][j] != 0) {						// �����Ŀ��� ���� 0�� �ƴ� ���Ҹ� ã��
					this.matrixCOO[0][count] = i;						// ���� 3���� ����� [0]�࿡ �߰�
					this.matrixCOO[1][count] = j;						// ���� 3���� ����� [1]�࿡ �߰�
					this.matrixCOO[2][count] = this.matrixSparse[i][j];	// ���� 3���� ����� [2]�࿡ �߰�
					count++;											// ī��Ʈ �� ����
				}
			}
		}
		System.out.println("�����Ŀ��� 0�� �ƴ� ����(��, ��, ��) ��� 3���� ��Ŀ� �����Ͽ����ϴ�.");
	}
}