package gui;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

public class PanelAnime extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ViewAnime> lista = new ArrayList<ViewAnime>();
	private int x;
	private int ancho = 50, largo= 50;

	public PanelAnime(VentanaAnime ventana) {
		
		setLayout(new FlowLayout(FlowLayout.CENTER, ancho, largo));
		listaAnime();
		size(ventana);
		
	}
	
	private void listaAnime() {
		int lon = 10;
		
		for(int i = 0; i < lon; i++) {
			lista.add(new ViewAnime());
		}
		
		for(int i = 0; i < lon; i++) {
			add(lista.get(i));
		}
	}
	
//	Falta arreglar el bug para cuando tenemos menos de 6 elemenatos
	protected void size(VentanaAnime ventana) {
		int sizeX = -6;
		int sizeY = largo;
		boolean entrar = true;
		int i;
		int ventanax = ventana.getSize().width;
		int viewX = lista.get(1).getPreferredSize().width + ancho;
		int viewY = lista.get(1).getPreferredSize().height + largo;
		for(i = 0; i < lista.size() && entrar; i++) {
			sizeX += viewX;
			if(ventanax-40 < sizeX) {
				entrar = false;
				sizeX -= viewX;
			}
		}
		i--;
		
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
