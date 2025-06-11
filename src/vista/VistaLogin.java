package vista;

import java.awt.*;
import javax.swing.*;

public class VistaLogin extends JFrame {

    public JTextField txtUsuario;
    public JPasswordField txtContrasena;
    public JButton btnIniciarSesion;
    public JLabel lblMensaje;
    public JLabel lblTitulo;

    public VistaLogin() {
        setTitle("Login - Sistema de Gestión Aeroportuaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal con márgenes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Título
        lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        // Campo Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtUsuario.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto
        panel.add(txtUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campo Contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtContrasena.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto
        panel.add(txtContrasena);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Botón de Iniciar Sesión
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnIniciarSesion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnIniciarSesion);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Mensaje de estado
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblMensaje);

        add(panel, BorderLayout.CENTER);
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
    }
}