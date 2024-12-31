package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bd.BDPeliculas;
import gui.clases.Pelicula;



public class VentanaAdministracion extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JFrame vActual, vAnterior;
	private JPanel panelSuperior;
	private JButton botonVolver;
	private DefaultTableModel modeloDatos;
	private JTable tabla;
	public VentanaAdministracion(JFrame ventanaAnterior){
		vActual = this;
		this.vAnterior = ventanaAnterior;
	
		
		
		setSize(800,600);
		setTitle("Tabla Administración");
		JPanel panel = new JPanel(new BorderLayout());
		
		  this.modeloDatos = new DefaultTableModel(new String[]{"Título", "Productora", "Presupuesto", "Recaudación", "Rentabilidad"}, 0) {
	            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				// Sobrescribir isCellEditable para que todas las celdas sean editables
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return true; // Todas las celdas son editables
	            }
	        };
	        
	       
	        
	        
	        tabla = new JTable(modeloDatos);
			
			JScrollPane scrollProduct = new JScrollPane(tabla);
			 
			panel.add(scrollProduct, BorderLayout.CENTER); 
			  
			this.loadPro();
			
			TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
				  JLabel result = new JLabel(value.toString());
				
				  
				  if(value.equals(true)) {
					  result.setText("Rentable");
					  result.setBackground(Color.GREEN);
				  }
				  else if(value.equals(false)) {
					  result.setText("Fracaso");
					  result.setBackground(Color.RED);
				  }
				  
				  result.setOpaque(true);
				return result;
				  
				  
				  
			
			  };
			  
			  tabla.setDefaultRenderer(Object.class, cellRenderer);
			  add(panel,BorderLayout.CENTER);
			  
			  panelSuperior = new JPanel(new BorderLayout());
			  botonVolver = new JButton("Volver");	
			  
			  panelSuperior.add(botonVolver,BorderLayout.WEST);
			  botonVolver.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vActual.dispose();
					vAnterior.setVisible(true);
					
				}
			});
			  
			  
			  
			  add(panelSuperior, BorderLayout.NORTH);
			  
			  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			  setVisible(true);
			


	}
	private void loadPro() {
		ArrayList<Pelicula> listaPeliculas = BDPeliculas.obtenerPeliculas();
	     DecimalFormat formato= new DecimalFormat("#,###");
		 
		 this.modeloDatos.setRowCount(0);
			
		 // Añadir las películas al modelo de datos
	        listaPeliculas.forEach(peli -> {
	            String presupuestoFormateado = formato.format(peli.getPresupuesto());
	            String recaudacionFormateada = formato.format(peli.getTaquilla());
	     

	            this.modeloDatos.addRow(new Object[] { peli.getTitulo(), peli.getProductora(), presupuestoFormateado, recaudacionFormateada, peli.getRentabilidad()
	            });
	        });
	    }
}
