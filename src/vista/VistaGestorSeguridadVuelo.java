package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import modelo.GestorAeropuerto; // Para interactuar con el modelo

public class VistaGestorSeguridadVuelo extends JPanel {

    private GestorAeropuerto gestor; // Referencia al modelo

    // Componentes para la gestión de seguridad de vuelo
    public JTable tablaIncidentes;
    public JButton btnReportarIncidente;
    public JButton btnVerDetallesIncidente;
    public JComboBox<String> cmbTipoIncidente;
    public JTextArea txtDescripcionIncidente;
    public JTextField txtVueloAfectadoId;


    public VistaGestorSeguridadVuelo(GestorAeropuerto gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Seguridad de Vuelo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Usaremos un JSplitPane para dividir la vista entre la tabla de incidentes y el formulario de reporte
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.6); // La tabla ocupará el 60% de la altura

        // Parte superior: Tabla de Incidentes
        JPanel panelTablaIncidentes = new JPanel(new BorderLayout());
        JLabel lblTablaTitulo = new JLabel("Historial de Incidentes", SwingConstants.LEFT);
        lblTablaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelTablaIncidentes.add(lblTablaTitulo, BorderLayout.NORTH);

        String[] columnasIncidentes = {"ID Incidente", "Tipo", "Vuelo Afectado", "Fecha", "Descripción Breve", "Estado"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnasIncidentes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaIncidentes = new JTable(modeloTabla);
        tablaIncidentes.setFillsViewportHeight(true);
        panelTablaIncidentes.add(new JScrollPane(tablaIncidentes), BorderLayout.CENTER);

        JPanel pnlBotonesTabla = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVerDetallesIncidente = new JButton("Ver Detalles");
        pnlBotonesTabla.add(btnVerDetallesIncidente);
        panelTablaIncidentes.add(pnlBotonesTabla, BorderLayout.SOUTH);

        splitPane.setTopComponent(panelTablaIncidentes);

        // Parte inferior: Formulario para Reportar Incidente
        JPanel panelReportarIncidente = new JPanel(new GridBagLayout());
        panelReportarIncidente.setBorder(BorderFactory.createTitledBorder("Reportar Nuevo Incidente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelReportarIncidente.add(new JLabel("Tipo de Incidente:"), gbc);
        gbc.gridx = 1;
        cmbTipoIncidente = new JComboBox<>(new String[]{"Retraso", "Desvío", "Emergencia Médica", "Fallo Técnico", "Amenaza de Seguridad", "Otro"});
        panelReportarIncidente.add(cmbTipoIncidente, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelReportarIncidente.add(new JLabel("ID Vuelo Afectado:"), gbc);
        gbc.gridx = 1;
        txtVueloAfectadoId = new JTextField(15);
        panelReportarIncidente.add(txtVueloAfectadoId, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelReportarIncidente.add(new JLabel("Descripción:"), gbc);
        
        gbc.gridy = 3;
        txtDescripcionIncidente = new JTextArea(5, 30);
        txtDescripcionIncidente.setLineWrap(true);
        txtDescripcionIncidente.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcionIncidente);
        panelReportarIncidente.add(scrollDescripcion, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        btnReportarIncidente = new JButton("Reportar Incidente");
        panelReportarIncidente.add(btnReportarIncidente, gbc);

        splitPane.setBottomComponent(panelReportarIncidente);

        add(splitPane, BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTablaIncidentes() {
        return (DefaultTableModel) tablaIncidentes.getModel();
    }

    public void limpiarCamposReporte() {
        cmbTipoIncidente.setSelectedIndex(0);
        txtVueloAfectadoId.setText("");
        txtDescripcionIncidente.setText("");
    }
}