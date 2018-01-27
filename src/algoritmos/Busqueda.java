package algoritmos;

import java.util.List;
import java.awt.Image;
import java.util.ArrayList;
import red.Conexion;
import gui.ViewAnime;

public class Busqueda {
	private Conexion c;
	private String[] codigo;
	private String fin = "</ul>";
	private String inicio = "class=\"list-inline reset-floats\"";
	private String titulo = "class=\"title-dirmanga\"";
	private String detalles = "class=\"man-descdir\"";
	private String imagen = "img data-original";
	
	public Busqueda(){
		c = new Conexion();
	}
	
	
	
	public ViewAnime[] buscarTodo(String url){
		codigo = c.codigoFuente(url);
		String[] nombres = getTitulo();
		String[] detalles = getDetalles();
		String[] urls = getURL();
		Image[] imagenes = getImagenes();
		ViewAnime[] listado = new ViewAnime[nombres.length];
		
		for(int i = 0; i < nombres.length; i++){
			listado[i] = new ViewAnime(nombres[i], detalles[i], urls[i], imagenes[i]);
			listado[i].getString();
		}
		
		return listado;
	}
	
	/*
	 * *****************************
	 * 			Geters
	 * *****************************
	 */
	
	private String[] getTitulo(){
		
		String[] lineas = buscarLinea(this.titulo, inicio, fin, 1);
		String[] contenido = lineas;
		//String[] contenido = etiquetas(lineas, "Title", 0);
		
		return contenido;
	}
	
	private String[] getURL(){
		
		String[] lineas = buscarLinea("href", inicio, fin, 0);
		String[] contenido = etiquetas(lineas, "href", 0);
		
		return contenido;
	}
	
	private String[] getDetalles(){
		
		String[] lineas = buscarLinea(this.titulo, inicio, fin, 1);
		String[] contenido = lineas;
		//String[] contenido = etiquetas(lineas, "Title", 0);
		
		return contenido;
	}
	
	private Image[] getImagenes(){
		
		String[] lineas = buscarLinea(this.imagen, inicio, fin, 0);
		String[] contenido = comillas(lineas, "data-original=", 0);
		
		Image[] imagenes = c.descargar(contenido);
		
		return imagenes;
		
	}
	
	/*
	 * *******************************
	 * 			Buscadores
	 * *******************************
	 */
	
	private String[] buscarLinea(String texto){
		List<String> lista = new ArrayList<String>();
		for(int i = 0; i < codigo.length; i++){
			if(codigo[i].indexOf(texto) != -1){
				lista.add(codigo[i]);
				//System.out.println(codigo[i]);
			}
		}
		
		String[] str = listAString(lista);
		
		return str;
	}
	
	private String[] buscarLinea(String texto, String inicio, String fin, int salto){
		boolean entrar = false;
		boolean bucle = true;
		List<String> lista = new ArrayList<String>();
		
		for(int i = 0; i < codigo.length && bucle; i++){
			if(codigo[i].indexOf(inicio) != -1) {
				entrar = true;
				System.out.println("Inicio Encontrado");
			}else if(codigo[i].indexOf(fin) != -1 && entrar) {
				bucle = false;
				System.out.println("Bucle finalizado en linea = " + i);
			}
			if(entrar) {
				if(codigo[i].indexOf(texto) != -1){
					lista.add(codigo[i+salto]);
					System.out.println(codigo[i+salto]);
				}
			}
			
		}
		
		String[] str = listAString(lista);
		
		return str;
	}
	
	private String[] comillas(String[] param, String ref, int index){
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
	
	private String[] etiquetas(String[] param, String ref, int index){
		String[] lista = new String[param.length];
		
		for(int i = 0; i < param.length; i++){
			int x = 0;
			lista[i] = "";
			for(int j = param[i].indexOf(ref); j < param[i].length() && x !=2; j++){
				System.out.print(param[i].charAt(j));
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
	
	private String[] listAString(List<String> lista){
		String[] str = new String[lista.size()];
		for(int i = 0; i < lista.size(); i++){
			str[i] = lista.get(i);
		}
		return str;
	}
	
}

