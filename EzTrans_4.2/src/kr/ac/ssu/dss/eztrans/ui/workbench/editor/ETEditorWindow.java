package kr.ac.ssu.dss.eztrans.ui.workbench.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

@SuppressWarnings("serial")
public class ETEditorWindow extends JInternalFrame {

	    private JPanel cPanel = null;
	    
	    private JScrollPane cScroll = null;
	    private ETEditorPage cPage = null;	
	    
		private JScrollPane cScroll_reNew = null;
		private ETEditorPage cPage_reNew = null;
		
		private int setpanelSizeX = 800;
		private int setpanelSizeY = 1024;
		public int select = 2;
		public int select2;
							
		/**
		 * EzTrans의 에디팅을 할 인너 프레임.
		 * 
		 * @param sTitle
		 *            프레임의 타이틀.
		 */
		public ETEditorWindow(String sTitle) {
			
			super(sTitle, true, true, true, true);
			
			this.cPage = new ETEditorPage();

			this.cPanel = new JPanel();
			this.cPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
			this.cPanel.setBackground(Color.GRAY);
			this.cPanel.add(this.cPage);
			
			this.cScroll = new JScrollPane(cPanel);
			super.getContentPane().add(cScroll, "Center");
		
			super.setBounds(50, 50, 600, 400);
			super.setMinimumSize(new Dimension(115, 115));

			setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
																
			addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosing(InternalFrameEvent e) {
					processCloseFrame();
				}
				public void internalFrameActivated(InternalFrameEvent e) {
		
				}

				public void internalFrameDeactivated(InternalFrameEvent e) {
					
				}
			}); 
		}
		public void eTEditorWindow_reNew(int choice, int sizeX, int sizeY) {
			setpanelSizeX = sizeX;
			setpanelSizeY = sizeY;
			select = choice;
			
			
			if(this.cPage !=null && this.cPage !=null && this.cScroll !=null)
			{
				this.remove(cPage);
				this.remove(cPanel);
				this.remove(cScroll);
							
				this.cPage = null;
				this.cPanel = null;
				this.cScroll = null;
				super.setMinimumSize(new Dimension(120, 120));
			}
			else
			{
				this.remove(cPage_reNew);
				this.remove(cPanel);
				this.remove(cScroll_reNew);
				super.setMinimumSize(new Dimension(125, 125));
				
			}
			
			this.cPage_reNew = new ETEditorPage(choice,sizeX,sizeY);
							
			this.cPanel = new JPanel();
			this.cPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
			this.cPanel.setBackground(Color.GRAY);
			this.cPanel.add(this.cPage_reNew);
			
			this.cScroll_reNew = new JScrollPane(cPanel);
			super.getContentPane().add(cScroll_reNew, "Center");
			
			super.setBounds(50, 50, 600, 400);
			super.setMinimumSize(new Dimension(120, 120));

			/*
			 * 
			super.setBounds(50, 50, 600, 400);
			super.setMinimumSize(new Dimension(115, 115));

			setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
																
			addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosing(InternalFrameEvent e) {
					processCloseFrame();
				}
				public void internalFrameActivated(InternalFrameEvent e) {
		
				}

				public void internalFrameDeactivated(InternalFrameEvent e) {
					
				}
				
			}); */
		}
		
		
		
		public int getselect()
		{
			return select;
		}
		
		public int getsetpanelSizeX()
		{
			return setpanelSizeX;
		}
		
		public int getsetpanelSizeY()
		{
			return setpanelSizeY;
		}
						
		private void processCloseFrame(){
			//System.exit(0);
		}
		public String toString(){
			String str = "editor";
			return str;
		}
		public ETEditorPage getOriginalPage(){return this.cPage;}
		public ETEditorPage getNewPage(){return this.cPage_reNew;}
	}

