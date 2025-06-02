package vista.paneles;

import javax.swing.*;
import java.awt.*;

public class PanelFlightSafety extends JPanel {
    public PanelFlightSafety() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.WHITE);
        add(new JLabel("Contenido de Seguridad de Vuelos"));
    }
}