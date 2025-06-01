import org.junit.Test;

import modelo.ControladorAereo;
import modelo.Terminal;

import static org.junit.Assert.*;

public class ControladorAereoTest {

    @Test
    public void testConstructor() {
        // Creamos una terminal
        Terminal terminal = new Terminal();

        // Creamos un controlador aéreo
        ControladorAereo controlador = new ControladorAereo("Juan", "contraseña123", 1, terminal);

        // Verificamos que el controlador se haya creado correctamente
        assertEquals("Juan", controlador.getNombre());
        assertEquals("contraseña123", controlador.getContraseña());
        assertEquals(1, controlador.getId());
        assertEquals(terminal, controlador.getTerminalAsignada());
    }

    @Test
    public void testAsignarTerminal() {
        // Creamos dos terminales
        Terminal terminal1 = new Terminal();
        Terminal terminal2 = new Terminal();

        // Creamos un controlador aéreo
        ControladorAereo controlador = new ControladorAereo("Ana", "contraseña456", 2, terminal1);

        // Verificamos que la terminal inicial esté asignada correctamente
        assertEquals(terminal1, controlador.getTerminalAsignada());

        // Asignamos una nueva terminal
        controlador.setTerminalAsignada(terminal2);

        // Verificamos que la terminal se haya cambiado correctamente
        assertEquals(terminal2, controlador.getTerminalAsignada());
        assertTrue(terminal2.getControladores().contains(controlador));
        assertFalse(terminal1.getControladores().contains(controlador));
    }

    @Test
    public void testCambiarTerminal() {
        // Creamos tres terminales
        Terminal terminal1 = new Terminal();
        Terminal terminal2 = new Terminal();
        Terminal terminal3 = new Terminal();

        // Creamos un controlador aéreo
        ControladorAereo controlador = new ControladorAereo("Carlos", "contraseña789", 3, terminal1);

        // Cambiamos la terminal del controlador
        controlador.cambiarTerminal(terminal2);

        // Verificamos que la terminal haya cambiado correctamente
        assertEquals(terminal2, controlador.getTerminalAsignada());

        // Cambiamos la terminal del controlador a otra
        controlador.cambiarTerminal(terminal3);

        // Verificamos que la terminal haya cambiado correctamente a la nueva
        assertEquals(terminal3, controlador.getTerminalAsignada());
    }

    @Test
    public void testToString() {
        // Creamos una terminal
        Terminal terminal = new Terminal();

        // Creamos un controlador aéreo
        ControladorAereo controlador = new ControladorAereo("Laura", "contraseña101", 4, terminal);

        // Verificamos el formato correcto de toString
        String expectedString = "Usuario [Nombre=Laura, Contraseña=contraseña101, Id=4, Tipo=Controlador], Terminal asignada: " + terminal.getId();
        assertEquals(expectedString, controlador.toString());
    }

    @Test
    public void testAgregarControladorATerminal() {
        // Creamos una terminal
        Terminal terminal = new Terminal();

        // Creamos un controlador aéreo
        ControladorAereo controlador = new ControladorAereo("Luis", "contraseña202", 5, terminal);

        // Verificamos que el controlador ha sido asignado a la terminal
        assertTrue(terminal.getControladores().contains(controlador));

        // Cambiamos de terminal
        Terminal terminal2 = new Terminal();
        controlador.setTerminalAsignada(terminal2);

        // Verificamos que la terminal anterior ya no tiene al controlador
        assertFalse(terminal.getControladores().contains(controlador));
        assertTrue(terminal2.getControladores().contains(controlador));
    }


    @Test
    public void testEliminarControladorDeTerminal() {
        // Creamos dos terminales
        Terminal terminal1 = new Terminal();
        Terminal terminal2 = new Terminal();

        // Creamos un controlador aéreo y asignamos a terminal1
        ControladorAereo controlador = new ControladorAereo("Pedro", "contraseña303", 6, terminal1);

        // Eliminamos el controlador de terminal1
        terminal1.eliminarControlador(controlador);

        // Verificamos que el controlador ha sido removido de terminal1
        assertFalse(terminal1.getControladores().contains(controlador));
        assertNull(controlador.getTerminalAsignada());
    }
}
