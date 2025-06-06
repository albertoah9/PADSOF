package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaControladorBuscarVuelo extends JFrame {

    public JTable tablaVuelos;
    public JButton btnVolver;
    public JButton btnFiltrar;
    public JTextField txtFiltroOrigen;
    public JTextField txtFiltroDestino;
    public JComboBox<String> comboFiltroEstado;
    public JComboBox<String> comboFiltroClase;

    private DefaultTableModel modeloTabla;

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

        // Panel de filtros
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

        // Botón volver abajo
        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void agregarVuelo(Object[] datosFila) {
        modeloTabla.addRow(datosFila);
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}
