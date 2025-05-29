package vista;

import java.awt.*;
import javax.swing.*;

public class VistaOperadorPrincipal extends JFrame {

    public JButton btnVuelos;
    public JButton btnAviones;
    public JButton btnFacturas;
    public JButton btnNotificaciones;
    public JLabel lblTitulo;

    public VistaOperadorPrincipal() {
        setTitle("Gestión del Operador de Aerolíneas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal con márgenes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // márgenes superior, izquierdo, inferior, derecho

        // Título
        lblTitulo = new JLabel("Panel del Operador de Aerolíneas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        // Botones con espaciado
        btnVuelos = crearBoton("Gestionar Vuelos");
        panel.add(btnVuelos);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnAviones = crearBoton("Gestionar Aviones");
        panel.add(btnAviones);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnFacturas = crearBoton("Gestionar Facturas");
        panel.add(btnFacturas);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnNotificaciones = crearBoton("Ver Notificaciones");
        panel.add(btnNotificaciones);

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