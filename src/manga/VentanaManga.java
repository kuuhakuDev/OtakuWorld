package manga;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class VentanaManga extends JFrame implements ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaManga manga;
	private JScrollPane scroll = new JScrollPane();
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private float x = (float) (tool.getScreenSize().width*0.8);
	private float y = (float) (tool.getScreenSize().height*0.8);
	private Dimension sizeFrame = new Dimension((int)x,(int)y);
	private PanelManga panelManga;

	private VentanaManga() {
		
		setLayout(new BorderLayout());
		addComponentListener(this);
		setTitle("OtakuWorld-Manga");
		setSize(sizeFrame);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		centro();
		setVisible(true);
	}
	
	public static VentanaManga getInstans() {
		if(manga == null) {
			manga = new VentanaManga();
			return manga;
		}else {
			return manga;
		}
		
	}
	
	private void centro() {
		panelManga = new PanelManga(this);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.getViewport().add(panelManga);
		add(scroll, BorderLayout.CENTER);
	}
	
	/*
	 *******************************************
	 * 		Interface Component Listener
	 *******************************************
	 */

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		panelManga.size(this);
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
