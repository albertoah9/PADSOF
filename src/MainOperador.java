import controlador.ControladorVistaOperadorPrincipal;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.OperadorAereo;
import vista.VistaOperadorPrincipal;

public class MainOperador {
    public static void main(String[] args) {
        Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madridddddd", "España", Aeropuerto.UbiRelCiudad.ESTE);
        Aerolinea aerolinea = new Aerolinea("Iberia", "A2345");
        OperadorAereo operador = new OperadorAereo("Alberto", aerolinea);
        aeropuerto.setUsuarioActivo(operador);
        VistaOperadorPrincipal vista = new VistaOperadorPrincipal();
        ControladorVistaOperadorPrincipal controlador = new ControladorVistaOperadorPrincipal(vista, aeropuerto, aerolinea);
        controlador.iniciar();
    }
}