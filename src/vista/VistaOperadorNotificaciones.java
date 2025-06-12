package vista;

import javax.swing.*;
import java.awt.*;

public class VistaOperadorNotificaciones extends JFrame {

    public JList<String> listaNotificaciones;
    public DefaultListModel<String> modeloLista;
    public JButton btnVolver;
    public JLabel lblTitulo;

    public VistaOperadorNotificaciones() {
        setTitle("Notificaciones del Usuario");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        lblTitulo = new JLabel("Notificaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaNotificaciones = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaNotificaciones);
        add(scroll, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void agregarNotificacion(String texto) {
        modeloLista.addElement(texto);
    }

    public void limpiarLista() {
        modeloLista.clear();
    }
}