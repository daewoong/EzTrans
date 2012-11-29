package kr.ac.ssu.dss.eztrans.ui.dialogs.component;


import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;


@SuppressWarnings("serial")
public class ETNaviImage extends JFrame {
	
	private JPanel contentPane;
	private JButton btn_ok;
	private JButton btn_choose;
	private JTextField textField1;
	private JTextField textField3;

	public ETNaviImage(){

		setTitle("이미지");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(250, 300);
		
		JPanel contentPane_original = new JPanel();
		contentPane_original.setLayout(new FlowLayout());
		JPanel contentPane_link = new JPanel();
		contentPane_link.setLayout(new FlowLayout());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);

		JLabel label1 = new JLabel("원본 이미지");
		JLabel label3 = new JLabel("하이퍼링크");

		textField1 = new JTextField(14);
		textField3 = new JTextField(14);		

		btn_ok = new JButton("확인");
		btn_choose = new JButton("찾아보기");

		contentPane_original.add(label1);
		contentPane_original.add(textField1);
		contentPane_original.add(btn_choose);

		contentPane_link.add(label3);
		contentPane_link.add(textField3);

		contentPane.add(contentPane_original);
		contentPane.add(contentPane_link);
		contentPane.add(btn_ok);

		pack();

		/*
		 * 확인 버튼이 눌렸을 때의 이벤트 핸들러
		 */
		btn_ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ETEditorPage.src = textField1.getText();//원본 이미지 주소
				ETEditorPage.src1 = textField3.getText();//하이퍼링크 주소
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
					String str = selected.getAbsolutePath();
					str = str.replace('\\', '/');
					textField1.setText(str);
				}
			}
		});
	}
	
	public void openDialog(){
		this.setVisible(true);
	}
}
