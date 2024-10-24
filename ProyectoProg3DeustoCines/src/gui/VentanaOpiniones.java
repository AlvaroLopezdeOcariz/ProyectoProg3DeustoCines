package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		  
		  
		  lblInicio= new JLabel("OPINIONES",JLabel.CENTER);
		  lblInicio.setFont(new Font("Arial", Font.BOLD, 22));
		  
		  
		  JPanel panelSuperior= new JPanel(new BorderLayout());
		  panelSuperior.setBackground(Color.CYAN);
		  
		  
		  
		  
		  panelSuperior.add(lblInicio);
		  
		  
		  
		  
		  
		  add(panelSuperior,BorderLayout.NORTH);
		  
		  setVisible(true);
		  
	}
	
}
