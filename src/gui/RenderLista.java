package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import recursos.Listado;
import recursos.Listado.Aux;

public class RenderLista extends JPanel implements ListCellRenderer<Aux>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel numero = new JLabel();
	private JLabel nombre = new JLabel();
	private JLabel fecha = new JLabel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();

	private Font fontTitulo = new Font("Times New Roman", Font.BOLD, 18);
	private Font fontDescripcion = new Font("Times New Roman", Font.PLAIN, 15);
	
	public RenderLista(){
		setLayout(new BorderLayout());
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(numero);
		panel1.add(nombre);
		
		panel2.add(fecha);
		
		add(panel1,BorderLayout.WEST);
		add(panel2,BorderLayout.EAST);
		
		//add(panel);
	}
	
	/*public Component getListCellRendererComponent(JList<? extends Aux> list, Listado value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
			fecha.setIcon(new ImageIcon(value.getImg().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

			numero.setText(value.getTitulo());
			numero.setFont(fontTitulo);
			nombre.setText(value.getDetalle());
			nombre.setFont(fontDescripcion);
			
			fecha.setOpaque(true);
	        numero.setOpaque(true);
	        nombre.setOpaque(true);
	        
	        setBorder(new LineBorder(new Color(5,5,5),2));
	        
	        if(isSelected){
	        	
	        	panel1.setBackground(list.getSelectionBackground());
	        	panel2.setBackground(list.getSelectionBackground());
	        	fecha.setBackground(list.getSelectionBackground());
	        	fecha.setBorder(new LineBorder(list.getSelectionBackground(),5));
	        	numero.setBackground(list.getSelectionBackground());
	        	nombre.setBackground(list.getSelectionBackground());
	        	setBackground(list.getSelectionBackground());
	        }else{
	        	panel1.setBackground(list.getBackground());
	        	panel2.setBackground(list.getBackground());
	        	fecha.setBackground(list.getBackground());
	        	fecha.setBorder(new LineBorder(list.getBackground(),5));
	        	numero.setBackground(list.getBackground());
	        	nombre.setBackground(list.getBackground());
	        	setBackground(list.getBackground());
	        }
		
		return this;
	}*/

	@Override
	public Component getListCellRendererComponent(JList<? extends Aux> list, Aux value, int index, 
			boolean isSelected,	boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
		numero.setText(value.numero);
		//numero.setFont(fontTitulo);
		nombre.setText(value.nombre);
		//nombre.setFont(fontDescripcion);
		fecha.setText(value.fecha);
		
		fecha.setOpaque(true);
        numero.setOpaque(true);
        nombre.setOpaque(true);
		
        if(isSelected){
        	panel1.setBackground(list.getSelectionBackground());
        	panel2.setBackground(list.getSelectionBackground());
        	fecha.setBackground(list.getSelectionBackground());
        	//fecha.setBorder(new LineBorder(list.getSelectionBackground(),5));
        	numero.setBackground(list.getSelectionBackground());
        	nombre.setBackground(list.getSelectionBackground());
        	setBackground(list.getSelectionBackground());
        }else{
        	panel1.setBackground(list.getBackground());
        	panel2.setBackground(list.getBackground());
        	fecha.setBackground(list.getBackground());
        	//fecha.setBorder(new LineBorder(list.getBackground(),5));
        	numero.setBackground(list.getBackground());
        	nombre.setBackground(list.getBackground());
        	setBackground(list.getBackground());
        }
        
		return this;
	}

}




