package gui.ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInicioSesion extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vActual;
    private JPanel panelInferior, panelPrincipal, panelSuperior;
    private JLabel lblInicio, etiquetaRegistro;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JButton botonIniciarSesion, botonVolver;

    public VentanaInicioSesion(JFrame vAnterior) {
        vActual = this;
        // Configuración de la ventana
        setTitle("INICIO DE SESION");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Crear paneles
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(34, 34, 34));

        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(34, 34, 34));

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(4, 1, 10, 10));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta de título
        lblInicio = new JLabel("INICIAR SESION", JLabel.CENTER);
        lblInicio.setFont(new Font("Arial", Font.BOLD, 22));
        lblInicio.setForeground(Color.WHITE);

        // Campos de texto
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2), "Usuario o correo electrónico", 0, 0, new Font("Arial", Font.BOLD, 12), new Color(0, 102, 204)));

        txtContrasenia = new JPasswordField();
        txtContrasenia.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContrasenia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2), "Contraseña", 0, 0, new Font("Arial", Font.BOLD, 12), new Color(0, 102, 204)));

        // Botones
        botonIniciarSesion = new JButton("Iniciar Sesion");
        botonIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        botonIniciarSesion.setBackground(new Color(34, 153, 255));
        botonIniciarSesion.setForeground(Color.WHITE);
        botonIniciarSesion.setFocusPainted(false);
        botonIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciarSesion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Arial", Font.PLAIN, 14));
        botonVolver.setBackground(new Color(220, 220, 220));
        botonVolver.setForeground(Color.DARK_GRAY);
        botonVolver.setFocusPainted(false);
        botonVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Etiqueta de registro
        etiquetaRegistro = new JLabel("REGISTRARSE");
        etiquetaRegistro.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaRegistro.setForeground(Color.WHITE);
        etiquetaRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregar componentes a los paneles
        panelSuperior.add(lblInicio, BorderLayout.CENTER);
        panelSuperior.add(botonVolver, BorderLayout.WEST);

        panelPrincipal.add(txtUsuario);
        panelPrincipal.add(txtContrasenia);
        panelPrincipal.add(botonIniciarSesion);

        panelInferior.add(etiquetaRegistro);

        // Agregar paneles a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de inicio de sesión
                if (main.Deustocines.iniciarSesion(txtUsuario.getText(), new String(txtContrasenia.getPassword()))) {
                    JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Has iniciado sesión con éxito.");
                    
                    vActual.dispose();
                    new VentanaPrincipal(vActual);
                } else {
                    JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Usuario o Contraseña incorrecta.");
                }
            }
        });

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vActual.dispose();
                new VentanaPrincipal(vActual);
            }
        });

        etiquetaRegistro.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new VentanaRegistro(vActual);
                vActual.dispose();
            }

            // Métodos no utilizados
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        
        setVisible(true);
    }
}