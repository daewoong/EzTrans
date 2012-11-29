package kr.ac.ssu.dss.eztrans.data.db;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ETSqlTableModel extends AbstractTableModel {

	private Object[][] data;
	private String[] name;
	
	public ETSqlTableModel(Object[][] data, String[] name){
		this.data = data;
		this.name = name;		
	}
	
	public void setData(Object[][] data){
		this.data = data;
	}
	
	public void setName(String[] name){
		this.name = name;
	}
	
	@Override
	public int getColumnCount() {
		return name.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	@Override
	public String getColumnName(int column) {
		return name[column];
	}

}
