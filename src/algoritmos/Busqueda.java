package algoritmos;

import java.util.List;

import javax.swing.JFrame;

import java.awt.Image;
import java.util.ArrayList;
import red.Conexion;
import values.ValuesStrings;
import gui.PanelView;
import manga.PanelManga;
import recursos.Listado;

public class Busqueda implements Runnable{
	private Conexion c;
	private String[] codigo;
	private String fin;
	private String inicio;
	private String titulo;
	private String url;
	private String detalles;
	private String imagen;
	private String puntuacion;
	private Thread hilo;
	private String link;
	private List<Listado> listado = new ArrayList<Listado>();
	private int continuar = 0;
	boolean entrar = false;
	
	public Busqueda(String inicio, String fin, String titulo, String url, String detalles, String imagen, String puntuacion){
		c = new Conexion();
		
		this.inicio = inicio;
		this.fin = fin;
		this.titulo = titulo;
		this.url = url;
		this.detalles = detalles;
		this.imagen = imagen;
		this.puntuacion = puntuacion;
	}
	
	public Busqueda(String inicio, String fin, String detalles) {
		c = new Conexion();
		
		this.detalles = detalles;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public Busqueda() {
		
	}
	
	public void buscarLista(String url) {
		link = url;
		hilo = new Thread(this);
		codigo = c.codigoFuente(url);
		hilo.start();
	}
	
	public void buscarTodo(String url){
		boolean entrar = true;
		
		for(int i = 0; entrar && i < 8; i++) {
			String img = getURLImagen();
			String nombre = getTitulo(i);
			if(nombre != "") {
				
				//listado.add(new Listado());
				PanelManga.nuevoManga(i);
				
				PanelManga.setNombre(nombre, i);
				//listado.get(i).setDetalleCorto(getDetalles(i));
				//listado.get(i).setUrl(getURL(i));
				PanelManga.setImage(getImagen(img), i);
				
				System.out.println(nombre);
				
			}else {
				entrar = false;
			}
		}
		
		/*****************
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
		
	}
	
	public void actualizarCodigo(String url){
		codigo = c.codigoFuente(url);
	}
	
	/*public String buscarTodo () {
		return getDetalles();
	}*/
	
	/*
	 * *****************************
	 * 			Geters
	 * *****************************
	 */
	
	public String getTitulo(int index){
		
		String lineas = buscarLinea(this.titulo, inicio, fin, 1);
		String contenido = extraerTexto(ValuesStrings.NADA, lineas, titulo, 0);
		
		return contenido;
	}
	
	public String getURL(int index){
		
		String lineas = buscarLinea(url, inicio, fin, 0);
		String contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, "href", 0);
		
		return contenido;
	}
	
	public String getDetalles(int index){
		
		String lineas = buscarLinea(detalles, inicio, fin, 1);
		String contenido = extraerTexto(ValuesStrings.NADA, lineas, detalles, 0);
		
		return contenido;
	}
	
	public String getPuntuacion(int index) {
		
		String lineas = buscarLinea(this.puntuacion, inicio, fin, 0);
		String contenido = extraerTexto(ValuesStrings.ETIQUETAS, lineas, puntuacion, 0);
		
		return contenido;
	}
	
	private Image getImagen(String lineas){
		String contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, imagen, 0);
		
		Image imagenes = c.descargar(contenido);
		
		return imagenes;
		
	}
	
	public String getURLImagen() {
		return buscarLinea(this.imagen, inicio, fin, 0); 
	}
	
	/*
	 * *******************************
	 * 			Buscadores
	 * *******************************
	 */

	
	public String buscarLinea(String texto, String inicio, String fin, int salto){
		boolean bucle = true;
		String linea = "";
		for(int i = continuar; i < codigo.length && bucle; i++){
			if(codigo[i].indexOf(inicio) != -1) {
				entrar = true;
			}else if(codigo[i].indexOf(fin) != -1 && entrar) {
				bucle = false;
			}
			if(entrar) {
				if(codigo[i].indexOf(texto) != -1){
					continuar = i + salto;
					linea = codigo[continuar];
					bucle = false;
				}
			}	
		}
		
		return linea;
	}
	
	//Extraer codigo entre comillas.
	public String comillas(String param, String ref, int index){
		String lista = "";
		int x = 0;
		
		for(int j = param.indexOf(ref, index); j < param.length() && x !=2; j++){
			if(param.charAt(j) == '\"'){
				x++;
			}
			else if(x == 1){
				lista += param.charAt(j);
			}
		}
		return lista;
	}
	
	//Extraer codigo entre etiquetas.
	public String etiquetas(String param, String ref, int index){
		String lista = "";
		int x = 0;
		for(int j = param.indexOf(ref, index); j < param.length() && x !=2; j++){
			if(param.charAt(j) == '<' || param.charAt(j) == '>'){
				x++;
			}
			else if(x == 1){
				lista += param.charAt(j);
			}
		}
		return lista;
	}
	
	/*protected String[] listAString(List<String> lista){
		String[] str = new String[lista.size()];
		for(int i = 0; i < lista.size(); i++){
			str[i] = lista.get(i);
		}
		return str;
	}*/
	
	
	protected String extraerTexto(String texto, String linea, String ref, int index) {
		if(texto.equals(ValuesStrings.COMILLAS)) {
			return comillas(linea, ref, index);
		}
		else if(texto.equals(ValuesStrings.ETIQUETAS)) {
			return etiquetas(linea, ref, index);
		}
		else {
			return linea;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		buscarTodo(link);
	}
}












