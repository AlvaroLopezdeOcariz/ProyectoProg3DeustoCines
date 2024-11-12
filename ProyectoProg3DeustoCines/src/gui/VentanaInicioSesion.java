package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaInicioSesion extends JFrame{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame vActual, vAnterior;
	private JPanel panelInferior, panelPrincipal, panelSuperior;
	private JLabel lblInicio, etiquetaRegistro;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private JButton botonIniciarSesion, botonVolver;
	
	public VentanaInicioSesion(JFrame vAnterior) {
		  vActual = this;
		  this.vAnterior= vAnterior;
		  
		   
		  setTitle("INICIO DE SESION");
		  setSize(800,600);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  
		  
		  lblInicio= new JLabel("INICIAR SESION",JLabel.CENTER);
		  lblInicio.setFont(new Font("Arial", Font.BOLD, 20));
		  
		  panelSuperior = new JPanel(new BorderLayout());
		  panelSuperior.setBackground(Color.LIGHT_GRAY);
		  
		  panelInferior = new JPanel();
		  panelInferior.setBackground(Color.BLACK);
		  etiquetaRegistro = new JLabel("REGISTRARSE");
	      etiquetaRegistro.setForeground(Color.WHITE);
		  
		  panelPrincipal = new JPanel();
		  panelPrincipal.setLayout(new GridLayout(3, 1, 10, 10));
	      panelPrincipal.setBackground(Color.WHITE);
	     
		  
		  txtUsuario= new JTextField();
		  txtUsuario.setBorder(BorderFactory.createTitledBorder("Usuario o correo electrónico"));
		  
		  
		  txtContrasenia= new JPasswordField();
		  txtContrasenia.setBorder(BorderFactory.createTitledBorder("Contraseña"));
	        
		  botonIniciarSesion= new JButton("Iniciar Sesion");
		  botonVolver = new JButton("Volver");
		  
		  panelSuperior.add(lblInicio);
		  panelSuperior.add(botonVolver,BorderLayout.WEST);
		  panelInferior.add(etiquetaRegistro);
		  panelPrincipal.add(txtUsuario);
		  panelPrincipal.add(txtContrasenia);
		  panelPrincipal.add(botonIniciarSesion);
		  
		  botonIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(main.Deustocines.iniciarSesion(txtUsuario.getText(),txtContrasenia.getText())==true){
				JOptionPane.showMessageDialog(VentanaInicioSesion.this,"Has iniciado sesion con exito");
				vActual.dispose();
				vAnterior.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(VentanaInicioSesion.this,"Usuario o Contraseña incorrecta");
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
		  etiquetaRegistro.addMouseListener(new MouseListener() {
				
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
					new VentanaRegistro(vActual);
					vActual.dispose();
					
				}
			});
		  
		  
		
		  
		  
		  
		add(panelInferior,BorderLayout.SOUTH);
		add(panelPrincipal,BorderLayout.CENTER);
		add(panelSuperior,BorderLayout.NORTH);
		
		
		
		
		
		//pack();
		setVisible(true);
		  
	}
}