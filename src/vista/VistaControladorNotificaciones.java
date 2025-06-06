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

        // Título
        lblTitulo = new JLabel("Notificaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        // Panel para las notificaciones
        panelNotificaciones = new JPanel();
        panelNotificaciones.setLayout(new BoxLayout(panelNotificaciones, BoxLayout.Y_AXIS));
        panelNotificaciones.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Inicialmente sin notificaciones
        actualizarNotificaciones();

        // Scroll pane para las notificaciones
        JScrollPane scrollPane = new JScrollPane(panelNotificaciones,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        panel.add(scrollPane);

        // Agregar panel central
        add(panel, BorderLayout.CENTER);

        // ===== NUEVO: Panel inferior con botón "Volver" =====
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public void actualizarNotificaciones() {
        panelNotificaciones.removeAll(); // Limpiar panel existente

        String[] notificaciones = new String[0]; // Lista vacía inicialmente

        if (notificaciones.length > 0) {
            for (int i = 0; i < notificaciones.length; i++) {
                JPanel notifPanel = new JPanel();
                notifPanel.setLayout(new BorderLayout());
                notifPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Texto de la notificación
                JTextArea textoNotif = new JTextArea(notificaciones[i]);
                textoNotif.setLineWrap(true);
                textoNotif.setWrapStyleWord(true);
                textoNotif.setEditable(false);
                textoNotif.setFont(new Font("Arial", Font.PLAIN, 12));
                textoNotif.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                textoNotif.setBackground(notifPanel.getBackground());
                notifPanel.add(textoNotif, BorderLayout.CENTER);

                // Panel para los botones "View more" y "Delete"
                JPanel botonesPanel = new JPanel();
                botonesPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                JButton btnViewMore = new JButton("View more");
                btnViewMore.setFont(new Font("Arial", Font.PLAIN, 12));
                btnViewMore.setPreferredSize(new Dimension(100, 30));
                JButton btnDelete = new JButton("Delete");
                btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
                btnDelete.setPreferredSize(new Dimension(100, 30));
                botonesPanel.add(btnViewMore);
                botonesPanel.add(btnDelete);
                notifPanel.add(botonesPanel, BorderLayout.EAST);

                panelNotificaciones.add(notifPanel);
                if (i < notificaciones.length - 1) {
                    panelNotificaciones.add(new JSeparator());
                }
            }
            panelNotificaciones.revalidate();
            panelNotificaciones.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaControladorNotificaciones frame = new VistaControladorNotificaciones();
            frame.setVisible(true);
            // Simulación de llegada de notificaciones después de 2 segundos (puedes quitar
            // esto)
            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    String[] nuevasNotificaciones = { "Notificación 1: Nueva alerta", "Notificación 2: Actualización" };
                    frame.actualizarNotificaciones();
                }
            }, 2000);
        });
    }
}