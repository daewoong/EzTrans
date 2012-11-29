package kr.ac.ssu.dss.eztrans.data.olap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import mondrian.olap.Connection;
import mondrian.olap.DriverManager;
import mondrian.olap.MondrianException;

@SuppressWarnings("serial")
public class ETOlapLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ID;
	private JPasswordField txt_PW;
	private JTextField txt_database;
	private JTextField txt_path;
	private JFileChooser jfc = new JFileChooser();

	/**
	 * Create the frame.
	 */
	public ETOlapLogin() {
		setResizable(false);
		setTitle("OLAP Login");
		setBounds(100, 100, 377, 248);
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
		txt_ID.setText("");
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(22, 72, 67, 15);
		contentPane.add(lblNewLabel_1);

		txt_PW = new JPasswordField();
		txt_PW.setBounds(111, 69, 116, 21);
		txt_PW.setText("");
		contentPane.add(txt_PW);

		JLabel lblNewLabel_2 = new JLabel("Database address");
		lblNewLabel_2.setBounds(22, 110, 140, 15);
		contentPane.add(lblNewLabel_2);

		txt_database = new JTextField();
		txt_database.setBounds(22, 135, 337, 21);
		txt_database.setText("");
		contentPane.add(txt_database);
		txt_database.setColumns(10);

		// Connect 버튼에 대한 이벤트
		JButton btn_connect = new JButton("Connect");
		btn_connect.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String id = txt_ID.getText();
					@SuppressWarnings("deprecation")
					String pw = txt_PW.getText();
					String db_addr = txt_database.getText();
					String catalog = txt_path.getText();
					String url = "Provider=mondrian;" + "Jdbc=jdbc:mysql://"
							+ db_addr + "?user=" + id + "&password=" + pw + ";"
							+ "Catalog=file:" + catalog + ";"
							+ "JdbcDrivers=com.mysql.jdbc.Driver;";

					Connection conn = DriverManager.getConnection(url, null, false);
					new ETOlapQuery(conn);

					ETOlapLogin.this.dispose();

				} catch (MondrianException g) {
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(
							getParent(),
							"Check your ID and Password and Catalog file\nDatabase address example : localhost:3306/example",
							"Can't find database", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_connect.setBounds(249, 32, 110, 93);
		contentPane.add(btn_connect);

		JLabel lblNewLabel_3 = new JLabel("Login to Mysql");
		lblNewLabel_3.setBounds(12, 10, 140, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Catalog file path(xml)");
		lblNewLabel_4.setBounds(22, 163, 337, 21);
		contentPane.add(lblNewLabel_4);

		txt_path = new JTextField();
		txt_path.setText("");
		txt_path.setBounds(22, 188, 205, 21);
		contentPane.add(txt_path);
		txt_path.setColumns(10);

		JButton btn_Open = new JButton("Open");
		btn_Open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// xml 파일만을 선택할 수 있게 제한한다.
				jfc.setFileFilter(new FileNameExtensionFilter("xml", "xml"));
				jfc.setMultiSelectionEnabled(false);
				if (jfc.showOpenDialog(ETOlapLogin.this) == JFileChooser.APPROVE_OPTION) {
					txt_path.setText(jfc.getSelectedFile().toString());
				}
			}
		});
		btn_Open.setBounds(249, 187, 110, 23);
		contentPane.add(btn_Open);

		this.setVisible(true);
	}
}
