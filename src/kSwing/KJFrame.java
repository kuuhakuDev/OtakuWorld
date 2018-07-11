package kSwing;

import javax.swing.JFrame;

import java.awt.Image;
import java.awt.Toolkit;


public class KJFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit tool = Toolkit.getDefaultToolkit();
	
	public KJFrame() {
		Image imagen = 	tool.getImage(getClass().getResource("/kSwing/logo.png"));
		setIconImage(imagen);
	}
}
