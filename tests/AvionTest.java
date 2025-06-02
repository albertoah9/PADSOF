import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import modelo.Aerolinea;
import modelo.Avion;
import modelo.AvionCarga;
import modelo.AvionPasajeros;

public class AvionTest {
    private Aerolinea aerolinea;
    private Avion avion;
    private AvionCarga avionCarga;
    private AvionPasajeros avionPasajeros;

    @Before
    public void setUp() {
        aerolinea = new Aerolinea("Sky Airways", "España", "SA123");

        avion = new Avion("Boeing", "737", "ABC123", 5000,
                LocalDate.of(2023, 5, 10), LocalDate.of(2019, 3, 15), null);
        
        avionCarga = new AvionCarga("Airbus", "A330", "CARGO001", 6000,
                LocalDate.of(2022, 6, 1), LocalDate.of(2020, 4, 10), true, 10000, true, null);
        
        avionPasajeros = new AvionPasajeros("Boeing", "747", "PASS001", 8000,
                LocalDate.of(2021, 8, 15), LocalDate.of(2018, 2, 5), 350, null);
    }

    @Test
    public void testConstructorAvion() {
        assertEquals("Boeing", avion.getMarca());
        assertEquals("737", avion.getModelo());
        assertEquals("ABC123", avion.getMatricula());
        assertEquals(5000, avion.getAutonomia());
        assertNull(avion.getAerolinea());
    }

    @Test
    public void testAsignarAerolinea() {
        avion.asignarAerolinea(aerolinea);
        assertEquals(aerolinea, avion.getAerolinea());
        assertTrue(aerolinea.getFlota().contains(avion));
    }

    @Test
    public void testAñadirYRemoverAvion() {
        aerolinea.añadirAvion(avion);
        aerolinea.añadirAvion(avionCarga);
        aerolinea.añadirAvion(avionPasajeros);
        
        assertEquals(3, aerolinea.getFlota().size());
        assertEquals(aerolinea, avion.getAerolinea());
        assertEquals(aerolinea, avionCarga.getAerolinea());
        assertEquals(aerolinea, avionPasajeros.getAerolinea());
        
        aerolinea.removerAvion(avion);
        
        assertEquals(2, aerolinea.getFlota().size());
        assertNull(avion.getAerolinea());
    }
    
    @Test
    public void testConstructorAvionCarga() {
        assertEquals("Airbus", avionCarga.getMarca());
        assertEquals("A330", avionCarga.getModelo());
        assertEquals("CARGO001", avionCarga.getMatricula());
        assertEquals(10000, avionCarga.getCargaMax(), 0.001);
        assertTrue(avionCarga.isMercPeligrosas());
    }
    
    @Test
    public void testConstructorAvionPasajeros() {
        assertEquals("Boeing", avionPasajeros.getMarca());
        assertEquals("747", avionPasajeros.getModelo());
        assertEquals("PASS001", avionPasajeros.getMatricula());
        assertEquals(350, avionPasajeros.getCapacidad());
    }
}
