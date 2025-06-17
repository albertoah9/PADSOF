package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana para buscar elementos disponibles en el aeropuerto,
 * como pistas, hangares, fingers, etc.
 * 
 * Se pueden aplicar filtros por ID, tipo o si están disponibles.
 */
public class VistaControladorDisponibilidad extends JFrame {

    /** Tabla que muestra los elementos disponibles */
    public JTable tablaElementos;
    /** Campo para filtrar por ID del elemento */
    public JTextField txtFiltroID;
    /** Campo para filtrar por tipo (pista, hangar, etc.) */
    public JTextField txtFiltroTipo;
    /** Campo para filtrar si está libre u ocupado */
    public JTextField txtFiltroDisponibilidad;
    /** Botón para aplicar los filtros */
    public JButton btnFiltrar;
    /** Botón para volver atrás */
    public JButton btnVolver;
    /** Modelo de la tabla que contiene los datos mostrados */
    private DefaultTableModel modeloTabla;

    /**
     * Constructor que arma la ventana con los filtros y la tabla de resultados.
     */
    public VistaControladorDisponibilidad() {
        setTitle("Buscar elementos disponibles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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

        String[] columnas = { "ID", "Tipo", "Disponibilidad" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaElementos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaElementos);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Limpia todos los datos que hay en la tabla.
     * 
     * Se puede usar antes de aplicar un nuevo filtro.
     */
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    /**
     * Agrega un nuevo elemento (como pista o hangar) a la tabla.
     *
     * @param fila Array con los datos de un elemento.
     */
    public void agregarElemento(Object[] fila) {
        modeloTabla.addRow(fila);
    }
}
