package vista;

import javax.swing.*;
import java.awt.*;

public class VistaControladorAsignarAlVuelo extends JFrame {
    public JTabbedPane tabbedPane;
    public JPanel panelAsignarPistaAterrizaje;
    public JPanel panelAsignarPistaDespegue;
    public JPanel panelAsignarAparcamiento;
    public JPanel panelAsignarHangar;
    public JPanel panelAsignarFinger;
    public JButton btnVolver;

    public VistaControladorAsignarAlVuelo() {
        setTitle("Asignar Elementos a Vuelos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        panelAsignarPistaAterrizaje = new JPanel(new BorderLayout());
        panelAsignarPistaDespegue = new JPanel(new BorderLayout());
        panelAsignarAparcamiento = new JPanel(new BorderLayout());
        panelAsignarHangar = new JPanel(new BorderLayout());
        panelAsignarFinger = new JPanel(new BorderLayout());

        tabbedPane.addTab("Pista Aterrizaje", panelAsignarPistaAterrizaje);
        tabbedPane.addTab("Pista Despegue", panelAsignarPistaDespegue);
        tabbedPane.addTab("Aparcamiento", panelAsignarAparcamiento);
        tabbedPane.addTab("Hangar", panelAsignarHangar);
        tabbedPane.addTab("Finger", panelAsignarFinger);

        btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }
}
