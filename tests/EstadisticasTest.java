import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EstadisticasTest {
    private Estadisticas estadisticas;
    private List<Hangar> hangares;
    private List<ZonaAparcamiento> zonasAparcamiento;
    private List<PuertaEmbarque> puertasEmbarque;
    private List<Finger> fingers;
    private List<Vuelo> vuelos;

    @Before
    public void setUp() {
        List<Hangar> hangares = new ArrayList<>(Arrays.asList(
        new Hangar(1, 50.0, 20.0, 30.0, 10, 100.0),
        new Hangar(2, 60.0, 25.0, 35.0, 15, 120.0)
        ));

        List<ZonaAparcamiento> zonasAparcamiento = Arrays.asList(
            new ZonaAparcamiento(50.0, 20.0, 30.0, 10, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS),
            new ZonaAparcamiento(40.0, 25.0, 35.0, 8, ZonaAparcamiento.TipoZonaAparcamiento.MERCANCIAS),
            new ZonaAparcamiento(60.0, 30.0, 40.0, 12, ZonaAparcamiento.TipoZonaAparcamiento.PASAJEROS)
        );
        
        List<Finger> fingers = Arrays.asList(
            new Finger(200.0, 15.0),
            new Finger(180.0, 14.5),
            new Finger(220.0, 16.0),
            new Finger(210.0, 15.5)
        );
        
        List<PuertaEmbarque> puertasEmbarque = Collections.singletonList(
            new PuertaEmbarque(fingers.get(0), zonasAparcamiento.get(0), 150, PuertaEmbarque.TipoPuerta.MERCANCIAS)
        );
        

        Vuelo vuelo1 = new Vuelo(1, LocalDate.of(2025, 3, 27), LocalDateTime.of(2025, 3, 27, 10, 0),
                LocalDate.of(2025, 3, 27), LocalDateTime.of(2025, 3, 27, 12, 0),
                null, null, null, null, Vuelo.EstadoVuelo.EN_HORA,
                null, null, null, null);

        Vuelo vuelo2 = new Vuelo(2, LocalDate.of(2025, 3, 27), LocalDateTime.of(2025, 3, 27, 15, 0),
                LocalDate.of(2025, 3, 27), LocalDateTime.of(2025, 3, 27, 18, 0),
                null, null, null, null, Vuelo.EstadoVuelo.RETRASADO,
                null, null, null, null);

        vuelos = Arrays.asList(vuelo1, vuelo2);

        estadisticas = new Estadisticas(hangares, zonasAparcamiento, puertasEmbarque, fingers, vuelos);
    }

    @Test
    public void testGetUsoHangares() {
        assertEquals(2, estadisticas.getUsoHangares());
    }

    @Test
    public void testGetUsoZonasAparcamiento() {
        assertEquals(3, estadisticas.getUsoZonasAparcamiento());
    }

    @Test
    public void testGetUsoPuertasEmbarque() {
        assertEquals(1, estadisticas.getUsoPuertasEmbarque());
    }

    @Test
    public void testGetUsoFingers() {
        assertEquals(4, estadisticas.getUsoFingers());
    }

    @Test
    public void testGetVuelosEnHora() {
        assertEquals(1, estadisticas.getVuelosEnHora());
    }

    @Test
    public void testGetVuelosRetrasados() {
        assertEquals(1, estadisticas.getVuelosRetrasados());
    }

    @Test
    public void testGetRetrasosPorMes() {
        Map<String, Long> retrasosPorMes = estadisticas.getRetrasosPorMes();
        assertEquals(1, (long) retrasosPorMes.get("MARCH"));
    }

    @Test
    public void testGetRetrasosPorFranjaHoraria() {
        Map<String, Long> retrasosPorFranja = estadisticas.getRetrasosPorFranjaHoraria();
        assertEquals(1, (long) retrasosPorFranja.get("Tarde"));
    }
}