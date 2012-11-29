package kr.ac.ssu.dss.eztrans.ui.dialogs.component;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;


@SuppressWarnings("serial")
public class ETTextDialog extends JFrame {
	
	private JPanel contentPane;
	private JTextArea textField;
	private JButton btn_ok;
	private JScrollPane scrollPane;
	
	public ETTextDialog(){
		
		setTitle("텍스트 입력창");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 300, 300, 150);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextArea();
		textField.setColumns(10);
		
		scrollPane = new JScrollPane(textField);
		
		btn_ok = new JButton("확인");
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(btn_ok, BorderLayout.SOUTH);
		
		btn_ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ETEditorPage.src = textField.getText();
				ETEditorPage.flag = 1;
				dispose();				
			}
			
		});
		
		this.setVisible(true);
	}
}
