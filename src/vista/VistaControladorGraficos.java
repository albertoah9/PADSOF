package vista;

import java.awt.*;
import javax.swing.*;

public class VistaControladorGraficos extends JFrame {
    public JLabel lblTitulo;
    private JPanel panelEstadoVuelos;
    private JPanel panelTraficoAereo;
    private JPanel panelRetrasosAerolinea;
    private JPanel panelUsoTerminales;
    private JLabel lblHoraActual;
    public JButton btnVolver;

    public VistaControladorGraficos() {
        setTitle("Gráficos necesarios para el Controlador Aéreo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("Visualización de los gráficos para el Controlador Aéreo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        JPanel panelGraficos = new JPanel();
        panelGraficos.setLayout(new GridLayout(2, 2, 10, 10));

        panelEstadoVuelos = new JPanel();
        panelEstadoVuelos.setBorder(BorderFactory.createTitledBorder("Estado de los vuelos activos"));
        panelEstadoVuelos.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelEstadoVuelos);

        panelTraficoAereo = new JPanel();
        panelTraficoAereo.setBorder(BorderFactory.createTitledBorder("Tráfico aéreo por hora"));
        panelTraficoAereo.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelTraficoAereo);

        panelRetrasosAerolinea = new JPanel();
        panelRetrasosAerolinea.setBorder(BorderFactory.createTitledBorder("Retrasos promedio por aerolínea"));
        panelRetrasosAerolinea.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelRetrasosAerolinea);

        panelUsoTerminales = new JPanel();
        panelUsoTerminales.setBorder(BorderFactory.createTitledBorder("Uso de terminales"));
        panelUsoTerminales.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelUsoTerminales);

        panel.add(panelGraficos);

        lblHoraActual = new JLabel("Hora actual: 10:35 PM CEST, 03/06/2025", SwingConstants.CENTER);
        lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHoraActual.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(lblHoraActual);

        add(panel, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaControladorGraficos().setVisible(true);
        });
    }
}