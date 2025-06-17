package modelo;

import java.time.LocalDate;

public class Descuento {
    public enum CondicionAplicacion { 
        IMPORTE_SUPERIOR, 
        VUELOS_MINIMOS 
    }

    private String descripcion;
    private LocalDate inicio;
    private LocalDate fin;
    private int porcentaje;
    private CondicionAplicacion condicion;
    private double valorCondicion;

    public Descuento(String descripcion, LocalDate inicio, LocalDate fin, int porcentaje, 
                                    CondicionAplicacion condicion, double valorCondicion) {
        if (porcentaje < 1 || porcentaje > 50)
            throw new IllegalArgumentException("Porcentaje fuera de rango (1-50)");

        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
        this.porcentaje = porcentaje;
        this.condicion = condicion;
        this.valorCondicion = valorCondicion;
    }

    public boolean estaActivoEn(LocalDate fecha) {
        return (fecha.equals(inicio) || fecha.isAfter(inicio)) && (fecha.equals(fin) || fecha.isBefore(fin));
    }

    public boolean cumpleCondicion(Aerolinea aerolinea, double importeFactura, int vuelosDelMes) {
        return switch (condicion) {
            case IMPORTE_SUPERIOR -> importeFactura > valorCondicion;
            case VUELOS_MINIMOS -> vuelosDelMes >= valorCondicion;
        };
    }

    public double aplicarDescuento(double base) {
        return base * (1 - (porcentaje / 100.0));
    }

    public String getDescripcion() { 
        return descripcion; 
    }

    public int getPorcentaje() { 
        return porcentaje; 
    }

    public CondicionAplicacion getCondicion() { 
        return condicion; 
    }

    public double getValorCondicion() { 
        return valorCondicion; 
    }

    @Override
    public String toString() {
        return String.format("%s (%d%%, %s a %s, Condición: %s %.2f)", 
            descripcion, porcentaje, inicio, fin, condicion, valorCondicion);
    }
}