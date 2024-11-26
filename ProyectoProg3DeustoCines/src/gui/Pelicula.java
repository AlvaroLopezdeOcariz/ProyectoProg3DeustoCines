package gui;

import java.util.ArrayList;


import javax.swing.ImageIcon;

public class Pelicula {
    private String titulo;
    private String duracion;
    private String genero;
    private double valoracion;
    private ImageIcon imagen;
    public ArrayList<Opinion> opiniones;
    private String productora;
    private Boolean rentabilidad;
    private String presupuesto;
    private String taquilla;
    private String descripcion;
    private double precio;

    

    public Pelicula(String titulo, String duracion, String genero, double valoracion, ImageIcon imagen,
			 String productora, Boolean rentabilidad, String presupuesto,String taquilla) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.valoracion = valoracion;
		this.imagen = imagen;
		this.opiniones = new ArrayList<Opinion>();
		this.productora = productora;
		this.rentabilidad = rentabilidad;
		this.presupuesto = presupuesto;
		this.taquilla=taquilla;
	}
    
    public Pelicula(String titulo, String descripcion, String duracion, String genero, double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.genero = genero;
        this.precio = precio;
    }
    public Pelicula(String titulo, String descripcion, String duracion, String genero, double precio,ImageIcon imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.genero = genero;
        this.precio = precio;
        this.imagen = imagen;
    }
    public Pelicula(String titulo, String duracion, String genero, double valoracion,
			 String productora, Boolean rentabilidad, String presupuesto,String taquilla) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.valoracion = valoracion;
		this.productora = productora;
		this.rentabilidad = rentabilidad;
		this.presupuesto = presupuesto;
		this.taquilla=taquilla;
    }
    // Getters para obtener la información de la película
    
    public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public String getTitulo() {
        return titulo;
    }

    public String getProductora() {
		return productora;
	}


	public void setProductora(String productora) {
		this.productora = productora;
	}


	public Boolean getRentabilidad() {
		return rentabilidad;
	}


	public void setRentabilidad(Boolean rentabilidad) {
		this.rentabilidad = rentabilidad;
	}


	public String getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}


	public String getTaquilla() {
		return taquilla;
	}


	public void setTaquilla(String taquilla) {
		this.taquilla = taquilla;
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

	
	

	public void setOpiniones(ArrayList<Opinion> opiniones) {
		this.opiniones = opiniones;
	}
	
	
	//Metedo para agergar Opiniones
	
	public void agregarOpiniones(Opinion opinion) {
		opiniones.add(opinion);
	
	}
    
}
