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

public class ETFormButtonDialog extends JFrame{
	private JButton btn_ok;
	private JButton btn_ok2;
	private JButton btn_ok3;
	private JButton btn_cancle;
	private JFrame frm;
	private JCheckBox checkbox;
	private JCheckBox checkbox2;
	private ButtonGroup bGroup;
	private ButtonGroup bGroup2;
	private ButtonGroup bGroup3;

	private JRadioButton btn1;
	private JRadioButton btn2;
	private JRadioButton btn3;
	private JRadioButton btn4;
	private JRadioButton btn5;
	private JRadioButton btn6;
	private JRadioButton btn7;
	private JRadioButton btn8;
	private JRadioButton btn9;
	private JRadioButton btn10;
	private JRadioButton btn11;

	private JRadioButton btn12;
	private JRadioButton btn13;
	private JRadioButton btn14;

	private Border rbtnBorder;
	private Border rbtnBorder2;
	private Border rbtnBorder3;

	private JPanel rbtnBorderPanel;
	private JPanel rbtnBorderPanel2;
	private JPanel rbtnBorderPanel3;

	private JPanel contentPane;
	private JPanel contentPane2;

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;

	public static int choiceFormButton = 1;
	public static int styleFormButton = 1;
	public static int typeFormButton = 1;
	public static String inputData;
	public static String inputData2;

	public JCheckBox checkOn;
	public JCheckBox checkOff;

	private JScrollPane scrollPane;
	private int checknum;
	private int checknum2;
	public static int numofAuto;
	public static int autoSelect;
	public static int numofUserForm; 


	public ETFormButtonDialog(){

		frm = new JFrame("FORM 입력창");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setBounds(250, 350, 320, 380);
		frm.setLayout(new BorderLayout());

		checkbox = new JCheckBox("Check Form Button");
		
		checkbox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				checkbox2 = new JCheckBox("Auto On/OFF");
				if(numofAuto==0)
				{
					setTitle("자동 완성기능창");
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					setBounds(100, 100, 250, 180);
					
					contentPane2 = new JPanel();
					contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane2.setLayout(new BorderLayout());
					setContentPane(contentPane2);
					
					btn9 = new JRadioButton("On(자동화 기능)");
					btn10 = new JRadioButton("Off(자동화 기능)");
					
					btn9.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent e){
							if(e.getStateChange() == ItemEvent.SELECTED){
								autoSelect = 1;
								}
							}
						});
					bGroup3 = new ButtonGroup();
					bGroup3.add(btn9);
					bGroup3.add(btn10);
					rbtnBorder3 = BorderFactory.createEtchedBorder();
					rbtnBorder3 = BorderFactory.createTitledBorder(rbtnBorder3, "EZtrans");
					
					rbtnBorderPanel3 = new JPanel();
					rbtnBorderPanel3.setLayout(new GridLayout(0,1));
					rbtnBorderPanel3.setBorder(rbtnBorder3);
					
					rbtnBorderPanel3.add(btn9);
					rbtnBorderPanel3.add(btn10);
					
					checkbox2.addItemListener(new ETcheckboxAndRadioButton(btn9, btn10));
					btn_ok3 = new JButton("확인");
					contentPane2.add(btn_ok3, BorderLayout.SOUTH);
					contentPane2.add(checkbox2, BorderLayout.NORTH);
					contentPane2.add(rbtnBorderPanel3, BorderLayout.CENTER);
					
					btn9.setEnabled(false);
					btn10.setEnabled(false);
					
					btn_ok3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							super.mouseClicked(e);
							
							dispose();
							}
						});
					numofAuto++;
					setVisible(true);
					}
			}
			});


		btn1 = new JRadioButton("Submit Button",true);
		btn3 = new JRadioButton("UserControl Button",true);
		btn2 = new JRadioButton("Search Button",true);
		btn6 = new JRadioButton("Date Button",true);
		btn7 = new JRadioButton("Url Button",true);
		btn8 = new JRadioButton("E-mail Button",true);
		btn11 = new JRadioButton("Points Button",true);
		btn12 = new JRadioButton("Tel Button",true);
		btn13 = new JRadioButton("Number Button",true);
		btn14 = new JRadioButton("Range Button",true);

		bGroup = new ButtonGroup();
		bGroup.add(btn1);
		bGroup.add(btn2);
		bGroup.add(btn3);
		bGroup.add(btn6);
		bGroup.add(btn7);
		bGroup.add(btn8);
		bGroup.add(btn11);
		bGroup.add(btn12);
		bGroup.add(btn13);
		bGroup.add(btn14);

		btn4 = new JRadioButton("원 Style",true);
		btn5 = new JRadioButton("네모 Style",true);

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
		rbtnBorderPanel.add(btn3);
		rbtnBorderPanel.add(btn6);
		rbtnBorderPanel.add(btn7);
		rbtnBorderPanel.add(btn8);
		rbtnBorderPanel.add(btn11);
		rbtnBorderPanel.add(btn12);
		rbtnBorderPanel.add(btn13);
		rbtnBorderPanel.add(btn14);

		rbtnBorderPanel2 = new JPanel();
		rbtnBorderPanel2.setLayout(new GridLayout(0,1));
		rbtnBorderPanel2.setBorder(rbtnBorder2);

		rbtnBorderPanel2.add(btn4);
		rbtnBorderPanel2.add(btn5);

		checkbox.addItemListener(new ETcheckboxAndRadioButton(btn1, btn2, btn3, btn6, btn7, btn8,btn11,btn12,btn13,btn14));
		checkbox.addItemListener(new ETcheckboxAndRadioButton(btn4, btn5));

		btn_ok = new JButton("확인");
		btn_cancle = new JButton("취소");

		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(btn_ok);
		contentPane.add(btn_cancle);

		frm.add(checkbox, BorderLayout.NORTH);
		frm.add(rbtnBorderPanel,BorderLayout.WEST);
		frm.add(rbtnBorderPanel2, BorderLayout.CENTER);
		frm.add(contentPane,BorderLayout.SOUTH);

		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(false);
		btn5.setEnabled(false);
		btn6.setEnabled(false);
		btn7.setEnabled(false);
		btn8.setEnabled(false);
		btn11.setEnabled(false);
		btn12.setEnabled(false);
		btn13.setEnabled(false);
		btn14.setEnabled(false);


		btn_ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				System.out.println("111111");

				ETEditorPage.flag = 9;
				if(choiceFormButton == 1){
					numofUserForm++;
					System.out.println("hfgddfgdf");
					ETEditorPage.src = "submit";
					ETEditorPage.btnStyle = 12;
				}
				else if(choiceFormButton == 2)
				{
					numofUserForm++;
					ETEditorPage.src = "search";
					ETEditorPage.btnStyle = 4;
				}
				else if(choiceFormButton == 3)
				{
					numofUserForm++;
					ETEditorPage.btnStyle = styleFormButton;
				}
				else if(choiceFormButton == 4)
				{
					numofUserForm++;
					ETEditorPage.src = "date";
					ETEditorPage.btnStyle = 11;
				}
				else if(choiceFormButton == 5)
				{
					numofUserForm++;
					ETEditorPage.src = "url";
					ETEditorPage.btnStyle = 5;
				}
				else if(choiceFormButton == 6)
				{
					numofUserForm++;
					ETEditorPage.src = "email";
					ETEditorPage.btnStyle = 6;
				}
				else if(choiceFormButton == 7)
				{
					numofUserForm++;
					ETEditorPage.src = "points";
					ETEditorPage.btnStyle = 7;
				}
				else if(choiceFormButton == 8)
				{
					numofUserForm++;
					ETEditorPage.src = "telephone";
					ETEditorPage.btnStyle = 8;
				}
				else if(choiceFormButton == 9)
				{
					numofUserForm++;
					ETEditorPage.src = "number";
					ETEditorPage.btnStyle = 9;
				}
				else if(choiceFormButton == 10)
				{
					numofUserForm++;
					ETEditorPage.src = "range";
					ETEditorPage.btnStyle = 10;
				}
				dispose();
				processExitProgram();
			}

		});

		btn_cancle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				dispose();
				processExitProgram();
			}

		});

		btn1.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED){
					choiceFormButton = 1;
					
					
				}


			}

		});

		btn2.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED){
					choiceFormButton = 2;
					
					
				}

			}

		});


		btn3.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 3;
	
				if(checknum++%2==0)
				{
					setTitle("사용자 입력창");
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					setBounds(100, 100, 280, 130);

					contentPane2 = new JPanel();
					contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane2.setLayout(new FlowLayout());
					setContentPane(contentPane2);

					textField1 = new JTextField("Button name");
					textField2 = new JTextField(14);

					textField3 = new JTextField(" HyperLink   ");
					textField4 = new JTextField(14);


					btn_ok2 = new JButton("확인");
					contentPane2.add(textField1);
					contentPane2.add(textField2);
					contentPane2.add(textField3);
					contentPane2.add(textField4);

					textField1.setEditable(false);
					textField3.setEditable(false);

					contentPane2.add(btn_ok2, BorderLayout.SOUTH);

					btn_ok2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							super.mouseClicked(e);
							ETEditorPage.src = textField2.getText();
							ETEditorPage.src1 = textField4.getText();
							inputData = textField2.getText();					
							inputData2 = textField4.getText();
							dispose();
						}
					});
					setVisible(true);
				}
			}
		});


		btn4.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					styleFormButton = 1;
				
				
			}
		});

		btn5.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					styleFormButton = 2;
			}
		});

		btn6.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 4;
				
			}

		});

		btn7.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 5;
				
			}

		});


		btn8.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 6;
				
			}

		});

		btn11.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 7;
				
			}

		});

		btn12.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 8;
			
			}

		});

		btn13.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 9;
		
			}

		});

		btn14.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					choiceFormButton = 10;
					
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

	public int getstyleFormButton()
	{
		return styleFormButton;
	}
}




