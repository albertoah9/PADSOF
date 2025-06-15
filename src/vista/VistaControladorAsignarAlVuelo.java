package vista;

import javax.swing.*;
import java.awt.*;

public class VistaControladorAsignarAlVuelo extends JFrame {

    public JButton btnVolver;
    public JTabbedPane pestañas;

    public JPanel panelAsignarAparcamiento;
    public JPanel panelAsignarHangar;
    public JPanel panelAsignarFinger;
    public JPanel panelAsignarPistaAterrizaje;
    public JPanel panelAsignarPistaDespegue;

    public VistaControladorAsignarAlVuelo() {
        setTitle("Asignaciones al Vuelo");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pestañas = new JTabbedPane();

        panelAsignarAparcamiento = crearPanelSimple("Aquí se asigna un aparcamiento");
        panelAsignarHangar = crearPanelSimple("Aquí se asigna un hangar");
        panelAsignarFinger = crearPanelSimple("Aquí se asigna un finger");
        panelAsignarPistaAterrizaje = crearPanelSimple("Aquí se asigna una pista de aterrizaje");
        panelAsignarPistaDespegue = crearPanelSimple("Aquí se asigna una pista de despegue");

        pestañas.addTab("Aparcamiento", panelAsignarAparcamiento);
        pestañas.addTab("Hangar", panelAsignarHangar);
        pestañas.addTab("Finger", panelAsignarFinger);
        pestañas.addTab("Pista Aterrizaje", panelAsignarPistaAterrizaje);
        pestañas.addTab("Pista Despegue", panelAsignarPistaDespegue);

        add(pestañas, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private JPanel crearPanelSimple(String texto) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        panel.add(etiqueta, BorderLayout.CENTER);
        return panel;
    }
}