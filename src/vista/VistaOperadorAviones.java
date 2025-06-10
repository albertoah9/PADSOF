package vista;

import java.awt.*;
import javax.swing.*;

public class VistaOperadorAviones extends JFrame {

    public JButton btnVerFlota;
    public JButton btnRegistrarAvion;
    public JButton btnEliminarAvion;
    public JButton btnVolver;
    public JLabel lblTitulo;

    public VistaOperadorAviones() {
        setTitle("Gestión de Aviones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // márgenes

        lblTitulo = new JLabel("Gestión de Flota de la Aerolínea", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        btnVerFlota = crearBoton("Ver Flota de Aviones");
        panel.add(btnVerFlota);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnRegistrarAvion = crearBoton("Registrar Nuevo Avión");
        panel.add(btnRegistrarAvion);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnEliminarAvion = crearBoton("Eliminar Avión");
        panel.add(btnEliminarAvion);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnVolver = crearBoton("Volver al Menú Principal");
        panel.add(btnVolver);

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