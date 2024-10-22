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

import gui.VentanaPrincipal;

public class Deustocines {

    public static HashMap<String, ArrayList> mapaUsuarios() {
        
    	HashMap<String, ArrayList> mapaUsuarios= new HashMap<>(); 
    	 File f = new File("UsuariosRegistrados.txt");
        
         if (!f.exists()) {
             System.err.println("El archivo UsuariosRegistrados.txt no existe.");
             
         }
         
         try {
             Scanner sc = new Scanner(f);
             while (sc.hasNext()) {
                ArrayList nombreyContrasenia = new ArrayList<>();
                 String linea = sc.nextLine();
                 String[] datos = linea.split(";");
                 
                 if (datos.length >= 3) {
                     String nombre = datos[0];
                     String usuario = datos[1];
                     String contrasenia = datos[2];
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
        for (String usuario : mapaUsuarios().keySet()) {
            if (usu.equals(usuario)) {
                return false; 
            }
        }
        File f = new File("UsuariosRegistrados.txt");
        if (!f.exists()) {
            System.err.println("El archivo UsuariosRegistrados.txt no existe.");
            return false; 
        } else {
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
    }
   
    public static boolean iniciarSesion(String usu,String contra){
        for(String ususario: mapaUsuarios().keySet()){
            if (usu.equals(ususario)&&contra.equals(mapaUsuarios().get(ususario).get(1))) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
		new VentanaPrincipal();
	}

}
