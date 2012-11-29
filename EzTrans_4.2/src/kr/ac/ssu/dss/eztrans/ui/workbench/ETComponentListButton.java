package kr.ac.ssu.dss.eztrans.ui.workbench;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 컴포넌트 버튼들에 대한 속성값 지정 
 * @author Complete
 *
 */
@SuppressWarnings("serial")
public class ETComponentListButton extends JToggleButton {
	
		private final Color DEFAULT_OVER_BACKGROUND_COLOR = new Color(214, 217, 223);
		private final Color ROLL_OVER_BACKGROUND_COLOR = new Color(253, 234, 218);
		private final Color SELECT_OVER_BACKGROUND_COLOR = new Color(198, 217, 241);
		
		public ETComponentListButton(String text) {
			
			super(text);
			
			setBorder(new EmptyBorder(0, 5, 0, 0));
			setHorizontalAlignment(SwingConstants.LEFT);
			setBackground(DEFAULT_OVER_BACKGROUND_COLOR);
			
			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isSelected())
						setBackground(ROLL_OVER_BACKGROUND_COLOR);
				}
				
				public void mouseExited(MouseEvent e) {
					if (!isSelected())
						setBackground(DEFAULT_OVER_BACKGROUND_COLOR);
				}
			});
		}

		public ETComponentListButton(String text, String imagePath) {
			
			super(text, new ImageIcon(imagePath));
			
			setBorder(new EmptyBorder(0, 5, 0, 0));
			setHorizontalAlignment(SwingConstants.LEFT);
			setBackground(DEFAULT_OVER_BACKGROUND_COLOR);
			
			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isSelected())
						setBackground(ROLL_OVER_BACKGROUND_COLOR);
				}
				
				public void mouseExited(MouseEvent e) {
					if (!isSelected())
						setBackground(DEFAULT_OVER_BACKGROUND_COLOR);
				}
			});
		}
		
		@Override
		public void setSelected(boolean bool) {
			super.setSelected(bool);
			if (bool)
				setBackground(SELECT_OVER_BACKGROUND_COLOR);
			else
				setBackground(DEFAULT_OVER_BACKGROUND_COLOR);
		}
}
