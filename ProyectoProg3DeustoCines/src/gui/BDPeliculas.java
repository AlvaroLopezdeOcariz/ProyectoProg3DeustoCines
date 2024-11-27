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
	    	peliculas.add(new Pelicula("El Padrino", "2h 55m", "Crime/Drama", 4.9, "Paramount Pictures", true, "6000000", "246120974"));
	    	peliculas.add(new Pelicula("El Señor de los Anillos: El Retorno del Rey", "3h 21m", "Fantasy/Adventure", 4.8, "New Line Cinema", true, "94000000", "1146030912"));
	    	peliculas.add(new Pelicula("Forrest Gump", "2h 22m", "Drama/Romance", 4.7, "Paramount Pictures", true, "55000000", "678200000"));
	    	peliculas.add(new Pelicula("Gladiator", "2h 35m", "Action/Drama", 4.5, "DreamWorks Pictures", true, "103000000", "460500000"));
	    	peliculas.add(new Pelicula("Avatar", "2h 42m", "Sci-Fi/Adventure", 4.7, "20th Century Fox", true, "237000000", "2925000000"));
	    	peliculas.add(new Pelicula("Interstellar", "2h 49m", "Sci-Fi/Drama", 4.6, "Paramount Pictures", true, "165000000", "701700000"));
	    	peliculas.add(new Pelicula("Joker", "2h 2m", "Drama/Crime", 4.5, "Warner Bros.", true, "55000000", "1074000000"));
	    	peliculas.add(new Pelicula("Pulp Fiction", "2h 34m", "Crime/Drama", 4.8, "Miramax Films", true, "8000000", "213900000"));
	    	peliculas.add(new Pelicula("La La Land", "2h 8m", "Musical/Romance", 4.4, "Summit Entertainment", true, "30000000", "448900000"));
	    	peliculas.add(new Pelicula("Coco", "1h 45m", "Animation/Adventure", 4.7, "Pixar Animation Studios", true, "175000000", "807800000"));
	    	peliculas.add(new Pelicula("Shrek", "1h 30m", "Animation/Comedy", 4.5, "DreamWorks Animation", true, "60000000", "487900000"));
	    	peliculas.add(new Pelicula("Frozen", "1h 42m", "Animation/Adventure", 4.4, "Walt Disney Animation Studios", true, "150000000", "1280800000"));
	    	peliculas.add(new Pelicula("Los Vengadores", "2h 23m", "Action/Adventure", 4.6, "Marvel Studios", true, "220000000", "1519000000"));
	    	peliculas.add(new Pelicula("La Vida es Bella", "1h 56m", "Drama/Comedy", 4.8, "Cecchi Gori Group", true, "20000000", "230000000"));
	    	peliculas.add(new Pelicula("It", "2h 15m", "Horror/Drama", 4.1, "New Line Cinema", true, "35000000", "701800000"));
	    	peliculas.add(new Pelicula("Spider-Man: No Way Home", "2h 28m", "Action/Adventure", 4.7, "Marvel Studios", true, "200000000", "1922000000"));
	    	   
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
