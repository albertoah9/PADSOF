package vista;

import java.awt.*;
import javax.swing.*;

public class VistaControladorPrincipal extends JFrame {

    public JButton btnVuelos;
    public JButton btnDisponibilidad;
    public JButton btnGraficos;
    public JButton btnNotificaciones;
    public JLabel lblTitulo;
    public JButton btnVueloSeguro;

    public VistaControladorPrincipal() {
        setTitle("Gestión del Controlador Aéreo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        lblTitulo = new JLabel("Panel del Controlador Aéreo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        btnVuelos = crearBoton("Gestionar Vuelos");
        panel.add(btnVuelos);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnDisponibilidad = crearBoton("Disponibilidad de elementos");
        panel.add(btnDisponibilidad);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnGraficos = crearBoton("Gráficos");
        panel.add(btnGraficos);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnNotificaciones = crearBoton("Ver Notificaciones");
        panel.add(btnNotificaciones);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnVueloSeguro = crearBoton("Incidencias");
        panel.add(btnVueloSeguro);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

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