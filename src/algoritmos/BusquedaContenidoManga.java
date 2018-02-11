package algoritmos;

import java.util.List;

import red.Conexion;
import values.ValuesStrings;

import java.util.ArrayList;

public class BusquedaContenidoManga extends Busqueda{

	private static final String DETALLE_CONTENIDO_MANGA = "class=\"text-justify\"";
	private static final String LISTA_CONTENIDO_MANGA = "class=\"cap-name\"";
	private static final String CAPITULO_LISTA_MANGA = "href=\"/manga";
	private static final String NOMBRE_LISTA_MANGA = "class=\"cap-name\"";
	private static final String FECHA_LISTA_MANGA = "class=\"right-date\"";
	private static final String detallesIni = "id=\"page-manga\"";
	private static final String detallesFin = "class=\"pub-desktop pub-300 pub-cap\"";
	private static final String listaIni = "class=\"list-unstyled ul-chapter \"";
	private static final String listaFin = "id=\"disqus_thread\"";
	
	private Conexion c = new Conexion();
	private String[] codigo;
	
	private String detalle;
	private List<String[]> lista = new ArrayList<String[]>();
	private Object[] contenido = new Object[3];
	
	
	public Object[] BuscarContenido(String link) {
		
		codigo = c.codigoFuente(link);
		
		getDetalles();
		getLista();
		
		contenido[0] = detalle;
		contenido[1] = lista;
		
		return contenido;
	}
	
	private void getDetalles(){
		
		String[] lineas = buscarLinea(codigo, DETALLE_CONTENIDO_MANGA, detallesIni, detallesFin, 1);
		detalle = extraerTexto(ValuesStrings.NADA, lineas, DETALLE_CONTENIDO_MANGA, 0)[0];
		
	}

	
	private List<String[]> getLista() {
		
			String[] lineas = buscarLinea(codigo, LISTA_CONTENIDO_MANGA, listaIni, listaFin, 0);
			String[] cap = extraerTexto(ValuesStrings.ETIQUETAS, lineas, CAPITULO_LISTA_MANGA, 0);
			String[] nombre = extraerTexto(ValuesStrings.ETIQUETAS, lineas, NOMBRE_LISTA_MANGA, 0);
			String[] fecha = extraerTexto(ValuesStrings.ETIQUETAS, lineas, FECHA_LISTA_MANGA, 0);
			
			for(int i = 0; i < cap.length; i++) {
				String[] aux = new String[3];
				aux [0] = cap[i];
				aux [1] = nombre[i];
				aux [2] = fecha[i];
				lista.add(aux);
			}
		
		return lista;
	}
}
