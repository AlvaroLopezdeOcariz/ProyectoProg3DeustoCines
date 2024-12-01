package gui.clases;

public class ItemCarrito {
	private String descripcion;
    private double precio;

    public ItemCarrito(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}
