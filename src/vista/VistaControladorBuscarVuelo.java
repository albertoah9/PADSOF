package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana que muestra todos los vuelos registrados en una tabla.
 * 
 * Permite filtrar los vuelos por origen, destino, estado o clase,
 * y también volver a la pantalla anterior.
 */
public class VistaControladorBuscarVuelo extends JFrame {

    /** Tabla donde se muestran los vuelos */
    public JTable tablaVuelos;
    /** Botón para volver atrás */
    public JButton btnVolver;
    /** Botón para aplicar los filtros */
    public JButton btnFiltrar;
    /** Campo para filtrar por origen */
    public JTextField txtFiltroOrigen;
    /** Campo para filtrar por destino */
    public JTextField txtFiltroDestino;
    /** Combo para elegir el estado del vuelo (ej: en hora, retrasado, etc.) */
    public JComboBox<String> comboFiltroEstado;
    /** Combo para elegir la clase del vuelo (pasajeros o mercancías) */
    public JComboBox<String> comboFiltroClase;
    /** Modelo de la tabla, usado para manejar los datos que se muestran */
    private DefaultTableModel modeloTabla;

    /**
     * Constructor que arma toda la interfaz gráfica para ver y filtrar vuelos.
     */
    public VistaControladorBuscarVuelo() {
        setTitle("Listado de Vuelos");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Vuelos Registrados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = { "ID", "Origen", "Destino", "Fecha Salida", "Fecha Llegada", "Tipo", "Clase", "Estado" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaVuelos = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaVuelos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        panelFiltro.add(new JLabel("Origen: "));
        txtFiltroOrigen = new JTextField(10);
        panelFiltro.add(txtFiltroOrigen);

        panelFiltro.add(new JLabel("Destino: "));
        txtFiltroDestino = new JTextField(10);
        panelFiltro.add(txtFiltroDestino);

        panelFiltro.add(new JLabel("Estado: "));
        comboFiltroEstado = new JComboBox<>(
                new String[] { "", "ESPERANDO_PISTA", "ESPERANDO_ATERRIZAJE", "EN_PREPARACION", "APARCADO", "EN_HANGAR",
                        "EMBARCANDO", "ESPERANDO_DESPEGUE", "DESPEGADO", "RETRASADO", "EN_HORA" });
        panelFiltro.add(comboFiltroEstado);

        panelFiltro.add(new JLabel("Clase: "));
        comboFiltroClase = new JComboBox<>(new String[] { "", "PASAJEROS", "MERCANCIAS" });
        panelFiltro.add(comboFiltroClase);

        btnFiltrar = new JButton("Filtrar");
        panelFiltro.add(btnFiltrar);

        add(panelFiltro, BorderLayout.NORTH);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    /**
     * Agrega una fila nueva a la tabla con los datos de un vuelo.
     *
     * @param datosFila Array con los datos del vuelo
     */
    public void agregarVuelo(Object[] datosFila) {
        modeloTabla.addRow(datosFila);
    }

    /**
     * Borra todos los vuelos que hay en la tabla.
     * 
     * Se usa cuando se quiere refrescar o limpiar los datos mostrados.
     */
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}
