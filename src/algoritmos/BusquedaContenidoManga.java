package algoritmos;

import java.util.List;

import javax.swing.JLabel;

import gui.ProgressBar;
import recursos.Listado;
import red.Conexion;
import values.ValuesStrings;

import java.util.ArrayList;

public class BusquedaContenidoManga extends Busqueda{

	private static final String DETALLE_CONTENIDO_MANGA = "class=\"text-justify\"";
	private static final String LISTA_CONTENIDO_MANGA = "class=\"cap-name\"";
	private static final String CAPITULO_LISTA_MANGA = "href=\"/manga";
	private static final String NOMBRE_LISTA_MANGA = "class=\"cap-name\"";
	private static final String FECHA_LISTA_MANGA = "class=\"right-date\"";
	private static final String URL_LISTA_MANGA = "href=";
	private static final String IMAGEN_LISTA_MANGA = "src=";
	private static final String URL_CONTENIDO_CAPITULO = "class=\"btn btn-material-blue-600 cap-option\"";
	private static final String URL_CONTENIDO_CAPITULO_IMAGEN = "class=\"cap-images\"";
	private static final String detallesIni = "id=\"page-manga\"";
	private static final String detallesFin = "class=\"pub-desktop pub-300 pub-cap\"";
	private static final String listaIni = "class=\"list-unstyled ul-chapter \"";
	private static final String listaFin = "id=\"disqus_thread\"";
	private static final String capIni = "class=\"col-sm-4\"";
	private static final String capFin = "</div>";
	private static final String imagenesIni = "id=\"cascade-images\"";
	private static final String imagenesFin = "class=\"viewcap-info\"";
	
	private Conexion c = new Conexion();
	private String[] codigo;
	
	private String detalle;
	private List<String[]> contenido = new ArrayList<String[]>();
	
	
	public Listado BuscarContenido(String link, Listado lista) {
		
		codigo = c.codigoFuente(link);
		
		getDetalles(lista);
		getContenido(lista);
		
		
		
		return lista;
	}
	
	private void getDetalles(Listado lista){
		
		String[] lineas = buscarLinea(codigo, DETALLE_CONTENIDO_MANGA, detallesIni, detallesFin, 1);
		lista.setDetalle(FormatText.utf8(extraerTexto(ValuesStrings.NADA, lineas, DETALLE_CONTENIDO_MANGA, 0)[0]));
		
	}

	
	private void getContenido(Listado lista) {
		
			String[] lineas = buscarLinea(codigo, LISTA_CONTENIDO_MANGA, listaIni, listaFin, 0);
			String[] cap = extraerTexto(ValuesStrings.ETIQUETAS, lineas, CAPITULO_LISTA_MANGA, 0);
			String[] nombre = extraerTexto(ValuesStrings.ETIQUETAS, lineas, NOMBRE_LISTA_MANGA, 0);
			String[] fecha = extraerTexto(ValuesStrings.ETIQUETAS, lineas, FECHA_LISTA_MANGA, 0);
			String[] urls = extraerTexto(ValuesStrings.COMILLAS, lineas, URL_LISTA_MANGA, 0);
			
			for(int i = 0; i < cap.length; i++) {
				lista.addCapNumero(FormatText.utf8(cap[i]));
				lista.addCapNombre(FormatText.utf8(nombre[i]));
				lista.addCapFecha(FormatText.utf8(fecha[i]));
				lista.addCapUrl(FormatText.utf8("http://www.leomanga.com" + urls[i]));
				System.out.println(i);
			}
	}
	
	public void DescargarCapitulos(String[] urls, String titulo, String[] capitulo, ProgressBar progreso1, ProgressBar progreso2, JLabel label) {
		String[] codigo = null;
		String[] lineas = null;
		String[] urls1 = null;
		List<String[]> urlLista = new ArrayList<String[]>();
		for(int i = 0; i < urls.length; i++) {
			codigo = c.codigoFuente(urls[i]);
			lineas = buscarLinea(codigo, URL_CONTENIDO_CAPITULO, capIni, capFin, 0);
			urls1 = extraerTexto(ValuesStrings.COMILLAS, lineas, URL_LISTA_MANGA, 0);
			codigo = c.codigoFuente("http://leomanga.com" + urls1[0]);
			lineas = buscarLinea(codigo, URL_CONTENIDO_CAPITULO_IMAGEN, imagenesIni, imagenesFin, 0);
			urlLista.add(extraerTexto(ValuesStrings.COMILLAS, lineas, IMAGEN_LISTA_MANGA, 0));
		}
		new Hilo(urlLista, titulo, capitulo, progreso1, progreso2, label);
	}
	
	class Hilo extends Thread{
		
		private List<String[]> urls;
		private String titulo;
		private String[] capitulo;
		private ProgressBar progreso, progreso2;
		private JLabel label;
		
		public void run() {
			c.descargarMangas(urls, titulo, capitulo, progreso, progreso2, label);
		}
		
		public Hilo(List<String[]> lista, String titulo, String[] capitulo, ProgressBar progreso, ProgressBar progreso2, JLabel label) {
			this.urls = lista;
			this.titulo = titulo;
			this.capitulo = capitulo;
			this.progreso = progreso;
			this.progreso2 = progreso2;
			this.label = label;
			start();
		}
		
	}
}
