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
 * ������ ���ǿ� ���� ������Ʈ ���� �߰��� �ǳ�
 * @author Chae
 *
 */
@SuppressWarnings("serial")
public class ETDataQueryLists extends JPanel{
	
	/** �����ϴ� ��ư ��ü�鿡 ���� ����Ʈ */
	private Vector<JToggleButton> tListOfButtons = null;

	private ButtonGroup buttonGroup = null;

	/** ��ư�� ���� ���ڿ� ����: TAG */
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
	 * �־��� �гο� ��ư���� �߰��Ѵ�.
	 * 
	 * @param tPanel ��ư���� ������ ��ü �г�.
	 *            
	 */
	private void addButtons(JPanel tPanel) {

		//��ư�� �߰��Ѵ�:
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
	 * ��ü �гο� �־��� ��ư�� �߰��Ѵ�.
	 * 
	 * @param tPanel ��ư�� �߰��� ��ü �г�.     
	 * @param tButton ��ü �гο� �߰��� ��ư ��ü.
	 *           
	 */
	private void addButton(JPanel tPanel, JToggleButton tButton) {
		tPanel.add(tButton);         // �гο� ��ư�� �߰��Ѵ�.
		tListOfButtons.add(tButton); // ��ư ����Ʈ�� ��ư�� �߰��Ѵ�.
		buttonGroup.add(tButton);
	}

	/**
	 * sButtonCommand�� ���� ��ư�� �гο��� �����Ѵ�.
	 * 
	 * @param sButtonCommand ������ ��ư�� ���ڿ�.
	 * @param bSimpleSelect ���̸� �ܼ��� ��ư�� �����ϴ� ���� �ǹ��Ѱ�, �����̸� �ٸ� ��ư�� ���� ����� �Ŀ� �ش� ��ư�� �����Ѵ�.
	 *            
	 */
	private void selectButton(String sButtonCommand, boolean bSimpleSelect) {

		System.out.println(sButtonCommand);

		// ��ư���� ��ȿ���� Ȯ���Ѵ�:
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
