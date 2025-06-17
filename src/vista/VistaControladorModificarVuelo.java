package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para que el controlador aéreo modifique el estado de los vuelos.
 * 
 * Muestra una lista de vuelos (o sus componentes), permite actualizarla
 * y confirmar cambios en su estado.
 */
public class VistaControladorModificarVuelo extends JFrame {

    /** Panel donde se mostrarán los vuelos disponibles para modificar */
    public JPanel panelVuelos;
    /** Botón para confirmar el cambio de estado de un vuelo */
    public JButton btnConfirmar;
    /** Botón para actualizar la lista de vuelos en pantalla */
    public JButton btnActualizar;
    /** Botón para volver a la pantalla anterior */
    public JButton btnVolver;
    /** Etiqueta con el título de la ventana */
    public JLabel lblTitulo;
    /** Etiqueta que muestra la hora actual */
    private JLabel lblHoraActual;

    /**
     * Constructor que inicializa y arma toda la interfaz gráfica
     * para la modificación de estados de vuelos.
     */
    public VistaControladorModificarVuelo() {
        setTitle("Modificar el estado del Vuelo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("Aquí se podrá modificar el estado del vuelo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        panelVuelos = new JPanel();
        panelVuelos.setLayout(new BoxLayout(panelVuelos, BoxLayout.Y_AXIS));
        panelVuelos.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JScrollPane scrollPane = new JScrollPane(panelVuelos,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(550, 150));
        panel.add(scrollPane);

        btnConfirmar = new JButton("Confirmar cambio");
        btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnConfirmar.setPreferredSize(new Dimension(150, 30));
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnConfirmar);

        btnActualizar = new JButton("Actualizar lista");
        btnActualizar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnActualizar.setPreferredSize(new Dimension(150, 30));
        btnActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnActualizar);

        lblHoraActual = new JLabel("Hora actual: 10:25 PM CEST, 03/06/2025", SwingConstants.CENTER);
        lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHoraActual.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(lblHoraActual);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVolver.setPreferredSize(new Dimension(150, 30));
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelInferior.add(btnVolver);

        add(panel, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

}
