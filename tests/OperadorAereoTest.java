import static org.junit.Assert.*;
import org.junit.Test;

import Avion.TipoAvion;
import usuarios.OperadorAereo;

import java.time.LocalDate;

public class OperadorAereoTest {

    @Test
    public void testOperadorAereo() {
        // Creamos una aerolínea y un operador aéreo
        Aerolinea aerolinea1 = new Aerolinea("Aerolinea1", "España", "A1");
        Aerolinea aerolinea2 = new Aerolinea("Aerolinea2", "Francia", "A2");
        OperadorAereo operador = new OperadorAereo("Juan", "1234", 1, aerolinea1);

        // Verificamos que la aerolínea asignada al operador sea la correcta
        assertEquals(aerolinea1, operador.getAerolineaAsignada());

        // Cambiamos la aerolínea asignada y verificamos que el cambio se ha realizado correctamente
        operador.cambiarAerolinea(aerolinea2);
        assertEquals(aerolinea2, operador.getAerolineaAsignada());

        // Verificamos el método toString
        String expectedString = "Operador [nombre=Juan, contraseña=1234, id=1, rol=Operador], Aerolínea asignada: Aerolinea2";
        assertEquals(expectedString, operador.toString());

        // Creamos un avión y asignamos la aerolínea
        Avion.TipoAvion tipo = Avion.TipoAvion.PASAJEROS;  // Asumiendo que TipoAvion tiene valores como COMERCIAL
        Avion avion = new Avion("Boeing", "737", "A12345", 5000, LocalDate.of(2022, 1, 1), LocalDate.of(2020, 5, 15), aerolinea2, tipo);
        
        // Probamos el método asignarAvion
        operador.asignarAvion(aerolinea2, avion);

        // Verificamos que el avión haya sido asignado correctamente a la aerolínea
        // Suponiendo que la aerolínea tiene el método añadirAvion y el avión se añade correctamente
        assertTrue(aerolinea2.getFlota().contains(avion));
    }
}
