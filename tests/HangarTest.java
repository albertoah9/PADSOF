import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HangarTest {
    double delta = 0.0001;
    
    @Test
    public void testConstructor() {
        // Creamos un Hangar con los valores iniciales
        Hangar hangar = new Hangar(1, 30.5, 15.2, 40.0, 10, 20.5);
        
        // Verificamos los valores que deberían ser iguales a los pasados al constructor
        assertEquals(1, hangar.getId());
        assertEquals(30.5, hangar.getAncho(), delta);
        assertEquals(15.2, hangar.getAlto(), delta);
        assertEquals(40.0, hangar.getLargo(), delta);
        assertEquals(10, hangar.getNumPlazas());
        assertEquals(20.5, hangar.getCostePorHora(), delta);
        
        // Verificamos que el hangar esté disponible al principio (por defecto no está ocupado)
        assertEquals(true, hangar.estaDisponible());
    }

    @Test
    public void testSetters() {
        Hangar hangar = new Hangar(1, 30.5, 15.2, 40.0, 10, 20.5);

        // Cambiamos los valores con los setters
        hangar.setId(2);
        hangar.setAncho(35.0);
        hangar.setAlto(18.0);
        hangar.setLargo(50.0);
        hangar.setNumPlazas(12);
        hangar.setCostePorHora(18.5);

        // Verificamos que los setters hayan funcionado correctamente
        assertEquals(2, hangar.getId());
        assertEquals(35.0, hangar.getAncho(), delta);
        assertEquals(18.0, hangar.getAlto(), delta);
        assertEquals(50.0, hangar.getLargo(), delta);
        assertEquals(12, hangar.getNumPlazas());
        assertEquals(18.5, hangar.getCostePorHora(), delta);
    }

    @Test
    public void testDisponibilidad() {
        // Creamos un nuevo Hangar
        Hangar hangar = new Hangar(1, 30.5, 15.2, 40.0, 10, 20.5);
        
        // Verificamos que el hangar esté disponible inicialmente
        assertEquals(true, hangar.estaDisponible());

        // Ocupamos el hangar
        hangar.ocupar();
        
        // Verificamos que después de ocuparlo, ya no está disponible
        assertEquals(false, hangar.estaDisponible());

        // Liberamos el hangar
        hangar.liberar();
        
        // Verificamos que después de liberarlo, el hangar está disponible nuevamente
        assertEquals(true, hangar.estaDisponible());
    }

    @Test
    public void testOcupacionYLiberacion() {
        Hangar hangar = new Hangar(1, 30.5, 15.2, 40.0, 10, 20.5);
        
        // Verificamos que al principio esté disponible
        assertEquals(true, hangar.estaDisponible());

        // Ocupamos el hangar
        hangar.ocupar();
        
        // Verificamos que ahora no está disponible
        assertEquals(false, hangar.estaDisponible());

        // Liberamos el hangar
        hangar.liberar();
        
        // Verificamos que esté disponible nuevamente
        assertEquals(true, hangar.estaDisponible());
    }
}
