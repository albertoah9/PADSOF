package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana que muestra varios gráficos útiles para el controlador aéreo.
 * 
 * Muestra visualmente el estado de los vuelos, el tráfico por hora,
 * los retrasos por aerolínea y el uso de las terminales.
 */
public class VistaControladorGraficos extends JFrame {

    /** Título principal de la ventana */
    private JLabel lblTitulo;
    /** Panel donde va el gráfico del estado de los vuelos */
    private JPanel panelEstadoVuelos;
    /** Panel donde va el gráfico del tráfico aéreo por hora */
    private JPanel panelTraficoAereo;
    /** Panel donde va el gráfico de retrasos por aerolínea */
    private JPanel panelRetrasosAerolinea;
    /** Panel donde va el gráfico de uso de terminales */
    private JPanel panelUsoTerminales;
    /** Etiqueta que muestra la hora actual */
    private JLabel lblHoraActual;
    /** Botón para volver a la pantalla anterior */
    public JButton btnVolver;

    /**
     * Constructor que arma toda la interfaz con los paneles para los gráficos.
     */
    public VistaControladorGraficos() {
        setTitle("Gráficos necesarios para el Controlador Aéreo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("Visualización de los gráficos para el Controlador Aéreo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelPrincipal.add(lblTitulo);

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

        lblHoraActual = new JLabel("Hora actual: ", SwingConstants.CENTER);
        lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHoraActual.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelPrincipal.add(lblHoraActual);

        add(panelPrincipal, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Método auxiliar para crear un panel con borde y título, donde se insertará un
     * gráfico.
     *
     * @param titulo El título del gráfico que se mostrará en ese panel.
     * @return El panel ya preparado para insertar el gráfico.
     */
    private JPanel crearPanelGrafico(String titulo) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.setLayout(new BorderLayout());
        return panel;
    }

    /** @return Panel donde se carga el gráfico del estado de los vuelos */
    public JPanel getPanelEstadoVuelos() {
        return panelEstadoVuelos;
    }

    /** @return Panel para el gráfico del tráfico aéreo por hora */
    public JPanel getPanelTraficoAereo() {
        return panelTraficoAereo;
    }

    /** @return Panel con el gráfico de retrasos por aerolínea */
    public JPanel getPanelRetrasosAerolinea() {
        return panelRetrasosAerolinea;
    }

    /** @return Panel para el gráfico del uso de terminales */
    public JPanel getPanelUsoTerminales() {
        return panelUsoTerminales;
    }

    /** @return Etiqueta que muestra la hora actual */
    public JLabel getLblHoraActual() {
        return lblHoraActual;
    }
}
