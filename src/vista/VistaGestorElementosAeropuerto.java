package vista;

import java.awt.*;
import javax.swing.*;
import modelo.*;

public class VistaGestorElementosAeropuerto extends JFrame {

    public JButton btnNuevoElemento;
    public JButton btnVolver;

    public JList<Object> listaElementos;
    private DefaultListModel<Object> modeloLista;

    public VistaGestorElementosAeropuerto() {
        setTitle("Gestión de Elementos del Aeropuerto");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Elementos del Aeropuerto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaElementos = new JList<>(modeloLista);

        listaElementos.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel();
            label.setOpaque(true);

            if (value instanceof Finger f) {
                label.setText("Finger ID: " + f.getId() + ", Altura máx: " + f.getAlturaMax());
            } else if (value instanceof Hangar h) {
                label.setText("Hangar ID: " + h.getId() + ", Plazas: " + h.getNumPlazas());
            } else if (value instanceof ZonaAparcamiento z) {
                label.setText("Zona Aparcamiento ID: " + z.getId() + ", Plazas: " + z.getNumPlazas());
            } else if (value instanceof Pista p) {
                label.setText("Pista ID: " + p.getId() + ", Longitud: " + p.getLongitud());
            } else if (value instanceof PuertaEmbarque p) {
                label.setText("Puerta Embarque ID: " + p.getId() + ", Aforo: " + p.getAforoMaximo());
            }

            label.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
            return label;
        });

        JScrollPane scroll = new JScrollPane(listaElementos);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnNuevoElemento = new JButton("Nuevo Elemento");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnNuevoElemento);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    public void agregarElemento(Object elemento) {
        modeloLista.addElement(elemento);
    }

    public void limpiarLista() {
        modeloLista.clear();
    }
}