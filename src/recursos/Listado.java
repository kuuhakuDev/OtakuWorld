package recursos;

import java.awt.Image;

public class Listado {
	
	private String titulo;
	private String detalle;
	private String url;
	private Image img;
	
	public Listado(String titulo, String detalle, String url, Image img){
		this.titulo = titulo;
		this.detalle = detalle;
		this.url = url;
		this.img = img;
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
	
	

}
