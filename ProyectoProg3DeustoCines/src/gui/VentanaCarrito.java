package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.Deustocines;
import java.awt.*;

public class VentanaCarrito extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private JFrame ventanaPrincipal; // Guardamos la referencia a la ventana principal

    public VentanaCarrito(JFrame parent) {
        super(parent, "Carrito de Compras", true);
        this.ventanaPrincipal = parent; // Guardamos la referencia al JFrame
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

     // Etiqueta de título
        JLabel lblTitulo = new JLabel("Carrito de Compras", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(50, 50, 150));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla de carrito
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Descripción", "Precio (€)"}, 0);

        // Añadir los ítems del carrito
        for (var item : Deustocines.carrito.getItems()) {
            modeloTabla.addRow(new Object[]{item.getDescripcion(), item.getPrecio()});
        }

        // Añadir los asientos seleccionados
        for (var asiento : Deustocines.carrito.getAsientos()) {
            modeloTabla.addRow(new Object[]{"Asiento: " + asiento, 10.0}); // Precio fijo de ejemplo
        }

        JTable tablaCarrito = new JTable(modeloTabla);
        tablaCarrito.setRowHeight(30);
        tablaCarrito.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaCarrito.getTableHeader().setBackground(new Color(200, 200, 255));
        tablaCarrito.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);

        // Panel inferior para total y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Total
        JLabel lblTotal = new JLabel("Total: €" + String.format("%.2f", Deustocines.carrito.calcularTotal()));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInferior.add(lblTotal, BorderLayout.NORTH);

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 0));

        JButton btnPagar = new JButton("Pagar");
        btnPagar.setFont(new Font("Arial", Font.BOLD, 14));
        btnPagar.setBackground(new Color(50, 200, 50));
        btnPagar.setForeground(Color.WHITE);
        btnPagar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Compra realizada. ¡Gracias!");
            Deustocines.carrito.limpiarCarrito();
            dispose();
        });

        JButton btnLimpiar = new JButton("Limpiar Carrito");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
        btnLimpiar.setBackground(new Color(200, 50, 50));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.addActionListener(e -> {
            Deustocines.carrito.limpiarCarrito();
            JOptionPane.showMessageDialog(this, "Carrito vacío.");
            dispose();
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(50, 150, 200));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.addActionListener(e -> {
            if (ventanaPrincipal != null) { // Validamos que ventanaPrincipal no sea null
                this.setVisible(false); // Oculta la ventana del carrito
                ventanaPrincipal.setVisible(true); // Muestra la ventana principal
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo volver a la ventana principal.", "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        });

        panelBotones.add(btnPagar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }
}
