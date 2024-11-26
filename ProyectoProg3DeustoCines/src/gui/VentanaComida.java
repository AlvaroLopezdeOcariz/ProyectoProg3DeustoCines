package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaComida extends JDialog {
    private static final long serialVersionUID = 1L;
    private Map<String, Integer> comidaSeleccionada = new HashMap<>();
    private double totalComida = 0.0;

    public VentanaComida(VentanaAsientos ventanaAsientos) {
        super(ventanaAsientos, "Menú de Comida", true);
        setSize(450, 350);
        setLocationRelativeTo(ventanaAsientos);
        setLayout(new BorderLayout());
        
        // Configurar colores
        Color backgroundColor = new Color(240, 248, 255);
        Color buttonColor = new Color(100, 149, 237);
        Color textColor = Color.BLACK;

        // Panel principal para el menú
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBackground(backgroundColor);
        panelMenu.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] comidas = {"Palomitas", "Refresco", "Chocolate"};
        double[] precios = {5.0, 3.0, 2.5};
        Map<String, JSpinner> spinners = new HashMap<>();

        for (int i = 0; i < comidas.length; i++) {
            String comida = comidas[i];
            double precio = precios[i];

            // Panel horizontal para cada comida
            JPanel panelComida = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelComida.setBackground(backgroundColor);
            
            // Etiqueta estilizada
            JLabel labelComida = new JLabel(comida + " (€" + precio + ")");
            labelComida.setFont(new Font("Arial", Font.BOLD, 14));
            labelComida.setForeground(textColor);
            panelComida.add(labelComida);

            // Spinner para seleccionar cantidad
            JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)); // De 0 a 10
            spinners.put(comida, spinnerCantidad);
            spinnerCantidad.setPreferredSize(new Dimension(50, 25));
            panelComida.add(spinnerCantidad);

            // Añadir panel horizontal al panel principal
            panelMenu.add(panelComida);
            
            // Separador para mayor claridad
            if (i < comidas.length - 1) {
                JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                separator.setForeground(Color.LIGHT_GRAY);
                panelMenu.add(separator);
            }
        }

        add(new JScrollPane(panelMenu), BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(backgroundColor);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(buttonColor);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Color.LIGHT_GRAY);
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFocusPainted(false);

        btnConfirmar.addActionListener(e -> {
            totalComida = 0.0;
            comidaSeleccionada.clear();

            // Procesar selección
            for (int i = 0; i < comidas.length; i++) {
                String comida = comidas[i];
                int cantidad = (int) spinners.get(comida).getValue();
                if (cantidad > 0) {
                    comidaSeleccionada.put(comida, cantidad);
                    totalComida += cantidad * precios[i];
                }
            }

            JOptionPane.showMessageDialog(this, "Compra de comida confirmada.\n"
                    + "Total: €" + totalComida);
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);
        
        // Título en la parte superior
        JLabel titulo = new JLabel("Seleccione su comida");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(textColor);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);
    }

    public double getTotalComida() {
        return totalComida;
    }

    public Map<String, Integer> getComidaSeleccionada() {
        return comidaSeleccionada;
    }
}
