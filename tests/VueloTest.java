import org.junit.Test;

import PuertaEmbarque.TipoPuerta;
import Vuelo.EstadoVuelo;
import Vuelo.TipoVuelo;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VueloTest {

    @Test
    public void testVuelo() {
        // Crear una Aerolinea
        Aerolinea aerolinea = new Aerolinea("Aerolínea Ejemplo", "España", "AE123");

        // Crear un Avión
        Avion avion = new Avion("Boeing", "737", "ABC123", 5000, LocalDate.of(2023, 5, 1), LocalDate.of(2018, 6, 15), aerolinea, TipoAvion.PASAJEROS);

        // Crear un Aeropuerto de origen y destino
        Aeropuerto aeropuertoOrigen = new Aeropuerto();
        aeropuertoOrigen.setNombre("Aeropuerto de Origen");
        Aeropuerto aeropuertoDestino = new Aeropuerto();
        aeropuertoDestino.setNombre("Aeropuerto de Destino");

        // Crear una Pista
        Pista pista = new Pista(1, 3000.0, 60.0);

        // Crear una Zona de Aparcamiento
        ZonaAparcamiento zonaAparcamiento = new ZonaAparcamiento(10.0, 50.0, 30.0, 100, TipoZonaAparcamiento.PASAJEROS);

        // Crear un Finger
        Finger finger = new Finger(20.5, 15.6);  // Suponiendo que el constructor de Finger necesita id y nombre

        // Crear una Puerta de Embarque
        PuertaEmbarque puertaEmbarque = new PuertaEmbarque(finger, zonaAparcamiento, 200, TipoPuerta.PASAJEROS);

        // Crear una Terminal
        Terminal terminal = new Terminal();

        // Crear el vuelo
        Vuelo vuelo = new Vuelo(1, "AE123", LocalDate.of(2025, 3, 27), LocalDateTime.of(2025, 3, 27, 10, 0), 
                                LocalDate.of(2025, 3, 27), LocalDateTime.of(2025, 3, 27, 12, 0),
                                terminal, avion, pista, puertaEmbarque, Vuelo.EstadoVuelo.ESPERANDO_PISTA,
                                aeropuertoOrigen, aeropuertoDestino, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea);

        // Verificar valores básicos
        assertEquals("AE123", vuelo.getNumeroVuelo());
        assertEquals(aeropuertoOrigen, vuelo.getAeropuertoOrigen());
        assertEquals(aeropuertoDestino, vuelo.getAeropuertoDestino());
        assertEquals(EstadoVuelo.ESPERANDO_PISTA, vuelo.getEstado());
        assertEquals(TipoVuelo.SALIDA, vuelo.getTipoVuelo());
        assertEquals(ClaseVuelo.PASAJEROS, vuelo.getClaseVuelo());
        assertEquals(aerolinea, vuelo.getAerolinea());
        assertEquals(avion, vuelo.getAvion());
        assertNotNull(vuelo.getHoraSalida());
        assertNotNull(vuelo.getHoraLlegada());

        // Verificar si el vuelo está retrasado en el estado correcto
        assertFalse(vuelo.estaRetrasado());  // El vuelo no está retrasado si está en estado 'ESPERANDO_PISTA'

        // Cambiar el estado a RETRASADO
        vuelo.setEstado(Vuelo.EstadoVuelo.RETRASADO);
        assertTrue(vuelo.estaRetrasado());  // Ahora el vuelo debería estar retrasado

        // Verificar los porcentajes de aerolíneas compartidas
        vuelo.addAerolineaCompartida(new Aerolinea("Aerolínea Compartida", "Francia", "AE456"), 40.0);
        assertEquals(40.0, vuelo.getAerolineasCompartidasPorcentajeTotal(), 0.01);

        // Verificar el coste de recursos utilizados
        vuelo.addCosteRecurso(500.0);
        assertEquals(500.0, vuelo.getCosteRecursosUtilizados(), 0.01);

        // Verificar si el vuelo está embarcando
        assertFalse(vuelo.estaEmbarcando());  // El vuelo no está embarcando si el estado no es 'EMBARCANDO'
        vuelo.setEstado(Vuelo.EstadoVuelo.EMBARCANDO);
        assertTrue(vuelo.estaEmbarcando());  // Ahora el vuelo debería estar embarcando

        // Verificar que el controlador del vuelo esté correctamente asignado
        ControladorAereo controlador = new ControladorAereo("Controlador1", "Juan Pérez", "Licencia1");
        vuelo.setControlador(controlador);
        assertEquals(controlador, vuelo.getControlador());

        // Verificar el método toString() para formato correcto
        String expectedToString = "Vuelo AE123 [Aeropuerto de Origen -> Aeropuerto de Destino] - ESPERANDO_PISTA";
        assertEquals(expectedToString, vuelo.toString());
    }
}
