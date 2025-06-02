package vista.paneles;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.GestorAeropuerto;

// Asumiendo que GestorAeropuerto es visible
// import tu_paquete.GestorAeropuerto;

public class PanelBusquedaFactura extends JPanel {

    private GestorAeropuerto gestor; // Mantenemos la referencia

    // Componentes de la interfaz
    private JTextField txtFechaBusqueda;
    private JButton btnFiltrar;
    private JTable tableFacturasBusqueda;
    private DefaultTableModel tableModelBusqueda;

    public PanelBusquedaFactura(GestorAeropuerto gestor) {
        this.gestor = gestor;

        setLayout(new BorderLayout()); // Layout principal del panel
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor

        // --- Panel Superior (Título y Controles de Búsqueda) ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Apila título y barra de búsqueda verticalmente
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Espacio inferior

        // Título
        JLabel lblTitulo = new JLabel("Búsqueda de Factura");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar título
        topPanel.add(lblTitulo);

        topPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre título y controles de búsqueda

        // Panel de Controles de Búsqueda
        JPanel searchControlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Alinea a la izquierda con espacio horizontal
        searchControlsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar este panel horizontalmente

        // Etiqueta Fecha
        searchControlsPanel.add(new JLabel("Fecha:"));

        // Campo de fecha con icono de calendario (placeholder)
        JPanel dateInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txtFechaBusqueda = new JTextField("DD/MM/YYYY", 10); // Placeholder de texto y tamaño
        JLabel iconoCalendarioBusqueda = new JLabel(" \uD83D\uDCC5"); // Icono de calendario unicode
        dateInputPanel.add(txtFechaBusqueda);
        dateInputPanel.add(iconoCalendarioBusqueda);
        searchControlsPanel.add(dateInputPanel);

        // Botón Filtrar
        btnFiltrar = new JButton("Filtrar");
        searchControlsPanel.add(btnFiltrar);

        topPanel.add(searchControlsPanel); // Añade los controles de búsqueda al panel superior

        add(topPanel, BorderLayout.NORTH); // Añade el panel superior completo a la parte norte del panel principal

        // --- Tabla de Resultados de Búsqueda ---
        // Definir las columnas de la tabla (igual que en Facturas por pagar + Estado)
        String[] columnNames = {"ID Factura", "Fecha", "Proveedor", "Concepto", "Cantidad (€)", "Estado"};
        tableModelBusqueda = new DefaultTableModel(columnNames, 0); // 0 significa 0 filas inicialmente
        tableFacturasBusqueda = new JTable(tableModelBusqueda);

        // Configuración básica de la tabla (opcional)
        tableFacturasBusqueda.setFillsViewportHeight(true);
        tableFacturasBusqueda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // NOTA: Inicialmente la tabla estará vacía o puedes añadir datos de ejemplo
        // que se filtren al hacer clic en el botón.

        // Meter la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableFacturasBusqueda);

        add(scrollPane, BorderLayout.CENTER); // La tabla con scroll en el centro

        // --- Añadir Action Listener al botón Filtrar ---
        btnFiltrar.addActionListener(e -> {
            // Aquí iría la lógica para realizar la búsqueda de facturas
            String fechaBusqueda = txtFechaBusqueda.getText();
            // Deberías obtener datos de tu sistema basándote en la fecha (y otros criterios si los añades)
            // Por ahora, solo mostramos un mensaje y añadimos datos de ejemplo filtrados

            JOptionPane.showMessageDialog(this, "Lógica de búsqueda para la fecha: " + fechaBusqueda, "Buscar Facturas", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar la tabla antes de añadir nuevos resultados
            tableModelBusqueda.setRowCount(0);

            // Añadir datos de ejemplo (simulando un filtro)
            addSampleFilteredData(fechaBusqueda);
        });
        
    }

    // Método para añadir datos de ejemplo a la tabla (simulando un filtro)
    private void addSampleFilteredData(String filterDate) {
         // En una aplicación real, aquí consultarías tu fuente de datos
         // y añadirías las filas que coincidan con el filtro
         if ("26/10/2023".equals(filterDate) || "DD/MM/YYYY".equals(filterDate)) { // Ejemplo simple de filtro
             tableModelBusqueda.addRow(new Object[]{"INV001", "26/10/2023", "Aerolinea A", "Uso de pista", 1500.00, "Pendiente"});
             tableModelBusqueda.addRow(new Object[]{"INV003", "26/10/2023", "Aerolinea C", "Tasas de aterrizaje", 2200.75, "Pagada"});
             tableModelBusqueda.addRow(new Object[]{"INV005", "26/10/2023", "Aerolinea B", "Combustible", 5000.00, "Pendiente"});
             tableModelBusqueda.addRow(new Object[]{"INV007", "26/10/2023", "Aerolinea A", "Mantenimiento menor", 650.00, "Pendiente"});
         } else if ("25/10/2023".equals(filterDate)) {
              tableModelBusqueda.addRow(new Object[]{"INV002", "25/10/2023", "Aerolinea B", "Servicios de hangar", 800.50, "Pendiente"});
              tableModelBusqueda.addRow(new Object[]{"INV006", "25/10/2023", "Aerolinea C", "Uso de finger", 400.00, "Pagada"});
         }
        // Añadir más lógica de filtro según necesites
    }
}