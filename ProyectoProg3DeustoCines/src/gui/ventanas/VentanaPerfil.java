package gui.ventanas;

import javax.swing.*;

import gui.clases.Usuario;

import java.awt.*;

public class VentanaPerfil extends JFrame {
    private JPanel panel;
    private JLabel nombreLabel,usuarioLabel,contraseniaLabel;

    public VentanaPerfil(JFrame vAnterior) {
        setTitle("Perfil de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        nombreLabel = new JLabel("Nombre: " + Usuario.getUsuarioActual().getNombre());
        nombreLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        
     
        usuarioLabel = new JLabel("Nombre de Usuario: " + Usuario.getUsuarioActual().getNomUsuario());
        usuarioLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        
       
        contraseniaLabel = new JLabel("Contraseña: " + Usuario.getUsuarioActual().getContrasenia());
        contraseniaLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        
       
        panel.add(nombreLabel);
        panel.add(usuarioLabel);
        panel.add(contraseniaLabel);

       
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setFont(new Font("Verdana", Font.BOLD, 14));
        cerrarButton.addActionListener(e -> dispose());

        panel.add(Box.createVerticalStrut(20));
        panel.add(cerrarButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
