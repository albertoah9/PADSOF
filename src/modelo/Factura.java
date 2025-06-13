package modelo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private static int contador = 0;

    private final int id;
    private final LocalDateTime fechaEmision;
    private final LocalDateTime fechaVencimiento;
    private double monto;
    private EstadoFactura estado;
    private Aerolinea aerolinea;

    private List<Descuento> descuentosAplicados;
    private double montoOriginal;

    public enum EstadoFactura {
        PENDIENTE_DE_PAGO, PAGADO
    }

    public Factura(double montoOriginal, Aerolinea aerolinea) {
        this.id = ++contador;
        this.fechaEmision = LocalDateTime.now();
        this.fechaVencimiento = LocalDateTime.now().plusDays(30);
        this.monto = montoOriginal;
        this.montoOriginal = montoOriginal;
        this.estado = EstadoFactura.PENDIENTE_DE_PAGO;
        this.aerolinea = aerolinea;
        this.descuentosAplicados = new ArrayList<>();
    }

    public void aplicarDescuentos(List<Descuento> descuentos, Aerolinea aerolinea, LocalDate mesFactura, int vuelosMes) {
        this.descuentosAplicados = new ArrayList<>();
        this.montoOriginal = this.monto;

        for (Descuento d : descuentos) {
            if (d.estaActivoEn(mesFactura) && d.cumpleCondicion(aerolinea, monto, vuelosMes)) {
                double base = monto;
                double nuevoMonto = d.aplicarDescuento(base);
                monto -= (base - nuevoMonto);
                descuentosAplicados.add(d);
            }
        }
    }

    public double getMonto() {
        return monto;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void marcarComoPagado() {
        this.estado = EstadoFactura.PAGADO;
    }

    public List<Descuento> getDescuentosAplicados() {
        return descuentosAplicados;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fechaEmision='" + fechaEmision + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", monto=" + monto +
                ", estado=" + estado +
                '}';
    }
}
