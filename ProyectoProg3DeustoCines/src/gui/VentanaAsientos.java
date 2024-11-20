package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class VentanaAsientos extends JDialog {
    private static final long serialVersionUID = 1L;

    private Set<String> asientosSeleccionados;
    private Timer temporizador;
    private int tiempoRestante = 7 * 60; // 420 segundos de límite

    // Constructor de la ventana
    public VentanaAsientos(JFrame padre) {
        super(padre, "Seleccionar Asientos", true); // Ventana modal (bloquea la ventana anterior mientras está abierta)
        asientosSeleccionados = new HashSet<>(); // Conjunto para guardar asientos seleccionados sin duplicados
        setSize(700, 500); 
        setLayout(new BorderLayout());
        setLocationRelativeTo(padre); // Centrar la ventana respecto a su "padre"

        // ** Panel que muestra los botones de los asientos **
        JPanel panelAsientos = new JPanel(new GridLayout(10, 10, 5, 5)); // Cuadrícula: 10 filas x 10 columnas con espacio entre botones
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                String asiento = (fila + 1) + "-" + (columna + 1);
                JButton botonAsiento = new JButton(asiento);
                botonAsiento.setBackground(Color.GREEN);
                botonAsiento.addActionListener(e -> { 
                    if (asientosSeleccionados.contains(asiento)) {
                        asientosSeleccionados.remove(asiento);
                        botonAsiento.setBackground(Color.GREEN);
                    } else {
                        asientosSeleccionados.add(asiento);
                        botonAsiento.setBackground(Color.RED);
                    }
                });
                panelAsientos.add(botonAsiento);
            }
        }

        // Panel inferior con el temporizador y botón de confirmación
        JPanel panelInferior = new JPanel(new BorderLayout());
        JLabel labelTiempo = new JLabel("Tiempo restante: " + tiempoRestante + "s");
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> {
            temporizador.stop();
            JOptionPane.showMessageDialog(this, "Asientos confirmados: " + asientosSeleccionados);
            dispose(); // Cerrar la ventana
        });

        // Temporizador para limitar el tiempo de selección
        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;

                // Calcular minutos y segundos
                int minutos = tiempoRestante / 60;
                int segundos = tiempoRestante % 60;

                // Actualizar el texto del temporizador en formato "MM:SS"
                labelTiempo.setText(String.format("Tiempo restante: %02d:%02d", minutos, segundos));

                if (tiempoRestante == 0) {
                    temporizador.stop();
                    JOptionPane.showMessageDialog(VentanaAsientos.this, "Tiempo agotado. Selección cancelada.");
                    dispose(); // Cerrar la ventana
                }
            }
        });
        temporizador.start();

        // Añadir componentes al panel inferior
        panelInferior.add(labelTiempo, BorderLayout.WEST);
        panelInferior.add(btnConfirmar, BorderLayout.EAST);

        // Añadir paneles a la ventana principal
        add(panelAsientos, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
}
