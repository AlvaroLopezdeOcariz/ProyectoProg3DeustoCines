package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaComida extends JDialog {
    private static final long serialVersionUID = 1L;
    private Map<String, Integer> comidaSeleccionada = new HashMap<>();
    private double totalComida = 0.0;

    public VentanaComida(VentanaAsientos ventanaAsientos) {
        super(ventanaAsientos, "Menú de Comida", true);
        setSize(400, 300);
        setLocationRelativeTo(ventanaAsientos);
        setLayout(new BorderLayout());

        // Panel principal para el menú
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        String[] comidas = {"Palomitas", "Refresco", "Chocolate"};
        double[] precios = {5.0, 3.0, 2.5};
        Map<String, JSpinner> spinners = new HashMap<>();

        for (int i = 0; i < comidas.length; i++) {
            String comida = comidas[i];
            double precio = precios[i];

            // Panel horizontal para cada comida
            JPanel panelComida = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelComida.add(new JLabel(comida + " (€" + precio + ")"));

            // Spinner para seleccionar cantidad
            JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)); // De 0 a 10
            spinners.put(comida, spinnerCantidad);
            panelComida.add(spinnerCantidad);

            // Añadir panel horizontal al panel principal
            panelMenu.add(panelComida);
        }

        add(new JScrollPane(panelMenu), BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

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
    }

    public double getTotalComida() {
        return totalComida;
    }

    public Map<String, Integer> getComidaSeleccionada() {
        return comidaSeleccionada;
    }
}
