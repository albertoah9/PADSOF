import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import modelo.Avion;
import modelo.Avion.TipoAvion;

public class AerolineaTest {
    private Aerolinea aerolinea;
    private Avion avion1;
    private Avion avion2;

    @Before
    public void setUp() {
        aerolinea = new Aerolinea("Sky Airways", "España", "SA123");

        // Crear los aviones con el tipoAvion necesario
        avion1 = new Avion("Boeing", "737", "ABC123", 5000,
                LocalDate.of(2023, 5, 10), LocalDate.of(2019, 3, 15), null, Avion.TipoAvion.PASAJEROS);
        avion2 = new Avion("Airbus", "A320", "XYZ789", 4000,
                LocalDate.of(2022, 8, 20), LocalDate.of(2020, 7, 30), null, Avion.TipoAvion.PASAJEROS);
    }

    @Test
    public void testConstructor() {
        assertEquals("Sky Airways", aerolinea.getNombre());
        assertEquals("España", aerolinea.getPais());
        assertEquals("SA123", aerolinea.getCodigoAerolinea());
        assertNotNull(aerolinea.getFlota());
        assertNotNull(aerolinea.getFacturas());
        assertNotNull(aerolinea.getOperadores());
    }

    @Test
    public void testSetters() {
        aerolinea.setNombre("Air Global");
        aerolinea.setPais("Francia");
        aerolinea.setCodigoAerolinea("AG456");
        
        assertEquals("Air Global", aerolinea.getNombre());
        assertEquals("Francia", aerolinea.getPais());
        assertEquals("AG456", aerolinea.getCodigoAerolinea());
    }

    @Test
    public void testAñadirYRemoverAvion() {
        aerolinea.añadirAvion(avion1);
        aerolinea.añadirAvion(avion2);
        
        assertEquals(2, aerolinea.getFlota().size());
        assertEquals(aerolinea, avion1.getAerolinea());
        assertEquals(aerolinea, avion2.getAerolinea());
        
        aerolinea.removerAvion(avion1);
        
        assertEquals(1, aerolinea.getFlota().size());
        assertNull(avion1.getAerolinea());
    }
}
