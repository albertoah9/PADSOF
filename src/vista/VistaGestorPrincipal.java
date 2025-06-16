package vista;

import java.awt.*;
import javax.swing.*;
import modelo.GestorAeropuerto;

public class VistaGestorPrincipal extends JFrame {

    public JButton btnUsuarios;
    public JButton btnElementos;
    public JButton btnNotificaciones;
    public JButton btnFacturacion;
    public JLabel lblTitulo;

    public VistaGestorPrincipal(GestorAeropuerto gestor) {
        setTitle("Panel de Gestión del Aeropuerto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        lblTitulo = new JLabel("Panel del Gestor del Aeropuerto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        btnUsuarios = crearBoton("Gestión de Usuarios");
        panel.add(btnUsuarios);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnElementos = crearBoton("Gestión Elementos Aeropuerto");
        panel.add(btnElementos);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnNotificaciones = crearBoton("Notificaciones");
        panel.add(btnNotificaciones);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnFacturacion = crearBoton("Facturación");
        panel.add(btnFacturacion);

        add(panel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setPreferredSize(new Dimension(250, 50));
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setFocusPainted(false);
        return boton;
    }
}