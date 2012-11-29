package kr.ac.ssu.dss.eztrans.ui.dialogs.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorWindow;

public class ETSizeofUserDialog extends JFrame {
	
	private JFrame frm;
	private JPanel contentPanes = new JPanel();
	private JPanel contentPanes_1 = new JPanel();
	private JPanel contentPanes_2 = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public JButton okBtn;
	public JButton cancleBtn;
	
	private int height;
	private int weight;
	
	public ETSizeofUserDialog()
	{
	    frm = new JFrame("사용자 정의");
	    frm.setBounds(50,50,300,180);
	    frm.setLayout(new BorderLayout());
	    
	 	contentPanes.setLayout(new FlowLayout());
	 	contentPanes_1.setLayout(new FlowLayout());
	 	contentPanes_2.setLayout(new FlowLayout());
	 	
		textField_1 = new JTextField("가로 입력 ");
		textField_2 = new JTextField(10);
		
		textField_3 = new JTextField("세로 입력 ");
		textField_4 = new JTextField(10);
		
		contentPanes.add(textField_1);
		contentPanes.add(textField_2);
		contentPanes_1.add(textField_3);
		contentPanes_1.add(textField_4);
		
		okBtn = new JButton("확인");
		cancleBtn = new JButton("닫기");
		contentPanes_2.add(okBtn);
		contentPanes_2.add(cancleBtn);
		
		textField_1.setEditable(false);
		textField_3.setEditable(false);
		
				
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setHeight();
				setWeight();
					
			}
		});
			
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setHeight();
				setWeight();
				processExitProgram();
			}
		});
		
		frm.add(contentPanes, BorderLayout.NORTH);
		frm.add(contentPanes_1, BorderLayout.CENTER);
		frm.add(contentPanes_2, BorderLayout.SOUTH);
	    
	    frm.setVisible(true);
	}
	private void setHeight()
	{
		String height_r = textField_2.getText();
		height = Integer.parseInt(height_r);
		
	}
		
	private void setWeight()
	{
		String weight_r = textField_4.getText();
		weight = Integer.parseInt(weight_r);
	}
	
	private void processExitProgram() {
		frm.dispose();
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWeight()
	{
		return weight;
	}

}
