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
 * ��ü EzTrans �ܰ��� ���
 * 
 * @author Complete
 * 
 */
@SuppressWarnings("serial")
public class ETWorkbench extends JFrame {

	// Text �̸�
	public static String fileName = "";

	// �ؽ�Ʈ �Ӽ�
	private JComboBox tFontList; // ��Ʈ ����
	private JComboBox tFontSize; // ��Ʈ ũ��
	private JToggleButton tBold; // ����
	private JToggleButton tItaly; // ���Ÿ�
	private JToggleButton tUnderline; // �������
	private JToggleButton tAlignLeft; // ���� ����
	private JToggleButton tAlignCenter; // ��� ����
	private JToggleButton tAlignRight; // ������ ����
	private ETNewFileDialog handler;

	public ETEditorPart editorPart = null;
	private ETNewFileDialog news = null;

	private boolean newFile = false;
	private int getWidth; // htmlâ�� ���� �׵θ�
	private int getHight; // htmlâ�� ���� �׵θ�
	private int select;
	private int getLine;
	//��ư�� ���ȴ��� ����
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
		tabbedpane.add("������Ʈ", new ETComponentLists());
		tabbedpane.add("�±�", new ETTagPanel(this.editorPart));
		tabbedpane.add("������ ����", new ETDataQueryLists());

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

		JMenu hFileMenu = new JMenu("����(H)");
		hFileMenu.setMnemonic(KeyEvent.VK_H);

		JMenuItem hSearch = new JMenuItem("����(H)");
		hSearch.setMnemonic(KeyEvent.VK_H);

		hSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				KeyEvent.CTRL_MASK));
		hSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		hFileMenu.add(hSearch);

		return hFileMenu;
	}

	private JMenu createFileMenus3() {

		JMenu tFileMenu = new JMenu("�����ϱ�(T)");
		tFileMenu.setMnemonic(KeyEvent.VK_T);

		JMenuItem tSearch = new JMenuItem("FTP ����(b)");
		tSearch.setMnemonic(KeyEvent.VK_B);

		tSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				KeyEvent.CTRL_MASK));
		tSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		tFileMenu.add(tSearch);

		return tFileMenu;
	}

	private JMenu createFileMenus2() {

		JMenu aFileMenu = new JMenu("���ã��(A)");
		aFileMenu.setMnemonic(KeyEvent.VK_A);

		JMenuItem aSearch = new JMenuItem("�ֱٿ� ����� �ּ�(S)");
		aSearch.setMnemonic(KeyEvent.VK_S);

		aSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		aSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		aFileMenu.add(aSearch);

		return aFileMenu;
	}

	private JMenu createFileMenus() {

		JMenu eFileMenu = new JMenu("����(E)");
		eFileMenu.setMnemonic(KeyEvent.VK_E);

		JMenuItem eCopy = new JMenuItem("����(C)");
		eCopy.setMnemonic(KeyEvent.VK_C);

		eCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.CTRL_MASK));
		eCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		eFileMenu.add(eCopy);

		JMenuItem eInput = new JMenuItem("�ٿ��ֱ�(V)");
		eInput.setMnemonic(KeyEvent.VK_V);

		eInput.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				KeyEvent.CTRL_MASK));
		eInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		eFileMenu.add(eInput);

		JMenuItem eSearch = new JMenuItem("ã��(F)");
		eSearch.setMnemonic(KeyEvent.VK_F);

		eSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		eSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		eFileMenu.add(eSearch);

		return eFileMenu;
	}

	private JMenu createFileMenu() {

		JMenu cFileMenu = new JMenu("����(F)");
		cFileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem cNew = new JMenuItem("���θ����(N)");
		cNew.setMnemonic(KeyEvent.VK_N);

		cNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));

		cNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				news = new ETNewFileDialog(editorPart);
				news.setVisible(true);

				newFile();

				// �׼�
			}
		});

		cFileMenu.add(cNew);

		JMenuItem cSave = new JMenuItem("�����ϱ�(S)");
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

		JMenuItem cLoad = new JMenuItem("�ҷ�����(L)");
		cLoad.setMnemonic(KeyEvent.VK_L);
		cLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				KeyEvent.CTRL_MASK));
		cLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});

		cFileMenu.add(cLoad);

		JMenuItem cTrans = new JMenuItem("��ȯ�ϱ�(T)");
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

		JMenuItem cExit = new JMenuItem("�����ϱ�(X)");
		cExit.setMnemonic(KeyEvent.VK_X);
		cExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				KeyEvent.CTRL_MASK));
		cExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
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

		JToolBar toolBar = new JToolBar("�⺻");
		addToolButtons(toolBar);

		return toolBar;
	}

	private Component createToolbar2() {

		JToolBar toolBar = new JToolBar("����");

		addToolButtons2(toolBar);

		return toolBar;
	}

	private void addToolButtons(JToolBar tToolbarPanel) {

		AbstractButton tNew = new JButton("���θ����", new ImageIcon(
				ETIconPaths.NEW));
		tNew.setToolTipText("New (Ctrl+N)");
		tNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				news = new ETNewFileDialog(editorPart);
				news.setVisible(true);
				newFile = true;
				// �׼�
				newFile();
			}
		});
		tNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		tNew.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tNew);

		AbstractButton tLoad = new JButton("�ҷ�����", new ImageIcon(
				ETIconPaths.OPEN));
		tLoad.setToolTipText("Load (Ctrl+L)");
		tLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
			}
		});
		tLoad.setVerticalTextPosition(SwingConstants.BOTTOM);
		tLoad.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tLoad);

		AbstractButton tSave = new JButton("�����ϱ�", new ImageIcon(
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

		AbstractButton tQSave = new JButton("�����̺�", new ImageIcon(
				ETIconPaths.SAVE));
		tQSave.setToolTipText("QuickSave (Ctrl+Q)");
		tQSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �׼�
				new ETSaveText(editorPart);
			}
		});
		tQSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		tQSave.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tQSave);

		AbstractButton tTransform = new JButton("��ȯ�ϱ�", new ImageIcon(
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

		AbstractButton tPreview = new JButton("�̸�����", new ImageIcon(
				ETIconPaths.PREVIEW));
		tPreview.setToolTipText("Preview (Ctrl+P)");
		tPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ETPreview();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// �׼�
				
			}
		});

		tPreview.setVerticalTextPosition(SwingConstants.BOTTOM);
		tPreview.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tPreview);
		tToolbarPanel.addSeparator();

		AbstractButton tHelp = new JButton("����", new ImageIcon(
				ETIconPaths.HELP));
		tHelp.setToolTipText("Help (Ctrl+H)");
		tHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ETHelpDialog();
				// �׼�
			}
		});
		tHelp.setVerticalTextPosition(SwingConstants.BOTTOM);
		tHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		tToolbarPanel.add(tHelp);

	}

	private void addToolButtons2(JToolBar tToolbarPanel) {

		// �۲� ���� �޺��ڽ��� �߰��Ѵ�:
		tFontList = selectFontType();
		tFontList.setToolTipText("�۲� ����");
		// �̺�Ʈ �ڵ鷯 ����:
		tToolbarPanel.add(tFontList);

		// �۲� ũ�� ���� �޺��ڽ��� �߰��Ѵ�:
		tFontSize = selectFontSize();
		tFontSize.setToolTipText("�۲� ũ�� ����");

		// �̺�Ʈ �ڵ鷯 ����:
		tToolbarPanel.add(tFontSize);
		tToolbarPanel.addSeparator();

		// ���� �۾�ü ��ư�� �߰��Ѵ�:
		tBold = new JToggleButton(new ImageIcon(ETIconPaths.BOLD));
		tBold.setToolTipText("����");
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
				// �׼�
			}
		});
		tToolbarPanel.add(tBold);

		// ������ �۾�ü ��ư�� �߰��Ѵ�:
		tItaly = new JToggleButton(new ImageIcon(ETIconPaths.ITALIC));
		tItaly.setToolTipText("���۲�");
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
				// �׼�
			}
		});
		tToolbarPanel.add(tItaly);

		/*
		// ���� �۾�ü ��ư�� �߰��Ѵ�:
		tUnderline = new JToggleButton(new ImageIcon(ETIconPaths.UNDER));
		tUnderline.setToolTipText("����");
		tUnderline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(ETEditorPage.fontStyle);
				
				ETEditorPage.isChanged = true;
				// �׼�
			}
		});
		tToolbarPanel.add(tUnderline);
		*/
		tToolbarPanel.addSeparator();

		// ���� ���� ��ư�� �߰��Ѵ�:
		tAlignLeft = new JToggleButton(new ImageIcon(ETIconPaths.LEFT));
		tAlignLeft.setToolTipText("���� ����");
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
				// �׼�
			}
		});
		tToolbarPanel.add(tAlignLeft);

		// ��� ���� ��ư�� �߰��Ѵ�:
		tAlignCenter = new JToggleButton(new ImageIcon(ETIconPaths.CENTER));
		tAlignCenter.setToolTipText("��� ����");
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
				// �׼�
			}
		});
		tToolbarPanel.add(tAlignCenter);

		// ������ ���� ��ư�� �߰��Ѵ�:
		tAlignRight = new JToggleButton(new ImageIcon(ETIconPaths.RIGHT));
		tAlignRight.setToolTipText("������ ����");
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
				// �׼�
			}
		});
		tToolbarPanel.add(tAlignRight);
		tToolbarPanel.addSeparator();

	}

	/**
	 * selectFontType : ��Ʈ Ÿ���� �����ϴ� �޺��ڽ��� ����
	 * 
	 * @return
	 */
	private JComboBox selectFontType() {

		final JComboBox tReturn = new JComboBox();

		// �ý��� �� �ִ� ��� ���Ͽ� ���� ������ ��´�:
		GraphicsEnvironment tGE = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] tListOfFonts = tGE.getAvailableFontFamilyNames(Locale.KOREAN);
		for (int i = 0; i < tListOfFonts.length; i++) {

			String sFontName = tListOfFonts[i];
			// ��ȿ�� �ý��� �۲ø� �߰��Ѵ�:
			if (sFontName.compareTo("��") >= 0
					|| (sFontName.compareTo("(") >= 0 && sFontName
					.compareTo("`") < 0))
				tReturn.addItem(tListOfFonts[i]);
		}

		// �׼� �����ʸ� �����Ѵ�:
		tReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// EditorPage�� ��Ʈ�� ������ ���� �Ǿ����� �˷��ش�
				ETEditorPage.fontName = tReturn.getSelectedItem().toString();
				ETEditorPage.isChanged = true;
			}
		});

		return tReturn;
	}

	/**
	 * selectFontSize : ��Ʈ ũ�⸦ �����ϴ� �޺��ڽ��� ����
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

		// �׼� �����ʸ� �����Ѵ�:
		tReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// EditorPage�� ��Ʈ�� ũ�Ⱑ ����Ǿ����� �˷��ش�
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

		// ����Ʈ ��Ʈ ũ�⸦ �����Ѵ�:
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
	 * processExitProgram : ���α׷� ����� �ʿ��� ��� ����
	 */
	private void processExitProgram() {
		System.exit(0);
	}

	private void newFile() {

		int titleNum = 0;
		String sTitle = "Noname" + titleNum + ".ezt";

		// �� �������� �����Ѵ�:
		ETEditorWindow tCFrame = new ETEditorWindow(sTitle);
		tCFrame.setVisible(true);

		// �� �������� �������� ������ �����Ѵ�:
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
