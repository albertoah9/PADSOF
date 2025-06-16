import controlador.*;
import vista.*;
import modelo.*;
import modelo.IncidenteSeguridad.EstadoIncidente; // Importar el enum para EstadoIncidente
import modelo.Factura.EstadoFactura; // Importar el enum para EstadoFactura si se usa
import modelo.PuertaEmbarque.TipoPuerta; // Importar el enum TipoPuerta

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.SwingUtilities;
import modelo.*;
import vista.*;

public class MainGestor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 1. Crear la instancia principal del Aeropuerto
                Aeropuerto aeropuerto = new Aeropuerto("Aeropuerto Internacional de Barajas", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);
                System.out.println("Aeropuerto principal creado: " + aeropuerto.getNombre());

                // 2. Crear la instancia del GestorAeropuerto, inyectándole el Aeropuerto
                GestorAeropuerto gestorAeropuerto = new GestorAeropuerto("gestor", "password123", aeropuerto);
                System.out.println("Gestor de Aeropuerto '" + gestorAeropuerto.getNombre() + "' creado.");

                // 3. Añadir datos de ejemplo al Aeropuerto y al GestorAeropuerto

                // --- Terminales, Fingers, zonaAparcamiento, Puertas, Pistas, Hangares ---
                Terminal terminal1 = new Terminal(); // Asumo constructor sin parámetros o con parámetros por defecto
                Terminal terminal2 = new Terminal();
                aeropuerto.addTerminal(terminal1);
                aeropuerto.addTerminal(terminal2);
                gestorAeropuerto.registrarEvento("TERMINAL_CREADA", "Terminal ID " + terminal1.getId() + " creada.");
                gestorAeropuerto.registrarEvento("TERMINAL_CREADA", "Terminal ID " + terminal2.getId() + " creada.");

                // Fingers
                Finger finger1 = new Finger(100);
                Finger finger2 = new Finger(120);
                Finger finger3 = new Finger(80);

                // zonaAparcamiento
                ZonaAparcamiento zonaAparcamiento1 = new ZonaAparcamiento(500.0, 1000.0, 15);
                ZonaAparcamiento zonaAparcamiento2 = new ZonaAparcamiento(750.0, 1500.0, 20);
                ZonaAparcamiento zonaAparcamiento3 = new ZonaAparcamiento(800.0, 2000.0, 25);
                aeropuerto.addZonaAparcamiento(zonaAparcamiento1);
                aeropuerto.addZonaAparcamiento(zonaAparcamiento2);
                aeropuerto.addZonaAparcamiento(zonaAparcamiento3);
                gestorAeropuerto.registrarEvento("APARCAMIENTO_CREADO", "Zona de Aparcamiento '" + zonaAparcamiento1.getId() + "' creada.");

                PuertaEmbarque puerta1 = new PuertaEmbarque(finger1, zonaAparcamiento1, 150, TipoPuerta.PASAJEROS);
                PuertaEmbarque puerta2 = new PuertaEmbarque(finger2, zonaAparcamiento2, 100, TipoPuerta.PASAJEROS);
                PuertaEmbarque puerta3 = new PuertaEmbarque(finger3, zonaAparcamiento3, 80, TipoPuerta.MERCANCIAS);
                aeropuerto.addPuertaEmbarque(puerta1);
                aeropuerto.addPuertaEmbarque(puerta2);
                aeropuerto.addPuertaEmbarque(puerta3);
                gestorAeropuerto.registrarEvento("PUERTA_CREADA", "Puerta " + puerta1.getId() + " (Pasajeros) creada.");
                gestorAeropuerto.registrarEvento("PUERTA_CREADA", "Puerta " + puerta2.getId() + " (Pasajeros) creada.");
                gestorAeropuerto.registrarEvento("PUERTA_CREADA", "Puerta " + puerta3.getId() + " (Mercancías) creada.");


                Pista pista1 = new PistaAterrizaje(3000, 60);
                Pista pista2 = new PistaDespegue(2500, 45);
                Pista pista3 = new PistaAterrizaje(3200, 60);
                aeropuerto.addPista(pista1);
                aeropuerto.addPista(pista2);
                aeropuerto.addPista(pista3);
                gestorAeropuerto.registrarEvento("PISTA_CREADA", "Pista ID " + pista1.getId() + " creada.");
                gestorAeropuerto.registrarEvento("PISTA_CREADA", "Pista ID " + pista3.getId() + " creada.");

                // Hangares
                Hangar hangar1 = new Hangar(3000.0, 50.0, 1500.0, 20);
                Hangar hangar2 = new Hangar(1000.0, 60.0, 600.0, 15);
                aeropuerto.addHangar(hangar1);
                aeropuerto.addHangar(hangar2);
                gestorAeropuerto.registrarEvento("HANGAR_CREADO", "Hangar ID " + hangar1.getId() + " creado.");


                // --- Aerolineas, Aviones ---
                Aerolinea aerolinea1 = new Aerolinea("Air España", "AE001");
                Aerolinea aerolinea2 = new Aerolinea("Vuelo Seguro", "VS002");
                Aerolinea aerolinea3 = new Aerolinea("Carga Express", "CX003"); // Añadimos otra aerolínea
                aeropuerto.addAerolinea(aerolinea1);
                aeropuerto.addAerolinea(aerolinea2);
                aeropuerto.addAerolinea(aerolinea3);
                gestorAeropuerto.registrarEvento("AEROLINEA_AGREGADA", "Aerolínea '" + aerolinea1.getNombre() + "' agregada.");
                gestorAeropuerto.registrarEvento("AEROLINEA_AGREGADA", "Aerolínea '" + aerolinea3.getNombre() + "' agregada.");

                Avion avionPasajeros1 = new AvionPasajeros("Airbus", "A320", "EC-ABC", 5000,
                        LocalDate.of(2023, 1, 15), LocalDate.of(2020, 5, 20), 180, aerolinea1);
                Avion avionCarga1 = new AvionCarga("Boeing", "747F", "N123FD", 8000,
                        LocalDate.of(2024, 2, 10), LocalDate.of(2018, 11, 1), true, 100000, false, aerolinea2);
                Avion avionPasajeros2 = new AvionPasajeros("Boeing", "737", "EC-DEF", 4500, // Nuevo avión para vuelos
                        LocalDate.of(2022, 3, 20), LocalDate.of(2019, 7, 10), 150, aerolinea1);
                Avion avionCarga2 = new AvionCarga("Antonov", "An-124", "UR-8207", 12000, // Nuevo avión de carga
                        LocalDate.of(2025, 1, 5), LocalDate.of(2005, 9, 1), true, 250000, true, aerolinea3);

                aerolinea1.añadirAvion(avionPasajeros1);
                aerolinea2.añadirAvion(avionCarga1);
                aerolinea1.añadirAvion(avionPasajeros2);
                aerolinea3.añadirAvion(avionCarga2);
                gestorAeropuerto.registrarEvento("AVION_AGREGADO", "Avión '" + avionPasajeros1.getMatricula() + "' a aerolínea " + aerolinea1.getNombre() + ".");
                gestorAeropuerto.registrarEvento("AVION_AGREGADO", "Avión '" + avionCarga2.getMatricula() + "' a aerolínea " + aerolinea3.getNombre() + ".");


                // --- Vuelos (Ejemplos de Vuelos Activos/Próximos) ---
                AeropuertoDestino madridDest = new AeropuertoDestino(1, "Madrid-Barajas", "Madrid", "España", AeropuertoDestino.UbiRelCiudad.ESTE);
                AeropuertoDestino barcelonaDest = new AeropuertoDestino(2, "Barcelona-El Prat", "Barcelona", "España", AeropuertoDestino.UbiRelCiudad.ESTE);
                AeropuertoDestino londresDest = new AeropuertoDestino(3, "Heathrow", "Londres", "Reino Unido", AeropuertoDestino.UbiRelCiudad.NORTE);
                AeropuertoDestino parisDest = new AeropuertoDestino(4, "Charles de Gaulle", "París", "Francia", AeropuertoDestino.UbiRelCiudad.NORTE);
                AeropuertoDestino frankfurtDest = new AeropuertoDestino(5, "Frankfurt Airport", "Frankfurt", "Alemania", AeropuertoDestino.UbiRelCiudad.NORTE);
                aeropuerto.addAeropuertoDestino(madridDest); // El aeropuerto en sí es también un destino
                aeropuerto.addAeropuertoDestino(barcelonaDest);
                aeropuerto.addAeropuertoDestino(londresDest);
                aeropuerto.addAeropuertoDestino(parisDest);
                aeropuerto.addAeropuertoDestino(frankfurtDest);

                // Vuelo de Salida: EMBARCANDO (para hoy)
                Vuelo vuelo1 = new Vuelo("Madrid", "Barcelona", LocalDateTime.of(2025, 6, 14, 13, 0), // Hoy, en 20 minutos
                        LocalDateTime.of(2025, 6, 14, 11, 30), terminal1, avionPasajeros1, pista1, puerta1,
                        Vuelo.EstadoVuelo.EMBARCANDO, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
                aeropuerto.addVuelo(vuelo1);
                gestorAeropuerto.registrarEvento("VUELO_PROGRAMADO", "Vuelo " + vuelo1.getId() + " (" + vuelo1.getOrigen() + " a " + vuelo1.getDestino() + ") - EMBARCANDO.");

                // Vuelo de Llegada: EN VUELO (para hoy)
                Vuelo vuelo2 = new Vuelo("Londres", "Madrid", LocalDateTime.of(2025, 6, 14, 15, 30), // Hoy
                        LocalDateTime.of(2025, 6, 14, 13, 0), terminal2, avionCarga1, pista2, puerta2, // Puerta 2, Pista 2
                        Vuelo.EstadoVuelo.DESPEGADO, aeropuerto, Vuelo.TipoVuelo.LLEGADA, Vuelo.ClaseVuelo.MERCANCIAS, aerolinea2);
                aeropuerto.addVuelo(vuelo2);
                gestorAeropuerto.registrarEvento("VUELO_PROGRAMADO", "Vuelo " + vuelo2.getId() + " (" + vuelo2.getOrigen() + " a " + vuelo2.getDestino() + ") - EN_VUELO.");

                // Vuelo de Salida: EN PREPARACION (para mañana)
                Vuelo vuelo3 = new Vuelo("Madrid", "París", LocalDateTime.of(2025, 6, 15, 18, 0),
                        LocalDateTime.of(2025, 6, 15, 16, 30), terminal1, avionPasajeros2, pista1, puerta3,
                        Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
                aeropuerto.addVuelo(vuelo3);
                gestorAeropuerto.registrarEvento("VUELO_PROGRAMADO", "Vuelo " + vuelo3.getId() + " (" + vuelo3.getOrigen() + " a " + vuelo3.getDestino() + ") - EN_PREPARACION.");

                // Vuelo de Llegada: ATERRIZADO (ejemplo de un vuelo reciente)
                Vuelo vuelo4 = new Vuelo("Frankfurt", "Madrid", LocalDateTime.of(2025, 6, 14, 9, 0), // Hoy, ya aterrizado
                        LocalDateTime.of(2025, 6, 14, 7, 0), terminal2, avionPasajeros1, pista3, puerta1, // Pista 3
                        Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE, aeropuerto, Vuelo.TipoVuelo.LLEGADA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
                aeropuerto.addVuelo(vuelo4);
                gestorAeropuerto.registrarEvento("VUELO_FINALIZADO", "Vuelo " + vuelo4.getId() + " (" + vuelo4.getOrigen() + " a " + vuelo4.getDestino() + ") - ATERRIZADO.");

                // Vuelo de Carga: RETRASADO (próximo)
                Vuelo vuelo5 = new Vuelo("Madrid", "Dublín", LocalDateTime.of(2025, 6, 16, 11, 0),
                        LocalDateTime.of(2025, 6, 16, 9, 0), terminal2, avionCarga2, pista2, null, // Sin puerta asignada
                        Vuelo.EstadoVuelo.RETRASADO, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.MERCANCIAS, aerolinea3);
                aeropuerto.addVuelo(vuelo5);
                gestorAeropuerto.registrarEvento("VUELO_RETRASADO", "Vuelo " + vuelo5.getId() + " (" + vuelo5.getOrigen() + " a " + vuelo5.getDestino() + ") - RETRASADO.");


                // --- Personal (Operadores y Controladores) ---
                ControladorAereo cont1 = gestorAeropuerto.darDeAltaControladorAereo("Maria Garcia", "passC1", terminal1);
                ControladorAereo cont2 = gestorAeropuerto.darDeAltaControladorAereo("Carlos Lopez", "passC2", terminal2);
                aeropuerto.agregarControladorAereo(cont1);
                aeropuerto.agregarControladorAereo(cont2);

                OperadorAereo op1 = gestorAeropuerto.darDeAltaOperadorAereo("Juan Perez", "passO1", aerolinea1);
                OperadorAereo op2 = gestorAeropuerto.darDeAltaOperadorAereo("Ana Gomez", "passO2", aerolinea2);
                aeropuerto.agregarOperadorAereo(op1);
                aeropuerto.agregarOperadorAereo(op2);

                // --- Facturas y Pagos (Ejemplos de diferentes estados) ---
                // Factura 1: Completamente pagada
                Factura factura1 = new Factura(250.75, aerolinea1);
                gestorAeropuerto.addFactura(factura1);
                Pago pago1 = new Pago(factura1.getId(), 250.75, LocalDateTime.now().minusDays(8));
                gestorAeropuerto.addPago(pago1);
                // Asegúrate que tu clase Factura tiene un método setEstado y el enum EstadoFactura.
                factura1.setEstado(EstadoFactura.PAGADO);
                gestorAeropuerto.registrarEvento("FACTURA_PAGADA", "Factura " + factura1.getId() + " (250.75€) pagada completamente por " + aerolinea1.getNombre() + ".");

                // Factura 2: Pendiente de pago
                Factura factura2 = new Factura(1200.00, aerolinea2);
                gestorAeropuerto.addFactura(factura2);
                gestorAeropuerto.registrarEvento("FACTURA_PENDIENTE", "Factura " + factura2.getId() + " (1200.00€) pendiente de pago para " + aerolinea2.getNombre() + ".");

                // Factura 3: Parcialmente pagada
                Factura factura3 = new Factura(500.00, aerolinea1);
                gestorAeropuerto.addFactura(factura3);
                Pago pago2 = new Pago(factura3.getId(), 200.00, LocalDateTime.now().minusDays(1)); // Primer pago
                gestorAeropuerto.addPago(pago2);
                factura3.setEstado(EstadoFactura.PAGADO); // Corrección si el estado "PAGADO" no implica parcial.
                gestorAeropuerto.registrarEvento("FACTURA_PAGO_PARCIAL", "Factura " + factura3.getId() + " (500.00€) parcialmente pagada (200.00€) por " + aerolinea1.getNombre() + ".");

                // Nueva Factura 4: Pendiente para aerolínea 3
                Factura factura4 = new Factura(750.50, aerolinea3);
                gestorAeropuerto.addFactura(factura4);
                gestorAeropuerto.registrarEvento("FACTURA_PENDIENTE", "Factura " + factura4.getId() + " (750.50€) pendiente para " + aerolinea3.getNombre() + ".");

                // --- Incidentes de Seguridad ---
                // Constructor de IncidenteSeguridad: (tipoIncidente, vueloAfectado, descripcion, fechaHoraReporte, estado)
                IncidenteSeguridad incidente1 = new IncidenteSeguridad(
                    "Intrusión", null, "Detectado personal no autorizado en zona restringida cerca de Terminal 1.",
                    LocalDateTime.now().minusHours(5), EstadoIncidente.EN_INVESTIGACION
                );
                gestorAeropuerto.addIncidenteSeguridad(incidente1);
                gestorAeropuerto.registrarEvento("INCIDENTE_REPORTADO", "Incidente de seguridad ('" + incidente1.getTipoIncidente() + "') reportado.");


                IncidenteSeguridad incidente2 = new IncidenteSeguridad(
                    "Problema Técnico", vuelo1, "Fallo en el sistema de iluminación de la pista de aterrizaje principal, afectando el vuelo " + vuelo1.getId() + ".",
                    LocalDateTime.now().minusHours(1), EstadoIncidente.REPORTADO
                );
                gestorAeropuerto.addIncidenteSeguridad(incidente2);
                gestorAeropuerto.registrarEvento("INCIDENTE_REPORTADO", "Incidente de seguridad ('" + incidente2.getTipoIncidente() + "') reportado, afectando Vuelo " + vuelo1.getId() + ".");

                // Otro incidente
                IncidenteSeguridad incidente3 = new IncidenteSeguridad(
                    "Equipaje Extraviado", null, "Reclamación de equipaje extraviado en el vuelo " + vuelo4.getId() + " de " + vuelo4.getOrigen() + ".",
                    LocalDateTime.now().minusMinutes(30), EstadoIncidente.REPORTADO
                );
                gestorAeropuerto.addIncidenteSeguridad(incidente3);
                gestorAeropuerto.registrarEvento("INCIDENTE_REPORTADO", "Incidente de seguridad ('" + incidente3.getTipoIncidente() + "') reportado.");


                // 4. INTERFACES
                VistaGestorPrincipal ventanaPrincipal = new VistaGestorPrincipal(gestorAeropuerto);

                VistaHistorial vistaHistorial = ventanaPrincipal.getVistaHistorial();
                ControladorVistaHistorial controladorHistorial = new ControladorVistaHistorial(vistaHistorial, gestorAeropuerto);

                // Ejemplo para el ControladorVistaGestorBuscarVuelos:
                // Esta vista se lanzaría desde alguna otra vista principal (ej. un botón en VistaGestionVuelos)
                // Por ahora, solo para demostrar la creación, la crearé y la mostraré.
                // Idealmente, esto se manejaría desde un controlador de la ventana principal.
                // VistaGestorBuscarVuelos vistaBuscarVuelos = new VistaGestorBuscarVuelos(ventanaPrincipal);
                // ControladorVistaGestorBuscarVuelos controladorBuscarVuelos = new ControladorVistaGestorBuscarVuelos(vistaBuscarVuelos, aeropuerto, ventanaPrincipal);
                // controladorBuscarVuelos.iniciar(); // Esto la haría visible al inicio, lo cual quizás no quieras.

                // Finalmente, haz visible la ventana principal del gestor
                ventanaPrincipal.setVisible(true);

            } catch (Exception e) {
                System.err.println("Error fatal al iniciar la aplicación del Gestor: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}