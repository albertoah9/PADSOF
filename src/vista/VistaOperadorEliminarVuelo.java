package vista;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class VistaOperadorEliminarVuelo extends JFrame {

    public JComboBox<String> cmbVuelos;
    public JButton btnEliminar;
    public JButton btnVolver;

    private Map<String, Integer> vueloIdMap = new HashMap<>();

    public VistaOperadorEliminarVuelo() {
        setTitle("Eliminar Vuelo");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblSeleccion = new JLabel("Selecciona el vuelo a eliminar:", SwingConstants.CENTER);
        add(lblSeleccion);

        cmbVuelos = new JComboBox<>();
        add(cmbVuelos);

        JPanel panelBotones = new JPanel();
        btnEliminar = new JButton("Eliminar");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);
        add(panelBotones);
    }

    public void agregarVuelo(int id, String descripcion) {
        String display = "ID " + id + " - " + descripcion;
        vueloIdMap.put(display, id);
        cmbVuelos.addItem(display);
    }

    public void limpiarVuelos() {
        cmbVuelos.removeAllItems();
        vueloIdMap.clear();
    }

    public int getVueloSeleccionado() {
        String seleccion = (String) cmbVuelos.getSelectedItem();
        return vueloIdMap.getOrDefault(seleccion, -1);
    }
}