package gui;

import java.util.ArrayList;

public class Carrito {
	private ArrayList<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemCarrito item) {
        items.add(item);
    }

    public ArrayList<ItemCarrito> getItems() {
        return items;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getPrecio();
        }
        return total;
    }
    
    public void limpiarCarrito() {
    	items.clear();
    }
}

