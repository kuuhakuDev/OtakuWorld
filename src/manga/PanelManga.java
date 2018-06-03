package manga;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algoritmos.Busqueda;
import gui.PanelView;
import recursos.Listado;
import values.ValuesStrings;

public class PanelManga extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PanelView> lista = new ArrayList<PanelView>();
	private int x;
	private int ancho = 50, largo= 50;
	private VentanaManga ventana;
	
	public PanelManga(VentanaManga ventana) {
		
		setLayout(new FlowLayout(FlowLayout.CENTER, ancho, largo));
		listaAnime();
		this.ventana = ventana;
		size(ventana);
		
	}
	
	private void listaAnime() {
		String inicio = ValuesStrings.INICIO_MANGA;
		String fin = ValuesStrings.FIN_MANGA;
		String titulo = ValuesStrings.TITULO_MANGA;
		String url = ValuesStrings.URL_MANGA;
		String detalles = ValuesStrings.DETALLES_MANGA;
		String portada = ValuesStrings.PORTADA_MANGA;
		
		Busqueda b = new Busqueda(inicio, fin, titulo, url, detalles, portada);
		Listado[] lista = b.buscarTodo(null, ValuesStrings.LEO_MANGA_DIR);
		
		PanelView[] anime = new PanelView[lista.length];
		
		for(int i = 0; i < anime.length; i++) {
			anime[i] = new PanelView(ventana,lista[i]);
		}
		
		for(int i = 0; i < anime.length; i++) {
			//lista.add(new ViewAnime("Naruto", "", "",new ImageIcon(getClass().getResource("/image/minato.jpg")).getImage()));
			this.lista.add(anime[i]);
		}
		
		for(int i = 0; i < anime.length; i++) {
			add(this.lista.get(i));
		}
		
	}
	
//	Falta arreglar el bug para cuando tenemos menos de 6 elemenatos
	protected void size(JFrame ventana) {
		int sizeX = -6;
		int sizeY = largo;
		boolean entrar = true;
		int i;
		int ventanax = ventana.getSize().width;
		int viewX = lista.get(0).getPreferredSize().width + ancho;
		int viewY = lista.get(0).getPreferredSize().height + largo;
		for(i = 0; i < lista.size() && entrar; i++) {
			sizeX += viewX;
			if(ventanax-40 < sizeX) {
				entrar = false;
				sizeX -= viewX;
				i--;
			}
		}
		
		if(i != 0 && x != i && i != lista.size()-1) {
			if((lista.size() % i) == 0) {
				sizeY += (lista.size()/i) * viewY;
				System.out.println("Adentro :v   " + i);
			}else {
				sizeY += (((int)(lista.size()/i)) + 1) * viewY;
				System.out.println("Afuera :v   " + i);
			}
			
			setPreferredSize(new Dimension(sizeX, sizeY));
			System.out.println(sizeX +"       "+ sizeY);
			x = i;
		}
	}

}

