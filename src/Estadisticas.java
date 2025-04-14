
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
        return (int) hangares.stream().count();
    }

    public int getUsoZonasAparcamiento() {
        return (int) zonasAparcamiento.stream().count();
    }

    public int getUsoPuertasEmbarque() {
        return (int) puertasEmbarque.stream().count();
    }

    public int getUsoFingers() {
        return (int) fingers.stream().count();
    }

    // Estadísticas para el Operador
    public long getVuelosEnHora() {
        return vuelos.stream().filter(v -> v.getEstado() == Vuelo.EstadoVuelo.EN_HORA).count();
    }

    public long getVuelosRetrasados() {
        return vuelos.stream().filter(v -> v.getEstado() == Vuelo.EstadoVuelo.RETRASADO).count();
    }

    public Map<String, Long> getRetrasosPorMes() {
        return vuelos.stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.RETRASADO)
                .collect(Collectors.groupingBy(v -> v.getFechaSalida().getMonth().toString(), Collectors.counting()));
    }

    public Map<String, Long> getRetrasosPorFranjaHoraria() {
        return vuelos.stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.RETRASADO)
                .collect(Collectors.groupingBy(v -> {
                    int hora = v.getHoraSalida().getHour();
                    if (hora < 6) return "Madrugada";
                    else if (hora < 12) return "Mañana";
                    else if (hora < 18) return "Tarde";
                    else return "Noche";
                }, Collectors.counting()));
    }
}
