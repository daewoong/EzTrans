package kr.ac.ssu.dss.eztrans.ui.workbench;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import kr.ac.ssu.dss.eztrans.contants.ETIconPaths;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETAudioDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETDragandDropsDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETFormButtonDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETHoverDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETHyperLinkDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETImageDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETTextDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETVideoDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETNavigationDialog;


/**
 * HTML5 �±� ������Ʈ�� �߰��� �ǳ�
 * @author Complete
 * 
 */
@SuppressWarnings("serial")
public class ETComponentLists extends JPanel{
	

		/** �����ϴ� ��ư ��ü�鿡 ���� ����Ʈ */
		private Vector<JToggleButton> tListOfButtons = null;
		
		private ButtonGroup buttonGroup = null;

		/** ��ư�� ���� ���ڿ� ����: TAG */
		public static final String sText = "Text";
		public static final String sImage = "Image";
		public static final String sVideo = "Video";
		public static final String sAudio = "Audio";
		public static final String sHyperLink = "HyperLink";
		///////////////////////////////////////////////
		public static final String sDrag_Drops = "Drag_Drops";
		public static final String sForm = "Form Button";
		public static final String sHover = "Hover";
		/////////////////////////////////////////////o
		public static final String sNavigation = "Navigation";
		public static ETVideoDialog etVideoDialog;
		public static ETAudioDialog etAudioDialog;
		
		public ETComponentLists() {
		
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
			ETComponentListButton tText = new ETComponentListButton("TEXT", ETIconPaths.TEXT);
			tText.setActionCommand(sText);
			tText.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tText);
			
			
			ETComponentListButton tImage = new ETComponentListButton("IMAGE", ETIconPaths.IMAGE);
			tImage.setActionCommand(sImage);
			tImage.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tImage);
			
			ETComponentListButton tVideo = new ETComponentListButton("VIDEO", ETIconPaths.VIDEO);
			tVideo.setActionCommand(sVideo);
			tVideo.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tVideo);
			
			ETComponentListButton tAudio = new ETComponentListButton("AUDIO", ETIconPaths.AUDIO);
			tAudio.setActionCommand(sAudio);
			tAudio.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tAudio);
			
			ETComponentListButton tHyperLink = new ETComponentListButton("HYPERLINK", ETIconPaths.HYPERLINK);
			tHyperLink.setActionCommand(sHyperLink);
			tHyperLink.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tHyperLink);
		/////////////////////////////////////////////////////////////////////	
			ETComponentListButton tDrag_Drops = new ETComponentListButton("DRAG&DROP", ETIconPaths.DRAGDROPS);
			tDrag_Drops.setActionCommand(sDrag_Drops);
			tDrag_Drops.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tDrag_Drops);
			
			ETComponentListButton tDrops = new ETComponentListButton("FORMBUTTON", ETIconPaths.FORMBUTTON);
			tDrops.setActionCommand(sForm);
			tDrops.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tDrops);
			
			ETComponentListButton tHover = new ETComponentListButton("HOVEREFFECT", ETIconPaths.HOVER);
			tHover.setActionCommand(sHover);
			tHover.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tHover);
			
			ETComponentListButton tNavigation = new ETComponentListButton("NAVIGATION", ETIconPaths.NAVIGATION);
			tNavigation.setActionCommand(sNavigation);
			tNavigation.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});
			addButton(tPanel, (JToggleButton) tNavigation);
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
			
			//�� ��ư�� �ش��ϴ� ���̾�α׸� ����
			if(tNewlySelected.getText().equals("TEXT")){
				new ETTextDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("IMAGE")){
				new ETImageDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("VIDEO")){
				etVideoDialog =  new ETVideoDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("AUDIO")){
				etAudioDialog = new ETAudioDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("HYPERLINK")){
				new ETHyperLinkDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("DRAG&DROP")){
				new ETDragandDropsDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("FORMBUTTON")){
				new ETFormButtonDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("HOVEREFFECT")){
				new ETHoverDialog();
				tNewlySelected.setSelected(false);
			}
			else if(tNewlySelected.getText().equals("NAVIGATION")){
				new ETNavigationDialog();
				tNewlySelected.setSelected(false);
			}	
		}
		
}
