package gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class ViewAnime extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension sizeMain = new Dimension(200,325);
	private Dimension sizeImage = new Dimension(200,250);
	private JLabel imagen, jNombre;
	private JPanel panelLabel = new JPanel(new FlowLayout());
	
	private String titulo;
	private String detalle;
	private String url;
	private Image img;
	
		
	
	public ViewAnime(String titulo, String detalle, String url, Image img) {
		
		this.titulo = titulo;
		this.detalle = detalle;
		this.url = url;
		this.img = img;
		
		setLayout(new BorderLayout());
		setPreferredSize(sizeMain);
		
		iniComponentes();
	}
	
	private void iniComponentes() {
		
		imagen = new JLabel(new ImageIcon(img.getScaledInstance(sizeImage.width, sizeImage.height, Image.SCALE_SMOOTH)));
		imagen.setPreferredSize(sizeImage);
		add(imagen, BorderLayout.NORTH);
		
		jNombre = new JLabel(titulo);
		panelLabel.add(jNombre);
		
		add(panelLabel, BorderLayout.CENTER);
		
	}
	
	/*
	 * 
	 * Setters and Getters 
	 * 
	 */
	
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

}
