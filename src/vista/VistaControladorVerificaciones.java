package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para las verificaciones de vuelos.
 * 
 * Contiene pestañas para permitir y confirmar aterrizajes y despegues,
 * con paneles separados para cada tipo de acción.
 */
public class VistaControladorVerificaciones extends JFrame {

    /** Panel principal con pestañas */
    public JTabbedPane tabbedPanePrincipal;
    /** Panel para permitir aterrizajes */
    public JPanel panelPermitirAterrizaje;
    /** Panel para permitir despegues */
    public JPanel panelPermitirDespegue;
    /** Panel para confirmar aterrizajes */
    public JPanel panelConfirmarAterrizaje;
    /** Panel para confirmar despegues */
    public JPanel panelConfirmarDespegue;
    /** Botón para volver a la pantalla anterior */

    public JButton btnVolver;

    /**
     * Constructor que configura la ventana de verificaciones,
     * creando las pestañas y paneles correspondientes.
     */
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

    /**
     * Método auxiliar para crear un JScrollPane con un JPanel interno
     * y un título en el borde.
     * 
     * @param innerPanel panel que va dentro del JScrollPane
     * @param titulo     título que aparece en el borde
     * @return JScrollPane que contiene el panel con scroll y título
     */
    private JScrollPane createScrollablePanel(JPanel innerPanel, String titulo) {
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(titulo));
        return scrollPane;
    }
}
