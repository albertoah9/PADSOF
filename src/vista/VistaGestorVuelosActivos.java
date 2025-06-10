package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import modelo.GestorAeropuerto; // Para interactuar con el modelo

public class VistaGestorVuelosActivos extends JPanel {

    private GestorAeropuerto gestor; // Referencia al modelo
    public JTable tablaVuelosActivos;
    public JButton btnRefrescar;

    public VistaGestorVuelosActivos(GestorAeropuerto gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Vuelos Activos en Tiempo Real", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"ID Vuelo", "Origen", "Destino", "Estado", "Aerolínea", "Hora Salida", "Hora Llegada Prevista", "Pista", "Puerta"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Las celdas no son editables
            }
        };
        tablaVuelosActivos = new JTable(modeloTabla);
        
        // Configuración de la tabla para que se vea bien
        tablaVuelosActivos.setFillsViewportHeight(true);
        tablaVuelosActivos.setRowHeight(25);
        tablaVuelosActivos.getTableHeader().setReorderingAllowed(false); // No permitir reordenar columnas
        
        JScrollPane scrollPane = new JScrollPane(tablaVuelosActivos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRefrescar = new JButton("Refrescar Vuelos");
        panelSur.add(btnRefrescar);
        add(panelSur, BorderLayout.SOUTH);
    }

    public DefaultTableModel getModeloTablaVuelosActivos() {
        return (DefaultTableModel) tablaVuelosActivos.getModel();
    }
}