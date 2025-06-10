package modelo;

import java.time.LocalDateTime;
import java.util.UUID;

public class IncidenteSeguridad {
    private String id;
    private String tipoIncidente;
    private Vuelo vueloAfectado; // Puede ser null si el incidente no está ligado a un vuelo específico
    private String descripcion;
    private LocalDateTime fechaHoraReporte;
    private EstadoIncidente estado;

    public enum EstadoIncidente {
        REPORTADO, EN_INVESTIGACION, RESUELTO, CERRADO
    }

    public IncidenteSeguridad(String id, String tipoIncidente, Vuelo vueloAfectado, String descripcion, LocalDateTime fechaHoraReporte, EstadoIncidente estado) {
        this.id = id; // El controlador ya genera el ID
        this.tipoIncidente = tipoIncidente;
        this.vueloAfectado = vueloAfectado;
        this.descripcion = descripcion;
        this.fechaHoraReporte = fechaHoraReporte;
        this.estado = estado;
    }

    // Getters
    public String getId() { return id; }
    public String getTipoIncidente() { return tipoIncidente; }
    public Vuelo getVueloAfectado() { return vueloAfectado; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFechaHoraReporte() { return fechaHoraReporte; }
    public EstadoIncidente getEstado() { return estado; }

    // Setter para estado (si es necesario para el seguimiento)
    public void cambiarEstado(EstadoIncidente nuevoEstado) {
        this.estado = nuevoEstado;
    }
}