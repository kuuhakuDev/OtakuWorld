package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton anime, manga, noticias;
	private JPanel panelCentral, panelNorte;
	private JLabel otakuWorld;
	private Font letra = new Font("Time New Roman", Font.BOLD, 30);

	
	public VentanaPrincipal() {
		
		setTitle("OtakuWorld");
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		center();
		north();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void center() {
		
		anime = new JButton("Anime");
		manga = new JButton("Manga");
		noticias = new JButton("Noticias");
		panelCentral = new JPanel(new GridLayout(3,0, 20, 20));
		
		panelCentral.add(anime);
		panelCentral.add(manga);
		panelCentral.add(noticias);
		add(panelCentral, BorderLayout.CENTER);
		
	}

	private void north() {
		
		otakuWorld = new JLabel("Otaku World");
		otakuWorld.setFont(letra);
		panelNorte = new JPanel(new FlowLayout());
		panelNorte.setBorder(new EmptyBorder(10,10,10,10));
		panelNorte.add(otakuWorld);
		add(panelNorte, BorderLayout.NORTH);
		
	}
}
