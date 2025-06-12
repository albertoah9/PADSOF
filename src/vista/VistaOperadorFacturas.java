package vista;

import javax.swing.*;
import java.awt.*;

public class VistaOperadorFacturas extends JFrame {

    public JButton btnVerFacturas;
    public JButton btnPagarFacturas;
    public JButton btnVolver;
    public JLabel lblTitulo;

    public VistaOperadorFacturas() {
        setTitle("Gestión de Facturas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        lblTitulo = new JLabel("Panel de Facturas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        btnVerFacturas = crearBoton("Ver Facturas");
        panel.add(btnVerFacturas);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnPagarFacturas = crearBoton("Pagar Facturas");
        panel.add(btnPagarFacturas);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnVolver = crearBoton("Volver");
        panel.add(btnVolver);

        add(panel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }
}