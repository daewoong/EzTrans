/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 * VisualizePanel.java
 * Copyright (C) 2007 University of Waikato, Hamilton, New Zealand
 */

package kr.ac.ssu.dss.eztrans.data.mining;

import weka.core.Instances;
import weka.gui.explorer.Messages;
import weka.gui.visualize.MatrixPanel;

import java.awt.BorderLayout;

import kr.ac.ssu.dss.eztrans.data.mining.ETWekaExplorer.ExplorerPanel;

/**
 * A slightly extended MatrixPanel for better support in the Explorer.
 * 
 * @author fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 7059 $
 * @see MatrixPanel
 */
public class ETWekaVisualizePanel extends MatrixPanel implements ExplorerPanel {

	/** for serialization */
	private static final long serialVersionUID = 6084015036853918846L;

	/** the parent frame */
	protected ETWekaExplorer m_Explorer = null;

	/**
	 * Sets the Explorer to use as parent frame (used for sending notifications
	 * about changes in the data)
	 * 
	 * @param parent
	 *            the parent frame
	 */
	public void setExplorer(ETWekaExplorer parent) {
		m_Explorer = parent;
	}

	/**
	 * returns the parent Explorer frame
	 * 
	 * @return the parent
	 */
	public ETWekaExplorer getExplorer() {
		return m_Explorer;
	}

	/**
	 * Returns the title for the tab in the Explorer
	 * 
	 * @return the title of this tab
	 */
	public String getTabTitle() {
		return Messages.getInstance().getString(
				"VisualizePanel_GetTabTitle_Text");
	}

	/**
	 * Returns the tooltip for the tab in the Explorer
	 * 
	 * @return the tooltip of this tab
	 */
	public String getTabTitleToolTip() {
		return Messages.getInstance().getString(
				"VisualizePanel_GetTabTitleToolTip_Text");
	}

	/**
	 * Tests out the visualize panel from the command line.
	 * 
	 * @param args
	 *            may optionally contain the name of a dataset to load.
	 */
	public static void main(String[] args) {

		try {
			final javax.swing.JFrame jf = new javax.swing.JFrame(Messages
					.getInstance().getString("VisualizePanel_Main_JFrame_Text"));
			jf.getContentPane().setLayout(new BorderLayout());
			final ETWekaVisualizePanel sp = new ETWekaVisualizePanel();
			jf.getContentPane().add(sp, BorderLayout.CENTER);
			jf.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					jf.dispose();
					System.exit(0);
				}
			});
			jf.pack();
			jf.setSize(800, 600);
			jf.setVisible(true);
			if (args.length == 1) {
				System.err.println(Messages.getInstance().getString(
						"VisualizePanel_Main_Error_Text")
						+ args[0]);
				java.io.Reader r = new java.io.BufferedReader(
						new java.io.FileReader(args[0]));
				Instances i = new Instances(r);
				sp.setInstances(i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
	}
}
