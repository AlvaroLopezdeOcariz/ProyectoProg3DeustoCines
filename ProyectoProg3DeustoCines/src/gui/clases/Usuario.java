package gui.clases;

public class Usuario {
private String nombre;
private String nomUsuario;
private String contrasenia;
private static Usuario usuarioactual = null;
public Usuario() {
	super();
	// TODO Auto-generated constructor stub
}
public Usuario(String nombre, String nomUsuario, String contrasenia) {
	super();
	this.nombre = nombre;
	this.nomUsuario = nomUsuario;
	this.contrasenia = contrasenia;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getNomUsuario() {
	return nomUsuario;
}
public void setNomUsuario(String nomUsuario) {
	this.nomUsuario = nomUsuario;
}
public String getContrasenia() {
	return contrasenia;
}
public void setContrasenia(String contrasenia) {
	this.contrasenia = contrasenia;
}
public static void setUsuarioActual(Usuario usuario) {
    usuarioactual = usuario;
}

public static Usuario getUsuarioActual() {
    return usuarioactual;
}

}
