package algoritmos;

import java.util.List;
import java.awt.Image;
import java.util.ArrayList;
import red.Conexion;
import values.ValuesStrings;
import gui.PanelView;

public class Busqueda {
	private Conexion c;
	private String[] codigo;
	private String fin;
	private String inicio;
	private String titulo;
	private String detalles;
	private String imagen;
	
	public Busqueda(String inicio, String fin, String titulo, String detalles, String imagen){
		c = new Conexion();
		
		this.inicio = inicio;
		this.fin = fin;
		this.titulo = titulo;
		this.detalles = detalles;
		this.imagen = imagen;
	}
	
	
	
	public PanelView[] buscarTodo(String url){
		codigo = c.codigoFuente(url);
		String[] nombres = getTitulo();
		String[] detalles = getDetalles();
		String[] urls = getURL();
		Image[] imagenes = getImagenes();
		PanelView[] listado = new PanelView[nombres.length];
		
		for(int i = 0; i < nombres.length; i++){
			listado[i] = new PanelView(nombres[i], detalles[i], urls[i], imagenes[i]);
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
		String[] contenido = extraerTexto(ValuesStrings.NADA, lineas, titulo, 0);
		
		return contenido;
	}
	
	private String[] getURL(){
		
		String[] lineas = buscarLinea("href", inicio, fin, 0);
		String[] contenido = extraerTexto(ValuesStrings.ETIQUETAS, lineas, "href", 0);
		
		return contenido;
	}
	
	private String[] getDetalles(){
		
		String[] lineas = buscarLinea(detalles, inicio, fin, 1);
		String[] contenido = extraerTexto(ValuesStrings.NADA, lineas, detalles, 0);
		//String[] contenido = etiquetas(lineas, "Title", 0);
		
		return contenido;
	}
	
	private Image[] getImagenes(){
		
		String[] lineas = buscarLinea(this.imagen, inicio, fin, 0);
		String[] contenido = extraerTexto(ValuesStrings.COMILLAS, lineas, imagen, 0);
		
		Image[] imagenes = c.descargar(contenido);
		
		return imagenes;
		
	}
	
	/*
	 * *******************************
	 * 			Buscadores
	 * *******************************
	 */

	
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
	
	
	private String[] extraerTexto(String texto, String[] lineas, String ref, int index) {
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












