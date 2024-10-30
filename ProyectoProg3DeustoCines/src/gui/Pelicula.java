package gui;
public class Pelicula {
    private String titulo;
    private String duracion;
    private String genero;
    private double valoracion;

    public Pelicula(String titulo, String duracion, String genero, double valoracion) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.valoracion = valoracion;
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
}