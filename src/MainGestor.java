import controlador.ControladorVistaGestorPrincipal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import modelo.*;
import vista.VistaGestorPrincipal;

public class MainGestor {
    public static void main(String[] args) {
        Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suarez", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);

        GestorAeropuerto gestor = new GestorAeropuerto("María", "1234", aeropuerto);
        aeropuerto.addUsuario(gestor);
        aeropuerto.setGestor(gestor);
        aeropuerto.setUsuarioActivo(gestor);

        Terminal tPasajeros = new TerminalPasajeros(1250);
        aeropuerto.addTerminal(tPasajeros);
        Terminal tCarga = new TerminalCarga();
        aeropuerto.addTerminal(tCarga);

        Finger finger1 = new Finger(3);

        ZonaAparcamiento parking1 = new ZonaAparcamiento(135.25, 50.0, 5);
        ZonaAparcamiento parking2 = new ZonaAparcamiento(35.25, 50.0, 7);
        aeropuerto.addZonaAparcamiento(parking1);
        aeropuerto.addZonaAparcamiento(parking2);

        PuertaEmbarque puerta1 = new PuertaEmbarque(finger1, parking1, 1200, PuertaEmbarque.TipoPuerta.MERCANCIAS);
        finger1.agregarPuertaEmbarque(puerta1);

        Pista pistaAterrizaje = new PistaAterrizaje(3000.0, 45.0);
        Pista pistaDespegue = new PistaDespegue(3200.0, 50.0);
        aeropuerto.addPista(pistaAterrizaje);
        aeropuerto.addPista(pistaDespegue);

        ControladorAereo controlador1 = new ControladorAereo("Juan", "abcd", tPasajeros);
        aeropuerto.addUsuario(controlador1);
        ControladorAereo controlador2 = new ControladorAereo("Pedro", "abcd1234", tCarga);
        aeropuerto.addUsuario(controlador2);

        Aerolinea aerolinea1 = new Aerolinea("American Airways", "A1212459");
        Aerolinea aerolinea2 = new Aerolinea("Qatar Airways", "T276582");
        aeropuerto.addAerolinea(aerolinea1);
        aeropuerto.addAerolinea(aerolinea2);

        OperadorAereo operador1 = new OperadorAereo("Mike", "ggg222", aerolinea1);
        aeropuerto.addUsuario(operador1);
        OperadorAereo operador2 = new OperadorAereo("Aron", "fgfgfg", aerolinea2);
        aeropuerto.addUsuario(operador2);
        OperadorAereo operador3 = new OperadorAereo("Marcos", "fgf564", aerolinea2);
        operador3.setBloqueado(true);
        aeropuerto.addUsuario(operador3);

        Avion avion1 = new AvionPasajeros("Airbus", "A320", "AA-11", 180, LocalDate.of(2020, 1, 1), LocalDate.of(2010, 1, 1), 150, aerolinea1);
        Vuelo vuelo1 = new Vuelo("Madrid", "Paris", LocalDateTime.of(2025, 1, 2, 10, 0), LocalDateTime.of(2025, 1, 2, 13, 0), tPasajeros, avion1, pistaDespegue, puerta1, Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
        aeropuerto.addVuelo(vuelo1);

        Avion avion2 = new AvionPasajeros("Airbus", "A320", "AA-12", 180, LocalDate.of(2020, 1, 1), LocalDate.of(2010, 1, 1), 150, aerolinea1);
        Vuelo vuelo2 = new Vuelo("Madrid", "Londres", LocalDateTime.of(2025, 1, 4, 11, 0), LocalDateTime.of(2025, 1, 4, 14, 0), tPasajeros, avion2, pistaDespegue, puerta1, Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
        aeropuerto.addVuelo(vuelo2);

        Avion avion3 = new AvionCarga("Boeing", "747", "QR-21", 250, LocalDate.of(2019, 5, 1), LocalDate.of(2011, 1, 1), true, 25000, false, aerolinea1);
        Vuelo vuelo3 = new Vuelo("Madrid", "Doha", LocalDateTime.of(2025, 1, 6, 14, 0), LocalDateTime.of(2025, 1, 6, 18, 0), tCarga, avion3, pistaDespegue, puerta1, Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.MERCANCIAS, aerolinea2);
        aeropuerto.addVuelo(vuelo3);

        aeropuerto.addUsoElementoAeropuerto(new UsoElementoAeropuerto(parking1, aerolinea1, LocalDateTime.of(2025, 1, 3, 8, 0), LocalDateTime.of(2025, 1, 3, 13, 0)));
        aeropuerto.addUsoElementoAeropuerto(new UsoElementoAeropuerto(parking2, aerolinea2, LocalDateTime.of(2025, 1, 5, 9, 0), LocalDateTime.of(2025, 1, 5, 15, 0)));

        Descuento d1 = new Descuento("10% por gasto > 1000€", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31), 10, Descuento.CondicionAplicacion.IMPORTE_SUPERIOR, 1000.0);
        Descuento d2 = new Descuento("25% por mínimo 2 vuelos", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31), 25, Descuento.CondicionAplicacion.VUELOS_MINIMOS, 2);
        aeropuerto.addDescuento(d1);
        aeropuerto.addDescuento(d2);

        aeropuerto.addNotificacion(new Notificacion("Nuevas condiciones del aeropuerto de Túnez", List.of(gestor)));

        VistaGestorPrincipal vistaGestor = new VistaGestorPrincipal(gestor);
        ControladorVistaGestorPrincipal controlador = new ControladorVistaGestorPrincipal(vistaGestor, aeropuerto, gestor);
        controlador.iniciar();
    }
}