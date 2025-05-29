// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class VentanaOperador extends VentanaBase {
    public VentanaOperador(OperadorAereo operador) {
        super("Ventana Operador - " + operador.getNombre());

        inicializarMenuOperador(operador);
    }

    private void inicializarMenuOperador(OperadorAereo operador) {
        JButton btnRegistrarVuelo = new JButton("Registrar Vuelo");
        JButton btnConsultarVuelos = new JButton("Consultar Vuelos");
        JButton btnEditarVuelo = new JButton("Editar Vuelo");
        JButton btnCancelarVuelo = new JButton("Cancelar Vuelo");

        // Añadir botones al panel lateral
        panelLateral.add(btnRegistrarVuelo);
        panelLateral.add(btnConsultarVuelos);
        panelLateral.add(btnEditarVuelo);
        panelLateral.add(btnCancelarVuelo);

        // Añadir listeners que actualicen el panelContenido
        btnRegistrarVuelo.addActionListener(e -> mostrarPanelOperacion("Registrar", operador));
        btnConsultarVuelos.addActionListener(e -> mostrarPanelOperacion("Consultar", operador));
        btnEditarVuelo.addActionListener(e -> mostrarPanelOperacion("Editar", operador));
        btnCancelarVuelo.addActionListener(e -> mostrarPanelOperacion("Cancelar", operador));
    }

    private void mostrarPanelOperacion(String operacion, OperadorAereo operador) {
        panelContenido.removeAll();

        switch (operacion) {
            case "Registrar":
                panelContenido.add(new FormularioRegistroVuelo(null, operador));
                break;
            // Otros casos: Consultar, Editar, Cancelar...
            default:
                panelContenido.add(new JLabel("Operación no implementada aún"));
        }

        revalidate();
        repaint();
    }
}



