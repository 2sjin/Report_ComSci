package OS_Project_20194146;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 클래스: GUI를 생성하고 응용프로그램을 실행하는 메인 클래스
public class CaeserCipherApp extends JFrame {
	// 정규화 관련 warning을 방지하기 위해 UID 초기화
	private static final long serialVersionUID = 1L;

	// 스윙 컨테이너 및 컴포넌트 선언
	private Container cont;		// 컨텐트팬
	private JLabel [] label;	// 레이블 배열
	private JTextField [] tf;	// 텍스트필드
	private JButton [] btn;		// 버튼 배열
	
	// 생성자
	public CaeserCipherApp() {
		// 프레임(창) 종료 시 프로그램 전체를 종료하도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		cont = getContentPane();			// 프레임의 컨텐트팬 리턴하여 객체에 대입
		cont.setLayout(new FlowLayout());	// 컨텐트팬 배치관리자 설정
		
		setComponent();		// 컴포넌트 설정
		setEvent();			// 컴포넌트 이벤트 설정

		setTitle("Caeser Cipher");	// 프레임 제목 설정
		setSize(470, 140);		// 프레임 크기 설정
		setVisible(true);		// 프레임 보이기
	}
	
	// 메소드: 컴포넌트 설정
	public void setComponent() {		
		// 레이블 생성
		label = new JLabel[2];
		label[0] = new JLabel("File");
		label[1] = new JLabel("Key");

		// 텍스트필드 생성
		tf = new JTextField[2];
		tf[0] = new JTextField(40);
		tf[1] = new JTextField(40);
		tf[1].setText("100");

		// 버튼 생성
		btn = new JButton[3];
		btn[0] = new JButton("Open File");
		btn[1] = new JButton("Run Encryption");
		btn[2] = new JButton("Run Decryption");

		// 컨텐트팬에 컴포넌트를 순서대로 추가
		cont.add(label[0]);
		cont.add(tf[0]);
		cont.add(label[1]);
		cont.add(tf[1]);
		cont.add(btn[0]);
		cont.add(btn[1]);
		cont.add(btn[2]);
	}

	// 메소드: 컴포넌트 이벤트 설정
	public void setEvent() {
		// "Open File" 버튼을 눌렀을 때 실행할 이벤트 추가
		btn[0].addActionListener(new ActionListener() {
			// 파일 선택기에서 선택한 파일명을 텍스트필드에 적용
			public void actionPerformed(ActionEvent e) {
				tf[0].setText(JFileChooserUtil.fileChoose());
			}
		});
		
		// "Run Encryption" 버튼을 눌렀을 때 실행할 이벤트 추가
		btn[1].addActionListener(new ActionListener() {
			// 텍스트필드에 입력한 키 값으로 파일 암호기 실행(암호화)
			public void actionPerformed(ActionEvent e) {
				long key = Integer.parseInt(tf[1].getText());
				FileEncryptor.encrypt(key, tf[0].getText());
			}
		});
		
		// "Run Decryption" 버튼을 눌렀을 때 실행할 이벤트 추가
		btn[2].addActionListener(new ActionListener() {
			// 텍스트필드에 입력한 키 값에 -1을 곱하여 파일 암호기 실행(복호화)
			public void actionPerformed(ActionEvent e) {
				long key = -1 * Integer.parseInt(tf[1].getText());
				FileEncryptor.encrypt(key, tf[0].getText());
			}
		});
	}

	// 메소드: main
	public static void main(String[] args) {
		System.out.println("20194146 이승진\n");
		new CaeserCipherApp();	// 카이사르 암호 앱 생성
	}
}
