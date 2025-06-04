package vista;

import java.awt.*;
import javax.swing.*;

public class VistaControladorModificarVuelo extends JFrame {
    public JLabel lblTitulo;
    private JPanel panelVuelos;
    private JButton btnConfirmar;
    private JButton btnActualizar;
    private JLabel lblHoraActual;

    public VistaControladorModificarVuelo() {
        setTitle("Modificar el estado del Vuelo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        lblTitulo = new JLabel("Aquí se podrá modificar el estado del vuelo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        // Panel dinámico para los vuelos
        panelVuelos = new JPanel();
        panelVuelos.setLayout(new BoxLayout(panelVuelos, BoxLayout.Y_AXIS));
        panelVuelos.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Inicialmente sin vuelos
        actualizarVuelos();

        // Agregar el panel de vuelos dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelVuelos,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(550, 150));
        panel.add(scrollPane);

        // Botón de confirmar cambio
        btnConfirmar = new JButton("Confirmar cambio");
        btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnConfirmar.setPreferredSize(new Dimension(150, 30));
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnConfirmar);

        // Botón de actualización
        btnActualizar = new JButton("Actualizar lista");
        btnActualizar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnActualizar.setPreferredSize(new Dimension(150, 30));
        btnActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnActualizar);

        // Hora actual (actualizada a 10:25 PM CEST, 03/06/2025)
        lblHoraActual = new JLabel("Hora actual: 10:25 PM CEST, 03/06/2025", SwingConstants.CENTER);
        lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHoraActual.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(lblHoraActual);

        add(panel, BorderLayout.CENTER);
    }

    // Método para actualizar la lista de vuelos
    public void actualizarVuelos() {
        panelVuelos.removeAll(); // Limpiar panel existente

        // Simulación de vuelos activos (puedes reemplazar esto con tu lógica real)
        String[][] vuelos = new String[0][4]; // Lista vacía inicialmente

        if (vuelos.length > 0) {
            String[] estados = { "ESPERANDO_PISTA", "ESPERANDO_ATERRIZAJE", "EN_PREPARACION", "APARCADO", "EN_HANGAR",
                    "EMBARCANDO", "ESPERANDO_DESPEGUE", "DESPEGADO", "RETRASADO", "EN_HORA" };
            for (int i = 0; i < vuelos.length; i++) {
                JPanel vueloPanel = new JPanel();
                vueloPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
                vueloPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                // Detalles del vuelo
                JLabel lblNumero = new JLabel("Vuelo: " + vuelos[i][0]);
                lblNumero.setFont(new Font("Arial", Font.PLAIN, 12));
                vueloPanel.add(lblNumero);

                JLabel lblOrigen = new JLabel("Origen: " + vuelos[i][1]);
                lblOrigen.setFont(new Font("Arial", Font.PLAIN, 12));
                vueloPanel.add(lblOrigen);

                JLabel lblDestino = new JLabel("Destino: " + vuelos[i][2]);
                lblDestino.setFont(new Font("Arial", Font.PLAIN, 12));
                vueloPanel.add(lblDestino);

                // Desplegable para el estado
                JComboBox<String> comboEstado = new JComboBox<>(estados);
                comboEstado.setSelectedItem(vuelos[i][3]); // Estado inicial
                comboEstado.setPreferredSize(new Dimension(120, 25)); // Corregido a Dimension
                comboEstado.setFont(new Font("Arial", Font.PLAIN, 12));
                vueloPanel.add(comboEstado);

                panelVuelos.add(vueloPanel);
                if (i < vuelos.length - 1) {
                    panelVuelos.add(new JSeparator());
                }
            }
        }

        panelVuelos.revalidate();
        panelVuelos.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaControladorModificarVuelo().setVisible(true);
        });
    }
}