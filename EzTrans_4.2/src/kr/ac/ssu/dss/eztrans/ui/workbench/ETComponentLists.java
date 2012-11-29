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
 * HTML5 태그 컴포넌트가 추가될 판넬
 * @author Complete
 * 
 */
@SuppressWarnings("serial")
public class ETComponentLists extends JPanel{
	

		/** 현존하는 버튼 객체들에 대한 리스트 */
		private Vector<JToggleButton> tListOfButtons = null;
		
		private ButtonGroup buttonGroup = null;

		/** 버튼을 위한 문자열 선언: TAG */
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
		 * 주어진 패널에 버튼들을 추가한다.
		 * 
		 * @param tPanel 버튼들이 놓여질 객체 패널.
		 *            
		 */
		private void addButtons(JPanel tPanel) {
			
			//버튼을 추가한다:
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
			
			//각 버튼에 해당하는 다이얼로그를 띄운다
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
