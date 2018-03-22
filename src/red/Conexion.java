package red;

import java.net.URL;
import javax.imageio.ImageIO;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;


public class Conexion {

	public String[] codigoFuente(String link) {
		List<String> codigo = new ArrayList<String>();
	      try {
	         // Se abre la conexión
	         URL url = new URL(link);
	         
	         HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
	         httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
	         httpcon.setConnectTimeout(1000);
	         
	         // Lectura
	         InputStream is = httpcon.getInputStream();
	         BufferedReader br = new BufferedReader(new InputStreamReader(is));
	         
	         String add = new String("");
	         while((add = br.readLine()) != null){
	        	 codigo.add(add.trim());
	         }
	         
	         br.close();
	         is.close();
	         httpcon.disconnect();
	         
	            //codigo.add(add);
	      } catch (SocketTimeoutException e) {
		    	 e.printStackTrace();
		    	 System.out.println("Ocurrio una descarga en la descarga, por favor, vuelva a intentarlo.");
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	       
	      }
	      
	      String[] codigoFuente = new String[codigo.size()];
	      codigoFuente = codigo.toArray(codigoFuente);
	      
	      return codigoFuente;
	   }
	
	public Image[] descargar(String[] direccion){
		   
		   Image[] imagenes = new Image[direccion.length];
		   try {
		   for(int i = 0; i < direccion.length; i++){
			   
				URL url = new URL("http://www.leomanga.com" + direccion[i]);
				HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
		         httpcon.addRequestProperty("User-Agent", 
		        		 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
				
				InputStream is = httpcon.getInputStream();
				
				imagenes[i] = ImageIO.read(is);
				System.out.println(i + " = " + direccion[i]);
				
			} 
		   }catch (MalformedURLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }catch (IOException e) {
			   e.printStackTrace();
		   }
		   
		   return imagenes;
	       
	   }
	
	public void descargarMangas(List<String[]> direccion, String titulo, String[] capitulo){
		   
		   BufferedImage[] imagenes = new BufferedImage[direccion.size()];
		   try {
		   for(int i = 0; i < direccion.size(); i++){
			   for(int j = 0; j < direccion.get(i).length; j++) {
				   File carpeta = new File("Mangas");
				   if(!carpeta.exists()) {
					   carpeta.mkdirs();
					   System.out.println("Carpeta Mangas Creada");
				   }
				   File carpeta2 = new File(carpeta, titulo.trim());
				   if(!carpeta2.exists()) {
					   carpeta2.mkdirs();
					   System.out.println("Carpeta " + titulo + " Creada");
				   }
				   File carpeta3 = new File(carpeta2, capitulo[i].trim());
				   if(!carpeta3.exists()) {
					   carpeta3.mkdirs();
					   System.out.println("Carpeta " + capitulo[i].trim() + " Creada");
				   }
				   File carpeta4 = new File(carpeta3, j + ".jpg");
				   if(!carpeta4.exists()) {
					   System.out.println(carpeta4.getName());
					   //carpeta4.createNewFile();
					   //System.out.println("Fichero " + j + ".jpg" + " Creada");
					   
					   URL url = new URL("http://www.leomanga.com" + direccion.get(i)[j]);
					   HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
					   httpcon.setConnectTimeout(500);
				       httpcon.addRequestProperty("User-Agent", 
				       	 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
						
				       InputStream is = httpcon.getInputStream();
				       imagenes[i] = ImageIO.read(is);
						
				       ImageIO.write(imagenes[i], "jpg", carpeta4);
				       System.out.println(i + " = " + direccion.get(i)[j]);
				   }

			   }
			   
			   				
			} 
		   System.out.println("Capitilo/s descargado con exito");
		      } catch (SocketTimeoutException e) {
			    	 e.printStackTrace();
			    	 System.out.println("Ocurrio una descarga en la descarga, por favor, vuelva a intentarlo.");
			    	 descargarMangas(direccion, titulo, capitulo);
		   }catch (MalformedURLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }catch (IOException e) {
			   e.printStackTrace();
		   }
		   
	       
	   }
}
