package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class BDPeliculas {
	
	private ArrayList<Pelicula> peliculas;

	 private static final String DB_URL = "jdbc:sqlite:Peliculas.db";

	    // Método para inicializar la conexión y crear la tabla si no existe
	    public void InicializarBD() {
	        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Peliculas ("
	                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                + "titulo TEXT NOT NULL,"
	                
	                + "duracion TEXT,"
	                + "genero TEXT,"
	                + "descripcion TEXT,"
	                + "valoracion REAL,"
	                + "imagen TEXT,"
	                + "productora TEXT,"
	                + "rentabilidad BOOLEAN,"
	                + "presupuesto REAL,"
	                + "taquilla REAL"
	                + ");";

	        try (Connection conexion = DriverManager.getConnection(DB_URL);
	             Statement consulta = conexion.createStatement()) {
	            consulta.execute(sqlCreateTable);
	            
	            
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    

	    
	    
	    
	    public void insertarPeliculas() {
	        // Consulta SQL para insertar una película
	    /*	if(!estaVacia()) {
	    		return;//no se insertan peliculas
	    	}
	    	
	    	  */
	    	
	    	
	    		 File f = new File("src/peliculas.txt");
	            ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
	            if (!f.exists()) {
	                System.err.println("El archivo peliculas.txt no existe.");
	            }
	            try {
	                Scanner sc = new Scanner(f);
	                while (sc.hasNext()) {
	                    String linea = sc.nextLine();
	                    String[] datos = linea.split(";");
	                    if (datos.length == 10) {
	                        String titulo = datos[0];
	                        String descripcion = datos[1];
	                        String duracion = datos[2];
	                        String genero = datos[3];	                        
	                        String i = ("/imagenes/" + datos[4]);
	                        Double valoracion= Double.parseDouble(datos[5]);
	                        String distribuidora = datos[6];	  
	                        Boolean rentable= Boolean.parseBoolean(datos[7]);
	                        Double presupuesto = Double.parseDouble(datos[8]);
	                        Double recaudacion=Double.parseDouble(datos[9]);
	                        
	                        
	                        
	                        //ImageIcon imagen = new ImageIcon(getClass().getResource(i));
	                        listaPeliculas.add(new Pelicula(titulo, descripcion, duracion, genero, i, valoracion, distribuidora, rentable, presupuesto, recaudacion));
	                    }
	                   
	                }sc.close();
	            }
	            
	            catch (FileNotFoundException e) {
	                e.printStackTrace();
	            }

	            String comprobacion= "SELECT COUNT(*) FROM peliculas WHERE titulo=?";
	            
	        

	    	   String sql = "INSERT INTO Peliculas (titulo, duracion, genero, descripcion,imagen, valoracion, productora, rentabilidad, presupuesto, taquilla)  "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	
	    	   
	    	   for (Pelicula pelicula : listaPeliculas) {
	    		   try (Connection conexion = DriverManager.getConnection(DB_URL);
	    		   PreparedStatement pstmt = conexion.prepareStatement(comprobacion))
	        	
	        {

	            
	            
	                pstmt.setString(1, pelicula.getTitulo());           
	                ResultSet rs = pstmt.executeQuery();
	                rs.next();
	                int count = rs.getInt(1);
	                
	                
	                
	                if(count==0) {
	                
	             
	                	 String insertarPeli = "INSERT INTO peliculas (titulo, descripcion, duracion, genero, imagen, valoracion, productora, rentabilidad, presupuesto, taquilla) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                     PreparedStatement insertStmt = conexion.prepareStatement(insertarPeli);
	                     insertStmt.setString(1, pelicula.getTitulo());
	                     insertStmt.setString(2, pelicula.getDescripcion());
	                     insertStmt.setString(3, pelicula.getDuracion());
	                     insertStmt.setString(4, pelicula.getGenero());
	                     insertStmt.setString(5, pelicula.getImagen2());
	                     insertStmt.setDouble(6, pelicula.getValoracion());
	                     insertStmt.setString(7, pelicula.getProductora());
	                     insertStmt.setBoolean(8, pelicula.getRentabilidad());
	                     insertStmt.setDouble(9, pelicula.getPresupuesto());
	                     insertStmt.setDouble(10, pelicula.getTaquilla());
	                     insertStmt.executeUpdate();  // Inserta la nueva película
	                     System.out.println("Película insertada exitosamente.");
	                	
	                }   }    catch (SQLException e) {
	            e.printStackTrace();
	        } }
	    }
	    
	    
	/*    
	    private boolean estaVacia() {
			
	    	String i= "SELECT COUNT(*) AS total FROM Peliculas";
	    	
	    	try (Connection conexion = DriverManager.getConnection(DB_URL);
	                Statement stmt = conexion.createStatement();
	                ResultSet rs = stmt.executeQuery(i)){
	    		
	    		if(rs.next()) {
	    			int total= rs.getInt("total");
	    			return total==0;
	    		}
	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return true; 
	    	
	    	
	    	
	    	
	    }
	    */
	    
	    
	    public static  ArrayList<Pelicula> obtenerPeliculas() {
	    	ArrayList<Pelicula> lsPeliculas= new ArrayList<>();
	    	String sql ="SELECT * FROM Peliculas";
	    	try (Connection conexion = DriverManager.getConnection(DB_URL);
	                Statement stmt = conexion.createStatement();
	                ResultSet rs = stmt.executeQuery(sql)){
	    		
	    		
	    		while(rs.next()) {
	    			Pelicula peli = new Pelicula(rs.getString("titulo"),rs.getString("duracion"),rs.getString("genero"),rs.getDouble("valoracion"),null, rs.getString("productora"), rs.getBoolean("rentabilidad"), rs.getDouble("presupuesto"), rs.getDouble("taquilla") );
	    			lsPeliculas.add(peli);
	    		}
	    		
	    		}
	    
	    	catch (SQLException e) {
	            e.printStackTrace();
	        }
			return lsPeliculas;
	       
	    	
	    }
	    
	  
      
	


}
