package kr.ac.ssu.dss.eztrans.ui.workbench;

import java.io.IOException;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class ETPreview extends JFrame {
	
	public ETPreview() throws IOException{
		Runtime time = Runtime.getRuntime();

		time.exec("explorer NewFile.html");
			
		Process explorerProcess = time.exec("explorer NewFile.html");
		explorerProcess.destroy();
		
	}
}
