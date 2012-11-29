package kr.ac.ssu.dss.eztrans.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETFormButtonDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.menu.ETNewFileDialog;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPart;
import kr.ac.ssu.dss.eztrans.ui.workbench.ETComponentLists;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETFormButtonDialog;;

public class ETMakeHTML5File {

	private String fileName;

	private ETEditorPage APage = null;
	private String css = "";
	private String body = "";
	private String script = "";

	private ETEditorPart editorPart;
	private String textField;

	public static String charset = "EUC-KR";

	// 생성자
	public ETMakeHTML5File(ETEditorPage APage, ETEditorPart editorPart, int choice, int setLenght, int setHeight, int checkLine) throws FileNotFoundException
	{
		this.APage = APage;
		this.editorPart = editorPart;
		
		//파일을 저장할 위치를 물어보기 위해 JFileChooser를 화면에 출력한다
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(new FileFilter(".html", "html 파일"));

		int status = chooser.showSaveDialog(null);

		//확인 버튼이 눌리면 파일이 저장될 곳의 절대경로를 받아온다
		if(status == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
			fileName = file.getAbsolutePath();
			if(fileName.indexOf(".") == -1)
				fileName += ".html";
			else
			{
				if(fileName.indexOf(".html") == -1)
				{
					new JOptionPane();
					JOptionPane.showMessageDialog(null, "파일 확장자는 .html 이어야 합니다");
					return;
				}
			}
		}
		//취소 버튼이 눌리면 리턴
		else
		{
			return;
		}

		if(this.APage == null){

		}else{
			if(checkLine==1)
			{
				switch(choice)
				{
				case 1:
					css += "html{height:135%;overflow:hidden;}";
					css += "body{height:135%;width:600px;margin:0px auto;}";
					css += "#outer{height:80%;border:2px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 2:
					css += "html{height:110%;}";
					css += "body{height:110%;width:800px;margin:0px auto;}";
					css += "#outer{height:100%;border:2px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 3:
					css += "html{height:90%;}";
					css += "body{height:100%;width:1024px;margin:0px auto;}";
					css += "#outer{height:100%;border:2px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 4:
					css += "html{height:90%;}";
					css += "body{height:130%;width:1048px;margin:0px auto;}";
					css += "#outer{height:100%;border:2px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 5:
					css += "html{height:90%;}";
					css += "body{height:140%;width:1060px;margin:0px auto;}";
					css += "#outer{height:100%;border:2px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 6:
					css += "html{height:100%;}";
					css += "body{height:150%;width:setLenghtpx;margin:0px auto;}";
					css += "#outer{height:100%;border:2px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					break;
				}
			}
			else
			{
				switch(choice)
				{
				case 1:
					css += "html{height:135%;overflow:hidden;}";
					css += "body{height:135%;width:600px;margin:0px auto;}";
					css += "#outer{height:80%;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 2:
					css += "html{height:110%;}";
					css += "body{height:110%;width:800px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 3:
					css += "html{height:90%;}";
					css += "body{height:100%;width:1024px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 4:
					css += "html{height:90%;}";
					css += "body{height:130%;width:1048px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 5:
					css += "html{height:90%;}";
					css += "body{height:140%;width:1060px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					break;

				case 6:
					css += "html{height:100%;}";
					css += "body{height:150%;width:setLenghtpx;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					break;
				}	
			}


			// css
			styleText();
			styleDBText();
			styleImage();
			styleVideo();
			styleAudio();
			styleHyperlink();
			//////////////////////
			styleDragDrops();
			styleFormButton();
			styleMining();
			styleDB();
			styleOlap();
			

			// body
			bodyText();
			bodyDBText();
			bodyImage();
			bodyVideo();
			bodyAudio();
			bodyHyperlink();
			/////////////////////////
			bodyDragDrops();
			bodyFormButton();
			bodyMining();
			bodyDB();
			bodyOlap();
			
			//head안에 들어갈 JavaScript 코드
			//RGraph를 import 한다
			importRgraph();
			scrpitMining();
		}


		file = new File(fileName);

		//출력을 할 파일 열기
		PrintWriter out = new PrintWriter(file);

//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset= \"" + charset + "\"/>");
//		out.println("<title>");
//		out.println("</title>");
//		out.println("<style>");
//		out.println(css);
//		out.println("</style>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<div id=\"outer\">");
//		out.println("<div id=\"inner\">");
//		out.println(body);
//		out.println("</div>");
//		out.println("</div>");
//		out.println("</body>");
//		out.println("</html>");

		String inText = editorPart.tagEditingArea.getText();
		System.out.print(inText);
		out.print(inText);

		//끄기
		out.close();
	}
	
	private void importRgraph(){
		if(APage.Mining_start.size() != 0){
			this.script += "<script src=\"./lib/rgraph/RGraph.common.core.js\" ></script>\n";
			this.script += "<script src=\"./lib/rgraph/RGraph.common.effects.js\" ></script>\n";
			this.script += "<script src=\"./lib/rgraph/RGraph.common.tooltips.js\" ></script>\n";
			this.script += "<script src=\"./lib/rgraph/RGraph.bar.js\" ></script>\n\n";
		}
	}

	private void scrpitMining(){
		if(APage.Mining_start.size() != 0){
			Hashtable<String, Vector<String>> temp = new Hashtable<String, Vector<String>>();
			String color[] = {"#77f", "#7f7", "#f7f", "#ff7", "#7ff", "#777", "#fef", "#bda", "#cff", "#cac"};

			this.script += "<script>\n" +
					"window.onload = function ()" + "\n" +
					"{\n";

			for(int i = 0; i < APage.Mining_start.size(); i++){
				temp = APage.Mining_src.get(i);
				Vector<String> rows = temp.get("row");
				Vector<String> columns = temp.get("column");

				this.script += "var bar" + i + "=new RGraph.Bar('bar" + i + "' , [\n";

				for(int j = 0; j < columns.size(); j++){
					String x = columns.get(j);
					this.script += "[";
					for(int k = 0; k < rows.size(); k++){
						String y = rows.get(k);
						String xy = x + "," + y;
						Vector<String> value = temp.get(xy);
						this.script += value.get(0);
						if(k == rows.size() - 1)
							break;
						else
							this.script += ", ";
					}
					this.script += "]";
					if(j == columns.size() - 1)
						this.script += "]);\n";
					else
						this.script += ",\n";
				}

				this.script += "bar" + i + ".Set('chart.background.barcolor1', 'white');\n";
				this.script += "bar" + i + ".Set('chart.background.barcolor2', 'white');\n";
				this.script += "bar" + i + ".Set('chart.labels', [";

				for(int j = 0; j < columns.size(); j++){
					this.script += "'" + columns.get(j) + "'";
					if(j == columns.size() - 1)
						break;
					else
						this.script += ", ";
				}

				this.script += "]);\n";
				this.script += "bar" + i + ".Set('chart.key', [";

				for(int j = 0; j < rows.size(); j++){
					this.script += "'" + rows.get(j) + "'";
					if(j == rows.size() - 1)
						break;
					else
						this.script += ", ";
				}
				this.script += "]);\n";

				this.script += "bar" + i + ".Set('chart.key.position.y', 35);\n";
				this.script += "bar" + i + ".Set('chart.key.position', 'gutter');\n";
				this.script += "bar" + i + ".Set('chart.key.background', 'rgb(255,255,255)');\n";
				this.script += "bar" + i + ".Set('chart.colors', [";
				
				for(int j = 0; j < rows.size(); j++){
					this.script += "'" + color[j] + "'";
					if(j != rows.size() - 1)
						this.script += ", ";
				}
				
				this.script += "]);\n";
				
				this.script += "bar" + i + ".Set('chart.shadow', true);\n";
				this.script += "bar" + i + ".Set('chart.shadow.blur', 15);\n";
				this.script += "bar" + i + ".Set('chart.shadow.offsetx', 0);\n";
				this.script += "bar" + i + ".Set('chart.shadow.offsety', 0);\n";
				this.script += "bar" + i + ".Set('chart.shadow.color', '#aaa');\n";
				this.script += "bar" + i + ".Set('chart.yaxispos', 'right');\n";
				this.script += "bar" + i + ".Set('chart.strokestyle', 'rgba(0,0,0,0)');\n";
				this.script += "bar" + i + ".Set('chart.gutter.left', 5);\n";
				this.script += "bar" + i + ".Set('chart.gutter.right', 45);\n";
				this.script += "bar" + i + ".Draw();\n";
			}

			this.script += "}\n" + "</script>\n";
		}
	}

	private void styleText() {
		// if(APage.Text_start.size()==0)
		// return ;
		for (int i = 0; i < APage.Text_start.size(); i++) {
			int width = APage.Text_end.get(i).x - APage.Text_start.get(i).x;
			int height = APage.Text_end.get(i).y - APage.Text_start.get(i).y;
			this.css += ".t" + Integer.toString(i) + "{" +
			"position:absolute; " +
			"font-family:" + ETEditorPage.fontName + "; " + 
			"font-size:" + ETEditorPage.fontSize +"px; " +
			"font-style:" + ETEditorPage.fontStyle + "; " 
			+ "top:" + APage.Text_start.get(i).y + "px; " + "left:"
			+ APage.Text_start.get(i).x + "px; " + "width:" + width
			+ "px; " + "height:" + height + "px;} " + "\n";
		}
	}

	private void styleDBText() {
		for (int i = 0; i < APage.DB_start.size(); i++) {
			int width = APage.DB_end.get(i).x - APage.DB_start.get(i).x;
			int height = APage.DB_end.get(i).y - APage.DB_start.get(i).y;
			this.css += ".d" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.DB_start.get(i).y + "px;" + "left:"
			+ APage.DB_start.get(i).x + "px;" + "width:" + width
			+ "px;" + "height:" + height + "px;}" + "\n";
		}
	}

	private void styleImage() {
		// if(APage.Image_start.size()==0)
		// return ;
		for (int i = 0; i < APage.Image_start.size(); i++) {
			int width = APage.Image_end.get(i).x - APage.Image_start.get(i).x;
			int height = APage.Image_end.get(i).y - APage.Image_start.get(i).y;
			this.css += ".i" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.Image_start.get(i).y + "px;" + "left:"
			+ APage.Image_start.get(i).x + "px;" + "width:" + width
			+ "px;" + "height:" + height + "px;}" + "\n";
		}
	}

	private void styleVideo() {
		// if(APage.Video_start.size()==0)
		// return ;
		for (int i = 0; i < APage.Video_start.size(); i++) {
			int width = APage.Video_end.get(i).x - APage.Video_start.get(i).x;
			int height = APage.Video_end.get(i).y - APage.Video_start.get(i).y;
			this.css += ".v" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.Video_start.get(i).y + "px;" + "left:"
			+ APage.Video_start.get(i).x + "px;" + "width:" + width
			+ "px;" + "height:" + height + "px;}" + "\n";
		}
	}

	private void styleAudio() {
		// if(APage.Audio_start.size()==0)
		// return ;
		for (int i = 0; i < APage.Audio_start.size(); i++) {
			int width = APage.Audio_end.get(i).x - APage.Audio_start.get(i).x;
			int height = APage.Audio_end.get(i).y - APage.Audio_start.get(i).y;
			this.css += ".a" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.Audio_start.get(i).y + "px;" + "left:"
			+ APage.Audio_start.get(i).x + "px;" + "width:" + width
			+ "px;" + "height:" + height + "px;}" + "\n";

		}
	}

	private void styleHyperlink() {
		// if(APage.HyperLink_start.size()==0)
		// return ;
		for (int i = 0; i < APage.HyperLink_start.size(); i++) {
			int width = APage.HyperLink_end.get(i).x
			- APage.HyperLink_start.get(i).x;
			int height = APage.HyperLink_end.get(i).y
			- APage.HyperLink_start.get(i).y;
			this.css += ".h" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.HyperLink_start.get(i).y + "px;" + "left:"
			+ APage.HyperLink_start.get(i).x + "px;" + "width:" + width
			+ "px;" + "height:" + height + "px;}" + "\n";
		}
	}

	private void styleDragDrops() {
		for (int i = 0; i < APage.Drag_Drops_start.size(); i++) {
			int width = APage.Drag_Drops_end.get(i).x
			- APage.Drag_Drops_start.get(i).x;
			int height = APage.Drag_Drops_end.get(i).y
			- APage.Drag_Drops_start.get(i).y;
			this.css += ".d" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.Drag_Drops_start.get(i).y + "px;"
			+ "left:" + APage.Drag_Drops_start.get(i).x + "px;"
			+ "width:" + width + "px;" + "height:" + height + "px;}"
			+ "\n";
			// this.css +=".drop_1{position:absolute; left:100px; top:200px";
		}
	}

	private void styleFormButton() {
		for (int i = 0; i < APage.FromButton_start.size(); i++) {
			int width = APage.Drops_end.get(i).x - APage.FromButton_start.get(i).x;
			int height = APage.Drops_end.get(i).y - APage.FromButton_start.get(i).y;
			this.css += ".r" + Integer.toString(i) + "{position:absolute;"
			+ "top:" + APage.FromButton_start.get(i).y + "px;" + "left:"
			+ APage.FromButton_start.get(i).x + "px;" + "width:" + width
			+ "px;" + "height:" + height + "px;}" + "\n";
			this.css += ".r0{margin:0px auto;";
			this.css += ".r0{position : relative;";
		}
	}
	
	private void styleMining()
	{
		int width = 0, height = 0;
		for(int i = 0; i < APage.Mining_start.size(); i++){
			width = APage.Mining_end.get(i).x - APage.Mining_start.get(i).x;
			height = APage.Mining_end.get(i).y - APage.Mining_start.get(i).y;
			this.css += ".m" + Integer.toString(i) + 
					"{position:absolute;" +
					"top:" + APage.Mining_start.get(i).y + "px;" +
					"left:" + APage.Mining_start.get(i).x + "px;" +
					"width:" + width + "px;" +
					"height:" + height + "px;}" + "\n";
		}
	}

	private void styleDB(){
		int width= 0, height = 0;
		for(int i = 0; i < APage.DB_start.size(); i++){
			width = APage.DB_end.get(i).x - APage.DB_start.get(i).x;
			height = APage.DB_end.get(i).y - APage.DB_start.get(i).y;
			this.css += ".db" + Integer.toString(i) + 
					"{position:absolute;" +
					"top:" + APage.DB_start.get(i).y + "px;" +
					"left:" + APage.DB_start.get(i).x + "px;" +
					"width:" + width + "px;" +
					"height:" + height + "px;}" + "\n";
		}
	}
	
	private void styleOlap(){
		int width= 0, height = 0;
		for(int i = 0; i < APage.Olap_start.size(); i++){
			width = APage.Olap_end.get(i).x - APage.Olap_start.get(i).x;
			height = APage.Olap_end.get(i).y - APage.Olap_start.get(i).y;
			this.css += ".olap" + Integer.toString(i) + 
					"{position:absolute;" +
					"top:" + APage.Olap_start.get(i).y + "px;" +
					"left:" + APage.Olap_start.get(i).x + "px;" +
					"width:" + width + "px;" +
					"height:" + height + "px;}" + "\n";
		}
	}

	private void bodyText() {
		// if(APage.Text_start.size()==0)
		// return ;
		for (int i = 0; i < APage.Text_start.size(); i++) {
			this.body += "<PRE><p class='t" + Integer.toString(i) + "'>"
			+ APage.Text_src.get(i) + "</p></PRE>" + "\n";
		}
	}

	private void bodyDBText() {
		for (int i = 0; i < APage.DB_start.size(); i++) {
			this.body += "<PRE><p class='d" + Integer.toString(i) + "'>"
			+ APage.DB_src.get(i) + "</p></PRE>" + "\n";
		}
	}

	private void bodyImage() {
		// if(APage.Image_start.size()==0)
		// return ;

		for (int i = 0; i < APage.Image_start.size(); i++) {
			this.body += "<img class='i" + Integer.toString(i) + "' src='"
			+ APage.Image_src.get(i) + "' />" + "\n";
			;
		}
	}

	private void bodyVideo()
	{
//		if(APage.Video_start.size()==0)
//			return ;
		for(int i=0;i<APage.Video_start.size();i++)
		{
			this.body += "<video class='v" + Integer.toString(i) + "' src='"+ APage.Video_src.get(i) + "' " + setVProperty() + " controls preload='false'></video>" + "\n";;
		}
	}
	
	private void bodyAudio()
	{
//		if(APage.Audio_start.size()==0)
//			return ;
		for(int i=0;i<APage.Audio_start.size();i++)
		{
			this.body += "<audio class='a" + Integer.toString(i) + "' src='"+ APage.Audio_src.get(i) + "' " + setAProperty() + " controls='controls' ></audio>" + "\n";;
		}
	}

	private void bodyHyperlink() {
		// if(APage.HyperLink_start.size()==0)
		// return ;
		for (int i = 0; i < APage.HyperLink_start.size(); i++) {
			this.body += "<a class='h" + Integer.toString(i) + "' href='"
			+ APage.HyperLink_src.get(i) + "'>"
			+ APage.HyperLink_src1.get(i) + "</a>" + "\n";
			;
		}
	}

	private void bodyDragDrops() {
		for (int i = 0; i < APage.Drag_Drops_start.size(); i++) {
			this.body += "<PRE><p class='d" + Integer.toString(i) + "' draggable= \"true\">"
			+ APage.Drag_Drops_src.get(i) + "</p></PRE>" + "\n";
		}
	}

	private void bodyFormButton() {
		// if(APage.HyperLink_start.size()==0)s
		// return ;
		
		for (int i = 0; i < APage.FromButton_start.size(); i++) {
			this.body += "<PRE><form class='r" + Integer.toString(i) + "'>"
		//	+ "<input type =\"button\" value=\"" + "확인" + "\"></form>" + "</p></PRE>" + "\n";
			 + "<input type =\"button\" value=\"" + APage.Drops_src.get(i) + "\"></form>" + "</p></PRE>" + "\n";
			
			;
		
		}

	}
	
	private void bodyMining(){
		for(int i = 0; i < APage.Mining_start.size(); i++){
			int width = APage.Mining_end.get(i).x - APage.Mining_start.get(i).x;
			int height = APage.Mining_end.get(i).y - APage.Mining_start.get(i).y;
			this.body += "<mining class='m" + Integer.toString(i) + "'>" + "\n" 
					+ "<canvas id=\"bar" + i + "\" width=\"" + width + "\" height=\"" + 
					height + "\"></canvas>" + "\n"
					+ "</mining>\n";
		}
	}

	private void bodyDB(){
		for(int i = 0; i < APage.DB_start.size(); i++){
			this.body += "<PRE><db class='db" + Integer.toString(i) + "'>\n"
					+ APage.DB_src.get(i) + "\n</db></PRE>" + "\n";
		}
	}
	
	private void bodyOlap(){
		for(int i = 0; i < APage.Olap_start.size(); i++){
			this.body += "<PRE><olap class='olap" + Integer.toString(i) + "'>\n"
					+ APage.Olap_src.get(i) + "\n</olap></PRE>" + "\n";
		}
	}

	/**
	 * 저장하기 다이얼로그에서 저장될 파일의 필터를 설정하기 위한 이너클래스
	 */
	private class FileFilter extends javax.swing.filechooser.FileFilter {
		private String type;
		private String desc;

		public FileFilter(String type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		@Override
		public boolean accept(File f) {
			return f.getName().endsWith(type) || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return desc;
		}

	}
	private String setVProperty()
	{
		String s = "";
		if(ETComponentLists.etVideoDialog.checkLoop.isSelected()==true)
			s += " loop";
		else
			s += "";
		if(ETComponentLists.etVideoDialog.checkAutoPlay.isSelected()==true){
			s += " autoplay";
			return s;
		}
		else
			return s;
		
	}
	private String setAProperty()
	{
		String s = "";
		if(ETComponentLists.etAudioDialog.checkLoop.isSelected()==true)
			s += " loop";
		else
			s += "";
		if(ETComponentLists.etAudioDialog.checkAutoPlay.isSelected()==true){
			s += " autoplay";
			return s;
		}
		else
			return s;
	}
}
