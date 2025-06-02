package vista.paneles;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel; // Para manejar los datos de la tabla

import modelo.GestorAeropuerto;

// Asumiendo que GestorAeropuerto es visible
// import tu_paquete.GestorAeropuerto;

public class PanelFacturasPorPagar extends JPanel {

    private GestorAeropuerto gestor; // Mantenemos la referencia

    // Componentes de la interfaz
    private JTable tableFacturas;
    private DefaultTableModel tableModel;
    private JButton btnVer;
    private JButton btnPagar;

    public PanelFacturasPorPagar(GestorAeropuerto gestor) {
        this.gestor = gestor;

        setLayout(new BorderLayout()); // Layout principal del panel
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor

        // --- Panel Superior (Título y Botones de Acción) ---
        JPanel topPanel = new JPanel(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Facturas por pagar");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        // lblTitulo.setHorizontalAlignment(SwingConstants.LEFT); // Alineado a la izquierda por defecto en BorderLayout.WEST
        topPanel.add(lblTitulo, BorderLayout.WEST);

        // Panel para los botones de acción (Ver y Pagar)
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Alinea a la derecha con 10px de espacio horizontal
        btnVer = new JButton("Ver");
        btnPagar = new JButton("Pagar");
        actionButtonPanel.add(btnVer);
        actionButtonPanel.add(btnPagar);

        topPanel.add(actionButtonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH); // Añade el panel superior a la parte norte del panel principal

        // --- Tabla de Facturas ---
        // Definir las columnas de la tabla (nombres placeholder por ahora)
        String[] columnNames = {"ID Factura", "Fecha", "Proveedor", "Concepto", "Cantidad (€)", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0); // 0 significa 0 filas inicialmente
        tableFacturas = new JTable(tableModel);

        // Configuración básica de la tabla (opcional)
        tableFacturas.setFillsViewportHeight(true); // La tabla ocupa todo el alto del JScrollPane
        tableFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitir seleccionar solo una fila a la vez

        // Añadir algunos datos de ejemplo (placeholder)
        addSampleInvoiceData();

        // Meter la tabla en un JScrollPane para que tenga scrollbars
        JScrollPane scrollPane = new JScrollPane(tableFacturas);

        add(scrollPane, BorderLayout.CENTER); // La tabla con scroll en el centro

        // --- Añadir Action Listeners a los botones ---
        btnVer.addActionListener(e -> {
            int selectedRow = tableFacturas.getSelectedRow();
            if (selectedRow != -1) { // Si hay una fila seleccionada
                // Aquí iría la lógica para "Ver" la factura seleccionada
                // Puedes obtener datos de la fila seleccionada así:
                Object invoiceId = tableModel.getValueAt(selectedRow, 0);
                JOptionPane.showMessageDialog(this, "Lógica para ver detalles de la factura: " + invoiceId, "Ver Factura", JOptionPane.INFORMATION_MESSAGE);
                // Podrías abrir una nueva ventana o mostrar otro panel con los detalles
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una factura para ver.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPagar.addActionListener(e -> {
            int selectedRow = tableFacturas.getSelectedRow();
            if (selectedRow != -1) { // Si hay una fila seleccionada
                 // Aquí iría la lógica para "Pagar" la factura seleccionada
                Object invoiceId = tableModel.getValueAt(selectedRow, 0);
                Object estadoActual = tableModel.getValueAt(selectedRow, 5);

                if ("Pendiente".equals(estadoActual)) { // Ejemplo: solo pagar si está Pendiente
                     int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres pagar la factura " + invoiceId + "?", "Confirmar Pago", JOptionPane.YES_NO_OPTION);
                     if (confirm == JOptionPane.YES_OPTION) {
                         // Lógica real de pago...
                         JOptionPane.showMessageDialog(this, "Lógica de pago para la factura: " + invoiceId, "Pagar Factura", JOptionPane.INFORMATION_MESSAGE);
                         // Actualizar el estado en la tabla (ejemplo)
                         tableModel.setValueAt("Pagada", selectedRow, 5);
                     }
                } else {
                     JOptionPane.showMessageDialog(this, "La factura " + invoiceId + " ya ha sido pagada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una factura para pagar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

    }

    // Método para añadir datos de ejemplo a la tabla
    private void addSampleInvoiceData() {
        tableModel.addRow(new Object[]{"INV001", "2023-10-26", "Aerolinea A", "Uso de pista", 1500.00, "Pendiente"});
        tableModel.addRow(new Object[]{"INV002", "2023-10-25", "Aerolinea B", "Servicios de hangar", 800.50, "Pendiente"});
        tableModel.addRow(new Object[]{"INV003", "2023-10-26", "Aerolinea C", "Tasas de aterrizaje", 2200.75, "Pagada"});
        tableModel.addRow(new Object[]{"INV004", "2023-10-24", "Aerolinea A", "Catering", 350.00, "Pendiente"});
        tableModel.addRow(new Object[]{"INV005", "2023-10-26", "Aerolinea B", "Combustible", 5000.00, "Pendiente"});
        tableModel.addRow(new Object[]{"INV006", "2023-10-25", "Aerolinea C", "Uso de finger", 400.00, "Pagada"});
        tableModel.addRow(new Object[]{"INV007", "2023-10-26", "Aerolinea A", "Mantenimiento menor", 650.00, "Pendiente"});
    }
}