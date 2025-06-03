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

    public VistaControladorGraficos() {
        setTitle("Gráficos necesarios para el Controlador Aéreo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600); // Tamaño ajustado para los 4 recuadros
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        lblTitulo = new JLabel("Visualización de los gráficos para el Controlador Aéreo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        // Panel para los gráficos en una cuadrícula 2x2
        JPanel panelGraficos = new JPanel();
        panelGraficos.setLayout(new GridLayout(2, 2, 10, 10)); // 2 filas, 2 columnas, espaciado de 10px

        // Recuadro 1: Estado de los vuelos activos
        panelEstadoVuelos = new JPanel();
        panelEstadoVuelos.setBorder(BorderFactory.createTitledBorder("Estado de los vuelos activos"));
        panelEstadoVuelos.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelEstadoVuelos);

        // Recuadro 2: Tráfico aéreo por hora
        panelTraficoAereo = new JPanel();
        panelTraficoAereo.setBorder(BorderFactory.createTitledBorder("Tráfico aéreo por hora"));
        panelTraficoAereo.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelTraficoAereo);

        // Recuadro 3: Retrasos promedio por aerolínea
        panelRetrasosAerolinea = new JPanel();
        panelRetrasosAerolinea.setBorder(BorderFactory.createTitledBorder("Retrasos promedio por aerolínea"));
        panelRetrasosAerolinea.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelRetrasosAerolinea);

        // Recuadro 4: Uso de terminales
        panelUsoTerminales = new JPanel();
        panelUsoTerminales.setBorder(BorderFactory.createTitledBorder("Uso de terminales"));
        panelUsoTerminales.setPreferredSize(new Dimension(350, 200));
        panelGraficos.add(panelUsoTerminales);

        panel.add(panelGraficos);

        // Hora actual
        lblHoraActual = new JLabel("Hora actual: 10:35 PM CEST, 03/06/2025", SwingConstants.CENTER);
        lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHoraActual.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(lblHoraActual);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaControladorGraficos().setVisible(true);
        });
    }
}