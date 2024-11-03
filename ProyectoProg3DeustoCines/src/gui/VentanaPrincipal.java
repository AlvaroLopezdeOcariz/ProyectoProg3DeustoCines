package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Usuario;

public class VentanaPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame vActual, vAnterior;
	private JPanel mainPanel, menuPanel, botonPanel, titulosPeliculas;
	private JLabel deustoCinesLabel, populares;

	public VentanaPrincipal() {
		vActual=this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DEUSTOCINES");
		setSize(800,600);
		
		
		mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE); 
        mainPanel.setLayout(new BorderLayout());
        
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BorderLayout());
        
        botonPanel = new JPanel();
        botonPanel.setBackground(Color.BLACK);
        botonPanel.setLayout(new FlowLayout());
        
        //botones parte superior derecha
        String[] menuItems = {"CARTELERA", "OPINIONES","IDIOMA" ,"INICIAR SESION/REGISTRARSE"};
        
        for(String item: menuItems) {
        	JButton boton = new JButton(item);
        	boton.setForeground(Color.WHITE);
        	boton.setBackground(Color.BLACK);
        	boton.setFocusPainted(false);       
            boton.setBorderPainted(false);
        	botonPanel.add(boton);
        	
        	
        	boton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (boton.getText().equals("INICIAR SESION/REGISTRARSE")){
						vActual.dispose();//Cerrar ventana actual
						new VentanaInicioSesion(vActual);
						
					}else if(boton.getText().equals("OPINIONES")){
						
						vActual.dispose();
						new VentanaOpiniones(vActual);
					}else if(boton.getText().equals("CARTELERA")){
						
						vActual.dispose();
						new VentanaCartelera(vActual);
					}
					
					else {
					JOptionPane.showMessageDialog(VentanaPrincipal.this,"Has Tocado el Boton");
					}
				}
			});
        	
        }
        
       //DEUSTOCINES
        deustoCinesLabel = new JLabel("DeustoCines");
        deustoCinesLabel.setForeground(Color.BLACK);
        deustoCinesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuPanel.add(deustoCinesLabel, BorderLayout.WEST);
        menuPanel.add(botonPanel, BorderLayout.EAST);
        add(menuPanel,BorderLayout.NORTH);
      
        //el panel done pone polpulares ahora mismo
        titulosPeliculas = new JPanel();
        titulosPeliculas.setBackground(Color.BLACK);
        titulosPeliculas.setLayout(new FlowLayout(FlowLayout.LEFT,20,80));
        mainPanel.add(titulosPeliculas, BorderLayout.NORTH);

       
       
        populares= new JLabel("Populares Ahora Mismo: ");
        populares.setForeground(Color.WHITE);
        populares.setFont(new Font("ARIAL", Font.BOLD, 20));
        titulosPeliculas.add(populares);
       	
       
		
		add(mainPanel);
		setVisible(true);
	}
	
}
