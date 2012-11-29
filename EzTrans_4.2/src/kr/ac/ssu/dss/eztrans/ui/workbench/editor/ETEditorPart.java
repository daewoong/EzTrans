package kr.ac.ssu.dss.eztrans.ui.workbench.editor;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import kr.ac.ssu.dss.eztrans.contants.ETTagLists;

/**
 * 에디팅을 할 수 있는 공간
 * 위쪽은 컴포넌트들에 대한 드래그
 * 아래쪽은 사용자 입력창
 * @author Complete
 *
 */
@SuppressWarnings("serial")
public class ETEditorPart extends JDesktopPane{
	
	    private ETEditorWindow editorWindow = null;
	    public JTextPane tagEditingArea;
	    public StringBuffer sb = null;
	    
		public ETEditorPart() {
			
			super();
		
			super.setLayout(new BorderLayout());
			super.setBorder(BorderFactory.createEtchedBorder());
		
			this.buildContents();
			
		}
		
		private void buildContents(){
			
			super.setLayout(new BorderLayout());
			
			JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
			splitpane.setResizeWeight(0.9);
			splitpane.setOneTouchExpandable(true);
			
			JTabbedPane tabbedpane = this.typingEditingArea();
			//JPanel panel = this.dragEditingArea();
			
			this.editorWindow = new ETEditorWindow("NEW");		
			this.editorWindow.setVisible(true);
			
			splitpane.setTopComponent(this.editorWindow);
			splitpane.setBottomComponent(tabbedpane);
			
			super.add(splitpane, BorderLayout.CENTER);	
		}
		
		
		/**
		 * 태그 드래그를 통해 디자인 할 수 있는 영역지정
		 * @return panel: 태그 드래그를 통해 디자인이 가능한 판넬 영역
		 */
		
		@SuppressWarnings("unused")
		private JPanel dragEditingArea(){
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			panel.setBorder(BorderFactory.createTitledBorder("DragArea"));
			return panel;
		}
		
		/**
		 * 사용자가 직접 태그 타이핑이 가능한 영역
		 * @return tabbedpane: 타이핑이 가능한 탭 컴포넌트 기반의 에디팅 영역
		 */
		private JTabbedPane typingEditingArea(){
		
			JTabbedPane tabbedpane =  new JTabbedPane(JTabbedPane.TOP);
			this.createTagEditingArea();
			JTextPane textpane2 = new JTextPane();
			
			tabbedpane.add("Tag Editing Area", new JScrollPane(this.tagEditingArea));
			tabbedpane.add("Script", new JScrollPane(textpane2));
			
			return tabbedpane;
		}
		
		private void createTagEditingArea(){
			
			this.tagEditingArea = new JTextPane();
			
			this.sb = new StringBuffer();
			
			sb.append(ETTagLists.DOCTYPE+"\n");
			sb.append(ETTagLists.HTML+"\n");
			//sb.append(ETTagLists.HEAD+"\n");
			//sb.append(ETTagLists.TITLE+"\n");
			//sb.append(ETTagLists.ENDTITLE+"\n");
			//sb.append(ETTagLists.STYLE+"\n");
			//sb.append(ETTagLists.ENDSTYLE+"\n");
			//sb.append(ETTagLists.BODY+"\n");
			
			this.tagEditingArea.setText(sb.toString());
			
		}
		
		public JTextPane getTagEditingArea(){return this.tagEditingArea;}	
		public StringBuffer getStringBuffer(){return this.sb;}	
		public ETEditorWindow getETEditorWindow(){return this.editorWindow;}
		
}