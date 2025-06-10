package controlador;

import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaSearchFlights; // Ahora es una clase JFrame
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorSearchFlights {

    private final VistaSearchFlights vista;
    private final Aeropuerto aeropuerto;
    private final JFrame vistaAnterior; // Referencia a la vista de la que se lanzó (ej. VistaOperadorVuelos)

    public ControladorSearchFlights(VistaSearchFlights vista, Aeropuerto aeropuerto, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;

        // Listener para el botón de búsqueda
        this.vista.btnBuscar.addActionListener(new FiltrarVuelosListener());
        
        // Listener para el botón de volver
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); // Cierra la ventana actual
                if (vistaAnterior != null) {
                    vistaAnterior.setVisible(true); // Vuelve a mostrar la ventana anterior
                }
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true); // Hace visible la ventana de búsqueda
    }

    class FiltrarVuelosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String aerolineaStr = vista.getAerolineaFiltro();
            String origen = vista.getOrigenFiltro();
            String destino = vista.getDestinoFiltro();
            String codigoVueloStr = vista.getCodigoVueloFiltro();
            String terminalStr = vista.getTerminalFiltro();
            String fechaStr = vista.getFechaFiltro(); // Campo de fecha
            String horaStr = vista.getHoraFiltro();   // Campo de hora
            String tipoVueloStr = vista.getSalidaLlegadaFiltro(); // "SALIDA" o "LLEGADA"

            // Validar que al menos un campo de filtro está lleno
            if (aerolineaStr.isEmpty() && origen.isEmpty() && destino.isEmpty() && 
                codigoVueloStr.isEmpty() && terminalStr.isEmpty() && fechaStr.isEmpty() && 
                horaStr.isEmpty() && (tipoVueloStr == null || tipoVueloStr.equals("TODOS"))) {
                JOptionPane.showMessageDialog(vista, "Por favor, introduce al menos un criterio de búsqueda.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                vista.limpiarTabla(); // Asegúrate de que la tabla esté vacía si no hay criterios
                return;
            }

            List<Vuelo> vuelosFiltrados = aeropuerto.getVuelos().stream()
                .filter(vuelo -> {
                    boolean cumpleFiltro = true;

                    // Filtro por Aerolínea
                    if (!aerolineaStr.isEmpty()) {
                        cumpleFiltro = cumpleFiltro && (vuelo.getAerolinea() != null && vuelo.getAerolinea().getNombre().equalsIgnoreCase(aerolineaStr));
                    }

                    // Filtro por Origen
                    if (!origen.isEmpty()) {
                        cumpleFiltro = cumpleFiltro && vuelo.getOrigen().equalsIgnoreCase(origen);
                    }

                    // Filtro por Destino
                    if (!destino.isEmpty()) {
                        cumpleFiltro = cumpleFiltro && vuelo.getDestino().equalsIgnoreCase(destino);
                    }

                    // Filtro por ID de Vuelo (asumiendo que "codigoVuelo" es el ID)
                    if (!codigoVueloStr.isEmpty()) {
                        try {
                            int idVuelo = Integer.parseInt(codigoVueloStr);
                            cumpleFiltro = cumpleFiltro && (vuelo.getId() == idVuelo);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(vista, "El ID de vuelo debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                            return false; // Invalida la búsqueda si el formato es incorrecto
                        }
                    }

                    // Filtro por Terminal
                    if (!terminalStr.isEmpty()) {
                        // Asumiendo que Terminal tiene un método para obtener su "código" o "nombre" o ID
                        // Si terminalStr es un ID, necesitarías parsearlo a int.
                        // Usaremos el ID de la terminal por simplicidad.
                        cumpleFiltro = cumpleFiltro && (vuelo.getTerminal() != null && String.valueOf(vuelo.getTerminal().getId()).equalsIgnoreCase(terminalStr)); 
                    }

                    // Filtro por Fecha y Hora
                    if (!fechaStr.isEmpty() && !horaStr.isEmpty()) {
                        try {
                            LocalDateTime fechaHoraFiltro = LocalDateTime.parse(fechaStr + "T" + horaStr);
                            LocalDateTime fechaHoraVuelo = vuelo.getFechaHoraSalida(); // O getFechaHoraLlegada() según lo que busques
                            cumpleFiltro = cumpleFiltro && fechaHoraVuelo.equals(fechaHoraFiltro);
                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(vista, "Formato de fecha u hora inválido. Usa AAAA-MM-DD y HH:MM", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                            return false; // Invalida la búsqueda para este vuelo
                        }
                    } else if (!fechaStr.isEmpty()) { // Solo fecha
                         try {
                            LocalDateTime fechaFiltro = LocalDateTime.parse(fechaStr + "T00:00:00"); // Hora arbitraria para comparación de solo fecha
                            LocalDateTime fechaVuelo = vuelo.getFechaHoraSalida(); // O getFechaHoraLlegada()
                            cumpleFiltro = cumpleFiltro && fechaVuelo.toLocalDate().equals(fechaFiltro.toLocalDate());
                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Usa AAAA-MM-DD", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                            return false; // Invalida la búsqueda
                        }
                    } else if (!horaStr.isEmpty()) { // Solo hora (esto podría ser un rango si la hora se usa para salidas/llegadas en un día específico)
                        try {
                            // Si solo se busca por hora, asumimos que se busca cualquier vuelo en esa hora en cualquier día
                            String horaVueloStr = vuelo.getFechaHoraSalida().toLocalTime().toString(); // O getFechaHoraLlegada()
                            cumpleFiltro = cumpleFiltro && horaVueloStr.startsWith(horaStr); // Busca coincidencias de hora (ej: "10:30" coincide con "10:30:00")
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(vista, "Formato de hora inválido. Usa HH:MM", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                            return false; // Invalida la búsqueda
                        }
                    }


                    // Filtro por Tipo de Vuelo (SALIDA/LLEGADA)
                    if (tipoVueloStr != null && !tipoVueloStr.isEmpty() && !tipoVueloStr.equals("TODOS")) {
                        try {
                            Vuelo.TipoVuelo tipoFiltro = Vuelo.TipoVuelo.valueOf(tipoVueloStr.toUpperCase());
                            cumpleFiltro = cumpleFiltro && vuelo.getTipoVuelo() == tipoFiltro;
                        } catch (IllegalArgumentException ex) {
                            // Si el tipo de vuelo no es válido, este filtro no se cumple.
                            cumpleFiltro = false; 
                        }
                    }

                    return cumpleFiltro;
                })
                .collect(Collectors.toList());

            // Actualizar la vista con la lista de objetos Vuelo filtrados
            vista.actualizarTablaVuelos(vuelosFiltrados);

            if (vuelosFiltrados.isEmpty()) {
                 JOptionPane.showMessageDialog(vista, "No se encontraron vuelos con los criterios especificados.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}