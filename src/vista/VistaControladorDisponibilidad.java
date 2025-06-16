package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaControladorDisponibilidad extends JFrame {

    public JTable tablaElementos;
    public JTextField txtFiltroID;
    public JTextField txtFiltroTipo;
    public JTextField txtFiltroDisponibilidad;
    public JButton btnFiltrar;
    public JButton btnVolver;

    private DefaultTableModel modeloTabla;

    public VistaControladorDisponibilidad() {
        setTitle("Buscar elementos disponibles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFiltros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelFiltros.add(new JLabel("ID:"));
        txtFiltroID = new JTextField();
        panelFiltros.add(txtFiltroID);

        panelFiltros.add(new JLabel("Tipo:"));
        txtFiltroTipo = new JTextField();
        panelFiltros.add(txtFiltroTipo);

        panelFiltros.add(new JLabel("Disponibilidad (libre/ocupado):"));
        txtFiltroDisponibilidad = new JTextField();
        panelFiltros.add(txtFiltroDisponibilidad);

        btnFiltrar = new JButton("Filtrar");
        panelFiltros.add(btnFiltrar);

        btnVolver = new JButton("Volver");
        panelFiltros.add(btnVolver);

        add(panelFiltros, BorderLayout.NORTH);

        // Tabla de resultados
        String[] columnas = { "ID", "Tipo", "Disponibilidad" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaElementos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaElementos);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    public void agregarElemento(Object[] fila) {
        modeloTabla.addRow(fila);
    }
}
