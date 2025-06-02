import org.junit.Test;

import modelo.Factura;
import modelo.Finger;
import modelo.Hangar;
import modelo.ZonaAparcamiento;
import modelo.ZonaAparcamiento.TipoZonaAparcamiento;

import static org.junit.Assert.*;
import org.junit.Before;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FacturaTest {
    @Before
    public void setUp(){
        Factura.reiniciarContador();
    }
    @Test
    public void testCrearFactura() {
        // Crear instancias de hangares, zonas de aparcamiento y fingers
        Hangar hangar1 = new Hangar(1, 50.0, 20.0, 30.0, 5, 100.0);
        ZonaAparcamiento zona1 = new ZonaAparcamiento(30.0, 25.0, 35.0, 10, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        Finger finger1 = new Finger(40.0, 10.0);
        
        List<Hangar> hangares = Arrays.asList(hangar1);
        List<ZonaAparcamiento> zonasAparcamiento = Arrays.asList(zona1);
        List<Finger> fingers = Arrays.asList(finger1);
        List<Double> horasDeUso = Arrays.asList(2.0);

        // Crear factura
        Factura factura = Factura.crearFactura(hangares, zonasAparcamiento, fingers, horasDeUso);
        
        // Verificar que los valores son correctos
        assertNotNull(factura);
        assertEquals(1, factura.getId());
        assertEquals(Factura.EstadoFactura.PENDIENTE_DE_PAGO, factura.getEstado());
    }

    @Test
    public void testCalcularMontoTotal() {
        Hangar hangar1 = new Hangar(1, 50.0, 20.0, 30.0, 5, 100.0);
        ZonaAparcamiento zona1 = new ZonaAparcamiento(30.0, 25.0, 35.0, 10, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS);
        Finger finger1 = new Finger(40.0, 10.0);

        List<Hangar> hangares = Arrays.asList(hangar1);
        List<ZonaAparcamiento> zonasAparcamiento = Arrays.asList(zona1);
        List<Finger> fingers = Arrays.asList(finger1);
        List<Double> horasDeUso = Arrays.asList(2.0);

        Factura factura = Factura.crearFactura(hangares, zonasAparcamiento, fingers, horasDeUso);
        
        double montoEsperado = (100.0 * 2) + (30.0 * 2) + (40.0 * 2); // 200 + 60 + 80 = 340
        assertEquals(montoEsperado, factura.getMonto(), 0.01);
    }

    @Test
    public void testMarcarComoPagado() {
        Factura factura = Factura.crearFactura(List.of(), List.of(), List.of(), List.of());
        assertEquals(Factura.EstadoFactura.PENDIENTE_DE_PAGO, factura.getEstado());
        
        factura.marcarComoPagado();
        assertEquals(Factura.EstadoFactura.PAGADO, factura.getEstado());
    }
    
    @Test
    public void testToString() {
        Factura factura = Factura.crearFactura(List.of(), List.of(), List.of(), List.of());
        String resultado = factura.toString();
        assertTrue(resultado.contains("Factura{"));
        assertTrue(resultado.contains("monto=0.0"));
        assertTrue(resultado.contains("estado=PENDIENTE_DE_PAGO"));
    }
}
