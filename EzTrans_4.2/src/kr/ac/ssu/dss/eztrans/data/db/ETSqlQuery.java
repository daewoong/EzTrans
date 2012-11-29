package kr.ac.ssu.dss.eztrans.data.db;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class ETSqlQuery extends JFrame{
	
	private JPanel contentPane;
	private ArrayList<String> tables;
	@SuppressWarnings("unused")
	private String db_name;
	private Hashtable<String, ArrayList<String>> columns;
	private JTextArea txt_SQL;
	private Connection conn;
	private String[] col;
	private Object[][] data;
	private JTable table;

	/**
	 * 데이터베이스의 내용을 불러온다
	 * 질의의 결과를 테이블 및 스트링으로 출력해준다
	 */
	public ETSqlQuery(String db_name, ArrayList<String> tables, 
			Hashtable<String, ArrayList<String>> columns, Connection con) {
		setResizable(false);		
		this.db_name = db_name;
		this.tables = tables;
		this.columns = columns;
		conn = con;
		setTitle("SQL Query");
		setBounds(100, 100, 807, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tables and columns");
		lblNewLabel.setBounds(12, 10, 192, 15);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane_tree = new JScrollPane();
		scrollPane_tree.setBounds(12, 60, 192, 144);
		contentPane.add(scrollPane_tree);

		//draw JTree view
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(db_name);
		createNodes(root);

		JTree tree = new JTree(root);
		scrollPane_tree.setViewportView(tree);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 214, 203, 7);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Send SQL Query");
		lblNewLabel_1.setBounds(12, 231, 192, 15);
		contentPane.add(lblNewLabel_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(216, 10, 8, 364);
		contentPane.add(separator_1);

		JScrollPane scrollPane_SQL = new JScrollPane();
		scrollPane_SQL.setBounds(12, 256, 192, 83);
		contentPane.add(scrollPane_SQL);

		txt_SQL = new JTextArea();
		txt_SQL.setLineWrap(true);
		scrollPane_SQL.setViewportView(txt_SQL);

		JLabel lblNewLabel_2 = new JLabel("Query Result");
		lblNewLabel_2.setBounds(229, 10, 111, 15);
		contentPane.add(lblNewLabel_2);

		//When 'Send query' button is clicked
		JButton btn_send = new JButton("Send Query");
		btn_send.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String input_text = txt_SQL.getText();
					input_text = input_text.replaceAll("\n", " ");
					input_text = input_text.replaceAll("FROM", "from");
					String tmp = input_text;
					StringTokenizer stok = new StringTokenizer(tmp, " ");
					while(stok.hasMoreTokens()){
						String str = stok.nextToken();
						if(str.equals("from"))
							break;					
					}
					String table_name = stok.nextToken();

					//row count
					String sql = "select COUNT(*) cnt from " + table_name;
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					rs.next();
					int rowCount = rs.getInt("cnt");
					
					//row contents
					String sql1 = input_text;
					ResultSet rs1 = pstmt.executeQuery(sql1);
					ResultSetMetaData rsmd = rs1.getMetaData();
					int colCount = rsmd.getColumnCount();

					BufferedWriter bw = new BufferedWriter(new FileWriter("SqlResult.log"));
					
					//column name
					col = new String[colCount];
					for(int i = 0; i < col.length; i++){
						col[i] = rsmd.getColumnName(i+1);
//						bw.write(col[i] + "\t");
					}
//					bw.write("\n----------------------------------------------------\n");
					
					//Data
					data = new Object[rowCount][colCount];
					for(int i = 0; rs1.next(); i++){
						for(int j = 0; j < colCount; j++){
							data[i][j] = rs1.getString(j + 1);
							bw.write((String)data[i][j] + "\t");
						}
						bw.write("\n");
					}
					bw.close();
					
					table.setModel(new ETSqlTableModel(data, col));
					table.updateUI();
					
					
				} catch (SQLException e) {
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(getParent(), "Incorrect SQL",
							"!", JOptionPane.WARNING_MESSAGE);
				} catch (IOException e) {
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(getParent(), "I/O Exception!",
							"!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_send.setBounds(51, 351, 111, 23);
		contentPane.add(btn_send);

		JLabel lblNewLabel_3 = new JLabel("in your database");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(12, 35, 192, 15);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 35, 562, 308);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//Result to Text 버튼이 눌렸을 때의 이벤트 처리
		JButton btnNewButton = new JButton("Result to Text");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getRowCount() == 0){
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(getParent(), "질의결과가 없습니다 !\n질의를 먼저 해주세요.",
							"!", JOptionPane.WARNING_MESSAGE);
				}
				else{
					new ETSqlQueryText();
					ETSqlQuery.this.dispose();
				}
			}
		});
		btnNewButton.setBounds(618, 351, 171, 23);
		contentPane.add(btnNewButton);

		this.setVisible(true);
	}

	//create tree nodes
	private void createNodes(DefaultMutableTreeNode root) {
		DefaultMutableTreeNode table_name = null;
		DefaultMutableTreeNode column_name = null;

		for(int i = 0; i < tables.size(); i++){
			table_name = new DefaultMutableTreeNode(tables.get(i));
			root.add(table_name);
			ArrayList<String> temp = columns.get(tables.get(i));
			for(int j = 0; j < temp.size(); j++){
				column_name = new DefaultMutableTreeNode(temp.get(j));
				table_name.add(column_name);
			}
		}

	}
}
