package gui.ventanas;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import bd.BDPeliculas;
import gui.clases.Pelicula;

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
        mainPanel.setBackground(new Color(30, 144, 255)); // Azul claro
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
            boton.setForeground(Color.WHITE);
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
        deustoCinesLabel.setForeground(new Color(30, 144, 255)); 
        deustoCinesLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        deustoCinesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.add(deustoCinesLabel, BorderLayout.WEST);
        menuPanel.add(botonPanel, BorderLayout.EAST);
        add(menuPanel, BorderLayout.NORTH);

        // Panel para el título y películas
        JPanel centroPanel = new JPanel();
        centroPanel.setOpaque(false);
        centroPanel.setLayout(new BorderLayout());

        // Crear un panel para agrupar el título y las películas
        JPanel tituloYPeliculasPanel = new JPanel();
        tituloYPeliculasPanel.setOpaque(false);
        tituloYPeliculasPanel.setLayout(new BoxLayout(tituloYPeliculasPanel, BoxLayout.Y_AXIS));

        // Etiqueta "Populares Ahora Mismo"
        populares = new JLabel("POPULARES AHORA MISMO", SwingConstants.CENTER);
        populares.setForeground(Color.WHITE);
        populares.setFont(new Font("Verdana", Font.BOLD, 18));
        populares.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el título horizontalmente
        populares.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Agregar el título al panel
        tituloYPeliculasPanel.add(populares);

        // Panel para las películas populares
        titulosPeliculas = new JPanel();
        titulosPeliculas.setOpaque(false);
        titulosPeliculas.setLayout(new GridLayout(1, 5, 20, 20)); // Una fila con 5 columnas
        tituloYPeliculasPanel.add(titulosPeliculas);

        // Agregar el panel agrupado al centro del BorderLayout
        centroPanel.add(tituloYPeliculasPanel, BorderLayout.NORTH);
        mainPanel.add(centroPanel, BorderLayout.CENTER);
        cargarPeliculasPopulares();
        cargarPeliculasDuracion();

        // Agregar el panel principal a la ventana
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void cargarPeliculasDuracion() {
        // Ordenar películas por duración (descendente y ascendente)
        peliculas.sort((p1, p2) -> Integer.compare(Integer.parseInt(p2.getDuracion()), Integer.parseInt(p1.getDuracion())));
        ArrayList<Pelicula> masLargas = new ArrayList<>();
        for (int i = 0; i < 2 && i < peliculas.size(); i++) {
            masLargas.add(peliculas.get(i));
        }

        peliculas.sort((p1, p2) -> Integer.compare(Integer.parseInt(p1.getDuracion()), Integer.parseInt(p2.getDuracion())));
        ArrayList<Pelicula> masCortas = new ArrayList<>();
        for (int i = 0; i < 2 && i < peliculas.size(); i++) {
            masCortas.add(peliculas.get(i));
        }

        // Crear el panel inferior
        JPanel panelInferior = new JPanel(new GridLayout(1, 2, 40, 0)); // Separación horizontal uniforme
        panelInferior.setOpaque(false);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen alrededor del panel

        // Crear panel con título y películas de mayor duración
        JPanel panelMayorDuracion = new JPanel();
        panelMayorDuracion.setOpaque(false);
        panelMayorDuracion.setLayout(new BorderLayout());
        panelMayorDuracion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        JLabel tituloMayorDuracion = new JLabel("PELICULAS MAS LARGAS", SwingConstants.CENTER);
        tituloMayorDuracion.setFont(new Font("Verdana", Font.BOLD, 18));
        tituloMayorDuracion.setForeground(Color.WHITE);
        panelMayorDuracion.add(tituloMayorDuracion, BorderLayout.NORTH);

        JPanel peliculasLargasPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // Separación entre películas
        peliculasLargasPanel.setOpaque(false);
        for (Pelicula pelicula : masLargas) {
            peliculasLargasPanel.add(crearBotonPelicula(pelicula));
        }
        panelMayorDuracion.add(peliculasLargasPanel, BorderLayout.CENTER);

        // Crear panel con título y películas de menor duración
        JPanel panelMenorDuracion = new JPanel();
        panelMenorDuracion.setOpaque(false);
        panelMenorDuracion.setLayout(new BorderLayout());
        panelMenorDuracion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        JLabel tituloMenorDuracion = new JLabel("PELICULAS MAS CORTAS", SwingConstants.CENTER);
        tituloMenorDuracion.setFont(new Font("Verdana", Font.BOLD, 18));
        tituloMenorDuracion.setForeground(Color.WHITE);
        panelMenorDuracion.add(tituloMenorDuracion, BorderLayout.NORTH);

        JPanel peliculasCortasPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // Separación entre películas
        peliculasCortasPanel.setOpaque(false);
        for (Pelicula pelicula : masCortas) {
            peliculasCortasPanel.add(crearBotonPelicula(pelicula));
        }
        panelMenorDuracion.add(peliculasCortasPanel, BorderLayout.CENTER);

        // Añadir ambos paneles al panel inferior
        panelInferior.add(panelMayorDuracion);
        panelInferior.add(panelMenorDuracion);

        // Añadir el panel inferior al mainPanel
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }



    private JButton crearBotonPelicula(Pelicula pelicula) {
        ImageIcon imagenOriginal = new ImageIcon(getClass().getResource(pelicula.getImagen2()));
        ImageIcon imagenRedimensionada = redimensionarImagen(imagenOriginal, 120, 150);

        JButton botonPelicula = new JButton();
        botonPelicula.setIcon(imagenRedimensionada);
        botonPelicula.setFocusPainted(false);
        botonPelicula.setContentAreaFilled(false);
        botonPelicula.setBorderPainted(false);
        botonPelicula.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonPelicula.addActionListener(e -> {
            new VentanaCartelera(vActual).setVisible(true);
            dispose();
        });
        return botonPelicula;
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
