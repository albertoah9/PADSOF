package vista;

import java.awt.*;
import javax.swing.*;

public class VistaOperadorCrearVuelo extends JFrame {

    public JComboBox<String> cmbTipoVuelo;
    public JComboBox<String> cmbAeropuertoOtro;
    public JLabel lblAeropuertoOtro;
    public JComboBox<String> cmbClaseVuelo;
    public JTextField txtFecha;
    public JTextField txtHora;
    public JComboBox<String> cmbPeriodicidad;
    public JCheckBox chkUsaFinger;
    public JComboBox<String> cmbAvion;
    public JButton btnCrear;
    public JButton btnCancelar;

    public VistaOperadorCrearVuelo() {
        setTitle("Crear Nuevo Vuelo");
        setSize(520, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));  // Margen exterior

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Tipo de Vuelo:"), gbc);
        gbc.gridx = 1;
        cmbTipoVuelo = new JComboBox<>(new String[]{"SALIDA", "LLEGADA"});
        panel.add(cmbTipoVuelo, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        lblAeropuertoOtro = new JLabel("Aeropuerto de Llegada/Salida:");
        panel.add(lblAeropuertoOtro, gbc);
        gbc.gridx = 1;
        cmbAeropuertoOtro = new JComboBox<>();
        panel.add(cmbAeropuertoOtro, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Clase de Vuelo:"), gbc);
        gbc.gridx = 1;
        cmbClaseVuelo = new JComboBox<>(new String[]{"PASAJEROS", "MERCANCIAS"});
        panel.add(cmbClaseVuelo, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Fecha (AAAA-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtFecha = new JTextField();
        panel.add(txtFecha, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Hora (HH:MM):"), gbc);
        gbc.gridx = 1;
        txtHora = new JTextField();
        panel.add(txtHora, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Periodicidad:"), gbc);
        gbc.gridx = 1;
        cmbPeriodicidad = new JComboBox<>(new String[]{"PUNTUAL", "DIARIO", "DIAS_SEMANA"});
        panel.add(cmbPeriodicidad, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("¿Usa Finger?:"), gbc);
        gbc.gridx = 1;
        chkUsaFinger = new JCheckBox();
        panel.add(chkUsaFinger, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Seleccione Avión:"), gbc);
        gbc.gridx = 1;
        cmbAvion = new JComboBox<>();
        panel.add(cmbAvion, gbc);

        y++;
        gbc.gridy = y;
        gbc.gridx = 0;
        btnCrear = new JButton("Crear Vuelo");
        panel.add(btnCrear, gbc);
        gbc.gridx = 1;
        btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar, gbc);

        add(panel, BorderLayout.CENTER);
    }
}