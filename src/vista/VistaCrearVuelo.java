package vista;

import javax.swing.*;
import java.awt.*;

public class VistaCrearVuelo extends JFrame {

    public JTextField txtOrigen;
    public JTextField txtDestino;
    public JComboBox<String> cmbTipoVuelo;
    public JComboBox<String> cmbClaseVuelo;
    public JTextField txtFechaLlegada;
    public JTextField txtFechaSalida;
    public JComboBox<String> cmbPeriodicidad;
    public JCheckBox chkUsaFinger;
    public JButton btnCrear;
    public JButton btnCancelar;

    public VistaCrearVuelo() {
        setTitle("Crear Nuevo Vuelo");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Origen:"), gbc);
        gbc.gridx = 1;
        txtOrigen = new JTextField();
        panel.add(txtOrigen, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Destino:"), gbc);
        gbc.gridx = 1;
        txtDestino = new JTextField();
        panel.add(txtDestino, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Tipo de Vuelo:"), gbc);
        gbc.gridx = 1;
        cmbTipoVuelo = new JComboBox<>(new String[]{"SALIDA", "LLEGADA"});
        panel.add(cmbTipoVuelo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Clase de Vuelo:"), gbc);
        gbc.gridx = 1;
        cmbClaseVuelo = new JComboBox<>(new String[]{"PASAJEROS", "MERCANCIAS"});
        panel.add(cmbClaseVuelo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Fecha y Hora de Llegada (AAAA-MM-DDTHH:MM):"), gbc);
        gbc.gridx = 1;
        txtFechaLlegada = new JTextField();
        panel.add(txtFechaLlegada, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Fecha y Hora de Salida (AAAA-MM-DDTHH:MM):"), gbc);
        gbc.gridx = 1;
        txtFechaSalida = new JTextField();
        panel.add(txtFechaSalida, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Periodicidad:"), gbc);
        gbc.gridx = 1;
        cmbPeriodicidad = new JComboBox<>(new String[]{"PUNTUAL", "DIARIO", "DIAS_SEMANA"});
        panel.add(cmbPeriodicidad, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("¿Usa Finger?:"), gbc);
        gbc.gridx = 1;
        chkUsaFinger = new JCheckBox();
        panel.add(chkUsaFinger, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        btnCrear = new JButton("Crear Vuelo");
        panel.add(btnCrear, gbc);
        gbc.gridx = 1;
        btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar, gbc);

        add(panel, BorderLayout.CENTER);
    }
}