package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCartelera extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame vActual, vAnterior;
	private JPanel panelCartelera, panelSuperior;
	private JButton botonVolver, botonPelicula;
	private ArrayList<Pelicula> peliculas;
	private ImageIcon imagenRedimensionada;

	public VentanaCartelera(JFrame vAnterior) {
		vActual = this;
		this.vAnterior= vAnterior;
		  
        setTitle("Cartelera");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal para contener la cartelera
        panelCartelera = new JPanel();
        panelCartelera.setLayout(new GridLayout(0, 2, 10, 10)); // Organiza en una cuadrícula
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
 String[] opciones = {"Madrid", "Bilbao", "Barcelona", "Vigo","Sevilla"};
        

        
        JComboBox<String> jcomboCiudades= new JComboBox<>(opciones);
        jcomboCiudades.setBounds(30,30,100,30);
        panelSuperior.add(jcomboCiudades,BorderLayout.EAST);
        
        JLabel peli= new JLabel("Peliculas en "+opciones[0]);
        panelSuperior.add(peli, BorderLayout.CENTER);
        jcomboCiudades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el elemento seleccionado
                String seleccion = (String) jcomboCiudades.getSelectedItem();
                
                	peli.setText("Peliculas en " + seleccion);
                	
                	panelSuperior.revalidate();
                	panelSuperior.repaint();
            }
        });
        
        
        // Crear una lista de películas
        peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Inception", "2h 28m", "Ciencia Ficción", 4.8, new ImageIcon(getClass().getResource("/Imagenes/Inception.jpg")),"Warner Bros",true,"160000000","839000000"));
        peliculas.add(new Pelicula("Titanic", "3h 15m", "Romance/Drama", 4.5, new ImageIcon(getClass().getResource("/Imagenes/Titanic.jpg")),"20th Century Fox",true,"200000000","2260000000"));
        peliculas.add(new Pelicula("The Dark Knight", "2h 32m", "Acción/Crimen", 4.9, new ImageIcon(getClass().getResource("/Imagenes/TheDarkNight.jpg")),"Warner Bros", true,"185000000","1010000000"));
        peliculas.add(new Pelicula("Toy Story", "1h 21m", "Animación/Familia", 4.7, new ImageIcon(getClass().getResource("/Imagenes/ToyStory.jpg")),"Disney pixar",true,"30000000","394000000"));
        peliculas.add(new Pelicula("Parasite", "2h 12m", "Drama/Thriller", 4.6, new ImageIcon(getClass().getResource("/Imagenes/Parasite.jpg")),"CJ Entertaiment",true,"11400000","262300000"));
        // Puedes añadir más películas aquí...
        
        // Crear botones para cada película en la cartelera
        for (Pelicula pelicula : peliculas) {
            //JButton botonPelicula;
            if (pelicula.getImagen() != null) {
            	imagenRedimensionada = redimensionarImagen(pelicula.getImagen(), 125, 125); // redimensionar las imagenes debido a su tamaño
				botonPelicula = new JButton(pelicula.getTitulo(), imagenRedimensionada);
				botonPelicula.setVerticalTextPosition(SwingConstants.BOTTOM);
				botonPelicula.setHorizontalTextPosition(SwingConstants.CENTER);
			} else {
				botonPelicula = new JButton(pelicula.getTitulo());
			}
            botonPelicula.setFont(new Font("Arial", Font.PLAIN, 16));
            
            botonPelicula.addActionListener(e -> mostrarInformacionPelicula(pelicula));
           
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
        dialogoPelicula.setSize(450, 350);
        dialogoPelicula.setLayout(new BorderLayout());
        dialogoPelicula.setLocationRelativeTo(this);
        

        // Crear el panel de información
        JPanel panelInformacion = new JPanel(new GridLayout(0,1));
        panelInformacion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        // Añadir la imagen al dialogo cuando cliquemos para ver la info
        if (pelicula.getImagen() != null) {
        	ImageIcon imagenRedimensionada = redimensionarImagen(pelicula.getImagen(), 125, 125);
			JLabel labelImagen = new JLabel(imagenRedimensionada);
			dialogoPelicula.add(labelImagen, BorderLayout.NORTH);
		}

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

        
        
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        panelBotones.add(btnCompras);
        panelBotones.add(btnOpinion);
        panelBotones.add(btnCerrar);
        
        
        // Añadir los componentes al diálogo
        dialogoPelicula.add(panelInformacion, BorderLayout.CENTER);
        dialogoPelicula.add(panelBotones, BorderLayout.SOUTH);
        
       

        // Mostrar el diálogo
        dialogoPelicula.setVisible(true);
    }
    
    // Metodo para redimensionar una imagen y que entren todas en la ventan y que se vea bien
    public ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int ancho, int alto) {
        Image imagen = imagenOriginal.getImage(); // Obtener la imagen original
        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada); // Devolver la imagen redimensionada como ImageIcon
    }
}