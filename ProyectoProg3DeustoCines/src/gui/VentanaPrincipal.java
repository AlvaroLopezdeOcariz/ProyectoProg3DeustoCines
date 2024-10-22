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

public class VentanaPrincipal extends JFrame {
	public VentanaPrincipal() {
		JFrame vActual;
		vActual=this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DEUSTOCINES");
		setSize(800,600);
		
		
		JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE); 
        mainPanel.setLayout(new BorderLayout());
        
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BorderLayout());
        
        JPanel botonPanel = new JPanel();
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
						
					}							
					else {
					JOptionPane.showMessageDialog(VentanaPrincipal.this,"Has Tocado el Boton");
					}
				}
			});
        	
        }
        
       //DEUSTOCINES
        JLabel deustoCinesLabel = new JLabel("DeustoCines");
        deustoCinesLabel.setForeground(Color.WHITE);
        deustoCinesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuPanel.add(deustoCinesLabel, BorderLayout.WEST);
        menuPanel.add(botonPanel, BorderLayout.EAST);
        add(menuPanel,BorderLayout.NORTH);
      
        //el panel done pone polpulares ahora mismo
        JPanel titulosPeliculas = new JPanel();
       titulosPeliculas.setBackground(Color.BLACK);
       titulosPeliculas.setLayout(new FlowLayout(FlowLayout.LEFT,20,80));
       mainPanel.add(titulosPeliculas, BorderLayout.NORTH);

       
       
       JLabel populares= new JLabel("Populares Ahora Mismo: ");
       populares.setForeground(Color.WHITE);
       populares.setFont(new Font("ARIAL", Font.BOLD, 20));
       titulosPeliculas.add(populares);
       	
       
		
		add(mainPanel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaPrincipal();
	}
}
