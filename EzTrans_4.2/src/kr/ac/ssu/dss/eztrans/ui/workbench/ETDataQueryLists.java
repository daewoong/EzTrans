package kr.ac.ssu.dss.eztrans.ui.workbench;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kr.ac.ssu.dss.eztrans.ETMain;
import kr.ac.ssu.dss.eztrans.contants.ETIconPaths;
import kr.ac.ssu.dss.eztrans.data.db.ETSqlLogin;
import kr.ac.ssu.dss.eztrans.data.mining.ETWekaExplorer;
import kr.ac.ssu.dss.eztrans.data.olap.ETOlapLogin;

/**
 * 데이터 질의에 관한 컴포넌트 들이 추가될 판넬
 * @author Chae
 *
 */
@SuppressWarnings("serial")
public class ETDataQueryLists extends JPanel{
	
	/** 현존하는 버튼 객체들에 대한 리스트 */
	private Vector<JToggleButton> tListOfButtons = null;

	private ButtonGroup buttonGroup = null;

	/** 버튼을 위한 문자열 선언: TAG */
	public static final String sDB = "DB Query";
	public static final String sOlap = "OLAP";
	public static final String sDataMining = "Data Mining";

	public ETDataQueryLists() {

		this.tListOfButtons = new Vector<JToggleButton>();
		this.buttonGroup = new ButtonGroup();

		super.setBorder(BorderFactory.createEtchedBorder());
		super.setLayout(new GridLayout(21,1));

		this.addButtons(this);

	}

	/**
	 * 주어진 패널에 버튼들을 추가한다.
	 * 
	 * @param tPanel 버튼들이 놓여질 객체 패널.
	 *            
	 */
	private void addButtons(JPanel tPanel) {

		//버튼을 추가한다:
		ETComponentListButton tDB = new ETComponentListButton(sDB, ETIconPaths.DB);
		tDB.setActionCommand(sDB);
		tDB.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
			}
		});
		addButton(tPanel, (JToggleButton) tDB);


		ETComponentListButton tOlap = new ETComponentListButton("OLAP", ETIconPaths.OLAP);
		tOlap.setActionCommand(sOlap);
		tOlap.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
			}
		});
		addButton(tPanel, (JToggleButton) tOlap);

		ETComponentListButton tDataMining = new ETComponentListButton("Data Mining", ETIconPaths.DATAMINING);
		tDataMining.setActionCommand(sDataMining);
		tDataMining.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
			}
		});
		addButton(tPanel, (JToggleButton) tDataMining);
	}

	/**
	 * 객체 패널에 주어진 버튼을 추가한다.
	 * 
	 * @param tPanel 버튼이 추가될 객체 패널.     
	 * @param tButton 객체 패널에 추가될 버튼 객체.
	 *           
	 */
	private void addButton(JPanel tPanel, JToggleButton tButton) {
		tPanel.add(tButton);         // 패널에 버튼을 추가한다.
		tListOfButtons.add(tButton); // 버튼 리스트에 버튼을 추가한다.
		buttonGroup.add(tButton);
	}

	/**
	 * sButtonCommand를 가진 버튼을 패널에서 선택한다.
	 * 
	 * @param sButtonCommand 선택할 버튼명 문자열.
	 * @param bSimpleSelect 참이면 단순히 버튼을 선택하는 것을 의미한고, 거짓이면 다른 버튼을 선택 취소한 후에 해당 버튼을 선택한다.
	 *            
	 */
	private void selectButton(String sButtonCommand, boolean bSimpleSelect) {

		System.out.println(sButtonCommand);

		// 버튼명이 유효한지 확인한다:
		JToggleButton tNewlySelected = null;

		for (int i = 0; i < tListOfButtons.size(); i++)
			if (tListOfButtons.get(i).getActionCommand().equals(sButtonCommand)) {
				tNewlySelected = tListOfButtons.get(i);
				break;
			}

		if (tNewlySelected == null)
			return;

		tNewlySelected.setSelected(true);

		if (bSimpleSelect)
			return;

		if(tNewlySelected.getText().equals(sDB)){
			@SuppressWarnings("unused")
			ETSqlLogin sqlLogin = new ETSqlLogin();
		}
		else if(tNewlySelected.getText().equals(sOlap)){
			@SuppressWarnings("unused")
			ETOlapLogin olapLogin = new ETOlapLogin();
		}
		else if(tNewlySelected.getText().equals(sDataMining)){
					
			ETWekaExplorer.main(new String[0]);
			
		}
	}
}
