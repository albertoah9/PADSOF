package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para el control de vuelos seguros y reporte de incidentes.
 * 
 * Esta ventana permite al controlador reportar nuevos incidentes
 * y consultar la lista de incidentes ya reportados.
 */
public class VistaControladorVueloSeguro extends JFrame {

    /** Botón para volver a la ventana anterior */
    public JButton btnVolver;
    /** Área de texto para describir el incidente */
    public JTextArea areaDescripcion;
    /** Botón para enviar el reporte del incidente */
    public JButton btnReportar;
    /** Modelo que contiene la lista de incidentes reportados */
    public DefaultListModel<String> listaModelIncidentes;
    /** Lista que muestra los incidentes reportados */
    public JList<String> listaIncidentes;

    /**
     * Constructor que crea y configura la ventana,
     * con pestañas para reportar incidentes y ver la lista.
     */
    public VistaControladorVueloSeguro() {
        setTitle("Control de Vuelos y Reporte de Incidentes");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelIncidentes = new JPanel(new BorderLayout());

        JPanel panelArriba = new JPanel(new BorderLayout());
        panelArriba.add(new JLabel("Descripción del incidente:"), BorderLayout.NORTH);

        panelIncidentes.add(panelArriba, BorderLayout.NORTH);

        areaDescripcion = new JTextArea(5, 30);
        JScrollPane scrollDescripcion = new JScrollPane(areaDescripcion);
        panelIncidentes.add(scrollDescripcion, BorderLayout.CENTER);

        btnReportar = new JButton("Reportar Incidente");
        panelIncidentes.add(btnReportar, BorderLayout.SOUTH);

        tabbedPane.addTab("Reportar Incidente", panelIncidentes);

        JPanel panelLista = new JPanel(new BorderLayout());
        listaModelIncidentes = new DefaultListModel<>();
        listaIncidentes = new JList<>(listaModelIncidentes);
        JScrollPane scrollLista = new JScrollPane(listaIncidentes);
        panelLista.add(scrollLista, BorderLayout.CENTER);
        tabbedPane.addTab("Incidentes Reportados", panelLista);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);
    }
}
