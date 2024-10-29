package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Usuario;

public class VentanaOpiniones extends JFrame {

	
	
	
	 JFrame vActual, vAnterior;
	 JPanel panelInferior, panelPrincipal;
	 JLabel lblInicio;
	 JTextField txtUsuario;
	 JPasswordField txtContrasenia;
	
	
	
	public VentanaOpiniones(JFrame vAnterior) {
		 vActual = this;
		  this.vAnterior= vAnterior;
		  
		  
		  setTitle("OPINIONES");
		  setSize(800,600);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  
		  
		  
		  JPanel panelInferior = new JPanel(new BorderLayout());
		  panelInferior.setBackground(Color.GREEN);
		  
		  
		  lblInicio= new JLabel("OPINIONES",JLabel.CENTER);
		  lblInicio.setFont(new Font("Arial", Font.BOLD, 22));
		  
		  
		  JPanel panelSuperior= new JPanel(new BorderLayout());
		  panelSuperior.setBackground(Color.CYAN);
		  
		  JButton botonOpinion= new JButton("Nueva Opinion");
		  
	
		  panelSuperior.add(lblInicio);
		  
		  panelInferior.add(botonOpinion);
		  
		  
		  
		  
		  add(panelSuperior,BorderLayout.NORTH);
		  add(panelInferior, BorderLayout.SOUTH);
		  
		  botonOpinion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 if (Usuario.getUsuarioActual() != null) {
		                    JOptionPane.showMessageDialog(VentanaOpiniones.this, "HOLA " + Usuario.getUsuarioActual().getNomUsuario());
		                } else {
		                    // Si no hay usuario, mostrar diálogo
		                    int option = JOptionPane.showConfirmDialog(VentanaOpiniones.this,
		                            "¿Estás registrado?", "Registro", JOptionPane.YES_NO_OPTION);

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
		  
		  
		  
		  
		  setVisible(true);
		  
	}
	
}
