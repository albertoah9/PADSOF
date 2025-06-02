package vista;

import java.awt.*;
import javax.swing.*;

public class VistaGestorOverview extends JFrame {
    private JButton btnOverview;
    private JPanel subMenuOverview;

    public VistaGestorOverview() {
        // ----- BOTÓN OVERVIEW -----
        btnOverview = new JButton("Overview");
        btnOverview.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOverview.addActionListener(e -> toggleSubMenu(subMenuOverview));

        subMenuOverview = new JPanel();
        subMenuOverview.setLayout(new BoxLayout(subMenuOverview, BoxLayout.Y_AXIS));
        subMenuOverview.setVisible(false);

        JButton btnGraficos = new JButton("Gráficos");
        btnGraficos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGraficos.addActionListener(e -> mostrarVista(new PanelGraficos(gestor)));

        JPanel subMenuOrdenPago = new JPanel();
        subMenuOrdenPago.setLayout(new BoxLayout(subMenuOrdenPago, BoxLayout.Y_AXIS));
        subMenuOrdenPago.setVisible(false);

        JButton btnPagoFact = new JButton("Pagos y facuras");
        btnPagoFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPagoFact.addActionListener(e -> toggleSubMenu(subMenuOrdenPago));
    }
    
}
