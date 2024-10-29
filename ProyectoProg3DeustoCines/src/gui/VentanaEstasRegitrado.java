package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEstasRegitrado extends JFrame {

	JLabel lblPregunta;
	 JFrame vActual, vAnterior;
	
	
	public  VentanaEstasRegitrado(JFrame vAnterior){
		 vActual = this;
		  this.vAnterior= vAnterior;

		  setTitle("Estas Registrado?");
		  setSize(400,300);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	  lblPregunta = new JLabel("¿Tienes cuenta DeustoCines?",JLabel.CENTER);
	  lblPregunta.setFont(new Font("Arial", Font.BOLD, 22));
	  
	  JPanel panelSuperior = new JPanel(new BorderLayout());
	  panelSuperior.setBackground(Color.MAGENTA);
	  JPanel panelInferior = new JPanel(new GridLayout(1,4,40,0));
	  
	  
	  JButton botonSi = new JButton("SI");
	  JButton botonNo = new JButton("NO");
	  
	  panelSuperior.add(lblPregunta);
	  
	  panelInferior.add(botonSi);
	  panelInferior.add(botonNo);
	  
	  
	  
	  
	  
	  add(panelSuperior,BorderLayout.CENTER);
	  add(panelInferior,BorderLayout.SOUTH);
	  
	  
	  botonSi.addActionListener(new ActionListener() {
		  
		  public void actionPerformed(ActionEvent e) {
			  vActual.dispose();
				new VentanaInicioSesion(vActual);
		  }
	  });
	  
	  
	  botonNo.addActionListener(new ActionListener() {
		  
		  public void actionPerformed(ActionEvent e) {
			  vActual.dispose();
				new VentanaRegistro(vActual);
		  }
	  });
	  
	  setVisible(true);
	}
}
