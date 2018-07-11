package gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;;

public class VentanaAnime extends JFrame implements ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaAnime anime;
	private JScrollPane scroll = new JScrollPane();
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private float x = (float) (tool.getScreenSize().width*0.8);
	private float y = (float) (tool.getScreenSize().height*0.8);
	private Dimension sizeFrame = new Dimension((int)x,(int)y);
	private PanelAnime panelAnime;
	
	private VentanaAnime() {
		
		setLayout(new BorderLayout());
		addComponentListener(this);
		setTitle("OtakuWorld-Anime");
		setSize(sizeFrame);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		centro();
		setVisible(true);
		
	}
	
	public static VentanaAnime getInstans() {
		
		if(anime == null) {
			anime = new VentanaAnime();
		}
		else if(!anime.isVisible()) {
			anime.setVisible(true);
		}
		
		return anime;
	}

	private void centro() {
		//panelAnime = new PanelAnime(this);
		scroll.getViewport().add(panelAnime);
		add(scroll, BorderLayout.CENTER);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		//panelAnime.size(this);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
