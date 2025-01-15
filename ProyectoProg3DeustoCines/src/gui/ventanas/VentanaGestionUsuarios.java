package gui.ventanas;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.clases.Usuario;
import main.Deustocines;

public class VentanaGestionUsuarios extends JFrame{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	 private ModeloTablaUsuarios modeloTabla;
	 private JScrollPane scrollTabla;
	 public VentanaGestionUsuarios(JFrame vAnterior) {
		 //clave=nomUsuario valor= Usuario
		 HashMap<String, Usuario> usuarios = Deustocines.mapaUsuarios();

		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("GESTION DE USUARIOS");
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        modeloTabla = new ModeloTablaUsuarios(usuarios);
			tabla = new JTable(modeloTabla);
			scrollTabla = new JScrollPane(tabla);
			getContentPane().add(scrollTabla, BorderLayout.CENTER);
	        
	        
	        
	        setVisible(true);
	 }
}
