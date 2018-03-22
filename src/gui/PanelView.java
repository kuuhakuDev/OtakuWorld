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
import javax.swing.border.MatteBorder;

import recursos.Listado;
import values.ValuesStrings;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelView extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension sizeMain = new Dimension(200,325);
	private Dimension sizeImage = new Dimension(200,250);
	private JPanel panelLabel = new JPanel(new FlowLayout());
	private Color suave = new Color(100, 100, 100);
	private Color fuerte = new Color(60, 60, 60);
	private LineBorder bordeOut = new LineBorder(suave, 6, true);
	private LineBorder bordePress = new LineBorder(fuerte, 8, true);
	private LineBorder bordeIn = new LineBorder(fuerte, 6, true);
	//private MatteBorder bordeIn = new MatteBorder(2,2,6,6, Color.GRAY);
	
	private JLabel imagen, jNombre;
	private String titulo;
	private String detalle;
	private String url;
	private Image img;
	private Listado lista;
	private JFrame modal;
	
		
	
	public PanelView(JFrame modal,Listado lista) {
		
		
		this.lista = lista;
		this.titulo = lista.getTitulo();
		this.detalle = lista.getDetalleCorto();
		this.url = lista.getUrl();
		this.img = lista.getImg();
		this.modal = modal;
		
		setLayout(new BorderLayout());
		setBorder(bordeOut);
		addMouseListener(this);
		setPreferredSize(sizeMain);
		
		iniComponentes();
	}
	
	private void iniComponentes() {
		
		imagen = new JLabel(new ImageIcon(img.getScaledInstance(sizeImage.width, sizeImage.height, Image.SCALE_SMOOTH)));
		imagen.setPreferredSize(sizeImage);
		add(imagen, BorderLayout.NORTH);
		
		jNombre = new JLabel("<html>" + titulo + "</html>");
		panelLabel.add(jNombre);
		
		add(panelLabel, BorderLayout.CENTER);
		
	}
	
	/*
	 * 
	 * Setters and Getters 
	 * 
	 */
	protected Listado getLista() {
		return lista;
	}
	
	protected String getTitulo() {
		return titulo;
	}

	protected void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	protected String getDetalle() {
		return detalle;
	}

	protected void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	protected String getUrl() {
		return url;
	}

	protected void setUrl(String url) {
		this.url = url;
	}

	protected Image getImg() {
		return img;
	}

	protected void setImg(Image img) {
		this.img = img;
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
		new JDialogContenido(modal, lista, lista.getTitulo());
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

}
