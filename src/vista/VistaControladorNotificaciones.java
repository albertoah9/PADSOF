package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana que muestra las notificaciones para el controlador aéreo.
 * 
 * Permite ver la lista de notificaciones y volver a la pantalla anterior.
 */
public class VistaControladorNotificaciones extends JFrame {

    /** Botón para volver a la pantalla anterior */
    public JButton btnVolver;
    /** Modelo que guarda las notificaciones mostradas en la lista */
    private DefaultListModel<String> modeloLista;
    /** Lista donde se visualizan las notificaciones */
    private JList<String> listaNotificaciones;

    /**
     * Constructor que crea la interfaz con la lista de notificaciones y el botón
     * volver.
     */
    public VistaControladorNotificaciones() {
        setTitle("Notificaciones del Controlador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        listaNotificaciones = new JList<>(modeloLista);
        listaNotificaciones.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(listaNotificaciones);

        btnVolver = new JButton("Volver");

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelPrincipal.add(new JLabel("Lista de notificaciones:"), BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnVolver);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    /**
     * Limpia todas las notificaciones de la lista.
     */
    public void limpiarLista() {
        modeloLista.clear();
    }

    /**
     * Agrega una nueva notificación a la lista para mostrar.
     * 
     * @param texto texto de la notificación a agregar
     */
    public void agregarNotificacion(String texto) {
        modeloLista.addElement(texto);
    }

    /**
     * Devuelve la lista de notificaciones para posibles acciones externas.
     * 
     * @return la lista de notificaciones (JList)
     */
    public JList<String> getListaNotificaciones() {
        return listaNotificaciones;
    }
}
