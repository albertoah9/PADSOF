import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import modelo.GestorAeropuerto;

public class Main {
   public static void main(String[] args) {
      Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suarez", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);
      
      // Gestor
      GestorAeropuerto gestor = new GestorAeropuerto("María", "1234");
      aeropuerto.setGestor(gestor);
      aeropuerto.iniciarSesion(gestor.getNombre(), gestor.getContraseña());

      // Elementos del aeropuerto
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

      // Pistas
      Pista pistaAterrizaje = new PistaAterrizaje(3000.0, 45.0);
      Pista pistaDespegue = new PistaDespegue(3200.0, 50.0);
      aeropuerto.addPista(pistaAterrizaje);
      aeropuerto.addPista(pistaDespegue);

      // Controladores
      ControladorAereo controlador1 = new ControladorAereo("Juan", "abcd", tPasajeros);
      aeropuerto.addControlador(controlador1);
      ControladorAereo controlador2 = new ControladorAereo("Pedro", "abcd1234", tCarga);
      aeropuerto.addControlador(controlador2);

      // Aerolíneas y operadores
      Aerolinea aerolinea1 = new Aerolinea("American Airways","A1212459");
      Aerolinea aerolinea2 = new Aerolinea("Qatar Airways", "T276582");

      OperadorAereo operador1 = new OperadorAereo("Mike", "ggg222", aerolinea1);
      aeropuerto.añadirOperador(operador1, aerolinea1);
      OperadorAereo operador2 = new OperadorAereo("Aron", "fgfgfg", aerolinea2);
      aeropuerto.añadirOperador(operador2, aerolinea2);

      // Vuelos
      Avion avion1 = new AvionPasajeros("Boeing", "737", "AA-123", 180, LocalDate.of(2025, 1, 26), LocalDate.of(2015, 3, 26), 120, aerolinea1);
      Vuelo vuelo1 = new Vuelo("Madrid", "New York", LocalDateTime.now(), LocalDateTime.now().plus(50, ChronoUnit.MINUTES), tPasajeros, avion1, pistaDespegue, puerta1, Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea1);
      aeropuerto.addVuelo(vuelo1);

      // Simulación de operaciones
      aeropuerto.asignarTerminalAVuelo(vuelo1);
      vuelo1.setEstado(Vuelo.EstadoVuelo.EMBARCANDO);
      System.out.println("Vuelo actualizado: " + vuelo1.getEstado());
   }
}
