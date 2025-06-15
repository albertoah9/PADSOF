package controlador;

import modelo.*;
import vista.VistaControladorDisponibilidad;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaControladorDisponibilidad {

    private VistaControladorDisponibilidad vista;
    private JFrame vistaAnterior;
    private List<ElementoAeropuerto> elementosAeropuerto;
    private List<Pista> pistas;
    private ArrayList<UsoElementoAeropuerto> usos;

    public ControladorVistaControladorDisponibilidad(
            VistaControladorDisponibilidad vista,
            List<ElementoAeropuerto> elementosAeropuerto,
            List<Pista> pistas,
            ArrayList<UsoElementoAeropuerto> usos,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.elementosAeropuerto = elementosAeropuerto;
        this.pistas = pistas;
        this.usos = usos;
        this.vistaAnterior = vistaAnterior;

        cargarElementos(null, null, null);

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        this.vista.btnFiltrar.addActionListener(e -> {
            String idStr = vista.txtFiltroID.getText().trim();
            Integer idFiltro = null;
            try {
                if (!idStr.isEmpty()) {
                    idFiltro = Integer.parseInt(idStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "ID debe ser un número.");
                return;
            }

            String tipoFiltro = vista.txtFiltroTipo.getText().trim();
            String disponibilidadFiltro = vista.txtFiltroDisponibilidad.getText().trim().toLowerCase();

            cargarElementos(idFiltro, tipoFiltro.isEmpty() ? null : tipoFiltro,
                    disponibilidadFiltro.isEmpty() ? null : disponibilidadFiltro);
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void cargarElementos(Integer idFiltro, String tipoFiltro, String disponibilidadFiltro) {
        vista.limpiarTabla();

        // Fecha actual para validar disponibilidad
        LocalDateTime ahora = LocalDateTime.now();

        // Buscar entre elementos del aeropuerto (Hangar, Finger, ZonaAparcamiento)
        for (ElementoAeropuerto elem : elementosAeropuerto) {
            if (cumpleFiltro(elem.getId(), elem.getClass().getSimpleName(), elem.isOcupado(ahora, usos), idFiltro,
                    tipoFiltro, disponibilidadFiltro)) {
                Object[] fila = {
                        elem.getId(),
                        elem.getClass().getSimpleName(),
                        elem.isOcupado(ahora, usos) ? "Ocupado" : "Libre"
                };
                vista.agregarElemento(fila);
            }
        }

        // Buscar entre pistas
        for (Pista pista : pistas) {
            boolean ocupada = pista.isOcupada();
            if (cumpleFiltro(pista.getId(), pista.getClass().getSimpleName(), ocupada, idFiltro, tipoFiltro,
                    disponibilidadFiltro)) {
                Object[] fila = {
                        pista.getId(),
                        pista.getClass().getSimpleName(),
                        ocupada ? "Ocupado" : "Libre"
                };
                vista.agregarElemento(fila);
            }
        }

        vista.tablaElementos.revalidate();
        vista.tablaElementos.repaint();
    }

    private boolean cumpleFiltro(int id, String tipo, boolean ocupado, Integer filtroID, String filtroTipo,
            String filtroDisponibilidad) {
        boolean disponible = !ocupado;

        if (filtroID != null && id != filtroID)
            return false;

        if (filtroTipo != null && !tipo.toLowerCase().contains(filtroTipo.toLowerCase()))
            return false;

        if (filtroDisponibilidad != null) {
            if (filtroDisponibilidad.equals("libre") && ocupado)
                return false;
            if (filtroDisponibilidad.equals("ocupado") && !ocupado)
                return false;
        }

        return true;
    }
}
