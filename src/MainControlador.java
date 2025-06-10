import controlador.ControladorVistaControladorDisponibilidad;
import controlador.ControladorVistaControladorPrincipal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Avion;
import modelo.AvionCarga;
import modelo.AvionPasajeros;
import modelo.Finger;
import modelo.OperadorAereo;
import modelo.Pista;
import modelo.PistaAterrizaje;
import modelo.PistaDespegue;
import modelo.PuertaEmbarque;
import modelo.Vuelo;
import modelo.ZonaAparcamiento;
import vista.VistaControladorDisponibilidad;
import vista.VistaControladorPrincipal;

public class MainControlador {
        public static void main(String[] args) {
                Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madrid", "España",
                                Aeropuerto.UbiRelCiudad.ESTE);

                Aerolinea aerolinea = new Aerolinea("Iberia", "A2345");
                aeropuerto.addAerolinea(aerolinea);

                OperadorAereo operador = new OperadorAereo("Alberto", aerolinea);
                aeropuerto.setUsuarioActivo(operador);

                Avion avionCarga = new AvionCarga("Boeing", "737", "1234 JBDN", 3354, LocalDate.of(2027, 7, 23),
                                LocalDate.of(2027, 7, 23), false, 3500, false, aerolinea);
                aerolinea.añadirAvion(avionCarga);

                Avion avionPasajeros = new AvionPasajeros("Falkon", "Pedrito", "0000 HDP", 1250,
                                LocalDate.of(2027, 7, 23), LocalDate.of(2027, 7, 23), 12, aerolinea);
                aerolinea.añadirAvion(avionPasajeros);

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

                // Añadir 20 vuelos adicionales
                Vuelo vuelo3 = new Vuelo("Madrid", "Barcelona", LocalDateTime.of(2027, 7, 23, 13, 0),
                                LocalDateTime.of(2027, 7, 23, 14, 0), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo3);

                Vuelo vuelo4 = new Vuelo("Amsterdam", "Madrid", LocalDateTime.of(2027, 7, 23, 13, 10),
                                LocalDateTime.of(2027, 7, 23, 10, 30), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo4);

                Vuelo vuelo5 = new Vuelo("Madrid", "Londres", LocalDateTime.of(2027, 7, 23, 14, 0),
                                LocalDateTime.of(2027, 7, 23, 15, 30), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo5);

                Vuelo vuelo6 = new Vuelo("Berlin", "Madrid", LocalDateTime.of(2027, 7, 23, 14, 20),
                                LocalDateTime.of(2027, 7, 23, 11, 40), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo6);

                Vuelo vuelo7 = new Vuelo("Madrid", "Roma", LocalDateTime.of(2027, 7, 23, 15, 0),
                                LocalDateTime.of(2027, 7, 23, 16, 40), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo7);

                Vuelo vuelo8 = new Vuelo("Paris", "Madrid", LocalDateTime.of(2027, 7, 23, 15, 30),
                                LocalDateTime.of(2027, 7, 23, 13, 0), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo8);

                Vuelo vuelo9 = new Vuelo("Madrid", "Lisboa", LocalDateTime.of(2027, 7, 23, 16, 0),
                                LocalDateTime.of(2027, 7, 23, 16, 50), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo9);

                Vuelo vuelo10 = new Vuelo("Viena", "Madrid", LocalDateTime.of(2027, 7, 23, 16, 40),
                                LocalDateTime.of(2027, 7, 23, 14, 10), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo10);

                Vuelo vuelo11 = new Vuelo("Madrid", "Atenas", LocalDateTime.of(2027, 7, 23, 17, 0),
                                LocalDateTime.of(2027, 7, 23, 19, 0), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo11);

                Vuelo vuelo12 = new Vuelo("Oslo", "Madrid", LocalDateTime.of(2027, 7, 23, 17, 30),
                                LocalDateTime.of(2027, 7, 23, 14, 50), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo12);

                Vuelo vuelo13 = new Vuelo("Madrid", "Dublin", LocalDateTime.of(2027, 7, 23, 18, 0),
                                LocalDateTime.of(2027, 7, 23, 19, 30), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo13);

                Vuelo vuelo14 = new Vuelo("Estocolmo", "Madrid", LocalDateTime.of(2027, 7, 23, 18, 20),
                                LocalDateTime.of(2027, 7, 23, 15, 40), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo14);

                Vuelo vuelo15 = new Vuelo("Madrid", "Praga", LocalDateTime.of(2027, 7, 23, 19, 0),
                                LocalDateTime.of(2027, 7, 23, 20, 40), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo15);

                Vuelo vuelo16 = new Vuelo("Bruselas", "Madrid", LocalDateTime.of(2027, 7, 23, 19, 30),
                                LocalDateTime.of(2027, 7, 23, 17, 0), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo16);

                Vuelo vuelo17 = new Vuelo("Madrid", "Varsovia", LocalDateTime.of(2027, 7, 23, 20, 0),
                                LocalDateTime.of(2027, 7, 23, 22, 0), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo17);

                Vuelo vuelo18 = new Vuelo("Copenhague", "Madrid", LocalDateTime.of(2027, 7, 23, 20, 30),
                                LocalDateTime.of(2027, 7, 23, 18, 0), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo18);

                Vuelo vuelo19 = new Vuelo("Madrid", "Budapest", LocalDateTime.of(2027, 7, 23, 21, 0),
                                LocalDateTime.of(2027, 7, 23, 23, 0), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo19);

                Vuelo vuelo20 = new Vuelo("Helsinki", "Madrid", LocalDateTime.of(2027, 7, 23, 21, 30),
                                LocalDateTime.of(2027, 7, 23, 18, 50), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo20);

                Vuelo vuelo21 = new Vuelo("Madrid", "Zurich", LocalDateTime.of(2027, 7, 23, 22, 0),
                                LocalDateTime.of(2027, 7, 23, 23, 30), null, avionPasajeros, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA,
                                Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
                aeropuerto.addVuelo(vuelo21);

                Vuelo vuelo22 = new Vuelo("Múnich", "Madrid", LocalDateTime.of(2027, 7, 23, 22, 30),
                                LocalDateTime.of(2027, 7, 23, 20, 0), null, avionCarga, null, null,
                                Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA,
                                Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
                aeropuerto.addVuelo(vuelo22);

                ZonaAparcamiento z1 = new ZonaAparcamiento(5, 5, 20);
                ZonaAparcamiento z2 = new ZonaAparcamiento(4, 7, 90);

                Finger f1 = new Finger(4.0);
                PuertaEmbarque p1 = new PuertaEmbarque(f1, z1, 150, PuertaEmbarque.TipoPuerta.PASAJEROS);
                PuertaEmbarque p2 = new PuertaEmbarque(f1, z2, 100, PuertaEmbarque.TipoPuerta.MERCANCIAS);
                p2.ocuparPuerta();
                f1.agregarPuertaEmbarque(p1);
                f1.agregarPuertaEmbarque(p2);

                Pista pista1 = new PistaDespegue(1000, 50);
                Pista pista2 = new PistaAterrizaje(800, 40);

                VistaControladorPrincipal vista = new VistaControladorPrincipal();
                ControladorVistaControladorPrincipal controlador = new ControladorVistaControladorPrincipal(vista,
                                aeropuerto, aerolinea, vista);
                controlador.iniciar();
        }
}