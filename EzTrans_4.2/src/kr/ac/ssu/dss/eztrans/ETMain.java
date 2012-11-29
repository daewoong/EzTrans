/**
 * 
 */
package kr.ac.ssu.dss.eztrans;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kr.ac.ssu.dss.eztrans.contants.ETIconPaths;
import kr.ac.ssu.dss.eztrans.ui.workbench.ETWorkbench;


/**
 * EzTrans 구동을 위한 Main 
 * @author Complete
 *
 */
public class ETMain {

	@SuppressWarnings("unused")
	private static final String WINDOW_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private static final String NIMBUS_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		
	public static void main(String[] args) {
		
		Window splash = createSplash();
		splash.setVisible(true);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				launchEzTrans();
			}			
		});
		
		splash.dispose();
	}
	
	private static void launchEzTrans() {
		
		setLookAndFeel();	
		Window workbench = new ETWorkbench();	
		workbench.setVisible(true);
	}
	
	private static Window createSplash() {
		
		JWindow splash = new JWindow();	
		Icon icon = new ImageIcon(ETIconPaths.SPLASH);
		
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		splash.setBounds((screenSize.width - iconWidth) >> 1, (screenSize.height - iconHeight) >> 1, iconWidth, iconHeight);
		splash.getContentPane().add(new JLabel(icon));
		
		return splash;
	}
	
	private static void setLookAndFeel() {
		
		try {
			UIManager.setLookAndFeel(NIMBUS_LOOK_AND_FEEL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
