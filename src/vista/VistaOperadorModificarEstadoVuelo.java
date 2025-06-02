package vista;

import javax.swing.*;
import java.awt.*;

public class VistaOperadorModificarEstadoVuelo extends JFrame {

    public JComboBox<String> cmbVuelos;
    public JComboBox<String> cmbEstadoNuevo;
    public JButton btnModificar;
    public JButton btnVolver;

    public VistaOperadorModificarEstadoVuelo() {
        setTitle("Modificar Estado de Vuelo");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Modificar Estado de Vuelo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(new JLabel("Seleccione Vuelo:"));
        cmbVuelos = new JComboBox<>();
        panel.add(cmbVuelos);

        panel.add(new JLabel("Nuevo Estado:"));
        cmbEstadoNuevo = new JComboBox<>(new String[]{
            "ESPERANDO_PISTA", "ESPERANDO_ATERRIZAJE", "EN_PREPARACION",
            "APARCADO", "EN_HANGAR", "EMBARCANDO", "ESPERADNO_DESPEGUE",
            "DESPEGADO", "RETRASADO", "EN_HORA"
        });
        panel.add(cmbEstadoNuevo);

        btnModificar = new JButton("Modificar Estado");
        btnVolver = new JButton("Volver");
        panel.add(btnModificar);
        panel.add(btnVolver);

        add(panel, BorderLayout.CENTER);
    }
}