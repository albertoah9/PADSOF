import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HangarCargaTest {
    double delta = 0.0001;
    
    @Test
    public void testConstructor() {
        // Creamos un HangarCarga con los valores iniciales
        HangarCarga hangarCarga = new HangarCarga(1, 30.5, 15.2, 40.0, 10, 20.5, -5, true);
        
        // Verificamos los valores que deberían ser iguales a los pasados al constructor
        assertEquals(1, hangarCarga.getId());
        assertEquals(30.5, hangarCarga.getAncho(), delta);
        assertEquals(15.2, hangarCarga.getAlto(), delta);
        assertEquals(40.0, hangarCarga.getLargo(), delta);
        assertEquals(10, hangarCarga.getNumPlazas());
        assertEquals(20.5, hangarCarga.getCostePorHora(), delta);
        
        // Verificamos los valores específicos de HangarCarga
        assertEquals(-5, hangarCarga.getTemperatura());
        assertEquals(true, hangarCarga.isMercPeligrosas());
        
        // Verificamos que el hangar esté disponible al principio (por defecto no está ocupado)
        assertEquals(true, hangarCarga.estaDisponible());
    }

    @Test
    public void testSetters() {
        HangarCarga hangarCarga = new HangarCarga(1, 30.5, 15.2, 40.0, 10, 20.5, -5, true);

        // Cambiamos los valores con los setters
        hangarCarga.setId(2);
        hangarCarga.setAncho(35.0);
        hangarCarga.setAlto(18.0);
        hangarCarga.setLargo(50.0);
        hangarCarga.setNumPlazas(12);
        hangarCarga.setCostePorHora(18.5);
        hangarCarga.setTemperatura(10);
        hangarCarga.setMercPeligrosas(false);

        // Verificamos que los setters hayan funcionado correctamente
        assertEquals(2, hangarCarga.getId());
        assertEquals(35.0, hangarCarga.getAncho(), delta);
        assertEquals(18.0, hangarCarga.getAlto(), delta);
        assertEquals(50.0, hangarCarga.getLargo(), delta);
        assertEquals(12, hangarCarga.getNumPlazas());
        assertEquals(18.5, hangarCarga.getCostePorHora(), delta);
        assertEquals(10, hangarCarga.getTemperatura());
        assertEquals(false, hangarCarga.isMercPeligrosas());
    }

    @Test
    public void testDisponibilidad() {
        // Creamos un nuevo HangarCarga
        HangarCarga hangarCarga = new HangarCarga(1, 30.5, 15.2, 40.0, 10, 20.5, -5, true);
        
        // Verificamos que el hangar esté disponible inicialmente
        assertEquals(true, hangarCarga.estaDisponible());

        // Ocupamos el hangar
        hangarCarga.ocupar();
        
        // Verificamos que después de ocuparlo, ya no está disponible
        assertEquals(false, hangarCarga.estaDisponible());

        // Liberamos el hangar
        hangarCarga.liberar();
        
        // Verificamos que después de liberarlo, el hangar está disponible nuevamente
        assertEquals(true, hangarCarga.estaDisponible());
    }

    @Test
    public void testOcupacionYLiberacion() {
        HangarCarga hangarCarga = new HangarCarga(1, 30.5, 15.2, 40.0, 10, 20.5, -5, true);
        
        // Verificamos que al principio esté disponible
        assertEquals(true, hangarCarga.estaDisponible());

        // Ocupamos el hangar
        hangarCarga.ocupar();
        
        // Verificamos que ahora no está disponible
        assertEquals(false, hangarCarga.estaDisponible());

        // Liberamos el hangar
        hangarCarga.liberar();
        
        // Verificamos que esté disponible nuevamente
        assertEquals(true, hangarCarga.estaDisponible());
    }
}
