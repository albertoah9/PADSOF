import controlador.ControladorVistaOperadorPrincipal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.AeropuertoDestino;
import modelo.Avion;
import modelo.AvionCarga;
import modelo.AvionPasajeros;
import modelo.Factura;
import modelo.Notificacion;
import modelo.OperadorAereo;
import modelo.Vuelo;
import vista.VistaOperadorPrincipal;

public class MainOperador {
    public static void main(String[] args) {
        Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);

        AeropuertoDestino aeropuertoBruselas = new AeropuertoDestino("Brussels International Airport", "Bruselas", "Belgica", AeropuertoDestino.UbiRelCiudad.NORTE);
        aeropuerto.addAeropuertoDestino(aeropuertoBruselas);
        AeropuertoDestino aeropuertoParis = new AeropuertoDestino("Paris Airport", "Paris", "Francia", AeropuertoDestino.UbiRelCiudad.SUR);
        aeropuerto.addAeropuertoDestino(aeropuertoParis);
        AeropuertoDestino aeropuertoNY = new AeropuertoDestino("New York International Airport", "Nueva York", "EEUU", AeropuertoDestino.UbiRelCiudad.OESTE);
        aeropuerto.addAeropuertoDestino(aeropuertoNY);

        Aerolinea aerolinea = new Aerolinea("Iberia", "A2345");
        aeropuerto.addAerolinea(aerolinea);

        OperadorAereo operador = new OperadorAereo("Alberto", aerolinea);
        aeropuerto.addUsuario(operador);
        aeropuerto.setUsuarioActivo(operador);

        Avion avionCarga = new AvionCarga("Boeing", "737", "1234 JBDN", 3354, LocalDate.of(2027, 7, 23), LocalDate.of(2027, 7, 23), false, 3500, false, aerolinea);
        aerolinea.añadirAvion(avionCarga);

        Avion avionPasajeros = new AvionPasajeros("Falkon", "Pedrito", "0000 HDP", 1250, LocalDate.of(2027, 7, 23), LocalDate.of(2027, 7, 23), 12, aerolinea);
        aerolinea.añadirAvion(avionPasajeros);

        Vuelo vuelo1 = new Vuelo("Madrid", "Paris", LocalDateTime.of(2027, 7, 23, 12, 20), LocalDateTime.of(2027, 7, 23, 10, 00), null, avionPasajeros, null, null, Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.SALIDA, Vuelo.ClaseVuelo.PASAJEROS, aerolinea);
        aeropuerto.addVuelo(vuelo1);

        Vuelo vuelo2 = new Vuelo("Rotterdam", "Madrid", LocalDateTime.of(2027, 7, 23, 12, 30), LocalDateTime.of(2027, 7, 23, 9, 20), null, avionCarga, null, null, Vuelo.EstadoVuelo.EN_PREPARACION, aeropuerto, Vuelo.TipoVuelo.LLEGADA, Vuelo.ClaseVuelo.MERCANCIAS, aerolinea);
        aeropuerto.addVuelo(vuelo2);

        Notificacion noti1 = new Notificacion("Bienvenido a AirManager.", Arrays.asList(operador));
        Notificacion noti2 = new Notificacion("Nueva política de despegues de la UE.", Arrays.asList(operador));

        Factura factura1 = new Factura(3500.75, aerolinea);
        Factura factura2 = new Factura(1200.00, aerolinea);
        aerolinea.getFacturas().add(factura1);
        aerolinea.getFacturas().add(factura2);

        VistaOperadorPrincipal vista = new VistaOperadorPrincipal();
        ControladorVistaOperadorPrincipal controlador = new ControladorVistaOperadorPrincipal(vista, aeropuerto, aerolinea);
        controlador.iniciar();
    }
}