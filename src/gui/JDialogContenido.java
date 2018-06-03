package gui;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
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

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class JDialogContenido extends JDialog implements MouseListener, ActionListener{

	/**
	 * Atributos de la Clase
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelIz = new JPanel();
	private JPanel panelCen = new JPanel(new BorderLayout());
	private JPanel panelDetalle = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
	private JPanel panelDescarga = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
	private JPanel panelLista = new JPanel();
	private JPanel panelProgreso = new JPanel(new BorderLayout());
	private JLabel imagenLabel;
	private JLabel detalles;
	private JLabel labelDescargas;
	private JButton desInicio;
	private JButton desFin;
	private JButton descargar = new JButton("Descargar");
	private JTextField textInicio;
	private JTextField textFin;
	private JList<Aux> jLista;
	private Listado lista;
	private Dimension sizeImage = new Dimension(300, 500);
	private RenderLista render = new RenderLista();
	private int inicio, fin;
	private String titulo;
	private BusquedaContenidoManga b;
	private ProgressBar progresoPrincipal;
	private ProgressBar progresoSecundario;
	
	/**
	 * Constructor 
	 * @param modal: Ventana;
	 * @param lista: Lista de capitulos y mangas;
	 */
	public JDialogContenido (JFrame modal, Listado lista, String titulo){
		super(modal, true);
		this.lista = lista;
		this.titulo = titulo;
		setLayout(new BorderLayout());
		setTitle("OtakuWord - Mangas - " + titulo);
		
		//Paneles
		panelIzquierda();
		panelCentral(ValuesStrings.LEO_MANGA+lista.getUrl());
		panelAbajo();
		
		//Propiedades de visivilidad
		pack();
		setLocationRelativeTo(modal);
		setVisible(true);
	}

	/**
	 * Panel Izquierdo con Imagen del manga
	 * @param imagen: Portada del manga
	 */
	private void panelIzquierda() {
		imagenLabel  = new JLabel(new ImageIcon(lista.getImg().getScaledInstance(sizeImage.width, sizeImage.height, Image.SCALE_SMOOTH)));
		panelIz.add(imagenLabel);
		add(panelIz, BorderLayout.WEST);
	}
	
	/**
	 * Panel Central
	 * @param url
	 */
	private void panelCentral(String url) {
		
		//<<INICIO>> de la descripcion del manga
		b = new BusquedaContenidoManga();
		lista = b.BuscarContenido(url, lista);
		
		String detalle = lista.getDetalle();
		
		detalles = new JLabel("<html>" +detalle + "</html>");
		detalles.setPreferredSize(new Dimension(600,220));
		panelDetalle.add(detalles);
		//<<FIN>> de la descripcion del manga
		
		panelDescarga.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		
		desInicio = new JButton("Desde: ");
		desInicio.addActionListener(this);
		desFin = new JButton("Hasta:");
		desFin.addActionListener(this);
		textInicio = new JTextField(15);
		textInicio.setEnabled(false);
		textFin = new JTextField(15);
		textFin.setEnabled(false);
		descargar.addActionListener(this);
		
		panelDescarga.add(desInicio);
		panelDescarga.add(textInicio);
		panelDescarga.add(desFin);
		panelDescarga.add(textFin);
		panelDescarga.add(descargar);
		
		//<<INICIO>> de la Lista de capitulos
		jLista = new JList<Aux>();
		jLista.setListData(lista.getAux());
		jLista.setCellRenderer(render);
		
		JScrollPane scroll = new JScrollPane();
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setViewportView(jLista);
		//<<FIN>> De la lista de capitulos
		
		//<<INICIO>> de agregar los paneles al panel Central
		panelCen.add(panelDetalle, BorderLayout.NORTH);
		panelCen.add(panelDescarga, BorderLayout.CENTER);
		panelCen.add(scroll, BorderLayout.SOUTH);
		add(panelCen, BorderLayout.CENTER);
		//<<FIN>> de agregar los paneles al panel Central
	}
	
	private void panelAbajo() {
		progresoPrincipal = new ProgressBar();
		progresoSecundario = new ProgressBar();
		panelProgreso.add(progresoPrincipal, BorderLayout.SOUTH);
		panelProgreso.add(progresoSecundario, BorderLayout.CENTER);
		
		labelDescargas = new JLabel("No hay descargas");
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(labelDescargas, BorderLayout.CENTER);
		panelProgreso.add(panel2, BorderLayout.NORTH);
		
		add(panelProgreso, BorderLayout.SOUTH);
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
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(desInicio)) {
			textInicio.setText(jLista.getModel().getElementAt(jLista.getSelectedIndex()).numero);
			inicio = jLista.getSelectedIndex();
			
		}else if(e.getSource().equals(desFin)) {
			textFin.setText(jLista.getModel().getElementAt(jLista.getSelectedIndex()).numero);
			fin = jLista.getSelectedIndex();
			
		}else if(e.getSource().equals(descargar)) {
			if(inicio > fin) {
				int aux = inicio;
				inicio = fin;
				fin = aux;
			}
			
			String[] urls = new String[(fin - inicio) + 1];
			String[] capitulos = new String[(fin - inicio) + 1];
			for(int i = inicio; i <= fin; i++) {
				urls[i-inicio] = jLista.getModel().getElementAt(i).urls;
				capitulos[i-inicio] = jLista.getModel().getElementAt(i).numero;
			}
			
			//Descargas.getInstans().setVisible(true);
			b.DescargarCapitulos(urls, titulo, capitulos, progresoPrincipal, progresoSecundario, labelDescargas);
			
		}
	}
}















