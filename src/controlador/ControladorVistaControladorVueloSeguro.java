package controlador;

import modelo.*;
import vista.VistaControladorVueloSeguro;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la vista de Controlador de Vuelo Seguro.
 * Gestiona la interacción entre la interfaz gráfica y la lógica de reporte de
 * incidentes de seguridad.
 * 
 * Permite reportar incidentes, mostrarlos en la lista, y manejar la navegación
 * entre ventanas.
 */
public class ControladorVistaControladorVueloSeguro {

    private VistaControladorVueloSeguro vista;
    private JFrame vistaAnterior;
    private List<IncidenteSeguridad> incidentes;

    /**
     * Crea un controlador que maneja la vista de vuelo seguro.
     *
     * @param vista         instancia de la vista VistaControladorVueloSeguro
     * @param vistaAnterior ventana anterior a la que se regresará al cerrar esta
     *                      vista
     */
    public ControladorVistaControladorVueloSeguro(
            VistaControladorVueloSeguro vista,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.incidentes = new ArrayList<>();

        inicializar();
    }

    /**
     * Inicializa los listeners de la vista para manejar eventos como
     * reporte de incidentes y navegación de la interfaz.
     */
    private void inicializar() {
        vista.btnReportar.addActionListener(_ -> {
            String descripcion = vista.areaDescripcion.getText().trim();
            if (descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese descripción del incidente");
                return;
            }

            IncidenteSeguridad incidente = new IncidenteSeguridad(
                    "General",
                    null,
                    descripcion,
                    LocalDateTime.now(),
                    IncidenteSeguridad.EstadoIncidente.REPORTADO);

            incidentes.add(incidente);
            vista.listaModelIncidentes.addElement(formatIncidente(incidente));

            vista.areaDescripcion.setText("");
            JOptionPane.showMessageDialog(vista, "Incidente reportado correctamente");
        });

        vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            if (vistaAnterior != null)
                vistaAnterior.setVisible(true);
        });
    }

    /**
     * Formatea un incidente para mostrarlo en la lista de la vista.
     *
     * @param incidente el incidente de seguridad a formatear
     * @return texto formateado que representa el incidente
     */
    private String formatIncidente(IncidenteSeguridad incidente) {
        return "ID:" + incidente.getId()
                + " - Estado:" + incidente.getEstado()
                + " - " + incidente.getDescripcion();
    }

    /**
     * Muestra la vista de vuelo seguro.
     */
    public void iniciar() {
        vista.setVisible(true);
    }

    /**
     * Obtiene la lista de incidentes reportados durante la sesión.
     *
     * @return lista de incidentes de seguridad
     */
    public List<IncidenteSeguridad> getIncidentes() {
        return incidentes;
    }
}
