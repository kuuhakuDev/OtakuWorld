package gui;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import algoritmos.Busqueda;
import algoritmos.BusquedaContenidoManga;
import algoritmos.FormatText;
import recursos.Listado;
import recursos.Listado.Aux;
import values.ValuesStrings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class JDialogContenido extends JDialog implements MouseListener{

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
	private Listado lista;
	private RenderLista render = new RenderLista();
	
	public JDialogContenido (JFrame modal, Listado lista){
		super(modal, true);
		
		this.lista = lista;
		
		setLayout(new BorderLayout());
		
		panelIzquierda(lista.getImg());
		panelCentral(ValuesStrings.LEO_MANGA+lista.getUrl());
		
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
		lista = b.BuscarContenido(url, lista);
		
		String detalle = lista.getDetalle();
		
		detalles = new JLabel("<html>" +FormatText.utf8(detalle) + "</html>");
		detalles.setPreferredSize(new Dimension(600,250));
		panelDetalle.add(detalles);
		
		String[] numeros = lista.getCapNumero();
		String[] nombres = lista.getCapNombre();
		String[] fechas = lista.getCapFecha();
		
		/*panelLista.setLayout(new GridLayout(nombres.length, 3, 0, 5));
		for(int i = 0; i < nombres.length; i++) {
			JLabel label1 = new JLabel(FormatText.utf8(numeros[i]));
			JLabel label2 = new JLabel(FormatText.utf8(nombres[i]));
			JLabel label3 = new JLabel(FormatText.utf8(fechas[i]));
			
			label1.setBorder(new javax.swing.border.MatteBorder(1,0,0,0,Color.BLACK));
			label1.addMouseListener(this);
			label2.setBorder(new javax.swing.border.MatteBorder(1,0,0,0,Color.BLACK));
			label2.addMouseListener(this);
			label3.setBorder(new javax.swing.border.MatteBorder(1,0,0,0,Color.BLACK));
			label3.addMouseListener(this);
			
			panelLista.add(label1);
			panelLista.add(label2);
			panelLista.add(label3);
		}*/
		
		JList<Aux> jLista = new JList<Aux>();
		jLista.setListData(lista.getAux());
		jLista.setCellRenderer(render);
		
		
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		//scroll.add(panelLista);
		scroll.setViewportView(jLista);
		
		panelCen.add(panelDetalle, BorderLayout.NORTH);
		panelCen.add(scroll, BorderLayout.CENTER);
		
		add(panelCen, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < panelLista.getComponentCount(); i++) {
			e.getComponent().setBackground(Color.BLUE);
			if(e.equals(panelLista.getComponents()[i])) {
				System.out.println("Estoy dentro en la primera condicional = "+ i);
				panelLista.getComponents()[i].setBackground(Color.BLUE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
