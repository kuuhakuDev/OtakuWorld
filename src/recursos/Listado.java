package recursos;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Listado {
	
	private String titulo;
	private String detalle;
	private String detalleCorto;
	private String url;
	private Image img;
	private List<String> capNumero = new ArrayList<String>();
	private List<String> capNombre = new ArrayList<String>();
	private List<String> capFecha = new ArrayList<String>();
	private List<String> caps = new ArrayList<String>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	}

	public String getDetalleCorto() {
		return detalleCorto;
	}

	public void setDetalleCorto(String detalleCorto) {
		this.detalleCorto = detalleCorto;
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
		String[] urls = new String[caps.size()];
		urls = caps.toArray(urls);
		return urls;
	}
	
	public void addCapUrl(String capUrl) {
		caps.add(capUrl);
	}
	
	public Aux[] getAux(){
		Aux[] aux = new Aux[capNombre.size()];
		
		for(int i = 0; i < aux.length; i++) {
			aux[i] = new Aux(
					capNombre.get(i),
					capNumero.get(i),
					capFecha.get(i),
					caps.get(i)
					);
		}
		
		return aux;
	}
	
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
