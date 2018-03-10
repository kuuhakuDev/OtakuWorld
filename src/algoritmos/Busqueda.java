package algoritmos;

import java.util.List;

import javax.swing.JFrame;

import java.awt.Image;
import java.util.ArrayList;
import red.Conexion;
import values.ValuesStrings;
import gui.PanelView;
import recursos.Listado;

public class Busqueda {
	private Conexion c;
	private String[] codigo;
	private String fin;
	private String inicio;
	private String titulo;
	private String url;
	private String detalles;
	private String imagen;
	
	public Busqueda(String inicio, String fin, String titulo, String url, String detalles, String imagen){
		c = new Conexion();
		
		this.inicio = inicio;
		this.fin = fin;
		this.titulo = titulo;
		this.url = url;
		this.detalles = detalles;
		this.imagen = imagen;
	}
	
	public Busqueda(String inicio, String fin, String detalles) {
		c = new Conexion();
		
		this.detalles = detalles;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public Busqueda() {
		
	}
	
	
	
	public Listado[] buscarTodo(JFrame modal, String url){
		codigo = c.codigoFuente(url);
		String[] nombres = getTitulo();
		String[] detalles = getDetalles();
		String[] urls = getURL();
		Image[] imagenes = getImagenes();
		
		Listado[] lista = new Listado[nombres.length];
		
		for(int i = 0; i < nombres.length; i++) {
			lista[i] = new Listado();
			lista[i].setTitulo(nombres[i]);
			lista[i].setDetalleCorto(detalles[i]);
			lista[i].setUrl(urls[i]);
			lista[i].setImg(imagenes[i]);
		}
		
		/*PanelView[] listado = new PanelView[nombres.length];
		
		for(int i = 0; i < nombres.length; i++){
			listado[i] = new PanelView(modal, nombres[i], detalles[i], urls[i], imagenes[i]);
			listado[i].getString();
		}*/
		
		return lista;
	}
	
	public String buscarTodo (String url) {
		codigo = c.codigoFuente(url);
		return getDetalles()[0];
	}
	
	/*
	 * *****************************
	 * 			Geters
	 * *****************************
	 */
	
	private String[] getTitulo(){
		
		String[] lineas = buscarLinea(codigo, this.titulo, inicio, fin, 1);
		String[] contenido = extraerTexto(ValuesStrings.NADA, lineas, titulo, 0);
		
		return contenido;
	}
	
	private String[] getURL(){
		
		String[] lineas = buscarLinea(codigo, "href", inicio, fin, 0);
		String[] contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, "href", 0);
		
		return contenido;
	}
	
	private String[] getDetalles(){
		
		String[] lineas = buscarLinea(codigo, detalles, inicio, fin, 1);
		String[] contenido = extraerTexto(ValuesStrings.NADA, lineas, detalles, 0);
		//String[] contenido = etiquetas(lineas, "Title", 0);
		
		return contenido;
	}
	
	private Image[] getImagenes(){
		
		String[] lineas = buscarLinea(codigo, this.imagen, inicio, fin, 0);
		String[] contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, imagen, 0);
		
		Image[] imagenes = c.descargar(contenido);
		
		return imagenes;
		
	}
	
	/*
	 * *******************************
	 * 			Buscadores
	 * *******************************
	 */

	
	protected String[] buscarLinea(String[] codigo, String texto, String inicio, String fin, int salto){
		boolean entrar = false;
		boolean bucle = true;
		List<String> lista = new ArrayList<String>();
		
		for(int i = 0; i < codigo.length && bucle; i++){
			if(codigo[i].indexOf(inicio) != -1) {
				entrar = true;
			}else if(codigo[i].indexOf(fin) != -1 && entrar) {
				bucle = false;
			}
			if(entrar) {
				if(codigo[i].indexOf(texto) != -1){
					lista.add(codigo[i+salto]);
				}
			}
			
		}
		
		String[] str = listAString(lista);
		
		return str;
	}
	
	protected String[] comillas(String[] param, String ref, int index){
		String[] lista = new String[param.length];
		
		for(int i = 0; i < param.length; i++){
			int x = 0;
			lista[i] = "";
			for(int j = param[i].indexOf(ref, index); j < param[i].length() && x !=2; j++){
				if(param[i].charAt(j) == '\"'){
					x++;
				}
				else if(x == 1){
					lista[i] += param[i].charAt(j);
				}
			}
		}
		return lista;
	}
	
	protected String[] etiquetas(String[] param, String ref, int index){
		String[] lista = new String[param.length];
		
		for(int i = 0; i < param.length; i++){
			int x = 0;
			lista[i] = "";
			for(int j = param[i].indexOf(ref); j < param[i].length() && x !=2; j++){
				if(param[i].charAt(j) == '<' || param[i].charAt(j) == '>'){
					x++;
				}
				else if(x == 1){
					lista[i] += param[i].charAt(j);
				}
				//System.out.print(param[i].charAt(j));
			}
		}
		return lista;
	}
	
	protected String[] listAString(List<String> lista){
		String[] str = new String[lista.size()];
		for(int i = 0; i < lista.size(); i++){
			str[i] = lista.get(i);
		}
		return str;
	}
	
	
	protected String[] extraerTexto(String texto, String[] lineas, String ref, int index) {
		if(texto.equals(ValuesStrings.COMILLAS)) {
			return comillas(lineas, ref, index);
		}
		else if(texto.equals(ValuesStrings.ETIQUETAS)) {
			return etiquetas(lineas, ref, index);
		}
		else {
			return lineas;
		}
	}
}












