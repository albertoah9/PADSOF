package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaOperadorMostrarFacturas extends JFrame {

    public JTable tablaFacturas;
    public DefaultTableModel modeloTabla;
    public JButton btnVolver;

    public VistaOperadorMostrarFacturas() {
        setTitle("Facturas de la Aerolínea");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"ID", "Fecha Emisión", "Fecha Vencimiento", "Monto", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaFacturas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaFacturas);

        btnVolver = new JButton("Volver");

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void agregarFila(Object[] datos) {
        modeloTabla.addRow(datos);
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}