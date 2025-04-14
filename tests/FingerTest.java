import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FingerTest {

    @Before
    public void setUp() {
        Finger.reiniciarContador();
    }

    @Test
    public void testCrearFinger() {
        Finger finger = new Finger(50.0, 15.0);

        assertNotNull(finger);
        assertEquals(1, finger.getId());
        assertEquals(50.0, finger.getCostePorHora(), 0.01);
        assertEquals(15.0, finger.getAlturaMax(), 0.01);
        assertEquals(Finger.EstadoFinger.LIBRE, finger.getEstadoFinger());
        assertNull(finger.getPuertaEmbarque());
    }

    @Test
    public void testModificarFinger() {
        Finger finger = new Finger(50.0, 15.0);

        finger.setCostePorHora(60.0);
        finger.setAlturaMax(20.0);
        finger.setEstadoFinger(Finger.EstadoFinger.OCUPADO);

        assertEquals(60.0, finger.getCostePorHora(), 0.01);
        assertEquals(20.0, finger.getAlturaMax(), 0.01);
        assertEquals(Finger.EstadoFinger.OCUPADO, finger.getEstadoFinger());
    }

    @Test
    public void testAsignarPuertaEmbarque() {
        Finger finger = new Finger(50.0, 15.0);
        PuertaEmbarque puerta = new PuertaEmbarque(null, null, 100, null);

        finger.setPuertaEmbarque(puerta);
        assertEquals(puerta, finger.getPuertaEmbarque());
    }
}
