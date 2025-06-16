package vista;

import javax.swing.*;
import java.awt.*;

public class VistaControladorVueloSeguro extends JFrame {
    public JButton btnVolver;
    public JTextArea areaDescripcion;
    public JButton btnReportar;
    public DefaultListModel<String> listaModelIncidentes;
    public JList<String> listaIncidentes;

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
