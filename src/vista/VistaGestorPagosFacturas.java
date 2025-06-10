package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.GestorAeropuerto; // Para interactuar con el modelo

public class VistaGestorPagosFacturas extends JPanel {

    private GestorAeropuerto gestor; // Referencia al modelo

    // Componentes para la gestión de pagos y facturas
    public JTabbedPane subTabbedPane; // Para organizar sub-secciones como "Generar Factura", "Ver Facturas", "Registrar Pago"

    // Podrías tener campos de texto, botones, tablas, etc.
    public JTextField txtMontoFactura;
    public JComboBox<String> cmbAerolineaFactura;
    public JButton btnGenerarFactura;
    public JTable tablaFacturas;
    private DefaultListModel<String> listaPagosModel;
    public JList<String> listaPagos;
    public JButton btnRegistrarPago;


    public VistaGestorPagosFacturas(GestorAeropuerto gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Pagos y Facturas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Usamos un JTabbedPane interno para organizar las sub-secciones de pagos/facturas
        subTabbedPane = new JTabbedPane();
        add(subTabbedPane, BorderLayout.CENTER);

        // Pestaña: Generar Factura
        JPanel panelGenerarFactura = new JPanel(new GridBagLayout());
        panelGenerarFactura.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelGenerarFactura.add(new JLabel("Monto de la Factura:"), gbc);
        gbc.gridx = 1;
        txtMontoFactura = new JTextField(15);
        panelGenerarFactura.add(txtMontoFactura, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelGenerarFactura.add(new JLabel("Aerolínea:"), gbc);
        gbc.gridx = 1;
        cmbAerolineaFactura = new JComboBox<>(); // Se poblará desde el controlador
        panelGenerarFactura.add(cmbAerolineaFactura, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        btnGenerarFactura = new JButton("Generar Factura");
        panelGenerarFactura.add(btnGenerarFactura, gbc);

        subTabbedPane.addTab("Generar Factura", panelGenerarFactura);

        // Pestaña: Ver Facturas
        JPanel panelVerFacturas = new JPanel(new BorderLayout());
        String[] columnasFacturas = {"ID Factura", "Aerolínea", "Monto", "Fecha", "Estado"};
        tablaFacturas = new JTable(new DefaultTableModel(columnasFacturas, 0));
        panelVerFacturas.add(new JScrollPane(tablaFacturas), BorderLayout.CENTER);
        subTabbedPane.addTab("Ver Facturas", panelVerFacturas);

        // Pestaña: Registrar Pago
        JPanel panelRegistrarPago = new JPanel(new BorderLayout());
        listaPagosModel = new DefaultListModel<>();
        listaPagos = new JList<>(listaPagosModel);
        btnRegistrarPago = new JButton("Registrar Pago Seleccionado");
        
        JPanel pnlPagoBtns = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlPagoBtns.add(btnRegistrarPago);

        panelRegistrarPago.add(new JScrollPane(listaPagos), BorderLayout.CENTER);
        panelRegistrarPago.add(pnlPagoBtns, BorderLayout.SOUTH);
        subTabbedPane.addTab("Registrar Pago", panelRegistrarPago);
    }

    // Métodos para limpiar o actualizar componentes si es necesario
    public void limpiarCamposGenerarFactura() {
        txtMontoFactura.setText("");
        cmbAerolineaFactura.setSelectedIndex(-1);
    }

    public DefaultTableModel getModeloTablaFacturas() {
        return (DefaultTableModel) tablaFacturas.getModel();
    }

    public DefaultListModel<String> getListaPagosModel() {
        return listaPagosModel;
    }
}