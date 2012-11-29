package kr.ac.ssu.dss.eztrans.ui.dialogs.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.ETComponentLists;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;


@SuppressWarnings("serial")
public class ETHyperLinkDialog extends JFrame {
	
	private JPanel contentPane;// �����۸�ũ
	private JPanel contentPane1;// �ؽ�Ʈ
	private JPanel contentPaneF;// ��ü
	private JTextField textField;
	private JTextField textField1;// �ؽ�Ʈ
	private JButton btn_ok;
	private JButton btn_ok1;// �ؽ�Ʈ
	
	public ETHyperLinkDialog(){
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		setTitle("�����۸�ũ �Է�â");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((dimension.width / 2 - 300) >> 1, (dimension.width / 2 - 400) >> 1, 300, 400);

		// �����۸�ũ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel labelLink = new JLabel("��ũ�� �־��ּ���");
		JLabel labelComment = new JLabel("������ �ؽ�Ʈ");
		textField = new JTextField();
		textField.setColumns(10);
		contentPane.add(textField, BorderLayout.CENTER);
		contentPane.add(labelLink, BorderLayout.NORTH);
		contentPane.add(labelComment, BorderLayout.SOUTH);
		
		// �ؽ�Ʈ
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane1);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		
		btn_ok1 = new JButton("Ȯ��");
		contentPane1.add(textField1, BorderLayout.CENTER);
		contentPane1.add(btn_ok1, BorderLayout.SOUTH);
		
		btn_ok1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ETEditorPage.src = textField.getText();
				ETEditorPage.src1 = textField1.getText();
				ETEditorPage.flag = 5;
				dispose();				
			}
			
		});
		
		contentPaneF = new JPanel();
		contentPaneF.add(contentPane);
		contentPaneF.add(contentPane1);
		contentPaneF.setVisible(true);
		contentPane1.setVisible(true);
		contentPane.setVisible(true);
		contentPaneF.setLayout(new GridLayout(2,1));
		setContentPane(contentPaneF);
		
		this.setVisible(true);
	}
	
}
