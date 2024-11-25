package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vActual;
    private JPanel mainPanel, menuPanel, botonPanel, titulosPeliculas;
    private JLabel deustoCinesLabel, populares;

    public VentanaPrincipal() {
        vActual = this;

        // Configuración de la ventana principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DEUSTOCINES");
        setSize(800, 600);

        // Panel principal con fondo azul
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(30, 30, 70)); // Azul oscuro
        mainPanel.setLayout(new BorderLayout());

        // Panel del menú superior
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BorderLayout());

        // Panel donde estarán los botones
        botonPanel = new JPanel();
        botonPanel.setBackground(Color.WHITE);
        botonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        // Crear los botones
        String[] menuItems = {"CARTELERA", "ADMINISTRADOR", "INICIAR SESION/REGISTRARSE"};
        for (String item : menuItems) {
            JButton boton = new JButton(item);
            boton.setFont(new Font("Verdana", Font.BOLD, 14));
            boton.setBackground(new Color(0, 128, 255)); 
            boton.setFocusPainted(false);
            boton.setOpaque(true);
            botonPanel.add(boton);

            // Asignar la acción a cada botón
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (boton.getText().equals("INICIAR SESION/REGISTRARSE")) {
                        vActual.dispose(); // Cerrar ventana actual
                        new VentanaInicioSesion(vActual);
                    } else if (boton.getText().equals("CARTELERA")) {
                        vActual.dispose(); 
                        new VentanaCartelera(vActual);
                    } else if (boton.getText().equals("ADMINISTRADOR")) {
                        vActual.dispose();
                        new VentanaAdministracion(vActual);
                    }
                }
            });
        }

        // Etiqueta "DEUSTOCINES"
        deustoCinesLabel = new JLabel("DeustoCines");
        deustoCinesLabel.setForeground(new Color(70, 130, 180)); // 
        deustoCinesLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        menuPanel.add(deustoCinesLabel, BorderLayout.WEST);
        menuPanel.add(botonPanel, BorderLayout.EAST);
        add(menuPanel, BorderLayout.NORTH);

        // Panel para el título "Populares Ahora Mismo"
        titulosPeliculas = new JPanel();
        titulosPeliculas.setBackground(new Color(30, 30, 70)); // Mismo color que el fondo principal
        titulosPeliculas.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 80));
        mainPanel.add(titulosPeliculas, BorderLayout.CENTER);

        // Etiqueta "Populares Ahora Mismo"
        populares = new JLabel("Populares Ahora Mismo: ");
        populares.setForeground(Color.WHITE);
        populares.setFont(new Font("Verdana", Font.BOLD, 20));
        titulosPeliculas.add(populares);

        // Agregar el panel principal a la ventana
        add(mainPanel);
        setVisible(true);
    }
    
}
