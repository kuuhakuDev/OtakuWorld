package gui;

import java.awt.Component;
import java.awt.FlowLayout;
//import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import gui.PanelView.Aux;

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

	//private Font fontTitulo = new Font("Times New Roman", Font.BOLD, 18);
	//private Font fontDescripcion = new Font("Times New Roman", Font.PLAIN, 15);
	
	public RenderLista(){
		setLayout(new BorderLayout());
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(numero);
		panel1.add(nombre);
		
		panel2.add(fecha);
		
		add(panel1,BorderLayout.WEST);
		add(panel2,BorderLayout.EAST);
	}

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
        	numero.setBackground(list.getSelectionBackground());
        	nombre.setBackground(list.getSelectionBackground());
        	setBackground(list.getSelectionBackground());
        }else{
        	panel1.setBackground(list.getBackground());
        	panel2.setBackground(list.getBackground());
        	fecha.setBackground(list.getBackground());
        	numero.setBackground(list.getBackground());
        	nombre.setBackground(list.getBackground());
        	setBackground(list.getBackground());
        }
        
		return this;
	}

}




