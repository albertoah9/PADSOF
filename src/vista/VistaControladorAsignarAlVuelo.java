package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana que permite asignar distintos recursos a un vuelo,
 * como pista, hangar, finger, etc.
 * 
 * Es una interfaz con varias pestañas para organizar mejor cada asignación.
 */
public class VistaControladorAsignarAlVuelo extends JFrame {

    /** Panel con pestañas donde se agrupan las diferentes asignaciones */
    public JTabbedPane tabbedPane;
    /** Panel para asignar la pista de aterrizaje */
    public JPanel panelAsignarPistaAterrizaje;
    /** Panel para asignar la pista de despegue */
    public JPanel panelAsignarPistaDespegue;
    /** Panel para asignar el aparcamiento del avión */
    public JPanel panelAsignarAparcamiento;
    /** Panel para asignar el hangar al vuelo */
    public JPanel panelAsignarHangar;
    /** Panel para asignar un finger (pasarela de embarque) */
    public JPanel panelAsignarFinger;
    /** Botón para volver a la pantalla anterior */
    public JButton btnVolver;

    /**
     * Constructor que arma toda la interfaz.
     * 
     * Se crean las pestañas y el botón de volver,
     * y se organiza todo con layouts.
     */
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
