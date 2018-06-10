package recursos;

import java.nio.file.Files;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Fichero {

	public Fichero() {
		
	}
	
	public static boolean existe(File fichero) {
		return fichero.exists();
	}
	
	public static boolean existe(String path) {
		File fichero = new File(path);
		return fichero.exists();
	}
	
	public static boolean crearCarpeta(File carpeta) {
		return carpeta.mkdir();
	}
	
	public static boolean crearCarpeta(String path) {
		File carpeta = new File(path);
		return carpeta.mkdirs();
	}
	
	public static void ocultarFichero(String path) {
		File fichero = new File (path);
		try {
			Files.setAttribute(fichero.toPath(), "dos:hidden", true);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String extraerNombre(String url) {
		String nombre = "";
		boolean seguir = true;
		int i;
		for(i = url.length(); i > 0 && seguir; i--) {
			if(url.charAt(i-1) == '/') {	
				seguir = false;
			}
		}
		for(int j = i + 1;j < url.length(); j++) {
			nombre += url.charAt(j);
		}
			
		
		return nombre;
	}
	
	public static void guardarImagen(BufferedImage imagen, String extencion, String path) {
		File fichero = new File(path);
		try {
			ImageIO.write(imagen, extencion, fichero);
			System.out.println("Imagen "+ fichero +" guardada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al guardar la imagen");
		}
	}
	
	public static File getFichero(String path) {
		return new File(path);
	}
	
	public static Image getImagen(String path) {
		try {
			return ImageIO.read(Fichero.getFichero(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}






