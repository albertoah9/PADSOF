import static org.junit.Assert.*;
import org.junit.Test;

public class PistaTest {

    @Test
    public void testPistaAterrizajeDisponibilidad() {
        // Creamos una pista de aterrizaje
        Pista pistaAterrizaje = new PistaAterrizaje(1, 3000, 50);
        
        // Verificamos que la pista está libre inicialmente
        assertEquals(Pista.EstadoPista.LIBRE, pistaAterrizaje.verificarDisponibilidad());

        // Cambiamos el estado de la pista a ocupada
        pistaAterrizaje.setOcupada(true);
        
        // Verificamos que la pista ahora está ocupada
        assertEquals(Pista.EstadoPista.OCUPADA, pistaAterrizaje.verificarDisponibilidad());
    }

    @Test
    public void testPistaDespegueDisponibilidad() {
        // Creamos una pista de despegue
        Pista pistaDespegue = new PistaDespegue(2, 2500, 45);
        
        // Verificamos que la pista de despegue está libre inicialmente
        assertEquals(Pista.EstadoPista.LIBRE, pistaDespegue.verificarDisponibilidad());

        // Cambiamos el estado de la pista a ocupada
        pistaDespegue.setOcupada(true);
        
        // Verificamos que la pista de despegue está ocupada
        assertEquals(Pista.EstadoPista.OCUPADA, pistaDespegue.verificarDisponibilidad());
    }

    @Test
    public void testPistaAterrizajeGettersAndSetters() {
        Pista pistaAterrizaje = new PistaAterrizaje(1, 3000, 50);

        // Verificamos los valores iniciales
        assertEquals(1, pistaAterrizaje.getId());
        assertEquals(3000, pistaAterrizaje.getLongitud(), 0.01);
        assertEquals(50, pistaAterrizaje.getAnchura(), 0.01);

        // Modificamos los valores
        pistaAterrizaje.setId(2);
        pistaAterrizaje.setLongitud(3500);
        pistaAterrizaje.setAnchura(60);

        // Verificamos que los valores se han actualizado correctamente
        assertEquals(2, pistaAterrizaje.getId());
        assertEquals(3500, pistaAterrizaje.getLongitud(), 0.01);
        assertEquals(60, pistaAterrizaje.getAnchura(), 0.01);
    }

    @Test
    public void testPistaDespegueGettersAndSetters() {
        Pista pistaDespegue = new PistaDespegue(3, 2500, 45);

        // Verificamos los valores iniciales
        assertEquals(3, pistaDespegue.getId());
        assertEquals(2500, pistaDespegue.getLongitud(), 0.01);
        assertEquals(45, pistaDespegue.getAnchura(), 0.01);

        // Modificamos los valores
        pistaDespegue.setId(4);
        pistaDespegue.setLongitud(2800);
        pistaDespegue.setAnchura(50);

        // Verificamos que los valores se han actualizado correctamente
        assertEquals(4, pistaDespegue.getId());
        assertEquals(2800, pistaDespegue.getLongitud(), 0.01);
        assertEquals(50, pistaDespegue.getAnchura(), 0.01);
    }
}
