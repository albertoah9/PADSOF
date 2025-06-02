import static org.junit.Assert.*;
import org.junit.Test;

import modelo.Aerolinea;
import modelo.ControladorAereo;
import modelo.Terminal;
import notificaciones.Notificacion;
import usuarios.OperadorAereo;
import usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class NotificacionTest {

    @Test
    public void testNotificacion() {
        // Creamos instancias de los usuarios
        Usuario operador = new OperadorAereo("Juan", "1234", 1, new Aerolinea("Aerolinea1", "España", "A1"));
        Usuario controlador = new ControladorAereo("Pedro", "5678", 2, new Terminal());

        // Usamos ArrayList en lugar de Arrays.asList para permitir agregar y eliminar destinatarios
        List<Usuario> destinatarios = new ArrayList<>();
        destinatarios.add(operador);
        destinatarios.add(controlador);
        
        // Creamos la notificación
        Notificacion notificacion = new Notificacion("Notificación importante", 1, destinatarios);

        // Verificamos los valores iniciales
        assertEquals(1, notificacion.getId());
        assertEquals("Notificación importante", notificacion.getMensaje());
        assertEquals(2, notificacion.getDestinatarios().size());
        assertTrue(notificacion.isActivada());
        assertFalse(notificacion.isLeida());

        // Probamos los métodos de activación y desactivación
        notificacion.desactivarNotificacion();
        assertFalse(notificacion.isActivada());

        notificacion.activarNotificacion();
        assertTrue(notificacion.isActivada());

        // Probamos marcar como leída
        notificacion.marcarComoLeida();
        assertTrue(notificacion.isLeida());

        // Probamos agregar y eliminar destinatarios
        Usuario nuevoUsuario = new OperadorAereo("Maria", "abcd", 3, new Aerolinea("Aerolinea2", "Francia", "A2"));
        notificacion.agregarDestinatario(nuevoUsuario);
        assertEquals(3, notificacion.getDestinatarios().size());

        notificacion.eliminarDestinatario(nuevoUsuario);
        assertEquals(2, notificacion.getDestinatarios().size());

        // Verificamos la función de mostrar la notificación
        notificacion.mostrarNotificacion();
    }
}
