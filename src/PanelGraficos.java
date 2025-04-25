import javax.swing.*;
import java.awt.*;

public class PanelGraficos extends JPanel {

    public PanelGraficos() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Panel de Gráficos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        add(titulo, BorderLayout.CENTER);
    }
}