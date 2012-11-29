package kr.ac.ssu.dss.eztrans.ui.dialogs.component;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.ETComponentLists;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;


@SuppressWarnings("serial")
public class ETVideoDialog extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JButton btn_ok;
	private JButton btn_choose;
	public JCheckBox checkLoop;
	public JCheckBox checkAutoPlay;

	public ETVideoDialog(){
		
		setTitle("비디오 입력창");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 300, 350, 170);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 10, 320, 68);
		
		checkLoop = new JCheckBox("loop");
		checkLoop.setBounds(12, 80, 50, 23);
		checkAutoPlay = new JCheckBox("autoplay");
		checkAutoPlay.setBounds(72, 80, 100, 23);
		
		btn_ok = new JButton("확인");
		btn_choose = new JButton("찾아보기");
		btn_choose.setBounds(126, 109, 97, 23);
		btn_ok.setBounds(235, 109, 97, 23);
		contentPane.add(textField);
		contentPane.add(btn_ok);
		contentPane.add(btn_choose);
		contentPane.add(checkLoop);
		contentPane.add(checkAutoPlay);
		
		/*
		 * 확인 버튼이 눌렸을 때의 이벤트 핸들러
		 */
		btn_ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ETEditorPage.src = textField.getText();
				ETEditorPage.flag = 3;
				ETEditorPage.Vpro = setVProperty();
				dispose();				
			}
			
		});
		
		/*
		 * 찾아보기 버튼이 눌렸을 때의 이벤트 핸들러
		 */
		btn_choose.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int status = fileChooser.showOpenDialog(null);
				
				if(status == JFileChooser.APPROVE_OPTION){
					//파일의 절대경로를 JTextField 에 출력한다
					File selected = fileChooser.getSelectedFile();
					textField.setText(selected.getAbsolutePath());
				}
			}
			
		});
		
		this.setVisible(true);
	}
	
	private String setVProperty()
	{
		String s = "";
		if(ETComponentLists.etVideoDialog.checkLoop.isSelected()==true)
			s += " loop";
		else
			s += "";
		if(ETComponentLists.etVideoDialog.checkAutoPlay.isSelected()==true){
			s += " autoplay";
			return s;
		}
		else
			return s;
	}
}