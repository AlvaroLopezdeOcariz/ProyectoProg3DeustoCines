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
        setSize(500, 600);
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

        // Categorías y datos del menú
        String[][] categorias = {
                {"Comida principal", "Hamburguesa", "Pizza", "Hot Dog"},
                {"Snacks", "Palomitas", "Nachos", "Patatas fritas"},
                {"Bebidas", "Refresco", "Agua", "Cerveza"}
        };
        double[][] precios = {
                {8.0, 7.5, 6.0}, // Precios de Comida principal
                {5.0, 4.5, 3.5}, // Precios de Snacks
                {3.0, 1.5, 4.0}  // Precios de Bebidas
        };

        Map<String, JSpinner> spinners = new HashMap<>();

        for (int cat = 0; cat < categorias.length; cat++) {
            // Título de la categoría
            JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelCategoria.setBackground(backgroundColor);

            JLabel labelCategoria = new JLabel("• " + categorias[cat][0]);
            labelCategoria.setFont(new Font("Arial", Font.BOLD, 14));
            labelCategoria.setForeground(textColor);
            panelCategoria.add(labelCategoria);

            panelMenu.add(panelCategoria);

            // Crear opciones de comida dentro de la categoría
            for (int item = 1; item < categorias[cat].length; item++) {
                String comida = categorias[cat][item];
                double precio = precios[cat][item - 1];

                JPanel panelComida = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panelComida.setBackground(backgroundColor);

                // Etiqueta estilizada para la comida
                JLabel labelComida = new JLabel("   " + comida + " (€" + precio + ")");
                labelComida.setFont(new Font("Arial", Font.PLAIN, 14));
                labelComida.setForeground(textColor);
                panelComida.add(labelComida);

                // Spinner para seleccionar cantidad
                JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
                spinners.put(comida, spinnerCantidad);
                spinnerCantidad.setPreferredSize(new Dimension(50, 25));
                panelComida.add(spinnerCantidad);

                // Añadir cada opción al panel principal
                panelMenu.add(panelComida);
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
            for (int cat = 0; cat < categorias.length; cat++) {
                for (int item = 1; item < categorias[cat].length; item++) {
                    String comida = categorias[cat][item];
                    int cantidad = (int) spinners.get(comida).getValue();
                    if (cantidad > 0) {
                        comidaSeleccionada.put(comida, cantidad);
                        totalComida += cantidad * precios[cat][item - 1];
                    }
                }
            }

            JOptionPane.showMessageDialog(this, generarResumenCompra(), "Resumen de Compra", JOptionPane.INFORMATION_MESSAGE);
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

    private String generarResumenCompra() {
        StringBuilder resumen = new StringBuilder("Resumen de su compra:\n\n");
        comidaSeleccionada.forEach((comida, cantidad) -> resumen.append(comida)
                .append(" x ")
                .append(cantidad)
                .append("\n"));
        resumen.append("\nTotal: €").append(String.format("%.2f", totalComida));
        return resumen.toString();
    }

    public double getTotalComida() {
        return totalComida;
    }

    public Map<String, Integer> getComidaSeleccionada() {
        return comidaSeleccionada;
    }
}
