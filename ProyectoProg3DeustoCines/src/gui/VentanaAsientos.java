package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class VentanaAsientos extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTable tablaAsientos;
    private DefaultTableModel modeloTabla;
    private Set<String> asientosSeleccionados;
    private JLabel labelTiempo, labelTotal; // Label -- tiempo restante y total a pagar
    private int tiempoRestante = 7 * 60; // 7 minutos en segundos
    private double precioTotal = 0.0; // Precio total de los asientos seleccionados

    public VentanaAsientos(JFrame padre) {
        super(padre, "Seleccionar Asientos", true);
        asientosSeleccionados = new HashSet<>();
        setSize(730, 530);
        setLayout(new BorderLayout());
        setLocationRelativeTo(padre);

        // Panel superior: Pantalla
        JLabel labelPantalla = new JLabel("Pantalla", SwingConstants.CENTER);
        labelPantalla.setFont(new Font("Arial", Font.BOLD, 20));
        labelPantalla.setOpaque(true);
        labelPantalla.setBackground(Color.LIGHT_GRAY);
        labelPantalla.setPreferredSize(new Dimension(getWidth(), 30));
        add(labelPantalla, BorderLayout.NORTH);

        // Crear tabla de asientos
        modeloTabla = new DefaultTableModel(8, 12) { // 8 filas x 12 columnas
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Las celdas no son editables
            }
        };

        tablaAsientos = new JTable(modeloTabla);
        tablaAsientos.getTableHeader().setReorderingAllowed(false); // No se pueden mover las columnas
        tablaAsientos.setRowHeight(50); // Altura de cada fila
        tablaAsientos.setDefaultRenderer(Object.class, new AsientosRenderer()); // Renderer personalizado para las celdas
        tablaAsientos.addMouseListener(new java.awt.event.MouseAdapter() { // Listener para los clicks
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaAsientos.getSelectedRow();
                int columna = tablaAsientos.getSelectedColumn();
                String asiento = String.format("%c%d", 'A' + fila, columna + 1);

                if (modeloTabla.getValueAt(fila, columna).equals("Ocupado")) {
                    return; // No se pueden seleccionar asientos ocupados
                }

                if (asientosSeleccionados.contains(asiento)) {
                    asientosSeleccionados.remove(asiento);
                    precioTotal -= obtenerPrecio(fila);
                    modeloTabla.setValueAt("Libre", fila, columna);
                } else {
                    asientosSeleccionados.add(asiento);
                    precioTotal += obtenerPrecio(fila);
                    modeloTabla.setValueAt("Seleccionado", fila, columna);
                }

                actualizarPrecioTotal();
            }
        });

        inicializarAsientos();
        add(new JScrollPane(tablaAsientos), BorderLayout.CENTER);

        // Panel inferior: Temporizador, precio y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        labelTiempo = new JLabel("Tiempo restante: 07:00");
        labelTotal = new JLabel("Total: €0.0");
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar Compra");

        btnConfirmar.addActionListener(e -> { // Accion al confirmar
            JOptionPane.showMessageDialog(this, "Compra confirmada.\nAsientos: " + asientosSeleccionados + "\nTotal: €" + precioTotal);
            dispose();
        });

        btnCancelar.addActionListener(e -> { // Accion al cancelar
            JOptionPane.showMessageDialog(this, "Compra cancelada.");
            dispose();
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);
        panelInferior.add(labelTiempo, BorderLayout.WEST);
        panelInferior.add(labelTotal, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);

        // Hilo para manejar el temporizador
        new Thread(() -> {
            while (tiempoRestante > 0) {
                try {
                    Thread.sleep(1000); // Dormimos 1 segundo
                    tiempoRestante--; // Reducimos 1 segundo del tiempo restante
                    SwingUtilities.invokeLater(() -> { // Actualizamos la interfaz en el hilo
                        int minutos = tiempoRestante / 60;
                        int segundos = tiempoRestante % 60;
                        labelTiempo.setText(String.format("Tiempo restante: %02d:%02d", minutos, segundos));
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(VentanaAsientos.this, "Tiempo agotado. Selección cancelada.");
            dispose(); // Se cierra la ventana si se acaba el tiempo
        }).start(); // Iniciamos el hilo
    }

    // Inicializar asientos con disponibilidad
    private void inicializarAsientos() {
        for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
            for (int columna = 0; columna < modeloTabla.getColumnCount(); columna++) {
                if (Math.random() < 0.2) { // 20% de probabilidad de estar ocupado
                    modeloTabla.setValueAt("Ocupado", fila, columna);
                } else {
                    modeloTabla.setValueAt("Libre", fila, columna);
                }
            }
        }
    }

    // Obtener precio según la fila
    private double obtenerPrecio(int fila) {
        if (fila < 2) return 15.0; // Fila VIP
        if (fila < 5) return 10.0; // Fila estándar
        return 7.0; // Fila económica
    }

    // Actualizar el total en tiempo real
    private void actualizarPrecioTotal() {
        labelTotal.setText(String.format("Total: €%.2f", precioTotal)); // Precio total con dos decimales
    }

    // Renderer para personalizar la apariencia de las celdas
    private class AsientosRenderer extends JLabel implements TableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER); // Texto centrado en la celda
            setOpaque(true);

            if (value.equals("Ocupado")) {
                setBackground(Color.GRAY);
                setForeground(Color.WHITE);
            } else if (value.equals("Seleccionado")) {
                setBackground(Color.RED);
                setForeground(Color.WHITE);
            } else { // Libre
                if (row < 2) setBackground(new Color(255, 215, 0)); // VIP
                else if (row < 5) setBackground(new Color(173, 216, 230)); // Estándar
                else setBackground(new Color(144, 238, 144)); // Económica
                setForeground(Color.BLACK);
            }

            setText(value.toString());
            return this;
        }
    }
}
