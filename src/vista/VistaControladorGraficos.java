package vista;

import javax.swing.*;
import java.awt.*;

public class VistaControladorGraficos extends JFrame {

    private JLabel lblTitulo;
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

        // Panel principal vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        lblTitulo = new JLabel("Visualización de los gráficos para el Controlador Aéreo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelPrincipal.add(lblTitulo);

        // Paneles de estadísticas en grid
        JPanel panelGraficos = new JPanel(new GridLayout(2, 2, 10, 10));

        panelEstadoVuelos = crearPanelGrafico("Estado de los vuelos activos");
        panelGraficos.add(panelEstadoVuelos);

        panelTraficoAereo = crearPanelGrafico("Tráfico aéreo por hora");
        panelGraficos.add(panelTraficoAereo);

        panelRetrasosAerolinea = crearPanelGrafico("Retrasos promedio por aerolínea");
        panelGraficos.add(panelRetrasosAerolinea);

        panelUsoTerminales = crearPanelGrafico("Uso de terminales");
        panelGraficos.add(panelUsoTerminales);

        panelPrincipal.add(panelGraficos);

        // Hora actual (puedes actualizar esto desde el controlador si quieres)
        lblHoraActual = new JLabel("Hora actual: ", SwingConstants.CENTER);
        lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHoraActual.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelPrincipal.add(lblHoraActual);

        add(panelPrincipal, BorderLayout.CENTER);

        // Botón inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelGrafico(String titulo) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.setLayout(new BorderLayout());
        return panel;
    }

    // Getters para que el controlador pueda acceder a los paneles
    public JPanel getPanelEstadoVuelos() {
        return panelEstadoVuelos;
    }

    public JPanel getPanelTraficoAereo() {
        return panelTraficoAereo;
    }

    public JPanel getPanelRetrasosAerolinea() {
        return panelRetrasosAerolinea;
    }

    public JPanel getPanelUsoTerminales() {
        return panelUsoTerminales;
    }

    public JLabel getLblHoraActual() {
        return lblHoraActual;
    }
}
