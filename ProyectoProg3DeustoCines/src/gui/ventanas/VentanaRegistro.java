package gui.ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaRegistro extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vActual;
    private JPanel panelSuperior, panelPrincipal, panelInferior;
    private JButton botonVolver, botonRegistrarse;
    private JLabel lblRegistro;
  
    private JTextField txtNombre, txtUsuario, txtContrasenia;

    public VentanaRegistro(JFrame vAnterior) {
        vActual = this;
        setTitle("REGISTRARSE");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear paneles
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(34, 34, 34));

        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(34, 34, 34));

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta de título
        lblRegistro = new JLabel("REGISTRATE", JLabel.CENTER);
        lblRegistro.setFont(new Font("Arial", Font.BOLD, 22));
        lblRegistro.setForeground(Color.WHITE);

        // Campos de texto
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNombre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2), "Ingresa tu nombre", 0, 0, new Font("Arial", Font.BOLD, 12), new Color(0, 102, 204)));

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2), "Ingresa tu Usuario o correo electrónico", 0, 0, new Font("Arial", Font.BOLD, 12), new Color(0, 102, 204)));

        txtContrasenia = new JPasswordField();
        txtContrasenia.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContrasenia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2), "Ingresa tu Contraseña", 0, 0, new Font("Arial", Font.BOLD, 12), new Color(0, 102, 204)));

        

        

        // Organizar los campos en el panel principal
        JPanel panelArriba = new JPanel(new GridLayout(4, 1, 10, 10));
        panelArriba.setBackground(Color.WHITE);
        panelArriba.add(txtNombre);
        panelArriba.add(txtUsuario);
        panelArriba.add(txtContrasenia);
        

        panelPrincipal.add(panelArriba);

        // Botones
        botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Arial", Font.PLAIN, 14));
        botonVolver.setBackground(new Color(220, 220, 220));
        botonVolver.setForeground(Color.DARK_GRAY);
        botonVolver.setFocusPainted(false);
        botonVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setFont(new Font("Arial", Font.PLAIN, 16));
        botonRegistrarse.setBackground(new Color(34, 153, 255));
        botonRegistrarse.setForeground(Color.WHITE);
        botonRegistrarse.setFocusPainted(false);
        botonRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonRegistrarse.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Agregar componentes a los paneles
        panelSuperior.add(lblRegistro, BorderLayout.CENTER);
        panelSuperior.add(botonVolver, BorderLayout.WEST);

        panelInferior.add(botonRegistrarse);

        // Agregar paneles a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Acción de los botones
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
                if (main.Deustocines.IniciarRegistro(txtNombre.getText(), txtUsuario.getText(), txtContrasenia.getText(),false)) {
                    JOptionPane.showMessageDialog(VentanaRegistro.this, "Te has registrado con éxito.");
                    vActual.dispose();
                    vAnterior.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaRegistro.this, "Este usuario ya está registrado. Prueba con otro nombre de usuario o contraseña.");
                }
            }
        });

        setVisible(true);
    }
}