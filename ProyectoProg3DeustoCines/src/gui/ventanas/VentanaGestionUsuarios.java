package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.clases.Usuario;
import main.Deustocines;

public class VentanaGestionUsuarios extends JFrame{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame vActual;
	private JTable tabla;
	private ModeloTablaUsuarios modeloTabla;
	private JScrollPane scrollTabla;
	private JButton botonVolver;
	private JPanel panelSuperior;
	 public VentanaGestionUsuarios(JFrame vAnterior) {
		 vActual = this;
	        //clave=nomUsuario valor= Usuario
		 HashMap<String, Usuario> usuarios = Deustocines.mapaUsuarios();
		 

		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("GESTION DE USUARIOS");
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        modeloTabla = new ModeloTablaUsuarios(usuarios);
			tabla = new JTable(modeloTabla);
			scrollTabla = new JScrollPane(tabla);
			getContentPane().add(scrollTabla, BorderLayout.CENTER);
			 panelSuperior = new JPanel(new BorderLayout());
		        this.add(panelSuperior,BorderLayout.NORTH);
		       
			
			 botonVolver = new JButton("Volver");
		        botonVolver.setFont(new Font("Arial", Font.PLAIN, 14));
		        botonVolver.setBackground(new Color(220, 220, 220));
		        botonVolver.setForeground(Color.DARK_GRAY);
		        botonVolver.setFocusPainted(false);
		        botonVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		        botonVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		        panelSuperior.add(botonVolver, BorderLayout.WEST);
	        
	        tabla.setDefaultRenderer(Object.class,(table,value,isSelected,hasFocus,row,column)->{
	        	Component c ;
				JLabel l = new JLabel();
				l.setOpaque(true);
				l.setHorizontalAlignment(JLabel.CENTER);
				l.setText(value.toString());
				
				if(table.getValueAt(row,3).toString().equals("Si")) {
					l.setBackground(Color.GREEN);
					
				}
				
				c=l;
				return c;
	        });
	        tabla.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					Point p = e.getPoint();
					int fila = tabla.rowAtPoint(p);
					//int columna = tabla.columnAtPoint(p);
					
					if(tabla.getValueAt(fila,3).toString().equals("No")) {
						int opciones = JOptionPane.showConfirmDialog(VentanaGestionUsuarios.this,"¿QUIERES QUE ESTE USUARIO SEA ADMINISTRADOR?", "NUEVO ADMINISTRADOR", JOptionPane.YES_NO_OPTION);
						if(opciones==JOptionPane.YES_OPTION) {
							
							usuarios.get(tabla.getValueAt(fila, 1)).setEsAdmin(true);
							Deustocines.editarUsuarios(usuarios.get(tabla.getValueAt(fila, 1)));
							tabla.repaint();
							
						}
						
					}else  {
						int opciones = JOptionPane.showConfirmDialog(VentanaGestionUsuarios.this,"¿QUIERES QUE ESTE USUARIO DEJE DE SER ADMINISTRADOR?", "NUEVO ADMINISTRADOR", JOptionPane.YES_NO_OPTION);
						if(opciones ==JOptionPane.YES_OPTION){
							//System.out.println("d");
							usuarios.get(tabla.getValueAt(fila, 1)).setEsAdmin(false);
							Deustocines.editarUsuarios(usuarios.get(tabla.getValueAt(fila, 1)));
							tabla.repaint();
						}
					}
					
				}
			});
	        botonVolver.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                vActual.dispose();
	                vAnterior.setVisible(true);
	            }
	        });
	        
	        
	        setVisible(true);
	 }
}
