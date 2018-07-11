package algoritmos;

import java.util.List;

import javax.swing.JLabel;

import gui.PanelView;
import gui.ProgressBar;
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
	
	public void BuscarContenido(String link, PanelView lista) {
		
		super.codigo = c.codigoFuente(link);
		getDetalles(lista);
		getContenido(lista);
		
	}
	
	private void getDetalles(PanelView lista){
		
		super.continuar = 0;
		super.entrar = false;
		
		String lineas = buscarLinea(DETALLE_CONTENIDO_MANGA, detallesIni, detallesFin, 1);
		lista.setDetalle(FormatText.utf8(extraerTexto(ValuesStrings.NADA, lineas, DETALLE_CONTENIDO_MANGA, 0)));
		
	}

	
	private void getContenido(PanelView lista) {
		super.continuar = 0;
		super.entrar = false;
		
		List<String> lineas = new ArrayList<String>();
		boolean salir = false;

		do {
			String linea = buscarLinea(LISTA_CONTENIDO_MANGA, listaIni, listaFin, 0);
			if(linea != "") {
				continuar += 1;
				lineas.add(linea);
			}else {
				salir = true;
			}
			System.out.println(linea);
		}while(!salir);
		System.out.println("2");
			for(int i = 0; i < lineas.size(); i++) {
				System.out.println("3");
				lista.addCapNumero(FormatText.utf8(extraerTexto(ValuesStrings.ETIQUETAS, lineas.get(i), CAPITULO_LISTA_MANGA, 0)));
				lista.addCapNombre(FormatText.utf8(extraerTexto(ValuesStrings.ETIQUETAS, lineas.get(i), NOMBRE_LISTA_MANGA, 0)));
				lista.addCapFecha(FormatText.utf8(extraerTexto(ValuesStrings.ETIQUETAS, lineas.get(i), FECHA_LISTA_MANGA, 0)));
				lista.addCapUrl(FormatText.utf8("http://www.leomanga.com" + extraerTexto(ValuesStrings.COMILLAS, lineas.get(i), URL_LISTA_MANGA, 0)));
				System.out.println(i);
			}
	}
	
	public void DescargarCapitulos(String[] urls, String titulo, String[] capitulo, ProgressBar progreso1, ProgressBar progreso2, JLabel label) {
		String[] codigo = new String[0];
		String linea = null;
		String urls1 = null;
		List<String[]> urlLista = new ArrayList<String[]>();
		System.out.println("1");
		for(int i = 0; i < urls.length; i++) {
			super.continuar = 0;
			super.entrar = false;
			boolean boo = true;
			super.codigo = c.codigoFuente(urls[i]);
			
			linea = buscarLinea(URL_CONTENIDO_CAPITULO, capIni, capFin, 0);
			urls1 = extraerTexto(ValuesStrings.COMILLAS, linea, URL_LISTA_MANGA, 0);
			super.codigo = c.codigoFuente("http://leomanga.com" + urls1);
			super.entrar = false;
			List<String> lineas = new ArrayList<String>();
			System.out.println("2");
			int x = 0;
			do {
				continuar = x+1;
				linea = buscarLinea(URL_CONTENIDO_CAPITULO_IMAGEN, imagenesIni, imagenesFin, 0);
				if(linea != "") {
					lineas.add(extraerTexto(ValuesStrings.COMILLAS, linea, IMAGEN_LISTA_MANGA, 0));
					System.out.println("3");
				}
				else {
					boo=false;
					System.out.println("4");
				}
				x = continuar;
			}while(boo);
			System.out.println("5");
			urlLista.add(lineas.toArray(codigo));
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
