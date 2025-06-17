package vista;

import javax.swing.*;
import java.awt.*;

public class VistaGestorFacturacion extends JFrame {

    public JButton btnGestionarDescuentos;
    public JButton btnFacturarAerolineas;
    public JButton btnVolver;

    public VistaGestorFacturacion() {
        setTitle("Gestión de Facturación");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Facturación", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        btnGestionarDescuentos = new JButton("Gestionar Descuentos");
        btnFacturarAerolineas = new JButton("Facturar Aerolíneas");
        centerPanel.add(btnGestionarDescuentos);
        centerPanel.add(btnFacturarAerolineas);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        btnVolver = new JButton("Volver");
        bottomPanel.add(btnVolver);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}