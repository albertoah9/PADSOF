package controlador;

import modelo.*;
import vista.VistaControladorModificarVuelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la vista de modificación de vuelos.
 * 
 * Permite mostrar una lista de vuelos del aeropuerto, cambiar su estado
 * mediante un JComboBox,
 * y confirmar los cambios aplicados, generando notificaciones para el usuario
 * activo.
 */
public class ControladorVistaControladorModificarVuelo {

    private VistaControladorModificarVuelo vista;
    private Aeropuerto aeropuerto;
    private JFrame vistaAnterior;

    private List<VueloEstadoPanel> vueloEstadoPanels;

    /**
     * Clase interna que agrupa un vuelo con su JComboBox de estado y el panel que
     * lo contiene.
     */
    private static class VueloEstadoPanel {
        Vuelo vuelo;
        JComboBox<String> comboEstado;
        JPanel panel;

        /**
         * Constructor para asociar un vuelo con su selector de estado y panel visual.
         * 
         * @param vuelo       Vuelo al que se asocia el panel
         * @param comboEstado JComboBox con los estados posibles
         * @param panel       JPanel que contiene la UI del vuelo
         */
        VueloEstadoPanel(Vuelo vuelo, JComboBox<String> comboEstado, JPanel panel) {
            this.vuelo = vuelo;
            this.comboEstado = comboEstado;
            this.panel = panel;
        }
    }

    /**
     * Constructor del controlador.
     * 
     * @param vista         Vista para modificar vuelos
     * @param aeropuerto    Aeropuerto con los vuelos a gestionar
     * @param vistaAnterior Vista anterior para regresar
     */
    public ControladorVistaControladorModificarVuelo(VistaControladorModificarVuelo vista, Aeropuerto aeropuerto,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.vueloEstadoPanels = new ArrayList<>();

        this.vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        this.vista.btnActualizar.addActionListener(_ -> cargarVuelos());

        this.vista.btnConfirmar.addActionListener(_ -> confirmarCambios());

        cargarVuelos();
    }

    /**
     * Carga los vuelos desde el aeropuerto en la vista,
     * mostrando cada vuelo con su estado actual editable.
     */
    private void cargarVuelos() {
        vista.panelVuelos.removeAll();
        vueloEstadoPanels.clear();

        List<Vuelo> vuelos = aeropuerto.getVuelos();

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

    /**
     * Aplica los cambios en los estados de los vuelos según las selecciones del
     * usuario.
     * Si algún estado cambia, actualiza el vuelo y genera una notificación para el
     * usuario activo.
     * También informa al usuario mediante un mensaje.
     */
    private void confirmarCambios() {
        boolean huboCambio = false;

        for (VueloEstadoPanel vep : vueloEstadoPanels) {
            String estadoSeleccionado = (String) vep.comboEstado.getSelectedItem();
            if (estadoSeleccionado != null && !vep.vuelo.getEstado().name().equals(estadoSeleccionado)) {
                try {
                    Vuelo.EstadoVuelo nuevoEstado = Vuelo.EstadoVuelo.valueOf(estadoSeleccionado);
                    vep.vuelo.setEstado(nuevoEstado);
                    huboCambio = true;

                    String mensaje = "El estado del vuelo " + vep.vuelo.getId() + " ha cambiado a " + estadoSeleccionado
                            + ".";
                    new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vista, "Estado inválido para vuelo " + vep.vuelo.getId(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (huboCambio) {
            JOptionPane.showMessageDialog(vista, "Estados de vuelos actualizados correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarVuelos();
        }
    }

    /**
     * Muestra la ventana de modificación de vuelos.
     */
    public void iniciar() {
        vista.setVisible(true);
    }
}
