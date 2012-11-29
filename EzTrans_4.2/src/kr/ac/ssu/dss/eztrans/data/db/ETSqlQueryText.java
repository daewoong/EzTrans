package kr.ac.ssu.dss.eztrans.data.db;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;

@SuppressWarnings("serial")
public class ETSqlQueryText extends JFrame {

	private JPanel contentPane;
	private String result = ""; //������ ����� ������ �ִ� ��Ʈ��
	private JTextArea textArea = new JTextArea();

	/**
	 * ������ ����� ��Ʈ������ ��Ÿ�����ִ� ������
	 */
	@SuppressWarnings("static-access")
	public ETSqlQueryText() {
		setResizable(false);
		setTitle("QueryResult");
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 420, 226);
		contentPane.add(scrollPane);

		/*
		 * ������ ����� SqlResult.log �� ���� �����´�
		 */
		try {
			String str = "";
			BufferedReader br = new BufferedReader(new FileReader("SqlResult.log"));
			while((str = br.readLine()) != null){
				result += str + "\n";
			}
			textArea.setText(result);
			
		} catch (FileNotFoundException e) {
			JOptionPane msg = new JOptionPane();
			msg.showMessageDialog(getParent(), "I/O Exception!",
					"!", JOptionPane.WARNING_MESSAGE);	
		} catch (IOException e) {
			JOptionPane msg = new JOptionPane();
			msg.showMessageDialog(getParent(), "I/O Exception!",
					"!", JOptionPane.WARNING_MESSAGE);
		}

		scrollPane.setViewportView(textArea);

		/*
		 * Ȯ�� ��ư�� ������ ������ �������� �÷��׿�
		 * ����� ����ִ� ��Ʈ���� �Ѱ��ش�
		 */
		JButton btnClose = new JButton("Ȯ��");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ETEditorPage.flag = 6;
				ETEditorPage.src = result;
				ETSqlQueryText.this.dispose();
			}
		});
		btnClose.setBounds(175, 249, 97, 23);
		contentPane.add(btnClose);

		this.setVisible(true);
	}
}
