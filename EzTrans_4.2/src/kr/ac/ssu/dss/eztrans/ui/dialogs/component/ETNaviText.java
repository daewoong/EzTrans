package kr.ac.ssu.dss.eztrans.ui.dialogs.component;


import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;


@SuppressWarnings("serial")
public class ETNaviText extends JFrame {
	
	private JPanel contentPane;
	private JButton btn_ok;
	private JTextField textField1;
	private JTextField textField3;
	public Vector<String> aNavi_src;
	public Vector<String> aNavi_link;

	public ETNaviText(){

		setTitle("텍스트");
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

		JLabel label1 = new JLabel("텍스트");
		JLabel label3 = new JLabel("하이퍼링크");

		textField1 = new JTextField(14);
		textField3 = new JTextField(14);	

		btn_ok = new JButton("확인");

		contentPane_original.add(label1);
		contentPane_original.add(textField1);

		contentPane_link.add(label3);
		contentPane_link.add(textField3);

		contentPane.add(contentPane_original);
		contentPane.add(contentPane_link);
		contentPane.add(btn_ok);

		pack();

		aNavi_src = new Vector<String>();
		aNavi_link = new Vector<String>();
		
		/*
		 * 확인 버튼이 눌렸을 때의 이벤트 핸들러
		 */
		btn_ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				aNavi_src.add(textField1.getText());
				aNavi_link.add(textField3.getText());
				
				dispose();				
			}

		});
	}
	
	public void openDialog(){	
		textField1.setText("");
		textField3.setText("");
		this.setVisible(true);
	}
	

	public Vector<String> getaNavi_src() {
		return aNavi_src;
	}

	public Vector<String> getaNavi_link() {
		return aNavi_link;
	}
}
