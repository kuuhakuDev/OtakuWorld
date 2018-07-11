package manga;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.Descargas;
import kSwing.KJFrame;

public class VentanaManga extends KJFrame implements ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaManga manga;
	private JScrollPane scroll = new JScrollPane();
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private float x = (float) (tool.getScreenSize().width*0.8);
	private float y = (float) (tool.getScreenSize().height*0.8);
	private Dimension sizeFrame = new Dimension((int)x,(int)y);
	private PanelManga panelManga;
	private JPanel panelNort = new JPanel();
	private JLabel labelBusqueda;
	private JTextField textBusqueda;
	private JButton buttonBusqueda;
	

	private VentanaManga() {
		
		setLayout(new BorderLayout());
		addComponentListener(this);
		setTitle("OtakuWorld-Manga");
		setSize(sizeFrame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		norte();
		setVisible(true);
		Descargas.getInstans().setVisible(false);
		centro();
		
		
	}
	
	public static VentanaManga getInstans() {
		if(manga == null) {
			manga = new VentanaManga();
			return manga;
		}else {
			return manga;
		}
		
	}
	
	private void norte() {
		labelBusqueda = new JLabel("Buscar: ");
		textBusqueda = new JTextField();
		buttonBusqueda = new JButton("Buscar");
		/*panelNort.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelNort.add(labelBusqueda);
		panelNort.add(textBusqueda);
		panelNort.add(buttonBusqueda);*/
		panelNort.setLayout(new BorderLayout(10,5));
		panelNort.setBorder(new EmptyBorder(10,10,10,10));
		panelNort.add(labelBusqueda, BorderLayout.WEST);
		panelNort.add(textBusqueda, BorderLayout.CENTER);
		panelNort.add(buttonBusqueda, BorderLayout.EAST);
		add(panelNort, BorderLayout.NORTH);
	}
	
	private void centro() {
		panelManga = new PanelManga(this);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.getViewport().add(panelManga);
		add(scroll, BorderLayout.CENTER);
	}
	
	/*
	 *******************************************
	 * 		Interface Component Listener
	 *******************************************
	 */

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		PanelManga.size(this);
		//Aqui arriba se ha cambiado. 
		//En caso de fallo el codigo anterior era "panelManga.size(this);"
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
