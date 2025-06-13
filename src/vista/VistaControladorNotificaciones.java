package vista;

import java.awt.*;
import javax.swing.*;

public class VistaControladorNotificaciones extends JFrame {
    public JLabel lblTitulo;
    private JPanel panelNotificaciones;
    public JButton btnVolver;

    public VistaControladorNotificaciones() {
        setTitle("Notificaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("Notificaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        panelNotificaciones = new JPanel();
        panelNotificaciones.setLayout(new BoxLayout(panelNotificaciones, BoxLayout.Y_AXIS));
        panelNotificaciones.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        JScrollPane scrollPane = new JScrollPane(panelNotificaciones,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void actualizarNotificaciones(String[] notificaciones) {
        panelNotificaciones.removeAll();

        if (notificaciones.length == 0) {
            JLabel noNotif = new JLabel("No hay notificaciones.");
            noNotif.setFont(new Font("Arial", Font.ITALIC, 14));
            noNotif.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelNotificaciones.add(noNotif);
        } else {
            for (int i = 0; i < notificaciones.length; i++) {
                JPanel notifPanel = new JPanel(new BorderLayout());
                notifPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JTextArea textoNotif = new JTextArea(notificaciones[i]);
                textoNotif.setLineWrap(true);
                textoNotif.setWrapStyleWord(true);
                textoNotif.setEditable(false);
                textoNotif.setFont(new Font("Arial", Font.PLAIN, 12));
                textoNotif.setBackground(notifPanel.getBackground());
                textoNotif.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                notifPanel.add(textoNotif, BorderLayout.CENTER);

                panelNotificaciones.add(notifPanel);

                if (i < notificaciones.length - 1) {
                    panelNotificaciones.add(new JSeparator());
                }
            }
        }

        panelNotificaciones.revalidate();
        panelNotificaciones.repaint();
    }
}
