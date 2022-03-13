package OS_Project_20194146;

import java.io.*;
import java.util.*;
import javax.swing.*;

// 클래스: 파일 암호기
public class FileEncryptor {
	// 버퍼의 크기 상수(2KB)
	private static final int BUF_SIZE = 1024 * 2;
	
	// 메소드: 암·복호화 수행
	public static void encrypt(long key, String srcPath) {
		// 매개변수로 받은 키 값에 따라 명령 타입 설정
		String commandType;
		if(key > 0) commandType = "암호화";
		else commandType = "복호화";
		
		// 새 파일 이름에 사용할 날짜 코드 생성
		Date date = new Date();
		long dateCode = date.getTime() / 100 % 100000000;
		
		// 파일 객체 생성
		File src = new File(srcPath);	// 원본 파일(소스 파일) 생성
		File dest = new File(dateCode + "_" + src.getName());	// 새 파일(목적 파일) 생성
		
		try {
			FileInputStream fi = new FileInputStream(src);		// 파일 입력(읽기) 바이트 스트림 생성
			FileOutputStream fo = new FileOutputStream(dest);	// 파일 출력(쓰기) 바이트 스트림 생성
			
			byte [] buf = new byte[BUF_SIZE];		// 2KB 버퍼 생성
			while (true) {
				int n = fi.read(buf);		// 원본 파일의 데이터를 2KB만큼 읽기, n은 실제 읽은 바이트		
				for(int i=0; i<BUF_SIZE; i++)
					buf[i] += key;		// 암·복호화 수행(버퍼 내의 각 바이트 코드 값 + 키 값)
				fo.write(buf, 0, n);		// 사본 파일에 buf[0]부터 n바이트 쓰기
				if(n < buf.length) break;	// 버퍼 크기보다 작게 읽으면 복사 종료(파일의 끝에 도달)	
			}
			fi.close();		// 입력 스트림 닫기
			fo.close();		// 출력 스트림 닫기

			// 팝업 메시지 출력
			JOptionPane.showMessageDialog(null, src.getName()
				+ " 파일의 "+ commandType + "를 정상적으로 수행하였습니다.\n"
				+ "(" + dest.getAbsoluteFile() + ")",	// 상대 경로를 절대 경로로 표시
				"Caeser Cipher", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(FileNotFoundException e) {	// 파일을 찾을 수 없을 때 예외 처리(팝업 메시지 출력)
			JOptionPane.showMessageDialog(null, src.getName()
				+ " 파일을 찾을 수 없습니다.",
				"Caeser Cipher", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e) {	// 입출력 예외 처리(팝업 메시지 출력)
			JOptionPane.showMessageDialog(null, src.getName()
				+ "파일의 손상 또는 디스크 공간의 부족으로 인한 오류가 발생하였습니다.",
				"Caeser Cipher", JOptionPane.ERROR_MESSAGE);
		}
	}
}
