import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZonaAparcamientoTest {

    @Before
    public void setUp() {
        // Reiniciar el contador para asegurar que el ID empiece en 1 en cada test
        ZonaAparcamiento.reiniciarContador();
    }

    @Test
    public void testCalcularArea() {
        // Crear una zona de aparcamiento
        ZonaAparcamiento zona = new ZonaAparcamiento(5.0, 10.0, 20.0, 50, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        
        // Calcular el área esperada
        double areaEsperada = 10.0 * 20.0;
        
        // Verificar que el área calculada es la correcta
        assertEquals(areaEsperada, zona.calcularArea(), 0.01);
    }

    @Test
    public void testCalcularCosteEstancia() {
        // Crear una zona de aparcamiento
        ZonaAparcamiento zona = new ZonaAparcamiento(5.0, 10.0, 20.0, 50, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        
        // Definir el número de horas
        int horas = 3;
        
        // Calcular el coste esperado
        double costeEsperado = 5.0 * horas;
        
        // Verificar que el coste calculado es el correcto
        assertEquals(costeEsperado, zona.calcularCosteEstancia(horas), 0.01);
    }

    @Test
    public void testOcuparPlaza() {
        // Crear una zona de aparcamiento con 2 plazas
        ZonaAparcamiento zona = new ZonaAparcamiento(5.0, 10.0, 20.0, 2, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        
        // Ocupar una plaza
        zona.ocuparPlaza();
        
        // Verificar que hay 1 plaza ocupada
        assertEquals(1, zona.getPlazasDisponibles());
        
        // Ocupar la segunda plaza
        zona.ocuparPlaza();
        
        // Verificar que no hay plazas disponibles
        assertEquals(0, zona.getPlazasDisponibles());
        
        // Intentar ocupar una plaza cuando ya no hay disponibles
        zona.ocuparPlaza();
        
        // Verificar que el mensaje de error se muestra correctamente (esto se verifica manualmente ya que es un mensaje en consola)
        // Pero no podemos comprobarlo con assert directamente, por lo tanto no lo incluimos en el test
    }

    @Test
    public void testLiberarPlaza() {
        // Crear una zona de aparcamiento con 2 plazas
        ZonaAparcamiento zona = new ZonaAparcamiento(5.0, 10.0, 20.0, 2, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        
        // Ocupar una plaza
        zona.ocuparPlaza();
        
        // Verificar que hay 1 plaza ocupada
        assertEquals(1, zona.getPlazasDisponibles());
        
        // Liberar la plaza
        zona.liberarPlaza();
        
        // Verificar que hay 2 plazas disponibles
        assertEquals(2, zona.getPlazasDisponibles());
        
        // Intentar liberar una plaza cuando no hay plazas ocupadas
        zona.liberarPlaza();
        zona.liberarPlaza();
        
        // Verificar que el número de plazas disponibles sigue siendo correcto
        assertEquals(2, zona.getPlazasDisponibles());
    }

    @Test
    public void testToString() {
        // Crear una zona de aparcamiento
        ZonaAparcamiento zona = new ZonaAparcamiento(5.0, 10.0, 20.0, 50, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        
        // Obtener el resultado esperado del método toString
        String resultadoEsperado = "Zona de Aparcamiento ID: 1\n" +
            "Tipo: PASAJEROS\n" +
            "Tamaño: 10.0m x 20.0m (200.0m²)\n" +
            "Número de plazas: 50\n" +
            "Coste por hora: 5.0€/hora\n" +
            "Plazas disponibles: 50";
        
        // Verificar que el toString de la zona es correcto
        assertEquals(resultadoEsperado, zona.toString());
    }
}
