package bd;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import gui.clases.Carrito;
import gui.clases.ItemCarrito;
import gui.clases.Pelicula;
import gui.clases.Usuario;

public class BDPeliculas {
	
	private static final String DB_URL = "jdbc:sqlite:Peliculas.db";

	    // Método para inicializar la conexión y crear las tablas si no existe
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

	        String sqlCreateTableOpinion= "CREATE TABLE IF NOT EXISTS Opiniones("
	        		+"id INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"autor TEXT NOT NULL,"
	        		+"texto TEXT"
	        		+");";
	        
	        
	        String sqlCreateTableUsuarios="CREATE TABLE IF NOT EXISTS Usuarios("
	        		+"id INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"nombre TEXT NOT NULL,"
	        		+"nomUsuario TEXT,"
	        		+"contrasenia TEXT,"
	        		+"admin BOOLEAN"
	        		+");";
	        
	       String sqlCreateTableCarritos="CREATE TABLE IF NOT EXISTS Carritos ("
	    		   +"id_Car INTEGER PRIMARY KEY AUTOINCREMENT,"
	    		   +"id_Usuario INTEGER NOT NULL,"
	    		   +"total REAL,"
	    		   +"FOREIGN KEY(id_Usuario) REFERENCES Usuarios(id) ON DELETE CASCADE); ";
	        		
	       String sqlCreateTableItemCarritos="CREATE TABLE IF NOT EXISTS ItemsCarrito ("
	       		+ "    id INTEGER PRIMARY KEY AUTOINCREMENT,"
	       		+ "    carrito_id INTEGER NOT NULL,"
	       		+ "    nombre_item TEXT NOT NULL,"
	       		+ "    precio REAL NOT NULL,"
	       		
	       		+ "    FOREIGN KEY (carrito_id) REFERENCES Carritos(id)"
	       		+ ");";
	       
	       String sqlCreateTableAsientos= "CREATE TABLE IF NOT EXISTS Asientos("
	    		   +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
	    		   +"id_Usuario INTEGER NOT NULL,"
	    		   +"num_Asientos INTEGER,"
	    		   +"FOREIGN KEY(id_Usuario) REFERENCES Usuarios(id) ON DELETE CASCADE); ";

	        try (Connection conexion = DriverManager.getConnection(DB_URL);
	             Statement consulta = conexion.createStatement()) {
	            consulta.execute(sqlCreateTable);
	            consulta.execute(sqlCreateTableOpinion);
	            consulta.execute(sqlCreateTableUsuarios);
	            consulta.execute(sqlCreateTableCarritos);
	            consulta.execute(sqlCreateTableItemCarritos);
	            consulta.execute(sqlCreateTableAsientos);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void insertarAsientos(int usuarioId, int num_Asientos) {
	    	String query= "INSERT INTO Asientos(id_usuario, num_Asientos) VALUES(?,?)";
	    	try(Connection conexion = DriverManager.getConnection(DB_URL);
	    	    	PreparedStatement insertStmt= conexion.prepareStatement(query)){
	    		insertStmt.setInt(1, usuarioId);
	    		insertStmt.setDouble(2, num_Asientos);
	    		 insertStmt.executeUpdate();
	    		 System.out.println("Asiento insertada exitosamente.");
	    	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    public static void insertarCarrito(int usuarioId, Double pago) {
	    	String query= "INSERT INTO Carritos(id_usuario, total) VALUES(?,?)";
	    	try(Connection conexion = DriverManager.getConnection(DB_URL);
	    	PreparedStatement insertStmt= conexion.prepareStatement(query)){
	    		insertStmt.setInt(1, usuarioId);
	    		insertStmt.setDouble(2, pago);
	    		 insertStmt.executeUpdate();
	    		 System.out.println("Carrito insertada exitosamente.");
	    		
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	
	    	
	    }
	    
	    public void insertarItemCarrito(Carrito carrito,int carritoId ) {
	    	
	    	for(ItemCarrito i :carrito.getItems()) {
	    		
	    	String query = "INSERT INTO ItemsCarrito (carrito_id, nombre_item, precio) VALUES (?, ?, ?, ?)";
	    	try (Connection conexion = DriverManager.getConnection(DB_URL);
	    	    	PreparedStatement insertStmt= conexion.prepareStatement(query)){
	    		insertStmt.setInt(1, carritoId);
	    		insertStmt.setString(2, i.toString());
	    		insertStmt.setDouble(3, i.getPrecio());
	    		insertStmt.executeUpdate();
	    		System.out.println("Carrito insertada exitosamente.");
	    		
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	}
	    }
	    
	    public void insertarUsuarios() {
	    	
	    	File f= new File("src/UsuariosRegistrados.txt");
	    	ArrayList<Usuario> listaUsuarios= new ArrayList<>();
	    	if(!f.exists()) {
	    		System.err.println("El archivo peliculas.txt no existe.");
	    	}
	    	try {
	    		Scanner sc = new Scanner(f);
	    		
	    		while(sc.hasNext()) {
	    			String linea = sc.nextLine();
	    			String[] datos = linea.split(";");
	    			if (datos.length== 4) {
	    				String nom= datos[0];
	    				String nomUsuario= datos[1];
	    				String contra= datos[2];
	    				Boolean admin= Boolean.parseBoolean(datos[3]);
	    				listaUsuarios.add(new Usuario(nom,nomUsuario,contra, admin));
	    				
	    			}
	    			
	    			
	    		}
	    		sc.close();
	    	}catch (FileNotFoundException e) {
                e.printStackTrace();}
	    
	    
	    String comprobacion= "SELECT COUNT(*) FROM Usuarios WHERE nomUsuario=?";
	    
	    for(Usuario usu: listaUsuarios ) 
	    {
	    	  try (Connection conexion = DriverManager.getConnection(DB_URL);
		    		   PreparedStatement pstmt = conexion.prepareStatement(comprobacion))
		        	
		        {
		 	    		pstmt.setString(1, usu.getNomUsuario() );           
		                ResultSet rs = pstmt.executeQuery();
		                rs.next();
		                int count = rs.getInt(1);
		                
		                if(count==0) {
		                	String insertarUsuario= "INSERT INTO Usuarios(nombre,nomUsuario,contrasenia,admin) VALUES(?,?,?,?)";
		                	PreparedStatement insertStmt= conexion.prepareStatement(insertarUsuario);
		                	insertStmt.setString(1, usu.getNombre());
		                	insertStmt.setString(2, usu.getNomUsuario());
		                	insertStmt.setString(3,usu.getContrasenia());
		                	insertStmt.setBoolean(4,usu.getEsAdmin());
		                	insertStmt.executeUpdate();  
		                	System.out.println("Usuario insertada exitosamente.");
		                }
		                
		                
		                
		        }catch (SQLException e) {
		            e.printStackTrace();
		        }
	    	  }
	    
	    	
	    
	    
	    
	    
	    }
	    
	    
	    
	    public void insertarPeliculas() {
	   
	    		//Cargar las  Peliculas
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
	                        String i = ("/Imagenes/" + datos[4]);
	                        Double valoracion= Double.parseDouble(datos[5]);
	                        String distribuidora = datos[6];	  
	                        Boolean rentable = Boolean.parseBoolean(datos[7]);
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
	            //Contar cuantas veces esta la pelicula en la tabla peliculas
	            String comprobacion = "SELECT COUNT(*) FROM peliculas WHERE titulo=?";
	            
	        

	    	
	    	
	    	   
	    	   for (Pelicula pelicula : listaPeliculas) {
	    		   try (Connection conexion = DriverManager.getConnection(DB_URL);
	    		   PreparedStatement pstmt = conexion.prepareStatement(comprobacion))
	        	
	        {

	            
	            

	    			   pstmt.setString(1, pelicula.getTitulo());           
	                ResultSet rs = pstmt.executeQuery();
	                rs.next();
	                int count = rs.getInt(1);
	                
	                
	                
	                
	                
	                //Si la pelicula no esta se añade y si esta no se hace nada
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
	                     insertStmt.executeUpdate();  
	                     System.out.println("Película insertada exitosamente.");
	                	
	                }   }    catch (SQLException e) {
	            e.printStackTrace();
	        } }
	    }
	    
	    public static int obtenerIdUsuario(String nombre) {
	    	String query = "SELECT * FROM Usuarios WHERE nomUsuario=?";
	    	try (Connection conexion = DriverManager.getConnection(DB_URL);
	    			PreparedStatement pstmt = conexion.prepareStatement(query)){
	    		
	    		pstmt.setString(1, nombre);
	    		
	    		try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("id"); // Devolver el ID de la actividad
	                } else {
	                    System.out.println("Usuario no encontrada.");
	                }
	            }
	    		
	    			
	    		
	    		
	    	}catch (SQLException e) {
	            e.printStackTrace();
	        }
	    	
	    	return 0;
	    	
	    }
	
	    //Metodo para seleccionar las peliculas de la base de datos y meterlas en una lista
	    public static  ArrayList<Pelicula> obtenerPeliculas() {
	    	ArrayList<Pelicula> lsPeliculas= new ArrayList<>();
	    	String sql = "SELECT * FROM Peliculas";
	    	try (Connection conexion = DriverManager.getConnection(DB_URL);
	                Statement stmt = conexion.createStatement();
	                ResultSet rs = stmt.executeQuery(sql)){
	    		
	    		
	    		while(rs.next()) {
	    			Pelicula peli = new Pelicula(rs.getString("titulo"), rs.getString("descripcion"), rs.getString("duracion"), rs.getString("genero"), rs.getString("imagen"), rs.getDouble("valoracion"), rs.getString("productora"), rs.getBoolean("rentabilidad"), rs.getDouble("presupuesto"), rs.getDouble("taquilla") );
	    			lsPeliculas.add(peli);
	    		}
	    		
	    		}
	    
	    	catch (SQLException e) {
	            e.printStackTrace();
	        }
			return lsPeliculas;
	       
	    	
	    }
	    
	  //Metodo para insertar Opiniones en la bd
      public void insertarOpiniones() {
    	   HashMap<String, HashMap<String, List<String>>> mapaOpiniones = new HashMap<>();
           File f = new File("src/Opiniones.txt");

           if (!f.exists()) {
               System.err.println("El archivo Opiniones.txt no existe.");
              
           }

           try {
               Scanner sc = new Scanner(f);
               while (sc.hasNext()) {
                   String linea = sc.nextLine();
                   String[] datos = linea.split(";");
                   
                   if (datos.length == 3) {
                       String usuario = datos[0];
                       String pelicula = datos[1];
                       List<String> opiniones = new ArrayList<>(Arrays.asList(datos[2].split(",")));

                       //mapaOpiniones.putIfAbsent(pelicula, new HashMap<>());
                       if(!mapaOpiniones.containsKey(pelicula)) {
                       	mapaOpiniones.put(pelicula,new HashMap<>());
                       }
                       
                       mapaOpiniones.get(pelicula).put(usuario, opiniones);
                   }
               }
               sc.close();
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
           
           //Se Comprueba si la opinion esta en la base de datos o no
           
           String comprobacion= "SELECT COUNT(*) FROM Opiniones WHERE texto=?";
           
           //Dentro del mapa se busca la opinion de un usuario sobre una pelicula y se comprueba si esta en la bd
           for(String peli: mapaOpiniones.keySet()) {
        	   try (Connection conexion = DriverManager.getConnection(DB_URL);
    	    		   PreparedStatement pstmt = conexion.prepareStatement(comprobacion))
    	        	
    	        {
        		   for(String user: mapaOpiniones.get(peli).keySet() ) {
        			   for(String opi: mapaOpiniones.get(peli).get(user)) {
        				   pstmt.setString(1, opi);
        				   
        				   ResultSet rs = pstmt.executeQuery();
       	                rs.next();
       	                int count = rs.getInt(1);
       	                
       	                //Si no esta se inserta y si esta se pasa a la siguiente
       	                if(count==0) {
       	                	String insertarOpi= "INSERT INTO Opiniones (autor,texto) VALUES(?,?)" ;
       	                	PreparedStatement insertarPstm= conexion.prepareStatement(insertarOpi);
       	                	
       	                	insertarPstm.setString(1, user);
       	                	insertarPstm.setString(2, opi);
       	                	System.out.println("Opinion insertada exitosamente.");
    	                	
       	                }
        				   
        			   }
        		   }
    	        }  catch (SQLException e) {
    	            e.printStackTrace();
    	        }
           }
           
           
       }
	

}
