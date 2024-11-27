package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



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
	private ArrayList<Pelicula> peliculas;
	
	public VentanaAdministracion(JFrame ventanaAnterior){
		vActual = this;
		this.vAnterior = ventanaAnterior;
	
		
		
		setSize(800,600);
		setTitle("Tabla Administracion");
		JPanel panel = new JPanel(new BorderLayout());
		
		  this.modeloDatos = new DefaultTableModel(new String[]{"Titulo", "Productora", "Presupuesto", "Recaudacion","Rentabilidad"}, 0) {
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
	        
	       
	        
	        
	        tabla= new JTable(modeloDatos);
			
			JScrollPane scrollProduct= new JScrollPane(tabla);
			 
			panel.add(scrollProduct, BorderLayout.CENTER); 
			  
			this.loadPro();
			
			TableCellRenderer cellRenderer= (table,value,isSelected,hasFocus,row, column) -> {
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
			  JButton btnInsertar= new JButton();
			  JButton btnEliminar= new JButton();
			  JButton btnModificar= new JButton();
			  
			  
			  
			  add(panelSuperior, BorderLayout.NORTH);
			  
			  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			  setVisible(true);
			


	}
	private void loadPro() {
		 peliculas = BDPeliculas.obtenerPeliculas();
	      /*  peliculas.add(new Pelicula("Inception", "2h 28m", "Ciencia Ficción", 4.8, new ImageIcon(getClass().getResource("/imagenes/Inception.jpg")),"Warner Bros",true,"160000000","839000000"));
	        peliculas.add(new Pelicula("Titanic", "3h 15m", "Romance/Drama", 4.5, new ImageIcon(getClass().getResource("/imagenes/Titanic.jpg")),"20th Century Fox",true,"200000000","2260000000"));
	        peliculas.add(new Pelicula("The Dark Knight", "2h 32m", "Acción/Crimen", 4.9, new ImageIcon(getClass().getResource("/imagenes/TheDarkNight.jpg")),"Warner Bros", true,"185000000","1010000000"));
	        peliculas.add(new Pelicula("Toy Story", "1h 21m", "Animación/Familia", 4.7, new ImageIcon(getClass().getResource("/imagenes/ToyStory.jpg")),"Disney pixar",true,"30000000","394000000"));
	        peliculas.add(new Pelicula("Parasite", "2h 12m", "Drama/Thriller", 4.6, new ImageIcon(getClass().getResource("/imagenes/Parasite.jpg")),"CJ Entertaiment",true,"11400000","262300000"));
	       */
		 
		 
		 this.modeloDatos.setRowCount(0);
			//Se añaden las pelis uno a uno al modelo de datos
			peliculas.forEach(peli -> this.modeloDatos.addRow(
					new Object[] {peli.getTitulo(),peli.getProductora(),peli.getPresupuesto(),peli.getTaquilla(),peli.getRentabilidad()} )
			);
		
	}
	
}
