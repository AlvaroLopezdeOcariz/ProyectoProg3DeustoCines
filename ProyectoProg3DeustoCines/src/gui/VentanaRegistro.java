package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class VentanaRegistro extends JFrame{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame vActual, vAnterior;
	private JPanel panelSuperior, panelPrincipal, panelInferior;
	private JButton botonVolver, botonRegistrarse;
	private JLabel lblRegistro;
	private JTextField txtNombre, txtUsuario, txtContrasenia;
	
 public VentanaRegistro(JFrame vAnterior) {
	 vActual=this;
	 this.vAnterior=vAnterior;
	 
	 setTitle("Registrarse");
	 setSize(800, 600);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 setLocationRelativeTo(null);
	 
	 //Parte Superior
	 panelSuperior = new JPanel(new BorderLayout());
	 botonVolver = new JButton("Volver");
	 lblRegistro= new JLabel("REGISTRO", JLabel.CENTER);
	 lblRegistro.setFont(new Font("Arial", Font.BOLD, 20));
	 panelSuperior.setBackground(Color.LIGHT_GRAY);
	 panelSuperior.add(lblRegistro,BorderLayout.CENTER);
	 panelSuperior.add(botonVolver,BorderLayout.WEST);
	 
	 //Parte Central
	 panelPrincipal = new JPanel();
	 panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	 txtNombre = new JTextField();
	 txtNombre.setBorder(BorderFactory.createTitledBorder("Ingresa tu nombre"));
	 txtUsuario= new JTextField();
	 txtUsuario.setBorder(BorderFactory.createTitledBorder("Ingresa tu Usuario o correo electrónico"));
	 txtContrasenia= new JPasswordField();
	 txtContrasenia.setBorder(BorderFactory.createTitledBorder("Ingresa tu Contraseña"));
	 
	 JRadioButton usuarioAdmin = new JRadioButton("Administrador");
     JRadioButton usuarioClient = new JRadioButton("Cliente");
     ButtonGroup radioButtonGroup = new ButtonGroup();
     
     radioButtonGroup.add(usuarioAdmin);
     radioButtonGroup.add(usuarioClient);
     
     
     JPanel panelRadioButton = new JPanel();
     
     panelRadioButton.setLayout(new BoxLayout(panelRadioButton, BoxLayout.X_AXIS));
    
     panelRadioButton.add(usuarioClient);
     panelRadioButton.add(usuarioAdmin);
     
     
     
     // panelPrincipal.add(txtNombre);
	// panelPrincipal.add(txtUsuario);
	// panelPrincipal.add(txtContrasenia);
	 JPanel panelPrincipalArriba= new JPanel(new GridLayout(3,1));
	 panelPrincipalArriba.add(txtNombre);
	 panelPrincipalArriba.add(txtUsuario);
	 panelPrincipalArriba.add(panelRadioButton);
	 
	 panelRadioButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
	 
	 
	 ActionListener actionListener = new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
             JToggleButton toggleButton = (JToggleButton) e.getSource();
             System.out.println("Cambio de estado en " + toggleButton.getText() + ". Seleccionado: " + toggleButton.isSelected());
         }

     };
     usuarioAdmin.addActionListener(actionListener);
     usuarioClient.addActionListener(actionListener);
	 
	 
	 
	 
	 JPanel panelPrincipalAbajo= new JPanel(new GridLayout(1,1));
	 panelPrincipalAbajo.add(txtContrasenia);
	 
	 panelPrincipal.add(panelPrincipalArriba);
	 
	 panelPrincipal.add(panelPrincipalAbajo);
	 
	 //Parte inferior
	 panelInferior = new JPanel();
	 botonRegistrarse = new JButton("Registrarse");
	 panelInferior.add(botonRegistrarse);
	 
	 
	 add(panelSuperior,BorderLayout.NORTH);
	 add(panelPrincipal,BorderLayout.CENTER);
	 
	 add(panelInferior,BorderLayout.SOUTH);
	// panelInferior.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
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