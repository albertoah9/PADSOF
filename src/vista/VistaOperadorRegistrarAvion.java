package vista;

import java.awt.*;
import javax.swing.*;

public class VistaOperadorRegistrarAvion extends JFrame {

    public JComboBox<String> cmbTipoAvion;
    public JTextField txtMarca;
    public JTextField txtModelo;
    public JTextField txtMatricula;
    public JTextField txtAutonomia;
    public JTextField txtUltimaRevision;
    public JTextField txtAnyoCompra;

    public JTextField txtCapacidad;
    public JTextField txtCargaMax;
    public JCheckBox chkMercPeligrosas;
    public JCheckBox chkControlTemperatura;

    public JButton btnRegistrar;
    public JButton btnCancelar;

    public JPanel panelPasajeros;
    public JPanel panelCarga;

    public VistaOperadorRegistrarAvion() {
        setTitle("Registrar Avión");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Tipo de Avión:"), gbc);
        gbc.gridx = 1;
        cmbTipoAvion = new JComboBox<>(new String[]{"PASAJEROS", "CARGA"});
        panel.add(cmbTipoAvion, gbc);

        gbc.gridy++; gbc.gridx = 0;
        panel.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        txtMarca = new JTextField();
        panel.add(txtMarca, gbc);

        gbc.gridy++; gbc.gridx = 0;
        panel.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        txtModelo = new JTextField();
        panel.add(txtModelo, gbc);

        gbc.gridy++; gbc.gridx = 0;
        panel.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        txtMatricula = new JTextField();
        panel.add(txtMatricula, gbc);

        gbc.gridy++; gbc.gridx = 0;
        panel.add(new JLabel("Autonomía (km):"), gbc);
        gbc.gridx = 1;
        txtAutonomia = new JTextField();
        panel.add(txtAutonomia, gbc);

        gbc.gridy++; gbc.gridx = 0;
        panel.add(new JLabel("Última Revisión (AAAA-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtUltimaRevision = new JTextField();
        panel.add(txtUltimaRevision, gbc);

        gbc.gridy++; gbc.gridx = 0;
        panel.add(new JLabel("Año de Compra (AAAA-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtAnyoCompra = new JTextField();
        panel.add(txtAnyoCompra, gbc);

        // Panel para PASAJEROS
        gbc.gridy++; gbc.gridx = 0;
        panelPasajeros = new JPanel(new GridLayout(1, 2, 10, 5));
        panelPasajeros.add(new JLabel("Capacidad:"));
        txtCapacidad = new JTextField();
        panelPasajeros.add(txtCapacidad);
        gbc.gridwidth = 2;
        panel.add(panelPasajeros, gbc);

        // Panel para CARGA
        gbc.gridy++;
        panelCarga = new JPanel(new GridLayout(3, 2, 10, 5));
        panelCarga.add(new JLabel("Carga Máxima (kg):"));
        txtCargaMax = new JTextField();
        panelCarga.add(txtCargaMax);
        panelCarga.add(new JLabel("Mercancías Peligrosas:"));
        chkMercPeligrosas = new JCheckBox();
        panelCarga.add(chkMercPeligrosas);
        panelCarga.add(new JLabel("Control de Temperatura:"));
        chkControlTemperatura = new JCheckBox();
        panelCarga.add(chkControlTemperatura);
        panel.add(panelCarga, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        btnRegistrar = new JButton("Registrar");
        panel.add(btnRegistrar, gbc);
        gbc.gridx = 1;
        btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar, gbc);

        add(panel, BorderLayout.CENTER);
    }
}