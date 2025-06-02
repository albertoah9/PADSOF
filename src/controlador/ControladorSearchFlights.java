package controlador;

import modelo.*;
import vista.paneles.PanelSearchFlights;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSearchFlights {

    private final PanelSearchFlights vista;
    private final GestorAeropuerto modelo;

    public ControladorSearchFlights(PanelSearchFlights vista, GestorAeropuerto modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.addFiltrarVuelosListener(new FiltrarVuelosListener());
        // Aquí se podrían añadir listeners para los botones de "Modify" y "View" de los vuelos
    }

    class FiltrarVuelosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String aerolinea = vista.getAerolineaFiltro();
            String origen = vista.getOrigenFiltro();
            String destino = vista.getDestinoFiltro();
            String codigoVuelo = vista.getCodigoVueloFiltro();
            String terminal = vista.getTerminalFiltro();
            String hora = vista.getHoraFiltro();
            String salidaLlegada = vista.getSalidaLlegadaFiltro();

            // Llamar al modelo para buscar vuelos con los criterios proporcionados
            java.util.List<String> resultados = modelo.buscarVuelos(aerolinea, origen, destino, codigoVuelo, terminal, hora, salidaLlegada);

            // Actualizar la vista con los resultados
            vista.actualizarListaVuelos(resultados);
        }
    }

    // Métodos para manejar las acciones de "Modify" y "View" de los vuelos
    // ...
}