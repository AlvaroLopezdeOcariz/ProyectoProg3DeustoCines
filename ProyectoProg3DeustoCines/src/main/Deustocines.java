package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import bd.BDPeliculas;
import gui.clases.Carrito;
import gui.clases.Usuario;
import gui.ventanas.VentanaPrincipal;

public class Deustocines {
	public static Carrito carrito = new Carrito();
	private static File f = new File("src/UsuariosRegistrados.txt");
	
	public static HashMap<String, Usuario> mapaUsuarios() {
	        
	    	HashMap<String, Usuario> mapaUsuarios = new HashMap<>(); 
	        
	         if (!f.exists()) {
	             System.err.println("El archivo UsuariosRegistrados.txt no existe.");
	             
	         }
	         
	         try {
	             Scanner sc = new Scanner(f);
	             while (sc.hasNext()) {
	                 String linea = sc.nextLine();
	                 String[] datos = linea.split(";");
	                 
	                 if (datos.length == 4) {
	                     String nombre = datos[0];
	                     String usuario = datos[1];
	                     String contrasenia = datos[2];
	                     Boolean esAdmin = Boolean.parseBoolean(datos[3]);
	                     Usuario u = new Usuario(nombre, usuario, contrasenia, esAdmin);
	                     mapaUsuarios.put(usuario, u);
	                 }
	             }
	             sc.close();
	         } catch (FileNotFoundException e) {
	             System.err.println("Error al abrir el fichero.");
	         }
	        return mapaUsuarios;
	    }
	    
	public static boolean IniciarRegistro(String nom, String usu, String contra, Boolean esadmin) {
		HashMap<String, Usuario> usuarios = mapaUsuarios();
	    if (usuarios.containsKey(usu)) {
	        return false;
	    }
	
	    if (!f.exists()) {
	        System.err.println("El archivo UsuariosRegistrados.txt no existe.");
	        return false; 
	    }
	
	    
	    try {
	    	FileWriter fw = new FileWriter(f, true); 
	         PrintWriter pw = new PrintWriter(fw);
	        pw.print(nom + ";" + usu + ";" + contra + ";"+esadmin);
	        pw.println();
	        pw.flush();
	        pw.close();
	        return true; 
	    } catch (IOException e) {
	        System.err.println("Error al escribir en el fichero.");		
	        return false; 
	    }
	}
   
	public static boolean iniciarSesion(String usu,String contra){
		HashMap<String,Usuario> usuarios = mapaUsuarios();
	    if (usuarios.containsKey(usu)) {
	    	Usuario u = usuarios.get(usu);
	        return contra.equals(u.getContrasenia());
	    }
	    return false;
	}
    //METODO PARA CARGAR LAS OPINIONES DE OPINIONES.TXT 
    
    public HashMap<String, HashMap<String, List<String>>> cargarOpiniones() {
        HashMap<String, HashMap<String, List<String>>> mapaOpiniones = new HashMap<>();
        File f = new File("src/Opiniones.txt");

        if (!f.exists()) {
            System.err.println("El archivo Opiniones.txt no existe.");
            return mapaOpiniones;
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

                    if (!mapaOpiniones.containsKey(pelicula)) {
                        mapaOpiniones.put(pelicula, new HashMap<>());
                    }
                   
                    mapaOpiniones.get(pelicula).put(usuario, opiniones);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
        return mapaOpiniones;
    }

    HashMap<String, HashMap<String,List<String>>> mapaOpiniones = cargarOpiniones();
    //MAPA QUE DE UNA PELICULA TE DA CLAVE: USUARIO VALOR: OPINIONES DE ESA PELICULA
    public HashMap<String,List<String>> opinionesPeliculas(String peli) {
    	HashMap<String,List<String>> mapaUsuariosOpiniones = new HashMap<String, List<String>>();
    	for(String p:mapaOpiniones.keySet()){
    		if(p.equals(peli)) {
    			for(String usuario:mapaOpiniones.get(p).keySet()) {
    				List<String> opiniones = mapaOpiniones.get(p).get(usuario);
    				if(mapaUsuarios().containsKey(usuario)) {
    				mapaUsuariosOpiniones.put(usuario, opiniones);}
    			}
    			
    			//mapaUsuariosOpiniones.put(mapaOpiniones.get(p).keySet().toString(), mapaOpiniones.get(p).values());
    		}
    	}
    	System.out.print(mapaUsuariosOpiniones);
		return mapaUsuariosOpiniones;
    }
    //GUARDA EN OPINIONES.TXT UNA NUEVA OPINIION
    public void guardarOpinionEnArchivo(String usuario, String pelicula, String opinion) {
        File f = new File("src/Opiniones.txt");
        try {
            HashMap<String, List<String>> opinionesPelicula;
            if (mapaOpiniones.containsKey(pelicula)) {
                opinionesPelicula = mapaOpiniones.get(pelicula);
            } else {
                opinionesPelicula = new HashMap<>();
                mapaOpiniones.put(pelicula, opinionesPelicula);
            }

        
            if (opinionesPelicula.containsKey(usuario)) {
                List<String> opiniones = opinionesPelicula.get(usuario);
                if (!opiniones.contains(opinion)) {
                    opiniones.add(opinion); 
                }
            } else {
                List<String> opiniones = new ArrayList<>();
                opiniones.add(opinion);
                opinionesPelicula.put(usuario, opiniones);
            }

            // Reescribe todo el archivo con el contenido actualizado
            FileWriter fw = new FileWriter(f, false); // Sobrescribe el archivo
            PrintWriter pw = new PrintWriter(fw);

            for (String peli : mapaOpiniones.keySet()) {
                HashMap<String, List<String>> usuariosOpiniones = mapaOpiniones.get(peli);
                for (String user : usuariosOpiniones.keySet()) {
                    List<String> opiniones = usuariosOpiniones.get(user);
                    String linea = user + ";" + peli + ";" + String.join(",", opiniones);
                    pw.println(linea);
                }
            }

            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.err.println("Error al guardar la opinión en el archivo.");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
    	BDPeliculas baseDatos= new BDPeliculas();
   	 	baseDatos.InicializarBD();
   	 	baseDatos.insertarPeliculas();
   	 	baseDatos.insertarOpiniones();
   	 
   	 	SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    	
    	
    	
    	
    	
    	//new VentanaPrincipal();
	}

}
