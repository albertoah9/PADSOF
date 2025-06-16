import controlador.ControladorVistaControladorPrincipal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import modelo.*;
import vista.VistaControladorPrincipal;

public class MainControlador {
        public static void main(String[] args) {
                Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madrid", "España",
                                Aeropuerto.UbiRelCiudad.ESTE);

                Aerolinea aerolinea = new Aerolinea("Iberia", "A2345");
                aeropuerto.addAerolinea(aerolinea);

                TerminalPasajeros t1 = new TerminalPasajeros(450);

                ControladorAereo controlador = new ControladorAereo("Robert", "123", t1);
                aeropuerto.setUsuarioActivo(controlador);

                // Aviones
                AvionCarga avionCarga = new AvionCarga("Boeing", "737", "1234 JBDN", 3354, LocalDate.of(2027, 7, 23),
                                LocalDate.of(2027, 7, 23), false, 3500, false, aerolinea);
                aerolinea.añadirAvion(avionCarga);

                AvionPasajeros avionPasajeros = new AvionPasajeros("Falkon", "Pedrito", "0000 HDP", 1250,
                                LocalDate.of(2027, 7, 23), LocalDate.of(2027, 7, 23), 12, aerolinea);
                aerolinea.añadirAvion(avionPasajeros);

                // Vuelos (menos y con el mismo estado para probar)
                Vuelo vuelo1 = new Vuelo("Madrid", "Paris", LocalDateTime.of(2027, 7, 23, 12, 20),
                                LocalDateTime.of(2027, 7, 23, 10, 0), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo1);

                Vuelo vuelo2 = new Vuelo("Rotterdam", "Madrid", LocalDateTime.of(2027, 7, 23, 12, 30),
                                LocalDateTime.of(2027, 7, 23, 9, 20), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo2);

                Vuelo vuelo3 = new Vuelo("Madrid", "Barcelona", LocalDateTime.of(2027, 7, 23, 13, 0),
                                LocalDateTime.of(2027, 7, 23, 14, 0), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo3);

                ZonaAparcamiento zona1 = new ZonaAparcamiento(5, 5, 20);
                ZonaAparcamiento zona2 = new ZonaAparcamiento(4, 7, 90);

                Finger finger1 = new Finger(4.0);
                Finger finger2 = new Finger(3.5);

                PuertaEmbarque puerta1 = new PuertaEmbarque(finger1, zona1, 150, PuertaEmbarque.TipoPuerta.PASAJEROS);
                PuertaEmbarque puerta2 = new PuertaEmbarque(finger1, zona2, 100, PuertaEmbarque.TipoPuerta.MERCANCIAS);
                PuertaEmbarque puerta3 = new PuertaEmbarque(finger2, zona1, 120, PuertaEmbarque.TipoPuerta.PASAJEROS);

                puerta2.ocuparPuerta();

                finger1.agregarPuertaEmbarque(puerta1);
                finger1.agregarPuertaEmbarque(puerta2);
                finger2.agregarPuertaEmbarque(puerta3);

                t1.addFinger(finger1);
                t1.addFinger(finger2);

                Hangar hangar1 = new Hangar(2, 2, 2, 500);
                Hangar hangar2 = new Hangar(2, 2, 2, 750);

                aeropuerto.addHangar(hangar1);
                aeropuerto.addHangar(hangar2);

                Pista pistaDespegue = new PistaDespegue(1000, 50);
                Pista pistaAterrizaje = new PistaAterrizaje(800, 40);

                aeropuerto.addPista(pistaDespegue);
                aeropuerto.addPista(pistaAterrizaje);

                Notificacion noti1 = new Notificacion("El vuelo a París ha sido actualizado.",
                                Arrays.asList(controlador));
                Notificacion noti2 = new Notificacion("Nueva política de despegues de la UE.",
                                Arrays.asList(controlador));

                VistaControladorPrincipal vista = new VistaControladorPrincipal();
                ControladorVistaControladorPrincipal controladorVista = new ControladorVistaControladorPrincipal(vista,
                                aeropuerto, aerolinea, vista);
                controladorVista.iniciar();
        }
}
