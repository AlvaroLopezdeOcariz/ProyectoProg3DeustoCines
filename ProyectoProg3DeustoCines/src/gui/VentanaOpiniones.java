package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaOpiniones extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private JFrame vActual, vAnterior;
	 private JPanel panelSuperior;
	 private JLabel lblInicio;
	 private JButton botonOpinion, botonVolver;
	 private Pelicula pelicula;
	 
	
	public VentanaOpiniones(JFrame vAnterior, Pelicula pelicula) {
		 vActual = this;
		  this.vAnterior= vAnterior;
		  this.pelicula= pelicula;
		  
		  
		  setTitle("OPINIONES de " +pelicula.getTitulo());
		  setSize(800,600);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  
		  
		  panelSuperior= new JPanel(new BorderLayout());
		 
		  
		  
		  lblInicio= new JLabel("Opiniones de " + pelicula.getTitulo(),JLabel.CENTER);
		  lblInicio.setFont(new Font("Arial", Font.BOLD, 22));
		  
		  
		  
		  
		  botonOpinion= new JButton("Nueva Opinion");
		  
	
		  
		  
		  botonVolver = new JButton("Volver");
		  
		  
		 
		  
		  botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vActual.dispose();
				vAnterior.setVisible(true);
				
			}
		});
		  
		  
		  //Es un campo de texto para ver la opiniones y poder agragar más
		  JTextArea areaOpiniones = new JTextArea(10, 40);
		  areaOpiniones.setEditable(false);
		  JScrollPane scrollOpiniones= new JScrollPane(areaOpiniones);
		  
		  
		  
		  
		   JPanel panelOpiniones= new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
		   
		  
		  
		 
		  
		  botonOpinion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 if (Usuario.getUsuarioActual() != null) {
		                    JOptionPane.showMessageDialog(VentanaOpiniones.this, "HOLA " + Usuario.getUsuarioActual().getNomUsuario());
		                    String nuevaOpinion = JOptionPane.showInputDialog(VentanaOpiniones.this, "Escribe tu opinión:");
		                    if(nuevaOpinion != null && !nuevaOpinion.isEmpty()) {
		                    	areaOpiniones.append(Usuario.getUsuarioActual().getNomUsuario() + ": " + nuevaOpinion + "\n");
		                    }
		                } else {
		                    // Si no hay usuario, mostrar diálogo
		                    int option = JOptionPane.showConfirmDialog(VentanaOpiniones.this,
		                            "¿Estás registrado?","REGISTRO", JOptionPane.YES_NO_OPTION);

		                    if (option == JOptionPane.YES_OPTION) {
		                        vActual.dispose(); // Cerrar ventana actual
		                        new VentanaInicioSesion(vActual);
		                    } else {
		                        vActual.dispose(); // Cerrar ventana actual
		                        new VentanaRegistro(vActual);
		                    }
		                }
		            }
		        });
		  
		  	panelSuperior.add(botonVolver, BorderLayout.WEST);
		  	panelSuperior.add(lblInicio);  
	        panelOpiniones.add(scrollOpiniones);  
	        panelOpiniones.add(botonOpinion);  
	        
		  
	        add(panelOpiniones, BorderLayout.CENTER);
	        add(panelSuperior,BorderLayout.NORTH);
		  
		  setVisible(true);
		  
	}
	
}