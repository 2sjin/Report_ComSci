package OS_Project_20194146;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Ŭ����: GUI�� �����ϰ� �������α׷��� �����ϴ� ���� Ŭ����
public class CaeserCipherApp extends JFrame {
	// ����ȭ ���� warning�� �����ϱ� ���� UID �ʱ�ȭ
	private static final long serialVersionUID = 1L;

	// ���� �����̳� �� ������Ʈ ����
	private Container cont;		// ����Ʈ��
	private JLabel [] label;	// ���̺� �迭
	private JTextField [] tf;	// �ؽ�Ʈ�ʵ�
	private JButton [] btn;		// ��ư �迭
	
	// ������
	public CaeserCipherApp() {
		// ������(â) ���� �� ���α׷� ��ü�� �����ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		cont = getContentPane();			// �������� ����Ʈ�� �����Ͽ� ��ü�� ����
		cont.setLayout(new FlowLayout());	// ����Ʈ�� ��ġ������ ����
		
		setComponent();		// ������Ʈ ����
		setEvent();			// ������Ʈ �̺�Ʈ ����

		setTitle("Caeser Cipher");	// ������ ���� ����
		setSize(470, 140);		// ������ ũ�� ����
		setVisible(true);		// ������ ���̱�
	}
	
	// �޼ҵ�: ������Ʈ ����
	public void setComponent() {		
		// ���̺� ����
		label = new JLabel[2];
		label[0] = new JLabel("File");
		label[1] = new JLabel("Key");

		// �ؽ�Ʈ�ʵ� ����
		tf = new JTextField[2];
		tf[0] = new JTextField(40);
		tf[1] = new JTextField(40);
		tf[1].setText("100");

		// ��ư ����
		btn = new JButton[3];
		btn[0] = new JButton("Open File");
		btn[1] = new JButton("Run Encryption");
		btn[2] = new JButton("Run Decryption");

		// ����Ʈ�ҿ� ������Ʈ�� ������� �߰�
		cont.add(label[0]);
		cont.add(tf[0]);
		cont.add(label[1]);
		cont.add(tf[1]);
		cont.add(btn[0]);
		cont.add(btn[1]);
		cont.add(btn[2]);
	}

	// �޼ҵ�: ������Ʈ �̺�Ʈ ����
	public void setEvent() {
		// "Open File" ��ư�� ������ �� ������ �̺�Ʈ �߰�
		btn[0].addActionListener(new ActionListener() {
			// ���� ���ñ⿡�� ������ ���ϸ��� �ؽ�Ʈ�ʵ忡 ����
			public void actionPerformed(ActionEvent e) {
				tf[0].setText(JFileChooserUtil.fileChoose());
			}
		});
		
		// "Run Encryption" ��ư�� ������ �� ������ �̺�Ʈ �߰�
		btn[1].addActionListener(new ActionListener() {
			// �ؽ�Ʈ�ʵ忡 �Է��� Ű ������ ���� ��ȣ�� ����(��ȣȭ)
			public void actionPerformed(ActionEvent e) {
				long key = Integer.parseInt(tf[1].getText());
				FileEncryptor.encrypt(key, tf[0].getText());
			}
		});
		
		// "Run Decryption" ��ư�� ������ �� ������ �̺�Ʈ �߰�
		btn[2].addActionListener(new ActionListener() {
			// �ؽ�Ʈ�ʵ忡 �Է��� Ű ���� -1�� ���Ͽ� ���� ��ȣ�� ����(��ȣȭ)
			public void actionPerformed(ActionEvent e) {
				long key = -1 * Integer.parseInt(tf[1].getText());
				FileEncryptor.encrypt(key, tf[0].getText());
			}
		});
	}

	// �޼ҵ�: main
	public static void main(String[] args) {
		System.out.println("20194146 �̽���\n");
		new CaeserCipherApp();	// ī�̻縣 ��ȣ �� ����
	}
}
