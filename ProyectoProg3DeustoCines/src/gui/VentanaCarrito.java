package gui;

import javax.swing.*;
import main.Deustocines;
import java.awt.*;

public class VentanaCarrito extends JDialog {
    private static final long serialVersionUID = 1L;

    public VentanaCarrito(JFrame parent) {
        super(parent, "Carrito", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Crear datos para la tabla
        String[] columnas = {"Descripción", "Precio (€)"};
        Object[][] datos = Deustocines.carrito.getItems().isEmpty()
            ? new Object[][]{{"Carrito vacío", ""}}
            : Deustocines.carrito.getItems().stream()
                .map(item -> new Object[]{item.getDescripcion(), item.getPrecio()})
                .toArray(Object[][]::new);

        // Crear tabla para mostrar ítems
        JTable tablaCarrito = new JTable(datos, columnas);
        add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);

        // Mostrar el total
        double total = Deustocines.carrito.calcularTotal();
        JLabel lblTotal = new JLabel("Total: €" + String.format("%.2f", total));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTotal, BorderLayout.NORTH);

        // Botones para finalizar compra o limpiar carrito
        JPanel panelBotones = new JPanel();
        JButton btnPagar = new JButton("Pagar");
        JButton btnLimpiar = new JButton("Limpiar Carrito");

        btnPagar.addActionListener(e -> {
            if (total > 0) {
                JOptionPane.showMessageDialog(this, "Compra realizada. ¡Gracias!");
                Deustocines.carrito.limpiarCarrito();
            } else {
                JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            }
            dispose();
        });

        btnLimpiar.addActionListener(e -> {
            Deustocines.carrito.limpiarCarrito();
            dispose();
        });

        panelBotones.add(btnPagar);
        panelBotones.add(btnLimpiar);
        add(panelBotones, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
