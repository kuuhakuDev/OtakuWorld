package kSwing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;


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
