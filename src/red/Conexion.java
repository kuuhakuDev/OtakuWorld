package red;

import java.net.URL;
import javax.imageio.ImageIO;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;


public class Conexion {
	
	private List<String> codigo = new ArrayList<String>();

	public String[] codigoFuente(String link) {
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
}
