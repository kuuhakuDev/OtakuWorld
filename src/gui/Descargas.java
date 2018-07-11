package gui;

import javax.swing.JDialog;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Descargas extends JDialog{

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit tool = Toolkit.getDefaultToolkit();
	private Dimension size = new Dimension(tool.getScreenSize().width/4,
												tool.getScreenSize().height/3);

	private static Descargas descarga;
	private static JList<ProgressBar> lista = new JList<ProgressBar>();
	
	private Descargas() {
		
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		setTitle("Descargas");
		setSize(size);
		setLocationRelativeTo(null);
		setVisible(true);
		
	    add(lista);
	}
	
	public static Descargas getInstans() {
		
		if(descarga == null) {
			descarga = new Descargas();
		}
		
		return descarga;
	}
	
	public static void crearBarraDescarga(int total){
		
		ProgressBar barra = new ProgressBar(total);
		//listaDescargas.add(barra);
		lista.add(barra);
		
		
	}
	
	public static void aumentar(int progreso) {
		lista.getModel().getElementAt(0).aumentar(progreso);
	}
}
