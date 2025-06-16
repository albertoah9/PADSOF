package vista;

import modelo.Vuelo; // Importa la clase Vuelo para poder procesar la lista
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VistaGestorBuscarVuelos extends JDialog { // JDialog es adecuado para ventanas modales de búsqueda

    private JTextField txtAerolinea;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JTextField txtCodigoVuelo;
    private JTextField txtTerminal;
    private JTextField txtFecha; // Formato AAAA-MM-DD
    private JTextField txtHora;   // Formato HH:MM
    private JComboBox<String> cbTipoVuelo;

    public JButton btnBuscar;
    public JButton btnVolver;

    private JTable tablaVuelos;
    private DefaultTableModel tableModel;

    private static final String[] COLUMN_NAMES = {
        "ID Vuelo", "Aerolínea", "Tipo", "Origen", "Destino",
        "Salida Prevista", "Llegada Prevista", "Estado", "Terminal", "Pista"
    };

    public VistaGestorBuscarVuelos(JFrame parent) {
        super(parent, "Buscar Vuelos", true); // true para que sea modal
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent); // Centrar respecto a la ventana padre

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout()); // Layout principal de la ventana

        // Panel de Filtros
        JPanel panelFiltros = new JPanel(new GridLayout(4, 4, 10, 10)); // Más filas y columnas para organizar
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Criterios de Búsqueda"));

        txtAerolinea = new JTextField(15);
        txtOrigen = new JTextField(15);
        txtDestino = new JTextField(15);
        txtCodigoVuelo = new JTextField(15);
        txtTerminal = new JTextField(15);
        txtFecha = new JTextField(15);
        txtHora = new JTextField(15);
        cbTipoVuelo = new JComboBox<>(new String[]{"TODOS", "SALIDA", "LLEGADA"});

        panelFiltros.add(new JLabel("Aerolínea:"));
        panelFiltros.add(txtAerolinea);
        panelFiltros.add(new JLabel("Origen:"));
        panelFiltros.add(txtOrigen);
        panelFiltros.add(new JLabel("Destino:"));
        panelFiltros.add(txtDestino);
        panelFiltros.add(new JLabel("ID Vuelo:"));
        panelFiltros.add(txtCodigoVuelo);
        panelFiltros.add(new JLabel("Terminal (ID):"));
        panelFiltros.add(txtTerminal);
        panelFiltros.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panelFiltros.add(txtFecha);
        panelFiltros.add(new JLabel("Hora (HH:MM):"));
        panelFiltros.add(txtHora);
        panelFiltros.add(new JLabel("Tipo de Vuelo:"));
        panelFiltros.add(cbTipoVuelo);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnBuscar = new JButton("Buscar Vuelos");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnBuscar);
        panelBotones.add(btnVolver);

        // Tabla de Resultados
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir edición de celdas
            }
        };
        tablaVuelos = new JTable(tableModel);
        tablaVuelos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo una fila seleccionable
        JScrollPane scrollPane = new JScrollPane(tablaVuelos);

        // Añadir paneles a la ventana
        add(panelFiltros, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // --- Métodos Getters para los campos de filtro ---
    public String getAerolineaFiltro() {
        return txtAerolinea.getText().trim();
    }

    public String getOrigenFiltro() {
        return txtOrigen.getText().trim();
    }

    public String getDestinoFiltro() {
        return txtDestino.getText().trim();
    }

    public String getCodigoVueloFiltro() {
        return txtCodigoVuelo.getText().trim();
    }

    public String getTerminalFiltro() {
        return txtTerminal.getText().trim();
    }

    public String getFechaFiltro() {
        return txtFecha.getText().trim();
    }

    public String getHoraFiltro() {
        return txtHora.getText().trim();
    }

    public String getSalidaLlegadaFiltro() {
        return (String) cbTipoVuelo.getSelectedItem();
    }

    /**
     * Actualiza la tabla de vuelos con los resultados filtrados.
     * @param vuelosFiltrados La lista de objetos Vuelo a mostrar.
     */
    public void actualizarTablaVuelos(List<Vuelo> vuelosFiltrados) {
        tableModel.setRowCount(0); // Limpiar la tabla

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Vuelo vuelo : vuelosFiltrados) {
            Object[] rowData = {
                vuelo.getId(),
                (vuelo.getAerolinea() != null ? vuelo.getAerolinea().getNombre() : "N/A"),
                (vuelo.getTipoVuelo() != null ? vuelo.getTipoVuelo().name() : "N/A"),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                (vuelo.getfechaHoraSalida() != null ? vuelo.getfechaHoraSalida().format(formatter) : "N/A"),
                (vuelo.getfechaHoraLlegada() != null ? vuelo.getFechaHora().format(formatter) : "N/A"),
                (vuelo.getEstado() != null ? vuelo.getEstado().name() : "N/A"),
                (vuelo.getTerminal() != null ? vuelo.getTerminal().getId() : "N/A"), // Asumiendo que Terminal tiene getId()
                (vuelo.getPista() != null ? vuelo.getPista().getId() : "N/A") // Asumiendo que Pista tiene getId()
            };
            tableModel.addRow(rowData);
        }
    }

    /**
     * Limpia la tabla de resultados.
     */
    public void limpiarTabla() {
        tableModel.setRowCount(0);
    }
}