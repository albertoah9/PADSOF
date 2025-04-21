import org.junit.Test;

import usuarios.GestorAeropuerto;
import usuarios.OperadorAereo;
import usuarios.Usuario;

import static org.junit.Assert.*;

public class GestorAeropuertoTest {

    @Test
    public void testAñadirUsuario() {
        // Crear instancias necesarias
        Terminal terminal = new Terminal();
        Aerolinea aerolinea = new Aerolinea("Aerolinea A", "España", "AA123");
        GestorAeropuerto gestor = new GestorAeropuerto("Gestor1", "password123", 1);

        // Añadir un usuario de tipo "operador"
        gestor.añadirUsuario("operador", "Operador1", "password1", 2, terminal, aerolinea);
        
        // Verificar que el usuario se ha añadido correctamente
        Usuario usuario = gestor.buscarUsuario(2);
        assertNotNull(usuario);
        assertTrue(usuario instanceof OperadorAereo);
        assertEquals("Operador1", usuario.getNombre());
        assertEquals(aerolinea, ((OperadorAereo) usuario).getAerolineaAsignada());
    }

    @Test
    public void testModificarUsuario() {
        // Crear instancias necesarias
        Terminal terminal = new Terminal();
        Aerolinea aerolinea = new Aerolinea("Aerolinea B", "Francia", "BB456");
        GestorAeropuerto gestor = new GestorAeropuerto("Gestor2", "password456", 3);

        // Añadir un usuario de tipo "controlador"
        gestor.añadirUsuario("controlador", "Controlador1", "password1", 4, terminal, null);
        
        // Modificar el nombre y la contraseña del usuario
        gestor.modificarUsuario(4, "ControladorModificado", "passwordModificada");
        
        // Verificar que los valores fueron modificados correctamente
        Usuario usuario = gestor.buscarUsuario(4);
        assertNotNull(usuario);
        assertEquals("ControladorModificado", usuario.getNombre());
        assertEquals("passwordModificada", usuario.getContraseña());
    }

    @Test
    public void testEliminarUsuario() {
        // Crear instancias necesarias
        Terminal terminal = new Terminal();
        Aerolinea aerolinea = new Aerolinea("Aerolinea C", "México", "CC789");
        GestorAeropuerto gestor = new GestorAeropuerto("Gestor3", "password789", 5);

        // Añadir un usuario de tipo "operador"
        gestor.añadirUsuario("operador", "Operador2", "password2", 6, terminal, aerolinea);
        
        // Eliminar el usuario
        gestor.eliminarUsuario(6);
        
        // Verificar que el usuario fue eliminado
        Usuario usuario = gestor.buscarUsuario(6);
        assertNull(usuario);
    }

    @Test
    public void testCambiarTerminal() {
        // Crear instancias necesarias
        Terminal terminal1 = new Terminal();
        Terminal terminal2 = new Terminal();
        Aerolinea aerolinea = new Aerolinea("Aerolinea D", "Italia", "DD101");
        GestorAeropuerto gestor = new GestorAeropuerto("Gestor4", "password101", 7);

        // Añadir un usuario de tipo "controlador"
        gestor.añadirUsuario("controlador", "Controlador2", "password2", 8, terminal1, null);
        
        // Cambiar la terminal del controlador
        gestor.cambiarTerminal(8, terminal2);
        
        // Verificar que la terminal del controlador ha sido cambiada
        Usuario usuario = gestor.buscarUsuario(8);
        assertNotNull(usuario);
        assertEquals(terminal2, ((ControladorAereo) usuario).getTerminalAsignada());
    }

    @Test
    public void testCambiarAerolinea() {
        // Crear instancias necesarias
        Terminal terminal = new Terminal();
        Aerolinea aerolinea1 = new Aerolinea("Aerolinea E", "Argentina", "EE112");
        Aerolinea aerolinea2 = new Aerolinea("Aerolinea F", "Chile", "FF223");
        GestorAeropuerto gestor = new GestorAeropuerto("Gestor5", "password112", 9);

        // Añadir un usuario de tipo "operador"
        gestor.añadirUsuario("operador", "Operador3", "password3", 10, terminal, aerolinea1);
        
        // Cambiar la aerolínea del operador
        gestor.cambiarAerolinea(10, aerolinea2);
        
        // Verificar que la aerolínea del operador ha sido cambiada
        Usuario usuario = gestor.buscarUsuario(10);
        assertNotNull(usuario);
        assertEquals(aerolinea2, ((OperadorAereo) usuario).getAerolineaAsignada());
    }
}
