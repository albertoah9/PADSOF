package controlador;

import vista.*;
import modelo.GestorAeropuerto;
import modelo.IncidenteSeguridad; // Necesitarás una clase IncidenteSeguridad
import modelo.Vuelo; // Para referenciar vuelos

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.UUID; // Para IDs de incidentes

public class ControladorVistaGestorSeguridadVuelo {

    private VistaGestorSeguridadVuelo vista;
    private GestorAeropuerto gestor;

    public ControladorVistaGestorSeguridadVuelo(VistaGestorSeguridadVuelo vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        // Cargar incidentes existentes al iniciar
        cargarIncidentesEnTabla();

        // ActionListeners para los botones
        this.vista.btnReportarIncidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportarNuevoIncidente();
            }
        });

        this.vista.btnVerDetallesIncidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetallesIncidenteSeleccionado();
            }
        });
        
        gestor.registrarEvento("CONTROLADOR", "Controlador de VistaGestorSeguridadVuelo inicializado.");
    }

    private void reportarNuevoIncidente() {
        String tipo = (String) vista.cmbTipoIncidente.getSelectedItem();
        String vueloId = vista.txtVueloAfectadoId.getText().trim();
        String descripcion = vista.txtDescripcionIncidente.getText().trim();

        if (tipo == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, selecciona un tipo y escribe una descripción del incidente.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vuelo vueloAfectado = null;
        if (!vueloId.isEmpty()) {
            vueloAfectado = gestor.buscarVuelo(vueloAfectado.getId()); // Necesitas este método en GestorAeropuerto
            if (vueloAfectado == null) {
                JOptionPane.showMessageDialog(vista, "El ID de vuelo afectado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        try {
            IncidenteSeguridad nuevoIncidente = new IncidenteSeguridad(
                UUID.randomUUID().toString().substring(0, 8),
                vueloAfectado,
                descripcion,
                LocalDateTime.now(),
                IncidenteSeguridad.EstadoIncidente.REPORTADO
            );
            gestor.addIncidenteSeguridad(nuevoIncidente);

            JOptionPane.showMessageDialog(vista, "Incidente reportado con éxito: " + nuevoIncidente.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCamposReporte();
            cargarIncidentesEnTabla(); // Refrescar la tabla
            gestor.registrarEvento("SEGURIDAD", "Incidente de seguridad reportado: " + tipo + " (Vuelo: " + (vueloId.isEmpty() ? "N/A" : vueloId) + ")");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al reportar incidente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarIncidentesEnTabla() {
        DefaultTableModel modelo = vista.getModeloTablaIncidentes();
        modelo.setRowCount(0); // Limpiar tabla

        for (IncidenteSeguridad incidente : gestor.getIncidentesSeguridad()) { // Necesitas getIncidentesSeguridad() en GestorAeropuerto
            modelo.addRow(new Object[]{
                incidente.getId(),
                incidente.getTipoIncidente(),
                incidente.getVueloAfectado() != null ? incidente.getVueloAfectado().getId() : "N/A",
                incidente.getFechaHoraReporte().toLocalDate().toString(),
                incidente.getDescripcion().length() > 50 ? incidente.getDescripcion().substring(0, 47) + "..." : incidente.getDescripcion(),
                incidente.getEstado().toString()
            });
        }
    }

    private void verDetallesIncidenteSeleccionado() {
        int selectedRow = vista.tablaIncidentes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(vista, "Por favor, selecciona un incidente de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String incidenteId = (String) vista.getModeloTablaIncidentes().getValueAt(selectedRow, 0);
        IncidenteSeguridad incidente = gestor.buscarIncidenteSeguridadPorId(incidenteId); // Necesitas este método en GestorAeropuerto

        if (incidente != null) {
            String detalles = "ID: " + incidente.getId() + "\n" +
                              "Tipo: " + incidente.getTipoIncidente() + "\n" +
                              "Vuelo Afectado: " + (incidente.getVueloAfectado() != null ? incidente.getVueloAfectado().getId() : "N/A") + "\n" +
                              "Fecha y Hora: " + incidente.getFechaHoraReporte().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" +
                              "Estado: " + incidente.getEstado() + "\n" +
                              "Descripción:\n" + incidente.getDescripcion();
            JOptionPane.showMessageDialog(vista, detalles, "Detalles del Incidente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró el incidente seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}