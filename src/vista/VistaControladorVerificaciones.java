package vista;

import javax.swing.*;
import java.awt.*;

public class VistaControladorVerificaciones extends JFrame {
    public JTabbedPane tabbedPanePrincipal;

    public JPanel panelPermitirAterrizaje;
    public JPanel panelPermitirDespegue;

    public JPanel panelConfirmarAterrizaje;
    public JPanel panelConfirmarDespegue;

    public JButton btnVolver;

    public VistaControladorVerificaciones() {
        setTitle("Verificaciones de Vuelos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabbedPanePrincipal = new JTabbedPane();

        JPanel panelPermitir = new JPanel(new GridLayout(1, 2));
        panelPermitirAterrizaje = new JPanel();
        panelPermitirDespegue = new JPanel();
        panelPermitir.add(createScrollablePanel(panelPermitirAterrizaje, "Permitir Aterrizaje"));
        panelPermitir.add(createScrollablePanel(panelPermitirDespegue, "Permitir Despegue"));

        JPanel panelConfirmar = new JPanel(new GridLayout(1, 2));
        panelConfirmarAterrizaje = new JPanel();
        panelConfirmarDespegue = new JPanel();
        panelConfirmar.add(createScrollablePanel(panelConfirmarAterrizaje, "Confirmar Aterrizaje"));
        panelConfirmar.add(createScrollablePanel(panelConfirmarDespegue, "Confirmar Despegue"));

        tabbedPanePrincipal.addTab("Permitir", panelPermitir);
        tabbedPanePrincipal.addTab("Confirmar", panelConfirmar);

        btnVolver = new JButton("Volver");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPanePrincipal, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);
    }

    private JScrollPane createScrollablePanel(JPanel innerPanel, String titulo) {
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(titulo));
        return scrollPane;
    }
}
