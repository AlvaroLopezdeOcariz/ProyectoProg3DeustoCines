package gui.ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import gui.clases.Pelicula;
import gui.clases.Usuario;
import main.Deustocines;

public class VentanaOpiniones extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vActual, vAnterior;
    private JPanel panelSuperior, panelOpiniones, panelInferior;
    private JLabel lblTitulo, lblContadorOpiniones;
    private JButton botonOpinion, botonVolver;
    private JTextArea areaOpiniones;
    private int contadorOpiniones = 0;
    private Pelicula pelicula;
    private Deustocines deustocines;

    public VentanaOpiniones(JFrame vAnterior, Pelicula pelicula) {
        vActual = this;
        this.vAnterior = vAnterior;
        this.pelicula = pelicula;

        setTitle("Opiniones de " + pelicula.getTitulo());
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        // Panel superior
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(0, 51, 102));
        lblTitulo = new JLabel("Opiniones de " + pelicula.getTitulo(), JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);

        botonVolver = new JButton("Volver");
        botonVolver.setBackground(Color.LIGHT_GRAY);
        botonVolver.addActionListener(e -> {
            vActual.dispose();
            vAnterior.setVisible(true);
        });

        panelSuperior.add(botonVolver, BorderLayout.WEST);
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        // Área de opiniones
        areaOpiniones = new JTextArea(15, 50);
        areaOpiniones.setEditable(false);
        areaOpiniones.setFont(new Font("Arial", Font.PLAIN, 16));
        areaOpiniones.setLineWrap(true);
        areaOpiniones.setWrapStyleWord(true);
        JScrollPane scrollOpiniones = new JScrollPane(areaOpiniones);

        // Etiqueta para el contador de opiniones
        lblContadorOpiniones = new JLabel("Opiniones totales: " + contadorOpiniones);
        lblContadorOpiniones.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContadorOpiniones.setForeground(new Color(0, 51, 102));

        // Panel central
        panelOpiniones = new JPanel();
        panelOpiniones.setBackground(Color.WHITE);
        panelOpiniones.setLayout(new BoxLayout(panelOpiniones, BoxLayout.Y_AXIS));
        panelOpiniones.add(scrollOpiniones);
        panelOpiniones.add(Box.createVerticalStrut(10));
        panelOpiniones.add(lblContadorOpiniones);

        // Panel inferior
        panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(Color.LIGHT_GRAY);
        botonOpinion = new JButton("Nueva Opinión");
        botonOpinion.setFont(new Font("Arial", Font.BOLD, 14));
        botonOpinion.setBackground(new Color(0, 153, 76));
        botonOpinion.setForeground(Color.WHITE);
        botonOpinion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Usuario.getUsuarioActual() != null) {
                    String nuevaOpinion = JOptionPane.showInputDialog(VentanaOpiniones.this, "Escribe tu opinión:");
                    if (nuevaOpinion != null && !nuevaOpinion.isEmpty()) {
                        areaOpiniones.append(Usuario.getUsuarioActual().getNomUsuario() + ": " + nuevaOpinion + "\n");
                        contadorOpiniones++;
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
        add(panelOpiniones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        deustocines = new Deustocines();
        HashMap<String, java.util.List<String>> opiniones = deustocines.opinionesPeliculas(pelicula.getTitulo());

        // Si no hay opiniones, mostramos un mensaje indicativo
        if (opiniones.isEmpty()) {
            areaOpiniones.setText("No hay opiniones todavía. ¡Sé el primero en opinar!\n");
        } else {
            // Limpia el área de texto antes de añadir las opiniones
            areaOpiniones.setText("");
            // Añadir las opiniones directamente al JTextArea
            for (String usuario : opiniones.keySet()) {
                java.util.List<String> listaOpiniones = opiniones.get(usuario);
                for (String opinion : listaOpiniones) {
                    areaOpiniones.append(usuario + ": " + opinion + "\n");
                }
            }
        }

       
        
        setVisible(true);
    }
}