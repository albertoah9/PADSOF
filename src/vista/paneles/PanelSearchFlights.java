package vista.paneles;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelSearchFlights extends JPanel {
        private JLabel lblFiltrarVuelos;
        private JLabel lblAerolinea;
        private JTextField txtAerolinea;
        private JLabel lblOrigen;
        private JTextField txtOrigen;
        private JLabel lblHora;
        private JComboBox<String> comboHora;
        private JLabel lblCodigoVuelo;
        private JTextField txtCodigoVuelo;
        private JLabel lblDestino;
        private JTextField txtDestino;
        private JLabel lblTerminal;
        private JTextField txtTerminal;
        private JLabel lblSalidaLlegada;
        private JComboBox<String> comboSalidaLlegada;
        private JButton btnFiltrar;
        private JLabel lblVuelosHoy;
        private JScrollPane scrollPaneVuelos;
        private JPanel panelListaVuelos;

        public PanelSearchFlights() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.PAGE_START;

            lblFiltrarVuelos = new JLabel("Filtrar vuelos");
            lblFiltrarVuelos.setFont(new Font("SansSerif", Font.BOLD, 16));
            add(lblFiltrarVuelos, gbc);
            gbc.gridy++;
            gbc.gridwidth = 1;

            lblAerolinea = new JLabel("Aerolínea");
            gbc.gridx = 0;
            add(lblAerolinea, gbc);

            txtAerolinea = new JTextField();
        gbc.gridx = 1;
        add(txtAerolinea, gbc);

        lblOrigen = new JLabel("Origen");
        gbc.gridx = 2;
        add(lblOrigen, gbc);

        txtOrigen = new JTextField();
        gbc.gridx = 3;
        add(txtOrigen, gbc);

        lblHora = new JLabel("Hora");
        gbc.gridx = 4;
        add(lblHora, gbc);

        String[] horas = {"1 a.m.", "2 a.m.", "3 a.m.", "4 a.m.", "5 a.m.", "6 a.m.", "7 a.m.", "8 a.m.", "9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.",
                         "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.", "6 p.m.", "7 p.m.", "8 p.m.", "9 p.m.", "10 p.m.", "11 p.m.", "12 a.m."};
        comboHora = new JComboBox<>(horas);
        gbc.gridx = 5;
        add(comboHora, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        lblCodigoVuelo = new JLabel("Código de vuelo");
        add(lblCodigoVuelo, gbc);

        txtCodigoVuelo = new JTextField();
        gbc.gridx = 1;
        add(txtCodigoVuelo, gbc);

        lblDestino = new JLabel("Destino");
        gbc.gridx = 2;
        add(lblDestino, gbc);

        txtDestino = new JTextField();
        gbc.gridx = 3;
        add(txtDestino, gbc);

        lblTerminal = new JLabel("Terminal");
        gbc.gridx = 4;
        add(lblTerminal, gbc);

        txtTerminal = new JTextField();
        gbc.gridx = 5;
        add(txtTerminal, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        lblSalidaLlegada = new JLabel("Salida / Llegada");
        add(lblSalidaLlegada, gbc);

        String[] opcionesSalidaLlegada = {"Salida", "Llegada"};
        comboSalidaLlegada = new JComboBox<>(opcionesSalidaLlegada);
        gbc.gridx = 1;
        add(comboSalidaLlegada, gbc);

        gbc.gridx = 5;
        btnFiltrar = new JButton("Filtrar");
        add(btnFiltrar, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        lblVuelosHoy = new JLabel("Vuelos de hoy");
        lblVuelosHoy.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(lblVuelosHoy, gbc);

        gbc.gridy++;
        panelListaVuelos = new JPanel();
        panelListaVuelos.setLayout(new BoxLayout(panelListaVuelos, BoxLayout.Y_AXIS));
        for (int i = 0; i < 4; i++) {
            JPanel vueloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel detallesVuelo = new JLabel("Vuelo " + (i + 1) + " - Detalles");
            JButton btnModificar = new JButton("Modify");
            JButton btnVer = new JButton("View");
            vueloPanel.add(detallesVuelo);
            vueloPanel.add(Box.createHorizontalGlue());
            vueloPanel.add(btnModificar);
            vueloPanel.add(btnVer);
            panelListaVuelos.add(vueloPanel);
            panelListaVuelos.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        scrollPaneVuelos = new JScrollPane(panelListaVuelos);
        scrollPaneVuelos.setPreferredSize(new Dimension(600, 150));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        add(scrollPaneVuelos, gbc);

        setBackground(Color.WHITE);
    }

    // Métodos para listeners de los botones y combos si es necesario
    public void addFiltrarVuelosListener(ActionListener listener) {
        btnFiltrar.addActionListener(listener);
    }

    // Métodos para obtener los valores de los campos de filtro si es necesario
    public String getAerolineaFiltro() {
        return txtAerolinea.getText();
    }

    public String getOrigenFiltro() {
        return txtOrigen.getText();
    }

    public String getDestinoFiltro() {
        return txtDestino.getText();
    }

    public String getCodigoVueloFiltro() {
        return txtCodigoVuelo.getText();
    }

    public String getTerminalFiltro() {
        return txtTerminal.getText();
    }

    public String getHoraFiltro() {
        return (String) comboHora.getSelectedItem();
    }

    public String getSalidaLlegadaFiltro() {
        return (String) comboSalidaLlegada.getSelectedItem();
    }

    // Método para actualizar la lista de vuelos (dependerá de tu lógica de datos)
    public void actualizarListaVuelos(java.util.List<String> vuelos) {
        panelListaVuelos.removeAll();
        for (String vuelo : vuelos) {
            JPanel vueloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel detallesVuelo = new JLabel(vuelo);
            JButton btnModificar = new JButton("Modify");
            JButton btnVer = new JButton("View");
            vueloPanel.add(detallesVuelo);
            vueloPanel.add(Box.createHorizontalGlue());
            vueloPanel.add(btnModificar);
            vueloPanel.add(btnVer);
            panelListaVuelos.add(vueloPanel);
            panelListaVuelos.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        panelListaVuelos.revalidate();
        panelListaVuelos.repaint();
    }
}