package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class VistaControladorBuscarVuelo extends JFrame {
    public JTextField txtAerolinea;
    public JTextField txtOrigen;
    public JSpinner spinnerHora;
    public JTextField txtCodigoVuelo;
    public JTextField txtDestino;
    public JTextField txtTerminal;
    public JComboBox<String> comboTipoVuelo;
    public JButton btnBuscar;

    public JTable tablaVuelos;
    public DefaultTableModel modeloTabla;

    public VistaControladorBuscarVuelo() {
        // Configura la pantalla
        setTitle("Buscar Vuelo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));

        // se añade un campo
        panelFormulario.add(crearCampo("Aerolínea:", txtAerolinea = new JTextField(20)));
        // se añade un campo
        panelFormulario.add(crearCampo("Origen:", txtOrigen = new JTextField(20)));
        // se añade un campo de hora con JSpinner
        spinnerHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorHora = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(editorHora);
        panelFormulario.add(crearCampo("Hora:", spinnerHora));
        // se añade un campo
        panelFormulario.add(crearCampo("Código de vuelo:", txtCodigoVuelo = new JTextField(20)));
        // se añade un campo
        panelFormulario.add(crearCampo("Destino:", txtDestino = new JTextField(20)));
        // se añade un campo
        panelFormulario.add(crearCampo("Terminal:", txtTerminal = new JTextField(20)));
        // combobox para el tipo de vuelo
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTipoVuelo = new JLabel("Tipo de vuelo:");
        comboTipoVuelo = new JComboBox<>(new String[] { "Salida", "Llegada" });
        comboTipoVuelo.setPreferredSize(new Dimension(150, 25));
        panelCombo.add(lblTipoVuelo);
        panelCombo.add(comboTipoVuelo);
        panelFormulario.add(panelCombo);
        panelFormulario.add(new JLabel());
        // boton para filtrar
        btnBuscar = new JButton("Filtrar");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.setPreferredSize(new Dimension(150, 35));
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnBuscar);
        add(btnPanel, BorderLayout.SOUTH);

        add(panelFormulario, BorderLayout.NORTH);

        // tabla de resultados
        String[] columnas = { "Código", "Aerolínea", "Origen", "Destino", "Hora", "Terminal", "Tipo" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaVuelos = new JTable(modeloTabla);
        tablaVuelos.setFillsViewportHeight(true);
        tablaVuelos.setRowHeight(30);

        // scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaVuelos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Vuelos de hoy"));
        scrollPane.setPreferredSize(new Dimension(800, 500));
        add(scrollPane, BorderLayout.CENTER);
    }

    // metodo reutilizable
    private JPanel crearCampo(String labelTexto, JComponent campo) {
        JPanel panelCampo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelTexto);
        label.setPreferredSize(new Dimension(100, 25));
        campo.setPreferredSize(new Dimension(250, 25));
        panelCampo.add(label);
        panelCampo.add(campo);
        return panelCampo;
    }

}