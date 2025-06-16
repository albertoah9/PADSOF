import controlador.*;
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

                // --- Terminales, Puertas, Pistas, Hangares, Aparcamientos ---
                Terminal terminal1 = new Terminal();
                Terminal terminal2 = new Terminal();
                aeropuerto.addTerminal(terminal1);
                aeropuerto.addTerminal(terminal2);
                gestorAeropuerto.registrarEvento("TERMINAL_CREADA", "Terminal ID " + terminal1.getId() + " creada.");
                gestorAeropuerto.registrarEvento("TERMINAL_CREADA", "Terminal ID " + terminal2.getId() + " creada.");

                PuertaEmbarque puerta1 = new PuertaEmbarque(null, null, 0, null);
                PuertaEmbarque puerta2 = new PuertaEmbarque(null, null, 1, null); // Asumiendo que PuertaEmbarque tiene un constructor adecuado
                PuertaEmbarque puerta3 = new PuertaEmbarque(null, null, 2, null);
                aeropuerto.addPuertaEmbarque(puerta1);
                aeropuerto.addPuertaEmbarque(puerta2);
                aeropuerto.addPuertaEmbarque(puerta3);
                gestorAeropuerto.registrarEvento("PUERTA_CREADA", "Puerta " + puerta1.getId() + " creada.");

                Pista pista1 = new PistaAterrizaje(3000, 60);
                Pista pista2 = new PistaDespegue(2500, 45);
                aeropuerto.addPista(pista1);
                aeropuerto.addPista(pista2);
                gestorAeropuerto.registrarEvento("PISTA_CREADA", "Pista ID " + pista1.getId() + " creada.");

                Hangar hangar1 = new Hangar(3000.0, 50.0, 200.0, 20);
                Hangar hangar2 = new Hangar(1000.0, 60.0, 300.0, 15);
                aeropuerto.addHangar(hangar1);
                aeropuerto.addHangar(hangar2);
                gestorAeropuerto.registrarEvento("HANGAR_CREADO", "Hangar ID " + hangar1.getId() + " creado.");

                ZonaAparcamiento zonaAp1 = new ZonaAparcamiento(2000.0, 500.0, 10);
                ZonaAparcamiento zonaAp2 = new ZonaAparcamiento(1000.0, 2000.0, 30);
                aeropuerto.addZonaAparcamiento(zonaAp1);
                aeropuerto.addZonaAparcamiento(zonaAp2);
                gestorAeropuerto.registrarEvento("APARCAMIENTO_CREADO", "Zona de Aparcamiento '" + zonaAp1.getId() + "' creada.");

                // --- Aerolineas, Aviones ---
                Aerolinea aerolinea1 = new Aerolinea("Air España", "AE001");
                Aerolinea aerolinea2 = new Aerolinea("Vuelo Seguro", "VS002");
                aeropuerto.addAerolinea(aerolinea1);
                aeropuerto.addAerolinea(aerolinea2);
                gestorAeropuerto.registrarEvento("AEROLINEA_AGREGADA", "Aerolínea '" + aerolinea1.getNombre() + "' agregada.");

                Avion avionPasajeros1 = new AvionPasajeros("Airbus", "A320", "EC-ABC", 5000,
                        LocalDate.of(2023, 1, 15), LocalDate.of(2020, 5, 20), 180, aerolinea1);
                Avion avionCarga1 = new AvionCarga("Boeing", "747F", "N123FD", 8000,
                        LocalDate.of(2024, 2, 10), LocalDate.of(2018, 11, 1), true, 100000, false, aerolinea2);
                aerolinea1.añadirAvion(avionPasajeros1);
                aerolinea2.añadirAvion(avionCarga1);
                gestorAeropuerto.registrarEvento("AVION_AGREGADO", "Avión '" + avionPasajeros1.getMatricula() + "' a aerolínea " + aerolinea1.getNombre() + ".");

                // --- Vuelos ---
                AeropuertoDestino madrid = new AeropuertoDestino("Madrid-Barajas", "Madrid", "España", AeropuertoDestino.UbiRelCiudad.ESTE);
                AeropuertoDestino barcelona = new AeropuertoDestino("Barcelona-El Prat", "Barcelona", "España", AeropuertoDestino.UbiRelCiudad.ESTE);
                AeropuertoDestino londres = new AeropuertoDestino("Heathrow", "Londres", "Reino Unido", AeropuertoDestino.UbiRelCiudad.NORTE);
                aeropuerto.addAeropuertoDestino(madrid);
                aeropuerto.addAeropuertoDestino(barcelona);
                aeropuerto.addAeropuertoDestino(londres);

                Vuelo vuelo1 = new Vuelo("Madrid", "Barcelona", LocalDateTime.of(2025, 6, 10, 10, 0), 
                    LocalDateTime.of(2025, 6, 10, 8, 30), terminal1, avionPasajeros1, pista1, puerta1, 
                    Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
                aeropuerto.addVuelo(vuelo1);
                gestorAeropuerto.registrarEvento("VUELO_PROGRAMADO", "Vuelo " + vuelo1.getId() + " (" + vuelo1.getOrigen() + " a " + vuelo1.getDestino() + ") programado.");

                Vuelo vuelo2 = new Vuelo("Londres", "Madrid", LocalDateTime.of(2025, 6, 11, 15, 30),
                    LocalDateTime.of(2025, 6, 11, 13, 0), terminal2, avionCarga1, pista2, puerta2,
                    Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE, aeropuerto, Vuelo.TipoVuelo.LLEGADA, Vuelo.ClaseVuelo.MERCANCIAS, aerolinea2);
                aeropuerto.addVuelo(vuelo2);
                gestorAeropuerto.registrarEvento("VUELO_PROGRAMADO", "Vuelo " + vuelo2.getId() + " (" + vuelo2.getOrigen() + " a " + vuelo2.getDestino() + ") programado.");

                // --- Personal (Operadores y Controladores) ---
                ControladorAereo cont1 = gestorAeropuerto.darDeAltaControladorAereo("Maria", "passC1", terminal1);
                aeropuerto.addUsuario(cont1);
                ControladorAereo cont2 = gestorAeropuerto.darDeAltaControladorAereo("Carlos", "passC2", terminal2);
                aeropuerto.addUsuario(cont2);

                OperadorAereo op1 = gestorAeropuerto.darDeAltaOperadorAereo("Juan", "passO1", aerolinea1);
                aeropuerto.addUsuario(op1);
                OperadorAereo op2 = gestorAeropuerto.darDeAltaOperadorAereo("Ana", "passO2", aerolinea2);
                aeropuerto.addUsuario(op2);

                // --- Facturas y Pagos ---
                Factura factura1 = new Factura(250.75, aerolinea1);
                Factura factura2 = new Factura(1200.00, aerolinea2);
                gestorAeropuerto.addFactura(factura1);
                gestorAeropuerto.addFactura(factura2);

                Pago pago1 = new Pago(factura1.getId(), 250.75, LocalDateTime.now());
                gestorAeropuerto.addPago(pago1);
                factura1.marcarComoPagado();
                gestorAeropuerto.registrarEvento("PAGO_REALIZADO", "Pago de factura " + factura1.getId() + " completado.");

                // --- Incidentes de Seguridad ---
                // Asumiendo que IncidenteSeguridad tiene un constructor como (tipo, descripcion, vueloAfectado, fechaHora)
                // y que Vuelo tiene un método getId()
                // modelo.IncidenteSeguridad incidente1 = new modelo.IncidenteSeguridad(
                //     "Intrusión", "Detectado personal no autorizado en zona restringida.", null, LocalDateTime.now()
                // );
                // modelo.IncidenteSeguridad incidente2 = new modelo.IncidenteSeguridad(
                //     "Problema Técnico", "Fallo en el sistema de iluminación de la pista.", vuelo1, LocalDateTime.now()
                // );
                // gestorAeropuerto.addIncidenteSeguridad(incidente1);
                // gestorAeropuerto.addIncidenteSeguridad(incidente2);


                // 4. Configurar la interfaz de usuario (UI)
                // Ahora, crea la ventana principal y pasa el gestorAeropuerto a sus controladores
                VistaGestorPrincipal ventanaPrincipal = new VistaGestorPrincipal(gestorAeropuerto); // Esto creará la UI

                // Asumiendo que tu VentanaPrincipalGestor tiene getters para sus sub-vistas
                // y que cada sub-vista tiene getters para sus componentes interactivos (botones, etc.)

                // Ejemplo para el ControladorVistaHistorial:
                VistaHistorial vistaHistorial = ventanaPrincipal.getVistaHistorial(); // Obtén la instancia de la vista de historial
                ControladorVistaHistorial controladorHistorial = new ControladorVistaHistorial(vistaHistorial, gestorAeropuerto);

                // Repite para otros controladores y sus vistas
                // ControladorGestionVuelos controladorVuelos = new ControladorGestionVuelos(ventanaPrincipal.getVistaGestionVuelos(), gestorAeropuerto);
                // ControladorGestionPersonal controladorPersonal = new ControladorGestionPersonal(ventanaPrincipal.getVistaGestionPersonal(), gestorAeropuerto);
                // ... y así sucesivamente para cada sección de la interfaz

                // Si tienes un controlador de login (que manejaría la autenticación inicial):
                // ControladorLogin controladorLogin = new ControladorLogin(vistaLogin, gestorAeropuerto);
                // controladorLogin.iniciar(); // Esto mostraría la ventana de login primero    

                // Finalmente, haz visible la ventana principal
                ventanaPrincipal.setVisible(true);

            } catch (Exception e) {
                System.err.println("Error fatal al iniciar la aplicación: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}