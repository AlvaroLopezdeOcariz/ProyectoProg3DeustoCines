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
    private static final long serialVersionUID = 1L;
	private JFrame vActual, vAnterior;
    private ArrayList<Pelicula> peliculas;
    private JPanel panelCartelera, panelSuperior;
    private JButton botonVolver;
    private JLabel lblBuscador;
    private JTextField txtPeliculaBuscar;

    public VentanaCartelera(JFrame vAnterior) {
        vActual = this;
        this.vAnterior = vAnterior;

        setTitle("Cartelera");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con buscador y botón Volver 
       
        
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSuperior.setBackground(new Color(230, 240, 255));

        botonVolver = crearBoton("Volver", new Color(200, 230, 255));
        botonVolver.addActionListener(e -> {
            vActual.dispose();
            vAnterior.setVisible(true);
        });

        lblBuscador = new JLabel("Buscar película: ");
        lblBuscador.setFont(new Font("Arial", Font.PLAIN, 16));

        txtPeliculaBuscar = new JTextField(30);
        txtPeliculaBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPeliculaBuscar.setBorder(BorderFactory.createLineBorder(new Color(200, 230, 255), 2));

        JPanel panelBuscador = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBuscador.setBackground(new Color(230, 240, 255));
        panelBuscador.add(lblBuscador);
        panelBuscador.add(txtPeliculaBuscar);

        panelSuperior.add(botonVolver, BorderLayout.WEST);
        panelSuperior.add(panelBuscador, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel principal de la cartelera
        panelCartelera = new JPanel(new GridLayout(0, 3, 15, 15));
        panelCartelera.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCartelera.setBackground(Color.WHITE);
        JScrollPane scrollPanelCartelera = new JScrollPane(panelCartelera);

        add(scrollPanelCartelera, BorderLayout.CENTER);

        // Listener para el buscador
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
                String texto = txtPeliculaBuscar.getText().toLowerCase();
                mostrarPeliculas(filtrarPeliculas(texto));
            }
        });

        // Cargar y mostrar todas las películas
        ArrayList<Pelicula> lsPelicula= listaImagenPeli();
        mostrarPeliculas(lsPelicula);

        setVisible(true);
    }

    private void mostrarPeliculas(ArrayList<Pelicula> peliculasFiltradas) {
        panelCartelera.removeAll();

        for (Pelicula pelicula : peliculasFiltradas) {
            ImageIcon imagenRedimensionada = redimensionarImagen(pelicula.getImagen(), 120, 150);
            JButton botonPelicula = crearBoton(pelicula.getTitulo(), Color.WHITE);
            botonPelicula.setIcon(imagenRedimensionada);
            botonPelicula.setHorizontalTextPosition(SwingConstants.CENTER);
            botonPelicula.setVerticalTextPosition(SwingConstants.BOTTOM);
            botonPelicula.addActionListener(e -> mostrarInformacionPelicula(pelicula));
            panelCartelera.add(botonPelicula);
        }

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
/*
    private ArrayList<Pelicula> cargarPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        File archivo = new File("src/peliculas.txt");
        if (!archivo.exists()) {
            System.err.println("El archivo peliculas.txt no existe.");
            return listaPeliculas;
        }
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine()) {
                String[] datos = sc.nextLine().split(";");
                if (datos.length == 6) {
                    String titulo = datos[0];
                    String descripcion = datos[1];
                    String duracion = datos[2];
                    String genero = datos[3];
                    double precio = Double.parseDouble(datos[4]);
                    ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/" + datos[5]));
                    listaPeliculas.add(new Pelicula(titulo, descripcion, duracion, genero, precio, imagen));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPeliculas;
    }
    */
   
    
    private ArrayList<Pelicula> listaImagenPeli(){
    	 ArrayList<Pelicula> listaPeliculas = BDPeliculas.obtenerPeliculas();
		ArrayList<Pelicula> lsPeliculaImagen= new ArrayList<Pelicula>();
		for(Pelicula peli: listaPeliculas) {
			ImageIcon imagen =  new ImageIcon(getClass().getResource(peli.getImagen2()));
			lsPeliculaImagen.add(new Pelicula(peli.getTitulo(), peli.getDescripcion(), peli.getDuracion(), peli.getGenero(), imagen, peli.getValoracion(), peli.getProductora(), peli.getRentabilidad(), peli.getPresupuesto(),peli.getTaquilla()));
    	
			
    	
		}
    	return lsPeliculaImagen;
    	
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
        panelInformacion.add(new JLabel("Valoración:" + pelicula.getValoracion()));
        //panelInformacion.add(new JLabel("Precio: " + pelicula.getPrecio() + "€"));

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
        
        
        
        JButton btnCompras = new JButton("Comprar");
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

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setFocusPainted(false);
        boton.setBackground(colorFondo);
        boton.setBorder(BorderFactory.createLineBorder(new Color(200, 230, 255), 2));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int ancho, int alto) {
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
