package vista;

import java.awt.*;
import javax.swing.*;

public class VistaControladorVuelos extends JFrame {
    public JButton btnModificarVuelo;
    public JButton btnBuscarVuelo;
    public JLabel lblTitulo;

    public VistaControladorVuelos() {
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
        btnBuscarVuelo = crearBoton("Buscar Vuelo");
        panel.add(btnBuscarVuelo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnModificarVuelo = crearBoton("Modificar Estado del Vuelo");
        panel.add(btnModificarVuelo);
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
