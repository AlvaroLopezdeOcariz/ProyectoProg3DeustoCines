package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EstasRegitrado extends JFrame {

	JLabel lblPregunta;
	 JFrame vActual, vAnterior;
	
	
	public  EstasRegitrado(JFrame vAnterior){
		 vActual = this;
		  this.vAnterior= vAnterior;

		  setTitle("Estas Registrado?");
		  setSize(400,300);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	  lblPregunta = new JLabel("¿Tienes cuenta DeustoCines?",JLabel.CENTER);
	  lblPregunta.setFont(new Font("Arial", Font.BOLD, 22));
	  
	  JPanel panelSuperior = new JPanel(new BorderLayout());
	  panelSuperior.setBackground(Color.MAGENTA);
	  JPanel panelInferior = new JPanel(new BorderLayout());
	  
	  
	  JButton botonSi = new JButton("SI");
	  JButton botonNo = new JButton("NO");
	  
	  panelSuperior.add(lblPregunta);
	  
	  panelInferior.add(botonNo,BorderLayout.EAST);
	  panelInferior.add(botonSi,BorderLayout.WEST);
	  
	  
	  
	  
	  add(panelSuperior,BorderLayout.CENTER);
	  add(panelInferior,BorderLayout.SOUTH);
	  
	  
	  botonSi.addActionListener(new ActionListener() {
		  
		  public void actionPerformed(ActionEvent e) {
			  vActual.dispose();
				vAnterior.setVisible(true);
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
