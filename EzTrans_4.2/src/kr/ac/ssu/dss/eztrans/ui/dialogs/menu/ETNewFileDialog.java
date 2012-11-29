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
	private JComboBox tFontList;         // 폰트 종류
	private JComboBox tFontList2;         // 폰트 종류
	private JComboBox tFontSize;         // 폰트 크기

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

	public int select;            // 창 크기 선택
	public static int getLine = 2;           // 테두리 유무
	private boolean newFile = false;


	public ETNewFileDialog(ETEditorPart editorPart)
	{
		super("새로 만들기");
		setBounds(250,300,350,200);
		setLayout(new BorderLayout());
		Border border = BorderFactory.createEtchedBorder();

		this.editorPart = editorPart;
		
		Border create = BorderFactory.createTitledBorder(border, "창크기");
		JPanel create_panel = new JPanel();
		create_panel.setBorder(create);

		Border create2 = BorderFactory.createTitledBorder(border, "인코딩방법");
		JPanel create2_panel = new JPanel();
		create2_panel.setBorder(create2);
		
		Border create4 = BorderFactory.createTitledBorder(border, "테두리유무");
		JPanel create4_panel = new JPanel();
		create4_panel.setBorder(create4);

		FlowLayout create3;
		JPanel create3_panel = new JPanel();

		JButton button_ok = new JButton("확인");
		JButton button_cancle = new JButton("취소");

		//확인버튼에 대한 이벤트 핸들러:
		button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processExitProgram();
				makeSizeFrame(select);		
			}

		});

		//취소 버튼에 대한 이벤트 핸들러:
		button_cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processExitProgram();
			}

		});

		create3_panel.add(button_ok);
		create3_panel.add(button_cancle);

		tSize = selectSizeType();
		tSize.setToolTipText("크기");
		create_panel.add(tSize);

		tSize_1 = selectSizeType_1();
		tSize_1.setToolTipText("인코딩");
		create2_panel.add(tSize_1);
		
		tSize_2 = selectSizeType_2();
		tSize_2.setToolTipText("테두리");
		create4_panel.add(tSize_2);

		
		// 이벤트 핸들러 설정:
		

		add(create_panel, BorderLayout.NORTH);
		add(create2_panel, BorderLayout.EAST);
		add(create4_panel, BorderLayout.WEST);
		add(create3_panel, BorderLayout.SOUTH);

		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * selectFontType : 폰트 타입을 설정하는 콤보박스를 생성
	 * @return
	 */
	private JComboBox selectSizeType()
	{
		final JComboBox tReturn = new JComboBox();

		tReturn.addItem("크기를 선택하세요");
		tReturn.addItem("600  *  800");
		tReturn.addItem("800  *  1024");
		tReturn.addItem("1024  * 1048");
		tReturn.addItem("1048  * 1060");
		tReturn.addItem("1060  * 1096");
		tReturn.addItem("사용자 지정");

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
										
						//  확인버튼을 눌렀을 때 이벤트 발생
						user.okBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								height = user.getHeight();
								weight = user.getWeight();
							}//  height와 weight에 초기화가 안된 값 0이 저장
						});
						// 닫기 버튼을 눌렀을 때 이벤트 발생
						user.cancleBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								height = user.getHeight();
								weight = user.getWeight();

							}// 실제 입력한 데이터의 값이 저장
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

		tReturn_1.addItem("방식을 선택하세요");
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

		tReturn_2.addItem("방식을 선택하세요");
		tReturn_2.addItem("테두리 생성(HTML변환시)");
		tReturn_2.addItem("테두리 소멸(HTML변환시)");
		
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
						
		}// 사용자로부터의 입력값(가로,세로)변수가 추가되어서 함수매개변수를 바꿨습니다.
	}


	/**
	 * selectFontSize : 폰트 크기를 조정하는 콤보박스를 생성
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
	 * processExitProgram : 프로그램 종료시 필요한 명령 수행
	 */


	private void processExitProgram(){
		dispose();
	}

	private void newFile(){

		int titleNum = 0;
		String sTitle = "Noname" + titleNum + ".ezt";

		// 새 프레임을 생성한다:
		ETEditorWindow tCFrame = new ETEditorWindow(sTitle);
		tCFrame.setVisible(true);

		//이 프레임을 전면으로 보내고 선택한다:
		tCFrame.toFront();
	}
	public int getLineHtml()
	{
		return getLine;
	}

	
}