package gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Carrito {
	private List<ItemCarrito> items; // Comida u otros productos
    private Set<String> asientos; // Asientos seleccionados

    public Carrito() {
        items = new ArrayList<>();
        asientos = new HashSet<>();
    }

    public void agregarItem(ItemCarrito item) {
        items.add(item);
    }

    public void agregarAsiento(String asiento) {
        asientos.add(asiento);
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public Set<String> getAsientos() {
        return asientos;
    }

    public void limpiarCarrito() {
        items.clear();
        asientos.clear();
    }

    public double calcularTotal() {
        double totalItems = items.stream().mapToDouble(ItemCarrito::getPrecio).sum();
        double totalAsientos = asientos.size() * 10.0; // Precio fijo por asiento
        return totalItems + totalAsientos;
    }
}

