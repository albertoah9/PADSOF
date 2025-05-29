package vista;

import java.awt.*;
import javax.swing.*;

public class VistaOperadorVuelos extends JFrame {

    public JButton btnCrearVuelo;
    public JButton btnModificarVuelo;
    public JButton btnEliminarVuelo;
    public JLabel lblTitulo;

    public VistaOperadorVuelos() {
        setTitle("Gestión de Vuelos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Márgenes

        // Título
        lblTitulo = new JLabel("Gestión de Vuelos de la Aerolínea", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        // Botones
        btnCrearVuelo = crearBoton("Crear Nuevo Vuelo");
        panel.add(btnCrearVuelo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnModificarVuelo = crearBoton("Modificar Estado del Vuelo");
        panel.add(btnModificarVuelo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnEliminarVuelo = crearBoton("Eliminar Vuelo");
        panel.add(btnEliminarVuelo);

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