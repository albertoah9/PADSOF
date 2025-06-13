package modelo;

import java.time.LocalDateTime;

public class IncidenteSeguridad {
    private int id;
    private String tipoIncidente;
    private Vuelo vueloAfectado;
    private String descripcion;
    private LocalDateTime fechaHoraReporte;
    private EstadoIncidente estado;

    private static int contador = 0;

    public enum EstadoIncidente {
        REPORTADO, EN_INVESTIGACION, RESUELTO, CERRADO
    }

    public IncidenteSeguridad(String tipoIncidente, Vuelo vueloAfectado, String descripcion, LocalDateTime fechaHoraReporte, EstadoIncidente estado) {
        this.id = ++contador;
        this.tipoIncidente = tipoIncidente;
        this.vueloAfectado = vueloAfectado;
        this.descripcion = descripcion;
        this.fechaHoraReporte = fechaHoraReporte;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getTipoIncidente() { return tipoIncidente; }
    public Vuelo getVueloAfectado() { return vueloAfectado; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFechaHoraReporte() { return fechaHoraReporte; }
    public EstadoIncidente getEstado() { return estado; }

    public void cambiarEstado(EstadoIncidente nuevoEstado) {
        this.estado = nuevoEstado;
    }
}