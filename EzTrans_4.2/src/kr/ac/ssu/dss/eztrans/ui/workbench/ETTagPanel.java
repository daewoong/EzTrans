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
 * HTML5 �±� ������Ʈ�� �߰��� �ǳ�
 * @author Complete
 * 
 */
@SuppressWarnings("serial")
public class ETTagPanel extends JPanel implements ActionListener{
	

		/** ��Ʈ*/
		Font f1 = new Font("Serif", Font.BOLD, 15);
		/** �����ϴ� ��ư ��ü�鿡 ���� ����Ʈ */
		private Vector<JToggleButton> tListOfButtons = null;
		
		private ButtonGroup buttonGroup = null;
		
		/** �±� ����Ʈ */
		private Vector<String> tagList = null;
		
		/** ��ư�� ���� ���ڿ� ����: TAG */
		public static final String sTSelect = "Select";

		private ETEditorPart eteditorPart = null;
		
		//������
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
		 * �־��� �гο� ��ư���� �߰��Ѵ�.
		 * 
		 * @param tPanel ��ư���� ������ ��ü �г�.
		 *            
		 */
		private void addButtons(JPanel tPanel) {
			
			//��ư�� �߰��Ѵ�1:
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
		 * �־��� �гο� �󺧵��� �߰��Ѵ�.
		 * 
		 * @param tPanel �󺧵��� ������ ��ü �г�.
		 *            
		 */
		private void addLabels_List(JPanel tPanel) {
			
			//�г��� �߰��Ѵ�1:
			JPanel smallPanel1 = new JPanel();
			smallPanel1.setLayout(null);
			//���� �߰��Ѵ�1:
			JLabel section = new JLabel("Section");
			section.setFont(f1);
			section.setSize(300, 20);
			//����Ʈ�� �߰��Ѵ�1:
			List selectOne = new List(21);
			selectOne.addActionListener(this);
			selectOne.setLocation(0, 18);
			selectOne.setSize(440, 120);
			selectOne.add("address");selectOne.add("article");selectOne.add("aside");selectOne.add("blockquote");selectOne.add("body");
			selectOne.add("details");selectOne.add("div");selectOne.add("figure");selectOne.add("footer");selectOne.add("head");
			selectOne.add("header");selectOne.add("hgroup");selectOne.add("html");selectOne.add("map");selectOne.add("nav");selectOne.add("p");
			selectOne.add("q");selectOne.add("script");selectOne.add("section");selectOne.add("style");selectOne.add("title");
			//section ����
			addPanel(tPanel, smallPanel1);
			addLabel(smallPanel1, section);
			addList(smallPanel1, selectOne);
		}
			
			
			private void addLabels_List2(JPanel tPanel) {
			//�г��� �߰��Ѵ�2:
			JPanel smallPanel2 = new JPanel();
			smallPanel2.setLayout(null);
			//���� �߰��Ѵ�2:
			JLabel section2 = new JLabel("Multi");
			section2.setFont(f1);
			section2.setSize(300, 20);
			//����Ʈ�� �߰��Ѵ�2:
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
			//section ����
			addPanel(tPanel, smallPanel2);
			addLabel(smallPanel2, section2);
			addList(smallPanel2, selectTwo);
		}
		
			private void addLabels_List3(JPanel tPanel) {
				//�г��� �߰��Ѵ�3:
				JPanel smallPanel3 = new JPanel();
				smallPanel3.setLayout(null);
				//���� �߰��Ѵ�3:
				JLabel section3 = new JLabel("Letter");
				section3.setFont(f1);
				section3.setSize(300, 20);
				//����Ʈ�� �߰��Ѵ�3:
				List selectThree = new List(25);
				selectThree.addActionListener(this);
				selectThree.setLocation(0, 18);
				selectThree.setSize(440, 120);
				selectThree.add("b");selectThree.add("bdo");selectThree.add("br");selectThree.add("cite");selectThree.add("code");
				selectThree.add("del");selectThree.add("dfn");selectThree.add("em");selectThree.add("h1");selectThree.add("h2");
				selectThree.add("h3");selectThree.add("h4");selectThree.add("h5");selectThree.add("h6");selectThree.add("i");
				selectThree.add("ins");selectThree.add("kbd");selectThree.add("mark");selectThree.add("pre");selectThree.add("progress");
				selectThree.add("s");selectThree.add("samp");selectThree.add("small");selectThree.add("strong");selectThree.add("var");
				//section ����
				addPanel(tPanel, smallPanel3);
				addLabel(smallPanel3, section3);
				addList(smallPanel3, selectThree);
			}	
			
			private void addLabels_List4(JPanel tPanel) {
				//�г��� �߰��Ѵ�4:
				JPanel smallPanel4 = new JPanel();
				smallPanel4.setLayout(null);
				//���� �߰��Ѵ�4:
				JLabel section4 = new JLabel("Table");
				section4.setFont(f1);
				section4.setSize(300, 20);
				//����Ʈ�� �߰��Ѵ�4:
				List selectFour = new List(10);
				selectFour.setLocation(0, 18);
				selectFour.addActionListener(this);
				selectFour.setSize(440, 120);
				selectFour.add("caption");selectFour.add("col");selectFour.add("colgroup");selectFour.add("table");selectFour.add("tbody");
				selectFour.add("td");selectFour.add("tfoot");selectFour.add("th");selectFour.add("thead");selectFour.add("tr");
				//section ����
				addPanel(tPanel, smallPanel4);
				addLabel(smallPanel4, section4);
				addList(smallPanel4, selectFour);
			}	
			
			private void addLabels_List5(JPanel tPanel) {
				//�г��� �߰��Ѵ�5:
				JPanel smallPanel5 = new JPanel();
				smallPanel5.setLayout(null);
				//���� �߰��Ѵ�5:
				JLabel section5 = new JLabel("Form");
				section5.setFont(f1);
				section5.setSize(300, 20);
				//����Ʈ�� �߰��Ѵ�5:
				List selectFive = new List(18);
				selectFive.addActionListener(this);
				selectFive.setLocation(0, 18);
				selectFive.setSize(440, 120);
				selectFive.add("audio");selectFive.add("button");selectFive.add("canvas");selectFive.add("fieldset");selectFive.add("form");
				selectFive.add("iframe");selectFive.add("input");selectFive.add("keygen");selectFive.add("label");selectFive.add("legend");
				selectFive.add("li");selectFive.add("menu");selectFive.add("ol");selectFive.add("optgroup");selectFive.add("option");
				selectFive.add("select");selectFive.add("textarea");selectFive.add("ul");
				//section ����
				addPanel(tPanel, smallPanel5);
				addLabel(smallPanel5, section5);
				addList(smallPanel5, selectFive);
			}	
			
			/**
			 * �־��� �гο� �гε��� �߰��Ѵ�.
			 * 
			 * @param tPanel ��ư���� ������ ��ü �г�.
			 *            
			 */
			private void addPanels(JPanel tPanel) {
				
				//�г��� �߰��Ѵ�:
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3,1));
				tPanel.add(p);
				
				p.add(new JPanel());
				
				
				//��ư�� �߰��Ѵ�2:
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
			 * �־��� �гο� �гε��� �߰��Ѵ�.
			 * 
			 * @param tPanel ��ư���� ������ ��ü �г�.
			 *            
			 */
			private void addPanels2(JPanel tPanel) {
				
				//�г��� �߰��Ѵ�:
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3,1));
				tPanel.add(p);

				
				//��ư�� �߰��Ѵ�3:
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
		 * ��ü �гο� �־��� ���� �߰��Ѵ�.
		 * 
		 * @param tPanel ���� �߰��� ��ü �г�.     
		 * @param tButton ��ü �гο� �߰��� �� ��ü.
		 *           
		 */
		private void addLabel(JPanel tPanel, JLabel tLabbel) {
			tPanel.add(tLabbel);         // �гο� ���� �߰��Ѵ�.
		}
		
		/**
		 * ��ü �гο� �־��� ����Ʈ�� �߰��Ѵ�.
		 * 
		 * @param tPanel ���� �߰��� ��ü �г�.     
		 * @param tButton ��ü �гο� �߰��� �� ��ü.
		 *           
		 */
		private void addList(JPanel tPanel, List tList) {
			tPanel.add(tList);         // �гο� ����Ʈ�� �߰��Ѵ�.
		}
		
		/**
		 * ��ü �гο� �־��� �г��� �߰��Ѵ�.
		 * 
		 * @param tPanel ���� �߰��� ��ü �г�.     
		 * @param tButton ��ü �гο� �߰��� �� ��ü.
		 *           
		 */
		private void addPanel(JPanel tPanel, JPanel ttPanel) {
			tPanel.add(ttPanel);         // �гο� ���� �߰��Ѵ�.
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
			
		}
		
		/**
		 * �־��� ����Ʈ�� ActionListener �� �ڴ´�
		 * 
		 * @param tPanel ��ư���� ������ ��ü �г�.
		 *            
		 */
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand() == "!----"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.COMENT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.COMENT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			}
			else if(e.getActionCommand() == "a"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.A);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.A);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());			 
			}
			else if(e.getActionCommand() == "abbr"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.ABBR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ABBR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "address"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.ADDRESS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ADDRESS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "area"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.AREA);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.AREA);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }		
			
			else if(e.getActionCommand() == "article"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.ARTICLE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ARTICLE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "aside"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.ASIDE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.ASIDE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "audio"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.AUDIO);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.AUDIO);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "b"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.B);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.B);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "base"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.BASE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BASE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "bdo"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.BDO);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BDO);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "blockquote"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.BLOCKQUOTE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BLOCKQUOTE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "body"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.BODY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BODY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "br"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.BR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());

			 }
			else if(e.getActionCommand() == "button"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.BUTTON);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.BUTTON);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
//				
			 }
			else if(e.getActionCommand() == "canvas"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.CANVAS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CANVAS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "caption"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.CAPTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CAPTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "cite"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.CITE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CITE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "code"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.CODE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CODE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "col"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.COL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.COL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "colgroup"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.CLOGROUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.CLOGROUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "command"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.COMMAND);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.COMMAND);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "datalist"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DATALIST);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DATALIST);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dd"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "del"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DEL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DEL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "details"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DETAILS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DETAILS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dfn"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DFN);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DFN);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "div"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DIV);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DIV);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dl"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "dt"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.DT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.DT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "em"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.EM);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.EM);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "embed"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.EMBED);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.EMBED);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "fieldset"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.FIELDSET);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FIELDSET);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "figcaption"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.FIGCAPTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FIGCAPTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "figure"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.FIGURE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FIGURE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());
			 }
			else if(e.getActionCommand() == "footer"){		
				//�̷��� ���
				this.tagList.add(ETTagLists.FOOTER);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FOOTER);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "form")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.FORM);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.FORM);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h1")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.H1);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H1);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h2")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.H2);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H2);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h3")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.H3);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H3);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h4")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.H4);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H4);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h5")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.H5);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H5);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "h6")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.H6);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.H6);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "head")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.HEAD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HEAD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "header")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.HEADER);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HEADER);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "hgroup")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.HGROUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HGROUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "hr")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.HR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "html")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.HTML);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.HTML);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "i")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.I);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.I);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "iframe")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.IFRAME);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.IFRAME);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "img")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.IMG);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.IMG);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "input")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.INPUT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.INPUT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ins")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.INS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.INS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "keygen")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.KEYGEN);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.KEYGEN);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "kbd")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.KBD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.KBD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "label")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.LABEL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LABEL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "legend")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.LEGEND);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LEGEND);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "li")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.LI);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LI);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "link")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.LINK);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.LINK);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "map")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.MAP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.MAP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "mark")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.MARK);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.MARK);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "menu")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.MENU);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.MENU);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "meta")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.META);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.META);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "meter")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.METER);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.METER);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "nav")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.NAV);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.NAV);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "noscript")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.NOSCRIPT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.NOSCRIPT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "object")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.OBJECT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OBJECT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ol")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.OL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "optgroup")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.OPTGROUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OPTGROUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "option")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.OPTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OPTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "output")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.OUTPUT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.OUTPUT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "p")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.P);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.P);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "param")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.PARAM);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.PARAM);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "pre")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.PRE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.PRE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "progress")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.PROGRESS);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.PROGRESS);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "q")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.Q);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.Q);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "rp")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.RP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.RP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "rt")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.RT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.RT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ruby")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.RUBY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.RUBY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "s")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.S);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.S);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "samp")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SAMP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SAMP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "script")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SCRIPT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SCRIPT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "section")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SECTION);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SECTION);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "select")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SELECT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SELECT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "small")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SMALL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SMALL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "source")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SOURCE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SOURCE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "span")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SPAN);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SPAN);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "strong")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.STRONG);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.STRONG);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "style")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.STYLE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.STYLE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "sub")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SUB);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SUB);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "summary")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SUMMARY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SUMMARY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "sup")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.SUP);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.SUP);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "table")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TABLE);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TABLE);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "tbody")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TBODY);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TBODY);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "td")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "textarea")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TEXTAREA);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TEXTAREA);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "tfoot")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TFOOT);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TFOOT);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "th")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TH);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TH);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "thead")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.THREAD);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.THREAD);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "time")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TIME);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TIME);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "title")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TITL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TITL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "tr")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.TR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.TR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "ul")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.UL);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.UL);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "var")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.VAR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.VAR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "video")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.VIDEO);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.VIDEO);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}
			else if(e.getActionCommand() == "wbr")
			{		
				//�̷��� ���
				this.tagList.add(ETTagLists.WBR);
				StringBuffer sb = this.eteditorPart.getStringBuffer().append(ETTagLists.WBR);
				this.eteditorPart.getTagEditingArea().setText(sb.toString());}			
		}
		
}
