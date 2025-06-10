package modelo;

import java.time.LocalDateTime;
// Ya no es necesario importar UUID si usaremos un contador para el ID
// import java.util.UUID; 

public class Pago {
    private static int contador = 0; // Contador estático para generar IDs únicos

    private int id;
    private int facturaId;
    private double monto;
    private LocalDateTime fechaPago;

    public Pago(int facturaId, double monto, LocalDateTime fechaPago) {
        this.id = ++contador; // Asigna un ID único autoincremental
        this.facturaId = facturaId;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    // Getters
    public int getId() { return id; } // El getter devuelve int
    public int getFacturaId() { return facturaId; } // El getter devuelve int
    public double getMonto() { return monto; }
    public LocalDateTime getFechaPago() { return fechaPago; }
}