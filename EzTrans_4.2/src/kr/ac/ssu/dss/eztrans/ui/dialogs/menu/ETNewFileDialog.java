package kr.ac.ssu.dss.eztrans.ui.dialogs.menu;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import kr.ac.ssu.dss.eztrans.generator.ETEditorTransform;
import kr.ac.ssu.dss.eztrans.generator.ETMakeHTML5File;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPart;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorWindow;


public class ETNewFileDialog extends JFrame
{
	private JComboBox tFontList;         // ��Ʈ ����
	private JComboBox tFontList2;         // ��Ʈ ����
	private JComboBox tFontSize;         // ��Ʈ ũ��

	private JComboBox tSize;
	private JComboBox tSize_1;
	private JComboBox tSize_2;
	private ETEditorPart editorPart = null;
	private static Runtime times;
	private JPanel contentPane;
	private ETSizeofUserDialog user;
	private int height;
	private int height_r;
	private int weight;

	public int select;            // â ũ�� ����
	public static int getLine = 2;           // �׵θ� ����
	private boolean newFile = false;


	public ETNewFileDialog(ETEditorPart editorPart)
	{
		super("���� �����");
		setBounds(250,300,350,200);
		setLayout(new BorderLayout());
		Border border = BorderFactory.createEtchedBorder();

		this.editorPart = editorPart;
		
		Border create = BorderFactory.createTitledBorder(border, "âũ��");
		JPanel create_panel = new JPanel();
		create_panel.setBorder(create);

		Border create2 = BorderFactory.createTitledBorder(border, "���ڵ����");
		JPanel create2_panel = new JPanel();
		create2_panel.setBorder(create2);
		
		Border create4 = BorderFactory.createTitledBorder(border, "�׵θ�����");
		JPanel create4_panel = new JPanel();
		create4_panel.setBorder(create4);

		FlowLayout create3;
		JPanel create3_panel = new JPanel();

		JButton button_ok = new JButton("Ȯ��");
		JButton button_cancle = new JButton("���");

		//Ȯ�ι�ư�� ���� �̺�Ʈ �ڵ鷯:
		button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processExitProgram();
				makeSizeFrame(select);		
			}

		});

		//��� ��ư�� ���� �̺�Ʈ �ڵ鷯:
		button_cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processExitProgram();
			}

		});

		create3_panel.add(button_ok);
		create3_panel.add(button_cancle);

		tSize = selectSizeType();
		tSize.setToolTipText("ũ��");
		create_panel.add(tSize);

		tSize_1 = selectSizeType_1();
		tSize_1.setToolTipText("���ڵ�");
		create2_panel.add(tSize_1);
		
		tSize_2 = selectSizeType_2();
		tSize_2.setToolTipText("�׵θ�");
		create4_panel.add(tSize_2);

		
		// �̺�Ʈ �ڵ鷯 ����:
		

		add(create_panel, BorderLayout.NORTH);
		add(create2_panel, BorderLayout.EAST);
		add(create4_panel, BorderLayout.WEST);
		add(create3_panel, BorderLayout.SOUTH);

		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * selectFontType : ��Ʈ Ÿ���� �����ϴ� �޺��ڽ��� ����
	 * @return
	 */
	private JComboBox selectSizeType()
	{
		final JComboBox tReturn = new JComboBox();

		tReturn.addItem("ũ�⸦ �����ϼ���");
		tReturn.addItem("600  *  800");
		tReturn.addItem("800  *  1024");
		tReturn.addItem("1024  * 1048");
		tReturn.addItem("1048  * 1060");
		tReturn.addItem("1060  * 1096");
		tReturn.addItem("����� ����");

		tReturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int choices;
				int i;
				
				
				JComboBox cb = (JComboBox)e.getSource();
				choices = cb.getSelectedIndex();
				
				switch(choices){
					case 1:
						select = choices;
						break;
					case 2:
						select = choices;
						break;
					case 3:
						select = choices;
						break;
					case 4:
						select = choices;
						break;
					case 5:
						select = choices;
						break;
					case 6:
						select = choices;
						user = new ETSizeofUserDialog();
										
						//  Ȯ�ι�ư�� ������ �� �̺�Ʈ �߻�
						user.okBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								height = user.getHeight();
								weight = user.getWeight();
							}//  height�� weight�� �ʱ�ȭ�� �ȵ� �� 0�� ����
						});
						// �ݱ� ��ư�� ������ �� �̺�Ʈ �߻�
						user.cancleBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								height = user.getHeight();
								weight = user.getWeight();

							}// ���� �Է��� �������� ���� ����
						});
						break;
						
				}
			}

		});
		return tReturn;
	}
	public int getselect()
	{
		return select;
	}

	
	private JComboBox selectSizeType_1()
	{
		JComboBox tReturn_1 = new JComboBox();

		tReturn_1.addItem("����� �����ϼ���");
		tReturn_1.addItem("UTF-8");
		tReturn_1.addItem("EUC-KR");
		tReturn_1.addItem("EUC-JP");
		
		tReturn_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JComboBox cb = (JComboBox) e.getSource();
				int index = cb.getSelectedIndex();
				
				switch(index){
				
					case 1:
						ETMakeHTML5File.charset = "UTF-8";
						ETEditorTransform.charset = "UTF-8";
						break;
						
					case 2:
						ETMakeHTML5File.charset = "EUC-KR";
						ETEditorTransform.charset = "EUC-KR";
						break;
						
					case 3:
						ETMakeHTML5File.charset = "EUC-JP";
						ETEditorTransform.charset = "EUC-JP";
						break;
						
					default:
						break;
						
				}
			}
		});
		
		return tReturn_1;
	}
	
	private JComboBox selectSizeType_2()
	{
		JComboBox tReturn_2 = new JComboBox();

		tReturn_2.addItem("����� �����ϼ���");
		tReturn_2.addItem("�׵θ� ����(HTML��ȯ��)");
		tReturn_2.addItem("�׵θ� �Ҹ�(HTML��ȯ��)");
		
		tReturn_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int choices;
				int i;
				
				JComboBox cb = (JComboBox)e.getSource();
				choices = cb.getSelectedIndex();
				
				switch(choices){
					case 1:
						getLine = choices;
						break;
					case 2:
						getLine = choices;
						break;
											
				}
			}

		});
		
		return tReturn_2;
	}


	private void makeSizeFrame(int choice){

		switch(choice)
		{
			case 1:
				this.editorPart.getETEditorWindow().eTEditorWindow_reNew(1,0,0);
				break;
				
			case 2:
				this.editorPart.getETEditorWindow().eTEditorWindow_reNew(2,0,0);
				break;			
			case 3:
				this.editorPart.getETEditorWindow().eTEditorWindow_reNew(3,0,0);
				break;
			case 4:
				this.editorPart.getETEditorWindow().eTEditorWindow_reNew(4,0,0);
				break;
			case 5:
				this.editorPart.getETEditorWindow().eTEditorWindow_reNew(5,0,0);
				break;
			case 6:
				this.editorPart.getETEditorWindow().eTEditorWindow_reNew(6,height,weight);
				break;
						
		}// ����ڷκ����� �Է°�(����,����)������ �߰��Ǿ �Լ��Ű������� �ٲ���ϴ�.
	}


	/**
	 * selectFontSize : ��Ʈ ũ�⸦ �����ϴ� �޺��ڽ��� ����
	 * @return
	 */

	private void addListeners() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				processExitProgram();
			}
		});
	}

	/**
	 * processExitProgram : ���α׷� ����� �ʿ��� ��� ����
	 */


	private void processExitProgram(){
		dispose();
	}

	private void newFile(){

		int titleNum = 0;
		String sTitle = "Noname" + titleNum + ".ezt";

		// �� �������� �����Ѵ�:
		ETEditorWindow tCFrame = new ETEditorWindow(sTitle);
		tCFrame.setVisible(true);

		//�� �������� �������� ������ �����Ѵ�:
		tCFrame.toFront();
	}
	public int getLineHtml()
	{
		return getLine;
	}

	
}