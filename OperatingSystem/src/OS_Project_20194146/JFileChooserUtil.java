package OS_Project_20194146;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
 
// 클래스: 파일 선택기
public class JFileChooserUtil {
    public static String fileChoose(){
        String folderPath = "";

        // 파일 선택기 객체 생성
        JFileChooser chooser
         = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // 파일 선택기 설정
        chooser.setCurrentDirectory(new File(".")); 	// 초기 디렉토리 지정(프로젝트 폴더)
        chooser.setAcceptAllFileFilterUsed(true);   	// 파일 필터에 모든 파일 적용
        chooser.setDialogTitle("Open File"); 			// 파일 선택기 윈도우의 제목 설정
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 	// 파일 선택 모드 설정(파일만 선택)
        
        // 파일 확장자 필터 생성(txt)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");

        // 파일 선택기에 파일 확장자 필터 적용
        chooser.setFileFilter(filter);
        
        // 열기용 창 오픈
        int returnVal = chooser.showOpenDialog(null);
        
        // 열기를 출력할 경우 파일 경로 저장
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            folderPath = chooser.getSelectedFile().toString();
        }
        
        // 취소를 클릭할 경우 파일 경로 비우기
        else if(returnVal == JFileChooser.CANCEL_OPTION){
            System.out.println("cancel"); 
            folderPath = "";
        }
        
        // 파일 경로 리턴
        return folderPath;
    }
}
