package vista;

import javax.swing.*;
import java.awt.*;

public class VistaControladorNotificaciones extends JFrame {

    public JButton btnVolver;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaNotificaciones;

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

    public void limpiarLista() {
        modeloLista.clear();
    }

    public void agregarNotificacion(String texto) {
        modeloLista.addElement(texto);
    }

    public JList<String> getListaNotificaciones() {
        return listaNotificaciones;
    }
}
