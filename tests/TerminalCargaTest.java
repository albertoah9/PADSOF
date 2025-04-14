import org.junit.Test;
import static org.junit.Assert.*;

public class TerminalCargaTest {

    @Test
    public void testCrearTerminalCarga() {
        // Crear una TerminalCarga
        TerminalCarga terminalCarga = new TerminalCarga();

        // Verificar que la terminal no tiene controladores asignados inicialmente
        assertEquals(0, terminalCarga.getControladores().size());
    }
}
