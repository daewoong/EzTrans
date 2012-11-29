package kr.ac.ssu.dss.eztrans.generator;

import kr.ac.ssu.dss.eztrans.ui.workbench.editor.ETEditorPart;

public class ETSaveText {
	
	public ETEditorPart editorPart;
	//»ý¼ºÀÚ
	public ETSaveText(ETEditorPart editorPart){
		this.editorPart = editorPart;
		String intext = editorPart.tagEditingArea.getText();
		this.editorPart.sb = new StringBuffer();
		this.editorPart.getStringBuffer().append(intext);
	}
}
