package modelo;
import javax.swing.*;
import java.awt.*;

public class PanelVistaBase extends JPanel {

    protected GestorAeropuerto gestor; // Si necesitas el gestor en todos los paneles

    public PanelVistaBase(String titulo, GestorAeropuerto gestor) {
        this.gestor = gestor; // O guarda el tipo de usuario adecuado

        setLayout(new BorderLayout()); // O el layout que prefieras para el contenido general
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el título si usas BoxLayout.Y_AXIS

        // Podemos añadir el título en la parte superior
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);

        add(panelTitulo, BorderLayout.NORTH);

        // El resto del contenido específico de cada vista se añadiría al CENTER
        // add(crearContenidoEspecifico(), BorderLayout.CENTER);
    }

    // Las clases hijas pueden sobrescribir este método para añadir su contenido
    protected JPanel crearContenidoEspecifico() {
        JPanel contenido = new JPanel();
        // Aquí iría la lógica para crear el contenido de cada vista
        return contenido;
    }
}