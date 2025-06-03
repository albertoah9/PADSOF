
import controlador.ControladorVistaControladorPrincipal;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.ControladorAereo;
import modelo.Terminal;
import vista.VistaControladorPrincipal;

public class MainControlador {
    public static void main(String[] args) {
        Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madridddddd", "España", Aeropuerto.UbiRelCiudad.ESTE);
        Aerolinea aerolinea = new Aerolinea("Iberia", "A2345");
        aeropuerto.addAerolinea(aerolinea);
        Terminal terminalAsignada = new Terminal();
        ControladorAereo controlador = new ControladorAereo("adadwa", "AWDADADW", terminalAsignada);
        aeropuerto.setUsuarioActivo(controlador);
        VistaControladorPrincipal vista = new VistaControladorPrincipal();
        ControladorVistaControladorPrincipal controladorA = new ControladorVistaControladorPrincipal(vista, aeropuerto,
                aerolinea);
        controladorA.iniciar();
    }
}