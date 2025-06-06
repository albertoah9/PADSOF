package controlador;

import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaControladorBuscarVuelo;

import javax.swing.*;
import java.util.List;

public class ControladorVistaControladorBuscarVuelos {

    private VistaControladorBuscarVuelo vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaControladorBuscarVuelos(VistaControladorBuscarVuelo vista, Aeropuerto aeropuerto,
            Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.aerolinea = aerolinea;

        cargarVuelos(null, null, null, null);

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        this.vista.btnFiltrar.addActionListener(e -> {
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

    public void iniciar() {
        vista.setVisible(true);
    }

    private void cargarVuelos(String filtroOrigen, String filtroDestino, String filtroEstado, String filtroClase) {
        vista.limpiarTabla();

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea);

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

    private boolean cumpleFiltro(Vuelo v, String origen, String destino, String estado, String clase) {
        boolean cumple = true;

        if (origen != null) {
            cumple &= v.getOrigen().toLowerCase().contains(origen.toLowerCase());
        }
        if (destino != null) {
            cumple &= v.getDestino().toLowerCase().contains(destino.toLowerCase());
        }
        if (estado != null) {
            // Si getEstado() devuelve enum
            cumple &= v.getEstado().name().equalsIgnoreCase(estado);
        }
        if (clase != null) {
            // Si getClaseVuelo() devuelve enum
            cumple &= v.getClaseVuelo().name().equalsIgnoreCase(clase);
        }

        return cumple;
    }
}
