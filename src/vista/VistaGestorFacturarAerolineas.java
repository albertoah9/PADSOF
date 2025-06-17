package vista;

import java.awt.*;
import java.time.Year;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.Aerolinea;
import modelo.Descuento;

public class VistaGestorFacturarAerolineas extends JFrame {
    public JComboBox<Aerolinea> comboAerolineas;
    public JComboBox<Integer> comboMes;
    public JComboBox<Integer> comboAnio;
    public JButton btnFacturar;
    public JButton btnVolver;

    private JPanel panelDescuentos;
    private List<JCheckBox> checkboxes;

    public VistaGestorFacturarAerolineas(List<Aerolinea> aerolineas, List<Descuento> descuentos) {
        setTitle("Facturar Aerolíneas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel contenido = new JPanel(new GridLayout(6, 2, 10, 10));
        contenido.setBorder(new EmptyBorder(15, 15, 15, 15));

        contenido.add(new JLabel("Aerolínea:"));
        comboAerolineas = new JComboBox<>(aerolineas.toArray(new Aerolinea[0]));
        contenido.add(comboAerolineas);

        contenido.add(new JLabel("Mes (1-12):"));
        comboMes = new JComboBox<>();
        for (int i = 1; i <= 12; i++) comboMes.addItem(i);
        contenido.add(comboMes);

        contenido.add(new JLabel("Año:"));
        comboAnio = new JComboBox<>();
        int currentYear = Year.now().getValue();
        for (int i = currentYear; i >= currentYear - 5; i--) comboAnio.addItem(i);
        contenido.add(comboAnio);

        contenido.add(new JLabel("Descuentos disponibles:"));
        panelDescuentos = new JPanel();
        panelDescuentos.setLayout(new BoxLayout(panelDescuentos, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panelDescuentos);
        scroll.setPreferredSize(new Dimension(200, 100));
        contenido.add(scroll);

        for (Descuento d : descuentos) {
            JCheckBox box = new JCheckBox(d.toString());
            box.putClientProperty("descuento", d);
            panelDescuentos.add(box);
        }

        btnFacturar = new JButton("Facturar");
        btnVolver = new JButton("Volver");
        contenido.add(btnFacturar);
        contenido.add(btnVolver);

        add(contenido, BorderLayout.CENTER);
    }

    public Aerolinea getAerolineaSeleccionada() {
        return (Aerolinea) comboAerolineas.getSelectedItem();
    }

    public int getMesSeleccionado() {
        return (int) comboMes.getSelectedItem();
    }

    public int getAnioSeleccionado() {
        return (int) comboAnio.getSelectedItem();
    }

    public java.util.List<Descuento> getDescuentosSeleccionados() {
        java.util.List<Descuento> seleccionados = new java.util.ArrayList<>();
        for (Component c : panelDescuentos.getComponents()) {
            if (c instanceof JCheckBox cb && cb.isSelected()) {
                seleccionados.add((Descuento) cb.getClientProperty("descuento"));
            }
        }
        return seleccionados;
    }
}