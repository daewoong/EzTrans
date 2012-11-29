package kr.ac.ssu.dss.eztrans.data.olap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;

import mondrian.olap.Connection;
import mondrian.olap.MondrianException;
import mondrian.olap.Query;
import mondrian.olap.Result;

@SuppressWarnings("serial")
public class ETOlapQuery extends JFrame {

	private JPanel contentPane;
	private Connection con;
	private JTextArea text_mdx;
	private JTextArea text_result;
	private String table = "";

	/**
	 * Create the frame.
	 */
	public ETOlapQuery(Connection conn) {
		con = conn;

		setResizable(false);
		setTitle("OLAP Query");
		setBounds(100, 100, 450, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("MDX Query");
		lblNewLabel.setBounds(12, 10, 420, 22);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 420, 129);
		contentPane.add(scrollPane);

		text_mdx = new JTextArea();
		scrollPane.setViewportView(text_mdx);

		JButton btn_mdx_ok = new JButton("Send Query");
		btn_mdx_ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String mdx = text_mdx.getText();
				mdx = mdx.replace("\n", "");

				Query query = null;
				Result result = null;
				try{
					query = con.parseQuery(mdx);
					result = con.execute(query);

					File file = new File("OlapResult.log");
					FileOutputStream writer;
					FileReader fr;
					BufferedReader reader;

					writer = new FileOutputStream(file);
					result.print(new PrintWriter(writer,true));

					fr = new FileReader(file);
					reader = new BufferedReader(fr);
					String readLine = "";
					while((readLine = reader.readLine()) != null){
						table += readLine;
						table += "\n";
					}

					reader.close();
					writer.close();

					text_result.setText(table);
					
				}catch(MondrianException g){
					JOptionPane.showMessageDialog(getParent(), "Please input the correct Query!");
				} catch (IOException a) {
					JOptionPane.showMessageDialog(getParent(), "Can't find OlapResult.log!");
				}
			}
		});
		btn_mdx_ok.setBounds(307, 181, 125, 30);
		contentPane.add(btn_mdx_ok);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 220, 420, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Result");
		lblNewLabel_1.setBounds(12, 234, 420, 22);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 266, 420, 248);
		contentPane.add(scrollPane_1);

		text_result = new JTextArea();
		scrollPane_1.setViewportView(text_result);
		
		//확인 버튼이 눌렸을 때의 이벤트
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ETEditorPage.flag = 7;
				ETEditorPage.src = table;
				ETOlapQuery.this.dispose();
			}
		});
		btnNewButton.setBounds(174, 524, 97, 23);
		contentPane.add(btnNewButton);

		this.setVisible(true);
	}
}
