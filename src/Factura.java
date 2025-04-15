import java.time.LocalDateTime;

public class Factura {
    private static int contador = 0;

    private final int id;
    private final LocalDateTime fechaEmision;  // Cambiado a String
    private final LocalDateTime fechaVencimiento;  // Cambiado a String
    private double monto;
    private EstadoFactura estado;

    public enum EstadoFactura {
        PENDIENTE_DE_PAGO, PAGADO
    }

    // Constructor para crear una factura solo con monto y fecha de vencimiento
    public Factura(double monto) {
        this.id = ++contador;
        this.fechaEmision = java.time.LocalDateTime.now();
        this.fechaVencimiento = java.time.LocalDateTime.now().plusDays(30);
        this.monto = monto;
        this.estado = EstadoFactura.PENDIENTE_DE_PAGO;
    }

    // Getter para el monto
    public double getMonto() {
        return monto;
    }

    // Getter para el ID
    public int getId() {
        return id;
    }

    // Getter para la fecha de emisión
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    // Getter para la fecha de vencimiento
    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    // Estado de la factura
    public EstadoFactura getEstado() {
        return estado;
    }

    // Marcar factura como pagada
    public void marcarComoPagado() {
        this.estado = EstadoFactura.PAGADO;
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
