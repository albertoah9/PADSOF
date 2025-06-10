package vista.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelHistory extends JPanel {

    private JLabel lblHistorialFacturas;
    private JLabel lblFechaFacturas;
    private JFormattedTextField txtFechaFacturas;
    private JButton btnCalendarioFacturas;
    private JLabel lblPrecioFacturas;
    private JSlider sliderPrecioFacturas;
    private JButton btnBuscarFacturas;
    private JScrollPane scrollPaneFacturas;
    private JPanel panelListaFacturas;

    private JLabel lblHistorialPeticiones;
    private JLabel lblFechaPeticiones;
    private JFormattedTextField txtFechaPeticiones;
    private JButton btnCalendarioPeticiones;
    private JButton btnBuscarPeticiones;
    private JScrollPane scrollPanePeticiones;
    private JPanel panelListaPeticiones;

    public PanelHistory() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;

        // Historial de Facturas
        lblHistorialFacturas = new JLabel("Historial de Facturas");
        lblHistorialFacturas.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(lblHistorialFacturas, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;

        lblFechaFacturas = new JLabel("Fecha");
        add(lblFechaFacturas, gbc);
        gbc.gridx++;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtFechaFacturas = new JFormattedTextField(dtf.format(LocalDate.now()));
        txtFechaFacturas.setColumns(10);
        add(txtFechaFacturas, gbc);
        gbc.gridx++;

        /*btnCalendarioFacturas = new JButton(new ImageIcon(getClass().getResource("/calendario.png"))); // Asume que tienes un icono llamado calendario.png
        btnCalendarioFacturas.setPreferredSize(new Dimension(30, 30));
        add(btnCalendarioFacturas, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;*/

        lblPrecioFacturas = new JLabel("precio");
        add(lblPrecioFacturas, gbc);
        gbc.gridy++;

        sliderPrecioFacturas = new JSlider(0, 1000, 500); // Valores de ejemplo
        sliderPrecioFacturas.setMajorTickSpacing(200);
        sliderPrecioFacturas.setMinorTickSpacing(50);
        sliderPrecioFacturas.setPaintTicks(true);
        sliderPrecioFacturas.setPaintLabels(true);
        add(sliderPrecioFacturas, gbc);
        gbc.gridy++;

        btnBuscarFacturas = new JButton("Buscar");
        add(btnBuscarFacturas, gbc);
        gbc.gridy++;

        panelListaFacturas = new JPanel();
        panelListaFacturas.setLayout(new BoxLayout(panelListaFacturas, BoxLayout.Y_AXIS));
        // Aquí se añadirán los elementos de la lista de facturas
        for (int i = 0; i < 5; i++) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel texto = new JLabel("Factura " + (i + 1) + " - Detalles");
            JButton btnAbrir = new JButton("abrir");
            itemPanel.add(texto);
            itemPanel.add(Box.createHorizontalGlue());
            itemPanel.add(btnAbrir);
            panelListaFacturas.add(itemPanel);
            panelListaFacturas.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        scrollPaneFacturas = new JScrollPane(panelListaFacturas);
        scrollPaneFacturas.setPreferredSize(new Dimension(300, 150));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        add(scrollPaneFacturas, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0;

        // Historial de peticiones de vuelos compartidos
        lblHistorialPeticiones = new JLabel("Historial de peticiones de vuelos compartidos");
        lblHistorialPeticiones.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridwidth = 2;
        add(lblHistorialPeticiones, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;

        lblFechaPeticiones = new JLabel("Fecha");
        add(lblFechaPeticiones, gbc);
        gbc.gridx++;

        txtFechaPeticiones = new JFormattedTextField(dtf.format(LocalDate.now()));
        txtFechaPeticiones.setColumns(10);
        add(txtFechaPeticiones, gbc);
        gbc.gridx++;

        /*btnCalendarioPeticiones = new JButton(new ImageIcon(getClass().getResource("/calendario.png"))); // Asume que tienes un icono llamado calendario.png
        btnCalendarioPeticiones.setPreferredSize(new Dimension(30, 30));
        add(btnCalendarioPeticiones, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;*/

        btnBuscarPeticiones = new JButton("Buscar");
        add(btnBuscarPeticiones, gbc);
        gbc.gridy++;

        panelListaPeticiones = new JPanel();
        panelListaPeticiones.setLayout(new BoxLayout(panelListaPeticiones, BoxLayout.Y_AXIS));
        // Aquí se añadirán los elementos de la lista de peticiones
        for (int i = 0; i < 5; i++) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel texto = new JLabel("Petición " + (i + 1) + " - Detalles");
            JButton btnAbrir = new JButton("abrir");
            itemPanel.add(texto);
            itemPanel.add(Box.createHorizontalGlue());
            itemPanel.add(btnAbrir);
            panelListaPeticiones.add(itemPanel);
            panelListaPeticiones.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        scrollPanePeticiones = new JScrollPane(panelListaPeticiones);
        scrollPanePeticiones.setPreferredSize(new Dimension(300, 150));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        add(scrollPanePeticiones, gbc);

        setBackground(Color.WHITE);
    }

    // Métodos para añadir listeners a los botones y otros componentes
    public void addBuscarFacturasListener(ActionListener listener) {
        btnBuscarFacturas.addActionListener(listener);
    }

    public void addBuscarPeticionesListener(ActionListener listener) {
        btnBuscarPeticiones.addActionListener(listener);
    }

    /*public void addCalendarioFacturasListener(ActionListener listener) {
        btnCalendarioFacturas.addActionListener(listener);
    }*/

    /*public void addCalendarioPeticionesListener(ActionListener listener) {
        btnCalendarioPeticiones.addActionListener(listener);
    }*/

    public void addSliderPrecioFacturasChangeListener(ChangeListener listener) {
        sliderPrecioFacturas.addChangeListener(listener);
    }

    // Métodos para obtener los valores de los campos si es necesario
    public LocalDate getFechaFacturas() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(txtFechaFacturas.getText(), dtf);
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    public LocalDate getFechaPeticiones() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(txtFechaPeticiones.getText(), dtf);
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    public int getPrecioFacturasFiltrado() {
        return sliderPrecioFacturas.getValue();
    }

    // Método para actualizar la lista de facturas (esto dependerá de tu lógica de datos)
    public void actualizarListaFacturas(java.util.List<String> facturas) {
        panelListaFacturas.removeAll();
        for (String factura : facturas) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel texto = new JLabel(factura);
            JButton btnAbrir = new JButton("abrir");
            itemPanel.add(texto);
            itemPanel.add(Box.createHorizontalGlue());
            itemPanel.add(btnAbrir);
            panelListaFacturas.add(itemPanel);
            panelListaFacturas.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        panelListaFacturas.revalidate();
        panelListaFacturas.repaint();
    }

    // Método para actualizar la lista de peticiones (esto dependerá de tu lógica de datos)
    public void actualizarListaPeticiones(java.util.List<String> peticiones) {
        panelListaPeticiones.removeAll();
        for (String peticion : peticiones) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel texto = new JLabel(peticion);
            JButton btnAbrir = new JButton("abrir");
            itemPanel.add(texto);
            itemPanel.add(Box.createHorizontalGlue());
            itemPanel.add(btnAbrir);
            panelListaPeticiones.add(itemPanel);
            panelListaPeticiones.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        panelListaPeticiones.revalidate();
        panelListaPeticiones.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PanelHistory Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PanelHistory());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}