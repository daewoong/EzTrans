package kr.ac.ssu.dss.eztrans.ui.workbench.editor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


import kr.ac.ssu.dss.eztrans.contants.ETIconPaths;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETSizeChange;

@SuppressWarnings("serial")
public class ETEditorPage extends JPanel{

	/*
	 * flag가 0이면 아무것도 선택되지 않았음을 의미한다
	 * flag가 1이면 Text가 선택
	 * flag가 2이면 Image가 선택
	 * flag가 3이면 Video가 선택
	 * flag가 4이면 Audio가 선택
	 * flag가 5이면 HyperLink가 선택
	 * flag가 6이면 데이터 질의가 선택
	 * flag가 7이면 OLAP이 선택
	 * flag가 8이면 Drag_Drops가 선택
	 * flag가 9이면 Drops가 선택
	 * flag가 10이면 DataMining이 선택
	 * flag가 11이면 Hover가 선택
	 */
	public static int flag;
	public static boolean isChanged;

	//다이얼로그에 적힌 스트링을 받아오기위한 static 변수
	public static String naviStyle;
	public static String src = "";
	public static String src1 = "";// 텍스트
	public static String src2 = "";// 텍스트
	public static String src3 = "";// 텍스트
	public static Vector<String> Navi_src;
	public static Vector<String> Navi_link;
	public static String Vpro = "";
	public static String Apro = "";
	public static int btnStyle;
	public static Hashtable<String, Vector<String>> srcMining = null;

	//폰트의 속성을 지정하기 위한 변수
	public static int fontSize = 12;
	public static int fontStyle = Font.PLAIN;
	public static String fontName = "Serif";
	public static String fontAlign = "";

	//지정된 영역을 기록하기 위한 멤버 변수
	public Vector<Point> Text_start;
	public Vector<Point> Text_end;
	public Vector<Point> Image_start;
	public Vector<Point> Image_end;
	public Vector<Point> Video_start;
	public Vector<Point> Video_end;
	public Vector<Point> Audio_start;
	public Vector<Point> Audio_end;
	public Vector<Point> HyperLink_start;
	public Vector<Point> HyperLink_end;
	public Vector<Point> DB_start;
	public Vector<Point> DB_end;
	public Vector<Point> Olap_start;
	public Vector<Point> Olap_end;
	//////////////////////////////////////// 추가
	public Vector<Point> Drag_Drops_start;
	public Vector<Point> Drag_Drops_end;
	public Vector<Point> FromButton_start;
	public Vector<Point> Drops_end;
	public Vector<Point> Mining_start;
	public Vector<Point> Mining_end;
	public Vector<Point> Toggle_start;
	public Vector<Point> Toggle_end;
	//////////////////////////////////////// 추가
	public Vector<Point> Navi_start;
	public Vector<Point> Navi_end;
	
	//지정된 영역의 링크주소를 가지고 있을 멤버 변수
	public Vector<String> Text_src;
	public Vector<Font> Text_font;
	public Vector<String> Text_align;
	public Vector<String> Image_src;
	/////////////////////////////////
	public Vector<String> Image_link;
	public Vector<String> Video_src;
	public Vector<String> Video_link;
	public Vector<String> Audio_src;
	public Vector<String> HyperLink_src;
	public Vector<String> DB_src;
	public Vector<Font> DB_font;
	public Vector<String> DB_align;
	public Vector<String> Olap_src;
	public Vector<Font> Olap_font;
	public Vector<String> Olap_align;
	public Vector<String> HyperLink_src1;// 텍스트
	/////////////////////////////////////// 추가
	public Vector<String> Drag_Drops_src;
	public Vector<String> Drops_src;
	public Vector<String> Drops_link;
	public Vector<Integer> Drops_style;
	public Vector<Hashtable<String, Vector<String>>> Mining_src;
	public Vector<String> Toggle_title;
	public Vector<String> Toggle_sub;
	public Vector<String> Toggle_swap;
	//////////////////////////////////////// 추가
	public Vector<Vector<String>> navi_src;
	public Vector<Vector<String>> navi_link;
	public Vector<String> Navi_style;

	//복사, 붙여넣기 할 때 필요한 임시 객체
	public Point tmpStart = new Point();
	public Point tmpEnd = new Point();
	public String tmpString = "";
	public String tmpString1 = "";
	public String tmpString2 = "";
	public String tmpString3 = "";
	public Vector<String> tmpVector;
	public Vector<String> tmpVector1;
	public int tempInt1;
	public Font tmpFont = new Font(fontName, fontStyle, fontSize);
	public int tmpInt = 0;
	public Hashtable<String, Vector<String>> tmpHash;

	public Vector<String> Vproperty;
	public Vector<String> Aproperty;


	//드래그 되는 동안에 화면에 영역을 나타내줄 포인터
	private Point startP = null;
	private Point endP = null;

	//지정해 놓은 영역이 선택되었음을 알리는 플래그
	public static boolean isSelected[] = new boolean[12];

	//벡터의 몇번째 인덱스가 선택되었는지 알 수 있는 플래그
	private int getIndex;

	//드래그가 가능함을 알려주는 플래그
	private boolean isDragged;

	//복사를 해둔 객체가 있는지에 관한 플래그
	private boolean isCopied;

	/*
	 * 영역의 크기가 변경중이라는 것을 알리는 플래그
	 * changeSize[0] == true : 위쪽 영역 크기 조절
	 * changeSize[1] == true : 아래쪽 영역 크기 조절
	 * changeSize[2] == true : 왼쪽 영역 크기 조절
	 * changeSize[3] == true : 오른쪽 영역 크기 조절
	 */
	private boolean changeSize[] = new boolean[4];


	//에디터 판넬의 사이지를 지정한다
	private int panelSizeX = 800;
	private int panelSizeY = 1024;

	public int choice;


	//텍스트 영역의 내용을 출력할 영역
	//	private Vector<JScrollPane> textArea = new Vector<JScrollPane>();

	public ETEditorPage(){

		this.setPreferredSize(new Dimension(panelSizeX,panelSizeY));
		this.setMinimumSize(new Dimension(panelSizeX,panelSizeY));
		this.setBackground(Color.white);
		this.setBorder(new EmptyBorder(2,2,9,9));
		this.setLayout(null);

		this.setFocusable(true);
		this.addMouseListener(new EditMouseListener());
		this.addMouseMotionListener(new EditMouseListener());
		this.addKeyListener(new EditKeyboardListener());

		Text_start = new Vector<Point>();
		Text_end = new Vector<Point>();
		Image_start = new Vector<Point>();
		Image_end = new Vector<Point>();
		Video_start = new Vector<Point>();
		Video_end = new Vector<Point>();
		Audio_start = new Vector<Point>();
		Audio_end = new Vector<Point>();
		HyperLink_start = new Vector<Point>();
		HyperLink_end = new Vector<Point>();
		DB_start = new Vector<Point>();
		DB_end = new Vector<Point>();
		Olap_start = new Vector<Point>();
		Olap_end = new Vector<Point>();
		Drag_Drops_start = new Vector<Point>();
		Drag_Drops_end = new Vector<Point>();
		FromButton_start = new Vector<Point>();
		Drops_end = new Vector<Point>();
		Mining_start = new Vector<Point>();
		Mining_end = new Vector<Point>();
		Toggle_start = new Vector<Point>();
		Toggle_end = new Vector<Point>();
		Navi_start = new Vector<Point>();
		Navi_end = new Vector<Point>();

		Text_src = new Vector<String>();
		Text_font = new Vector<Font>();
		Text_align = new Vector<String>();
		Image_src = new Vector<String>();
		Image_link = new Vector<String>();
		Video_src = new Vector<String>();
		Video_link = new Vector<String>();
		Audio_src = new Vector<String>();
		HyperLink_src = new Vector<String>();
		DB_src = new Vector<String>();
		DB_font = new Vector<Font>();
		DB_align = new Vector<String>();
		Olap_src = new Vector<String>();
		Olap_font = new Vector<Font>();
		Olap_align = new Vector<String>();
		HyperLink_src1 = new Vector<String>(); // 텍스트
		Drag_Drops_src = new Vector<String>();
		Drops_src = new Vector<String>();
		Drops_link = new Vector<String>();
		Drops_style = new Vector<Integer>();
		Mining_src = new Vector<Hashtable<String,Vector<String>>>();
		Toggle_title = new Vector<String>();
		Toggle_sub = new Vector<String>();
		Toggle_swap = new Vector<String>();

		navi_src = new Vector<Vector<String>>();
		navi_link = new Vector<Vector<String>>();
		Navi_style = new Vector<String>();
		
		Vproperty = new Vector<String>();
		Aproperty = new Vector<String>();
		
		isCopied = false;
	}

	/**
	 * 
	 * @param choice : choice가 1이면 600 * 800, 2이면 800 * 1024, 3이면 1024 * 1048
	 */
	public ETEditorPage(int choice, int sizeofX, int sizeofY){


		//기존 판넬에 있던 모든 요소들을 초기화 한다
		this.removeAll();
		Text_start = new Vector<Point>();
		Text_end = new Vector<Point>();
		Image_start = new Vector<Point>();
		Image_end = new Vector<Point>();
		Video_start = new Vector<Point>();
		Video_end = new Vector<Point>();
		Audio_start = new Vector<Point>();
		Audio_end = new Vector<Point>();
		HyperLink_start = new Vector<Point>();
		HyperLink_end = new Vector<Point>();
		DB_start = new Vector<Point>();
		DB_end = new Vector<Point>();
		Olap_start = new Vector<Point>();
		Olap_end = new Vector<Point>();
		Drag_Drops_start = new Vector<Point>();
		Drag_Drops_end = new Vector<Point>();
		FromButton_start = new Vector<Point>();
		Drops_end = new Vector<Point>();
		Mining_start = new Vector<Point>();
		Mining_end = new Vector<Point>();
		Toggle_start = new Vector<Point>();
		Toggle_end = new Vector<Point>();
		Navi_start = new Vector<Point>();
		Navi_end = new Vector<Point>();

		Text_src = new Vector<String>();
		Text_font = new Vector<Font>();
		Text_align = new Vector<String>();
		Image_src = new Vector<String>();
		Image_link = new Vector<String>();
		Video_src = new Vector<String>();
		Video_link = new Vector<String>();
		Audio_src = new Vector<String>();
		HyperLink_src = new Vector<String>();
		DB_src = new Vector<String>();
		DB_font = new Vector<Font>();
		DB_align = new Vector<String>();
		Olap_src = new Vector<String>();
		Olap_font = new Vector<Font>();
		Olap_align = new Vector<String>();
		HyperLink_src1 = new Vector<String>(); // 텍스트
		Drag_Drops_src = new Vector<String>();
		Drops_src = new Vector<String>();
		Drops_link = new Vector<String>();
		Drops_style = new Vector<Integer>();
		Mining_src = new Vector<Hashtable<String,Vector<String>>>();
		Toggle_title = new Vector<String>();
		Toggle_sub = new Vector<String>();
		Toggle_swap = new Vector<String>();

		navi_src = new Vector<Vector<String>>();
		navi_link = new Vector<Vector<String>>();
		Navi_style = new Vector<String>();
		
		Vproperty = new Vector<String>();
		Aproperty = new Vector<String>();

		isCopied = false;

		for(int i = 0; i < isSelected.length; i++){
			isSelected[i] = false;
		}

		switch(choice)
		{
		case 1:
			panelSizeX = 600;
			panelSizeY = 800;
			this.setPreferredSize(new Dimension(600,800));
			this.setMinimumSize(new Dimension(800,1024));
			this.setBackground(Color.white);
			this.setBorder(new EmptyBorder(2,2,9,9));
			this.setLayout(null);
			break;

		case 2:
			panelSizeX = 800;
			panelSizeY = 1024;
			this.setPreferredSize(new Dimension(800,1024));
			this.setMinimumSize(new Dimension(1024,1048));
			this.setBackground(Color.white);
			this.setBorder(new EmptyBorder(2,2,9,9));
			this.setLayout(null);
			break;

		case 3:
			panelSizeX = 1024;
			panelSizeY = 1036;
			this.setPreferredSize(new Dimension(1024,1036));
			this.setMinimumSize(new Dimension(1030,1048));
			this.setBackground(Color.white);
			this.setBorder(new EmptyBorder(2,2,9,9));
			this.setLayout(null);
			break;

		case 4:
			panelSizeX = 1048;
			panelSizeY = 1060;
			this.setPreferredSize(new Dimension(1048,1060));
			this.setMinimumSize(new Dimension(1050,1096));
			this.setBackground(Color.white);
			this.setBorder(new EmptyBorder(2,2,9,9));
			this.setLayout(null);
			break;
		case 5:
			panelSizeX = 1060;
			panelSizeY = 1096;
			this.setPreferredSize(new Dimension(1060,1096));
			this.setMinimumSize(new Dimension(1096,1124));
			this.setBackground(Color.white);
			this.setBorder(new EmptyBorder(2,2,9,9));
			this.setLayout(null);
			break;

		case 6:
			panelSizeX = sizeofX;
			panelSizeY = sizeofY;
			this.setPreferredSize(new Dimension(panelSizeX,panelSizeY));
			this.setMinimumSize(new Dimension(panelSizeX+6,panelSizeY+12));
			this.setBackground(Color.white);
			this.setBorder(new EmptyBorder(2,2,9,9));
			this.setLayout(null);
			break;
		}

		//판넬에 다시 리스너를 추가한다
		this.setFocusable(true);
		this.addMouseListener(new EditMouseListener());
		this.addMouseMotionListener(new EditMouseListener());
		this.addKeyListener(new EditKeyboardListener());
	}


	/**
	 * 키보드의 입력을 담당하는 이벤트 핸들러
	 * @author chae
	 *
	 */
	private class EditKeyboardListener extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			//영역이 선택된 상태에서 Delete 키가 눌리면 해당 영역 삭제
			if(e.getKeyCode() == KeyEvent.VK_DELETE){
				if(isSelected[0])
				{
					Text_start.remove(getIndex);
					Text_end.remove(getIndex);
					Text_src.remove(getIndex);
					Text_font.remove(getIndex);
					Text_align.remove(getIndex);
					repaint();
				}
				else if(isSelected[1])
				{
					Image_start.remove(getIndex);
					Image_end.remove(getIndex);
					Image_src.remove(getIndex);
					Image_link.remove(getIndex);
					repaint();
				}
				else if(isSelected[2])
				{
					Video_start.remove(getIndex);
					Video_end.remove(getIndex);
					Video_src.remove(getIndex);
					//	Video_link.remove(getIndex);
					Vproperty.remove(getIndex);
					repaint();
				}
				else if(isSelected[3])
				{
					Audio_start.remove(getIndex);
					Audio_end.remove(getIndex);
					Audio_src.remove(getIndex);
					repaint();
				}
				else if(isSelected[4])
				{
					HyperLink_start.remove(getIndex);
					HyperLink_end.remove(getIndex);
					HyperLink_src.remove(getIndex);
					Aproperty.remove(getIndex);
					repaint();
				}
				else if(isSelected[5])
				{
					DB_start.remove(getIndex);
					DB_end.remove(getIndex);
					DB_src.remove(getIndex);
					DB_font.remove(getIndex);
					DB_align.remove(getIndex);
					repaint();
				}
				else if(isSelected[6])
				{
					Olap_start.remove(getIndex);
					Olap_end.remove(getIndex);
					Olap_src.remove(getIndex);
					Olap_font.remove(getIndex);
					Olap_align.remove(getIndex);
					repaint();
				}
				else if(isSelected[7])
				{
					Drag_Drops_start.remove(getIndex);
					Drag_Drops_end.remove(getIndex);
					Drag_Drops_src.remove(getIndex);
					repaint();
				}
				else if(isSelected[8])
				{
					FromButton_start.remove(getIndex);
					Drops_end.remove(getIndex);
					Drops_src.remove(getIndex);
					Drops_link.remove(getIndex);
					Drops_style.remove(getIndex);
					repaint();
				}
				else if(isSelected[9])
				{
					Mining_start.remove(getIndex);
					Mining_end.remove(getIndex);
					Mining_src.remove(getIndex);
					repaint();
				}
				else if(isSelected[10])
				{
					Toggle_start.remove(getIndex);
					Toggle_end.remove(getIndex);
					Toggle_title.remove(getIndex);
					Toggle_sub.remove(getIndex);
					Toggle_swap.remove(getIndex);
					repaint();
				}
				else if(isSelected[11])
				{
					Navi_start.remove(getIndex);
					Navi_end.remove(getIndex);
					navi_src.remove(getIndex);
					navi_link.remove(getIndex);
					Navi_style.remove(getIndex);
					repaint();
				}

				for(int i = 0; i < isSelected.length; i++)
					isSelected[i] = false;
			}
			else if(e.isControlDown()){
				if(e.getKeyCode() == KeyEvent.VK_C){
					System.out.println("Copy");
					if(isSelected[0]){
						tmpStart.x = Text_start.get(getIndex).x + 20;
						tmpStart.y = Text_start.get(getIndex).y + 20;
						tmpEnd.x = Text_end.get(getIndex).x + 20;
						tmpEnd.y = Text_end.get(getIndex).y + 20;
						tmpString = Text_src.get(getIndex);
						tmpFont = Text_font.get(getIndex);
						tmpString1 = Text_align.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[1]){
						tmpStart.x = Image_start.get(getIndex).x + 20;
						tmpStart.y = Image_start.get(getIndex).y + 20;
						tmpEnd.x = Image_end.get(getIndex).x + 20;
						tmpEnd.y = Image_end.get(getIndex).y + 20;
						tmpString = Image_src.get(getIndex);
						tmpString1 = Image_link.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[2]){
						tmpStart.x = Video_start.get(getIndex).x + 20;
						tmpStart.y = Video_start.get(getIndex).y + 20;
						tmpEnd.x = Video_end.get(getIndex).x + 20;
						tmpEnd.y = Video_end.get(getIndex).y + 20;
						tmpString = Video_src.get(getIndex);
						tmpString1 = Vproperty.get(getIndex);
						//tmpString1 = Video_link.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[3]){
						tmpStart.x = Audio_start.get(getIndex).x + 20;
						tmpStart.y = Audio_start.get(getIndex).y + 20;
						tmpEnd.x = Audio_end.get(getIndex).x + 20;
						tmpEnd.y = Audio_end.get(getIndex).y + 20;
						tmpString = Audio_src.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[4]){
						tmpStart.x = HyperLink_start.get(getIndex).x + 20;
						tmpStart.y = HyperLink_start.get(getIndex).y + 20;
						tmpEnd.x = HyperLink_end.get(getIndex).x + 20;
						tmpEnd.y = HyperLink_end.get(getIndex).y + 20;
						tmpString = HyperLink_src.get(getIndex);
						tmpString1 = Aproperty.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[5]){
						tmpStart.x = DB_start.get(getIndex).x + 20;
						tmpStart.y = DB_start.get(getIndex).y + 20;
						tmpEnd.x = DB_end.get(getIndex).x + 20;
						tmpEnd.y = DB_end.get(getIndex).y + 20;
						tmpString = DB_src.get(getIndex);
						tmpFont = DB_font.get(getIndex);
						tmpString1 = DB_align.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[6]){
						tmpStart.x = Olap_start.get(getIndex).x + 20;
						tmpStart.y = Olap_start.get(getIndex).y + 20;
						tmpEnd.x = Olap_end.get(getIndex).x + 20;
						tmpEnd.y = Olap_end.get(getIndex).y + 20;
						tmpString = Olap_src.get(getIndex);
						tmpFont = Olap_font.get(getIndex);
						tmpString1 = Olap_align.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[7]){
						tmpStart.x = Drag_Drops_start.get(getIndex).x + 20;
						tmpStart.y = Drag_Drops_start.get(getIndex).y + 20;
						tmpEnd.x = Drag_Drops_end.get(getIndex).x + 20;
						tmpEnd.y = Drag_Drops_end.get(getIndex).y + 20;
						tmpString = Drag_Drops_src.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[8]){
						tmpStart.x = FromButton_start.get(getIndex).x + 20;
						tmpStart.y = FromButton_start.get(getIndex).y + 20;
						tmpEnd.x = Drops_end.get(getIndex).x + 20;
						tmpEnd.y = Drops_end.get(getIndex).y + 20;
						tmpString = Drops_src.get(getIndex);
						tmpString1 = Drops_link.get(getIndex);
						tmpInt = Drops_style.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[9]){
						tmpStart.x = Mining_start.get(getIndex).x + 20;
						tmpStart.y = Mining_start.get(getIndex).y + 20;
						tmpEnd.x = Mining_end.get(getIndex).x + 20;
						tmpEnd.y = Mining_end.get(getIndex).y + 20;
						tmpHash = Mining_src.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[10]){
						tmpStart.x = Toggle_start.get(getIndex).x + 20;
						tmpStart.y = Toggle_start.get(getIndex).y + 20;
						tmpEnd.x = Toggle_end.get(getIndex).x + 20;
						tmpEnd.y = Toggle_end.get(getIndex).y + 20;
						tmpString = Toggle_title.get(getIndex);
						tmpString1 = Toggle_sub.get(getIndex);
						tmpString2 = Toggle_swap.get(getIndex);

						isCopied = true;
					}
					else if(isSelected[11]){
						tmpStart.x = Navi_start.get(getIndex).x + 20;
						tmpStart.y = Navi_start.get(getIndex).y + 20;
						tmpEnd.x = Navi_end.get(getIndex).x + 20;
						tmpEnd.y = Navi_end.get(getIndex).y + 20;
						tmpVector = navi_src.get(getIndex);
						tmpVector1 = navi_link.get(getIndex);
						tmpString = Navi_style.get(getIndex);
						isCopied = true;
					}
				}
				else if(e.getKeyCode() == KeyEvent.VK_V){
					System.out.println("Paste");
					if(isCopied){
						if(isSelected[0]){
							Text_start.add(tmpStart);
							Text_end.add(tmpEnd);
							Text_src.add(tmpString);
							Text_font.add(tmpFont);
							Text_align.add(tmpString1);

							isCopied = false;
							repaint();
						}
						else if(isSelected[1]){
							Image_start.add(tmpStart);
							Image_end.add(tmpEnd);
							Image_src.add(tmpString);
							Image_link.add(tmpString1);

							isCopied = false;
							repaint();
						}
						else if(isSelected[2]){
							Video_start.add(tmpStart);
							Video_end.add(tmpEnd);
							Video_src.add(tmpString);
							//Video_link.add(tmpString1);
							Vproperty.add(tmpString1);
							
							isCopied = false;
							repaint();
						}
						else if(isSelected[3]){
							Audio_start.add(tmpStart);
							Audio_end.add(tmpEnd);
							Audio_src.add(tmpString);

							isCopied = false;
							repaint();
						}
						else if(isSelected[4]){
							HyperLink_start.add(tmpStart);
							HyperLink_end.add(tmpEnd);
							HyperLink_src.add(tmpString);
							Aproperty.add(tmpString1);

							isCopied = false;
							repaint();
						}
						else if(isSelected[5]){
							DB_start.add(tmpStart);
							DB_end.add(tmpEnd);
							DB_src.add(tmpString);
							DB_font.add(tmpFont);
							DB_align.add(tmpString1);

							isCopied = false;
							repaint();
						}
						else if(isSelected[6]){
							Olap_start.add(tmpStart);
							Olap_end.add(tmpEnd);
							Olap_src.add(tmpString);
							Olap_font.add(tmpFont);
							Olap_align.add(tmpString);

							isCopied = false;
							repaint();
						}
						else if(isSelected[7]){
							Drag_Drops_start.add(tmpStart);
							Drag_Drops_end.add(tmpEnd);
							Drag_Drops_src.add(tmpString);

							isCopied = false;
							repaint();
						}
						else if(isSelected[8]){
							FromButton_start.add(tmpStart);
							Drops_end.add(tmpEnd);
							Drops_src.add(tmpString);
							Drops_link.add(tmpString1);
							Drops_style.add(tmpInt);

							isCopied = false;
							repaint();
						}
						else if(isSelected[9]){
							Mining_start.add(tmpStart);
							Mining_end.add(tmpEnd);
							Mining_src.add(tmpHash);

							isCopied = false;
							repaint();
						}
						else if(isSelected[10]){
							Toggle_start.add(tmpStart);
							Toggle_end.add(tmpEnd);
							Toggle_title.add(tmpString);
							Toggle_sub.add(tmpString1);
							Toggle_swap.add(tmpString2);

							isCopied = false;
							repaint();
						}
						else if(isSelected[11]){
							Navi_start.add(tmpStart);
							Navi_end.add(tmpEnd);
							navi_src.add(tmpVector);
							navi_link.add(tmpVector1);
							Navi_style.add(tmpString);
							
							isCopied = false;
							repaint();
						}
					}
				}
			}
		}

	}

	/**
	 * 마우스의 모션과 영역의 포인터를 기록하기 위한 이너 클래스
	 * @author Chae
	 *
	 */
	private class EditMouseListener extends MouseAdapter implements MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			//더블클릭하면 사이즈 변경
			if(e.getClickCount() == 2){
				new ETSizeChange();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if(isSelected[0]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Text_start.get(getIndex);
				Point end = Text_end.get(getIndex);

				/*
				 * 크기 변경 모드이면 크기를 변경한다
				 * changeSize[0] == true : 위쪽 영역 크기 조절
				 * changeSize[1] == true : 아래쪽 영역 크기 조절
				 * changeSize[2] == true : 왼쪽 영역 크기 조절
				 * changeSize[3] == true : 오른쪽 영역 크기 조절
				 */
				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Text_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Text_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Text_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Text_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Text_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Text_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Text_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Text_end.set(getIndex, pt);
					}
				}
				//크기 변경 모드가 아니면 위치이동을 한다
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Text_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Text_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Text_start.set(getIndex, start);
					Text_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Text_start.set(getIndex, start);
					Text_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Text_start.set(getIndex, start);
					Text_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Text_start.set(getIndex, start);
					Text_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[1]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Image_start.get(getIndex);
				Point end = Image_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Image_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Image_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Image_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Image_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Image_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Image_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Image_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Image_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Image_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Image_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Image_start.set(getIndex, start);
					Image_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Image_start.set(getIndex, start);
					Image_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Image_start.set(getIndex, start);
					Image_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Image_start.set(getIndex, start);
					Image_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[2]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Video_start.get(getIndex);
				Point end = Video_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Video_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Video_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Video_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Video_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Video_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Video_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Video_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Video_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Video_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Video_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Video_start.set(getIndex, start);
					Video_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Video_start.set(getIndex, start);
					Video_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Video_start.set(getIndex, start);
					Video_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Video_start.set(getIndex, start);
					Video_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[3]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Audio_start.get(getIndex);
				Point end = Audio_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Audio_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Audio_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Audio_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Audio_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Audio_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Audio_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Audio_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Audio_end.set(getIndex, pt);
					}
				}
				else{

					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Audio_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Audio_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Audio_start.set(getIndex, start);
					Audio_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Audio_start.set(getIndex, start);
					Audio_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Audio_start.set(getIndex, start);
					Audio_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Audio_start.set(getIndex, start);
					Audio_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[4]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = HyperLink_start.get(getIndex);
				Point end = HyperLink_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						HyperLink_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						HyperLink_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						HyperLink_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						HyperLink_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						HyperLink_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						HyperLink_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						HyperLink_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						HyperLink_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					HyperLink_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					HyperLink_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					HyperLink_start.set(getIndex, start);
					HyperLink_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					HyperLink_start.set(getIndex, start);
					HyperLink_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					HyperLink_start.set(getIndex, start);
					HyperLink_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					HyperLink_start.set(getIndex, start);
					HyperLink_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[5]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = DB_start.get(getIndex);
				Point end = DB_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						DB_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						DB_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						DB_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						DB_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						DB_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						DB_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						DB_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						DB_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					DB_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					DB_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					DB_start.set(getIndex, start);
					DB_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					DB_start.set(getIndex, start);
					DB_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					DB_start.set(getIndex, start);
					DB_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					DB_start.set(getIndex, start);
					DB_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[6]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Olap_start.get(getIndex);
				Point end = Olap_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Olap_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Olap_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Olap_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Olap_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Olap_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Olap_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Olap_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Olap_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Olap_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Olap_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Olap_start.set(getIndex, start);
					Olap_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Olap_start.set(getIndex, start);
					Olap_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Olap_start.set(getIndex, start);
					Olap_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Olap_start.set(getIndex, start);
					Olap_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[7]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Drag_Drops_start.get(getIndex);
				Point end = Drag_Drops_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Drag_Drops_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Drag_Drops_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Drag_Drops_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Drag_Drops_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Drag_Drops_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Drag_Drops_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Drag_Drops_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Drag_Drops_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Drag_Drops_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Drag_Drops_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Drag_Drops_start.set(getIndex, start);
					Drag_Drops_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Drag_Drops_start.set(getIndex, start);
					Drag_Drops_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Drag_Drops_start.set(getIndex, start);
					Drag_Drops_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Drag_Drops_start.set(getIndex, start);
					Drag_Drops_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[8]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = FromButton_start.get(getIndex);
				Point end = Drops_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						FromButton_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						FromButton_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Drops_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Drops_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						FromButton_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						FromButton_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Drops_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Drops_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					FromButton_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Drops_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					FromButton_start.set(getIndex, start);
					Drops_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					FromButton_start.set(getIndex, start);
					Drops_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					FromButton_start.set(getIndex, start);
					Drops_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					FromButton_start.set(getIndex, start);
					Drops_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[9]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Mining_start.get(getIndex);
				Point end = Mining_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Mining_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Mining_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Mining_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Mining_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Mining_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Mining_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Mining_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Mining_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Mining_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Mining_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Mining_start.set(getIndex, start);
					Mining_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Mining_start.set(getIndex, start);
					Mining_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Mining_start.set(getIndex, start);
					Mining_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Mining_start.set(getIndex, start);
					Mining_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[10]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Toggle_start.get(getIndex);
				Point end = Toggle_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Toggle_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Toggle_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Toggle_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Toggle_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Toggle_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Toggle_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Toggle_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Toggle_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Toggle_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Toggle_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Toggle_start.set(getIndex, start);
					Toggle_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Toggle_start.set(getIndex, start);
					Toggle_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Toggle_start.set(getIndex, start);
					Toggle_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Toggle_start.set(getIndex, start);
					Toggle_end.set(getIndex, end);
					return;
				}
			}
			else if(isSelected[11]){
				startP = endP;
				endP = e.getPoint();

				//드래그 된 만큼의 거리를 구한다
				int distanceX = 0;
				int distanceY = 0;
				if(isDragged){
					isDragged = false;
				}
				else{
					distanceX = endP.x - startP.x;
					distanceY = endP.y - startP.y;
				}

				//해당되는 영역의 시작점과 끝점을 불러온다
				Point start = Navi_start.get(getIndex);
				Point end = Navi_end.get(getIndex);

				if(changeSize[0]){
					if(end.y - start.y < 10){
						Point pt = new Point(start.x, start.y - 5);
						Navi_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x, start.y + distanceY);
						Navi_start.set(getIndex, pt);
					}
				}
				else if(changeSize[1]){
					if(end.y - start.y < 10){
						Point pt = new Point(end.x, end.y + 5);
						Navi_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x, end.y + distanceY);
						Navi_end.set(getIndex, pt);
					}
				}
				else if(changeSize[2]){
					if(end.x - start.x < 10){
						Point pt = new Point(start.x - 5, start.y);
						Navi_start.set(getIndex, pt);
					}
					else{
						Point pt = new Point(start.x + distanceX, start.y);
						Navi_start.set(getIndex, pt);
					}
				}
				else if(changeSize[3]){
					if(end.x - start.x < 10){
						Point pt = new Point(end.x + 5, end.y);
						Navi_end.set(getIndex, pt);
					}
					else{
						Point pt = new Point(end.x + distanceX, end.y);
						Navi_end.set(getIndex, pt);
					}
				}
				else{
					//시작점과 끝점에 거리를 더해서 벡터에 저장한다
					Point pt = new Point(start.x + distanceX, start.y + distanceY);
					Navi_start.set(getIndex, pt);				
					pt = new Point(end.x + distanceX, end.y + distanceY);
					Navi_end.set(getIndex, pt);
				}

				//지정된 영역을 넘어서지 않게 한다
				if(start.x < 0){
					start.x += 1;
					end.x += 1;
					Navi_start.set(getIndex, start);
					Navi_end.set(getIndex, end);
					return;
				}
				if(start.y < 0){
					start.y += 1;
					end.y += 1;
					Navi_start.set(getIndex, start);
					Navi_end.set(getIndex, end);
					return;
				}
				if(end.x > panelSizeX){
					start.x -= 1;
					end.x -= 1;
					Navi_start.set(getIndex, start);
					Navi_end.set(getIndex, end);
					return;
				}
				if(end.y > panelSizeY){
					start.y -= 1;
					end.y -= 1;
					Navi_start.set(getIndex, start);
					Navi_end.set(getIndex, end);
					return;
				}
			}
			else{
				endP = e.getPoint();
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {

			for(int i = 0; i < isSelected.length; i++)
				isSelected[i] = false;

			//flag의 값에 따른 이벤트
			switch(flag){
			case 0:
				startP = e.getPoint();

				//영역 안에 클릭을 했는지 검사한다
				if(Text_start.size() != 0){
					for(int i = 0; i < Text_start.size(); i++){
						if((Text_start.get(i).x - 3 < startP.x) && (startP.x < Text_end.get(i).x + 3)){
							if((Text_start.get(i).y - 3 < startP.y) && (startP.y < Text_end.get(i).y + 3)){
								isDragged = true;
								isSelected[0] = true;
								getIndex = i;
							}
							if(isSelected[0])
								break;
						}
					}
				}
				if(Image_start.size() != 0){
					for(int i = 0; i < Image_start.size(); i++){
						if((Image_start.get(i).x - 3 < startP.x) && (startP.x < Image_end.get(i).x + 3)){
							if((Image_start.get(i).y - 3 < startP.y) && (startP.y < Image_end.get(i).y + 3)){
								isDragged = true;
								isSelected[1] = true;
								getIndex = i;
							}
						}
						if(isSelected[1])
							break;
					}
				}
				if(Video_start.size() != 0){
					for(int i = 0; i < Video_start.size(); i++){
						if((Video_start.get(i).x - 3< startP.x) && (startP.x < Video_end.get(i).x + 3)){
							if((Video_start.get(i).y - 3< startP.y) && (startP.y < Video_end.get(i).y + 3)){
								isDragged = true;
								isSelected[2] = true;
								getIndex = i;
							}
						}
						if(isSelected[2])
							break;
					}
				}
				if(Audio_start.size() != 0){
					for(int i = 0; i < Audio_start.size(); i++){
						if((Audio_start.get(i).x - 3< startP.x) && (startP.x < Audio_end.get(i).x + 3)){
							if((Audio_start.get(i).y - 3< startP.y) && (startP.y < Audio_end.get(i).y + 3)){
								isDragged = true;
								isSelected[3] = true;
								getIndex = i;
							}
						}
						if(isSelected[3])
							break;
					}
				}
				if(HyperLink_start.size() != 0){
					for(int i = 0; i < HyperLink_start.size(); i++){
						if((HyperLink_start.get(i).x - 3< startP.x) && (startP.x < HyperLink_end.get(i).x + 3)){
							if((HyperLink_start.get(i).y - 3< startP.y) && (startP.y < HyperLink_end.get(i).y + 3)){
								isDragged = true;
								isSelected[4] = true;
								getIndex = i;
							}
						}
						if(isSelected[4])
							break;
					}
				}
				if(DB_start.size() != 0){
					for(int i = 0; i < DB_start.size(); i++){
						if((DB_start.get(i).x - 3< startP.x) && (startP.x < DB_end.get(i).x + 3)){
							if((DB_start.get(i).y - 3< startP.y) && (startP.y < DB_end.get(i).y + 3)){
								isDragged = true;
								isSelected[5] = true;
								getIndex = i;
							}
						}
						if(isSelected[5])
							break;
					}
				}
				if(Olap_start.size() != 0){
					for(int i = 0; i < Olap_start.size(); i++){
						if((Olap_start.get(i).x - 3< startP.x) && (startP.x < Olap_end.get(i).x + 3)){
							if((Olap_start.get(i).y - 3< startP.y) && (startP.y < Olap_end.get(i).y + 3)){
								isDragged = true;
								isSelected[6] = true;
								getIndex = i;
							}
						}
						if(isSelected[6])
							break;
					}
				}
				if(Drag_Drops_start.size() != 0){
					for(int i = 0; i < Drag_Drops_start.size(); i++){
						if((Drag_Drops_start.get(i).x - 3< startP.x) && (startP.x < Drag_Drops_end.get(i).x + 3)){
							if((Drag_Drops_start.get(i).y - 3< startP.y) && (startP.y < Drag_Drops_end.get(i).y + 3)){
								isDragged = true;
								isSelected[7] = true;
								getIndex = i;
							}
						}
						if(isSelected[7])
							break;
					}
				}
				if(FromButton_start.size() != 0){
					for(int i = 0; i < FromButton_start.size(); i++){
						if((FromButton_start.get(i).x - 3< startP.x) && (startP.x < Drops_end.get(i).x + 3)){
							if((FromButton_start.get(i).y - 3< startP.y) && (startP.y < Drops_end.get(i).y + 3)){
								isDragged = true;
								isSelected[8] = true;
								getIndex = i;
							}
						}
						if(isSelected[8])
							break;
					}
				}
				if(Mining_start.size() != 0){
					for(int i = 0; i < Mining_start.size(); i++){
						if((Mining_start.get(i).x - 3< startP.x) && (startP.x < Mining_end.get(i).x + 3)){
							if((Mining_start.get(i).y - 3< startP.y) && (startP.y < Mining_end.get(i).y + 3)){
								isDragged = true;
								isSelected[9] = true;
								getIndex = i;
							}
						}
						if(isSelected[9])
							break;
					}
				}
				if(Toggle_start.size() != 0){
					for(int i = 0; i < Toggle_start.size(); i++){
						if((Toggle_start.get(i).x - 3< startP.x) && (startP.x < Toggle_end.get(i).x + 3)){
							if((Toggle_start.get(i).y - 3< startP.y) && (startP.y < Toggle_end.get(i).y + 3)){
								isDragged = true;
								isSelected[10] = true;
								getIndex = i;
							}
						}
						if(isSelected[10])
							break;
					}
				}
				if(Navi_start.size() != 0){
					for(int i = 0; i < Navi_start.size(); i++){
						if((Navi_start.get(i).x - 3 < startP.x) && (startP.x < Navi_end.get(i).x + 3)){
							if((Navi_start.get(i).y - 3 < startP.y) && (startP.y < Navi_end.get(i).y + 3)){
								isDragged = true;
								isSelected[11] = true;
								getIndex = i;
							}
						}
						if(isSelected[11])
							break;
					}
				}
				break;
			case 1:
				startP = e.getPoint();
				Text_start.add(e.getPoint());
				break;
			case 2:
				startP = e.getPoint();
				Image_start.add(e.getPoint());
				break;
			case 3:
				startP = e.getPoint();
				Video_start.add(e.getPoint());
				break;
			case 4:
				startP = e.getPoint();
				Audio_start.add(e.getPoint());
				break;
			case 5:
				startP = e.getPoint();
				HyperLink_start.add(e.getPoint());
				break;
			case 6:
				startP = e.getPoint();
				DB_start.add(e.getPoint());
				break;
			case 7:
				startP = e.getPoint();
				Olap_start.add(e.getPoint());
				break;
			case 8:
				startP = e.getPoint();
				Drag_Drops_start.add(e.getPoint());
				break;
			case 9:
				startP = e.getPoint();
				FromButton_start.add(e.getPoint());				
				break;
			case 10:
				startP = e.getPoint();
				Mining_start.add(e.getPoint());
				break;
			case 11:
				startP = e.getPoint();
				Toggle_start.add(e.getPoint());
				break;
			case 12:
				startP = e.getPoint();
				Navi_start.add(e.getPoint());
				break;
			}

			//선택된 영역에 따른 이벤트
			if(isSelected[0]){
				//영역의 가운데 지점을 구한다
				int midX = (Text_start.get(getIndex).x + Text_end.get(getIndex).x) / 2;
				int midY = (Text_start.get(getIndex).y + Text_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Text_start.get(getIndex).y + 3) && (startP.y > Text_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Text_end.get(getIndex).y + 3) && (startP.y > Text_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Text_start.get(getIndex).x + 3) && (startP.x > Text_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Text_end.get(getIndex).x + 3) && (startP.x > Text_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[1]){
				//영역의 가운데 지점을 구한다
				int midX = (Image_start.get(getIndex).x + Image_end.get(getIndex).x) / 2;
				int midY = (Image_start.get(getIndex).y + Image_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Image_start.get(getIndex).y + 3) && (startP.y > Image_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Image_end.get(getIndex).y + 3) && (startP.y > Image_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Image_start.get(getIndex).x + 3) && (startP.x > Image_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Image_end.get(getIndex).x + 3) && (startP.x > Image_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[2]){
				//영역의 가운데 지점을 구한다
				int midX = (Video_start.get(getIndex).x + Video_end.get(getIndex).x) / 2;
				int midY = (Video_start.get(getIndex).y + Video_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Video_start.get(getIndex).y + 3) && (startP.y > Video_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Video_end.get(getIndex).y + 3) && (startP.y > Video_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Video_start.get(getIndex).x + 3) && (startP.x > Video_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Video_end.get(getIndex).x + 3) && (startP.x > Video_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[3]){
				//영역의 가운데 지점을 구한다
				int midX = (Audio_start.get(getIndex).x + Audio_end.get(getIndex).x) / 2;
				int midY = (Audio_start.get(getIndex).y + Audio_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Audio_start.get(getIndex).y + 3) && (startP.y > Audio_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Audio_end.get(getIndex).y + 3) && (startP.y > Audio_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Audio_start.get(getIndex).x + 3) && (startP.x > Audio_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Audio_end.get(getIndex).x + 3) && (startP.x > Audio_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[4]){
				//영역의 가운데 지점을 구한다
				int midX = (HyperLink_start.get(getIndex).x + HyperLink_end.get(getIndex).x) / 2;
				int midY = (HyperLink_start.get(getIndex).y + HyperLink_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < HyperLink_start.get(getIndex).y + 3) && (startP.y > HyperLink_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < HyperLink_end.get(getIndex).y + 3) && (startP.y > HyperLink_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < HyperLink_start.get(getIndex).x + 3) && (startP.x > HyperLink_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < HyperLink_end.get(getIndex).x + 3) && (startP.x > HyperLink_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[5]){
				//영역의 가운데 지점을 구한다
				int midX = (DB_start.get(getIndex).x + DB_end.get(getIndex).x) / 2;
				int midY = (DB_start.get(getIndex).y + DB_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < DB_start.get(getIndex).y + 3) && (startP.y > DB_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < DB_end.get(getIndex).y + 3) && (startP.y > DB_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < DB_start.get(getIndex).x + 3) && (startP.x > DB_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < DB_end.get(getIndex).x + 3) && (startP.x > DB_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[6]){
				//영역의 가운데 지점을 구한다
				int midX = (Olap_start.get(getIndex).x + Olap_end.get(getIndex).x) / 2;
				int midY = (Olap_start.get(getIndex).y + Olap_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Olap_start.get(getIndex).y + 3) && (startP.y > Olap_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Olap_end.get(getIndex).y + 3) && (startP.y > Olap_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Olap_start.get(getIndex).x + 3) && (startP.x > Olap_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Olap_end.get(getIndex).x + 3) && (startP.x > Olap_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[7]){
				//영역의 가운데 지점을 구한다
				int midX = (Drag_Drops_start.get(getIndex).x + Drag_Drops_end.get(getIndex).x) / 2;
				int midY = (Drag_Drops_start.get(getIndex).y + Drag_Drops_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Drag_Drops_start.get(getIndex).y + 3) && (startP.y > Drag_Drops_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Drag_Drops_end.get(getIndex).y + 3) && (startP.y > Drag_Drops_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Drag_Drops_start.get(getIndex).x + 3) && (startP.x > Drag_Drops_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Drag_Drops_end.get(getIndex).x + 3) && (startP.x > Drag_Drops_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[8]){
				//영역의 가운데 지점을 구한다
				int midX = (FromButton_start.get(getIndex).x + Drops_end.get(getIndex).x) / 2;
				int midY = (FromButton_start.get(getIndex).y + Drops_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < FromButton_start.get(getIndex).y + 3) && (startP.y > FromButton_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Drops_end.get(getIndex).y + 3) && (startP.y > Drops_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < FromButton_start.get(getIndex).x + 3) && (startP.x > FromButton_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Drops_end.get(getIndex).x + 3) && (startP.x > Drops_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[9]){
				//영역의 가운데 지점을 구한다
				int midX = (Mining_start.get(getIndex).x + Mining_end.get(getIndex).x) / 2;
				int midY = (Mining_start.get(getIndex).y + Mining_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Mining_start.get(getIndex).y + 3) && (startP.y > Mining_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Mining_end.get(getIndex).y + 3) && (startP.y > Mining_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Mining_start.get(getIndex).x + 3) && (startP.x > Mining_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Mining_end.get(getIndex).x + 3) && (startP.x > Mining_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[10]){
				//영역의 가운데 지점을 구한다
				int midX = (Toggle_start.get(getIndex).x + Toggle_end.get(getIndex).x) / 2;
				int midY = (Toggle_start.get(getIndex).y + Toggle_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Toggle_start.get(getIndex).y + 3) && (startP.y > Toggle_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Toggle_end.get(getIndex).y + 3) && (startP.y > Toggle_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Toggle_start.get(getIndex).x + 3) && (startP.x > Toggle_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Toggle_end.get(getIndex).x + 3) && (startP.x > Toggle_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
			else if(isSelected[11]){
				//영역의 가운데 지점을 구한다
				int midX = (Navi_start.get(getIndex).x + Navi_end.get(getIndex).x) / 2;
				int midY = (Navi_start.get(getIndex).y + Navi_end.get(getIndex).y) / 2;

				//위쪽의 사각형이 눌렸을 때
				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Navi_start.get(getIndex).y + 3) && (startP.y > Navi_start.get(getIndex).y - 3)){
					System.out.println("위쪽 눌림");
					changeSize[0] = true;
				}
				//아래쪽의 사각형이 눌렸을 때
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Navi_end.get(getIndex).y + 3) && (startP.y > Navi_end.get(getIndex).y - 3)){
					System.out.println("아래쪽 눌림");
					changeSize[1] = true;
				}
				//왼쪽의 사각형이 눌렸을 때
				else if((startP.x < Navi_start.get(getIndex).x + 3) && (startP.x > Navi_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("왼쪽 눌림");
					changeSize[2] = true;
				}
				//오른쪽의 사각형이 눌렸을 때
				else if((startP.x < Navi_end.get(getIndex).x + 3) && (startP.x > Navi_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					System.out.println("오른쪽 눌림");
					changeSize[3] = true;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			for(int i = 0; i < 4; i++)
				changeSize[i] = false;

			switch(flag){
			case 0:
				endP = startP;
				break;
			case 1:
				endP = e.getPoint();
				Text_end.add(e.getPoint());
				Text_src.add(src);
				Text_font.add(new Font(fontName, fontStyle, fontSize));
				Text_align.add(fontAlign);
				flag = 0;
				break;
			case 2:
				endP = e.getPoint();
				Image_src.add(src);
				Image_link.add(src2);
				Image_end.add(e.getPoint());
				flag = 0;
				break;
			case 3:
				endP = e.getPoint();
				Video_src.add(src);
				Video_link.add(src3);
				Video_end.add(e.getPoint());
				Vproperty.add(Vpro);

				flag = 0;
				break;
			case 4:
				endP = e.getPoint();
				Audio_src.add(src);
				Audio_end.add(e.getPoint());
				Aproperty.add(Apro);
				flag = 0;
				break;
			case 5:
				endP = e.getPoint();
				HyperLink_src.add(src);
				HyperLink_src1.add(src1);
				HyperLink_end.add(e.getPoint());
				flag = 0;
				break;
			case 6:
				endP = e.getPoint();
				DB_src.add(src);
				DB_end.add(e.getPoint());
				DB_font.add(new Font(fontName, fontStyle, fontSize));
				DB_align.add(fontAlign);
				flag = 0;
				break;
			case 7:
				endP = e.getPoint();
				Olap_src.add(src);
				Olap_end.add(e.getPoint());
				Olap_font.add(new Font(fontName, fontStyle, fontSize));
				Olap_align.add(fontAlign);
				flag = 0;
				break;
			case 8:
				endP = e.getPoint();
				Drag_Drops_src.add(src);
				Drag_Drops_end.add(e.getPoint());
				flag = 0;
				break;
			case 9:
				endP = e.getPoint();
				Drops_src.add(src);
				Drops_link.add(src1);
				Drops_style.add(btnStyle);
				Drops_end.add(e.getPoint());
				flag = 0;
				break;
			case 10:
				endP = e.getPoint();
				Mining_src.add(srcMining);
				Mining_end.add(e.getPoint());
				flag = 0;
				break;
			case 11:
				endP = e.getPoint();
				Toggle_title.add(src);
				Toggle_sub.add(src1);
				Toggle_swap.add(src2);
				Toggle_end.add(e.getPoint());
				flag = 0;
				break;
			case 12:
				endP = e.getPoint();
				navi_src.add(Navi_src);
				navi_link.add(Navi_link);
				Navi_end.add(e.getPoint());
				Navi_style.add(naviStyle);
				flag = 0;
				break;
			}
			endP = startP;
			src = "";
			src1 = "";
			src2 = "";
			src3 = "";
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);

			startP = e.getPoint();

			if(isSelected[0]){
				int midX = (Text_start.get(getIndex).x + Text_end.get(getIndex).x) / 2;
				int midY = (Text_start.get(getIndex).y + Text_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Text_start.get(getIndex).y + 3) && (startP.y > Text_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Text_end.get(getIndex).y + 3) && (startP.y > Text_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Text_start.get(getIndex).x + 3) && (startP.x > Text_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Text_end.get(getIndex).x + 3) && (startP.x > Text_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						Text_font.set(getIndex, new Font(fontName, fontStyle, fontSize));
						Text_align.set(getIndex, fontAlign);
						endP = startP;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Text_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Text_end.set(getIndex, point);
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[1]){
				int midX = (Image_start.get(getIndex).x + Image_end.get(getIndex).x) / 2;
				int midY = (Image_start.get(getIndex).y + Image_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Image_start.get(getIndex).y + 3) && (startP.y > Image_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Image_end.get(getIndex).y + 3) && (startP.y > Image_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Image_start.get(getIndex).x + 3) && (startP.x > Image_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Image_end.get(getIndex).x + 3) && (startP.x > Image_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Image_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Image_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[2]){
				int midX = (Video_start.get(getIndex).x + Video_end.get(getIndex).x) / 2;
				int midY = (Video_start.get(getIndex).y + Video_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Video_start.get(getIndex).y + 3) && (startP.y > Video_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Video_end.get(getIndex).y + 3) && (startP.y > Video_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Video_start.get(getIndex).x + 3) && (startP.x > Video_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Video_end.get(getIndex).x + 3) && (startP.x > Video_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Video_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Video_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();						
					}
				}
			}
			else if(isSelected[3]){
				int midX = (Audio_start.get(getIndex).x + Audio_end.get(getIndex).x) / 2;
				int midY = (Audio_start.get(getIndex).y + Audio_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Audio_start.get(getIndex).y + 3) && (startP.y > Audio_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Audio_end.get(getIndex).y + 3) && (startP.y > Audio_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Audio_start.get(getIndex).x + 3) && (startP.x > Audio_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Audio_end.get(getIndex).x + 3) && (startP.x > Audio_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Audio_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Audio_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[4]){
				int midX = (HyperLink_start.get(getIndex).x + HyperLink_end.get(getIndex).x) / 2;
				int midY = (HyperLink_start.get(getIndex).y + HyperLink_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < HyperLink_start.get(getIndex).y + 3) && (startP.y > HyperLink_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < HyperLink_end.get(getIndex).y + 3) && (startP.y > HyperLink_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < HyperLink_start.get(getIndex).x + 3) && (startP.x > HyperLink_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < HyperLink_end.get(getIndex).x + 3) && (startP.x > HyperLink_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						HyperLink_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						HyperLink_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[5]){
				int midX = (DB_start.get(getIndex).x + DB_end.get(getIndex).x) / 2;
				int midY = (DB_start.get(getIndex).y + DB_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < DB_start.get(getIndex).y + 3) && (startP.y > DB_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < DB_end.get(getIndex).y + 3) && (startP.y > DB_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < DB_start.get(getIndex).x + 3) && (startP.x > DB_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < DB_end.get(getIndex).x + 3) && (startP.x > DB_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						DB_font.set(getIndex, new Font(fontName, fontStyle, fontSize));
						DB_align.set(getIndex, fontAlign);
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						DB_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						DB_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[6]){
				int midX = (Olap_start.get(getIndex).x + Olap_end.get(getIndex).x) / 2;
				int midY = (Olap_start.get(getIndex).y + Olap_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Olap_start.get(getIndex).y + 3) && (startP.y > Olap_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Olap_end.get(getIndex).y + 3) && (startP.y > Olap_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Olap_start.get(getIndex).x + 3) && (startP.x > Olap_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Olap_end.get(getIndex).x + 3) && (startP.x > Olap_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						Olap_font.set(getIndex, new Font(fontName, fontStyle, fontSize));
						Olap_align.set(getIndex, fontAlign);
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Olap_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Olap_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[7]){
				int midX = (Drag_Drops_start.get(getIndex).x + Drag_Drops_end.get(getIndex).x) / 2;
				int midY = (Drag_Drops_start.get(getIndex).y + Drag_Drops_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Drag_Drops_start.get(getIndex).y + 3) && (startP.y > Drag_Drops_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Drag_Drops_end.get(getIndex).y + 3) && (startP.y > Drag_Drops_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Drag_Drops_start.get(getIndex).x + 3) && (startP.x > Drag_Drops_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Drag_Drops_end.get(getIndex).x + 3) && (startP.x > Drag_Drops_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Drag_Drops_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Drag_Drops_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[8]){
				int midX = (FromButton_start.get(getIndex).x + Drops_end.get(getIndex).x) / 2;
				int midY = (FromButton_start.get(getIndex).y + Drops_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < FromButton_start.get(getIndex).y + 3) && (startP.y > FromButton_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Drops_end.get(getIndex).y + 3) && (startP.y > Drops_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < FromButton_start.get(getIndex).x + 3) && (startP.x > FromButton_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Drops_end.get(getIndex).x + 3) && (startP.x > Drops_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						FromButton_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Drops_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[9]){
				int midX = (Mining_start.get(getIndex).x + Mining_end.get(getIndex).x) / 2;
				int midY = (Mining_start.get(getIndex).y + Mining_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Mining_start.get(getIndex).y + 3) && (startP.y > Mining_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Mining_end.get(getIndex).y + 3) && (startP.y > Mining_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Mining_start.get(getIndex).x + 3) && (startP.x > Mining_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Mining_end.get(getIndex).x + 3) && (startP.x > Mining_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Mining_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Mining_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[10]){
				int midX = (Toggle_start.get(getIndex).x + Toggle_end.get(getIndex).x) / 2;
				int midY = (Toggle_start.get(getIndex).y + Toggle_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Toggle_start.get(getIndex).y + 3) && (startP.y > Toggle_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Toggle_end.get(getIndex).y + 3) && (startP.y > Toggle_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Toggle_start.get(getIndex).x + 3) && (startP.x > Toggle_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Toggle_end.get(getIndex).x + 3) && (startP.x > Toggle_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Toggle_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Toggle_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
			else if(isSelected[11]){
				int midX = (Navi_start.get(getIndex).x + Navi_end.get(getIndex).x) / 2;
				int midY = (Navi_start.get(getIndex).y + Navi_end.get(getIndex).y) / 2;

				if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Navi_start.get(getIndex).y + 3) && (startP.y > Navi_start.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < midX + 3) && (startP.x > midX - 3) &&
						(startP.y < Navi_end.get(getIndex).y + 3) && (startP.y > Navi_end.get(getIndex).y - 3)){
					Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Navi_start.get(getIndex).x + 3) && (startP.x > Navi_start.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else if((startP.x < Navi_end.get(getIndex).x + 3) && (startP.x > Navi_end.get(getIndex).x - 3) &&
						(startP.y < midY + 3) && (startP.y > midY - 3)){
					Cursor cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
					ETEditorPage.this.setCursor(cursor);
				}
				else{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					ETEditorPage.this.setCursor(cursor);
					if(isChanged){
						isChanged = false;
						int x = Integer.parseInt(src);
						int y = Integer.parseInt(src1);
						int width = Integer.parseInt(src2);
						int height = Integer.parseInt(src3);
						Point point = new Point(x, y);
						Navi_start.set(getIndex, point);
						point = new Point(x + width, y + height);
						Navi_end.set(getIndex, point);
						endP = startP;
						src = "";
						src1 = "";
						src2 = "";
						src3 = "";
						repaint();
					}
				}
			}
		}
	}

	/**
	 * 패널에 그래픽의 출력을 담당하는 메소드
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.removeAll();

		//각 컴포넌트별 영역을 화면에 출력해줌
		if(Text_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.TEXTAREA);
			for(int i = 0; i < Text_end.size(); i++){
				Point sp = Text_start.get(i);
				Point ep = Text_end.get(i);


				g.drawImage(img, sp.x, sp.y, ep.x-sp.x, ep.y-sp.y, this);

				String str = Text_src.get(i);
				str = str.replaceAll("\t", "   ");
				Font font = Text_font.get(i);
				int height = sp.y + font.getSize();
				StringTokenizer token = new StringTokenizer(str, "\n", true);
				String tmp = "";
				while(token.hasMoreTokens()){
					g.setFont(font);
					if((tmp = token.nextToken()).equals("\n"))
						g.drawString(" ", sp.x, height);
					else
						g.drawString(tmp, sp.x, height);
					height += font.getSize()/2;
				}
				g.setFont(new Font("굴림", Font.PLAIN, 12));
				if(!Text_align.get(i).equals(""))
					g.drawString("<" + Text_align.get(i) + "정렬>", ep.x, ep.y);
			}
		}
		if(Image_start.size() != 0){
			for(int i = 0; i < Image_end.size(); i++){
				Point sp = Image_start.get(i);
				Point ep = Image_end.get(i);
				Image img;
				try {
					if(Image_src.get(i).indexOf("://") != -1){
						img = Toolkit.getDefaultToolkit().getImage(new URL(Image_src.get(i)));
					}
					else{
						img = Toolkit.getDefaultToolkit().getImage(Image_src.get(i));
					}
					g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(Video_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.VIDEOAREA);
			for(int i = 0; i < Video_end.size(); i++){
				Point sp = Video_start.get(i);
				Point ep = Video_end.get(i);
				g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
			}
		}
		if(Audio_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.AUDIOAREA);
			for(int i = 0; i < Audio_end.size(); i++){
				Point sp = Audio_start.get(i);
				Point ep = Audio_end.get(i);
				g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
			}
		}
		if(HyperLink_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.HYPERLINKAREA);
			for(int i = 0; i < HyperLink_end.size(); i++){
				Point sp = HyperLink_start.get(i);
				Point ep = HyperLink_end.get(i);
				g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
				g.drawString(HyperLink_src.get(i), sp.x, (sp.y + ep.y) / 2);
			}
		}
		if(DB_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.DBAREA);
			for(int i = 0; i < DB_end.size(); i++){
				Point sp = DB_start.get(i);
				Point ep = DB_end.get(i);

				g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);

				String str = DB_src.get(i);
				str = str.replaceAll("\t", " ");
				Font font = DB_font.get(i);
				int height = sp.y + font.getSize();
				StringTokenizer token = new StringTokenizer(str, "\n", true);
				String tmp = "";
				while(token.hasMoreTokens()){
					g.setFont(font);
					if((tmp = token.nextToken()).equals("\n"))
						g.drawString(" ", sp.x, height);
					else
						g.drawString(tmp, sp.x, height);
					height += font.getSize()/2;
				}
				g.setFont(new Font("굴림", Font.PLAIN, 12));
				if(!DB_align.get(i).equals(""))
					g.drawString("<" + DB_align.get(i) + "정렬>", ep.x, ep.y);
			}
		}
		if(Olap_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.OLAPAREA);
			for(int i = 0; i < Olap_end.size(); i++){
				Point sp = Olap_start.get(i);
				Point ep = Olap_end.get(i);

				g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);

				String str = Olap_src.get(i);
				str = str.replaceAll("\t", " ");
				Font font = Olap_font.get(i);
				int height = sp.y + font.getSize();
				StringTokenizer token = new StringTokenizer(str, "\n", true);
				String tmp = "";
				while(token.hasMoreTokens()){
					g.setFont(font);
					if((tmp = token.nextToken()).equals("\n"))
						g.drawString(" ", sp.x, height);
					else
						g.drawString(tmp, sp.x, height);
					height += font.getSize()/2;
				}
				g.setFont(new Font("굴림", Font.PLAIN, 12));
				if(!Olap_align.get(i).equals(""))
					g.drawString("<" + Olap_align.get(i) + "정렬>", ep.x, ep.y);
			}
		}
		if(Drag_Drops_start.size() != 0){
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.DRAGAREA);
			for(int i = 0; i < Drag_Drops_end.size(); i++){
				Point sp = Drag_Drops_start.get(i);
				Point ep = Drag_Drops_end.get(i);
				g.setFont(new Font("굴림", Font.PLAIN, 12));
				g.drawImage(img, sp.x, sp.y, ep.x-sp.x, ep.y-sp.y, this);

				String str = Drag_Drops_src.get(i);
				str = str.replaceAll("\t", "   ");
				int height = (sp.y + ep.y) / 2;
				StringTokenizer token = new StringTokenizer(str, "\n");
				while(token.hasMoreTokens()){
					g.drawString(token.nextToken(), sp.x, height);
					height += 15;
				}			
			}
		}
		if(FromButton_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.FORMBUTTONAREA);
			for(int i = 0; i < Drops_end.size(); i++){
				Point sp = FromButton_start.get(i);
				Point ep = Drops_end.get(i);
				g.setFont(new Font("굴림", Font.PLAIN, 12));
				g.drawImage(img, sp.x, sp.y, ep.x-sp.x, ep.y-sp.y, this);

				String str = Drops_src.get(i);
				str = str.replaceAll("\t", "   ");
				int height = (sp.y + ep.y) / 2;
				StringTokenizer token = new StringTokenizer(str, "\n");
				while(token.hasMoreTokens()){
					g.drawString(token.nextToken(), sp.x, height);
					height += 15;
				}
				switch(Drops_style.get(i)){
				case 1:
					g.drawString("<round 버튼>", ep.x, ep.y);
					break;
				case 2:
					g.drawString("<rectangle 버튼>", ep.x, ep.y);

				}
			}
		}
		if(Mining_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.MININGAREA);
			for(int i = 0; i < Mining_end.size(); i++){
				Point sp = Mining_start.get(i);
				Point ep = Mining_end.get(i);

				g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
			}
		}
		if(Toggle_start.size() != 0){
			Image img = null;
			for(int i = 0; i < Toggle_end.size(); i++){
				Point sp = Toggle_start.get(i);
				Point ep = Toggle_end.get(i);
				try {
					if(Toggle_title.get(i).indexOf("://") != -1){
						img = Toolkit.getDefaultToolkit().getImage(new URL(Toggle_title.get(i)));
					}
					else{
						img = Toolkit.getDefaultToolkit().getImage(Toggle_title.get(i));
					}
					g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(Navi_start.size() != 0){
			Image img = Toolkit.getDefaultToolkit().getImage(ETIconPaths.NAVIAREA);
			for(int i = 0; i < Navi_end.size(); i++){
				Point sp = Navi_start.get(i);
				Point ep = Navi_end.get(i);
					g.drawImage(img, sp.x, sp.y, ep.x - sp.x, ep.y - sp.y, this);
				}
		}

		//영역이 선택되면 선택되었음을 알려준다
		if(isSelected[0]){
			g.setColor(Color.RED);
			g.drawRect(Text_start.get(getIndex).x, Text_start.get(getIndex).y, 
					Text_end.get(getIndex).x - Text_start.get(getIndex).x, 
					Text_end.get(getIndex).y - Text_start.get(getIndex).y);

			//영역의 가운데 지점을 구한다
			int midX = ((Text_start.get(getIndex).x + Text_end.get(getIndex).x)) / 2;
			int midY = ((Text_start.get(getIndex).y + Text_end.get(getIndex).y)) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Text_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Text_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Text_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Text_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[1]){
			g.setColor(Color.RED);			
			g.drawRect(Image_start.get(getIndex).x, Image_start.get(getIndex).y, 
					Image_end.get(getIndex).x - Image_start.get(getIndex).x, 
					Image_end.get(getIndex).y - Image_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Image_start.get(getIndex).x + Image_end.get(getIndex).x) / 2;
			int midY = (Image_start.get(getIndex).y + Image_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Image_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Image_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Image_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Image_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[2]){
			g.setColor(Color.RED);			
			g.drawRect(Video_start.get(getIndex).x, Video_start.get(getIndex).y, 
					Video_end.get(getIndex).x - Video_start.get(getIndex).x, 
					Video_end.get(getIndex).y - Video_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Video_start.get(getIndex).x + Video_end.get(getIndex).x) / 2;
			int midY = (Video_start.get(getIndex).y + Video_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Video_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Video_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Video_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Video_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[3]){
			g.setColor(Color.RED);			
			g.drawRect(Audio_start.get(getIndex).x, Audio_start.get(getIndex).y, 
					Audio_end.get(getIndex).x - Audio_start.get(getIndex).x, 
					Audio_end.get(getIndex).y - Audio_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Audio_start.get(getIndex).x + Audio_end.get(getIndex).x) / 2;
			int midY = (Audio_start.get(getIndex).y + Audio_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Audio_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Audio_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Audio_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Audio_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[4]){
			g.setColor(Color.RED);			
			g.drawRect(HyperLink_start.get(getIndex).x, HyperLink_start.get(getIndex).y, 
					HyperLink_end.get(getIndex).x - HyperLink_start.get(getIndex).x, 
					HyperLink_end.get(getIndex).y - HyperLink_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (HyperLink_start.get(getIndex).x + HyperLink_end.get(getIndex).x) / 2;
			int midY = (HyperLink_start.get(getIndex).y + HyperLink_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, HyperLink_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, HyperLink_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(HyperLink_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(HyperLink_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[5]){
			g.setColor(Color.RED);			
			g.drawRect(DB_start.get(getIndex).x, DB_start.get(getIndex).y, 
					DB_end.get(getIndex).x - DB_start.get(getIndex).x, 
					DB_end.get(getIndex).y - DB_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (DB_start.get(getIndex).x + DB_end.get(getIndex).x) / 2;
			int midY = (DB_start.get(getIndex).y + DB_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, DB_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, DB_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(DB_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(DB_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[6]){
			g.setColor(Color.RED);			
			g.drawRect(Olap_start.get(getIndex).x, Olap_start.get(getIndex).y, 
					Olap_end.get(getIndex).x - Olap_start.get(getIndex).x, 
					Olap_end.get(getIndex).y - Olap_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Olap_start.get(getIndex).x + Olap_end.get(getIndex).x) / 2;
			int midY = (Olap_start.get(getIndex).y + Olap_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Olap_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Olap_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Olap_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Olap_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[7]){
			g.setColor(Color.RED);			
			g.drawRect(Drag_Drops_start.get(getIndex).x, Drag_Drops_start.get(getIndex).y, 
					Drag_Drops_end.get(getIndex).x - Drag_Drops_start.get(getIndex).x, 
					Drag_Drops_end.get(getIndex).y - Drag_Drops_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Drag_Drops_start.get(getIndex).x + Drag_Drops_end.get(getIndex).x) / 2;
			int midY = (Drag_Drops_start.get(getIndex).y + Drag_Drops_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Drag_Drops_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Drag_Drops_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Drag_Drops_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Drag_Drops_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[8]){
			g.setColor(Color.RED);			
			g.drawRect(FromButton_start.get(getIndex).x, FromButton_start.get(getIndex).y, 
					Drops_end.get(getIndex).x - FromButton_start.get(getIndex).x, 
					Drops_end.get(getIndex).y - FromButton_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (FromButton_start.get(getIndex).x + Drops_end.get(getIndex).x) / 2;
			int midY = (FromButton_start.get(getIndex).y + Drops_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, FromButton_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Drops_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(FromButton_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Drops_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[9]){
			g.setColor(Color.RED);			
			g.drawRect(Mining_start.get(getIndex).x, Mining_start.get(getIndex).y, 
					Mining_end.get(getIndex).x - Mining_start.get(getIndex).x, 
					Mining_end.get(getIndex).y - Mining_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Mining_start.get(getIndex).x + Mining_end.get(getIndex).x) / 2;
			int midY = (Mining_start.get(getIndex).y + Mining_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Mining_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Mining_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Mining_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Mining_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[10]){
			g.setColor(Color.RED);			
			g.drawRect(Toggle_start.get(getIndex).x, Toggle_start.get(getIndex).y, 
					Toggle_end.get(getIndex).x - Toggle_start.get(getIndex).x, 
					Toggle_end.get(getIndex).y - Toggle_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Toggle_start.get(getIndex).x + Toggle_end.get(getIndex).x) / 2;
			int midY = (Toggle_start.get(getIndex).y + Toggle_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Toggle_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Toggle_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Toggle_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Toggle_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else if(isSelected[11]){
			g.setColor(Color.RED);			
			g.drawRect(Navi_start.get(getIndex).x, Navi_start.get(getIndex).y, 
					Navi_end.get(getIndex).x - Navi_start.get(getIndex).x, 
					Navi_end.get(getIndex).y - Navi_start.get(getIndex).y);
			//영역의 가운데 지점을 구한다
			int midX = (Navi_start.get(getIndex).x + Navi_end.get(getIndex).x) / 2;
			int midY = (Navi_start.get(getIndex).y + Navi_end.get(getIndex).y) / 2;

			/*
			 * 사각형의 각 변에 크기를 조절할 수 있게 하는 사각형을 그려준다
			 * 사각형의 색깔은 검정색
			 */
			g.setColor(Color.BLACK);
			g.fillRect(midX - 3, Navi_start.get(getIndex).y - 3, 6, 6);//위쪽
			g.fillRect(midX - 3, Navi_end.get(getIndex).y - 3, 6, 6);//아래쪽
			g.fillRect(Navi_start.get(getIndex).x - 3, midY - 3, 6, 6);//왼쪽
			g.fillRect(Navi_end.get(getIndex).x - 3, midY - 3, 6, 6);//오른쪽
		}
		else{
			//드래그 하는 중에 영역표시
			if(startP != null && endP != null){
				switch(flag){
				case 0:
					g.setColor(Color.BLUE);
					break;
				default:
					g.setColor(Color.BLACK);
					break;
				}
				g.drawRect(startP.x, startP.y, endP.x - startP.x, endP.y - startP.y);
			}
		}	
	}

}
