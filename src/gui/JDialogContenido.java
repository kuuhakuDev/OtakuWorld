package gui;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import algoritmos.Busqueda;
import algoritmos.BusquedaContenidoManga;
import values.ValuesStrings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class JDialogContenido extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelIz = new JPanel();
	private JPanel panelCen = new JPanel(new BorderLayout());
	private JPanel panelDetalle = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
	private JPanel panelLista = new JPanel();
	private JLabel imagenLabel;
	private JLabel detalles;
	
	public JDialogContenido (JFrame modal,String url, Image imagen){
		super(modal, true);
		
		setLayout(new BorderLayout());
		
		panelIzquierda(imagen);
		panelCentral(url);
		
		pack();
		setLocationRelativeTo(modal);
		setVisible(true);
	}

	private void panelIzquierda(Image imagen) {
		imagenLabel  = new JLabel(new ImageIcon(imagen.getScaledInstance(300, 500, Image.SCALE_SMOOTH)));
		panelIz.add(imagenLabel);
		add(panelIz, BorderLayout.WEST);
	}
	
	private void panelCentral(String url) {
		//Busqueda b = new Busqueda("id=\"page-manga\"", "class=\"pub-desktop pub-300 pub-cap\"", ValuesStrings.DETALLE_CONTENIDO_MANGA);
		BusquedaContenidoManga b = new BusquedaContenidoManga();
		Object[] contenido = b.BuscarContenido(url);
		
		String detalle = (String) contenido[0];
		List<String[]> lista = (List<String[]>) contenido[1];
		
		detalles = new JLabel("<html>" + detalle + "</html>");
		detalles.setPreferredSize(new Dimension(600,250));
		panelDetalle.add(detalles);
		
		panelLista.setLayout(new GridLayout(lista.size(), 3, 5, 5));
		for(int i = 0; i < lista.size(); i++) {
			panelLista.add(new JLabel("<html>" + lista.get(i)[0] + "</html>"));
			panelLista.add(new JLabel("<html>" + lista.get(i)[1] + "</html>"));
			panelLista.add(new JLabel("<html>" + lista.get(i)[2] + "</html>"));
		}
		
		ScrollPane scroll = new ScrollPane();
		scroll.add(panelLista);
		
		panelCen.add(panelDetalle, BorderLayout.NORTH);
		panelCen.add(scroll, BorderLayout.CENTER);
		
		add(panelCen, BorderLayout.CENTER);
	}
}
