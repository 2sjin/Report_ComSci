package OS_Project_20194146;

import java.io.*;
import java.util.*;
import javax.swing.*;

// Ŭ����: ���� ��ȣ��
public class FileEncryptor {
	// ������ ũ�� ���(2KB)
	private static final int BUF_SIZE = 1024 * 2;
	
	// �޼ҵ�: �ϡ���ȣȭ ����
	public static void encrypt(long key, String srcPath) {
		// �Ű������� ���� Ű ���� ���� ��� Ÿ�� ����
		String commandType;
		if(key > 0) commandType = "��ȣȭ";
		else commandType = "��ȣȭ";
		
		// �� ���� �̸��� ����� ��¥ �ڵ� ����
		Date date = new Date();
		long dateCode = date.getTime() / 100 % 100000000;
		
		// ���� ��ü ����
		File src = new File(srcPath);	// ���� ����(�ҽ� ����) ����
		File dest = new File(dateCode + "_" + src.getName());	// �� ����(���� ����) ����
		
		try {
			FileInputStream fi = new FileInputStream(src);		// ���� �Է�(�б�) ����Ʈ ��Ʈ�� ����
			FileOutputStream fo = new FileOutputStream(dest);	// ���� ���(����) ����Ʈ ��Ʈ�� ����
			
			byte [] buf = new byte[BUF_SIZE];		// 2KB ���� ����
			while (true) {
				int n = fi.read(buf);		// ���� ������ �����͸� 2KB��ŭ �б�, n�� ���� ���� ����Ʈ		
				for(int i=0; i<BUF_SIZE; i++)
					buf[i] += key;		// �ϡ���ȣȭ ����(���� ���� �� ����Ʈ �ڵ� �� + Ű ��)
				fo.write(buf, 0, n);		// �纻 ���Ͽ� buf[0]���� n����Ʈ ����
				if(n < buf.length) break;	// ���� ũ�⺸�� �۰� ������ ���� ����(������ ���� ����)	
			}
			fi.close();		// �Է� ��Ʈ�� �ݱ�
			fo.close();		// ��� ��Ʈ�� �ݱ�

			// �˾� �޽��� ���
			JOptionPane.showMessageDialog(null, src.getName()
				+ " ������ "+ commandType + "�� ���������� �����Ͽ����ϴ�.\n"
				+ "(" + dest.getAbsoluteFile() + ")",	// ��� ��θ� ���� ��η� ǥ��
				"Caeser Cipher", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(FileNotFoundException e) {	// ������ ã�� �� ���� �� ���� ó��(�˾� �޽��� ���)
			JOptionPane.showMessageDialog(null, src.getName()
				+ " ������ ã�� �� �����ϴ�.",
				"Caeser Cipher", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e) {	// ����� ���� ó��(�˾� �޽��� ���)
			JOptionPane.showMessageDialog(null, src.getName()
				+ "������ �ջ� �Ǵ� ��ũ ������ �������� ���� ������ �߻��Ͽ����ϴ�.",
				"Caeser Cipher", JOptionPane.ERROR_MESSAGE);
		}
	}
}
