package gui.ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.clases.Pelicula;
import gui.clases.Usuario;
import main.Deustocines;

public class VentanaOpiniones extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vActual;
    private JPanel panelSuperior, panelCentral, panelInferior;
    private JLabel lblTitulo, lblContadorOpiniones;
    private JButton botonOpinion, botonVolver;
    private JTextArea areaOpiniones;
    private int contadorOpiniones = 0;
    private Pelicula pelicula;
    private Deustocines deustocines;

    public VentanaOpiniones(JFrame vAnterior, Pelicula pelicula) {
        vActual = this;
        this.pelicula = pelicula;

        setTitle("Opiniones de " + pelicula.getTitulo());
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel superior
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(30, 144, 255));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        lblTitulo = new JLabel("Opiniones de " + pelicula.getTitulo(), JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Arial", Font.BOLD, 12));
        botonVolver.setBackground(Color.WHITE);
        botonVolver.setForeground(new Color(30, 144, 255));
        botonVolver.setFocusPainted(false); // Elimina el borde al enfocarse
        botonVolver.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        botonVolver.addActionListener(e -> {
            vActual.dispose();
            vAnterior.setVisible(true);
        });

        panelSuperior.add(botonVolver, BorderLayout.WEST);
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        // Panel central
        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        areaOpiniones = new JTextArea(); // Texto para opiniones
        areaOpiniones.setEditable(false);
        areaOpiniones.setFont(new Font("Arial", Font.PLAIN, 16));
        areaOpiniones.setLineWrap(true); // Envuelve las lineas automaticamente
        areaOpiniones.setWrapStyleWord(true); // Rompe las lineas por palabras
        JScrollPane scrollOpiniones = new JScrollPane(areaOpiniones);
        scrollOpiniones.setBorder(BorderFactory.createTitledBorder("Opiniones"));

        lblContadorOpiniones = new JLabel("Opiniones totales: " + contadorOpiniones);
        lblContadorOpiniones.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContadorOpiniones.setForeground(new Color(0, 51, 102));
        
        panelCentral.add(scrollOpiniones, BorderLayout.CENTER);
        panelCentral.add(lblContadorOpiniones, BorderLayout.SOUTH);

        // Panel inferior
        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(240, 240, 240));
        panelInferior.setBorder(new EmptyBorder(10, 10, 10, 10));

        botonOpinion = new JButton("Nueva Opinión");
        botonOpinion.setFont(new Font("Arial", Font.BOLD, 14));
        botonOpinion.setBackground(new Color(50, 205, 50)); // Verde vivo
        botonOpinion.setForeground(Color.WHITE);
        botonOpinion.setFocusPainted(false); // Sin borde al enfocarse
        botonOpinion.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        botonOpinion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Usuario.getUsuarioActual() != null) {
                    String nuevaOpinion = JOptionPane.showInputDialog(VentanaOpiniones.this, "Escribe tu opinión:");
                    if (nuevaOpinion != null && !nuevaOpinion.isEmpty()) {
                        areaOpiniones.append(Usuario.getUsuarioActual().getNomUsuario() + ": " + nuevaOpinion + "\n");
                        contadorOpiniones++; // Aumenta el contador
                        lblContadorOpiniones.setText("Opiniones totales: " + contadorOpiniones);
                        new Deustocines().guardarOpinionEnArchivo(Usuario.getUsuarioActual().getNomUsuario(), pelicula.getTitulo(), nuevaOpinion);
                    }
                } else {
                    int opciones = JOptionPane.showConfirmDialog(VentanaOpiniones.this,
                            "¿Estás registrado?", "REGISTRO", JOptionPane.YES_NO_OPTION);

                    if (opciones == JOptionPane.YES_OPTION) {
                        vActual.dispose();
                        new VentanaInicioSesion(vActual);
                    } else {
                        vActual.dispose();
                        new VentanaRegistro(vActual);
                    }
                }
            }
        });
        
        panelInferior.add(botonOpinion);

        // Agregar paneles a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        
        deustocines = new Deustocines();
        cargarOpiniones();

        setVisible(true);
    }
    
    private void cargarOpiniones() {
        HashMap<String, java.util.List<String>> opiniones = deustocines.opinionesPeliculas(pelicula.getTitulo());

        if (opiniones.isEmpty()) {
            areaOpiniones.setText("No hay opiniones todavía. ¡Sé el primero en opinar!\n");
        } else {
            areaOpiniones.setText("");
            for (String usuario : opiniones.keySet()) {
                java.util.List<String> listaOpiniones = opiniones.get(usuario);
                for (String opinion : listaOpiniones) {
                    areaOpiniones.append(usuario + ": " + opinion + "\n");
                }
            }
        }
    }
}
