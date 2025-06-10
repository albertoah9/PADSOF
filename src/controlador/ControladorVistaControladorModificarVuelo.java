package controlador;

import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaControladorModificarVuelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaControladorModificarVuelo {

    private VistaControladorModificarVuelo vista;
    private Aeropuerto aeropuerto;
    private JFrame vistaAnterior;

    // Para asociar cada vuelo con su JComboBox de estado
    private List<VueloEstadoPanel> vueloEstadoPanels;

    // Clase auxiliar para mapear vuelo y su combobox en la vista
    private static class VueloEstadoPanel {
        Vuelo vuelo;
        JComboBox<String> comboEstado;
        JPanel panel;

        VueloEstadoPanel(Vuelo vuelo, JComboBox<String> comboEstado, JPanel panel) {
            this.vuelo = vuelo;
            this.comboEstado = comboEstado;
            this.panel = panel;
        }
    }

    public ControladorVistaControladorModificarVuelo(VistaControladorModificarVuelo vista, Aeropuerto aeropuerto,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.vueloEstadoPanels = new ArrayList<>();

        // Configurar botones
        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        this.vista.btnActualizar.addActionListener(e -> cargarVuelos());

        this.vista.btnConfirmar.addActionListener(e -> confirmarCambios());

        // Carga inicial
        cargarVuelos();
    }

    private void cargarVuelos() {
        vista.panelVuelos.removeAll();
        vueloEstadoPanels.clear();

        List<Vuelo> vuelos = aeropuerto.getVuelos(); // Método que devuelve la lista completa de vuelos

        String[] estados = { "ESPERANDO_PISTA", "ESPERANDO_ATERRIZAJE", "EN_PREPARACION", "APARCADO", "EN_HANGAR",
                "EMBARCANDO", "ESPERANDO_DESPEGUE", "DESPEGADO", "RETRASADO", "EN_HORA" };

        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo v = vuelos.get(i);

            JPanel vueloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            vueloPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            vueloPanel.add(new JLabel("Vuelo: " + v.getId()));
            vueloPanel.add(new JLabel("Origen: " + v.getOrigen()));
            vueloPanel.add(new JLabel("Destino: " + v.getDestino()));

            JComboBox<String> comboEstado = new JComboBox<>(estados);
            comboEstado.setSelectedItem(v.getEstado().name());
            comboEstado.setPreferredSize(new Dimension(150, 25));

            vueloPanel.add(comboEstado);

            vista.panelVuelos.add(vueloPanel);
            if (i < vuelos.size() - 1) {
                vista.panelVuelos.add(new JSeparator());
            }

            vueloEstadoPanels.add(new VueloEstadoPanel(v, comboEstado, vueloPanel));
        }

        vista.panelVuelos.revalidate();
        vista.panelVuelos.repaint();
    }

    private void confirmarCambios() {
        for (VueloEstadoPanel vep : vueloEstadoPanels) {
            String estadoSeleccionado = (String) vep.comboEstado.getSelectedItem();
            if (estadoSeleccionado != null && !vep.vuelo.getEstado().name().equals(estadoSeleccionado)) {
                // Cambiar estado del vuelo
                try {
                    Vuelo.EstadoVuelo nuevoEstado = Vuelo.EstadoVuelo.valueOf(estadoSeleccionado);
                    vep.vuelo.setEstado(nuevoEstado);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vista, "Estado inválido para vuelo " + vep.vuelo.getId(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        JOptionPane.showMessageDialog(vista, "Estados de vuelos actualizados correctamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        cargarVuelos(); // Recarga para refrescar la vista
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
