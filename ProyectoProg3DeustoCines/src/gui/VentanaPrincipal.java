package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame vActual;
    private JPanel mainPanel, menuPanel, botonPanel, titulosPeliculas;
    private JLabel deustoCinesLabel, populares;
    private ArrayList<Pelicula> peliculas;

    public VentanaPrincipal() {
        vActual = this;

        // Configuración de la ventana principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DEUSTOCINES");
        setSize(800, 600);

        // Panel principal con fondo azul
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(30, 30, 70)); // Azul oscuro
        mainPanel.setLayout(new BorderLayout());

        // Panel del menú superior
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BorderLayout());

        // Panel donde estarán los botones
        botonPanel = new JPanel();
        botonPanel.setBackground(Color.WHITE);
        botonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        // Crear los botones
        String[] menuItems = {"CARTELERA", "ADMINISTRADOR", "CARRITO", "INICIAR SESION/REGISTRARSE"};
        for (String item : menuItems) {
            JButton boton = new JButton(item);
            boton.setFont(new Font("Verdana", Font.BOLD, 11));
            boton.setBackground(new Color(0, 128, 255)); 
            boton.setFocusPainted(false);
            boton.setOpaque(true);
            botonPanel.add(boton);

            // Asignar la acción a cada botón
            boton.addActionListener(e -> {
                if (item.equals("INICIAR SESION/REGISTRARSE")) {
                    vActual.dispose();
                    new VentanaInicioSesion(vActual);
                } else if (item.equals("CARTELERA")) {
                    vActual.dispose();
                    new VentanaCartelera(vActual);
                } else if (item.equals("ADMINISTRADOR")) {
                    vActual.dispose();
                    new VentanaAdministracion(vActual);
                } else if (item.equals("CARRITO")) {
                    vActual.dispose();
                    new VentanaCarrito(vActual);
                }
            });
        }

        // Etiqueta "DEUSTOCINES"
        deustoCinesLabel = new JLabel("DeustoCines");
        deustoCinesLabel.setForeground(new Color(70, 130, 180)); // 
        deustoCinesLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        menuPanel.add(deustoCinesLabel, BorderLayout.WEST);
        menuPanel.add(botonPanel, BorderLayout.EAST);
        add(menuPanel, BorderLayout.NORTH);

        // Panel para el título "Populares Ahora Mismo"
        titulosPeliculas = new JPanel();
        titulosPeliculas.setBackground(new Color(30, 30, 70)); // Mismo color que el fondo principal
        titulosPeliculas.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 80));
        mainPanel.add(titulosPeliculas, BorderLayout.CENTER);

        // Etiqueta "Populares Ahora Mismo"
        populares = new JLabel("Populares Ahora Mismo: ");
        populares.setForeground(Color.WHITE);
        populares.setFont(new Font("Verdana", Font.BOLD, 20));
        titulosPeliculas.add(populares);

        
        cargarPeliculasPopulares();

        // Agregar el panel principal a la ventana
        add(mainPanel);
        setVisible(true);
    }
    
    private void cargarPeliculasPopulares() {
        peliculas = BDPeliculas.obtenerPeliculas(); // Obtener todas las películas
        peliculas.sort((p1, p2) -> Double.compare(p2.getValoracion(), p1.getValoracion())); // Ordenar por valoración

        // Crear una lista de las 5 películas más populares
        ArrayList<Pelicula> populares = new ArrayList<>();
        for (int i = 0; i < 5 && i < peliculas.size(); i++) {
            populares.add(peliculas.get(i));
        }

        // Crear botones para cada película popular
        for (Pelicula pelicula : populares) {
            // ImageIcon imagenOriginal = cargarImagen(pelicula.getTitulo()); // Usar el título para generar la ruta
        	ImageIcon imagenOriginal = new ImageIcon(getClass().getResource(pelicula.getImagen2()));
            ImageIcon imagenRedimensionada = redimensionarImagen(imagenOriginal, 120, 150); // Redimensionar

            JButton botonPelicula = new JButton();
            botonPelicula.setIcon(imagenRedimensionada);
            botonPelicula.setFocusPainted(false);
            botonPelicula.setContentAreaFilled(false);
            botonPelicula.setBorderPainted(false);
            botonPelicula.setCursor(new Cursor(Cursor.HAND_CURSOR));

            botonPelicula.addActionListener(e -> {
                // JOptionPane.showMessageDialog(vActual, "Película seleccionada: " + pelicula.getTitulo());
                new VentanaCartelera(vActual).setVisible(true);
                dispose();
            });

            titulosPeliculas.add(botonPelicula);
        }

        titulosPeliculas.revalidate();
        titulosPeliculas.repaint();
    }


    // Método para redimensionar imágenes
    public ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int ancho, int alto) {
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
    
}
