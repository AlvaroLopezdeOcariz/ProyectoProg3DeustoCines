package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCartelera extends JFrame {
	
	JFrame vActual, vAnterior;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaCartelera(JFrame vAnterior) {
		vActual = this;
		this.vAnterior= vAnterior;
		  
        setTitle("Cartelera");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal para contener la cartelera
        JPanel panelCartelera = new JPanel();
        panelCartelera.setLayout(new GridLayout(0, 2, 10, 10)); // Organiza en una cuadrícula
        panelCartelera.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear una lista de películas
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Inception", "2h 28m", "Ciencia Ficción", 4.8));
        peliculas.add(new Pelicula("Titanic", "3h 15m", "Romance/Drama", 4.5));
        peliculas.add(new Pelicula("The Dark Knight", "2h 32m", "Acción/Crimen", 4.9));
        peliculas.add(new Pelicula("Toy Story", "1h 21m", "Animación/Familia", 4.7));
        peliculas.add(new Pelicula("Parasite", "2h 12m", "Drama/Thriller", 4.6));
        // Puedes añadir más películas aquí...

        // Crear botones para cada película en la cartelera
        for (Pelicula pelicula : peliculas) {
            JButton botonPelicula = new JButton(pelicula.getTitulo());
            botonPelicula.setFont(new Font("Arial", Font.PLAIN, 16));

            // Agregar ActionListener para mostrar detalles de la película
            botonPelicula.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarInformacionPelicula(pelicula);
                }
            });

            panelCartelera.add(botonPelicula);
        }

        // Añadir el panel de cartelera a la ventana principal
        add(new JScrollPane(panelCartelera)); // Hacer scroll si hay muchas películas

        setVisible(true);
    }

    // Método para mostrar la información de la película en un diálogo
    private void mostrarInformacionPelicula(Pelicula pelicula) {
        // Crear un JDialog para mostrar la información de la película
        JDialog dialogoPelicula = new JDialog(this, pelicula.getTitulo(), true);
        dialogoPelicula.setSize(400, 300);
        dialogoPelicula.setLayout(new BorderLayout());
        dialogoPelicula.setLocationRelativeTo(this);

        // Crear el panel de información
        JPanel panelInformacion = new JPanel(new GridLayout(4, 1, 10, 10));
        panelInformacion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Añadir la información de la película al panel
        panelInformacion.add(new JLabel("Título: " + pelicula.getTitulo()));
        panelInformacion.add(new JLabel("Duración: " + pelicula.getDuracion()));
        panelInformacion.add(new JLabel("Género: " + pelicula.getGenero()));
        panelInformacion.add(new JLabel("Valoración: " + pelicula.getValoracion() + " / 5"));

        // Botón para cerrar el diálogo
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogoPelicula.dispose();
            }
        });

        // Añadir los componentes al diálogo
        dialogoPelicula.add(panelInformacion, BorderLayout.CENTER);
        dialogoPelicula.add(btnCerrar, BorderLayout.SOUTH);

        // Mostrar el diálogo
        dialogoPelicula.setVisible(true);
    }

    
}