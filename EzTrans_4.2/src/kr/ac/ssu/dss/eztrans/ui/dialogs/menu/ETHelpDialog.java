package kr.ac.ssu.dss.eztrans.ui.dialogs.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.contants.ETIconPaths;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;

public class ETHelpDialog extends JFrame {
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
		
	private JPanel contentPane_1;
	private JButton btn_ok;
	private String reference_book;
	
	public ETHelpDialog()
	{
		setTitle("������ �ñ��մϱ� ? ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 150);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setLayout(new BorderLayout(0, 0));
		
		textField_1 = new JTextField("EzTrans�� �����ΰ�?" +
				" �ս��� ������ �����Ҽ� �ִ� ���α׷�");
				
		textField_2 = new JTextField("�� ��ư �����  " + "\n"+
				" 1. ���θ���� :  " + "\n"+
				" 2. �ҷ����� :  " + "\n"+
				" 3. �����ϱ� : " + "\n"+
				" 4. ��ȯ�ϱ� : " + "\n"+
				" 5. �̸����� : ");
		
		textField_1.setEditable(false);
		textField_2.setEditable(false);
						
		contentPane_1.add(textField_1,BorderLayout.NORTH);
		contentPane_1.add(textField_2, BorderLayout.CENTER);
		
		btn_ok = new JButton("Ȯ��");
		contentPane.add(contentPane_1, BorderLayout.CENTER);
		contentPane.add(btn_ok, BorderLayout.SOUTH);
		
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
			
		this.setVisible(true);
	}
}

	