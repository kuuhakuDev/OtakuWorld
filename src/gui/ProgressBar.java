package gui;

import javax.swing.JProgressBar;

public class ProgressBar extends JProgressBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;

	/**
	 * 
	 * @param total = es el total de objetos para hacer el 100%
	 */
	public ProgressBar(int total) {
		
		this.total = total;
		setValue(0);
		setStringPainted(true);
		
	}
	
	public ProgressBar() {
		setValue(0);
		setStringPainted(true);
	}
	
	public void aumentar(int progreso) {
		
		int set = (int)(progreso * 100) / total;
		setValue(set);
		
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void resetear() {
		setValue(0);
	}
}
