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
        setTitle("Asignar Elementos a Vuelo");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        panelAsignarPistaAterrizaje = new JPanel();
        panelAsignarPistaAterrizaje.setLayout(new BorderLayout());

        panelAsignarPistaDespegue = new JPanel();
        panelAsignarPistaDespegue.setLayout(new BorderLayout());

        panelAsignarAparcamiento = new JPanel();
        panelAsignarAparcamiento.setLayout(new BorderLayout());

        panelAsignarHangar = new JPanel();
        panelAsignarHangar.setLayout(new BorderLayout());

        panelAsignarFinger = new JPanel();
        panelAsignarFinger.setLayout(new BorderLayout());

        tabbedPane.addTab("Pista Aterrizaje", panelAsignarPistaAterrizaje);
        tabbedPane.addTab("Pista Despegue", panelAsignarPistaDespegue);
        tabbedPane.addTab("Aparcamiento", panelAsignarAparcamiento);
        tabbedPane.addTab("Hangar", panelAsignarHangar);
        tabbedPane.addTab("Finger", panelAsignarFinger);

        btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }
}
