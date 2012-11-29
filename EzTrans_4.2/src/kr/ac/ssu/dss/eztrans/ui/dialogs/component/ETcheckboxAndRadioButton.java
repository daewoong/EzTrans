package kr.ac.ssu.dss.eztrans.ui.dialogs.component;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

public class ETcheckboxAndRadioButton implements ItemListener{
	
	private JRadioButton btn1;
	private JRadioButton btn2;
	private JRadioButton btn3;
	private JRadioButton btn4;
	private JRadioButton btn5;
	private JRadioButton btn6;
	private JRadioButton btn7;
	private JRadioButton btn8;
	private JRadioButton btn9;
	private JRadioButton btn10;
	
	
	
	public ETcheckboxAndRadioButton(JRadioButton b1, JRadioButton b2,JRadioButton b3,JRadioButton b4,JRadioButton b5,JRadioButton b6,JRadioButton b7,JRadioButton b8,JRadioButton b9,JRadioButton b10)
	{
		btn1 = b1;
		btn2 = b2;
		btn3 = b3;
		btn4 = b4;
		btn5 = b5;
		btn6 = b6;
		btn7 = b7;
		btn8 = b8;
		btn9 = b9;
		btn10 = b10;
	}
			
	public ETcheckboxAndRadioButton(JRadioButton b1, JRadioButton b2)
	{
		btn1 = b1;
		btn2 = b2;
		btn3 = null;
		btn4 = null;
		btn5 = null;
		btn6 = null;
		btn7 = null;
		btn8 = null;
		btn9 = null;
		btn10 = null;
	}
	
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getStateChange()==ItemEvent.SELECTED)
		{
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			
			if(btn3 != null)
			{
				btn3.setEnabled(true);
				btn4.setEnabled(true);
				btn5.setEnabled(true);
				btn6.setEnabled(true);
				btn7.setEnabled(true);
				btn8.setEnabled(true);
				btn9.setEnabled(true);
				btn10.setEnabled(true);
			}
		}
		else
		{
			btn1.setEnabled(false);
			btn2.setEnabled(false);
			
			if(btn3 != null)
			{
				btn3.setEnabled(false);
				btn4.setEnabled(false);
				btn5.setEnabled(false);
				btn6.setEnabled(false);
				btn7.setEnabled(false);
				btn8.setEnabled(false);
				btn9.setEnabled(false);
				btn10.setEnabled(false);
			}
			
		}
	}
	

	
}


