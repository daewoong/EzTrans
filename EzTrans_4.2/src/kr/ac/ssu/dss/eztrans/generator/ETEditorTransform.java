package kr.ac.ssu.dss.eztrans.generator;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JTextArea;

import kr.ac.ssu.dss.eztrans.ui.workbench.ETComponentLists;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPage;
import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPart;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETFormButtonDialog;
import kr.ac.ssu.dss.eztrans.ui.dialogs.component.ETNavigationDialog;

public class ETEditorTransform {

	private ETEditorPart eteditPart = null;

	private ETEditorPage APage;
	private String css="";
	private String body="";
	private String script = "";
	private int autoChoice;

	public static String charset = "EUC-KR";

	// 생성자
	public ETEditorTransform(ETEditorPage APage, ETEditorPart editPart, int choice, int setLenght, int setHeight, int checkLine) throws FileNotFoundException
	{
		this.APage = APage;
		this.eteditPart = editPart;

		autoChoice = ETFormButtonDialog.autoSelect;

		if(this.APage == null){

		}else{
			if(checkLine==1)
			{
				switch(choice)
				{
				case 1:
					css += "html{height:135%;overflow:hidden;}";
					css += "body{height:135%;width:600px;margin:0px auto;}";
					css += "#outer{height:80%;border:1px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 2:
					css += "html{height:110%;}";
					css += "body{height:110%;width:800px;margin:0px auto;}";
					css += "#outer{height:100%;border:1px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 3:
					css += "html{height:90%;}";
					css += "body{height:100%;width:1024px;margin:0px auto;}";
					css += "#outer{height:100%;border:1px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 4:
					css += "html{height:90%;}";
					css += "body{height:130%;width:1048px;margin:0px auto;}";
					css += "#outer{height:100%;border:1px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 5:
					css += "html{height:90%;}";
					css += "body{height:140%;width:1060px;margin:0px auto;}";
					css += "#outer{height:100%;border:1px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 6:
					css += "html{height:100%;}";
					css += "body{height:150%;width:setLenghtpx;margin:0px auto;}";
					css += "#outer{height:100%;border:1px Solid Black;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
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
					css += "\n";
					break;

				case 2:
					css += "html{height:110%;}";
					css += "body{height:110%;width:800px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 3:
					css += "html{height:90%;}";
					css += "body{height:100%;width:1024px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 4:
					css += "html{height:90%;}";
					css += "body{height:130%;width:1048px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 5:
					css += "html{height:90%;}";
					css += "body{height:140%;width:1060px;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;

				case 6:
					css += "html{height:100%;}";
					css += "body{height:150%;width:setLenghtpx;margin:0px auto;}";
					css += "#outer{height:100%;}";
					css += "#inner{;margin:10px; position:relative}";
					css += "\n";
					break;
				}	
			}
			//head안에 들어갈 JavaScript 코드
			importJquery();
			importRgraph();
			scrpitMining();

			// css
			styleText();
			styleImage();
			styleVideo();
			styleAudio();
			styleHyperlink();
			styleDragDrops();
			styleFormButton();
			styleMining();
			styleDB();
			styleOlap();
			styleButton();
			styleHover();
			styleNavigation();

			// body
			bodyText();
			bodyImage();
			bodyVideo();
			bodyAudio();
			bodyHyperlink();
			bodyFormButton();
			bodyDragDrops();
			bodyMining();
			bodyDB();
			bodyOlap();
			bodyHover();
			bodyNavigation();
		}

		StringWriter str = new StringWriter();

		//출력을 할 파일 열기


		PrintWriter out = new PrintWriter(str);

		//적기		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset= \"" + charset + "\"/>");
		out.println("<title>");
		out.println("</title>");
		out.println("<style>");
		out.println(this.css);
		out.println("</style>");

		//JavaScript
		out.println(this.script);

		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"outer\">");
		out.println("<div id=\"inner\">");

		out.print(this.body);

		out.println("</div>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");

		this.eteditPart.getTagEditingArea().setText(str.toString());
		this.eteditPart.getTagEditingArea().setForeground(Color.blue);

		//끄기
		out.close();
	}

	private void importJquery(){
		if(APage.Toggle_start.size() != 0){
			this.script += "<Script language='JavaScript' src='./lib/jquery/jquery.js'></script>";
		}
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

	private void styleHover(){
		if(APage.Toggle_start.size() != 0){
			for(int i = 0; i < APage.Toggle_end.size(); i++){
				int width = APage.Toggle_end.get(i).x - APage.Toggle_start.get(i).x;
				int height = APage.Toggle_end.get(i).y - APage.Toggle_start.get(i).y;
				int top = APage.Toggle_start.get(i).y;
				int left = APage.Toggle_start.get(i).x;

				this.script += "<style type=\"text/css\">\n" +
						"ul.thumb" + Integer.toString(i) + "{\n" +
						"	height: " + height + "px;\n" +
						"	width: " + width + "px;\n" +
						"	top: " + top + "px; left: " + left + "px;\n" +
						"	position: absolute;\n" +
						"	float: center;\n" +
						"	list-style: none;\n" +
						"	margin: 0; padding: 0;\n" +
						"}\n" +
						"ul.thumb" + Integer.toString(i) + " li {\n" +
						"	margin: 0; padding: 0;\n" +
						"	float: center;\n" +
						"	position: relative;\n" +
						"	width: " + width + "px; height: " + height + "px;\n" +
						"}\n" +
						"ul.thumb" + Integer.toString(i) + " li img {\n" +
						"	width: " + width + "px; height: " + height + "px;\n" +
						"	border: 1px solid #ddd;\n" +
						"	padding: 5px;\n" +
						"	background: #f0f0f0;\n" +
						"	position: absolute;\n" +
						"	left: 0; top: 0;\n" +
						"	-ms-interpolation-mode: bicubic;\n" + 
						"}\n" +
						"ul.thumb" + Integer.toString(i) + " li img.hover {\n" +
						"	background:url(thumb_bg.png) no-repeat center center;\n" +
						"	border: none;\n" +
						"}\n" +
						"</style>\n";

				this.script += "<script type=\"text/javascript\">\n" +
						"$(document).ready(function(){\n" +
						"	$(\"ul.thumb" + Integer.toString(i) + " li\").hover(function() {\n" +
						"		$(this).find('img').attr(\"src\", \"" + APage.Toggle_swap.get(i) + "\")\n" +
						"		$(this).find('img').addClass(\"hover\").stop()\n" +
						"			.animate({\n" +
						"				marginTop: '-" + height + "px', \n" +
						"				marginLeft: '-" + width + "px', \n" +
						"				top: '50%', \n" +
						"				left: '50%', \n" +
						"				width: '" + width * 1.5 + "px', \n" +
						"				height: '" + height * 1.5 + "px',\n" +
						"				padding: '20px' \n" +
						"			}, 500);\n" +
						"		} , function() {\n" +
						"		$(this).find('img').attr(\"src\", \"" + APage.Toggle_title.get(i) + "\")\n" +
						"		$(this).find('img').removeClass(\"hover\").stop()\n" +
						"			.animate({\n" +
						"				marginTop: '0', \n" +
						"				marginLeft: '0',\n" +
						"				top: '0', \n" +
						"				left: '0', \n" +
						"				width: '" + width + "px', \n" +
						"				height: '" + height + "px', \n" +
						"				padding: '5px'\n" +
						"			}, 500);\n" +
						"	});\n" +
						"	$(\"ul.thumb" + Integer.toString(i) + " li a\").click(function() {\n" +
						"		location.href='" + APage.Toggle_sub.get(i) + "';\n" +
						"		return false;		\n" +
						"	});\n" +
						" \n" +
						"});\n" +
						"</script>\n";
			}
		}
	}

	private void styleButton(){
		if(APage.Drops_end.size() != 0){
			for(int i = 0; i < APage.Drops_end.size(); i++){
				if(APage.Drops_style.get(i) == 1){
					this.css += "a.round.button" + Integer.toString(i) + "{\n"+
							"	display:inline-block;\n"+
							"	padding:5px 15px 6px;\n"+
							"	color:#000 !important;\n"+
							"	background-color:#ffffff\n"+
							"	font-size:13px;\n"+
							"	font-weight:bold;\n"+
							"	line-height:1;\n"+
							"	text-decoration:none;\n"+
							"	-moz-border-radius:10px;\n"+
							"	-webkit-border-radius:10px;\n"+
							"	-moz-box-shadow:0 1px 3px rgba(0, 0, 0, 0.8);\n"+
							"	-webkit-box-shadow:0 1px 3px rgba(0, 0, 0, 0.8);\n"+
							"	text-shadow:0 -1px 1px rgba(0, 0, 0, 0.25);\n"+
							"	border-bottom:1px solid rgba(0, 0, 0, 0.8);\n"+
							"	position:absolute;\n" + 
							"	top:" + APage.FromButton_start.get(i).y + "px;\n"+
							"	left:" + APage.FromButton_start.get(i).x + "px;\n"+
							"	width:auto;\n"+
							"	height:auto;\n"+
							"	cursor:pointer;\n"+
							"	overflow:visible;\n"+
							"}";
					this.css += "a.round.button" + Integer.toString(i) + ":hover{ background-color: #ffefd5; }";
				}
				else if(APage.Drops_style.get(i) == 2){
					this.css += "a.rec.button" + Integer.toString(i) + "{\n"+
							"	display:inline-block;\n"+
							"	padding:5px 15px 6px;\n"+
							"	color:#000 !important;\n"+
							"	background-color:#ffffff\n"+
							"	font-size:13px;\n"+
							"	font-weight:bold;\n"+
							"	line-height:1;\n"+
							"	text-decoration:none;\n"+
							"	-moz-box-shadow:0 1px 3px rgba(0, 0, 0, 0.8);\n"+
							"	-webkit-box-shadow:0 1px 3px rgba(0, 0, 0, 0.8);\n"+
							"	text-shadow:0 -1px 1px rgba(0, 0, 0, 0.25);\n"+
							"	border-bottom:1px solid rgba(0, 0, 0, 0.8);\n"+
							"	position:absolute;\n" + 
							"	top:" + APage.FromButton_start.get(i).y + "px;\n"+
							"	left:" + APage.FromButton_start.get(i).x + "px;\n"+
							"	width:auto;\n"+
							"	height:auto;\n"+
							"	cursor:pointer;\n"+
							"	overflow:visible;\n"+
							"}";
					this.css += "a.rec.button" + Integer.toString(i) + ":hover{ background-color: #ffefd5; }";
				}
			}
		}
	}

	private void styleText()
	{
		for(int i=0;i<APage.Text_start.size();i++)
		{
			int width = APage.Text_end.get(i).x - APage.Text_start.get(i).x;
			int height = APage.Text_end.get(i).y - APage.Text_start.get(i).y;
			this.css += ".t" + Integer.toString(i) + 
					"{position:absolute; " +
					"font-family:" + APage.Text_font.get(i).getFontName() + "; " + 
					"font-size:" + APage.Text_font.get(i).getSize() +"px; ";

			if(APage.Text_font.get(i).getStyle() == 0)
				this.css += "font-style: normal; ";
			else if(APage.Text_font.get(i).getStyle() == 2 || APage.Text_font.get(i).getStyle() == 3)
				this.css += "font-style: italic; ";

			if(APage.Text_font.get(i).getStyle() == 0)
				this.css += "font-weight: normal; ";
			else if(APage.Text_font.get(i).getStyle() == 1 || APage.Text_font.get(i).getStyle() == 3)
				this.css += "font-weight: bold; ";

			if(!APage.Text_align.get(i).equals(""))
				this.css += "text-align:" + APage.Text_align.get(i) + "; ";

			this.css += "top:" + APage.Text_start.get(i).y + "px; " +
					"left:" + APage.Text_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px;}" + "\n";
		}
	}

	private void styleImage()
	{	
		//		if(APage.Image_start.size()==0)
		//			return ;
		for(int i=0;i<APage.Image_start.size();i++)
		{
			int width = APage.Image_end.get(i).x - APage.Image_start.get(i).x;
			int height = APage.Image_end.get(i).y - APage.Image_start.get(i).y;
			this.css += ".i" + Integer.toString(i) + 
					"{position:absolute; " +
					"top:" + APage.Image_start.get(i).y + "px; " +
					"left:" + APage.Image_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px; border: 0; }" + "\n";
		}
	}

	private void styleVideo()
	{	
		//		if(APage.Video_start.size()==0)
		//			return ;
		for(int i=0;i<APage.Video_start.size();i++)
		{
			int width = APage.Video_end.get(i).x - APage.Video_start.get(i).x;
			int height = APage.Video_end.get(i).y - APage.Video_start.get(i).y;
			this.css += ".v" + Integer.toString(i) + 
					"{position:absolute; " +
					"top:" + APage.Video_start.get(i).y + "px; " +
					"left:" + APage.Video_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px;}" + "\n";;
		}
	}

	private void styleAudio()
	{	
		//		if(APage.Audio_start.size()==0)
		//			return ;
		for(int i=0;i<APage.Audio_start.size();i++)
		{
			int width = APage.Audio_end.get(i).x - APage.Audio_start.get(i).x;
			int height = APage.Audio_end.get(i).y - APage.Audio_start.get(i).y;
			this.css += ".a" + Integer.toString(i) + 
					"{position:absolute; " +
					"top:" + APage.Audio_start.get(i).y + "px; " +
					"left:" + APage.Audio_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px;}" + "\n";;
		}
	}

	private void styleHyperlink()
	{	
		//		if(APage.HyperLink_start.size()==0)
		//			return ;
		for(int i=0;i<APage.HyperLink_start.size();i++)
		{
			int width = APage.HyperLink_end.get(i).x - APage.HyperLink_start.get(i).x;
			int height = APage.HyperLink_end.get(i).y - APage.HyperLink_start.get(i).y;
			this.css += ".h" + Integer.toString(i) + 
					"{position:absolute; " +
					"top:" + APage.HyperLink_start.get(i).y + "px; " +
					"left:" + APage.HyperLink_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px;}" + "\n";;
		}
	}

	private void styleDragDrops()
	{
		for(int i=0;i<APage.Drag_Drops_start.size();i++)
		{
			int width = APage.Drag_Drops_end.get(i).x - APage.Drag_Drops_start.get(i).x;
			int height = APage.Drag_Drops_end.get(i).y - APage.Drag_Drops_start.get(i).y;
			this.css += ".d" + Integer.toString(i) + 
					"{position:absolute;" +
					"top:" + APage.Drag_Drops_start.get(i).y + "px;" +
					"left:" + APage.Drag_Drops_start.get(i).x + "px;" +
					"width:" + width + "px;" +
					"height:" + height + "px;}" + "\n";
		}
	}

	private void styleFormButton()
	{	
		for(int i=0;i<APage.FromButton_start.size();i++)
		{
			if(APage.Drops_style.get(i) != 1 && APage.Drops_style.get(i) != 2){
				int width = APage.Drops_end.get(i).x - APage.FromButton_start.get(i).x;
				int height = APage.Drops_end.get(i).y - APage.FromButton_start.get(i).y;
				this.css += ".r" + Integer.toString(i) + 
						"{position:absolute;" +
						"top:" + APage.FromButton_start.get(i).y + "px;" +
						"left:" + APage.FromButton_start.get(i).x + "px;" +
						"width:" + width + "px;" +
						"height:" + height + "px;}" + "\n";
			}
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
		for(int i=0;i<APage.DB_start.size();i++)
		{
			int width = APage.DB_end.get(i).x - APage.DB_start.get(i).x;
			int height = APage.DB_end.get(i).y - APage.DB_start.get(i).y;
			this.css += ".db" + Integer.toString(i) + 
					"{position:absolute; " +
					"font-family:" + APage.DB_font.get(i).getFontName() + "; " + 
					"font-size:" + APage.DB_font.get(i).getSize() +"px; ";

			if(APage.DB_font.get(i).getStyle() == 0)
				this.css += "font-style: normal; ";
			else if(APage.DB_font.get(i).getStyle() == 2 || APage.DB_font.get(i).getStyle() == 3)
				this.css += "font-style: italic; ";

			if(APage.DB_font.get(i).getStyle() == 0)
				this.css += "font-weight: normal; ";
			else if(APage.DB_font.get(i).getStyle() == 1 || APage.DB_font.get(i).getStyle() == 3)
				this.css += "font-weight: bold; ";

			if(!APage.DB_align.get(i).equals(""))
				this.css += "text-align:" + APage.DB_align.get(i) + "; ";

			this.css += "top:" + APage.DB_start.get(i).y + "px; " +
					"left:" + APage.DB_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px;}" + "\n";
		}
	}

	private void styleOlap(){
		for(int i=0;i<APage.Olap_start.size();i++)
		{
			int width = APage.Olap_end.get(i).x - APage.Olap_start.get(i).x;
			int height = APage.Olap_end.get(i).y - APage.Olap_start.get(i).y;
			this.css += ".olap" + Integer.toString(i) + 
					"{position:absolute; " +
					"font-family:" + APage.Olap_font.get(i).getFontName() + "; " + 
					"font-size:" + APage.Olap_font.get(i).getSize() +"px; ";

			if(APage.Olap_font.get(i).getStyle() == 0)
				this.css += "font-style: normal; ";
			else if(APage.Olap_font.get(i).getStyle() == 2 || APage.Olap_font.get(i).getStyle() == 3)
				this.css += "font-style: italic; ";

			if(APage.Olap_font.get(i).getStyle() == 0)
				this.css += "font-weight: normal; ";
			else if(APage.Olap_font.get(i).getStyle() == 1 || APage.Olap_font.get(i).getStyle() == 3)
				this.css += "font-weight: bold; ";

			if(!APage.Olap_align.get(i).equals(""))
				this.css += "text-align:" + APage.Olap_align.get(i) + "; ";

			this.css += "top:" + APage.Olap_start.get(i).y + "px; " +
					"left:" + APage.Olap_start.get(i).x + "px; " +
					"width:" + width + "px; " +
					"height:" + height + "px;}" + "\n";
		}
	}
	
	private void styleNavigation()
	{	
		//		if(APage.Video_start.size()==0)
		//			return ;
		for(int i=0;i<APage.Navi_start.size();i++)
		{
			if(APage.Navi_style.get(i).equals("A")){
				int width = APage.Navi_end.get(i).x - APage.Navi_start.get(i).x;
				int height = APage.Navi_end.get(i).y - APage.Navi_start.get(i).y;
				this.css += ".navbox" + Integer.toString(i) + 
						"{position:absolute; " +
						"top:" + APage.Navi_start.get(i).y + "px; " +
						"left:" + APage.Navi_start.get(i).x + "px; " +
						"width:" + width + "px; " +
						"height:" + height + "px;}" + "\n";
				this.css += ".navbox{position: relative;float: left;}\n" + 
						"ol.n" + Integer.toString(i) + "{list-style: none;display: block;width: 200px;position: relative;" + 
						"top: 100px;left: 100px;padding: 60px 0 60px 0;background: url(shad2.png) no-repeat;" +
						"-webkit-background-size: 50% 100%;}\n" +
						"li {margin: 5px 0 0 0;}\n" +
						"ol.n" + Integer.toString(i) + " li a {-webkit-transition: all 0.3s ease-out;background: #cbcbcb url(border.png) no-repeat;" +
						"color: #174867;padding: 7px 15px 7px 15px;-webkit-border-top-right-radius: 10px;" +
						"-webkit-border-bottom-right-radius: 10px;width: 100px;display: block;text-decoration: none;" +
						"-webkit-box-shadow: 2px 2px 4px #888;}\n" +
						"ol.n" + Integer.toString(i) + " li a:hover {background: #ebebeb url(border.png) no-repeat;color: #67a5cd;padding: 7px 15px 7px 30px;}\n";
			} else if(APage.Navi_style.get(i).equals("B")){
				int width = APage.Navi_end.get(i).x - APage.Navi_start.get(i).x;
				int height = APage.Navi_end.get(i).y - APage.Navi_start.get(i).y;
				this.css += ".n" + Integer.toString(i) + 
						"{position:absolute; " +
						"top:" + APage.Navi_start.get(i).y + "px; " +
						"left:" + APage.Navi_start.get(i).x + "px; " +
						"width:" + width + "px; " +
						"height:" + height + "px;}" + "\n";
				this.css += ".navbox{position: relative;float: left;}\n" + 
						"ul.n" + Integer.toString(i) + "{list-style: none;display: block;width: 200px;position: relative;" + 
						"top: 100px;left: 100px;padding: 60px 0 60px 0;background: url(shad2.png) no-repeat;" +
						"-webkit-background-size: 50% 100%;}\n" +
						"li {margin: 5px 0 0 0;}\n" +
						"ul.n" + Integer.toString(i) + " li a {-webkit-transition: all 0.3s ease-out;background: #cbcbcb url(border.png) no-repeat;" +
						"color: #174867;padding: 7px 15px 7px 15px;-webkit-border-top-right-radius: 10px;" +
						"-webkit-border-bottom-right-radius: 10px;width: 100px;display: block;text-decoration: none;" +
						"-webkit-box-shadow: 2px 2px 4px #888;}\n" +
						"ul.n" + Integer.toString(i) + " li a:hover {background: #ebebeb url(border.png) no-repeat;color: #67a5cd;padding: 7px 15px 7px 30px;}\n";
			}
		}
	}

	private void bodyText()
	{
		//		if(APage.Text_start.size()==0)
		//			return ;
		for(int i = 0; i < APage.Text_start.size(); i++)
		{
			this.body += "<p class='t" + Integer.toString(i) + "'>\n";
			String temp = APage.Text_src.get(i);
			temp = temp.replaceAll("\n", "<br>\n");
			this.body += temp;
			this.body += "\n</p>\n";
		}
	}
	private void bodyImage()
	{
		//		if(APage.Image_start.size()==0)
		//			return ;

		for(int i=0;i<APage.Image_start.size();i++)
		{
			//	this.body += "<img class='i" + Integer.toString(i) + "' src='" + APage.Image_src.get(i) + "' />" + "\n";;

			this.body += "<a href = '" +APage.Image_link.get(i)+ "'> "+ 
					"<img src='" + APage.Image_src.get(i) + "' class ='i" + Integer.toString(i)+"' /> "  +  "</a>\n";

		}
	}
	private void bodyVideo()
	{
		//		if(APage.Video_start.size()==0)
		//			return ;



		for(int i=0;i<APage.Video_start.size();i++)
		{
			this.body += "<video class='v" + Integer.toString(i) + "' src='"+ APage.Video_src.get(i) + "' " + APage.Vproperty.get(i) + " controls preload='false'></video>" + "\n";;


		}
	}
	private void bodyAudio()
	{
		//		if(APage.Audio_start.size()==0)
		//			return ;

		for(int i=0;i<APage.Audio_start.size();i++)
		{
			this.body += "<audio class='a" + Integer.toString(i) + "' src='"+ APage.Audio_src.get(i) + "' " + APage.Aproperty.get(i) + " controls='controls' ></audio>" + "\n";;
		}
	}
	private void bodyHyperlink()
	{
		//		if(APage.HyperLink_start.size()==0)
		//			return ;

		for(int i=0;i<APage.HyperLink_start.size();i++)
		{
			this.body += "<a class='h" + Integer.toString(i) + "' href='" + APage.HyperLink_src.get(i) + "'>" + APage.HyperLink_src1.get(i) + "</a>" + "\n";;
		}
	}

	private void bodyDragDrops() {
		for (int i = 0; i < APage.Drag_Drops_start.size(); i++) {
			this.body += "<p class='d" + Integer.toString(i) + "' draggable= \"true\">"
					+ APage.Drag_Drops_src.get(i) + "</p>" + "\n";
		}
	}

	private void bodyFormButton() {
		if(APage.Drops_end.size() != 0){
			this.body += "<form>\n";
			if(autoChoice==1)
			{
				for(int i = 0; i < APage.Drops_end.size(); i++){
					if(APage.Drops_style.get(i) == 12){
						this.body += "<a class='r" + Integer.toString(i) + "'>";
						this.body +=  "<input type='" + APage.Drops_src.get(i) + "' value='" + "전송" +
								"'/>";  // search 타입
					}
					else if(APage.Drops_style.get(i) == 1){
						this.body += "<a class = 'round button" + Integer.toString(i) + "' id = '" + Integer.toString(i) + 
								"' href = '" + APage.Drops_link.get(i) + "'>" +
								APage.Drops_src.get(i);
					}
					else if(APage.Drops_style.get(i) == 2){
						this.body += "<a class = 'rec button" + Integer.toString(i) + "' id = '" + Integer.toString(i) + 
								"' href = '" + APage.Drops_link.get(i) + "'>" +
								APage.Drops_src.get(i);
					}
					else {
						this.body += "<a class='r" + Integer.toString(i) + "'>";
						if(APage.Drops_style.get(i) == 4){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete='on'>";  // search 타입
						}
						else if(APage.Drops_style.get(i) == 5){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete='on'>";	// url 타입
						}
						else if(APage.Drops_style.get(i) == 6){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete='on'>";	// email 타입
						}
						else if(APage.Drops_style.get(i) == 7){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' min='1' max='10'>";	        // points 타입
						}
						else if(APage.Drops_style.get(i) == 8){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete = 'on'>";	// telephone 타입
						}
						else if(APage.Drops_style.get(i) == 9){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' min='0' max='100'>";	        // number 타입
						}
						else if(APage.Drops_style.get(i) == 10){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' min='0' max='100'>";	        // range 타입
						}
						else if(APage.Drops_style.get(i) == 11){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i)+ "'>";	        // date 타입" 
						}
					}
					this.body += "</a>\n";
				}
			}

			else
			{
				for(int i = 0; i < APage.Drops_end.size(); i++){
					if(APage.Drops_style.get(i) == 12){
						this.body += "<a class='r" + Integer.toString(i) + "'>";
						this.body +=  "<input type='" + APage.Drops_src.get(i) + "' value='" + "전송" +
								"/>' \n";  // search 타입
					}
					else if(APage.Drops_style.get(i) == 1){
						this.body += "<a class = 'round button" + Integer.toString(i) + "' id = '" + Integer.toString(i) + 
								"' href = '" + APage.Drops_link.get(i) + "'>" +
								APage.Drops_src.get(i);
					}
					else if(APage.Drops_style.get(i) == 2){
						this.body += "<a class = 'rec button" + Integer.toString(i) + "' id = '" + Integer.toString(i) + 
								"' href = '" + APage.Drops_link.get(i) + "'>" +
								APage.Drops_src.get(i);
					}
					else {
						this.body += "<a class='r" + Integer.toString(i) + "'>";
						if(APage.Drops_style.get(i) == 4){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete='off'>";  // search 타입
						}
						else if(APage.Drops_style.get(i) == 5){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete='off'>";	// url 타입
						}
						else if(APage.Drops_style.get(i) == 6){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete='off'> ";	// email 타입
						}
						else if(APage.Drops_style.get(i) == 7){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' min='1' max='10'>";	        // points 타입
						}
						else if(APage.Drops_style.get(i) == 8){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' required autocomplete = 'off'>";	// telephone 타입
						}
						else if(APage.Drops_style.get(i) == 9){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' min='0' max='100'>";	        // number 타입
						}
						else if(APage.Drops_style.get(i) == 10){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i) + "' min='0' max='100'>";	        // range 타입
						}
						else if(APage.Drops_style.get(i) == 11){
							this.body += APage.Drops_src.get(i) + "<input type='" + APage.Drops_src.get(i) + "' name='" + APage.Drops_src.get(i) +
									Integer.toString(i)+ "'> \n";	        // date 타입" 
						}
					}
					this.body += "</a>\n";
				}
			}
			this.body += "\n</form>\n";
		}
	}

	private void bodyMining(){
		for(int i = 0; i < APage.Mining_start.size(); i++){
			int width = APage.Mining_end.get(i).x - APage.Mining_start.get(i).x;
			int height = APage.Mining_end.get(i).y - APage.Mining_start.get(i).y;
			this.body += "<mining class='m" + Integer.toString(i) + "'>" + "\n" 
					+ "<canvas id=\"bar" + i + "\" width=\"" + width + "\" height=\"" + 
					height + "\"></canvas>" + "\n"	+ "</mining>\n";
		}
	}

	private void bodyDB(){
		for(int i = 0; i < APage.DB_start.size(); i++)
		{
			this.body += "<db class='db" + Integer.toString(i) + "'>\n";
			String temp = APage.DB_src.get(i);
			temp = temp.replaceAll("\n", "<br>\n");
			this.body += temp;
			this.body += "\n</db>\n";
		}
	}

	private void bodyOlap(){
		for(int i = 0; i < APage.Olap_start.size(); i++)
		{
			this.body += "<olap class='olap" + Integer.toString(i) + "'>\n";
			String temp = APage.Olap_src.get(i);
			temp = temp.replaceAll("\n", "<br>\n");
			this.body += temp;
			this.body += "\n</olap>\n";
		}
	}

	private void bodyHover(){
		for(int i = 0; i < APage.Toggle_start.size(); i++)
		{
			this.body += "<ul class='thumb" + Integer.toString(i) + "'>\n" +
					"<li><a href='#'><img src=\"" + APage.Toggle_title.get(i) + "\" alt=''/></a></li>\n" +
					"</ul>\n";
		}
	}
	
	private void bodyNavigation()
	{
		for(int i=0;i<APage.Navi_start.size();i++)
		{
			this.body += "<div class='navbox" + Integer.toString(i) + "'>\n";
			if(APage.Navi_style.get(i).equals("A")){
				this.body += "<ol class='n" + Integer.toString(i) + "'>\n";
				for(int j=0; j < APage.navi_src.get(i).size(); j++){
					if(ETNavigationDialog.choiceFormButton1 == 1){
						this.body += "      <li><a href = '" +APage.navi_link.get(i).get(j)+ "'> "+ 
							APage.navi_src.get(i).get(j) + "</a></li>\n";
					}
					else if(ETNavigationDialog.choiceFormButton1 == 2){
						this.body += "      <li><a href = '" +APage.navi_link.get(i).get(j)+ "'> "+ 
							"<img src='" + APage.navi_src.get(i).get(j) + "' /> "  +  "</a></li>\n";
					}
				}
				this.body += "</ol>\n";
			}
			else if(APage.Navi_style.get(i).equals("B")){
				this.body += "<ul class='n" + Integer.toString(i) + "'>\n";
				for(int j=0; j < APage.navi_src.get(i).size(); j++){
					if(ETNavigationDialog.choiceFormButton1 == 1){
						this.body += "      <li><a href = '" +APage.navi_link.get(i).get(j)+ "'> "+ 
							APage.navi_src.get(i).get(j) + "</a></li>\n";
					}
					else if(ETNavigationDialog.choiceFormButton1 == 2){
						this.body += "      <li><a href = '" +APage.navi_link.get(i).get(j)+ "'> "+ 
							"<img src='" + APage.navi_src.get(i).get(j) + "' /> "  +  "</a></li>\n";
					}
				}
				this.body += "</ul>\n";
			}
			this.body += "</div>\n";
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
