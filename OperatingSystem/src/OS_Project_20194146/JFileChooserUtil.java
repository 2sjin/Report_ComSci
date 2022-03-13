package OS_Project_20194146;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
 
// Ŭ����: ���� ���ñ�
public class JFileChooserUtil {
    public static String fileChoose(){
        String folderPath = "";

        // ���� ���ñ� ��ü ����
        JFileChooser chooser
         = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // ���� ���ñ� ����
        chooser.setCurrentDirectory(new File(".")); 	// �ʱ� ���丮 ����(������Ʈ ����)
        chooser.setAcceptAllFileFilterUsed(true);   	// ���� ���Ϳ� ��� ���� ����
        chooser.setDialogTitle("Open File"); 			// ���� ���ñ� �������� ���� ����
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 	// ���� ���� ��� ����(���ϸ� ����)
        
        // ���� Ȯ���� ���� ����(txt)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");

        // ���� ���ñ⿡ ���� Ȯ���� ���� ����
        chooser.setFileFilter(filter);
        
        // ����� â ����
        int returnVal = chooser.showOpenDialog(null);
        
        // ���⸦ ����� ��� ���� ��� ����
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            folderPath = chooser.getSelectedFile().toString();
        }
        
        // ��Ҹ� Ŭ���� ��� ���� ��� ����
        else if(returnVal == JFileChooser.CANCEL_OPTION){
            System.out.println("cancel"); 
            folderPath = "";
        }
        
        // ���� ��� ����
        return folderPath;
    }
}
