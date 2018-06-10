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
	private Color suave = new Color(160, 200, 255);
	private Color fuerte = new Color(200, 35, 50);
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
	
		
	
	public PanelView(JFrame modal) {
		
		/*
		this.lista = lista;
		this.titulo = lista.getTitulo();
		this.detalle = lista.getDetalleCorto();
		this.url = lista.getUrl();
		this.img = lista.getImg();
		*/
		this.modal = modal;
		
		setLayout(new BorderLayout());
		setBorder(bordeOut);
		addMouseListener(this);
		setPreferredSize(sizeMain);
		
		//iniComponentes();
		add(panelLabel, BorderLayout.CENTER);
	}
	
	private void iniComponentes() {
		
		if(img != null) {
			imagen = new JLabel(new ImageIcon(img.getScaledInstance(sizeImage.width, sizeImage.height, Image.SCALE_SMOOTH)));
			imagen.setPreferredSize(sizeImage);
			add(imagen, BorderLayout.NORTH);
		}
		
		
		jNombre = new JLabel("<html>" + titulo + "</html>");
		panelLabel.add(jNombre);
		
		add(panelLabel, BorderLayout.CENTER);
		
	}
	
	/*
	 * 
	 * Setters and Getters 
	 * 
	 */
	public Listado getLista() {
		return lista;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		jNombre = new JLabel("<html>" + titulo + "</html>");
		panelLabel.add(jNombre);
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
