package controlador;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import modelo.*;
import vista.VistaControladorVerificaciones;

/**
 * Controlador que gestiona la lógica y eventos para la vista de verificaciones
 * de vuelos.
 * 
 * Este controlador administra la interacción con los paneles de permitir y
 * confirmar
 * aterrizajes y despegues, actualizando el estado de los vuelos según las
 * acciones del usuario.
 * También maneja la navegación entre vistas.
 */
public class ControladorVistaControladorVerificaciones {
    private VistaControladorVerificaciones vista;
    private Aeropuerto aeropuerto;
    private JFrame vistaAnterior;

    /**
     * Constructor que inicializa el controlador con la vista, el aeropuerto y la
     * vista anterior para navegación.
     * 
     * @param vista         instancia de la vista de verificaciones de vuelos
     * @param aeropuerto    aeropuerto que contiene los vuelos a gestionar
     * @param vistaAnterior ventana desde la que se accedió a esta vista (para
     *                      regresar)
     */
    public ControladorVistaControladorVerificaciones(VistaControladorVerificaciones vista,
            Aeropuerto aeropuerto, JFrame vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.aeropuerto = aeropuerto;
        inicializar();
    }

    /**
     * Inicializa los componentes de la vista, carga los paneles con los vuelos
     * según su estado,
     * y define la acción para el botón "Volver".
     */
    private void inicializar() {
        vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            if (vistaAnterior != null) {
                vistaAnterior.setVisible(true);
            }
        });

        cargarPanelPermitirAterrizaje();
        cargarPanelPermitirDespegue();
        cargarPanelConfirmarAterrizaje();
        cargarPanelConfirmarDespegue();
    }

    /**
     * Carga el panel de vuelos que están esperando pista para permitir el
     * aterrizaje,
     * agregando checkboxes con acciones para cambiar su estado si el permiso es
     * concedido.
     */
    private void cargarPanelPermitirAterrizaje() {
        JPanel panel = vista.panelPermitirAterrizaje;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_PISTA)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Está seguro de permitir el aterrizaje?",
                        "Confirmar permiso", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Carga el panel de vuelos que están esperando permiso para despegar,
     * agregando checkboxes con acciones para actualizar el estado si se concede el
     * permiso.
     */
    private void cargarPanelPermitirDespegue() {
        JPanel panel = vista.panelPermitirDespegue;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_DESPEGUE)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Está seguro de permitir el despegue?",
                        "Confirmar permiso", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.DESPEGADO);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Carga el panel de vuelos que están esperando confirmar el aterrizaje,
     * agregando checkboxes con acciones para actualizar el estado al confirmarlo.
     */
    private void cargarPanelConfirmarAterrizaje() {
        JPanel panel = vista.panelConfirmarAterrizaje;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Confirmar que el vuelo ha aterrizado?",
                        "Confirmar aterrizaje", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.APARCADO);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Carga el panel de vuelos para confirmar que han salido correctamente,
     * agregando checkboxes que permiten cambiar el estado al confirmar la salida.
     */
    private void cargarPanelConfirmarDespegue() {
        JPanel panel = vista.panelConfirmarDespegue;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.DESPEGADO)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Confirmar que el vuelo ha salido correctamente?",
                        "Confirmar despegue", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.EN_HORA);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Muestra la vista de verificaciones.
     */
    public void iniciar() {
        vista.setVisible(true);
    }

    /**
     * Establece la ventana anterior para la navegación al regresar.
     * 
     * @param vistaAnterior la ventana previa a esta vista
     */
    public void setVistaAnterior(JFrame vistaAnterior) {
        this.vistaAnterior = vistaAnterior;
    }
}
