package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaOperadorVerFlota extends JFrame {

    public JTable tablaAviones;
    public JButton btnVolver;
    private DefaultTableModel modeloTabla;

    public VistaOperadorVerFlota() {
        setTitle("Flota de la Aerolínea");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Aviones Registrados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {
            "ID", "Tipo", "Marca", "Modelo", "Matrícula",
            "Autonomía", "Año Compra", "Última Revisión",
            "Capacidad/Carga", "Detalles Extra"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaAviones = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAviones);
        add(scrollPane, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void agregarAvion(Object[] fila) {
        modeloTabla.addRow(fila);
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}
