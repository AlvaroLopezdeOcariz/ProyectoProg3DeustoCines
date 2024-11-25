package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCartelera extends JFrame {
    private JFrame vActual, vAnterior;
    private ArrayList<Pelicula> peliculas; // Lista de películas
    private JPanel panelCartelera, panelSuperior;
    private JButton botonVolver, botonPelicula;
    private ImageIcon imagenRedimensionada;
    private JLabel lblBuscador;
    private JTextField txtPeliculaBuscar;

    public VentanaCartelera(JFrame vAnterior) {
        vActual = this;
        this.vAnterior = vAnterior;

        setTitle("Cartelera");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de paneles
        panelCartelera = new JPanel(new GridLayout(0, 2, 10, 10));
        panelCartelera.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelSuperior = new JPanel(new BorderLayout());
        botonVolver = new JButton("Volver");
        panelSuperior.add(botonVolver, BorderLayout.WEST);
        add(panelSuperior, BorderLayout.NORTH);

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vActual.dispose();
                vAnterior.setVisible(true);
            }
        });

        // Configuración del buscador
        JPanel panelBuscador = new JPanel();
        lblBuscador = new JLabel("Película: ");
        txtPeliculaBuscar = new JTextField(30);
        panelBuscador.add(lblBuscador);
        panelBuscador.add(txtPeliculaBuscar);
        panelSuperior.add(panelBuscador, BorderLayout.CENTER);

        // Listener para actualizar la cartelera según la búsqueda
        txtPeliculaBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarCartelera();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarCartelera();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarCartelera();
            }

            private void actualizarCartelera() {
                // Obtener el texto del cuadro de búsqueda
                String texto = txtPeliculaBuscar.getText().toLowerCase();

                // Filtrar películas según el texto
                ArrayList<Pelicula> peliculasFiltradas = filtrarPeliculas(texto);

                // Limpiar el panelCartelera
                panelCartelera.removeAll();

                // Crear botones para las películas filtradas
                for (Pelicula pelicula : peliculasFiltradas) {
                    ImageIcon imagenRedimensionada = redimensionarImagen(pelicula.getImagen(), 125, 125);
                    JButton botonPelicula = new JButton(pelicula.getTitulo(), imagenRedimensionada);
                    botonPelicula.setFont(new Font("Arial", Font.PLAIN, 16));
                    botonPelicula.addActionListener(e -> mostrarInformacionPelicula(pelicula));
                    botonPelicula.setVerticalTextPosition(SwingConstants.BOTTOM);
                    botonPelicula.setHorizontalTextPosition(SwingConstants.CENTER);
                    panelCartelera.add(botonPelicula);
                }

                // Actualizar la interfaz
                panelCartelera.revalidate();
                panelCartelera.repaint();
            }

            private ArrayList<Pelicula> filtrarPeliculas(String texto) {
                ArrayList<Pelicula> filtradas = new ArrayList<>();
                for (Pelicula p : peliculas) {
                    if (p.getTitulo().toLowerCase().contains(texto)) {
                        filtradas.add(p);
                    }
                }
                return filtradas;
            }
        });

        // Leer películas desde el archivo
        peliculas = cargarPeliculas();

        // Crear botones para cada película
        for (Pelicula pelicula : peliculas) {
            imagenRedimensionada = redimensionarImagen(pelicula.getImagen(), 125, 125);
            botonPelicula = new JButton(pelicula.getTitulo(), imagenRedimensionada);
            botonPelicula.setFont(new Font("Arial", Font.PLAIN, 16));
            botonPelicula.addActionListener(e -> mostrarInformacionPelicula(pelicula));
            panelCartelera.add(botonPelicula);
            botonPelicula.setVerticalTextPosition(SwingConstants.BOTTOM);
            botonPelicula.setHorizontalTextPosition(SwingConstants.CENTER);
        }

        add(new JScrollPane(panelCartelera));
        setVisible(true);
    }

    private ArrayList<Pelicula> cargarPeliculas() {
        File f = new File("src/peliculas.txt");
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        if (!f.exists()) {
            System.err.println("El archivo peliculas.txt no existe.");
        }
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String linea = sc.nextLine();
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    String titulo = datos[0];
                    String descripcion = datos[1];
                    String duracion = datos[2];
                    String genero = datos[3];
                    double precio = Double.parseDouble(datos[4]);
                    String i = ("/imagenes/" + datos[5]);
                    ImageIcon imagen = new ImageIcon(getClass().getResource(i));
                    listaPeliculas.add(new Pelicula(titulo, descripcion, duracion, genero, precio, imagen));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listaPeliculas;
    }

    private void mostrarInformacionPelicula(Pelicula pelicula) {
        JDialog dialogoPelicula = new JDialog(this, pelicula.getTitulo(), true);
        dialogoPelicula.setSize(450, 350);
        dialogoPelicula.setLayout(new BorderLayout());
        dialogoPelicula.setLocationRelativeTo(this);

        JPanel panelInformacion = new JPanel(new GridLayout(0, 1));
        panelInformacion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (pelicula.getImagen() != null) {
            ImageIcon imagenRedimensionada = redimensionarImagen(pelicula.getImagen(), 125, 125);
            JLabel labelImagen = new JLabel(imagenRedimensionada);
            dialogoPelicula.add(labelImagen, BorderLayout.NORTH);
        }

        panelInformacion.add(new JLabel("Título: " + pelicula.getTitulo()));
        panelInformacion.add(new JLabel("Descripción: " + pelicula.getDescripcion()));
        panelInformacion.add(new JLabel("Duración: " + pelicula.getDuracion()));
        panelInformacion.add(new JLabel("Género: " + pelicula.getGenero()));
        panelInformacion.add(new JLabel("Precio: " + pelicula.getPrecio() + "€"));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialogoPelicula.dispose());
        // Boton para ver las opiniones sobre la pelicula
        JButton btnOpinion = new JButton("Opiniones");
        btnOpinion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogoPelicula.dispose();
				
				new VentanaOpiniones(vAnterior, pelicula);
				vActual.dispose();
				
			}
        	
        });
        
        
        
        JButton btnCompras = new JButton("Compras");
        btnCompras.addActionListener(e -> {
            new VentanaAsientos(this).setVisible(true);
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnCerrar);
        panelBotones.add(btnCompras);
        panelBotones.add(btnOpinion);

        dialogoPelicula.add(panelInformacion, BorderLayout.CENTER);
        dialogoPelicula.add(panelBotones, BorderLayout.SOUTH);

        dialogoPelicula.setVisible(true);
    }

    public ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int ancho, int alto) {
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
