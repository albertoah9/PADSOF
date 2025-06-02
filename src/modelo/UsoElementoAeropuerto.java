package modelo;
import java.time.Duration;
import java.time.LocalDateTime;

public class UsoElementoAeropuerto {
    private ElementoAeropuerto elementoAeropuerto;
    private Aerolinea aerolinea;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    

    /* Precios */
    private double costoVuelo;
    private double costeFingerPorHora;
    private double costeHangerPorHora;
    private double costeHangarCargaPorHora;
    private double costeAparcamientoPorHora;

    // Constructor
    public UsoElementoAeropuerto(ElementoAeropuerto elementoAeropuerto, Aerolinea aerolinea,
                                  LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.elementoAeropuerto = elementoAeropuerto;
        this.aerolinea = aerolinea;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }

    // Getters y Setters
    public ElementoAeropuerto getElementoAeropuerto() {
        return elementoAeropuerto;
    }

    public void setElementoAeropuerto(ElementoAeropuerto elementoAeropuerto) {
        this.elementoAeropuerto = elementoAeropuerto;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public double getCostoVuelo() {
        return costoVuelo;
    }

    public void setCostoVuelo(double costoVuelo) {
        this.costoVuelo = costoVuelo;
    }

    public double getCosteFingerPorHora() {
        return costeFingerPorHora;
    }

    public void setCosteFingerPorHora(double costeFingerPorHora) {
        this.costeFingerPorHora = costeFingerPorHora;
    }

    public double getCosteHangerPorHora() {
        return costeHangerPorHora;
    }

    public void setCosteHangerPorHora(double costeHangerPorHora) {
        this.costeHangerPorHora = costeHangerPorHora;
    }

    public double getCosteHangarCargaPorHora() {
        return costeHangarCargaPorHora;
    }

    public void setCosteHangarCargaPorHora(double costeHangarCargaPorHora) {
        this.costeHangarCargaPorHora = costeHangarCargaPorHora;
    }

    public double getCosteAparcamientoPorHora() {
        return costeAparcamientoPorHora;
    }

    public void setCosteAparcamientoPorHora(double costeAparcamientoPorHora) {
        this.costeAparcamientoPorHora = costeAparcamientoPorHora;
    }

    // Método para calcular la duración del uso en horas (redondeado hacia arriba)
    public long getDuracionEnHoras() {
        long minutos = Duration.between(fechaHoraInicio, fechaHoraFin).toMinutes();
        return (minutos + 59) / 60; // redondea hacia arriba
    }

    // Método para calcular el coste total según el tipo de elemento
    public double calcularCosteTotal() {
        double horas = getDuracionEnHoras();

        if (elementoAeropuerto instanceof Finger) {
            return horas * costeFingerPorHora;
        } else if (elementoAeropuerto instanceof HangarCarga) {
            return horas * costeHangarCargaPorHora;
        } else if (elementoAeropuerto instanceof Hangar) {
            return horas * costeHangerPorHora;
        } else if (elementoAeropuerto instanceof ZonaAparcamiento) {
            return horas * costeAparcamientoPorHora;
        } else {
            return 0.0;
        }
    }
}