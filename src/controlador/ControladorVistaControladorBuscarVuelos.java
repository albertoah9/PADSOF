package controlador;

import java.util.List;
import javax.swing.*;
import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaControladorBuscarVuelo;

/**
 * Controlador encargado de gestionar la vista de búsqueda de vuelos.
 * Permite aplicar filtros por origen, destino, estado y clase del vuelo.
 */
public class ControladorVistaControladorBuscarVuelos {

    private VistaControladorBuscarVuelo vista;
    private Aeropuerto aeropuerto;
    private JFrame vistaAnterior;

    /**
     * Constructor del controlador.
     *
     * @param vista         Vista para la búsqueda de vuelos
     * @param aeropuerto    Aeropuerto que contiene los vuelos
     * @param vistaAnterior Vista anterior que será visible al volver
     */
    public ControladorVistaControladorBuscarVuelos(VistaControladorBuscarVuelo vista, Aeropuerto aeropuerto,
            JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;

        cargarVuelos(null, null, null, null);

        this.vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        this.vista.btnFiltrar.addActionListener(_ -> {
            String origen = vista.txtFiltroOrigen.getText().trim();
            String destino = vista.txtFiltroDestino.getText().trim();
            String estado = (String) vista.comboFiltroEstado.getSelectedItem();
            if (estado != null && estado.isEmpty())
                estado = null;
            String clase = (String) vista.comboFiltroClase.getSelectedItem();
            if (clase != null && clase.isEmpty())
                clase = null;

            cargarVuelos(
                    origen.isEmpty() ? null : origen,
                    destino.isEmpty() ? null : destino,
                    estado,
                    clase);
        });
    }

    /**
     * Muestra la vista de búsqueda de vuelos.
     */
    public void iniciar() {
        vista.setVisible(true);
    }

    /**
     * Carga y muestra los vuelos que cumplen con los filtros aplicados.
     *
     * @param filtroOrigen  Origen del vuelo (puede ser null)
     * @param filtroDestino Destino del vuelo (puede ser null)
     * @param filtroEstado  Estado del vuelo (puede ser null)
     * @param filtroClase   Clase del vuelo (puede ser null)
     */
    private void cargarVuelos(String filtroOrigen, String filtroDestino, String filtroEstado, String filtroClase) {
        vista.limpiarTabla();

        List<Vuelo> vuelos = aeropuerto.getVuelos();

        for (Vuelo v : vuelos) {
            if (cumpleFiltro(v, filtroOrigen, filtroDestino, filtroEstado, filtroClase)) {
                Object[] fila = {
                        v.getId(),
                        v.getOrigen(),
                        v.getDestino(),
                        v.getfechaHoraSalida(),
                        v.getfechaHoraLlegada(),
                        v.getTipoVuelo(),
                        v.getClaseVuelo(),
                        v.getEstado()
                };
                vista.agregarVuelo(fila);
            }
        }

        vista.tablaVuelos.revalidate();
        vista.tablaVuelos.repaint();
    }

    /**
     * Verifica si un vuelo cumple con los filtros indicados.
     *
     * @param v       Vuelo a evaluar
     * @param origen  Filtro de origen (puede ser null)
     * @param destino Filtro de destino (puede ser null)
     * @param estado  Filtro de estado del vuelo (puede ser null)
     * @param clase   Filtro de clase del vuelo (puede ser null)
     * @return true si el vuelo pasa todos los filtros, false en caso contrario
     */
    private boolean cumpleFiltro(Vuelo v, String origen, String destino, String estado, String clase) {
        boolean cumple = true;

        if (origen != null) {
            cumple &= v.getOrigen().toLowerCase().contains(origen.toLowerCase());
        }
        if (destino != null) {
            cumple &= v.getDestino().toLowerCase().contains(destino.toLowerCase());
        }
        if (estado != null) {
            cumple &= v.getEstado().name().equalsIgnoreCase(estado);
        }
        if (clase != null) {
            cumple &= v.getClaseVuelo().name().equalsIgnoreCase(clase);
        }

        return cumple;
    }
}
