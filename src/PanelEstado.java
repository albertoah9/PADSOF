import javax.swing.*;
import java.awt.*;

public class PanelEstado extends JPanel {

     private GestorAeropuerto gestor;

    public PanelEstado(GestorAeropuerto gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Vista de Estado General");
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.CENTER);
    }
}