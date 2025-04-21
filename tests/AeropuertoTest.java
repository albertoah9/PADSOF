import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import usuarios.GestorAeropuerto;
import usuarios.OperadorAereo;
import usuarios.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AeropuertoTest {
    
    private Aeropuerto aeropuerto;
    private Usuario gestor;
    private Usuario operador;
    private PuertaEmbarque puertaEmbarque;
    private Terminal terminal;
    private Vuelo vuelo;
    private Aerolinea aerolinea;
    private Avion avion;
    private Pista pista;
    private Finger finger;
    private ZonaAparcamiento zonaAparcamiento;

    @Before
    public void setUp() {
        // Crear el aeropuerto
        aeropuerto = new Aeropuerto();
        
        // Crear usuarios
        gestor = new GestorAeropuerto("gestor1", "password", 1);
        operador = new OperadorAereo("operador1", "password", 2, aerolinea);
        
        // Asignar el usuario activo
        aeropuerto.setUsuarioActivo(gestor);
        
        finger = new Finger(20.5, 3.2);
        zonaAparcamiento = new ZonaAparcamiento(20.5, 25.4, 2054.6, 1, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        puertaEmbarque = new PuertaEmbarque(finger, zonaAparcamiento, 150, PuertaEmbarque.TipoPuerta.PASAJEROS);

        avion = new Avion("Boeing", "737", "ABC123", 12000, LocalDate.of(2025, 1, 1), LocalDate.of(2015, 1, 1), aerolinea);
        pista = new Pista(1, 3000, 50);

        // Crear el vuelo
        LocalDate fechaSalida = LocalDate.of(2025, 3, 28);
        LocalDateTime horaSalida = LocalDateTime.of(2025, 3, 28, 10, 0);
        LocalDate fechaLLegada = LocalDate.of(2025, 3, 28);
        LocalDateTime horaLlegada = LocalDateTime.of(2025, 3, 28, 12, 0);

        vuelo = new Vuelo(1, fechaSalida, horaSalida, fechaLLegada, horaLlegada, terminal, avion, pista, puertaEmbarque,
                          Vuelo.EstadoVuelo.APARCADO, aeropuerto, Vuelo.TipoVuelo.LLEGADA, Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
        terminal = new Terminal();
        aerolinea = new Aerolinea("Air Global", "España", "AG456");
    }
    
    @Test
    public void testStatus() {
        // Asignar un status
        aeropuerto.setStatus(Aeropuerto.Status.OK);
        
        // Obtener el status actual y comprobar si es OK
        assertEquals(Aeropuerto.Status.OK, aeropuerto.getStatus());
    }

    @Test
    public void testErrorStatus() {
        // Asignar un error status
        aeropuerto.setStatus(Aeropuerto.Status.ERROR);
        
        // Obtener el status actual y comprobar si es ERROR
        assertEquals(Aeropuerto.Status.ERROR, aeropuerto.getStatus());
    }

    @Test
    public void testAddPuertaEmbarque() {
        // Crear los objetos necesarios para la puerta de embarque
        Aeropuerto.Status status= aeropuerto.addPuertaEmbarque(puertaEmbarque);
        assertEquals(Aeropuerto.Status.OK, status);
        assertTrue(aeropuerto.getPuertasEmbarque().contains(puertaEmbarque));
    }

    
    @Test
    public void testAddPuertaEmbarqueSinPermisos() {
        // Cambiar a un usuario sin permisos para añadir puerta de embarque
        aeropuerto.setUsuarioActivo(operador);
        
        Aeropuerto.Status status = aeropuerto.addPuertaEmbarque(puertaEmbarque);
        assertEquals(Aeropuerto.Status.ERROR, status);  // Debería devolver ERROR
        assertFalse(aeropuerto.getPuertasEmbarque().contains(puertaEmbarque));  // No se debe añadir
    }

    @Test
    public void testAddTerminal() {
        // Comprobar que se puede añadir una terminal si el usuario es un gestor
        Aeropuerto.Status status = aeropuerto.addTerminal(terminal);
        assertEquals(Aeropuerto.Status.OK, status);
        assertTrue(aeropuerto.getTerminales().contains(terminal));
    }


    @Test
    public void testAddTerminalSinPermisos() {
        // Cambiar a un usuario sin permisos para añadir terminal
        aeropuerto.setUsuarioActivo(operador);
        
        Aeropuerto.Status status = aeropuerto.addTerminal(terminal);
        assertEquals(Aeropuerto.Status.ERROR, status);  // Debería devolver ERROR
        assertFalse(aeropuerto.getTerminales().contains(terminal));  // No se debe añadir
    }
    
    @Test
    public void testAddVuelo() {
        // Establecer usuario operador
        aeropuerto.setUsuarioActivo(operador);
    
        // Añadir vuelo
        aeropuerto.addVuelo(vuelo);
    
        // Verificar que el vuelo se añade correctamente
        assertTrue(aeropuerto.getVuelos().contains(vuelo));
    }
    
    
    @Test
    public void testAddVueloSinPermisos() {
        // Cambiar a un usuario sin permisos (gestor)
        aeropuerto.setUsuarioActivo(gestor);
        // Guardar el número de vuelos antes de intentar añadir uno nuevo
        int vuelosAntes = aeropuerto.getVuelos().size();
        // Intentar añadir un vuelo con un usuario que no es operador
        aeropuerto.addVuelo(vuelo);
        // Guardar el número de vuelos después del intento
        int vuelosDespues = aeropuerto.getVuelos().size();
        // Verificar que el vuelo NO se ha añadido
        assertEquals(vuelosAntes, vuelosDespues);
        assertFalse(aeropuerto.getVuelos().contains(vuelo));
    }
    
    
    
    @Test
    public void testAddAerolinea() {
        // Añadir una aerolínea
        aeropuerto.addAerolinea(aerolinea);
        
        // Verificar que la aerolínea ha sido añadida
        assertTrue(aeropuerto.getAerolineas().contains(aerolinea));
    }
    
    @Test
    public void testAsignarTerminalAVuelo() {
        // Asignar una terminal a un vuelo
        aeropuerto.setUsuarioActivo(gestor);  // Asegurarse de que el usuario es gestor
        aeropuerto.addTerminal(terminal);
        aeropuerto.asignarTerminalAVuelo(vuelo);
        
        // Comprobar que el vuelo tiene una terminal asignada
        assertNotNull(vuelo.getTerminal());
    }
    
    @Test
    public void testAsignarPistaAVuelo() {
        // Asignar una pista a un vuelo
        Pista pista = new Pista(33, 1535.23, 68.3);
    aeropuerto.addPista(pista);  // Asumimos que esta función agrega la pista al aeropuerto

    // Asignar la pista al vuelo
    vuelo.setPista(pista);  // Se supone que el Vuelo tiene un método setPista() para asignar la pista

    // Comprobar que el vuelo tiene una pista asignada
    assertNotNull(vuelo.getPista());  // Verificar que el vuelo tiene la pista asignada
    }
}
