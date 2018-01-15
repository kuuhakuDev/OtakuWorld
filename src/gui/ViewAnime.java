package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

public class ViewAnime extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension sizeMain = new Dimension(200,325);
	
	public ViewAnime() {
		
		setPreferredSize(sizeMain);
		setBackground(Color.BLUE);
		
	}

}
