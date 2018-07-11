package algoritmos;

import java.awt.Image;
import red.Conexion;
import values.ValuesStrings;
import manga.PanelManga;

public class Busqueda implements Runnable{
	private Conexion c;
	protected String[] codigo;
	private String fin;
	private String inicio;
	private String titulo;
	private String url;
	private String detalles;
	private String imagen;
	private String puntuacion;
	private Thread hilo;
	private String link;
	protected int continuar = 0;
	protected boolean entrar = false;
	
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
		
		for(int i = 0; entrar && i < 12; i++) {
			String url1 = getURL();
			String img = getURLImagen();
			String nombre = getTitulo();
			if(nombre != "") {
				PanelManga.nuevoManga(i);
				
				PanelManga.setNombre(nombre, i);
				//listado.get(i).setDetalleCorto(getDetalles(i));
				PanelManga.setURL(url1, i);
				PanelManga.setImage(getImagen(img), i);
				System.out.println(nombre);
				
			}else {
				entrar = false;
			}
		}
		
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
	
	public String getTitulo(){
		
		String lineas = buscarLinea(this.titulo, inicio, fin, 1);
		String contenido = extraerTexto(ValuesStrings.NADA, lineas, titulo, 0);
		
		return contenido;
	}
	
	public String getURL(){
		
		String lineas = buscarLinea(url, inicio, fin, 0);
		String contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, "href", 0);
		
		return contenido;
	}
	
	public String getDetalles(){
		
		String lineas = buscarLinea(detalles, inicio, fin, 1);
		String contenido = extraerTexto(ValuesStrings.NADA, lineas, detalles, 0);
		
		return contenido;
	}
	
	public String getPuntuacion() {
		
		String lineas = buscarLinea(this.puntuacion, inicio, fin, 0);
		String contenido = extraerTexto(ValuesStrings.ETIQUETAS, lineas, puntuacion, 0);
		
		return contenido;
	}
	
	private Image getImagen(String lineas){
		String contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, imagen, 0);
		
		Image imagenes = c.descargar(contenido, "Manga");
		
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












