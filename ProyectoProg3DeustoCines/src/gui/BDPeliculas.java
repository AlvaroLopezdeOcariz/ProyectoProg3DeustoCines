package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	                + "valoracion REAL,"
	                + "imagen BLOB,"
	                + "productora TEXT,"
	                + "rentabilidad BOOLEAN,"
	                + "presupuesto TEXT,"
	                + "taquilla TEXT"
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
	    	if(!estaVacia()) {
	    		return;//no se insertan peliculas
	    	}
	    	
	    	ArrayList<Pelicula> peliculas= new ArrayList<>();
	    	peliculas.add(new Pelicula("Inception", "2h 28m", "Ciencia Ficción", 4.8,"Warner Bros",true,"160000000","839000000"));
	    	peliculas.add(new Pelicula("Titanic", "3h 15m", "Romance/Drama", 4.5,"20th Century Fox",true,"200000000","2260000000"));
	    	peliculas.add(new Pelicula("The Dark Knight", "2h 32m", "Acción/Crimen", 4.9, "Warner Bros", true,"185000000","1010000000"));
	    	peliculas.add(new Pelicula("Toy Story", "1h 21m", "Animación/Familia", 4.7, "Disney pixar",true,"30000000","394000000"));
	     peliculas.add(new Pelicula("Parasite", "2h 12m", "Drama/Thriller", 4.6,"CJ Entertaiment",true,"11400000","262300000"));
	      
	    	   
	    	   String sql = "INSERT INTO Peliculas (titulo, duracion, genero, valoracion, productora, rentabilidad, presupuesto, taquilla)  "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        try (Connection conexion = DriverManager.getConnection(DB_URL);
	             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

	            
	            for (Pelicula pelicula : peliculas) {
	                pstmt.setString(1, pelicula.getTitulo());           
	                pstmt.setString(2, pelicula.getDuracion());         
	                pstmt.setString(3, pelicula.getGenero());           
	                pstmt.setDouble(4, pelicula.getValoracion());       
	               // pstmt.setObject(5, pelicula.getImagen());           
	                pstmt.setString(5, pelicula.getProductora());       
	                pstmt.setBoolean(6, pelicula.getRentabilidad());    
	                pstmt.setString(7, pelicula.getPresupuesto());    
	                pstmt.setString(8, pelicula.getTaquilla());         

	               
	                pstmt.executeUpdate();
	            }

	            System.out.println("Películas insertadas correctamente.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
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
	    
	    
	    
	    public static ArrayList<Pelicula> obtenerPeliculas() {
	    	ArrayList<Pelicula> lsPeliculas= new ArrayList<>();
	    	String sql ="SELECT * FROM Peliculas";
	    	try (Connection conexion = DriverManager.getConnection(DB_URL);
	                Statement stmt = conexion.createStatement();
	                ResultSet rs = stmt.executeQuery(sql)){
	    		
	    		
	    		while(rs.next()) {
	    			Pelicula peli = new Pelicula(rs.getString("titulo"),rs.getString("duracion"),rs.getString("genero"),rs.getDouble("valoracion"),rs.getString("productora"), rs.getBoolean("rentabilidad"), rs.getString("presupuesto"), rs.getString("taquilla") );
	    			lsPeliculas.add(peli);
	    		}
	    		
	    		}
	    
	    	catch (SQLException e) {
	            e.printStackTrace();
	        }
			return lsPeliculas;
	       
	    	
	    }
	    
	  
      
	


}
