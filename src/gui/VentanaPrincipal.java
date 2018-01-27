package gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import manga.VentanaManga;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener{

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
		anime.addActionListener(this);
		anime.setEnabled(false);
		
		manga = new JButton("Manga");
		manga.setPreferredSize(size);
		manga.addActionListener(this);
		
		noticias = new JButton("Noticias");
		noticias.setPreferredSize(size);
		noticias.addActionListener(this);
		noticias.setEnabled(false);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(anime)) {
			VentanaAnime.getInstans();
			System.out.println("Anime");
		}
		else if(e.getSource().equals(manga)) {
			System.out.println("Manga");
			VentanaManga.getInstans();
		}
		else if(e.getSource().equals(noticias)) {
			System.out.println("Noticias");			
		}
	}
}
