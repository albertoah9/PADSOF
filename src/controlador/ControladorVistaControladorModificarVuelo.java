package controlador;

import modelo.ControladorAereo;
import modelo.Terminal;
import modelo.Vuelo;
import vista.VistaControladorModificarVuelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaControladorModificarVuelo {

    private VistaControladorModificarVuelo vista;
    private ControladorAereo controladorAereo;
    private JFrame vistaAnterior;

    // Para guardar los JComboBox de estados por vuelo y relacionarlos
    private List<Vuelo> vuelos;
    private List<JComboBox<String>> combosEstados;

    public ControladorVistaControladorModificarVuelo(VistaControladorModificarVuelo vista,
            ControladorAereo controladorAereo,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.controladorAereo = controladorAereo;
        this.vistaAnterior = vistaAnterior;

        this.vuelos = new ArrayList<>();
        this.combosEstados = new ArrayList<>();

        // Botón volver
        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        // Botón actualizar lista
        this.vista.btnActualizar.addActionListener(e -> cargarVuelos());

        // Botón confirmar cambios
        this.vista.btnConfirmar.addActionListener(this::confirmarCambios);

        cargarVuelos();
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void cargarVuelos() {
        vista.panelVuelos.removeAll();
        vuelos.clear();
        combosEstados.clear();

        Terminal terminal = controladorAereo.getTerminalAsignada();
        if (terminal == null) {
            JOptionPane.showMessageDialog(vista, "No hay terminal asignada al controlador aéreo.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Vuelo> vuelosTerminal = terminal.getVuelosActivos();

        if (vuelosTerminal.isEmpty()) {
            JLabel sinVuelos = new JLabel("No hay vuelos activos.");
            sinVuelos.setFont(new Font("Arial", Font.ITALIC, 14));
            vista.panelVuelos.add(sinVuelos);
        } else {
            String[] estados = { "ESPERANDO_PISTA", "ESPERANDO_ATERRIZAJE", "EN_PREPARACION", "APARCADO", "EN_HANGAR",
                    "EMBARCANDO", "ESPERANDO_DESPEGUE", "DESPEGADO", "RETRASADO", "EN_HORA" };

            for (Vuelo vuelo : vuelosTerminal) {
                vuelos.add(vuelo);

                JPanel vueloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                vueloPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                vueloPanel.add(new JLabel("Vuelo: " + vuelo.getId()));
                vueloPanel.add(new JLabel("Origen: " + vuelo.getOrigen()));
                vueloPanel.add(new JLabel("Destino: " + vuelo.getDestino()));

                JComboBox<String> comboEstado = new JComboBox<>(estados);
                comboEstado.setSelectedItem(vuelo.getEstado().name());
                comboEstado.setPreferredSize(new Dimension(150, 25));
                vueloPanel.add(comboEstado);

                combosEstados.add(comboEstado);
                vista.panelVuelos.add(vueloPanel);
                vista.panelVuelos.add(new JSeparator());
            }
        }

        vista.panelVuelos.revalidate();
        vista.panelVuelos.repaint();
    }

    private void confirmarCambios(ActionEvent e) {
        boolean cambiosRealizados = false;

        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo vuelo = vuelos.get(i);
            JComboBox<String> comboEstado = combosEstados.get(i);
            String estadoSeleccionado = (String) comboEstado.getSelectedItem();

            if (estadoSeleccionado != null && !estadoSeleccionado.equalsIgnoreCase(vuelo.getEstado().name())) {
                try {
                    Vuelo.EstadoVuelo nuevoEstado = Vuelo.EstadoVuelo.valueOf(estadoSeleccionado);
                    controladorAereo.cambiarEstadoVuelo(vuelo, nuevoEstado);
                    cambiosRealizados = true;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vista, "Estado inválido para vuelo " + vuelo.getId(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (cambiosRealizados) {
            JOptionPane.showMessageDialog(vista, "Estados actualizados correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarVuelos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se detectaron cambios en los estados.", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
