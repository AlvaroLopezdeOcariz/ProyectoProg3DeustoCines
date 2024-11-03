package gui;

import javax.swing.ImageIcon;

public class Pelicula {
    private String titulo;
    private String duracion;
    private String genero;
    private double valoracion;
    private ImageIcon imagen;

    public Pelicula(String titulo, String duracion, String genero, double valoracion, ImageIcon imagen) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.valoracion = valoracion;
        this.imagen = imagen;
    }

    // Getters para obtener la información de la película
    public String getTitulo() {
        return titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public double getValoracion() {
        return valoracion;
    }
    
    public ImageIcon getImagen() {
    	return imagen;
    }
}