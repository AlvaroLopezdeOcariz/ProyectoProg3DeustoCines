package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame{
	 JFrame vActual, vAnterior;
 public VentanaRegistro(JFrame vAnterior) {
	 vActual=this;
	 this.vAnterior=vAnterior;
	 
	 setTitle("Registrarse");
	 setSize(800,600);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 
	 //Parte Superior
	 JPanel panelSuperior = new JPanel(new BorderLayout());
	 JButton botonVolver = new JButton("Volver");
	 JLabel lblRegistro= new JLabel("Registro");
	 panelSuperior.add(lblRegistro,BorderLayout.CENTER);
	 panelSuperior.add(botonVolver,BorderLayout.WEST);
	 
	 //Parte Central
	 JPanel panelPrincipal = new JPanel(new GridLayout(3,1));
	 JTextField txtNombre = new JTextField();
	 txtNombre.setBorder(BorderFactory.createTitledBorder("Ingreasa tu nombre"));
	 JTextField txtUsuario= new JTextField();
	 txtUsuario.setBorder(BorderFactory.createTitledBorder("Ingresa tu Usuario o correo electrónico"));
	 JTextField txtContrasenia= new JPasswordField();
	 txtContrasenia.setBorder(BorderFactory.createTitledBorder("Ingresa tu Contraseña"));
	 
	 panelPrincipal.add(txtNombre);
	 panelPrincipal.add(txtUsuario);
	 panelPrincipal.add(txtContrasenia);
	 
	 //Parte inferior
	 JPanel panelInferior = new JPanel();
	 JButton botonRegistrarse = new JButton("Registrarse");
	 panelInferior.add(botonRegistrarse);
	 
	 
	 add(panelSuperior,BorderLayout.NORTH);
	 add(panelPrincipal,BorderLayout.CENTER);
	 add(panelInferior,BorderLayout.SOUTH);
	 
	 botonVolver.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			vActual.dispose();
			vAnterior.setVisible(true);
			
		}
	});
	 
	 
	 botonRegistrarse.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(main.Deustocines.IniciarRegistro(txtNombre.getText(),txtUsuario.getText(),txtContrasenia.getText())==true){
				JOptionPane.showMessageDialog(VentanaRegistro.this,"Te has registrado con exito");
				vActual.dispose();
				vAnterior.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(VentanaRegistro.this,"Este usuario ya esta registrado. Pruebe otra contraseña o cree un usuario con un nombre de usuario diferente");
			}
			
		}
	});
	 
	 setVisible(true);
 }

 
 
}
