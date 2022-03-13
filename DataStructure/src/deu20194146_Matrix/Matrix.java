package deu20194146_Matrix;

class Matrix {
	final private int ARRSIZE = 10;	// 배열의 크기는 상수로 선언
	private int[][] matrixSparse;	// 희소행렬 필드
	private int[][] matrixCOO;		// 3원소 행렬 필드
	
	// 생성자(행렬의 생성)
	public Matrix() {
		matrixSparse = new int[ARRSIZE][ARRSIZE];	// 10x10의 희소행렬 생성
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++)
				matrixSparse[i][j] = 0;		// 희소행렬 초기값은 모두 0으로 초기화
		}
		matrixCOO = new int[3][1];		// 새로운 3원소 행렬 생성(마지막 열은 실질적인 데이터가 없으므로 표시하지 않음)
		this.matrixCOO[0][0] = 0;		// [0]열의 모든 원소를 0으로 초기화
		this.matrixCOO[1][0] = 0;
		this.matrixCOO[2][0] = 0;
	}
		
	// 메소드: 행렬 원소 삽입(희소행렬 내에 원소 삽입 후 3원소 행렬 갱신)
	public void set(int i, int j, int value) {
		matrixSparse[i][j] = value;		// 좌표와 값을 매개 변수로 받아 희소행렬에 적용
		System.out.println("희소행렬 [" + i + "][" + j + "]에 " + value + " 값을 삽입하였습니다.");
		updateCOO();					// 희소행렬 값을 기준으로 3원소 행렬 갱신
	}
	
	// 메소드: 행렬 보기(희소행렬과 3원소 행렬 출력)
	public void show() {
		System.out.println("\n****** 희소행렬 ******");
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++)
				System.out.print(matrixSparse[i][j] + " ");	// 희소행렬 [i][j]의 원소 출력 
			System.out.println();							// 행이 바뀌면 줄바꿈
		}
		System.out.println("\n****** 3원소 행렬 ******");	
		String[] label = {"행", "열", "값"};									// 3원소 행렬에 출력할 필드(레이블) 이름
		for(int field=0; field<3; field++) {
			System.out.print(label[field] + " ");							// 필드(레이블) 출력
			for(int record=0; record<matrixCOO[0].length-1; record++) {
				System.out.print(matrixCOO[field][record] + " ");			// 3원소 행렬 [field][record]의 원소 출력
			}
			System.out.println();	// 3원소 행렬 모두 출력 후 줄바꿈
		}
	}
	
	// 메소드: 행렬 전치(3원소 행렬 전치 후 희소행렬 갱신)
	public void transpose() {
		for(int record=0; record<matrixCOO[0].length-1; record++) {
			int temp = matrixCOO[0][record];				// 행 값을 temp에 저장
			matrixCOO[0][record] = matrixCOO[1][record];	// 열 값을 행에 저장
			matrixCOO[1][record] = temp;					// temp 값을 열에 저장
		}
		System.out.println("3원소 행렬에서 행과 열의 전치를 완료하였습니다.");
		updateSparse();	// 3원소 행렬 값을 기준으로 희소행렬 갱신
	}
	
	// 메소드: 희소행렬 갱신(3원소 값을 기준으로 희소행렬 갱신)
	public void updateSparse() {
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++)
				matrixSparse[i][j] = 0;		// 희소행렬 값을 모두 0으로 초기화
		}
		for(int record=0; record<matrixCOO[0].length-1; record++) {
			int i = this.matrixCOO[0][record];						// 행: 3원소 행렬 [0] 값을 변수 i에 저장
			int j = this.matrixCOO[1][record];						// 열: 3원소 행렬 [1] 값을 변수 j에 저장
			this.matrixSparse[i][j] = this.matrixCOO[2][record];	// 값: 3원소 행렬 [2] 값을 희소행렬 [i][j]에 대입 
		}
		System.out.println("3원소 행렬의 원소(행, 열, 값) 모두 희소행렬에 적용하였습니다.");
	}

	// 메소드: 3원소 행렬 갱신(희소행렬 값을 기준으로 3원소 행렬 갱신)
	public void updateCOO() {
		int count = 0;								// 카운트 값 초기화
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++) {
				if(this.matrixSparse[i][j] != 0)	// 희소행렬에서 0이 아닌 값이
					count++;						// 모두 몇 개인지 카운트
			}
		}
		matrixCOO = new int[3][count+1];			// 카운트한 값을 기준으로 3원소 배열 생성
		count = 0;									// 카운트 값 초기화
		for(int i=0; i<ARRSIZE; i++) {
			for(int j=0; j<ARRSIZE; j++) {
				if(this.matrixSparse[i][j] != 0) {						// 희소행렬에서 값이 0이 아닌 원소를 찾아
					this.matrixCOO[0][count] = i;						// 행을 3원소 행렬의 [0]행에 추가
					this.matrixCOO[1][count] = j;						// 열을 3원소 행렬의 [1]행에 추가
					this.matrixCOO[2][count] = this.matrixSparse[i][j];	// 값을 3원소 행렬의 [2]행에 추가
					count++;											// 카운트 값 증가
				}
			}
		}
		System.out.println("희소행렬에서 0이 아닌 원소(행, 열, 값) 모두 3원소 행렬에 적용하였습니다.");
	}
}