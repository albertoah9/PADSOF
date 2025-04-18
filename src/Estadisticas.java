import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estadisticas {
    private List<Hangar> hangares;
    private List<ZonaAparcamiento> zonasAparcamiento;
    private List<PuertaEmbarque> puertasEmbarque;
    private List<Finger> fingers;
    private List<Vuelo> vuelos;

    public Estadisticas(List<Hangar> hangares, List<ZonaAparcamiento> zonasAparcamiento,
                        List<PuertaEmbarque> puertasEmbarque, List<Finger> fingers, List<Vuelo> vuelos) {
        this.hangares = hangares;
        this.zonasAparcamiento = zonasAparcamiento;
        this.puertasEmbarque = puertasEmbarque;
        this.fingers = fingers;
        this.vuelos = vuelos;
    }

    // Estadísticas para el Gestor
    public int getUsoHangares() {
        return hangares != null ? hangares.size() : 0;
    }

    public int getUsoZonasAparcamiento() {
        return zonasAparcamiento != null ? zonasAparcamiento.size() : 0;
    }

    public int getUsoPuertasEmbarque() {
        return puertasEmbarque != null ? puertasEmbarque.size() : 0;
    }

    public int getUsoFingers() {
        return fingers != null ? fingers.size() : 0;
    }

    // Estadísticas para el Operador
    public long getVuelosEnHora() {
        return vuelos != null
                ? vuelos.stream().filter(v -> v.getEstado() == Vuelo.EstadoVuelo.EN_HORA).count()
                : 0;
    }

    public long getVuelosRetrasados() {
        return vuelos != null
                ? vuelos.stream().filter(v -> v.getEstado() == Vuelo.EstadoVuelo.RETRASADO).count()
                : 0;
    }

/*
    public Map<String, Long> getRetrasosPorMes() {
        return vuelos != null
                ? vuelos.stream()
                    .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.RETRASADO)
                    .collect(Collectors.groupingBy(
                        v -> (String) v.getFechaSalida().getMonth().toString()
                        Collectors.counting()))
                : Map.of();
    }
*/

    public Map<String, Long> getRetrasosPorFranjaHoraria() {
        return vuelos != null
                ? vuelos.stream()
                    .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.RETRASADO)
                    .collect(Collectors.groupingBy(v -> {
                        int hora = v.getFechaHora().getHour();
                        if (hora < 6) return "Madrugada";
                        else if (hora < 12) return "Mañana";
                        else if (hora < 18) return "Tarde";
                        else return "Noche";
                    }, Collectors.counting()))
                : Map.of();
    }
}