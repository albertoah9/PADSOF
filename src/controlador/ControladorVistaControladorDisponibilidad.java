package controlador;

import modelo.*;
import vista.VistaControladorDisponibilidad;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar la vista de disponibilidad de los
 * elementos del aeropuerto.
 * Permite visualizar el estado (ocupado o libre) de distintos elementos e
 * incluye filtros por ID, tipo y disponibilidad.
 */
public class ControladorVistaControladorDisponibilidad {

    private VistaControladorDisponibilidad vista;
    private JFrame vistaAnterior;
    private List<ElementoAeropuerto> elementosAeropuerto;
    private List<Pista> pistas;
    private ArrayList<UsoElementoAeropuerto> usos;

    /**
     * Constructor del controlador.
     * 
     * @param vista               Vista que muestra la disponibilidad
     * @param elementosAeropuerto Lista de elementos del aeropuerto (hangar, finger,
     *                            zona de aparcamiento, etc.)
     * @param pistas              Lista de pistas
     * @param usos                Lista de usos registrados de elementos del
     *                            aeropuerto
     * @param vistaAnterior       Vista desde la cual se accedió
     */
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

        this.vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        this.vista.btnFiltrar.addActionListener(_ -> {
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

    /**
     * Muestra la ventana principal.
     */
    public void iniciar() {
        vista.setVisible(true);
    }

    /**
     * Carga en la tabla los elementos y pistas del aeropuerto filtrados por los
     * parámetros dados.
     * 
     * @param idFiltro             ID a filtrar (puede ser null)
     * @param tipoFiltro           Tipo de elemento (puede ser null)
     * @param disponibilidadFiltro "ocupado" o "libre" (puede ser null)
     */
    private void cargarElementos(Integer idFiltro, String tipoFiltro, String disponibilidadFiltro) {
        vista.limpiarTabla();
        LocalDateTime ahora = LocalDateTime.now();

        for (ElementoAeropuerto elem : elementosAeropuerto) {
            boolean ocupado = estaOcupado(elem, ahora);

            if (cumpleFiltro(elem.getId(), elem.getClass().getSimpleName(), ocupado, idFiltro, tipoFiltro,
                    disponibilidadFiltro)) {
                Object[] fila = {
                        elem.getId(),
                        elem.getClass().getSimpleName(),
                        ocupado ? "Ocupado" : "Libre"
                };
                vista.agregarElemento(fila);
            }
        }

        for (Pista pista : pistas) {
            boolean ocupado = pista.isOcupada();
            if (cumpleFiltro(pista.getId(), pista.getClass().getSimpleName(), ocupado, idFiltro, tipoFiltro,
                    disponibilidadFiltro)) {
                Object[] fila = {
                        pista.getId(),
                        pista.getClass().getSimpleName(),
                        ocupado ? "Ocupado" : "Libre"
                };
                vista.agregarElemento(fila);
            }
        }

        vista.tablaElementos.revalidate();
        vista.tablaElementos.repaint();
    }

    /**
     * Determina si un elemento está ocupado en el momento actual.
     * 
     * @param elem  Elemento del aeropuerto a comprobar
     * @param ahora Momento actual
     * @return true si el elemento está ocupado, false si está libre
     */
    private boolean estaOcupado(ElementoAeropuerto elem, LocalDateTime ahora) {
        if (elem instanceof Hangar) {
            Hangar hangar = (Hangar) elem;
            return hangar.estaOcupado();
        } else if (elem instanceof ZonaAparcamiento) {
            ZonaAparcamiento zona = (ZonaAparcamiento) elem;
            return zona.plazasOcupadas() > 0;
        } else if (elem instanceof Finger) {
            Finger finger = (Finger) elem;
            return finger.estaOcupado();
        } else {

            return elem.isOcupado(ahora, usos);
        }
    }

    /**
     * Comprueba si un elemento cumple los filtros aplicados por el usuario.
     * 
     * @param id                   ID del elemento
     * @param tipo                 Tipo del elemento
     * @param ocupado              Estado de ocupación del elemento
     * @param filtroID             Filtro por ID (puede ser null)
     * @param filtroTipo           Filtro por tipo (puede ser null)
     * @param filtroDisponibilidad Filtro por disponibilidad (puede ser null)
     * @return true si el elemento pasa todos los filtros, false en caso contrario
     */
    private boolean cumpleFiltro(int id, String tipo, boolean ocupado, Integer filtroID, String filtroTipo,
            String filtroDisponibilidad) {
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
