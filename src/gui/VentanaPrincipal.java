package gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private JButton anime, manga, noticias;
	private JPanel panelCentral, panelNorte;
	private JLabel otakuWorld;
	private Dimension size = new Dimension(200, 40);
	private Dimension sizeFrame = new Dimension(tool.getScreenSize().width/3,
												tool.getScreenSize().height/2);
	private Font letra = new Font("Time New Roman", Font.BOLD, 30);

	
	public VentanaPrincipal() {
		
		setTitle("OtakuWorld");
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		center();
		north();
		
		setSize(sizeFrame);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void center() {
		
		anime = new JButton("Anime");
		anime.setPreferredSize(size);
		manga = new JButton("Manga");
		manga.setPreferredSize(size);
		noticias = new JButton("Noticias");
		noticias.setPreferredSize(size);
		panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 30));
		
		panelCentral.add(anime);
		panelCentral.add(manga);
		panelCentral.add(noticias);
		add(panelCentral, BorderLayout.CENTER);
		
	}

	private void north() {
		
		otakuWorld = new JLabel("Otaku World");
		otakuWorld.setFont(letra);
		panelNorte = new JPanel(new FlowLayout());
		panelNorte.setBorder(new EmptyBorder(40,10,20,10));
		panelNorte.add(otakuWorld);
		add(panelNorte, BorderLayout.NORTH);
		
	}
}
