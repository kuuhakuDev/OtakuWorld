package red;

import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ArrayList;


public class Conexion {
	
	private Toolkit tool = Toolkit.getDefaultToolkit();
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
			   
			   //imagenes[i] = ImageIO.read(new URL("http://www.animeyt.tv/files/img/capitulos/m_27968.jpeg"));
			   
				URL url = new URL("http://www.leomanga.com" + direccion[i]);
				HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
		         httpcon.addRequestProperty("User-Agent", 
		        		 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		         //httpcon.setConnectTimeout(1000);
				
				// acceso al contenido web
				InputStream is = httpcon.getInputStream();

				// Fichero en el que queremos guardar el contenido
				//FileOutputStream fos = new FileOutputStream("e:/foto.jpg");
				
				imagenes[i] = ImageIO.read(is);
				System.out.println(i + " = " + direccion[i]);
				// buffer para ir leyendo.
				//byte [] array = new byte[1000];

				// Primera lectura y bucle hasta el final
				/*int leido = is.read(array);
				while (leido > 0) {
				   fos.write(array,0,leido);
				   
				   leido=is.read(array);
				}

				// Cierre de conexion y fichero.
				is.close();
				fos.close();*/
				
			} 
		   }catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		   }catch (IOException i) {
			   i.printStackTrace();
		   }
		   
		   return imagenes;
	       
	   }
}
