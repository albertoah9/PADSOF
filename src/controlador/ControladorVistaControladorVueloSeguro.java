package controlador;

import modelo.IncidenteSeguridad;
import modelo.Vuelo;
import vista.VistaControladorVueloSeguro;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaControladorVueloSeguro {

    private VistaControladorVueloSeguro vista;
    private JFrame vistaAnterior;
    private List<IncidenteSeguridad> incidentes;

    public ControladorVistaControladorVueloSeguro(
            VistaControladorVueloSeguro vista,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.incidentes = new ArrayList<>();

        inicializar();
    }

    private void inicializar() {
        // Acción reportar incidente
        vista.btnReportar.addActionListener(e -> {
            String descripcion = vista.areaDescripcion.getText().trim();
            if (descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese descripción del incidente");
                return;
            }

            IncidenteSeguridad incidente = new IncidenteSeguridad(
                    "General", // Tipo fijo, sin selección
                    null, // No hay vuelo afectado
                    descripcion,
                    LocalDateTime.now(),
                    IncidenteSeguridad.EstadoIncidente.REPORTADO);

            incidentes.add(incidente);
            vista.listaModelIncidentes.addElement(formatIncidente(incidente));

            vista.areaDescripcion.setText("");
            JOptionPane.showMessageDialog(vista, "Incidente reportado correctamente");
        });

        // Botón volver
        vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            if (vistaAnterior != null)
                vistaAnterior.setVisible(true);
        });
    }

    private String formatIncidente(IncidenteSeguridad incidente) {
        return "ID:" + incidente.getId()
                + " - Estado:" + incidente.getEstado()
                + " - " + incidente.getDescripcion();
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    public List<IncidenteSeguridad> getIncidentes() {
        return incidentes;
    }
}
