package kr.ac.ssu.dss.eztrans.ui.dialogs.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;

@SuppressWarnings("serial")

public class ETNavigationDialog extends JFrame{
	private JButton btn_ok;
	private JButton btn_cancle;
	private JFrame frm;
	private ButtonGroup bGroup;
	private ButtonGroup bGroup2;

	private JRadioButton btn1;
	private JRadioButton btn2;
	private JButton btn4;
	private JButton btn5;

	private Border rbtnBorder;
	private Border rbtnBorder2;

	private JPanel rbtnBorderPanel;
	private JPanel rbtnBorderPanel2;

	private JPanel contentPane;

	public static int choiceFormButton = 1;
	public static int choiceFormButton1 = 0;
	public static String styleList = "A";

	public static String inputData;
	public static String inputData2;

	public JCheckBox checkOn;
	public JCheckBox checkOff;
	public static int numofAuto;
	public static int autoSelect;
	public static int numofUserForm; 

	//각각의 다이얼로그에 대한 객체
	private ETNaviText etNaviText = new ETNaviText();
	private ETNaviImage etNaviImage = new ETNaviImage();
	
	//src와 link의 값을 가지고 있을 벡터
	private Vector<String> naviSrc;
	private Vector<String> naviLink;

	public ETNavigationDialog(){

		frm = new JFrame("NAVI 입력창");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setBounds(250, 300, 320, 380);
		frm.setLayout(new BorderLayout());

		btn1 = new JRadioButton("Number List",true);
		btn2 = new JRadioButton("Spot List",true);

		bGroup = new ButtonGroup();
		bGroup.add(btn1);
		bGroup.add(btn2);

		btn4 = new JButton("Text");
		btn5 = new JButton("Image");

		bGroup2 = new ButtonGroup();
		bGroup2.add(btn4);
		bGroup2.add(btn5);


		rbtnBorder = BorderFactory.createEtchedBorder();
		rbtnBorder = BorderFactory.createTitledBorder(rbtnBorder, "Form Type");

		rbtnBorder2 = BorderFactory.createEtchedBorder();
		rbtnBorder2 = BorderFactory.createTitledBorder(rbtnBorder2, "Form Style");

		rbtnBorderPanel = new JPanel();
		rbtnBorderPanel.setLayout(new GridLayout(0,1));
		rbtnBorderPanel.setBorder(rbtnBorder);

		rbtnBorderPanel.add(btn1);
		rbtnBorderPanel.add(btn2);

		rbtnBorderPanel2 = new JPanel();
		rbtnBorderPanel2.setLayout(new GridLayout(0,1));
		rbtnBorderPanel2.setBorder(rbtnBorder2);

		rbtnBorderPanel2.add(btn4);
		rbtnBorderPanel2.add(btn5);

		btn_ok = new JButton("확인");
		btn_cancle = new JButton("취소");

		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(btn_ok);
		contentPane.add(btn_cancle);

		frm.add(rbtnBorderPanel,BorderLayout.WEST);
		frm.add(rbtnBorderPanel2, BorderLayout.CENTER);
		frm.add(contentPane,BorderLayout.SOUTH);

		btn1.setEnabled(true);
		btn2.setEnabled(true);
		btn4.setEnabled(true);
		btn5.setEnabled(true);

		btn_ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.out.println(styleList);
				naviSrc = etNaviText.getaNavi_src();
				naviLink = etNaviText.getaNavi_link();
				ETEditorPage.Navi_src = naviSrc;//원본 이미지 주소
				ETEditorPage.Navi_link = naviLink;//하이퍼링크 주소
				ETEditorPage.naviStyle = styleList;
				ETEditorPage.flag = 12;
				
				processExitProgram();
			}

		});

		btn_cancle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				processExitProgram();
			}

		});

		btn1.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED){
					styleList = "A";					
				}
			}

		});

		btn2.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED){
					styleList = "B";
				}
			}

		});

		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				etNaviText.openDialog();
				choiceFormButton1 = 1;

			}
		});

		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				etNaviImage.openDialog();
				choiceFormButton1 = 2;
			}
		});
		
		frm.setVisible(true);
	}

	private void processExitProgram() {
		frm.dispose();
	}

	public int getchoiceFormButton()
	{
		return choiceFormButton;
	}

//	public int getstyleFormButton()
//	{
//		//return styleFormButton;
//	}
}
