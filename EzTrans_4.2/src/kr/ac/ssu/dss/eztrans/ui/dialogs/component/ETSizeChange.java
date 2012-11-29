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

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;

@SuppressWarnings("serial")
public class ETSizeChange extends JFrame{
	private JPanel paneX;
	private JPanel paneY;
	private JPanel paneWidth;
	private JPanel paneHeight;
	private JPanel paneButton;
	private JPanel contentPane;
	private JTextField textX;
	private JTextField textY;
	private JTextField textWidth;
	private JTextField textHeight;
	private JButton btnOk;
	private JButton btnCancel;
	
	public ETSizeChange(){
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		setTitle("크기 변경");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((dimension.width / 2 - 300) >> 1, (dimension.width / 2 - 200) >> 1, 300, 300);
		
		paneX = new JPanel();
		paneX.setLayout(new BorderLayout(0, 0));
		JLabel labelX = new JLabel(" x 좌표");
		textX = new JTextField(10);
		paneX.add(labelX, BorderLayout.NORTH);
		paneX.add(textX, BorderLayout.CENTER);
		
		paneY = new JPanel();
		paneY.setLayout(new BorderLayout(0, 0));
		JLabel labelY = new JLabel(" y 좌표");
		textY = new JTextField(10);
		paneY.add(labelY, BorderLayout.NORTH);
		paneY.add(textY, BorderLayout.CENTER);

		paneWidth = new JPanel();
		paneWidth.setLayout(new BorderLayout(0, 0));
		
		JLabel labelWidth = new JLabel(" 너비(width)");
		textWidth = new JTextField();
		textWidth.setColumns(10);
		paneWidth.add(labelWidth, BorderLayout.NORTH);
		paneWidth.add(textWidth, BorderLayout.CENTER);
		
		paneHeight = new JPanel();
		paneHeight.setLayout(new BorderLayout(0, 0));
		
		textHeight = new JTextField();
		textHeight.setColumns(10);

		JLabel labelHeight = new JLabel(" 높이(height)");
		paneHeight.add(labelHeight, BorderLayout.NORTH);
		paneHeight.add(textHeight, BorderLayout.CENTER);

		btnOk = new JButton("확인");
		btnCancel = new JButton("취소");
		paneButton = new JPanel();
		paneButton.setLayout(new FlowLayout());
		paneButton.add(btnOk);
		paneButton.add(btnCancel);
		
		contentPane = new JPanel();
		contentPane.add(paneX);
		contentPane.add(paneY);
		contentPane.add(paneWidth);
		contentPane.add(paneHeight);
		contentPane.add(paneButton);
		contentPane.setLayout(new GridLayout(5,1));
		
		setContentPane(contentPane);
		this.setVisible(true);
		
		btnOk.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(textX.getText().equals("") || textY.getText().equals("") ||
						textWidth.getText().equals("") || textHeight.getText().equals(""))
					return;
				ETEditorPage.src = textX.getText();
				ETEditorPage.src1 = textY.getText();
				ETEditorPage.src2 = textWidth.getText();
				ETEditorPage.src3 = textHeight.getText();
				ETEditorPage.isChanged = true;
				dispose();				
			}
			
		});
		
		btnCancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				dispose();				
			}
			
		});
	}
}
