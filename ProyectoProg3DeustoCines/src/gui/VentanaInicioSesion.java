package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaInicioSesion extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;

	public VentanaInicioSesion(JFrame vAnterior) {
		setTitle("Inicio de Sesión");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Título
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 51, 102));
		lblTitulo.setBorder(new EmptyBorder(10, 0, 10, 0));
		add(lblTitulo, BorderLayout.NORTH);

		// Panel principal centrado
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(Color.WHITE);
		panelCentro.setLayout(new GridLayout(3, 1, 10, 10));
		panelCentro.setBorder(new EmptyBorder(20, 30, 20, 30));

		// Campo Usuario
		txtUsuario = new JTextField();
		txtUsuario.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createLineBorder(new Color(0, 102, 204), 2, true), 
		BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsuario.setPreferredSize(new Dimension(200, 40));
		txtUsuario.setToolTipText("Introduce tu usuario o correo");
		panelCentro.add(txtUsuario);

		// Campo Contraseña
		txtContrasenia = new JPasswordField();
		txtContrasenia.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createLineBorder(new Color(0, 102, 204), 2, true), 
		BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		
		txtContrasenia.setFont(new Font("Arial", Font.PLAIN, 14));
		txtContrasenia.setPreferredSize(new Dimension(200, 40));
		txtContrasenia.setToolTipText("Introduce tu contraseña");
		panelCentro.add(txtContrasenia);

		// Botón Iniciar Sesión
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBackground(new Color(0, 102, 204));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 14));
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.setPreferredSize(new Dimension(200, 40));
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Intentando iniciar sesión...");
			}
		});
		panelCentro.add(btnIniciarSesion);

		add(panelCentro, BorderLayout.CENTER);
		setVisible(true);
	}

}