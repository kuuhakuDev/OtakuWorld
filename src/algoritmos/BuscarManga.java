package algoritmos;

import java.awt.Image;

import manga.PanelManga;
import red.Conexion;
import values.ValuesStrings;

public class BuscarManga extends Busqueda implements Runnable{
	
	public static final String BUSQUEDA = "http://leomanga.com/buscar?s=";
	public static final String BUSQUEDA_MANGA_INICIO = "<tbody>";
	public static final String BUSQUEDA_MANGA_FIN = "</tbody>";
	public static final String BUSQUEDA_TITULO = "class=\"title-searchmanga\"";
	public static final String BUSQUEDA_URL = "window.location=";
	public static final String BUSQUEDA_IMAGEN = "onerror=\"this.src=";
	private Conexion c;
	
	public BuscarManga() {
		c = new Conexion();
	}
	
	public void actualizarCodigo2(String url) {
		url = url.replace(' ', '+');
		super.codigo = c.codigoFuente(BUSQUEDA + url);
	}
	
	public void busqueda() {
		Thread hiloBusqueda = new Thread(this);
		hiloBusqueda.start();
	}
	
	public void buscarTodo(){
		boolean entrar = true;
		
		for(int i = 0; entrar && i < 12; i++) {
			String url1 = getURL();
			System.out.println(url1);
			String img = getURLImagen();
			System.out.println(img);
			String nombre = getTitulo();
			System.out.println(nombre);
			if(nombre != "") {
				PanelManga.nuevoManga(i);
				
				PanelManga.setNombre(nombre, i);
				PanelManga.setURL(url1, i);
				PanelManga.setImage(getImagen(img), i);
				System.out.println(nombre);
				
			}else {
				entrar = false;
			}
		}
	}
	
	/*
	 * *****************************
	 * 			Geters
	 * *****************************
	 */
	
	public String getTitulo(){
		
		String lineas = buscarLinea(BUSQUEDA_TITULO, BUSQUEDA_MANGA_INICIO, BUSQUEDA_MANGA_FIN, 0);
		String contenido = extraerTexto(ValuesStrings.ETIQUETAS, lineas, BUSQUEDA_TITULO, 0);
		
		return contenido;
	}
	
	public String getURL(){
		
		String lineas = buscarLinea(BUSQUEDA_URL, BUSQUEDA_MANGA_INICIO, BUSQUEDA_MANGA_FIN, 0);
		String contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, BUSQUEDA_URL, 0);
		
		return contenido;
	}
	/*
	public String getPuntuacion() {
		
		String lineas = buscarLinea(this.puntuacion, BUSQUEDA_MANGA_INICIO, BUSQUEDA_MANGA_FIN, 0);
		String contenido = extraerTexto(ValuesStrings.ETIQUETAS, lineas, puntuacion, 0);
		
		return contenido;
	}*/
	
	private Image getImagen(String lineas){
		String contenido = extraerTexto(ValuesStrings.COMILLAS_SIMPLES, lineas, BUSQUEDA_IMAGEN, 0);
		
		Image imagenes = c.descargar(contenido, "Manga");
		
		return imagenes;
		
	}
	
	public String getURLImagen() {
		return buscarLinea(BUSQUEDA_IMAGEN, BUSQUEDA_MANGA_INICIO, BUSQUEDA_MANGA_FIN, 0); 
	}

	
	public void run() {
		buscarTodo();
	}
}






