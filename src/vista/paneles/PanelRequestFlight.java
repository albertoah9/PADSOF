package vista.paneles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelRequestFlight extends JPanel {

    private JLabel lblSolicitudesVuelo;
    private JScrollPane scrollPaneSolicitudes;
    private JPanel panelListaSolicitudes;

    public PanelRequestFlight() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);

        lblSolicitudesVuelo = new JLabel("Solicitudes de Vuelo");
        lblSolicitudesVuelo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblSolicitudesVuelo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblSolicitudesVuelo, BorderLayout.NORTH);

        panelListaSolicitudes = new JPanel();
        panelListaSolicitudes.setLayout(new BoxLayout(panelListaSolicitudes, BoxLayout.Y_AXIS));
        scrollPaneSolicitudes = new JScrollPane(panelListaSolicitudes);
        add(scrollPaneSolicitudes, BorderLayout.CENTER);

        // Simulación de solicitudes de vuelo (esto se reemplazará con datos reales)
        actualizarListaSolicitudes(List.of(
                "Usuario: Ana, Origen: MAD, Destino: BCN, Hora: 10:00",
                "Usuario: Luis, Origen: SEV, Destino: VLC, Hora: 14:30",
                "Usuario: Marta, Origen: BIO, Destino: LPA, Hora: 18:00"
        ));
    }

    public void actualizarListaSolicitudes(List<String> solicitudes) {
        panelListaSolicitudes.removeAll();
        for (String solicitud : solicitudes) {
            JPanel panelSolicitud = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblSolicitud = new JLabel(solicitud);
            JButton btnAceptar = new JButton("Aceptar");
            JButton btnAlternativas = new JButton("Alternativas");

            panelSolicitud.add(lblSolicitud);
            panelSolicitud.add(Box.createHorizontalGlue());
            panelSolicitud.add(btnAceptar);
            panelSolicitud.add(btnAlternativas);

            panelListaSolicitudes.add(panelSolicitud);
            panelListaSolicitudes.add(Box.createRigidArea(new Dimension(0, 5)));

            // Aquí podrías añadir ActionListeners a los botones para cada solicitud
            final String solicitudActual = solicitud; // Necesario para usar en lambda

            btnAceptar.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Solicitud aceptada: " + solicitudActual, "Aceptar Solicitud", JOptionPane.INFORMATION_MESSAGE);
                // Lógica para aceptar la solicitud
            });

            btnAlternativas.addActionListener(e -> {
                // Simulación de mostrar alternativas
                String alternativas = "Próximas horas disponibles:\n11:30, 13:00, 15:00";
                JOptionPane.showMessageDialog(this, alternativas, "Horarios Alternativos", JOptionPane.INFORMATION_MESSAGE);
                // Lógica para mostrar horarios alternativos
            });
        }
        panelListaSolicitudes.revalidate();
        panelListaSolicitudes.repaint();
    }

    // Métodos para añadir listeners a los botones de cada solicitud (si se gestiona desde fuera)
    // Por ejemplo:
    /*
    public void addAceptarSolicitudListener(ActionListener listener) {
        // Necesitarías una forma de identificar a qué solicitud se aplica el listener
    }

    public void addMostrarAlternativasListener(ActionListener listener) {
        // Similar al anterior
    }
    */
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PanelRequestFlight Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PanelRequestFlight());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }*/
}