package kr.ac.ssu.dss.eztrans.data.db;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ETSqlLogin extends JFrame{
	private JPanel contentPane;
	private JTextField txt_ID;
	private JPasswordField txt_PW;
	private JTextField txt_database;
	
	/**
	 * Create the frame.
	 */
	public ETSqlLogin() {
		setResizable(false);
		setTitle("MySQL Login");
		setBounds(100, 100, 377, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(22, 36, 67, 15);
		contentPane.add(lblNewLabel);

		txt_ID = new JTextField();
		txt_ID.setBounds(111, 33, 116, 21);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(22, 72, 67, 15);
		contentPane.add(lblNewLabel_1);

		txt_PW = new JPasswordField();
		txt_PW.setBounds(111, 69, 116, 21);
		contentPane.add(txt_PW);

		JLabel lblNewLabel_2 = new JLabel("Database address");
		lblNewLabel_2.setBounds(22, 110, 140, 15);
		contentPane.add(lblNewLabel_2);

		txt_database = new JTextField();
		txt_database.setBounds(22, 135, 337, 21);
		contentPane.add(txt_database);
		txt_database.setColumns(10);

		//Connect button
		JButton btn_connect = new JButton("Connect");
		btn_connect.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String db_addr = txt_database.getText();
					String url = "jdbc:mysql://" + db_addr;
					String id = txt_ID.getText();
					@SuppressWarnings("deprecation")
					String pw = txt_PW.getText();
					String db_name = db_addr.substring(db_addr.lastIndexOf("/")+1, db_addr.length());
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url, id, pw);
					DatabaseMetaData d = conn.getMetaData();
					ResultSet rs = d.getTables(db_name, "", "", new String[]{"TABLE" });
					
					ArrayList<String> tables = new ArrayList<String>();
					
					//Get table names
					while(rs.next()){
						tables.add(rs.getString(3));
					} 
					
					Statement stmt = conn.createStatement();
					Hashtable<String, ArrayList<String>> columns = new Hashtable<String, ArrayList<String>>();
					
					//Get Column names
					for(int i = 0; i < tables.size(); i++){
						rs = stmt.executeQuery("SELECT * FROM " + tables.get(i));
						ResultSetMetaData md = rs.getMetaData();
						int col = md.getColumnCount();
						ArrayList<String> temp = new ArrayList<String>();
						for(int j = 1; j <= col; j++){
							temp.add(md.getColumnName(j));
						}
						columns.put(tables.get(i), temp);
					}
					new ETSqlQuery(db_name, tables, columns, conn);
					ETSqlLogin.this.dispose();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(getParent(), "Check your ID and Password\nDatabase address example : localhost:3306/example",
							"Can't find database", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_connect.setBounds(249, 32, 110, 93);
		contentPane.add(btn_connect);

		JLabel lblNewLabel_3 = new JLabel("Login to Mysql");
		lblNewLabel_3.setFont(new Font("gullim",Font.BOLD, 12));
		lblNewLabel_3.setBounds(12, 10, 140, 15);
		contentPane.add(lblNewLabel_3);
		
		this.setVisible(true);
	}
}
