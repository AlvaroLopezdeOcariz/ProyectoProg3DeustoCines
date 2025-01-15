package gui.ventanas;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import gui.clases.Usuario;

public class ModeloTablaUsuarios extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = {"Nombre", "Nombre de Usuario", "Contraseña", "Administrador"};
	private HashMap<String, Usuario> usuarios;
	
	 public ModeloTablaUsuarios(HashMap<String, Usuario> usuarios) {
		 if (usuarios == null) {
			    usuarios = new HashMap<>();
			}
			this.usuarios = usuarios;
	 }
	    

	@Override
	public int getRowCount() {
		
        if (usuarios == null) {
            return 0;
        }
        return usuarios.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnas[column];
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
	    // Obtener el usuario correspondiente a la fila
	    Usuario usuario = (Usuario) usuarios.values().toArray()[row];

	    // Dependiendo de la columna, devolver el valor adecuado
	    switch (column) {
	        case 0:  // Nombre
	            return usuario.getNombre();
	        case 1:  // Nombre de Usuario
	            return usuario.getNomUsuario();
	        case 2:  // Contraseña
	            return usuario.getContrasenia();
	        case 3:  // Administrador
	        	if(usuario.getEsAdmin()) {
	        		return "Si";
	        	}else {
	        		return "No";} 
	        default:
	            return null;
	    }
	}
	
	 
}
