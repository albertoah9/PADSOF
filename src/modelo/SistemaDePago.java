package modelo;
/* import java.util.HashMap;
import java.util.Map;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public class SistemaDePago {
    private Map<Integer, Factura> facturas; // Almacena facturas por su ID

    public SistemaDePago() {
        this.facturas = new HashMap<>();
    }

    // Método para agregar una nueva factura al sistema
    public void agregarFactura(Factura factura) {
        facturas.put(factura.getId(), factura);
    }

    // Método para obtener una factura por ID
    public Factura obtenerFacturaPorId(int id) {
        return facturas.get(id);
    }

    // Método para procesar un pago de una factura específica
    public void procesarPago(int idFactura, String numeroDeTarjeta, boolean modoSeguro) {
        Factura factura = facturas.get(idFactura);

        if (factura == null) {
            System.out.println("No se encontró una factura con el ID: " + idFactura);
            return;
        }

        if (factura.getEstado() == Factura.EstadoFactura.PAGADO) {
            System.out.println("La factura con ID " + idFactura + " ya está pagada.");
            return;
        }

        // Validamos el número de tarjeta
        if (!TeleChargeAndPaySystem.isValidCardNumber(numeroDeTarjeta)) {
            System.out.println("El número de tarjeta es inválido.");
            return;
        }

        try {
            // Intentamos realizar el cobro con los datos de la factura
            TeleChargeAndPaySystem.charge(numeroDeTarjeta, factura.getFechaVencimiento().toString(), factura.getMonto(), modoSeguro);
            
            // Si el pago es exitoso, marcamos la factura como pagada
            factura.marcarComoPagado();
            System.out.println("Pago realizado con éxito para la factura ID: " + idFactura);

        } catch (InvalidCardNumberException e) {
            System.out.println("Error: " + e.getMessage() + " Número de tarjeta: " + e.getCardNumberString());
        } catch (FailedInternetConnectionException e) {
            System.out.println("Error de conexión a internet: " + e.getMessage());
        } catch (OrderRejectedException e) {
            System.out.println("El pago fue rechazado. Número de tarjeta: " + e.getCardNumberString());
        }
    }
}

*/
