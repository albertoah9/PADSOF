import org.junit.Test;
import static org.junit.Assert.*;

public class TerminalTest {

    @Test
    public void testAgregarControlador() {
        // Crear una terminal
        Terminal terminal = new Terminal();
        // Crear un controlador
        ControladorAereo controlador = new ControladorAereo("Juan", "password", 1, terminal);
        // Verificar que la terminal inicialmente no tiene controladores
        assertEquals(0, terminal.getControladores().size());
        // Agregar el controlador a la terminal
        terminal.agregarControlador(controlador);
        // Verificar que la terminal tiene 1 controlador
        assertEquals(1, terminal.getControladores().size());
    }

    @Test
    public void testEliminarControlador() {
        // Crear la terminal
        Terminal terminal = new Terminal();

        // Crear controlador
        ControladorAereo controlador1 = new ControladorAereo("Carlos", "contraseña123", 1, terminal);

        // Agregar controlador a la terminal
        terminal.agregarControlador(controlador1);

        // Eliminar el controlador
        terminal.eliminarControlador(controlador1);

        // Verificar que la terminal no tenga controladores
        assertEquals(0, terminal.getControladores().size());
    }

    @Test
    public void testToString() {
        // Crear una terminal
        Terminal terminal = new Terminal();

        // Crear un controlador
        ControladorAereo controlador = new ControladorAereo("Carlos", "password", 1, terminal);

        // Agregar el controlador a la terminal
        terminal.agregarControlador(controlador);

        // Verificar que el toString se produce correctamente
        String resultadoEsperado = "Terminal ID: 1, Controladores: [Carlos]";
        assertEquals(resultadoEsperado, terminal.toString());
    }
}
