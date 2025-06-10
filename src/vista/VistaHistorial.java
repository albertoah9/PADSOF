package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaHistorial extends JPanel {

    public JTable tablaHistorial;
    private DefaultTableModel modeloTabla;

    public VistaHistorial() {
        setLayout(new BorderLayout(10, 10)); // Usar BorderLayout para el panel principal
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Márgenes

        // --- Título del Panel ---
        JLabel lblTitulo = new JLabel("Historial de Eventos del Aeropuerto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Tabla de Historial ---
        String[] columnas = {"Timestamp", "Evento", "Detalles"}; // Columnas para el historial
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        tablaHistorial = new JTable(modeloTabla);
        tablaHistorial.setFillsViewportHeight(true); // Para que la tabla ocupe todo el alto disponible
        
        // Mejorar la apariencia de la tabla
        tablaHistorial.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tablaHistorial.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tablaHistorial.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        add(scrollPane, BorderLayout.CENTER); // La tabla ocupará el centro
    }

    /**
     * Agrega una fila a la tabla del historial.
     * @param datos Los datos de la fila (deben coincidir con el número de columnas).
     */
    public void agregarFila(Object[] datos) {
        modeloTabla.addRow(datos);
    }

    /**
     * Limpia todas las filas de la tabla.
     */
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}