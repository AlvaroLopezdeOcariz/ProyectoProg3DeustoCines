package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import gui.BDPeliculas;
import gui.Carrito;
import gui.Usuario;
import gui.VentanaPrincipal;

public class Deustocines {
	public static Carrito carrito = new Carrito();
	private static File f = new File("src/UsuariosRegistrados.txt");
	
    public static HashMap<String, ArrayList<String>> mapaUsuarios() {
        
    	HashMap<String, ArrayList<String>> mapaUsuarios= new HashMap<>(); 
        
         if (!f.exists()) {
             System.err.println("El archivo UsuariosRegistrados.txt no existe.");
             
         }
         
         try {
             Scanner sc = new Scanner(f);
             while (sc.hasNext()) {
                 String linea = sc.nextLine();
                 String[] datos = linea.split(";");
                 
                 if (datos.length >= 3) {
                     String nombre = datos[0];
                     String usuario = datos[1];
                     String contrasenia = datos[2];
                     ArrayList<String> nombreyContrasenia = new ArrayList<>();
                     nombreyContrasenia.add(nombre);
                     nombreyContrasenia.add(contrasenia);
                     mapaUsuarios.put(usuario, nombreyContrasenia);
                 }
             }
             sc.close();
         } catch (FileNotFoundException e) {
             System.err.println("Error al abrir el fichero.");
         }
        return mapaUsuarios;
    }
    
    public static boolean IniciarRegistro(String nom, String usu, String contra) {
    	HashMap<String, ArrayList<String>> usuarios = mapaUsuarios();
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
            pw.print(nom + ";" + usu + ";" + contra);
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
    	HashMap<String, ArrayList<String>> usuarios = mapaUsuarios();
        if (usuarios.containsKey(usu)) {
        	Usuario.setUsuarioActual(new Usuario("", usu, contra));
            return contra.equals(usuarios.get(usu).get(1));
        }
        return false;
    }
    
    public static void main(String[] args) {
    	BDPeliculas baseDatos= new BDPeliculas();
   	 	baseDatos.InicializarBD();
   	 	baseDatos.insertarPeliculas();
   	 	SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    	
    	
    	
    	
    	
    	//new VentanaPrincipal();
	}

}
