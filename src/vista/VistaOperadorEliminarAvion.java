package vista;

import java.awt.*;
import javax.swing.*;

public class VistaOperadorEliminarAvion extends JFrame {

    public JComboBox<String> comboAviones;
    public JButton btnEliminar;
    public JButton btnCancelar;

    public VistaOperadorEliminarAvion() {
        setTitle("Eliminar Avión");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Selecciona un avión a eliminar:"), gbc);

        gbc.gridx = 1;
        comboAviones = new JComboBox<>();
        comboAviones.setPrototypeDisplayValue("ID: 9999 | Matrícula: ABCD1234 | Marca: XXXXX | Modelo: YYYYYYY");
        panel.add(comboAviones, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        btnEliminar = new JButton("Eliminar");
        panel.add(btnEliminar, gbc);

        gbc.gridx = 1;
        btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    public void agregarAvion(String descripcion) {
        comboAviones.addItem(descripcion);
    }

    public void limpiarCombo() {
        comboAviones.removeAllItems();
    }

    public int getAvionSeleccionadoIndex() {
        return comboAviones.getSelectedIndex();
    }
}