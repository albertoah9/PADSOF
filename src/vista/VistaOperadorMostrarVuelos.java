package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaOperadorMostrarVuelos extends JFrame {

    public JTable tablaVuelos;
    public JButton btnVolver;
    private DefaultTableModel modeloTabla;

    public VistaMostrarVuelos() {
        setTitle("Listado de Vuelos");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Vuelos Registrados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Origen", "Destino", "Fecha Salida", "Fecha Llegada", "Tipo", "Clase", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaVuelos = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaVuelos);
        add(scrollPane, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void agregarVuelo(Object[] datosFila) {
        modeloTabla.addRow(datosFila);
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}