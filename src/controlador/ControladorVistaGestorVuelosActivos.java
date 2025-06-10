package controlador;

import vista.*;
import modelo.GestorAeropuerto;
import modelo.Vuelo; // Importar la clase Vuelo

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorVistaGestorVuelosActivos {

    private VistaGestorVuelosActivos vista;
    private GestorAeropuerto gestor;

    public ControladorVistaGestorVuelosActivos(VistaGestorVuelosActivos vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        // Cargar los vuelos activos al iniciar
        cargarVuelosActivos();

        // ActionListener para el botón Refrescar
        this.vista.btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarVuelosActivos();
                gestor.registrarEvento("VISUALIZACION", "Tabla de Vuelos Activos refrescada.");
            }
        });

        gestor.registrarEvento("CONTROLADOR", "Controlador de VistaGestorVuelosActivos inicializado.");
    }

    private void cargarVuelosActivos() {
        DefaultTableModel modelo = vista.getModeloTablaVuelosActivos();
        modelo.setRowCount(0); // Limpiar la tabla

        // Filtrar vuelos que se consideren "activos"
        // Esto es una lógica de negocio que definirás en GestorAeropuerto o aquí.
        // Por ejemplo: vuelos con estado EN_PREPARACION, EN_PISTA, DESPEGADO, EN_VUELO, ATERRIZADO, EMBARCANDO, ESPERANDO_DESPEGUE
        List<Vuelo> todosLosVuelos = gestor.getVuelos(); // Obtener todos los vuelos del gestor

        for (Vuelo vuelo : todosLosVuelos) {
            // Ejemplo de lógica para determinar si un vuelo está "activo"
            if (vuelo.getEstado() == Vuelo.EstadoVuelo.EN_PREPARACION ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_PISTA ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.APARCADO ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.DESPEGADO ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.EN_HORA ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.EN_HANGAR ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.EMBARCANDO ||
                vuelo.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_DESPEGUE) {
                
                modelo.addRow(new Object[]{
                    vuelo.getId(),
                    vuelo.getOrigen(),
                    vuelo.getDestino(),
                    vuelo.getEstado().toString(),
                    vuelo.getAerolinea() != null ? vuelo.getAerolinea().getNombre() : "N/A",
                    vuelo.getfechaHoraSalida() != null ? vuelo.getfechaHoraSalida().toLocalTime().toString() : "N/A",
                    vuelo.getfechaHoraLlegada() != null ? vuelo.getfechaHoraLlegada().toLocalTime().toString() : "N/A",
                    vuelo.getPista() != null ? "Pista " + vuelo.getPista().getId() : "N/A", // Asumiendo Pista tiene un getId()
                    vuelo.getPuertaEmbarque() != null ? "Puerta " + vuelo.getPuertaEmbarque().getId() : "N/A" // Asumiendo PuertaEmbarque tiene un getId()
                });
            }
        }
    }
}