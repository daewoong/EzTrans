package kr.ac.ssu.dss.eztrans.ui.workbench;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.*;
import java.io.FileNotFoundException;
import java.util.Vector;


import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import kr.ac.ssu.dss.eztrans.contants.ETTagLists;
import kr.ac.ssu.dss.eztrans.generator.ETSaveText;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPart;

/**
 * HTML5 태그 컴포넌트가 추가될 판넬
 * @author Complete
 * 
 */
@SuppressWarnings("serial")
public class ETTagPanel extends JPanel implements ActionListener{
	

		/** 폰트*/
		Font f1 = new Font("Serif", Font.BOLD, 15);
		/** 현존하는 버튼 객체들에 대한 리스트 */
		private Vector<JToggleButton> tListOfButtons = null;
		
		private ButtonGroup buttonGroup = null;
		
		/** 태그 리스트 */
		private Vector<String> tagList = null;
		
		/** 버튼을 위한 문자열 선언: TAG */
		public static final String sTSelect = "Select";

		private ETEditorPart eteditorPart = null;
		
		//생성자
		public ETTagPanel(ETEditorPart editorPart) {
		
			this.tListOfButtons = new Vector<JToggleButton>();
			this.buttonGroup = new ButtonGroup();
			
			this.tagList = new Vector<String>();
			this.eteditorPart = editorPart;
			
			super.setBorder(BorderFactory.createEtchedBorder());
			super.setLayout(new GridLayout(7,1));
			
			//this.addButtons(this);
			this.addLabels_List(this);
			this.addLabels_List2(this);
			this.addLabels_List3(this);
			this.addLabels_List4(this);
			this.addLabels_List5(this);
			this.addPanels(this);
			this.addPanels2(this);

		}

		public Vector<String> getTagList(){
			return this.tagList;
		}
		
		/**
		 * 주어진 패널에 버튼들을 추가한다.
		 * 
		 * @param tPanel 버튼들이 놓여질 객체 패널.
		 *            
		 */
		private void addButtons(JPanel tPanel) {
			
			//버튼을 추가한다1:
			ETComponentListButton tSelect = new ETComponentListButton("TEST");
	
			tSelect.setActionCommand(sTSelect);
			tSelect.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					selectButton(((JToggleButton) e.getSource()).getActionCommand(),false);
				}
			});

			addButton(tPanel, (JToggleButton) tSelect);
		}
		
		/**
		 * 주어진 패널에 라벨들을 추가한다.
		 * 
		 * @param tPanel 라벨들이 놓여질 객체 패널.
		 *            
		 */
		private void addLabels_List(JPanel tPanel) {
			
			//패널을 추가한다1:
			JPanel smallPanel1 = new JPanel();
			smallPanel1.setLayout(null);
			//라벨을 추가한다1:
			JLabel section = new JLabel("Section");
			section.setFont(f1);
			section.setSize(300, 20);
			//리스트를 추가한다1:
			List selectOne = new List(21);
			selectOne.addActionListener(this);
			selectOne.setLocation(0, 18);
			selectOne.setSize(440, 120);
			selectOne.add("address");selectOne.add("article");selectOne.add("aside");selectOne.add("blockquote");selectOne.add("body");
			selectOne.add("details");selectOne.add("div");selectOne.add("figure");selectOne.add("footer");selectOne.add("head");
			selectOne.add("header");selectOne.add("hgroup");selectOne.add("html");selectOne.add("map");selectOne.add("nav");selectOne.add("p");
			selectOne.add("q");selectOne.add("script");selectOne.add("section");selectOne.add("style");selectOne.add("title");
			//section 영역
			addPanel(tPanel, smallPanel1);
			addLabel(smallPanel1, section);
			addList(smallPanel1, selectOne);
		}
			
			
			private void addLabels_List2(JPanel tPanel) {
			//패널을 추가한다2:
			JPanel smallPanel2 = new JPanel();
			smallPanel2.setLayout(null);
			//라벨을 추가한다2:
			JLabel section2 = new JLabel("Multi");
			section2.setFont(f1);
			section2.setSize(300, 20);
			//리스트를 추가한다2:
			List selectTwo = new List(32);
			selectTwo.addActionListener(this);
			selectTwo.setLocation(0, 18);
			selectTwo.setSize(440, 120);
			selectTwo.add("!----");selectTwo.add("a");selectTwo.add("abbr");selectTwo.add("area");selectTwo.add("base");
			selectTwo.add("command");selectTwo.add("datalist");selectTwo.add("dd");selectTwo.add("dl");selectTwo.add("dt");
			selectTwo.add("embed");selectTwo.add("figcaption");selectTwo.add("hr");selectTwo.add("img");selectTwo.add("link");
			selectTwo.add("meta");selectTwo.add("meter");selectTwo.add("noscript");selectTwo.add("object");selectTwo.add("output");
			selectTwo.add("param");selectTwo.add("rp");selectTwo.add("rt");selectTwo.add("ruby");selectTwo.add("source");
			selectTwo.add("span");selectTwo.add("sub");selectTwo.add("summary");selectTwo.add("sup");selectTwo.add("time");
			selectTwo.add("video");selectTwo.add("wbr");
			//section 영역
			addPanel(tPanel, smallPanel2);
			addLabel(smallPanel2, section2);
			addList(smallPanel2, selectTwo);
		}
		
			private void addLabels_List3(JPanel tPanel) {
				//패널을 추가한다3:
				JPanel smallPanel3 = new JPanel();
				smallPanel3.setLayout(null);
				//라벨을 추가한다3:
				JLabel section3 = new JLabel("Letter");
				section3.setFont(f1);
				section3.setSize(300, 20);
				//리스트를 추가한다3:
				List selectThree = new List(25);
				selectThree.addActionListener(this);
				selectThree.setLocation(0, 18);
				selectThree.setSize(440, 120);
				selectThree.add("b");selectThree.add("bdo");selectThree.add("br");selectThree.add("cite");selectThree.add("code");
				selectThree.add("del");selectThree.add("dfn");selectThree.add("em");selectThree.add("h1");selectThree.add("h2");
				selectThree.add("h3");selectThree.add("h4");selectThree.add("h5");selectThree.add("h6");selectThree.add("i");
				selectThree.add("ins");selectThree.add("kbd");selectThree.add("mark");selectThree.add("pre");selectThree.add("progress");
				selectThree.add("s");selectThree.add("samp");selectThree.add("small");selectThree.add("strong");selectThree.add("var");
				//section 영역
				addPanel(tPanel, smallPanel3);
				addLabel(smallPanel3, section3);
				addList(smallPanel3, selectThree);
			}	
			
			private void addLabels_List4(JPanel tPanel) {
				//패널을 추가한다4:
				JPanel smallPanel4 = new JPanel();
				smallPanel4.setLayout(null);
				//라벨을 추가한다4:
				JLabel section4 = new JLabel("Table");
				section4.setFont(f1);
				section4.setSize(300, 20);
				//리스트를 추가한다4:
				List selectFour = new List(10);
				selectFour.setLocation(0, 18);
				selectFour.addActionListener(this);
				selectFour.setSize(440, 120);
				selectFour.add("caption");selectFour.add("col");selectFour.add("colgroup");selectFour.add("table");selectFour.add("tbody");
				selectFour.add("td");selectFour.add("tfoot");selectFour.add("th");selectFour.add("thead");selectFour.add("tr");
				//section 영역
				addPanel(tPanel, smallPanel4);
				addLabel(smallPanel4, section4);
				addList(smallPanel4, selectFour);
			}	
			
			private void addLabels_List5(JPanel tPanel) {
				//패널을 추가한다5:
				JPanel smallPanel5 = new JPanel();
				smallPanel5.setLayout(null);
				//라벨을 추가한다5:
				JLabel section5 = new JLabel("Form");
				section5.setFont(f1);
				section5.setSize(300, 20);
				//리스트를 추가한다5:
				List selectFive = new List(18);
				selectFive.addActionListener(this);
				selectFive.setLocation(0, 18);
				selectFive.setSize(440, 120);
				selectFive.add("audio");selectFive.add("button");selectFive.add("canvas");selectFive.add("fieldset");selectFive.add("form");
				selectFive.add("iframe");selectFive.add("input");selectFive.add("keygen");selectFive.add("label");selectFive.add("legend");
				selectFive.add("li");selectFive.add("menu");selectFive.add("ol");selectFive.add("optgroup");selectFive.add("option");
				selectFive.add("select");selectFive.add("textarea");selectFive.add("ul");
				//section 영역
				addPanel(tPanel, smallPanel5);
				addLabel(smallPanel5, section5);
				addList(smallPanel5, selectFive);
			}	
			
			/**
			 * 주어진 패널에 패널들을 추가한다.
			 * 
			 * @param tPanel 버튼들이 놓여질 객체 패널.
			 *            
			 */
			private void addPanels(JPanel tPanel) {
				
				//패널을 추가한다:
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3,1));
				tPanel.add(p);
				
				p.add(new JPanel());
				
				
				//버튼을 추가한다2:
				ETComponentListButton tSelect2 = new ETComponentListButton("Text Save");
				tSelect2.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						new ETSaveText(eteditorPart);
//						try {
//							ETModel.saveMakeHtml5(ETEditorPart.textPane.getText());
//						} catch (FileNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
					}
				});
				addButton(p, (JToggleButton) tSelect2);
			}
			
			
			/**
			 * 주어진 패널에 패널들을 추가한다.
			 * 
			 * @param tPanel 버튼들이 놓여질 객체 패널.
			 *            
			 */
			private void addPanels2(JPanel tPanel) {
				
				//패널을 추가한다:
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3,1));
				tPanel.add(p);

				
				//버튼을 추가한다3:
//				ETComponentListButton tSelect = new ETComponentListButton("Exit");
//				tSelect.addMouseListener(new MouseAdapter() {
//					public void mousePressed(MouseEvent e) {
//						try {
//							ETModel.endMakeHtml5(ETEditorPart.textPane.getText());
//						} catch (FileNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
					}
//				});
//				addButton(p, (JToggleButton) tSelect);
//			}
		 	
			
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
		 * 객체 패널에 주어진 라벨을 추가한다.
		 * 
		 * @param tPanel 라벨이 추가될 객체 패널.     
		 * @param tButton 객체 패널에 추가될 라벨 객체.
		 *           
		 */
		private void addLabel(JPanel tPanel, JLabel tLabbel) {
			tPanel.add(tLabbel);         // 패널에 라벨을 추가한다.
		}
		
		/**
		 * 객체 패널에 주어진 리스트를 추가한다.
		 * 
		 * @param tPanel 라벨이 추가될 객체 패널.     
		 * @param tButton 객체 패널에 추가될 라벨 객체.
		 *           
		 */
		private void addList(JPanel tPanel, List tList) {
			tPanel.add(tList);         // 패널에 리스트를 추가한다.
		}
		
		/**
		 * 객체 패널에 주어진 패널을 추가한다.
		 * 
		 * @param tPanel 라벨이 추가될 객체 패널.     
		 * @param tButton 객체 패널에 추가될 라벨 객체.
		 *           
		 */
		private void addPanel(JPanel tPanel, JPanel ttPanel) {
			tPanel.add(ttPanel);         // 패널에 라벨을 추가한다.
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
			
		}
		
		/**
		 * 주어진 리스트에 ActionListener 를 박는다
		 * 
		 * @param tPanel 버튼들이 놓여질 객체 패널.
		 *            
		 */
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand() == "!----"){		
				//이러한 방식
				this.tagList.add(ETTagLists.COMENT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.COMENT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			}
			else if(e.getActionCommand() == "a"){		
				//이러한 방식
				this.tagList.add(ETTagLists.A);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.A);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());			 
			}
			else if(e.getActionCommand() == "abbr"){		
				//이러한 방식
				this.tagList.add(ETTagLists.ABBR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ABBR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "address"){		
				//이러한 방식
				this.tagList.add(ETTagLists.ADDRESS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ADDRESS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "area"){		
				//이러한 방식
				this.tagList.add(ETTagLists.AREA);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.AREA);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }		
			
			else if(e.getActionCommand() == "article"){		
				//이러한 방식
				this.tagList.add(ETTagLists.ARTICLE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ARTICLE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "aside"){		
				//이러한 방식
				this.tagList.add(ETTagLists.ASIDE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ASIDE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "audio"){		
				//이러한 방식
				this.tagList.add(ETTagLists.AUDIO);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.AUDIO);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "b"){		
				//이러한 방식
				this.tagList.add(ETTagLists.B);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.B);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "base"){		
				//이러한 방식
				this.tagList.add(ETTagLists.BASE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BASE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "bdo"){		
				//이러한 방식
				this.tagList.add(ETTagLists.BDO);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BDO);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "blockquote"){		
				//이러한 방식
				this.tagList.add(ETTagLists.BLOCKQUOTE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BLOCKQUOTE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "body"){		
				//이러한 방식
				this.tagList.add(ETTagLists.BODY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BODY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "br"){		
				//이러한 방식
				this.tagList.add(ETTagLists.BR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "button"){		
				//이러한 방식
				this.tagList.add(ETTagLists.BUTTON);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BUTTON);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
//				
			 }
			else if(e.getActionCommand() == "canvas"){		
				//이러한 방식
				this.tagList.add(ETTagLists.CANVAS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CANVAS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "caption"){		
				//이러한 방식
				this.tagList.add(ETTagLists.CAPTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CAPTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "cite"){		
				//이러한 방식
				this.tagList.add(ETTagLists.CITE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CITE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "code"){		
				//이러한 방식
				this.tagList.add(ETTagLists.CODE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CODE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "col"){		
				//이러한 방식
				this.tagList.add(ETTagLists.COL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.COL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "colgroup"){		
				//이러한 방식
				this.tagList.add(ETTagLists.CLOGROUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CLOGROUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "command"){		
				//이러한 방식
				this.tagList.add(ETTagLists.COMMAND);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.COMMAND);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "datalist"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DATALIST);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DATALIST);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dd"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "del"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DEL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DEL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "details"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DETAILS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DETAILS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dfn"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DFN);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DFN);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "div"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DIV);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DIV);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dl"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dt"){		
				//이러한 방식
				this.tagList.add(ETTagLists.DT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "em"){		
				//이러한 방식
				this.tagList.add(ETTagLists.EM);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.EM);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "embed"){		
				//이러한 방식
				this.tagList.add(ETTagLists.EMBED);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.EMBED);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "fieldset"){		
				//이러한 방식
				this.tagList.add(ETTagLists.FIELDSET);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FIELDSET);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "figcaption"){		
				//이러한 방식
				this.tagList.add(ETTagLists.FIGCAPTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FIGCAPTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "figure"){		
				//이러한 방식
				this.tagList.add(ETTagLists.FIGURE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FIGURE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "footer"){		
				//이러한 방식
				this.tagList.add(ETTagLists.FOOTER);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FOOTER);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "form")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.FORM);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FORM);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h1")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.H1);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H1);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h2")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.H2);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H2);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h3")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.H3);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H3);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h4")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.H4);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H4);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h5")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.H5);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H5);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h6")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.H6);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H6);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "head")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.HEAD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HEAD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "header")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.HEADER);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HEADER);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "hgroup")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.HGROUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HGROUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "hr")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.HR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "html")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.HTML);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HTML);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "i")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.I);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.I);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "iframe")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.IFRAME);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.IFRAME);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "img")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.IMG);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.IMG);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "input")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.INPUT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.INPUT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ins")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.INS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.INS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "keygen")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.KEYGEN);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.KEYGEN);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "kbd")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.KBD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.KBD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "label")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.LABEL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LABEL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "legend")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.LEGEND);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LEGEND);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "li")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.LI);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LI);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "link")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.LINK);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LINK);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "map")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.MAP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.MAP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "mark")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.MARK);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.MARK);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "menu")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.MENU);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.MENU);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "meta")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.META);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.META);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "meter")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.METER);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.METER);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "nav")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.NAV);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.NAV);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "noscript")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.NOSCRIPT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.NOSCRIPT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "object")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.OBJECT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OBJECT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ol")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.OL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "optgroup")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.OPTGROUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OPTGROUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "option")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.OPTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OPTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "output")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.OUTPUT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OUTPUT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "p")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.P);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.P);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "param")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.PARAM);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.PARAM);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "pre")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.PRE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.PRE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "progress")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.PROGRESS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.PROGRESS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "q")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.Q);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.Q);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "rp")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.RP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.RP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "rt")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.RT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.RT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ruby")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.RUBY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.RUBY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "s")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.S);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.S);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "samp")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SAMP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SAMP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "script")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SCRIPT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SCRIPT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "section")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SECTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SECTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "select")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SELECT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SELECT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "small")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SMALL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SMALL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "source")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SOURCE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SOURCE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "span")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SPAN);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SPAN);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "strong")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.STRONG);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.STRONG);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "style")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.STYLE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.STYLE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "sub")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SUB);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SUB);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "summary")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SUMMARY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SUMMARY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "sup")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.SUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "table")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TABLE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TABLE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "tbody")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TBODY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TBODY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "td")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "textarea")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TEXTAREA);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TEXTAREA);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "tfoot")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TFOOT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TFOOT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "th")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TH);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TH);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "thead")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.THREAD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.THREAD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "time")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TIME);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TIME);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "title")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TITL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TITL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "tr")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.TR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ul")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.UL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.UL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "var")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.VAR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.VAR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "video")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.VIDEO);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.VIDEO);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "wbr")
			{		
				//이러한 방식
				this.tagList.add(ETTagLists.WBR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.WBR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}			
		}
		
}
