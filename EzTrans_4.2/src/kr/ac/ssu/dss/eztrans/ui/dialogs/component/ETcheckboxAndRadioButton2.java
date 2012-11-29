package kr.ac.ssu.dss.eztrans.ui.dialogs.component;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class ETcheckboxAndRadioButton2 implements ItemListener{
	
	private JRadioButton btn1;
	private JRadioButton btn2;
	private JRadioButton btn3;
	
	private JButton but1;
	private JButton but2;
	
	public ETcheckboxAndRadioButton2(JRadioButton b1, JRadioButton b2, JRadioButton b3)
	{
		btn1 = b1;
		btn2 = b2;
		btn3 = b3;
	}
			
	public ETcheckboxAndRadioButton2(JRadioButton b1, JRadioButton b2)
	{
		btn1 = b1;
		btn2 = b2;
		btn3 = null;
	}
	
	public ETcheckboxAndRadioButton2(JButton b1, JButton b2)
	{
		but1 = b1;
		but2 = b2;
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
			}
		}
		else
		{
			btn1.setEnabled(false);
			btn2.setEnabled(false);
			
			if(btn3 != null)
			{
				btn3.setEnabled(false);
			}
			
		}
	}
	

	
}


