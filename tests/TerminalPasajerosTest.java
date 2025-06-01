import org.junit.Test;

import modelo.Finger;
import modelo.TerminalPasajeros;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class TerminalPasajerosTest {

    @Test
    public void testCrearTerminalPasajeros() {
        // Crear una TerminalPasajeros con aforo máximo de 100
        TerminalPasajeros terminalPasajeros = new TerminalPasajeros(100);

        // Verificar que el aforo máximo se ha establecido correctamente
        assertEquals(100, terminalPasajeros.getAforoMax());
    }

    @Test
    public void testAgregarFinger() {
        // Crear una TerminalPasajeros
        TerminalPasajeros terminalPasajeros = new TerminalPasajeros(100);

        // Crear un Finger
        Finger finger = new Finger(10.0, 5.0);

        // Agregar un Finger a la terminal
        terminalPasajeros.addFinger(finger);

        // Verificar que el Finger ha sido agregado correctamente
        assertEquals(1, terminalPasajeros.getFingers().size());
    }

    @Test
    public void testToString() {
        // Crear una TerminalPasajeros
        TerminalPasajeros terminalPasajeros = new TerminalPasajeros(100);

        // Crear un Finger y agregarlo
        Finger finger = new Finger(10.0, 5.0);
        terminalPasajeros.addFinger(finger);

        // Verificar el comportamiento del método toString
        String resultadoEsperado = "Terminal ID: " + terminalPasajeros.getId() + ", Controladores: [Ninguno]";
        assertTrue(terminalPasajeros.toString().contains("Controladores: [Ninguno]"));
    }
}
