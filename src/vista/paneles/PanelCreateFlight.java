package vista.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelCreateFlight extends JPanel {

    private JLabel lblCrearVuelo;
    private JLabel lblAerolinea;
    private JTextField txtAerolinea;
    private JLabel lblCodigoVuelo;
    private JTextField txtCodigoVuelo;
    private JLabel lblOrigen;
    private JTextField txtOrigen;
    private JLabel lblDestino;
    private JTextField txtDestino;
    private JLabel lblFechaSalida;
    private JTextField txtFechaSalida; // Podrías usar un JDatePicker
    private JLabel lblHoraSalida;
    private JComboBox<String> comboHoraSalida;
    private JLabel lblTerminalSalida;
    private JTextField txtTerminalSalida;
    private JButton btnCrear;

    public PanelCreateFlight() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;

        lblCrearVuelo = new JLabel("Crear Nuevo Vuelo");
        lblCrearVuelo.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(lblCrearVuelo, gbc);
        gbc.gridy++;

        lblAerolinea = new JLabel("Aerolínea:");
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(lblAerolinea, gbc);

        txtAerolinea = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(txtAerolinea, gbc);
        gbc.gridy++;

        lblCodigoVuelo = new JLabel("Código de Vuelo:");
        gbc.gridx = 0;
        add(lblCodigoVuelo, gbc);

        txtCodigoVuelo = new JTextField();
        gbc.gridx = 1;
        add(txtCodigoVuelo, gbc);
        gbc.gridy++;

        lblOrigen = new JLabel("Origen:");
        gbc.gridx = 0;
        add(lblOrigen, gbc);

        txtOrigen = new JTextField();
        gbc.gridx = 1;
        add(txtOrigen, gbc);
        gbc.gridy++;

        lblDestino = new JLabel("Destino:");
        gbc.gridx = 0;
        add(lblDestino, gbc);

        txtDestino = new JTextField();
        gbc.gridx = 1;
        add(txtDestino, gbc);
        gbc.gridy++;

        lblFechaSalida = new JLabel("Fecha de Salida:");
        gbc.gridx = 0;
        add(lblFechaSalida, gbc);

        txtFechaSalida = new JTextField(); // Considerar JFormattedTextField o JDatePicker
        gbc.gridx = 1;
        add(txtFechaSalida, gbc);
        gbc.gridy++;

        lblHoraSalida = new JLabel("Hora de Salida:");
        gbc.gridx = 0;
        add(lblHoraSalida, gbc);

        String[] horas = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
                         "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        comboHoraSalida = new JComboBox<>(horas);
        gbc.gridx = 1;
        add(comboHoraSalida, gbc);
        gbc.gridy++;

        lblTerminalSalida = new JLabel("Terminal de Salida:");
        gbc.gridx = 0;
        add(lblTerminalSalida, gbc);

        txtTerminalSalida = new JTextField();
        gbc.gridx = 1;
        add(txtTerminalSalida, gbc);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCrear = new JButton("Crear Vuelo");
        add(btnCrear, gbc);

        setBackground(Color.WHITE);
    }

    // Métodos para acceder a los valores de los campos
    public String getAerolinea() {
        return txtAerolinea.getText();
    }

    public String getCodigoVuelo() {
        return txtCodigoVuelo.getText();
    }

    public String getOrigen() {
        return txtOrigen.getText();
    }

    public String getDestino() {
        return txtDestino.getText();
    }

    public String getFechaSalida() {
        return txtFechaSalida.getText();
    }

    public String getHoraSalida() {
        return (String) comboHoraSalida.getSelectedItem();
    }

    public String getTerminalSalida() {
        return txtTerminalSalida.getText();
    }

    // Método para añadir un listener al botón "Crear Vuelo"
    public void addCrearVueloListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }
}