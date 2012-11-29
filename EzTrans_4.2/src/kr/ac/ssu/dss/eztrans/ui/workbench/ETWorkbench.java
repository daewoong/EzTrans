package kr.ac.ssu.dss.eztrans.ui.workbench;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kr.ac.ssu.dss.eztrans.contants.ETIconPaths;
import kr.ac.ssu.dss.eztrans.generator.ETEditorTransform;
import kr.ac.ssu.dss.eztrans.generator.ETSaveText;
import kr.ac.ssu.dss.eztrans.generator.ETMakeHTML5File;

import kr.ac.ssu.dss.eztrans.ui.dialogs.menu.ETHelpDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.menu.ETNewFileDialog;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPart;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorWindow;

/**
 * 전체 EzTrans 외관을 담당
 * 
 * @author Complete
 * 
 */
@SuppressWarnings("serial")
public class ETWorkbench extends JFrame {

	// Text 이름
	public static String fileName = "";

	// 텍스트 속성
	private JComboBox tFontList; // 폰트 종류
	private JComboBox tFontSize; // 폰트 크기
	private JToggleButton tBold; // 볼드
	private JToggleButton tItaly; // 이탤릭
	private JToggleButton tUnderline; // 언더라인
	private JToggleButton tAlignLeft; // 왼쪽 맞춤
	private JToggleButton tAlignCenter; // 가운데 맞춤
	private JToggleButton tAlignRight; // 오른쪽 맞춤
	private ETNewFileDialog handler;

	public ETEditorPart editorPart = null;
	private ETNewFileDialog news = null;

	private boolean newFile = false;
	private int getWidth; // html창의 가로 테두리
	private int getHight; // html창의 세로 테두리
	private int select;
	private int getLine;
	//버튼이 눌렸는지 여부
	private boolean isBold;
	private boolean isItalic;
	private boolean isUnder;
	private boolean alignLeft;
	private boolean alignCenter;
	private boolean alignRight;
	

	public ETWorkbench() {

		super("EzTrans");

		this.setBounds();
		this.setIconImage(new ImageIcon(ETIconPaths.LOGO).getImage());
		this.buildContentPane(super.getContentPane());

		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addListeners();

	}

	private void setBounds() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int windowWidth = screenSize.width / 3 * 2;
		int windowHeight = screenSize.height / 3 * 2;
		setBounds((screenSize.width - windowWidth) >> 1,
				(screenSize.height - windowHeight) >> 1, windowWidth,
				windowHeight);
	}

	private void buildContentPane(Container contentPane) {

		contentPane.setLayout(new BorderLayout());

		setJMenuBar(buildMenuBar());
		contentPane.add(buildToolBar(), BorderLayout.NORTH);

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		splitpane.setResizeWeight(0.8);
		splitpane.setOneTouchExpandable(true);

		this.editorPart = new ETEditorPart();

		JTabbedPane tabbedpane = new JTabbedPane(JTabbedPane.TOP);
		tabbedpane.add("컴포넌트", new ETComponentLists());
		tabbedpane.add("태그", new ETTagPanel(this.editorPart));
		tabbedpane.add("데이터 질의", new ETDataQueryLists());

		this.editorPart.putClientProperty("JDesktopPane.dragMode", "outline");

		splitpane.setRightComponent(tabbedpane);
		splitpane.setLeftComponent(this.editorPart);

		contentPane.add(splitpane, BorderLayout.CENTER);
		contentPane.add(createStatusBar(), BorderLayout.SOUTH);
	}

	private JMenuBar buildMenuBar() {

		JMenuBar cMenuBar = new JMenuBar();
		JMenu cFileMenu = createFileMenu();
		cMenuBar.add(cFileMenu);

		JMenu eFileMenu = createFileMenus();
		JMenu aFileMenu = createFileMenus2();
		JMenu tFileMenu = createFileMenus3();
		JMenu hFileMenu = createFileMenus4();

		cMenuBar.add(eFileMenu);
		cMenuBar.add(aFileMenu);
		cMenuBar.add(tFileMenu);
		cMenuBar.add(hFileMenu);

		return cMenuBar;
	}

	private JMenu createFileMenus4() {

		JMenu hFileMenu = new JMenu("도움말(H)");
		hFileMenu.setMnemonic(KeyEvent.VK_H);

		JMenuItem hSearch = new JMenuItem("내용(H)");
		hSearch.setMnemonic(KeyEvent.VK_H);

		hSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				KeyEvent.CTRL_MASK));
		hSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		hFileMenu.add(hSearch);

		return hFileMenu;
	}

	private JMenu createFileMenus3() {

		JMenu tFileMenu = new JMenu("연결하기(T)");
		tFileMenu.setMnemonic(KeyEvent.VK_T);

		JMenuItem tSearch = new JMenuItem("FTP 전송(b)");
		tSearch.setMnemonic(KeyEvent.VK_B);

		tSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				KeyEvent.CTRL_MASK));
		tSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		tFileMenu.add(tSearch);

		return tFileMenu;
	}

	private JMenu createFileMenus2() {

		JMenu aFileMenu = new JMenu("즐겨찾기(A)");
		aFileMenu.setMnemonic(KeyEvent.VK_A);

		JMenuItem aSearch = new JMenuItem("최근에 사용한 주소(S)");
		aSearch.setMnemonic(KeyEvent.VK_S);

		aSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		aSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		aFileMenu.add(aSearch);

		return aFileMenu;
	}

	private JMenu createFileMenus() {

		JMenu eFileMenu = new JMenu("편집(E)");
		eFileMenu.setMnemonic(KeyEvent.VK_E);

		JMenuItem eCopy = new JMenuItem("복사(C)");
		eCopy.setMnemonic(KeyEvent.VK_C);

		eCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.CTRL_MASK));
		eCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		eFileMenu.add(eCopy);

		JMenuItem eInput = new JMenuItem("붙여넣기(V)");
		eInput.setMnemonic(KeyEvent.VK_V);

		eInput.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				KeyEvent.CTRL_MASK));
		eInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		eFileMenu.add(eInput);

		JMenuItem eSearch = new JMenuItem("찾기(F)");
		eSearch.setMnemonic(KeyEvent.VK_F);

		eSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		eSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		eFileMenu.add(eSearch);

		return eFileMenu;
	}

	private JMenu createFileMenu() {

		JMenu cFileMenu = new JMenu("파일(F)");
		cFileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem cNew = new JMenuItem("새로만들기(N)");
		cNew.setMnemonic(KeyEvent.VK_N);

		cNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));

		cNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				news = new ETNewFileDialog(editorPart);
				news.setVisible(true);

				newFile();

				// 액션
			}
		});

		cFileMenu.add(cNew);

		JMenuItem cSave = new JMenuItem("저장하기(S)");
		cSave.setMnemonic(KeyEvent.VK_S);
		cSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));

		cSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserchoice();

				try {
					if (newFile == true) {
						System.out.println(select);
						new ETMakeHTML5File(editorPart.getETEditorWindow()
								.getNewPage(), editorPart, select, getWidth,
								getHight, getLine);
					} else {
						new ETMakeHTML5File(editorPart.getETEditorWindow()
								.getOriginalPage(), editorPart, select,
								getWidth, getHight, getLine);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		cFileMenu.add(cSave);

		JMenuItem cLoad = new JMenuItem("불러오기(L)");
		cLoad.setMnemonic(KeyEvent.VK_L);
		cLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				KeyEvent.CTRL_MASK));
		cLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		cFileMenu.add(cLoad);

		JMenuItem cTrans = new JMenuItem("변환하기(T)");
		cTrans.setMnemonic(KeyEvent.VK_T);
		cTrans.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				KeyEvent.CTRL_MASK));
		cTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserchoice();
				try {
					if (newFile == true) {
						new ETEditorTransform(editorPart.getETEditorWindow()
								.getNewPage(), editorPart, select, getWidth,
								getHight, getLine);
					} else {
						new ETEditorTransform(editorPart.getETEditorWindow()
								.getNewPage(), editorPart, select, getWidth,
								getHight, getLine);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		cFileMenu.add(cTrans);
		cFileMenu.addSeparator();

		JMenuItem cExit = new JMenuItem("종료하기(X)");
		cExit.setMnemonic(KeyEvent.VK_X);
		cExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				KeyEvent.CTRL_MASK));
		cExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});

		cFileMenu.add(cExit);

		return cFileMenu;
	}

	public void setUserchoice() {

		getWidth = this.editorPart.getETEditorWindow().getsetpanelSizeX();
		getHight = this.editorPart.getETEditorWindow().getsetpanelSizeY();
		select = this.editorPart.getETEditorWindow().getselect();
		getLine = ETNewFileDialog.getLine;

	}

	private Component buildToolBar() {

		JTabbedPane tabbedPane = new JTabbedPane() {

			public void add(Component component, Object constraints) {
				super.add(component, component.getName());
			}
		};

		tabbedPane.add(createToolbar());
		tabbedPane.add(createToolbar2());

		return tabbedPane;
	}

	private Component createToolbar() {

		JToolBar toolBar = new JToolBar("기본");
		addToolButtons(toolBar);

		return toolBar;
	}

	private Component createToolbar2() {

		JToolBar toolBar = new JToolBar("서식");

		addToolButtons2(toolBar);

		return toolBar;
	}

	private void addToolButtons(JToolBar tToolbarPanel) {

		AbstractButton tNew = new JButton("새로만들기", new ImageIcon(
				ETIconPaths.NEW));
		tNew.setToolTipText("New (Ctrl+N)");
		tNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				news = new ETNewFileDialog(editorPart);
				news.setVisible(true);
				newFile = true;
				// 액션
				newFile();
			}
		});
		tNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		tNew.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tNew);

		AbstractButton tLoad = new JButton("불러오기", new ImageIcon(
				ETIconPaths.OPEN));
		tLoad.setToolTipText("Load (Ctrl+L)");
		tLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
			}
		});
		tLoad.setVerticalTextPosition(SwingConstants.BOTTOM);
		tLoad.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tLoad);

		AbstractButton tSave = new JButton("저장하기", new ImageIcon(
				ETIconPaths.SAVE));
		tSave.setToolTipText("Save (Ctrl+S)");
		tSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserchoice();
				
				try {

					if (newFile == true) {
						new ETMakeHTML5File(editorPart.getETEditorWindow()
								.getNewPage(), editorPart, select, getWidth,
								getHight, getLine);
					} else {
						new ETMakeHTML5File(editorPart.getETEditorWindow()
								.getOriginalPage(), editorPart, select,
								getWidth, getHight, getLine);
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		tSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		tSave.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tSave);
		tToolbarPanel.addSeparator();

		AbstractButton tQSave = new JButton("퀵세이브", new ImageIcon(
				ETIconPaths.SAVE));
		tQSave.setToolTipText("QuickSave (Ctrl+Q)");
		tQSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 액션
				new ETSaveText(editorPart);
			}
		});
		tQSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		tQSave.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tQSave);

		AbstractButton tTransform = new JButton("변환하기", new ImageIcon(
				ETIconPaths.TRANSFORM));
		tTransform.setToolTipText("Transform (Ctrl+T)");
		tTransform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserchoice();
				try {

					if (newFile == true) {
						new ETEditorTransform(editorPart.getETEditorWindow()
								.getNewPage(), editorPart, select, getWidth,
								getHight, getLine);
					} else {
						new ETEditorTransform(editorPart.getETEditorWindow()
								.getOriginalPage(), editorPart, select, getWidth,
								getHight, getLine);
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		tTransform.setVerticalTextPosition(SwingConstants.BOTTOM);
		tTransform.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tTransform);
		
		// /////////////////////////////////////////////////////////////////////////////////////////

		AbstractButton tPreview = new JButton("미리보기", new ImageIcon(
				ETIconPaths.PREVIEW));
		tPreview.setToolTipText("Preview (Ctrl+P)");
		tPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ETPreview();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// 액션
				
			}
		});

		tPreview.setVerticalTextPosition(SwingConstants.BOTTOM);
		tPreview.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tPreview);
		tToolbarPanel.addSeparator();

		AbstractButton tHelp = new JButton("도움말", new ImageIcon(
				ETIconPaths.HELP));
		tHelp.setToolTipText("Help (Ctrl+H)");
		tHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ETHelpDialog();
				// 액션
			}
		});
		tHelp.setVerticalTextPosition(SwingConstants.BOTTOM);
		tHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tHelp);

	}

	private void addToolButtons2(JToolBar tToolbarPanel) {

		// 글꼴 선택 콤보박스를 추가한다:
		tFontList = selectFontType();
		tFontList.setToolTipText("글꼴 선택");
		// 이벤트 핸들러 설정:
		tToolbarPanel.add(tFontList);

		// 글꼴 크기 선택 콤보박스를 추가한다:
		tFontSize = selectFontSize();
		tFontSize.setToolTipText("글꼴 크기 선택");

		// 이벤트 핸들러 설정:
		tToolbarPanel.add(tFontSize);
		tToolbarPanel.addSeparator();

		// 굵은 글씨체 버튼을 추가한다:
		tBold = new JToggleButton(new ImageIcon(ETIconPaths.BOLD));
		tBold.setToolTipText("굵게");
		tBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isBold)
					isBold = false;
				else
					isBold = true;

				if(isBold)
					ETEditorPage.fontStyle += Font.BOLD;
				else
					ETEditorPage.fontStyle -= Font.BOLD;
				
				ETEditorPage.isChanged = true;
				
				System.out.println(ETEditorPage.fontStyle);
				// 액션
			}
		});
		tToolbarPanel.add(tBold);

		// 기울어진 글씨체 버튼을 추가한다:
		tItaly = new JToggleButton(new ImageIcon(ETIconPaths.ITALIC));
		tItaly.setToolTipText("기운글꼴");
		tItaly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isItalic)
					isItalic = false;
				else
					isItalic = true;

				if(isItalic)
					ETEditorPage.fontStyle += Font.ITALIC;
				else
					ETEditorPage.fontStyle -= Font.ITALIC;
				
				ETEditorPage.isChanged = true;

				System.out.println(ETEditorPage.fontStyle);
				// 액션
			}
		});
		tToolbarPanel.add(tItaly);

		/*
		// 밑줄 글씨체 버튼을 추가한다:
		tUnderline = new JToggleButton(new ImageIcon(ETIconPaths.UNDER));
		tUnderline.setToolTipText("밑줄");
		tUnderline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(ETEditorPage.fontStyle);
				
				ETEditorPage.isChanged = true;
				// 액션
			}
		});
		tToolbarPanel.add(tUnderline);
		*/
		tToolbarPanel.addSeparator();

		// 왼쪽 맞춤 버튼을 추가한다:
		tAlignLeft = new JToggleButton(new ImageIcon(ETIconPaths.LEFT));
		tAlignLeft.setToolTipText("왼쪽 맞춤");
		tAlignLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alignLeft)
					alignLeft = false;
				else
					alignLeft = true;

				if(alignLeft)
					ETEditorPage.fontAlign = "left";
				else
					ETEditorPage.fontAlign = "";
				
				ETEditorPage.isChanged = true;
				// 액션
			}
		});
		tToolbarPanel.add(tAlignLeft);

		// 가운데 맞춤 버튼을 추가한다:
		tAlignCenter = new JToggleButton(new ImageIcon(ETIconPaths.CENTER));
		tAlignCenter.setToolTipText("가운데 맞춤");
		tAlignCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alignCenter)
					alignCenter = false;
				else
					alignCenter = true;

				if(alignCenter)
					ETEditorPage.fontAlign = "center";
				else
					ETEditorPage.fontAlign = "";
				
				ETEditorPage.isChanged = true;
				// 액션
			}
		});
		tToolbarPanel.add(tAlignCenter);

		// 오른쪽 맞춤 버튼을 추가한다:
		tAlignRight = new JToggleButton(new ImageIcon(ETIconPaths.RIGHT));
		tAlignRight.setToolTipText("오른쪽 맞춤");
		tAlignRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alignRight)
					alignRight = false;
				else
					alignRight = true;

				if(alignRight)
					ETEditorPage.fontAlign = "right";
				else
					ETEditorPage.fontAlign = "";
				
				ETEditorPage.isChanged = true;
				// 액션
			}
		});
		tToolbarPanel.add(tAlignRight);
		tToolbarPanel.addSeparator();

	}

	/**
	 * selectFontType : 폰트 타입을 설정하는 콤보박스를 생성
	 * 
	 * @return
	 */
	private JComboBox selectFontType() {

		final JComboBox tReturn = new JComboBox();

		// 시스템 상에 있는 모든 파일에 대한 정보를 얻는다:
		GraphicsEnvironment tGE = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] tListOfFonts = tGE.getAvailableFontFamilyNames(Locale.KOREAN);
		for (int i = 0; i < tListOfFonts.length; i++) {

			String sFontName = tListOfFonts[i];
			// 유효한 시스템 글꼴만 추가한다:
			if (sFontName.compareTo("가") >= 0
					|| (sFontName.compareTo("(") >= 0 && sFontName
					.compareTo("`") < 0))
				tReturn.addItem(tListOfFonts[i]);
		}

		// 액션 리스너를 설정한다:
		tReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// EditorPage에 폰트의 종류가 변경 되었음을 알려준다
				ETEditorPage.fontName = tReturn.getSelectedItem().toString();
				ETEditorPage.isChanged = true;
			}
		});

		return tReturn;
	}

	/**
	 * selectFontSize : 폰트 크기를 조정하는 콤보박스를 생성
	 * 
	 * @return
	 */
	private JComboBox selectFontSize() {

		final JComboBox tReturn = new JComboBox();

		for (int i = 8; i <= 12; i++)
			tReturn.addItem(Integer.toString(i));

		for (int i = 14; i <= 28; i += 2)
			tReturn.addItem(Integer.toString(i));

		tReturn.addItem("36");
		tReturn.addItem("48");
		tReturn.addItem("72");
		tReturn.setEditable(true);

		// 액션 리스너를 설정한다:
		tReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// EditorPage에 폰트의 크기가 변경되었음을 알려준다
				for (int i = 0; i < 7; i++) {
					if (ETEditorPage.isSelected[i]) {
						ETEditorPage.fontSize = Integer.valueOf(tReturn
								.getSelectedItem().toString());
						ETEditorPage.isChanged = true;
						break;
					}
				}
			}
		});

		// 디폴트 폰트 크기를 설정한다:
		tReturn.setSelectedIndex(4);

		return tReturn;
	}

	private JPanel createStatusBar() {

		JPanel statusBar = new JPanel();
		return statusBar;
	}

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
	private void processExitProgram() {
		System.exit(0);
	}

	private void newFile() {

		int titleNum = 0;
		String sTitle = "Noname" + titleNum + ".ezt";

		// 새 프레임을 생성한다:
		ETEditorWindow tCFrame = new ETEditorWindow(sTitle);
		tCFrame.setVisible(true);

		// 이 프레임을 전면으로 보내고 선택한다:
		tCFrame.toFront();
	}

	public ETNewFileDialog getETComponentButtonHandler() {
		return news;
	}
	
	private void showLookAndFeel(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
}
