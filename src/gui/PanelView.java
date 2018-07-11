package gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import algoritmos.FormatText;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class PanelView extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension sizeMain = new Dimension(200,325);
	private Dimension sizeImage = new Dimension(200,250);
	private JPanel panelLabel = new JPanel(new FlowLayout());
	private Color suave = new Color(160, 200, 255);
	private Color fuerte = new Color(200, 35, 50);
	private LineBorder bordeOut = new LineBorder(suave, 6, true);
	private LineBorder bordePress = new LineBorder(fuerte, 8, true);
	private LineBorder bordeIn = new LineBorder(fuerte, 6, true);
	private JLabel imagen, jNombre;
	
	private String titulo;
	private String detalleCorto;
	private String detalle;
	private String url;
	private Image img;
	private List<String> capNumero = new ArrayList<String>();
	private List<String> capNombre = new ArrayList<String>();
	private List<String> capFecha = new ArrayList<String>();
	private List<String> capsURl = new ArrayList<String>();
	
	//private Listado lista;
	private JFrame modal;
	
		
	
	public PanelView(JFrame modal) {

		this.modal = modal;
		
		setLayout(new BorderLayout());
		setBorder(bordeOut);
		addMouseListener(this);
		setPreferredSize(sizeMain);
		
		//iniComponentes();
		add(panelLabel, BorderLayout.CENTER);
	}
	
	/*
	 ************************
	 * Setters and Getters 
	 ************************
	 */
	/*public Listado getLista() {
		return lista;
	}*/
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		jNombre = new JLabel("<html>" + FormatText.utf8(titulo)+ "</html>");
		panelLabel.add(jNombre);
	}

	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = FormatText.utf8(detalle);
	}
	
	public String getDetalleCorto() {
		return detalleCorto;
	}

	public void setDetalleCorto(String detalleCorto) {
		this.detalleCorto = FormatText.utf8(detalleCorto);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
		imagen = new JLabel(new ImageIcon(img.getScaledInstance(sizeImage.width, sizeImage.height, Image.SCALE_SMOOTH)));
		imagen.setPreferredSize(sizeImage);
		add(imagen, BorderLayout.NORTH);
	}
	
	
	public String[] getCapNumero() {
		String[] numeros = new String[capNumero.size()];
		numeros = capNumero.toArray(numeros);
		return numeros;
	}

	public void addCapNumero(String numero) {
		capNumero.add(numero);
	}

	public String[] getCapNombre() {
		String[] nombres = new String[capNombre.size()];
		nombres = capNombre.toArray(nombres);
		return nombres;
	}

	public void addCapNombre(String nombre) {
		capNombre.add(nombre);
	}

	public String[] getCapFecha() {
		String[] fechas = new String[capFecha.size()];
		fechas = capFecha.toArray(fechas);
		return fechas;
	}

	public void addCapFecha(String fecha) {
		capFecha.add(fecha);
	}
	
	public String[] getCapUrl() {
		String[] urls = new String[capsURl.size()];
		urls = capsURl.toArray(urls);
		return urls;
	}
	
	public void addCapUrl(String capUrl) {
		capsURl.add(capUrl);
	}
	
	public Aux[] getAux(){
		Aux[] aux = new Aux[capNombre.size()];
		
		for(int i = 0; i < aux.length; i++) {
			aux[i] = new Aux(
					capNombre.get(i),
					capNumero.get(i),
					capFecha.get(i),
					capsURl.get(i)
					);
		}
		
		return aux;
	}
	
	public String getString() {
		return titulo + ", " + detalle + ", " + url;
	}
	
	/*
	 * ********************************************
	 * **************Eventos de Mouse**************
	 * ********************************************
	 */

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(getString());
		new JDialogContenido(modal, this, getTitulo());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setBorder(bordeIn);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setBorder(bordeOut);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setBorder(bordePress);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setBorder(bordeIn);
	}
	
	
	//////////////////////////////////
	/////////Clase Auxiliar///////////
	//////////////////////////////////
	public class Aux{
		
		public String nombre;
		public String numero;
		public String fecha;
		public String urls;
		
		public Aux(String nombre, String numero, String fecha, String caps) {
			
			this.nombre = nombre;
			this.numero = numero;
			this.fecha = fecha;
			this.urls = caps;
		}
		
	}

}
