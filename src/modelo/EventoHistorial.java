package modelo;

import java.time.LocalDateTime;

public class EventoHistorial { // Asegúrate de que sea pública y no anidada
    private LocalDateTime fechaHora; // Nombre del atributo como en GestorAeropuerto
    private String tipo;            // Nombre del atributo como en GestorAeropuerto
    private String descripcion;

    public EventoHistorial(LocalDateTime fechaHora, String tipo, String descripcion) {
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() { // Getter acorde al nombre del atributo
        return fechaHora;
    }

    public String getTipo() { // Getter acorde al nombre del atributo
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "EventoHistorial{" +
                "fechaHora=" + fechaHora +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}